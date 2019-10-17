package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.repository.AccountRepository;
import com.xzjie.cms.service.AccountService;
import com.xzjie.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl extends AbstractService<Account, Long> implements AccountService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected JpaRepository getRepository() {
        return accountRepository;
    }


    @Override
    public Account getAccount(Long userId) {
        return accountRepository.findById(userId).orElseGet(Account::new);
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
        Account account = accountRepository.findAccountByName(name);
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
    public List<Account> getAccountList(Account account) {
        Specification<Account> query = new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
//                if(account.getNumber()!=null){
//                    predicates.add(criteriaBuilder.equal(root.get("number"),player.getNumber()));
//                }
//                if(StringUtils.isNotEmpty(player.getGender())){
//                    predicates.add(criteriaBuilder.equal(root.get("gender"),player.getGender()));
//                }
//                if(StringUtils.isNotEmpty(player.getName())){
//                    predicates.add(criteriaBuilder.like(root.get("name"),"%"+player.getName()+"%"));
//                }
//                if(StringUtils.isNotEmpty(player.getIdCard())){
//                    predicates.add(criteriaBuilder.like(root.get("idCard"),"%"+player.getIdCard()+"%"));
//                }
//                if(StringUtils.isNotEmpty(player.getWorksName())){
//                    predicates.add(criteriaBuilder.like(root.get("worksName"),"%"+player.getWorksName()+"%"));
//                }
//                if(StringUtils.isNotEmpty(player.getWorksType())){
//                    predicates.add(criteriaBuilder.equal(root.get("worksType"),player.getWorksType()));
//                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return accountRepository.findAll(query);
    }

    @Override
    public boolean updateAvatar(String username, String avatar) {
        return accountRepository.updateAvatar(username, avatar) > 0;
    }

    @Override
    public boolean updatePassword(Long userId, String encode) {
        return accountRepository.updatePassword(userId, encode) > 0;
    }

    @Override
    public boolean updateEmail(Long userId, String email) {
        return accountRepository.updateEmail(userId, email) > 0;
    }


    @Override
    public boolean update(Account obj) {
        Account model = accountRepository.findById(obj.getUserId()).orElseGet(Account::new);
        model.copy(obj);
        accountRepository.save(model);
        return true;
    }
}
