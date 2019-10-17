package com.xzjie.cms.repository;

import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.AdPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdPositionRepository extends JpaRepository<AdPosition, Long>, JpaSpecificationExecutor<AdPosition> {
    AdPosition findAdPositionByPositionCode(String positionCode);
}
