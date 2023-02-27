package com.dotto.app.oauth.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "OAuth Controller" ,tags ="oauth" )
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OAuthController {


}
