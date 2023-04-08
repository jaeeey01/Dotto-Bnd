package com.dotto.app.comment.repository;

import com.dotto.app.comment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    @Query("select c from PostComment c left join fetch c.parents where c.cNo = :pNo ")
    Optional<PostComment> findByParentNo(Long pNo);

    @Query("select c from PostComment c left join fetch c.writer left join fetch c.parents " +
            "where c.dottoPost.postNo = :postNo order by c.parents.cNo asc nulls first , c.cNo asc ")
    List<PostComment> findAllPostComment(Long postNo);
}
