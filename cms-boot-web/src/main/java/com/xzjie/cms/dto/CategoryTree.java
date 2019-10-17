package com.xzjie.cms.dto;

import lombok.Data;

@Data
public class CategoryTree extends TreeNode<CategoryTree> {

    private String categoryName;
}
