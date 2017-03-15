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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        log.debug("Authenticating {}", login);
        SystemUser systemUser = systemUserDao.findOneByLoginName(login);
        if(systemUser == null)
        	throw new BadCredentialsException("用户名或密码错误");
        if(systemUser.getStatus() != SystemUser.STATUS_SUCCESS)
        	throw new UserNotActivatedException("用户被锁定");
        return new SecurityUser(systemUser);
    }
    
    public static void main(String[] args) {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	System.out.println(encoder.encode("admin"));
	}
}
