package com.dotto.app.faq.service;

import com.dotto.app.exception.FaqNotFoundException;
import com.dotto.app.faq.dto.FaqCreateRequest;
import com.dotto.app.faq.dto.FaqUpdateRequest;
import com.dotto.app.faq.entity.Faq;
import com.dotto.app.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    //FAQ 작성 post
    @Transactional
    public Long create(FaqCreateRequest req){
        Faq faq = faqRepository.save(new Faq(req.getQContent(), req.getAContent(), req.getCategory()));
        return faq.getFaqNo();
    }
    //FAQ 수정 put
    @Transactional
    public Long update(Long faqNo, FaqUpdateRequest req){
        Faq findFaq = faqRepository.findById(faqNo).orElseThrow(FaqNotFoundException::new);
        faqRepository.updateFaq(findFaq.getFaqNo(), req.getCategory(), req.getQContent(), req.getAContent());
        return findFaq.getFaqNo();
    }
    //FAQ 삭제 delete


    //FAQ 목록조회 get

    //FAQ 검색 get


}
