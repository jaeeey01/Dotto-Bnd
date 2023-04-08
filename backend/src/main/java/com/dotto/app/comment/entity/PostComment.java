package com.dotto.app.comment.entity;

import com.dotto.app.common.entity.EntityDate;
import com.dotto.app.member.entity.Member;
import com.dotto.app.post.entity.DottoPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class PostComment extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cNo;

    @Column(nullable = false)
    @Lob
    private String cContent;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postNo", nullable = false)
    private DottoPost dottoPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_cNo")
    private PostComment parents;

    @OneToMany(mappedBy = "parents", orphanRemoval = true)
    private List<PostComment> children = new ArrayList<>();

    public PostComment(String cContent, Member writer, DottoPost dottoPost, PostComment parents){
        this.cContent = cContent;
        this.writer = writer;
        this.dottoPost = dottoPost;
        this.parents = parents;
        this.isDeleted = false;
    }

    public Optional<PostComment> findDeletablePostComment(){
        return hasChildren() ? Optional.empty() : Optional.of(findDeletablePostCommentByParents());
    }

    private PostComment findDeletablePostCommentByParents(){
        if(isDeletableParent()){
            PostComment deletableParent = getParents().findDeletablePostCommentByParents();
            if(getParents().getChildren().size()==1) return deletableParent;

        }
        return this;
    }
    private boolean hasChildren(){
        return getChildren().size() != 0;
    }

    private boolean isDeletableParent(){
        return getParents()!= null && getParents().isDeleted() && getChildren().size() ==1;
    }

    public void delete(){
        this.isDeleted = true;
    }




}
