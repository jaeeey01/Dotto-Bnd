package com.dotto.app.policy.controller;

import com.dotto.app.common.response.Response;
import com.dotto.app.policy.service.PolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Policy Controller", tags = "Policy")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @ApiOperation(value = "이용약관 조회", notes = "이용약관을 조회한다")
    @GetMapping("/policy")
    @ResponseStatus(HttpStatus.OK)
    public Response read(){
        return Response.success(policyService.read());
    }
}
