package com.ironchain.admin.common.security;

import org.springframework.data.domain.AuditorAware;

import com.ironchain.common.domain.SystemUser;

public class SecurityAuditorAware implements AuditorAware<SystemUser> {

	@Override
	public SystemUser getCurrentAuditor() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	    if (authentication == null || !authentication.isAuthenticated()) {
//	      return null;
//	    }
//
//	    return ((MyUserDetails) authentication.getPrincipal()).getUser();
		return null;
	}

}
