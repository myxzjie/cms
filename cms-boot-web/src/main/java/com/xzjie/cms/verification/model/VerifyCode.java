package com.xzjie.cms.verification.model;

import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.enums.VerifyCodeType;
import com.xzjie.cms.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Where(clause = "state = 1")
@Entity(name = "sys_verify_code")
@ApiModel("验证信息对象")
public class VerifyCode extends BaseEntity<VerifyCode> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty("ID")
//    private Long id;

    @Column(name = "scenes", columnDefinition = "varchar")
    @ApiModelProperty("业务类型")
    private VerifyCodeScenes scenes;
    @ApiModelProperty("验证值")
    private String value;
    @ApiModelProperty("目前对象信息")
    private String target;
    @ApiModelProperty("类型")
    @Column(name = "type", columnDefinition = "varchar")
    private VerifyCodeType type;
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;
    @ApiModelProperty("信息")
    private String message;
    @ApiModelProperty("状态")
    private Integer state;

    @Override
    public void copy(VerifyCode obj) {
        copyProperties(obj, this);
    }
}
