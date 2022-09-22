package com.xzjie.cms.system.role.model;

import com.xzjie.cms.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_role")
@Where(clause = "state =1")
@SQLDelete(sql = "update sys_role set state = 0 where id = ?")
@NoArgsConstructor
@ApiModel("角色对象")
public class Role extends BaseEntity<Role> {

    public Role(Long id, String roleCode, String roleName, Integer roleLevel) {
        this.setId(id);
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleLevel = roleLevel;
    }

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty("ID")
//    private Long id;
    @ApiModelProperty("角色code")
    private String roleCode;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色类型")
    private Integer roleType;
    @ApiModelProperty("角色说明")
    private String roleDesc;
    @ApiModelProperty("状态")
    @Column( columnDefinition = "int default 1")
    private Integer state;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Date createDate;
    @ApiModelProperty("创建者")
    private Long createUser;

    private Long orgId;
    @ApiModelProperty("角色等级")
    private Integer roleLevel;

    @Transient
    private Long siteId;

    @Override
    public void copy(Role obj) {
        copyProperties(obj, this);
    }
}
