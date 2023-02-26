package com.dotto.app.feed.dto;

import com.dotto.app.member.dto.MemberDto;
import com.dotto.app.feed.entity.Feed;
import com.dotto.app.feed.entity.FeedImage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedDetailDto {

    private Long feedNo;
    private String content;
    private List<FeedImage> images;
    private MemberDto member;

    public static FeedDetailDto toDto(Feed feed){
        return new FeedDetailDto(
                feed.getFeedNo(),
                feed.getContent(),
                feed.getFeedImages(),
                MemberDto.toDto(feed.getMember())
        );

    }

}
