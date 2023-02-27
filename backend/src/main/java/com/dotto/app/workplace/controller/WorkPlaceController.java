package com.dotto.app.workplace.controller;

import com.dotto.app.aop.AssignMemberNo;
import com.dotto.app.workplace.dto.WorkPlaceCreateRequest;
import com.dotto.app.common.response.Response;
import com.dotto.app.workplace.service.WorkPlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "workPlace Controller", tags = "WorkPlace")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkPlaceController {
    private final WorkPlaceService workPlaceService;

    @ApiOperation(value = "내 작업실 생성 요청", notes = "내 작업실 생성 요청을 한다")
    @PostMapping("/workplace")
    @ResponseStatus(HttpStatus.CREATED)
    @AssignMemberNo
    public Response create( @ModelAttribute WorkPlaceCreateRequest req){
        return Response.success(workPlaceService.create(req));
    }

}
