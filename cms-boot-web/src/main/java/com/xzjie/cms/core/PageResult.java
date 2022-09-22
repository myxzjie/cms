package com.xzjie.cms.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vito
 * @date : 2022/9/22 11:23 AM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private List<T> content;
    private Long total;

    public static <T> PageResult<T> toPage(Page page) {
        return PageResult.builder()
                .content(page.getContent())
                .total(page.getTotalElements()).build();
    }
}
