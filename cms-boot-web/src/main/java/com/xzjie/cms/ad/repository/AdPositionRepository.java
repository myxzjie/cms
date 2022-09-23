package com.xzjie.cms.ad.repository;

import com.xzjie.cms.ad.model.AdPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdPositionRepository extends JpaRepository<AdPosition, Long>, JpaSpecificationExecutor<AdPosition> {
    AdPosition findAdPositionByPositionCode(String positionCode);
}
