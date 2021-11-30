package com.xzjie.cms.persistence;

import com.xzjie.cms.persistence.annotation.QueryCondition;
import com.xzjie.cms.persistence.enums.ConditionType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecSearchCriteria {

    public static <T,Q> Specification<T> builder(Q query) {
        SpecSearchCriteria searchCriteria = new SpecSearchCriteria();
        return searchCriteria.toSpecification(query);
    }

    public <T,Q> Specification<T> toSpecification(Q query) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
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
                    String column = condition.value();
                    if (!StringUtils.hasText(column)) {
                        column = single.getName();
                    }
                    String[] blurry = condition.blurry();
                    ConditionType conditionType = condition.connect();

                    if (blurry.length != 0) {
                        List<Predicate> blurPredicates = new ArrayList<>(blurry.length);
                        for (String blur : blurry) {
                            if (!StringUtils.hasText(blur)) {
                                continue;
                            }
                            blurPredicates.add(criteriaBuilder.like(root.get(blur), "%" + value + "%"));
                        }
                        predicates.add(criteriaBuilder.or(blurPredicates.toArray(new Predicate[blurPredicates.size()])));
                    }

                    if (StringUtils.hasText(column)) {
                        switch (conditionType) {
                            case EQUAL:
                                predicates.add(criteriaBuilder.equal(root.get(column), value));
                                break;
                            case GT:
                                predicates.add(criteriaBuilder.gt(root.get(column), (Number) value));
                                break;
                            case GE:
                                predicates.add(criteriaBuilder.ge(root.get(column), (Number) value));
                                break;
                            case LT:
                                predicates.add(criteriaBuilder.lt(root.get(column), (Number) value));
                                break;
                            case LE:
                                predicates.add(criteriaBuilder.le(root.get(column), (Number) value));
                                break;
                            case NOT_EQUAL:
                                predicates.add(criteriaBuilder.notEqual(root.get(column), value));
                                break;
                            case LIKE:
                                predicates.add(criteriaBuilder.like(root.get(column), "%" + value + "%"));
                                break;
                            case NOT_LIKE:
                                predicates.add(criteriaBuilder.notLike(root.get(column), "%" + value + "%"));
                                break;
                            case GREATER_THAN:
                                predicates.add(criteriaBuilder.greaterThan(root.get(column), (Comparable) value));
                                break;
                            case GREATER_THAN_OR_EQUAL_TO:
                                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(column), (Comparable) value));
                                break;
                            case LESS_THAN:
                                predicates.add(criteriaBuilder.lessThan(root.get(column), (Comparable) value));
                                break;
                            case LESS_THAN_OR_EQUAL_TO:
                                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(column), (Comparable) value));
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
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
