package com.dotto.app.policy.dto;

import com.dotto.app.policy.entity.Policy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyDto {

    private String dottoPolicyContent;
    private String privatePolicyContent;
    private String marketingPolicyContent;


    public static PolicyDto toDto(Policy policy){
        return new PolicyDto(policy.getDottoPolicyContent(),
                policy.getPrivatePolicyContent(),
                policy.getMarketingPolicyContent());
    }
}
