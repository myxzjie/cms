package com.xzjie.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeTree {

    private Long id;

    private String label;

    private List<NodeTree> children = new ArrayList<>();
}
