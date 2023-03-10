package com.dotto.app.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DottoPostDto {

    private Long postNo;
    private String title;
    private String id;
    private String price;
    private String salesPrice;
    private String salesYn;
    private String salesPct;
    private String tags;

    private String originName;

    @QueryProjection
    public DottoPostDto(Long postNo, String title, String id, String price, String salesPrice, String salesYn, String salesPct, String tags, String originName){
        this.postNo = postNo;
        this.title = title;
        this.id = id;
        this.price = price;
        this.salesPrice = salesPrice;
        this.salesYn = salesYn;
        this.salesPct = salesPct;
        this.tags = tags;
        this.originName = originName;
    }

}
