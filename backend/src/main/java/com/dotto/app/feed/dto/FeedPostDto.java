package com.dotto.app.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedPostDto {

    private Long feedNo;
    private String content;
    private int likeHit;
    private Long memberNo;

    public static FeedPostDto toDto(Long feedNo, String content, int likeHit, Long memberNo){
        return new FeedPostDto(feedNo, content, likeHit, memberNo);
    }
}
