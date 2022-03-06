package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dto.UserDto;
import com.xzjie.cms.dto.UserQueryDto;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.vo.UserVo;
import org.springframework.data.domain.Page;

public interface AccountService extends BaseService<Account> {
    void save(UserDto dto);

    void update(Long userId,UserDto dto);

    Account getAccount(Long userId);

    Account getAccountByName(String name);

    Account getAccountByMobile(String mobile);

    Page<UserVo> getAccountList(UserQueryDto request);

    boolean updateAvatar(String username, String avatar);

    boolean updatePassword(Long userId, String encode);

    boolean updateEmail(Long userId, String email);
}
