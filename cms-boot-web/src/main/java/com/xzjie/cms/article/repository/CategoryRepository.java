package com.xzjie.cms.article.repository;

import com.xzjie.cms.article.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    List<Category> findCategoriesByPid(Long pid);

    List<Category> findCategoriesByPidOrderBySort(Long pid);

    Page<Category> findCategoriesByPid(Long pid, Pageable pageable);
}
