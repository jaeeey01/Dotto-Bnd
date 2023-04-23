package com.dotto.app.faq.entity;

import com.dotto.app.common.entity.EntityDate;
import com.dotto.app.config.constants.FaqType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqNo;

    private String qContent;

    private String aContent;

    private String category;

    private char deletedYn;

    private String deletedAt;

    public Faq(String qContent, String aContent, String category){
        this.qContent = qContent;
        this.aContent = aContent;
        this.category = FaqType.valueOf(category).toString();
        this.deletedYn = 'N';
        this.deletedAt = null;
    }

    public void delete(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String deleteTime = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(ts);
        this.deletedYn = 'Y';
        this.deletedAt = deleteTime;
    }
}
