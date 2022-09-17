package com.xzjie.cms.dto;

import com.xzjie.cms.enums.Sorting;
import lombok.Data;

@Data
public class BasePageDto extends Dto {
    private Integer page = 0;
    private Integer size = 15;
    private Sorting sorting;

    public void setPage(Integer page) {
        if (page > 0) this.page = page - 1;
    }
}
