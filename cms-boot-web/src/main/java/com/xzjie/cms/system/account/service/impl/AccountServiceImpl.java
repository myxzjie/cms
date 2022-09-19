package com.xzjie.cms.system.account.service.impl;

import com.xzjie.cms.system.account.convert.AccountConverter;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.service.VerifyCodeService;
import com.xzjie.cms.system.account.dto.AccountDto;
import com.xzjie.cms.system.account.dto.AccountQueryDto;
import com.xzjie.cms.enums.StateType;
import com.xzjie.cms.system.account.model.Account;
import com.xzjie.cms.system.account.model.AccountRole;
import com.xzjie.cms.model.Social;
import com.xzjie.cms.persistence.SpecSearchCriteria;
import com.xzjie.cms.system.account.repository.AccountRepository;
import com.xzjie.cms.system.account.repository.SocialRepository;
import com.xzjie.cms.system.account.service.AccountService;
import com.xzjie.cms.system.role.serivce.RoleService;
import com.xzjie.cms.vo.AccountVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends AbstractService<Account, AccountRepository> implements AccountService {

    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SocialRepository socialRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(AccountDto dto, Social social, String code) {
        Account account = AccountConverter.INSTANCE.target(dto);
        account.setState(StateType.NORMAL.getCode());
        baseRepository.save(account);
        List<AccountRole> accountRoles = new ArrayList<>();
        if (dto.getRoles().size() > 0) {
            dto.getRoles().stream().forEach(roleId -> {
                AccountRole accountRole = new AccountRole();
                accountRole.setUserId(account.getId());
                accountRole.setRoleId(roleId);
                accountRoles.add(accountRole);
            });
        }
        roleService.saveAccount(accountRoles);
        if (social != null) {
            social.setUserId(account.getId());
            this.saveSocial(social);
            redisTemplate.opsForValue()
                    .set("social:" + code, social, 10 * 60, TimeUnit.SECONDS);
        }
    }

    @Override
    @Transactional
    public void update(Long userId, AccountDto dto) {
        Account account = AccountConverter.INSTANCE.target(dto);
        List<AccountRole> accountRoles = new ArrayList<>();
        if (dto.getRoles().size() > 0) {
            dto.getRoles().stream().forEach(roleId -> {
                AccountRole accountRole = new AccountRole();
                accountRole.setUserId(userId);
                accountRole.setRoleId(roleId);
                accountRoles.add(accountRole);
            });
        }
        Account model = baseRepository.findById(userId).orElseGet(Account::new);
        model.copy(account);
        baseRepository.save(model);
        roleService.deleteAccountRole(userId);
        roleService.saveAccount(accountRoles);
    }

    @Override
    public Account getAccount(Long userId) {
        return baseRepository.findById(userId).orElseGet(Account::new);
    }


    /**
     * @describe 根据账号查询用户信息
     * @date 2018/11/17
     * @author Wang Chen Chen
     */
//    @Override
//    public UserInfoVo findUserInfo(String account) {
//        User user = findByAccount(account);
//        UserInfoVo result = new UserInfoVo(user.getUid(), user.getAvatar(), user.getNickname(), user.getAccount(), user.getMail());
//        Set<Permission> permissions = permissionService.findAllByUserId(user.getUid());
//        Set<ButtonVo> buttonVos = new HashSet<>();
//        Set<MenuVo> menuVos = new HashSet<>();
//        if (permissions != null && permissions.size() > 1) {
//            permissions.forEach(permission -> {
//                if (permission.getType().toLowerCase().equals(PermissionType.BUTTON)) {
//                    /*
//                     * 如果权限是按钮，就添加到按钮里面
//                     * */
//                    buttonVos.add(new ButtonVo(permission.getPid(), permission.getResources(), permission.getTitle()));
//                }
//                if (permission.getType().toLowerCase().equals(PermissionType.MENU)) {
//                    /*
//                     * 如果权限是菜单，就添加到菜单里面
//                     * */
//                    menuVos.add(new MenuVo(permission.getPid(), permission.getParentId(), permission.getIcon(), permission.getResources(), permission.getTitle()));
//                }
//            });
//        }
//        result.setButtons(buttonVos);
//        result.setMenus(findRoots(menuVos));
//        return result;
//    }
//
    @Override
    public Account getAccountByName(String name) {
        Account account = baseRepository.findAccountByName(name);
//        account.setRoles(roleService.getRoles(account.getUserId()));
        return account;
    }

    @Override
    public Account getAccountByMobile(String mobile) {
        return baseRepository.findAccountByPhone(mobile);
    }

    @Override
    public Page<AccountVo> getAccountList(AccountQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("id").descending());
        Specification<Account> specification = SpecSearchCriteria.builder(query);
        Page<Account> page = baseRepository.findAll(specification, pageable);
        Page<AccountVo> voPage = page.map(AccountConverter.INSTANCE::convert);
        voPage.forEach(userVo -> {
            List<Long> roles = roleService.getAccountRoleByUserId(userVo.getId());
            userVo.setRoles(roles);
        });
        return voPage;
    }

    @Override
    public boolean existsByName(String name) {
        return baseRepository.existsByName(name);
    }

    @Override
    public boolean updateAvatar(String username, String avatar) {
        return baseRepository.updateAvatar(username, avatar) > 0;
    }

    @Override
    public boolean updatePassword(Long userId, String encode) {
        return baseRepository.updatePassword(userId, encode) > 0;
    }

    @Override
    public boolean updateEmail(Long userId, String email) {
        return baseRepository.updateEmail(userId, email) > 0;
    }

    @Override
    @Transactional
    public Account save(Account obj) {
        obj.setState(1);
        obj.setCreateDate(LocalDateTime.now());
        return super.save(obj);
    }

    @Override
    public boolean update(Account obj) {
        Account model = baseRepository.findById(obj.getId()).orElseGet(Account::new);
        model.copy(obj);
        baseRepository.save(model);
        return true;
    }

    @Override
    public Social getSocialByUuid(String uuid) {
        return socialRepository.findByUuid(uuid);
    }

    @Override
    public Social saveSocial(Social social) {
        Social model = socialRepository.findByUuid(social.getUuid());
        if (model != null) {
            model.copy(social);
        } else {
            model = social;
        }
        return socialRepository.save(model);
    }

    @Override
    public void resetPassword(Long userId) {
        Account model = this.getOne(userId);
        if (model == null) {
            throw new RuntimeException("用户信息错误");
        }
        if (StringUtils.isBlank(model.getEmail())) {
            throw new RuntimeException("用户未有邮箱，不能重置密码");
        }

        String password = RandomStringUtils.randomAlphanumeric(8);

        baseRepository.updatePassword(userId, passwordEncoder.encode(password));
        Map<String, Object> data = MapUtils.create().set("name", model.getName()).set("password", password);

        String templateName = "email/reset_password.html";

        verifyCodeService.sendMail(model.getEmail(), VerifyCodeScenes.RESET_PASSWORD, password, data, templateName);
    }
}
