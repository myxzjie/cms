package com.xzjie.cms.article.service.impl;

import com.xzjie.cms.article.model.Article;
import com.xzjie.cms.article.model.ArticleHot;
import com.xzjie.cms.article.model.ArticleRecommendStat;
import com.xzjie.cms.article.model.Category;
import com.xzjie.cms.article.dto.SearchDto;
import com.xzjie.cms.article.vo.CaseVo;
import com.xzjie.cms.article.vo.ArticleDetailVo;
import com.xzjie.cms.article.convert.CategoryVoConverter;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.article.dto.ArticleHotResult;
import com.xzjie.cms.article.dto.ArticleQueryDto;
import com.xzjie.cms.article.dto.ArticleRecommendStatResult;
import com.xzjie.cms.article.dto.CategoryTree;
import com.xzjie.cms.enums.Sorting;
import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import com.xzjie.cms.article.repository.ArticleHotRepository;
import com.xzjie.cms.article.repository.ArticleRecommendStatRepository;
import com.xzjie.cms.article.repository.ArticleRepository;
import com.xzjie.cms.article.repository.CategoryRepository;
import com.xzjie.cms.article.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ArticleServiceImpl extends AbstractService<Article, ArticleRepository> implements ArticleService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ArticleHotRepository articleHotRepository;
    @Autowired
    private ArticleRecommendStatRepository articleRecommendStatRepository;

    @Override
    public Article getArticle(Long id) {
        return baseRepository.getOne(id);
    }

    @Override
    public boolean updatePraise(Long id) {
        baseRepository.updatePraise(id);
        return true;
    }

    @Override
    public ArticleDetailVo getArticleDetail(Long id) {
        Article article = this.getArticle(id);
        Article prev = null;
        Article next = null;
        if (article.getCategoryId() != null) {
            prev = baseRepository.findByIdLessThanAndCategoryId(id, article.getCategoryId());
            next = baseRepository.findByIdGreaterThanAndCategoryId(id, article.getCategoryId());
        }
        return ArticleDetailVo.create(article, prev, next);
    }

    @Override
    public Page<Article> getArticle(SearchDto dto) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), sort);

        Specification<Article> specification = SpecSearchCriteria.builder(dto);
        return baseRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Article> getArticle(ArticleQueryDto dto) {
        Sort sort = Sort.by("id").descending();
        if (Sorting.asc.equals(dto.getSorting())) {
            sort = Sort.by("id").ascending();
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), sort);

        Specification<Article> specification = SpecSearchCriteria.builder(dto);
        Page<Article> articles = baseRepository.findAll(specification, pageable);

        articles.forEach(article -> {
            Category category = categoryRepository.getOne(article.getCategoryId());
            article.setCategoryName(category.getCategoryName());
        });
        return articles;
    }


    @Override
    public Page<Article> getArticleByLabels(ArticleQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        return baseRepository.findByLabels_idIn(query.getLabelIds(), pageable);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public List<CaseVo> getCaseData(Long categoryId, ArticleQueryDto query) {
        List<Category> categories = categoryRepository.findCategoriesByPidOrderBySort(categoryId);
        List<CaseVo> caseResponses = CategoryVoConverter.INSTANCE.source(categories);

        for (CaseVo caseResponse : caseResponses) {
            query.setCategoryId(caseResponse.getId());
            Page<Article> articlePage = this.getArticle(query);
            caseResponse.setArticles(articlePage.getContent());
        }
        return caseResponses;
    }

    @Override
    public List<Category> getCategory() {
        return categoryRepository.findCategoriesByPid(0L);
    }

    @Override
    public Page<Category> getCategory(Integer page, int size, Category query) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
//        return categoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (query == null) {
//                return null;
//            }
//            if (null != query.getPid()) {
//                predicates.add(criteriaBuilder.equal(root.get("pid").as(String.class), query.getPid()));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//        }, pageable);
        return categoryRepository.findCategoriesByPid(0L, pageable);
    }

    @Override
    public List<CategoryTree> getCategoryTree() {
        List<Category> categories = categoryRepository.findCategoriesByPid(0L);

        List<CategoryTree> categoryTrees = new ArrayList<>();
        BeanUtils.copyProperties(categories, categoryTrees);
        categoryTrees = getTree(categoryTrees);
        return categoryTrees;
    }

    @Override
    public boolean saveCategory(Category category) {
        category.setCreateDate(LocalDateTime.now());
        category.setState(1);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean updateCategory(Category category) {
        Category model = categoryRepository.findById(category.getId()).orElseGet(Category::new);
        model.copy(category);
        categoryRepository.save(model);
        return true;
    }

    @Override
    public boolean deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    private List<CategoryTree> getTree(List<CategoryTree> categoryTrees) {
        Map<Long, CategoryTree> dataMap = new HashMap<>();
        for (CategoryTree categoryTree : categoryTrees) {
            dataMap.put(categoryTree.getId(), categoryTree);
        }

        List<CategoryTree> trees = new ArrayList<>();
        for (Map.Entry<Long, CategoryTree> entry : dataMap.entrySet()) {
            CategoryTree categoryTree = entry.getValue();
            if (categoryTree.getParentId() == null) {
                trees.add(categoryTree);
            } else {
                if (dataMap.get(Long.valueOf(categoryTree.getParentId())) != null) {
                    dataMap.get(Long.valueOf(categoryTree.getParentId())).getChildren().add(categoryTree);
                }
            }
        }
        return trees;
    }


    @Override
    public Article update(Article article) {
//        Article model = baseRepository.findById(article.getId()).orElseGet(Article::new);
//        model.copy(article);
        article.setUpdateDate(LocalDateTime.now());
//        baseRepository.save(model);
        return super.update(article);
    }

    @Override
    public Page<ArticleHotResult> getArticleHot(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sort").descending());
        return articleHotRepository.findArticleHot(pageable);
    }

    @Override
    public boolean saveArticleHot(Set<Long> ids) {
        List<ArticleHot> list = new ArrayList<>();
        for (Long articleId : ids) {
            if (!articleHotRepository.existsByArticleId(articleId)) {
                ArticleHot model = new ArticleHot();
                model.setArticleId(articleId);
                model.setSort(50);
                list.add(model);
            }
        }
        articleHotRepository.saveAll(list);
        return true;
    }

    @Override
    public boolean updateArticleHot(ArticleHot articleHot) {
        ArticleHot model = articleHotRepository.findById(articleHot.getId()).orElseGet(ArticleHot::new);
        model.copy(articleHot);
        articleHotRepository.save(model);
        return true;
    }

    @Override
    public boolean deleteArticleHot(Set<Long> ids) {
        ids.forEach(id -> {
            articleHotRepository.deleteById(id);
        });
        return true;
    }

    @Override
    public Page<ArticleRecommendStatResult> getRecommendStat(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sort").descending());
        return articleRecommendStatRepository.findArticleRecommendStat(pageable);
    }


    @Override
    public boolean saveRecommendStat(Set<Long> ids) {
        List<ArticleRecommendStat> list = new ArrayList<>();
        for (Long articleId : ids) {
            if (!articleRecommendStatRepository.existsByArticleId(articleId)) {
                ArticleRecommendStat model = new ArticleRecommendStat();
                model.setArticleId(articleId);
                model.setSort(50);
                list.add(model);
            }
        }
        articleRecommendStatRepository.saveAll(list);
        return true;
    }

    @Override
    public boolean updateRecommendStat(ArticleRecommendStat recommendStat) {
        ArticleRecommendStat model = articleRecommendStatRepository.findById(recommendStat.getId()).orElseGet(ArticleRecommendStat::new);
        model.copy(recommendStat);
        articleRecommendStatRepository.save(model);
        return true;
    }

    @Override
    public boolean deleteRecommendStat(Set<Long> ids) {
        ids.forEach(id -> {
            articleRecommendStatRepository.deleteById(id);
        });
        return true;
    }
}
