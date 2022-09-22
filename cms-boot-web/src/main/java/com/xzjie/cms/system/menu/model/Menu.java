package com.xzjie.cms.system.menu.model;

import com.xzjie.cms.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@Table(name = "sys_menu")
@Where(clause = "state = 1")
@SQLDelete(sql = "update sys_menu set state = 0 where id = ?")
@ApiModel("菜单对象")
public class Menu extends BaseEntity<Menu> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty("ID")
//    private Long id;

    /**
     * 上级菜单ID
     */
    @Column(name = "pid", nullable = false)
    @ApiModelProperty("父级ID")
    private Long pid;
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("排序")
    @Column(unique = true)
    private Integer sort = 50;
    @ApiModelProperty("路径")
    @Column(name = "path")
    private String path;
    @ApiModelProperty("component")
    private String component;
    @ApiModelProperty("component 名称")
    @Column(unique = true, name = "component_name")
    private String componentName;
    /**
     * 类型，目录、菜单、按钮
     */
    @ApiModelProperty("菜单类型")
    @Column(name = "type")
    private Integer type;

    /**
     * 权限
     */
    @Column(name = "permission")
    @ApiModelProperty("权限")
    private String permission;
    @ApiModelProperty("是否 always")
    private Boolean alwaysShow;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("是否隐藏")
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean hidden;
    @ApiModelProperty("是否缓存")
    private Boolean cache;
    @ApiModelProperty("状态地址")
    private String redirect;

    //    @ManyToMany(mappedBy = "menus")
//    @JsonIgnore
//    private Set<Role> roles;
    @ApiModelProperty("创建时间")
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @ApiModelProperty("状态")
    private Integer state;

    @Override
    public void copy(Menu obj) {
        copyProperties(obj, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(this.getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
