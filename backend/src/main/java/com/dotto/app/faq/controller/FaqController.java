package com.dotto.app.faq.controller;

import com.dotto.app.common.response.Response;
import com.dotto.app.faq.dto.FaqCreateRequest;
import com.dotto.app.faq.dto.FaqUpdateRequest;
import com.dotto.app.faq.service.FaqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Faq Controller", tags = "Faq")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    //FAQ 작성 post
    @ApiOperation(value = "FAQ 등록", notes = "FAQ를 등록한다")
    @PostMapping("/faq")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(FaqCreateRequest req){
        return Response.success(faqService.create(req));
    }
    //FAQ 수정 put
    @ApiOperation(value = "FAQ 수정", notes = "FAQ를 수정한다")
    @PutMapping("/faq/{faqNo}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(
            @ApiParam(value = "FAQ 번호", required = true)
            @PathVariable(value = "faqNo") Long faqNo,
            FaqUpdateRequest req){
        return Response.success(faqService.update(faqNo, req));
    }
    //FAQ 삭제 delete


    //FAQ 목록조회 get

    //FAQ 검색 get

}