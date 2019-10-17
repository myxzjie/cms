package com.xzjie.cms.repository;

import com.xzjie.cms.model.PicturesGroup;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PicturesGroupRepository extends JpaRepository<PicturesGroup, Long>, JpaSpecificationExecutor {

    List<PicturesGroup> findPicturesGroupByState(Integer state);

    default List<PicturesGroup> findPicturesGroup() {
        return findPicturesGroupByState(1);
    }
}
