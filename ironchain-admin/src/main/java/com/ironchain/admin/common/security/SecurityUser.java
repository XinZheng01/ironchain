package com.ironchain.admin.common.security;

import com.ironchain.common.domain.SystemUser;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public SecurityUser(SystemUser systemUser){
		super(systemUser.getName(), systemUser.getPassword(), null);
		this.id = systemUser.getId();
	}

	public Long getId() {
		return id;
	}
	
	public SystemUser getSystemUser(){
		SystemUser systemUser = new SystemUser();
		systemUser.setId(this.id);
		return systemUser;
	}
}
