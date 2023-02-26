package com.dotto.app.member.repository;

import com.dotto.app.member.entity.Role;
import com.dotto.app.config.constants.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
