package com.xzjie.cms.dto;

import lombok.Data;

@Data
public class BasePageRequest {
    private Integer page = 0;
    private Integer size = 15;

    public void setPage(Integer page) {
        if (page > 0) this.page = page - 1;
    }
}
