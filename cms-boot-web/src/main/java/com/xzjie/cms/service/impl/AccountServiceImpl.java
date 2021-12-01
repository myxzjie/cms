package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.UserRequest;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.persistence.SpecSearchCriteria;
import com.xzjie.cms.repository.AccountRepository;
import com.xzjie.cms.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl extends AbstractService<Account, AccountRepository> implements AccountService {


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
//    public UserVo findUserInfo(String account) {
//        User user = findByAccount(account);
//        UserVo result = new UserVo(user.getUid(), user.getAvatar(), user.getNickname(), user.getAccount(), user.getMail());
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
        Account account = new Account();
        account.setName(mobile);
        return null; // accountMapper.selectAccount(account);
    }

    @Override
    public Page<Account> getAccountList(UserRequest query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("userId").descending());
        Specification<Account> specification = SpecSearchCriteria.builder(query);
        return baseRepository.findAll(specification, pageable);
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
    public boolean save(Account obj) {
        obj.setState(1);
        obj.setCreateDate(LocalDateTime.now());
        return super.save(obj);
    }

    @Override
    public boolean update(Account obj) {
        Account model = baseRepository.findById(obj.getUserId()).orElseGet(Account::new);
        model.copy(obj);
        baseRepository.save(model);
        return true;
    }
}
