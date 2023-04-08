package com.dotto.app.comment.controller;

import com.dotto.app.aop.AssignMemberNo;
import com.dotto.app.comment.dto.DottoPostCommentCreateRequest;
import com.dotto.app.comment.service.CommentService;
import com.dotto.app.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Post Comment Controller", tags = "Comment")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 목록 조회", notes = "댓글 목록을 조회한다")
    @GetMapping("/post/comments/{postNo}")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll( @PathVariable Long postNo){
        return Response.success(commentService.readAll(postNo));
    }


    @ApiOperation(value = "댓글 생성" ,notes = "댓글 생성을 한다")
    @PostMapping("/post/comments")
    @AssignMemberNo
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(DottoPostCommentCreateRequest req){
        commentService.create(req);
        return Response.success();
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 한다")
    @DeleteMapping("/post/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long id){
        commentService.delete(id);
        return Response.success();
    }
}
