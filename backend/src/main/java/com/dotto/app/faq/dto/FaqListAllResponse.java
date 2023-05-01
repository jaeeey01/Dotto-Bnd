package com.dotto.app.faq.dto;

import com.dotto.app.faq.entity.Faq;
import lombok.Data;

@Data
public class FaqListAllResponse {

    private Long faqNo;

    private String category;

    private String qContent;

    private String aContent;

    public FaqListAllResponse(Faq faq){
        this.faqNo = faq.getFaqNo();
        this.category = faq.getCategory();
        this.qContent = faq.getQContent();
        this.aContent = faq.getAContent();
    }

    public static FaqListAllResponse toDto(Faq faq){
        return new FaqListAllResponse(faq);
    }

}
