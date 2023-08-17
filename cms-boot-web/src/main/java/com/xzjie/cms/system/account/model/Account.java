package com.xzjie.cms.system.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xzjie.cms.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@Table(name = "sys_account")
@SQLDelete(sql = "update sys_account set state = 0 where id = ?")
@Where(clause = "state = 1")
@ApiModel("管理用户对象")
public class Account extends BaseEntity<Account> {

//    @Id
//    @Column(name = "id")
//    @ApiModelProperty("ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    @Column(name = "e_mail")
    private String email;

//    @JsonIgnore
    @ApiModelProperty("密码")
    private String password;

//    @JsonIgnore
    private String salt;
    @ApiModelProperty("状态")
    private Integer state;

    @CreationTimestamp
//    @UpdateTimestamp
    @ApiModelProperty("创建时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @ApiModelProperty("备注")
    private String remarks;

    @Column(name = "stype", columnDefinition = "char")
    private String stype;
    @ApiModelProperty("性别")
    @Column(name = "sex", columnDefinition = "char")
    private String sex;

    private String card;
    @ApiModelProperty("生日")
    @Column(columnDefinition = "datetime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate birtn;
    @ApiModelProperty("创建者")
    private Long createUser;
    @ApiModelProperty("是否锁")
    private Integer locked;

    private Long orgId;
    @ApiModelProperty("头像")
    private String avatar;

    private Long rankId;
//    @ApiModelProperty("积分")
    private Integer points;

    @Transient
//    @ApiModelProperty("角色名称")
    private String roleName;

    public String credentialsSalt() {
        return name + salt;
    }

    /**
     * 添加中间表关系
     */
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "AccountRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
//    @Transient
//    private Set<Role> roles;



    @Override
    public void copy(Account obj) {
        BeanUtils.copyProperties(obj, this, getIgnoreProperty(obj));
    }
}
