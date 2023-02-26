package com.dotto.app.policy.controller;

import com.dotto.app.policy.dto.PolicyAgreeRequest;
import com.dotto.app.common.response.Response;
import com.dotto.app.policy.service.PolicyAgreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "PolicyAgree Controller", tags = "PolicyAgree")
@RestController("/api")
@RequiredArgsConstructor
public class PolicyAgreeController {

    private final PolicyAgreeService policyAgreeService;

    @ApiOperation(value = "이용약관 동의", notes = "이용약관을 동의 한다")
    @PostMapping("/policyAgree")
    @ResponseStatus(HttpStatus.OK)
    public Response create (PolicyAgreeRequest req){
        return Response.success(policyAgreeService.create(req));
    }
}
