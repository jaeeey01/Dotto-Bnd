package com.dotto.app.policy.repository;

import com.dotto.app.policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    @Query("select p from Policy p order by p.modifiedAt desc")
    Optional<Policy> findByPolicyNoOrderByModifiedAtDescLimit1();

}
