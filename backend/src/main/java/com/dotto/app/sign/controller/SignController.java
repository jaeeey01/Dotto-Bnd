package com.dotto.app.sign.controller;

import com.dotto.app.config.constants.LoginType;
import com.dotto.app.common.response.Response;
import com.dotto.app.oauth.dto.request.OAuthSignUpDTO;
import com.dotto.app.sign.dto.SignInRequest;
import com.dotto.app.sign.dto.SignUpRequest;
import com.dotto.app.oauth.dto.request.OAuthSignInRequest;
import com.dotto.app.oauth.dto.request.OAuthSignUpRequest;
import com.dotto.app.oauth.service.OAuthService;
import com.dotto.app.sign.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.dotto.app.common.response.Response.success;

@Api(value = "Sign Controller" ,tags ="sign" )
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;
    private final OAuthService oAuthService;

    @ApiOperation(value = "회원가입", notes = "회원가입 요청을 한다")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response SignUp( @RequestBody SignUpRequest req){
        signService.SignUp(req);
        return success();
    }

    @ApiOperation(value = "oauth 회원가입", notes = "SNS 회원가입")
    @PostMapping("/sign-up/oauth/{loginType}")
    @ResponseStatus(HttpStatus.OK)
    public Response oAuthSignUp(
                            @PathVariable(name = "loginType") String loginType,
                            @RequestHeader(value = "Authorization") String idToken,
                            @RequestBody OAuthSignUpRequest req) {
        signService.oAuthSignUp(new OAuthSignUpDTO(idToken,loginType,req));
        return success();

    }


    @ApiOperation(value = "로그인", notes = "로그인 요청을 한다")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response SignIn(@RequestBody SignInRequest req){
        return success(signService.SignIn(req));
    }


    @ApiOperation(value = "oauth 로그인", notes = "SNS 로그인")
    @PostMapping("/sign-in/oauth/{loginType}")
    @ResponseStatus(HttpStatus.OK)
    public Response oAuthSignIn(
                            @PathVariable(name = "loginType") String loginType,
                            @RequestHeader(value = "Authorization") String idToken) {
        return signService.oAuthSignIn(new OAuthSignInRequest(idToken,loginType));

    }

    @ApiOperation(value = "액세스 토큰 재발급", notes = "리프레쉬 토큰을 통해 액세스토큰을 재발급 받는다")
    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public Response refreshTokenResponse ( @RequestHeader(value = "Authorization") String refreshToken){
        return success(signService.refreshToken(refreshToken));
    }


    // --- temp ---
    @ApiOperation(value = "oauth 로그인 code 발급", notes = "oauth code 요청")
    @PostMapping("/sign-in/{loginType}")
    @ResponseStatus(HttpStatus.OK)
    public void oAuthCodeTest(
            @PathVariable(name = "loginType") LoginType socialLoginType) {
        log.info("--- SNS 로그인 요청 :: {} Social Login", socialLoginType);
        oAuthService.request(socialLoginType);
    }

    @ApiOperation(value = "oauth 로그인 token 발급", notes = "oauth 로그인 token 발급")
    @GetMapping("/{loginType}/callback")
    @ResponseStatus(HttpStatus.OK)
    public void googleCallbackTest(
            @PathVariable(name = "loginType") LoginType loginType,
            @RequestParam(name = "code") String code) {
        log.info("--- {} auth code :: {}", loginType,code);
        oAuthService.requestToken(loginType,code);
    }


}
