package com.dotto.app.comment.dto;

import com.dotto.app.comment.entity.PostComment;
import com.dotto.app.comment.repository.PostCommentRepository;
import com.dotto.app.exception.CommentNotFoundException;
import com.dotto.app.exception.DottoPostNotFoundException;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.post.repository.DottoPostRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.Optional;

@ApiModel(value = "댓글 생성 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DottoPostCommentCreateRequest {

    @ApiModelProperty(value = "댓글", notes = "댓글을 입력해주세요", required = true, example = "my comment")
    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;

    @ApiModelProperty(value = "게시글 아이디", notes = "게시글 아이디를 입력해주세요", example = "7")
    @NotNull(message = "게시글 아이디를 입력해주세요.")
    @Positive(message = "올바른 게시글 아이디를 입력해주세요.")
    private Long postNo;

    @ApiModelProperty(hidden = true)
    @Null
    private Long memberNo;

    @ApiModelProperty(value = "부모 댓글 아이디", notes = "부모 댓글 아이디를 입력해주세요", example = "7")
    private Long parentNo;


    public static PostComment toEntity(DottoPostCommentCreateRequest req, MemberRepository memberRepository, DottoPostRepository dottoPostRepository, PostCommentRepository postCommentRepository){
        return new PostComment(
                req.getContent(),
                memberRepository.findById(req.getMemberNo()).orElseThrow(MemberNotFoundException::new),
                dottoPostRepository.findById(req.getPostNo()).orElseThrow(DottoPostNotFoundException::new),
                Optional.ofNullable(req.getParentNo()).map(parentNo -> postCommentRepository.findById(parentNo).orElseThrow(CommentNotFoundException::new)).orElse(null)
        );
    }
}
