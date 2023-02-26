package com.dotto.app.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DottoPostList {

    Long postNo;
    String title;
    String nickname;
    String price;
    String salesPrice;
    String salesYn;
    String salesPct;
    String tags;
    String originName;
}
