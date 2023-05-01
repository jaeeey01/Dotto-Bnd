package com.dotto.app.faq.dto;

import com.dotto.app.faq.entity.Faq;
import lombok.Data;

@Data
public class FaqSearchResponse {
    private Long faqNo;
    private String category;
    private String qContent;
    private String aContent;

    public FaqSearchResponse(Faq faq){
        this.faqNo = faq.getFaqNo();
        this.category = faq.getCategory();
        this.qContent = faq.getQContent();
        this.aContent = faq.getAContent();
    }
}
