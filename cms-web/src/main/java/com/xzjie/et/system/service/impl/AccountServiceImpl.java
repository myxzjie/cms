package com.xzjie.et.system.service.impl;

import com.xzjie.et.system.dao.AccountRoleMapper;
import com.xzjie.et.system.model.AccountRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.core.utils.RegexUtils;
import com.xzjie.et.system.dao.AccountMapper;
import com.xzjie.et.system.model.Account;
import com.xzjie.et.system.service.AccountService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;

@Service("accountService")
public class AccountServiceImpl extends AbstractBaseService<Account, Long> implements AccountService {
	private final Logger LOG = LogManager.getLogger(getClass());
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountRoleMapper accountRoleMapper;
	@Autowired
	private PasswordHelper passwordHelper;

	enum decideType {
		NAME, PHONE, E_MAIL
	}

	@Override
	protected BaseMapper<Account, Long> getMapper() {
		return accountMapper;
	}

	@Override
	public boolean isExistName(String name, Long filterId) {
		return isExist(name, decideType.NAME, filterId);
	}

	@Override
	public boolean delete(Long id) {
		Account model = new Account();
		model.setUserId(id);
		model.setState(0);
		return this.update(model);
	}

	@Override
	public boolean isExistPhone(String phone, Long filterId) {
		return isExist(phone, decideType.PHONE, filterId);
	}

	@Override
	public boolean isExistEmail(String eMail, Long filterId) {
		return isExist(eMail, decideType.E_MAIL, filterId);
	}

	private boolean isExist(String value, decideType type, Long filterId) {
		Account account = new Account();

		switch (type) {
		case NAME:
			account.setName(value);
			break;
		case PHONE:
			account.setPhone(value);
			break;
		case E_MAIL:
			account.seteMail(value);
			break;
		default:
			break;
		}

		if (filterId != null) {
			account.setUserId(filterId);
		}

		return accountMapper.exist(account) > 0;
	}

	@Override
	public Account getAccountLogin(String value) {
		Account record = new Account();

		// 用户名查询
		if (RegexUtils.checkUserName(value)) {
			record.setName(value);
		} else if (RegexUtils.checkMobile(value)) { // 手机号查询
			record.setPhone(value);
		} else if (RegexUtils.checkEmail(value)) { // 邮箱查询
			record.seteMail(value);
		}

		return accountMapper.selectAccount(record);
	}

	@Override
	public boolean saveAccount(Account model, Long roleId) throws Exception {
		AccountRole accountRole = new AccountRole();

		passwordHelper.encryptPassword(model);

		this.save(model);

		if (model.getUserId() == null) {
			throw new Exception("用户ID是空");
		}
		accountRole.setRoleId(roleId);
		accountRole.setUserId(model.getUserId());

		accountRoleMapper.insertSelective(accountRole);
		return true;
	}

	@Override
	public String resetPassword(Long userId) {
		Account model = get(userId);
		String password = passwordHelper.getPassWord(6);

		model.setPassword(password);
		passwordHelper.encryptPassword(model);
		LOG.info("用户：{}，重置密码：{}", model.getName(), password);
		accountMapper.resetPassword(model);

		return password;
	}

	@Override
	public boolean updatePassword(Long userId, String name, String password) {
		Account model = new Account();
		model.setUserId(userId);
		model.setName(name);

		model.setPassword(password);
		passwordHelper.encryptPassword(model);
		return accountMapper.resetPassword(model) > 0;
	}

	@Override
	public boolean updateAccount(Account model, Long roleId) throws Exception {
		AccountRole accountRole = new AccountRole();
		this.update(model);
		accountRole.setUserId(model.getUserId());
		accountRole.setRoleId(roleId);
		accountRoleMapper.updateByUserId(accountRole);
		return true;
	}

}
