package com.dotto.app.policy.entity;

import com.dotto.app.common.entity.EntityDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Policy extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyNo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String dottoPolicyContent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String privatePolicyContent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String marketingPolicyContent;


    public Policy(String dottoPolicyContent, String privatePolicyContent, String marketingPolicyContent){
        this.dottoPolicyContent = dottoPolicyContent;
        this.privatePolicyContent = privatePolicyContent;
        this.marketingPolicyContent = marketingPolicyContent;
    }
}
