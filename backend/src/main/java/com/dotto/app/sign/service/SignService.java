package com.dotto.app.sign.service;

import com.dotto.app.config.token.TokenHelper;
import com.dotto.app.common.response.Response;
import com.dotto.app.oauth.dto.request.OAuthSignInRequest;
import com.dotto.app.oauth.dto.request.OAuthSignUpDTO;
import com.dotto.app.member.entity.Member;
import com.dotto.app.member.entity.Role;
import com.dotto.app.config.constants.RoleType;
import com.dotto.app.exception.*;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.member.repository.RoleRepository;
import com.dotto.app.sign.dto.RefreshTokenResponse;
import com.dotto.app.sign.dto.SignInRequest;
import com.dotto.app.sign.dto.SignInResponse;
import com.dotto.app.sign.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.dotto.app.common.response.Response.failure;
import static com.dotto.app.common.response.Response.success;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenHelper accessTokenHelper;
    private final TokenHelper refreshTokenHelper;

    @Transactional
    public void SignUp(SignUpRequest req){
        validSignUpInfo(req);
        String encodedPwd = passwordEncoder.encode(req.getPassword());
        List<Role> roles = List.of(roleRepository.findByRoleType(RoleType.ROlE_NORMAL).orElseThrow(RoleNotFoundException::new));
        memberRepository.save(
                new Member(
                        req.getId(),
                        encodedPwd,
                        req.getNickname(),
                        req.getGender(),
                        req.getPhone(),
                        roles,
                        req.getLoginType()
                )
        );
    }

    @Transactional
    public void oAuthSignUp(OAuthSignUpDTO req){
        List<Role> roles = List.of(roleRepository.findByRoleType(RoleType.ROlE_NORMAL).orElseThrow(RoleNotFoundException::new));
        memberRepository.save(
                new Member(
                        req.getId(),
                        req.getPassword(),
                        req.getReq().getNickName(),
                        req.getReq().getGender(),
                        req.getReq().getPhone(),
                        roles,
                        req.getLoginType()
                )
        );
    }


    @Transactional(readOnly = true)
    public SignInResponse SignIn(SignInRequest req){
        Member member = memberRepository.findWithRolesById(req.getId()).orElseThrow(MemberNotFoundException::new);
        validPwd(req, member);
        TokenHelper.PrivateClaims privateClaims = createPrivateClaims(member);
        String accessToken = accessTokenHelper.createToken(privateClaims);
        String refreshToken = refreshTokenHelper.createToken(privateClaims);
        String roles = String.valueOf(member.getRoles().stream()
                .map(memberRole -> memberRole.getRole())
                .map(role -> role.getRoleType())
                .map(roleType -> roleType.toString())
                .collect(Collectors.toList()));
        return new SignInResponse(accessToken, refreshToken, member.getNickname(),roles);
    }

    // sign in 이랑 리펙토링 필요
    @Transactional(readOnly = true)
    public Response oAuthSignIn(OAuthSignInRequest req){
        if (!memberRepository.existsById( req.getId() )) return failure(444,"회원 정보 없음");
        Member member = memberRepository.findWithRolesById(req.getId()).orElseThrow(MemberNotFoundException::new);
        TokenHelper.PrivateClaims privateClaims = createPrivateClaims(member);
        String accessToken = accessTokenHelper.createToken(privateClaims);
        String refreshToken = refreshTokenHelper.createToken(privateClaims);
        String roles = String.valueOf(member.getRoles().stream()
                .map(memberRole -> memberRole.getRole())
                .map(role -> role.getRoleType())
                .map(roleType -> roleType.toString())
                .collect(Collectors.toList()));
        return success(new SignInResponse(accessToken, refreshToken, member.getNickname(),roles));
    }

    private TokenHelper.PrivateClaims createPrivateClaims(Member member){
        return new TokenHelper.PrivateClaims(
                                            String.valueOf(member.getMemberNo()),
                                            member.getRoles().stream()
                                                            .map(memberRole -> memberRole.getRole())
                                                            .map(role -> role.getRoleType())
                                                            .map(roleType -> roleType.toString())
                                                            .collect(Collectors.toList())
        );
    }

    public RefreshTokenResponse refreshToken(String rToken){
        TokenHelper.PrivateClaims privateClaims = refreshTokenHelper.parse(rToken).orElseThrow(RefreshTokenFailureException::new);
        String accessToken = accessTokenHelper.createToken(privateClaims);
        return new RefreshTokenResponse(accessToken);
    }

    private void validPwd(SignInRequest req, Member member){
        if(!passwordEncoder.matches(req.getPassword(),member.getPassword())){
            throw new LoginFailureException();
        }

    }

    private void validSignUpInfo(SignUpRequest req){
        if(memberRepository.existsById(req.getId())){
            throw new MemberEmailAlreadyExistsException(req.getId());
        }
        if(memberRepository.existsByNickname(req.getNickname())){
            throw new MemberNicknameAlreadyExistsException(req.getNickname());
        }
    }
}
