package com.xzjie.cms.vo;

import com.xzjie.cms.system.account.model.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Vito
 * @since 2022/3/7 12:26 上午
 */
@Data
public class AccountVo extends Account {
    @ApiModelProperty("角色ID")
    List<Long> roles;
}
