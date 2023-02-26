package com.dotto.app.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowingList {
    private Long memberNo;
    private String nickname;
}
