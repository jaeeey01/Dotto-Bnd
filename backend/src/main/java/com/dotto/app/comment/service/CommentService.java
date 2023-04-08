package com.dotto.app.comment.service;

import com.dotto.app.comment.dto.DottoPostCommentCreateRequest;
import com.dotto.app.comment.dto.DottoPostCommentDto;
import com.dotto.app.comment.entity.PostComment;
import com.dotto.app.comment.repository.PostCommentRepository;
import com.dotto.app.exception.CommentNotFoundException;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.post.repository.DottoPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final PostCommentRepository postCommentRepository;
    private final MemberRepository memberRepository;
    private final DottoPostRepository dottoPostRepository;

    public List<DottoPostCommentDto> readAll(Long postNo){
        return DottoPostCommentDto.toDto(
                postCommentRepository.findAllPostComment(postNo)
        );
    }

    @Transactional
    public void create(DottoPostCommentCreateRequest req){
        postCommentRepository.save( DottoPostCommentCreateRequest.toEntity(req, memberRepository, dottoPostRepository, postCommentRepository));
    }

    @Transactional
    public void delete(Long id){
        PostComment postComment = postCommentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        postComment.findDeletablePostComment().ifPresentOrElse(postCommentRepository::delete, postComment::delete);

    }
}
