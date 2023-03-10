package com.dotto.app.post.controller;

import com.dotto.app.aop.AssignMemberNo;
import com.dotto.app.post.dto.DottoPostCreateRequest;
import com.dotto.app.post.dto.DottoPostUpdateRequest;
import com.dotto.app.post.dto.PostReadCondition;
import com.dotto.app.common.response.Response;
import com.dotto.app.post.service.DottoPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "DottoPost Controller", tags = "dottoPost")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DottoPostController {

    private final DottoPostService dottoPostService;

    @ApiOperation(value = "닷투 게시글 생성", notes = "닷투 게시글을 생성한다")
    @PostMapping("/dottopost")
    @ResponseStatus(HttpStatus.CREATED)
    @AssignMemberNo //게시글 생성시 인증된 정보를 통해 게시글 작성자를 화면에서 받지않고 직접 지정
    public Response create(@ModelAttribute DottoPostCreateRequest req){
        return Response.success(dottoPostService.create(req));
    }

    @ApiOperation(value = "닷투 게시판 전체목록 조회", notes = "닷투게시판 목록을 조회한다")
    @GetMapping("/dottopost")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(PostReadCondition cond){
        return Response.success(dottoPostService.readAll(cond));
    }

    @ApiOperation(value = "닷투 게시판 상세 글 보기", notes = "닷투게시판 상세 글을 조회한다")
    @GetMapping("/dottopost/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(
            @ApiParam(value = "게시물 id", required = true)
            @PathVariable Long id){
       return Response.success(dottoPostService.read(id));
    }

    @ApiOperation(value = "닷투 게시판 글 수정", notes = "닷투게시판에 작성한 글을 수정 한다")
    @PutMapping("/dottopost/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(
            @ApiParam(value = "게시물 id", required = true)
            @PathVariable Long id, DottoPostUpdateRequest req){
        return Response.success(dottoPostService.update(id, req));
    }

    @ApiOperation(value = "닷투 게시판 글 삭제", notes = "닷투게시판 글을 삭제한다")
    @DeleteMapping("/dottopost/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(
            @ApiParam(value = "게시물 id", required = true)
            @PathVariable Long id){
        dottoPostService.delete(id);
        return Response.success();
    }
}
