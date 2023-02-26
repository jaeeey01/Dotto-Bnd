package com.dotto.app.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRoleSwitchRequest {

    private Long memberNo;
    private String roles;
}
