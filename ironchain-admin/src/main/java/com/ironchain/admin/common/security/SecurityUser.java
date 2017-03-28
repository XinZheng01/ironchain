package com.ironchain.admin.common.security;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;

import com.ironchain.common.domain.SystemUser;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public SecurityUser(SystemUser systemUser){
		super(systemUser.getName(), systemUser.getPassword(), new ArrayList<GrantedAuthority>());
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
