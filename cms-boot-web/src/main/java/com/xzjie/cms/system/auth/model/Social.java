package com.xzjie.cms.system.auth.model;

import com.xzjie.cms.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Vito
 * @since 2022/6/13 1:01 上午
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_social")
public class Social extends BaseEntity<Social> {

//    @ApiModelProperty("ID")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("第三方唯一id")
    private String uuid;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("blog")
    private String blog;
    @ApiModelProperty("公司")
    private String company;
    @ApiModelProperty("位置地区")
    private String location;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("备注")
    private String remark;
    //    @Type(type = "char")
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("来源")
    private String source;


    @Override
    public void copy(Social obj) {
        this.copyProperties(obj, this);
    }
}
