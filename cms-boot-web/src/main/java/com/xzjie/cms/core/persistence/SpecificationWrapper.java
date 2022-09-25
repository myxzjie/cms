package com.xzjie.cms.core.persistence;

import com.xzjie.cms.core.ReflectionUtils;
import com.xzjie.cms.core.SFunction;
import com.xzjie.cms.core.persistence.enums.ConditionType;
import com.xzjie.cms.core.persistence.enums.SpecType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author vito
 * @date : 2022/9/24 2:38 PM
 */
public class SpecificationWrapper {
    private Specification spec;
    private Method method;

    public SpecificationWrapper(SpecType specType) {
        try {
            this.spec = Specification.where(null);
            this.method = this.spec.getClass().getMethod(specType.name().toLowerCase(), Specification.class);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    private <T> void invoke(Specification specification) {
        try {
            this.spec = (Specification<T>) this.method.invoke(this.spec, specification);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> SpecificationWrapper distinct() {
        Specification<T> specification = (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return null;
        };
        this.invoke(specification);
        return this;
    }

    /**
     * equal
     *
     * @param attribute 字段名
     * @param value     字段值
     * @return SpecificationUtils
     */
    public <T> SpecificationWrapper equal(String attribute, Object value) {
//        if (this.check(MatchType.EQUAL, new Object[]{value})) {
        Specification<T> specification = (root, query, builder) -> builder.equal(root.get(attribute), value);
        this.invoke(specification);
//        }
        return this;
    }

    public <T> SpecificationWrapper like(String attribute, String value) {
        Specification<T> specification = (root, query, builder) -> builder.like(root.get(attribute), "%" + value + "%");
        this.invoke(specification);
        return this;
    }

    public <T> SpecificationWrapper gt(String attribute, Number value) {
//        if (this.check(MatchType.GT, new Object[]{value})) {
        Specification<T> specification = (root, query, builder) -> builder.gt((root.get(attribute)), value);
        this.invoke(specification);
//        }
        return this;
    }

    public <T, R> SpecificationWrapper greaterThanOrEqualTo(SFunction<T, R> attribute, Comparable value) {
        return this.greaterThanOrEqualTo(ReflectionUtils.getFieldName(attribute), value);
    }

    public <T> SpecificationWrapper greaterThanOrEqualTo(String attribute, Comparable value) {
        Specification<T> specification = (root, query, builder) -> builder.greaterThanOrEqualTo((root.get(attribute)), value);
        this.invoke(specification);
        return this;
    }

    public <T, R> SpecificationWrapper lessThanOrEqualTo(SFunction<T, R> attribute, Comparable value) {
        return this.lessThanOrEqualTo(ReflectionUtils.getFieldName(attribute), value);
    }

    public <T> SpecificationWrapper lessThanOrEqualTo(String attribute, Comparable value) {
        Specification<T> specification = (root, query, builder) -> builder.lessThanOrEqualTo((root.get(attribute)), value);
        this.invoke(specification);
        return this;
    }

    public <T, R> SpecificationWrapper join(String table, JoinType joinType, String attribute, R value) {

        Specification<T> specification = ((root, query, builder) -> {
            Join join = root.join(table, joinType);
            return builder.equal(join.get(attribute), value);
        });
        this.invoke(specification);
        return this;
    }

//    private <T> Specification<T> to(ConditionType connect) {//, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        return ((root, query, cb) -> {
//            switch (connect){
//                case EQUAL:
//                    return builder.equal(join.get(attribute), value);
//            }
//        });
//    }
//   ,

//    private <T, R> Expression<T> getExpression(String attribute, Join join, Root<R> root) {
//        if (join != null) {
//            return join.get(attribute);
//        }
//        return root.get(attribute);
//    }


    /**
     * 切换后面的内容为and
     *
     * @return SpecificationUtils
     */
    public <T> SpecificationWrapper and() {
        try {
            this.method = this.spec.getClass().getMethod(SpecType.AND.name().toLowerCase(), Specification.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;
    }

    public <T> SpecificationWrapper and(Specification<T> specification) {
        return and(true, specification);
    }

    public <T> SpecificationWrapper and(Boolean condition, Specification<T> specification) {
        if (condition) {
            this.spec = this.spec.and(specification);
        }
        return this;
    }

    public <T> SpecificationWrapper or() {
        try {
            this.method = this.spec.getClass().getMethod(SpecType.OR.name().toLowerCase(), Specification.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public <T> SpecificationWrapper or(Specification<T> specification) {
        return or(true, specification);
    }

    public <T> SpecificationWrapper or(Boolean condition, Specification<T> specification) {
        if (condition) {
            this.spec = this.spec.or(specification);
        }
        return this;
    }

//    public static Builder builder() {
//        return new Builder();
//    }


//    public static class Builder {
//        SpecificationWrapper wrapper = new SpecificationWrapper(SpecType.AND);
//
//        public Builder and() {
//            wrapper = new SpecificationWrapper(SpecType.AND);
//            return this;
//        }
//
//        public <T> SpecificationWrapper and(Specification<T> specification) {
//           wrapper.
//        }
//
//        public Builder or() {
//            wrapper = new SpecificationWrapper(SpecType.OR);
//            return this;
//        }
//
//        public SpecificationWrapper build() {
//            return wrapper;
//        }
//    }

//    public static <T> SpecificationWrapper builder() {
//        return builder(SpecType.AND);
//    }

    public static <T> SpecificationWrapper builder(SpecType specType) {
        return new SpecificationWrapper(specType);
    }

    public static <T> SpecificationWrapper toSpecAnd() {
        return builder(SpecType.AND);
    }

    public static <T> SpecificationWrapper toSpecOr() {
        return builder(SpecType.OR);
    }

    public <T> Specification<T> build() {
        Specification<T> specification = this.spec;
        this.spec = Specification.where(null);
        return specification;
    }

    public static void main(String[] args) {
        SpecificationWrapper.toSpecAnd()
                // 直接使用的方式会判断后面的参数是否为空，如果为空就不会起作用
                // 如果第二个参数为空，则不会生效
                .equal("userName", "xxx")
//                .like("name", userVO.getName())
//                // 实体类中的类型为Instant 就使用betweenInstant，为Date就使用betweenDate 参数为List<String>  日期格式为yyyy-MM-dd
//                .betweenInstant("createdDate", userVO.getCreatedDate())
//                // 相当于or里面的内容加了个括号
                .and(SpecificationWrapper.builder(SpecType.OR)
                        .equal("phone", "pp")
//                        .equal("email", "email不相等")
                        .build())
//                // 如果没有找到与你期望的预设方法，可以使用下面的方法自定义条件
                .and((root, query, builder) -> builder.greaterThan(root.get("version"), 0))
                // 直接修改后面的方法为or 不太实用，最好还是使用or(Specification spec)
                .or()
                .like("avatar", "这里是肯定不等于值的，只是做个测试")
                .build();
    }
}
