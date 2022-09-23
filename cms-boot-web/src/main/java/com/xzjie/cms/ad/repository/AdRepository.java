package com.xzjie.cms.ad.repository;

import com.xzjie.cms.ad.model.Ad;
import com.xzjie.cms.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdRepository extends BaseRepository<Ad, Long> {

    List<Ad> findAdByPositionIdAndEnabled(Long positionId, boolean enabled);

    default List<Ad> findAdByPositionId(Long positionId) {
        return findAdByPositionIdAndEnabled(positionId, true);
    }
}
