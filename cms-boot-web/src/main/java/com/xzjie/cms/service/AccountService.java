package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.Account;

import java.util.List;

public interface AccountService extends BaseService<Account, Long> {
    Account getAccount(Long userId);

    Account getAccountByName(String name);

    Account getAccountByMobile(String mobile);

    List<Account> getAccountList(Account account);

    boolean updateAvatar(String username, String avatar);

    boolean updatePassword(Long userId, String encode);

    boolean updateEmail(Long userId, String email);
}
