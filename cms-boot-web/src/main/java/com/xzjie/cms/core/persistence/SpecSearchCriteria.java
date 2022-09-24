package com.xzjie.cms.core.persistence;

import com.xzjie.cms.core.persistence.annotation.QueryCondition;
import com.xzjie.cms.core.persistence.enums.ConditionType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpecSearchCriteria {

    public static <T, Q> Specification<T> builder(Q query) {
        SpecSearchCriteria searchCriteria = new SpecSearchCriteria();
        return searchCriteria.toSpecification(query);
    }

    public <T, Q> Specification<T> toSpecification(Q query) {
        return ((root, criteriaQuery, cb) -> {
            List<Field> fields = getFields(query.getClass());
            List<Predicate> predicates = new ArrayList<>();
            for (Field single : fields) {
                QueryCondition condition = single.getAnnotation(QueryCondition.class);
                if (condition == null) {
                    continue;
                }
                single.setAccessible(true);
                try {
                    Object value = single.get(query);
                    if (value == null) {
                        continue;
                    }
                    String attribute = condition.value();
                    if (!StringUtils.hasText(attribute)) {
                        attribute = single.getName();
                    }


                    // 模糊查询,支持多字段
                    String[] blurry = condition.blurry();
                    ConditionType conditionType = condition.connect();

                    if (blurry.length != 0) {
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
                    String table = condition.table();
                    if (StringUtils.hasText(table)) {
                        join = root.join(table, condition.joinType());
                    }

                    if (StringUtils.hasText(attribute) && blurry.length == 0) {
                        switch (conditionType) {
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
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

    public <T, R> Expression<T> getExpression(String attribute, Join join, Root<R> root) {
        if (join != null) {
            return join.get(attribute);
        }
        return root.get(attribute);
    }

    private <T> List<Field> getFields(Class<T> clazz) {
        List<Field> fields = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields.length != 0) {
            fields.addAll(Arrays.asList(declaredFields));
        }

        Class<? super T> superclass = clazz.getSuperclass();
        // 如果父类是Object, 直接返回
        if (superclass == Object.class) {
            return fields;
        }
        // 递归获取所有的父级的Field
        List<Field> superclassFields = getFields(superclass);
        if (!superclassFields.isEmpty()) {
            superclassFields.stream()
                    // 去除重复字段
                    .filter(field -> !fields.contains(field))
                    .forEach(fields::add);

        }
        return fields;
    }
}
