package com.xzjie.cms.model;

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
public class Menu extends BaseEntity<Menu> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 上级菜单ID
     */
    @Column(name = "pid", nullable = false)
    private Long pid;

    private String name;

    @Column(unique = true)
    private Integer sort = 50;

    @Column(name = "path")
    private String path;

    private String component;

    @Column(unique = true, name = "component_name")
    private String componentName;
    /**
     * 类型，目录、菜单、按钮
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 权限
     */
    @Column(name = "permission")
    private String permission;

    private Boolean alwaysShow;

    private String icon;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean hidden;

    private Boolean cache;

    private String redirect;

//    @ManyToMany(mappedBy = "menus")
//    @JsonIgnore
//    private Set<Role> roles;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

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
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
