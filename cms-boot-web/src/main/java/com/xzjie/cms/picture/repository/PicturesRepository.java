package com.xzjie.cms.picture.repository;

import com.xzjie.cms.picture.model.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PicturesRepository extends JpaRepository<Pictures, Long>, JpaSpecificationExecutor {
}
