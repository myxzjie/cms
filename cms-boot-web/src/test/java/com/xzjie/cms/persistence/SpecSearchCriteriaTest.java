package com.xzjie.cms.persistence;

import com.xzjie.cms.WebApplicationTests;
import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import com.xzjie.cms.core.persistence.annotation.QueryCondition;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class SpecSearchCriteriaTest extends WebApplicationTests {

    @Test
    public void test() {
        SpecSearchCriteria searchCriteria = new SpecSearchCriteria();
        TestModel model = new TestModel();
        model.setId(1000L);
        model.setName("test");
        searchCriteria.toSpecification(model);
    }

    @Data
    public class TestModel {
        @QueryCondition(value = "user_id")
        private Long id;
        @QueryCondition
        private String name;
    }
}