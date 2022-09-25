//package com.xzjie.cms.core.persistence;
//
//import com.xzjie.cms.core.persistence.enums.SpecType;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author vito
// * @date : 2022/9/24 10:57 AM
// */
//public class SimpleSpecification<T> implements Specification<T> {
//
//    private SpecType specsType = SpecType.AND;
//    private List<SimpleSpecification> specs = new ArrayList<>();
//
//    public SimpleSpecification<T> equal(String key, Object value) {
//        Specification<T> specification = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(key), value));
//        this.and(specification);
//        return this;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicates = new ArrayList<>();
//        for (SimpleSpecification spec : specs) {
//            Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
//            predicates.add(predicate);
//        }
//        if (specsType == SpecType.AND) {
//            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
//        } else {
//            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
//        }
//    }
//
//    @Override
//    public Specification<T> or(Specification<T> other) {
//        return Specification.super.or(other);
//    }
//
//    @Override
//    public Specification<T> and(Specification<T> other) {
//        return Specification.super.and(other);
//    }
//}
