package com.xzjie.cms.repository;

import com.xzjie.cms.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor {

    List<Category> findCategoriesByPid(Long pid);

    Page<Category> findCategoriesByPid(Long pid, Pageable pageable);
}
