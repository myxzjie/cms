package com.xzjie.cms.core.persistence;

import cn.hutool.core.lang.func.LambdaUtil;
import com.xzjie.cms.article.model.Article;
import com.xzjie.cms.core.ReflectionUtils;
import com.xzjie.cms.core.SFunction;
import com.xzjie.cms.core.persistence.enums.ConditionType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author vito
 * @date : 2022/9/23 8:06 PM
 */
public class PredicateWrapper {
    public static final String AND = "and";

    public static final String OR = "or";

    private String type = AND;
    private List<Criteria> list = new ArrayList<>();
//    List<PredicateWrapper<T>> wrappers = new ArrayList<>();

    public PredicateWrapper() {
        this(AND);
    }

    public PredicateWrapper(String type) {
        this.type = type;
    }

    public static PredicateWrapper and() {
        PredicateWrapper wrapper = new PredicateWrapper();
        return wrapper;
    }

    public static  PredicateWrapper or() {
        PredicateWrapper wrapper = new PredicateWrapper(OR);
        return wrapper;
    }


    public <T> Specification<T> build() {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>(list.size());
            for (Criteria criteria : list) {
                // 模糊查询,支持多字段
                String[] blurry = criteria.getBlurry();
                Object value = criteria.getValue();
                if (Objects.nonNull(blurry)) {
                    List<Predicate> blurPredicates = new ArrayList<>(blurry.length);
                    for (String blur : blurry) {
                        if (!StringUtils.hasText(blur)) {
                            continue;
                        }
                        blurPredicates.add(cb.like(root.get(blur), "%" + value + "%"));
                    }
                    predicates.add(cb.or(blurPredicates.toArray(new Predicate[blurPredicates.size()])));
                }
                // Join
                Join join = null;
                if (StringUtils.hasText(criteria.getTable())) {
                    join = root.join(criteria.getTable(), criteria.getJoinType());
                }

                String attribute = criteria.getAttribute();


                switch (criteria.getConnect()) {
                    case EQUAL:
                        predicates.add(cb.equal(this.getExpression(attribute, join, root), value));
                        break;
                    case GT:
                        predicates.add(cb.gt(this.getExpression(attribute, join, root), (Number) value));
                        break;
                    case GE:
                        predicates.add(cb.ge(this.getExpression(attribute, join, root), (Number) value));
                        break;
                    case LT:
                        predicates.add(cb.lt(this.getExpression(attribute, join, root), (Number) value));
                        break;
                    case LE:
                        predicates.add(cb.le(this.getExpression(attribute, join, root), (Number) value));
                        break;
                    case NOT_EQUAL:
                        predicates.add(cb.notEqual(this.getExpression(attribute, join, root), value));
                        break;
                    case LIKE:
                        predicates.add(cb.like(this.getExpression(attribute, join, root), "%" + value + "%"));
                        break;
                    case NOT_LIKE:
                        predicates.add(cb.notLike(this.getExpression(attribute, join, root), "%" + value + "%"));
                        break;
                    case GREATER_THAN:
                        predicates.add(cb.greaterThan(this.getExpression(attribute, join, root), (Comparable) value));
                        break;
                    case GREATER_THAN_OR_EQUAL_TO:
                        predicates.add(cb.greaterThanOrEqualTo(this.getExpression(attribute, join, root), (Comparable) value));
                        break;
                    case LESS_THAN:
                        predicates.add(cb.lessThan(this.getExpression(attribute, join, root), (Comparable) value));
                        break;
                    case LESS_THAN_OR_EQUAL_TO:
                        predicates.add(cb.lessThanOrEqualTo(this.getExpression(attribute, join, root), (Comparable) value));
                        break;
                    default:
                        break;
                }
            }

//            for (PredicateWrapper wrapper : wrappers) {
//                Predicate p = wrapper.build().toPredicate(root, query, cb);
//                predicates.add(p);
//            }
//
            if (Objects.equals(this.type, AND)) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            } else {
                return cb.or(predicates.toArray(new Predicate[predicates.size()]));
            }

        });

    }


    public <T, R> PredicateWrapper equal(SFunction<T, R> attribute, R value) {
        list.add(Criteria.builder()
                .connect(ConditionType.EQUAL)
                .attribute(ReflectionUtils.getFieldName(attribute))
                .value(value).build());
        return this;
    }

    public <R> PredicateWrapper equal(String attribute, R value) {
        list.add(Criteria.builder()
                .connect(ConditionType.EQUAL)
                .attribute(attribute)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper blurry(String value, SFunction<T, R>... attributes) {

        String[] blurry = new String[attributes.length];

        for (int i = 0; i < attributes.length; i++) {
            blurry[i] = ReflectionUtils.getFieldName(attributes[i]);
        }

        list.add(Criteria.builder()
                .blurry(blurry)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper gt(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.GT)
                .value(value).build());
        return this;
    }


    public <T, R> PredicateWrapper ge(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.GE)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper lt(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.LT)
                .value(value).build());
        return this;
    }


    public <T, R> PredicateWrapper le(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.LE)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper greaterThanOrEqualTo(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.GREATER_THAN_OR_EQUAL_TO)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper lessThanOrEqualTo(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.LESS_THAN_OR_EQUAL_TO)
                .value(value).build());
        return this;
    }


    public <T, R> PredicateWrapper like(SFunction<T, R> attribute, String value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
                .connect(ConditionType.LIKE)
                .value(value).build());
        return this;
    }

    // TODO
    public <T, R> PredicateWrapper leftLike(SFunction<T, R> attribute, String value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.LIKE)
                .value(value).build());
        return this;
    }


    public <T, R> PredicateWrapper rightLike(SFunction<T, R> attribute, String value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.LIKE)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper in(SFunction<T, R> attribute, List<R> values) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.IN)
                .value(values).build());
        return this;
    }

    public <T, R> PredicateWrapper notIn(SFunction<T, R> attribute, List<R> values) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.NOT_IN)
                .value(values).build());
        return this;
    }

    public <T, R> PredicateWrapper between(SFunction<T, R> attribute, Comparable value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.BETWEEN)
                .value(value).build());
        return this;
    }


    public <T, R> PredicateWrapper notNull(SFunction<T, R> attribute, R value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.NOT_NULL)
                .value(value).build());
        return this;
    }

    public <T, R> PredicateWrapper isNull(SFunction<T, R> attribute, R value) {
        list.add(Criteria.builder()
                .attribute(ReflectionUtils.getFieldName(attribute))
//                .connect(ConditionType.IS_NULL)
                .value(value).build());
        return this;
    }


    public <T, R> PredicateWrapper join(SFunction<T, R> table, JoinType joinType, ConditionType connect, SFunction<T, R> attribute, R value) {
        list.add(Criteria.builder()
                .table(ReflectionUtils.getFieldName(table))
                .joinType(joinType)
                .connect(connect)
                .attribute(ReflectionUtils.getFieldName(attribute))
                .value(value)
                .build());
        return this;
    }

    public <T, R> PredicateWrapper join(SFunction<T, R> table, JoinType joinType, ConditionType connect, String attribute, R value) {
        list.add(Criteria.builder()
                .table(ReflectionUtils.getFieldName(table))
                .joinType(joinType)
                .connect(connect)
                .attribute(attribute)
                .value(value)
                .build());
        return this;
    }

    public <T, R> PredicateWrapper join(String table, JoinType joinType, ConditionType connect, SFunction<T, R> attribute, R value) {
        list.add(Criteria.builder()
                .table(table)
                .joinType(joinType)
                .connect(connect)
                .attribute(ReflectionUtils.getFieldName(attribute))
                .value(value)
                .build());
        return this;
    }

    public <T, R> PredicateWrapper join(String table, JoinType joinType, ConditionType connect, String attribute, R value) {
        list.add(Criteria.builder()
                .table(table)
                .joinType(joinType)
                .connect(connect)
                .attribute(attribute)
                .value(value)
                .build());
        return this;
    }


    public <T, R> Expression<T> getExpression(String attribute, Join join, Root<R> root) {
        if (join != null) {
            return join.get(attribute);
        }
        return root.get(attribute);
    }

//    public PredicateWrapper nested(PredicateWrapper wrapper) {
//
//        return this;
//    }

    public static void main(String[] args) {
        PredicateWrapper
                .or()
                .equal(Article::getAuthor, "vito")
                .equal("kk", "ll")
//                .nested(PredicateWrapper.and().ge())
                .build();
    }
}
