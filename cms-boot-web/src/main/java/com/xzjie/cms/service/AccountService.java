package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dto.UserRequest;
import com.xzjie.cms.model.Account;
import org.springframework.data.domain.Page;

public interface AccountService extends BaseService<Account> {
    Account getAccount(Long userId);

    Account getAccountByName(String name);

    Account getAccountByMobile(String mobile);

    Page<Account> getAccountList(UserRequest request);

    boolean updateAvatar(String username, String avatar);

    boolean updatePassword(Long userId, String encode);

    boolean updateEmail(Long userId, String email);
}
