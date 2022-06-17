package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dto.UserDto;
import com.xzjie.cms.dto.UserQueryDto;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Social;
import com.xzjie.cms.vo.UserVo;
import org.springframework.data.domain.Page;

public interface AccountService extends BaseService<Account> {
    void save(UserDto dto, Social social, String code);

    void update(Long userId, UserDto dto);

    Account getAccount(Long userId);

    Account getAccountByName(String name);

    Account getAccountByMobile(String mobile);

    Page<UserVo> getAccountList(UserQueryDto request);

    boolean existsByName(String name);

    boolean updateAvatar(String username, String avatar);

    boolean updatePassword(Long userId, String encode);

    boolean updateEmail(Long userId, String email);

    Social getSocialByUuid(String uuid);

    Social saveSocial(Social social);
}
