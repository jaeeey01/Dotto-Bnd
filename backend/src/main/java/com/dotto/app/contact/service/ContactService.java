package com.dotto.app.contact.service;

import com.dotto.app.contact.dto.ContactCreateRequest;
import com.dotto.app.contact.dto.ContactCreateResponse;
import com.dotto.app.contact.entity.Contact;
import com.dotto.app.member.entity.Member;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.contact.repository.ContactRepository;
import com.dotto.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ContactCreateResponse create(ContactCreateRequest req){
        Member member = memberRepository.findById(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        Contact contact = contactRepository.save(new Contact(member, req));

        return new ContactCreateResponse(member.getMemberNo());
    }



}
