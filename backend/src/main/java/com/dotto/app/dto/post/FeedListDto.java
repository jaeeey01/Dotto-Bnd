package com.dotto.app.dto.post;

import com.dotto.app.entity.post.Feed;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class FeedListDto {
    private Long totalElements;
    private Integer totalPage;
    private boolean hasNext;
    private List<FeedPostDto> feedPostDtoList;

    public static FeedListDto toDto(Page<Feed> dto, List<FeedPostDto> postDto){
        return new FeedListDto(dto.getTotalElements(), dto.getTotalPages(), dto.hasNext(), postDto);
    }
}
