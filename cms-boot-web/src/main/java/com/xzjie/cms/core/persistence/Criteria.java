package com.xzjie.cms.core.persistence;

import com.xzjie.cms.core.persistence.enums.ConditionType;
import com.xzjie.cms.core.persistence.enums.SpecType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.JoinType;

/**
 * @author vito
 * @date : 2022/9/23 9:22 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
    private String attribute;
    private Object value;

    private String[] blurry;

    private ConditionType connect = ConditionType.EQUAL;

   private SpecType specsType = SpecType.AND;

    /**
     * join查询
     *
     * @return
     */
    private JoinType joinType = JoinType.INNER;

    /**
     * join表名对象
     *
     * @return
     */
    private String table;
}
