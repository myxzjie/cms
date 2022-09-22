package com.xzjie.cms.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_permission")
public class Permission extends BaseEntity<Permission>{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private Long roleId;
    private Long menuId;
    @Override
    public void copy(Permission obj) {

    }
}
