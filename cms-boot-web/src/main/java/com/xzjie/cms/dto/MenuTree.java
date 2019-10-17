package com.xzjie.cms.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class MenuTree {

    private Long id;

    private String label;

    private List<MenuTree> children = new ArrayList<>();
}
