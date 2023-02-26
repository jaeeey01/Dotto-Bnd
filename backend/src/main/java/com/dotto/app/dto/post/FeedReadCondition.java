package com.dotto.app.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedReadCondition {

    @NotNull(message = "페이지 번호를 입력해 주세요")
    @PositiveOrZero(message = "페이지 번호를 입력해 주세요(0 이상)")
    private Integer page;

    @NotNull(message = "페이지 크기를 입력해 주세요")
    @Positive(message = "페이지 크기를 입력해 주세요 (1 이상)")
    private Integer size;
}
