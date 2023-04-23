package com.dotto.app.faq.repository;

import com.dotto.app.config.constants.FaqType;
import com.dotto.app.faq.dto.FaqUpdateRequest;
import com.dotto.app.faq.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    @Modifying
    @Query("update Faq f set f.category = :category, f.qContent = :qContent, f.aContent = :aContent where f.faqNo = :faqNo ")
    void updateFaq(Long faqNo, String category, String qContent, String aContent);
}
