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
	 * 保存或更新用户
	 * @param systemUser
	 */
	@Transactional
	public void saveOrUpdate(SystemUser systemUser) {
		Long id = systemUser.getId();
		if(id == null){
			if(systemUser.getPassword() == null)
				throw new ServiceException(ResponseResult.SC_PARAMERROR, "密码不能为空");
			//密码加密
			systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
			systemUserDao.save(systemUser);
			return;
		}
		SystemUser systemUserPO = systemUserDao.findOne(id);
		systemUserPO.setLoginName(systemUser.getLoginName());
		systemUserPO.setName(systemUser.getName());
		systemUserPO.setEmail(systemUser.getEmail());
		systemUserPO.setMobilephone(systemUser.getMobilephone());
		systemUserPO.setStatus(systemUser.getStatus());
		systemUserPO.setRoles(systemUser.getRoles());
		
		systemUserDao.save(systemUserPO);
	}

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
