package com.xzjie.cms.system.account.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_account_role")
public class AccountRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long roleId;

    private Long userId;


}
