package com.dotto.app.oauth.service;

import com.dotto.app.config.constants.LoginType;
import com.dotto.app.oauth.service.socical.GoogleOAuth;
import com.dotto.app.oauth.service.socical.KakaoOAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Service
public class OAuthService {

    private final GoogleOAuth googleOAuth;
    private final KakaoOAuth kakaoOAuth;
    private final HttpServletResponse response;

    public void request(LoginType loginType){
        String redirectURL;

        switch (loginType) {
            case GOOGLE: {
                redirectURL = googleOAuth.getOauthURL();
            } break;
            case KAKAO: {
                redirectURL = kakaoOAuth.getOauthURL();
            } break;
            default: {
                throw new IllegalArgumentException("알 수 없는 로그인 형식.");
            }
        }
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void requestToken(LoginType loginType,String code){
        String redirectURL;
        RestTemplate restTemplate = new RestTemplate();
        switch (loginType) {
            case GOOGLE: {
                redirectURL = googleOAuth.getTokenURL(code);
            } break;
            case KAKAO: {
                redirectURL = kakaoOAuth.getTokenURL(code);
            } break;
            default: {
                throw new IllegalArgumentException("알 수 없는 로그인 형식");
            }
        }

        String response = restTemplate.exchange(redirectURL, HttpMethod.POST,null, String.class).getBody();
        System.out.println(response);
    }




}
