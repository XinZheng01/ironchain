package com.ironchain.admin.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ironchain.common.domain.SystemUser;

public class SecurityAuditorAware implements AuditorAware<SystemUser> {

	@Override
	public SystemUser getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	      return null;
	    }
	    return ((SecurityUser)authentication.getPrincipal()).getSystemUser();
	}

}
