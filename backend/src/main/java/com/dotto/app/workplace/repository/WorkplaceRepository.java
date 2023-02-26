package com.dotto.app.workplace.repository;

import com.dotto.app.workplace.entity.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkplaceRepository extends JpaRepository<WorkPlace, Long> {
}
