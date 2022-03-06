package com.xzjie.cms.vo;

import com.xzjie.cms.model.Account;
import lombok.Data;

import java.util.List;

/**
 * @author Vito
 * @since 2022/3/7 12:26 上午
 */
@Data
public class UserVo extends Account {
    List<Long> roles;
}
