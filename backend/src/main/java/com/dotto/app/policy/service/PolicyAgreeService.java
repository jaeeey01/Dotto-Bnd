package com.dotto.app.policy.service;

import com.dotto.app.policy.dto.PolicyAgreeRequest;
import com.dotto.app.policy.dto.PolicyAgreeResponse;
import com.dotto.app.member.entity.Member;
import com.dotto.app.policy.entity.Policy;
import com.dotto.app.policy.entity.PolicyAgree;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.exception.PolicyNotFoundException;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.policy.repository.PolicyAgreeRepository;
import com.dotto.app.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PolicyAgreeService {

    private final PolicyAgreeRepository policyAgreeRepository;
    private final PolicyRepository policyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PolicyAgreeResponse create(PolicyAgreeRequest req){
        Member member = memberRepository.findById(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        Policy policy = policyRepository.findById(req.getPolicyNo()).orElseThrow(PolicyNotFoundException::new);
        policyAgreeRepository.save(new PolicyAgree(policy, member, req.getDottoAgreementYn(), req.getPrivateAgreementYn(), req.getMarketingAgreementYn()));
        return new PolicyAgreeResponse(member.getMemberNo());
    }

}
