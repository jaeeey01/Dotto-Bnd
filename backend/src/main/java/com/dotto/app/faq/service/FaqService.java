package com.dotto.app.faq.service;

import com.dotto.app.exception.FaqNotFoundException;
import com.dotto.app.faq.dto.*;
import com.dotto.app.faq.entity.Faq;
import com.dotto.app.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Transactional
    public boolean delete(Long faqNo){
        Faq findFaq = faqRepository.findById(faqNo).orElseThrow(FaqNotFoundException::new);
        findFaq.delete();
        return true;
    }
    //FAQ 전체 목록조회 get
    public List<FaqListAllResponse> readAll(){
        return faqRepository.findAllByDeletedN().stream().map(FaqListAllResponse::new).collect(Collectors.toList());

    }

    //FAQ 카테고리별 목록조회 get
    public List<FaqListCategoryResponse> readAllCategory(String category){
        return faqRepository.findCategoryByDeletedN(category).stream().map(FaqListCategoryResponse::new).collect(Collectors.toList());

    }

    //FAQ 검색 get
    public List<FaqSearchResponse> search(String search){
        return faqRepository.findSearch(search).stream().map(FaqSearchResponse::new).collect(Collectors.toList());
    }
}
