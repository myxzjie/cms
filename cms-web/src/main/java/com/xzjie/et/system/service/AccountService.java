package com.xzjie.et.system.service;

import com.xzjie.mybatis.core.service.BaseService;
import com.xzjie.et.system.model.Account;

public interface AccountService extends BaseService<Account, Long> {

    boolean isExistName(String name, Long filterId);

    boolean isExistPhone(String phone, Long filterId);

    boolean isExistEmail(String eMail, Long filterId);

    Account getAccountLogin(String value);

    boolean saveAccount(Account model, Long roleId) throws Exception;

    String resetPassword(Long userId);

    boolean updatePassword(Long userId, String name, String password);

    boolean updateAccount(Account model, Long roleId) throws Exception;
}
