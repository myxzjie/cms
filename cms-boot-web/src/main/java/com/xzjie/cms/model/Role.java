package com.xzjie.cms.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_role")
@Where(clause = "state =1")
@SQLDelete(sql = "update sys_role set state = 0 where id = ?")
@NoArgsConstructor
public class Role extends BaseEntity<Role> implements Serializable {

    public Role(Long id, String roleCode, String roleName, Integer roleLevel) {
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleLevel = roleLevel;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleCode;

    private String roleName;

    private Integer roleType;

    private String roleDesc;

    @Column( columnDefinition = "int default 1")
    private Integer state;

    @CreationTimestamp
    private Date createDate;

    private Long createUser;

    private Long orgId;

    private Integer roleLevel;

    @Transient
    private Long siteId;

    @Override
    public void copy(Role obj) {
        copyProperties(obj, this);
    }
}
