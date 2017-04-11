package com.ironchain.admin.common.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.ironchain.common.domain.SystemUser;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private SystemUser systemUser;

	public SecurityUser(SystemUser systemUser){
		super(systemUser.getName(), systemUser.getPassword(), new ArrayList<GrantedAuthority>());
		this.id = systemUser.getId();
		this.systemUser = new SystemUser();
		this.systemUser.setId(this.id);
	}
	
	public SecurityUser(Long id, String name, String password, Collection<? extends GrantedAuthority> authorities){
		super(name, password, authorities);
		this.id = id;
		this.systemUser = new SystemUser();
		this.systemUser.setId(id);
	}

	public Long getId() {
		return this.id;
	}
	
	public SystemUser getSystemUser(){
		return this.systemUser;
	}
}
