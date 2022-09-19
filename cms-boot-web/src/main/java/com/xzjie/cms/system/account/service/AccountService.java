package com.xzjie.cms.system.account.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.system.account.dto.AccountDto;
import com.xzjie.cms.system.account.dto.AccountQueryDto;
import com.xzjie.cms.system.account.model.Account;
import com.xzjie.cms.model.Social;
import com.xzjie.cms.vo.AccountVo;
import org.springframework.data.domain.Page;

public interface AccountService extends BaseService<Account> {
    void save(AccountDto dto, Social social, String code);

    void update(Long userId, AccountDto dto);

    Account getAccount(Long userId);

    Account getAccountByName(String name);

    Account getAccountByMobile(String mobile);

    Page<AccountVo> getAccountList(AccountQueryDto request);

    boolean existsByName(String name);

    boolean updateAvatar(String username, String avatar);

    boolean updatePassword(Long userId, String encode);

    boolean updateEmail(Long userId, String email);

    Social getSocialByUuid(String uuid);

    Social saveSocial(Social social);

    void resetPassword(Long userId);
}
