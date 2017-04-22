package com.ironchain.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.SystemPermission;

public interface SystemPermissionDao extends BaseDao<SystemPermission, Long> {
	
	/**
	 * 查询用户的菜单列表
	 * @param userId
	 * @return
	 */
	@Query("select p from SystemPermission p join p.roles r join r.users u where u.id=?1 and p.type <> 3 and p.status = 1")
	List<SystemPermission> findMenuByUserId(Long userId);
	
}
