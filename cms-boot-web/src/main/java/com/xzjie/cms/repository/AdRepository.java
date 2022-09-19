package com.xzjie.cms.repository;

import com.xzjie.cms.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long>, JpaSpecificationExecutor<Ad> {

    List<Ad> findAdByPositionIdAndEnabled(Long positionId, boolean enabled);

    default List<Ad> findAdByPositionId(Long positionId) {
        return findAdByPositionIdAndEnabled(positionId, true);
    }
}
