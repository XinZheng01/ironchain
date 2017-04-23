package com.ironchain.admin.modules.system.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.SystemUserDao;
import com.ironchain.common.domain.ResponseResult;
import com.ironchain.common.domain.SystemUser;
import com.ironchain.common.exception.ServiceException;

@Service
public class SystemUserService extends BaseService {

	@Autowired
	private SystemUserDao systemUserDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 */
	@Transactional
	public void changePwd(Long id, String oldPassword, String newPassword) {
		SystemUser systemUser = systemUserDao.findOne(id);
		if(!passwordEncoder.matches(oldPassword, systemUser.getPassword()))
			throw new ServiceException(ResponseResult.SC_PARAMERROR, "旧密码不正确");
		
		systemUser.setPassword(passwordEncoder.encode(newPassword));
		systemUserDao.save(systemUser);
	}

}
