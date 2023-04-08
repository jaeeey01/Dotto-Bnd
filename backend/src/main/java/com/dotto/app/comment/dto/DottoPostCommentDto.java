package com.dotto.app.comment.dto;

import com.dotto.app.comment.entity.PostComment;
import com.dotto.app.handler.NestedConvertHelper;
import com.dotto.app.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class DottoPostCommentDto {
    private Long cNo;
    private String content;
    private MemberDto member;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp createdAt;
    private List<DottoPostCommentDto> children;


    public static List<DottoPostCommentDto> toDto(List<PostComment> comments){
        NestedConvertHelper helper = NestedConvertHelper.newInstance(
            comments,
                c -> new DottoPostCommentDto(c.getCNo(), c.isDeleted()? null : c.getCContent(), c.isDeleted()? null: MemberDto.toDto(c.getWriter()), c.getCreatedAt(), new ArrayList<>()),
                c -> c.getParents(),
                c -> c.getCNo(),
                d -> d.getChildren()
        );
        return helper.convert();
    }
}
