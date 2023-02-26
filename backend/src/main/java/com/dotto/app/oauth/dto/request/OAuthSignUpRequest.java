package com.dotto.app.oauth.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@NoArgsConstructor
@Getter
public class OAuthSignUpRequest {

    private String nickName;
    private String gender;
    private String phone;


}
