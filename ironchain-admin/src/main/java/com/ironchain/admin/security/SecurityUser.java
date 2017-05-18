package com.ironchain.admin.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.ironchain.common.domain.SystemUser;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String loginName;
	
	private SystemUser systemUser;

	public SecurityUser(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities){
		super(systemUser.getName(), systemUser.getPassword(), authorities);
		this.id = systemUser.getId();
		this.loginName = systemUser.getLoginName();
		this.systemUser = new SystemUser();
		this.systemUser.setId(this.id);
	}
	
	public SecurityUser(Long id, String name, String password, Collection<? extends GrantedAuthority> authorities, String loginName){
		super(name, password, authorities);
		this.id = id;
		this.loginName = loginName;
		this.systemUser = new SystemUser();
		this.systemUser.setId(id);
	}

	public Long getId() {
		return this.id;
	}
	
	public String getLoginName(){
		return this.loginName;
	}
	
	public SystemUser getSystemUser(){
		return this.systemUser;
	}
	
}
