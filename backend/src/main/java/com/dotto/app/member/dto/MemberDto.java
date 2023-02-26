package com.dotto.app.member.dto;

import com.dotto.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long memberNo;
    private String id;
    private String nickname;

    public static MemberDto toDto(Member member){
        return new MemberDto(member.getMemberNo(), member.getId(), member.getNickname());
    }


}
