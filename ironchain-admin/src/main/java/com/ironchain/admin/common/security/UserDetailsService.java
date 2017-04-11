package com.ironchain.admin.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ironchain.common.dao.SystemPermissionDao;
import com.ironchain.common.dao.SystemUserDao;
import com.ironchain.common.domain.SystemUser;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private SystemUserDao systemUserDao;
    @Autowired
    private SystemPermissionDao systemPermissionDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        log.debug("Authenticating {}", login);
        //超级管理员
        if(login.equals("superadmin")){
        	log.info("======= superadmin login... =======");
        	return getSuperAdmin();
        }
        SystemUser systemUser = systemUserDao.findOneByLoginName(login);
        if(systemUser == null)
        	throw new BadCredentialsException("用户名或密码错误");
        if(systemUser.getStatus() != SystemUser.STATUS_SUCCESS)
        	throw new UserNotActivatedException("用户被锁定");
        //查询用户拥有的权限
//        List<SystemPermission> codes = systemPermissionDao.findCodeByUser(systemUser.getId());
        return new SecurityUser(systemUser);
    }
    
    public SecurityUser getSuperAdmin(){
    	return new SecurityUser(Long.valueOf(0l), "superadmin", "$2a$10$ZYXP85rYC.cXHT/Uv5koOuoaqLM1VvkTdxl5Iy3glm52vqeearyNu", null);
    }
    
    public static void main(String[] args) {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	System.out.println(encoder.encode("admin"));
	}
}
