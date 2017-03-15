package com.ironchain.admin.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        	throw new UsernameNotFoundException("账号不存在");
        if(systemUser.getStatus() != SystemUser.STATUS_SUCCESS)
        	throw new UserNotActivatedException("账号被锁定");
        return new SecurityUser(systemUser);
    }
}
