package com.dotto.app.member.service;

import com.dotto.app.member.entity.Member;
import com.dotto.app.member.entity.ProfileImage;
import com.dotto.app.config.constants.RoleType;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.exception.MemberRoleAuthorizationException;
import com.dotto.app.member.dto.*;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.common.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
//    private final FileService fileService;

    public MemberDto read(Long memberNo){
        return MemberDto.toDto(memberRepository.findByMemberNoAndDeletedYnEqualsN(memberNo).orElseThrow(MemberNotFoundException::new));
    }

    @Transactional
    public MemberUpdateResponse update(Long memberNo, MemberUpdateRequest req){
        Member member = memberRepository.findByMemberNoAndDeletedYnEqualsN(memberNo).orElseThrow(MemberNotFoundException::new);
        member.update(req.getNickname(),req.getIntro());
        return new MemberUpdateResponse(memberNo);
    }

    @Transactional
    public void delete(Long memberNo){
        Member member = memberRepository.findByMemberNoAndDeletedYnEqualsN(memberNo).orElseThrow(MemberNotFoundException::new);
        member.deleted();
    }

    @Transactional
    public MemberProfileUploadResponse uploadProfile(MemberProfileUploadRequest req){
        Member member = memberRepository.findById(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        if(member.getProfileImage()!=null){
//            deletedImage(member.getProfileImage());
        }
        Member.ProfileImageUpdateResult rs = member.uploadProfile(req);
//        uploadImage(rs.getAddedImageFile(), rs.getAddedImages());
        return new MemberProfileUploadResponse(member.getMemberNo());
    }

    @Transactional
    public void deletedProfile(Long id){
        log.info("deletedProfile memberNo ={}", id);
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.deletedProfile(member.getProfileImage());
//        deletedImage(member.getProfileImage());

    }

    public void roleSwitch(MemberRoleSwitchRequest req){
        Member member = memberRepository.findWithRolesByMemberNo(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        if (req.getRoles().contains(String.valueOf(RoleType.ROLE_ARTIST))){
            member.roleSwitcher();
        }else if(req.getRoles().contains(String.valueOf(RoleType.ROLE_SWITCHER))){
            member.roleSwitchArtist();
        }else {
            throw new MemberRoleAuthorizationException("권한이 없습니다.");
        }
    }


    public boolean existsByNickname(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

    public boolean existsById(String id){
        return memberRepository.existsById(id);
    }


//    private void uploadImage(MultipartFile files, ProfileImage image){
//        fileService.upload(files, image.getName());
//    }

//    private void deletedImage(ProfileImage image){
//        fileService.deleted(image.getName());
//    }
}
