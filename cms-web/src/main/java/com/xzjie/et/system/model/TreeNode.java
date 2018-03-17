package com.xzjie.et.system.model;

/**
 * Created by xzjie on 2017/9/10.
 */
public class TreeNode {
    private long id;         //节点的标识属性，对应的是启用简单数据格式时idKey对应的属性名，并不一定是id,如果setting中定义的idKey:"zId",那么此处就是zId
    private long pId;        //节点parentId属性，命名规则同id
    private String name;      //节点显示的文本
    private boolean checked;   //节点是否勾选，ztree配置启用复选框时有效

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
//    open,       //节点是否展开
//    icon,       //节点的图标
//    iconOpen,   //节点展开式的图标
//    iconClose,  //节点折叠时的图标

//
//    children,   //得到该节点所有孩子节点，直接下级，若要得到所有下属层级节点，需要自己写递归得到
//    isParent,   //判断该节点是否是父节点，一般应用中通常需要判断只有叶子节点才能进行相关操作，或者删除时判断下面是有子节点时经常用到。
//    getPath()   //得到该节点的路径，即所有父节点，包括自己，此方法返回的是一个数组，通常用于创建类似面包屑导航的东西A-->B-->C
}
