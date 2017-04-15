package com.ironchain.common.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.SystemUser;

public interface SystemUserDao extends BaseDao<SystemUser, Long>{

	/**
	 * 根据id查找用户，并加载角色及权限数据
	 * @param id
	 * @return
	 */
	@EntityGraph(attributePaths="roles.permissions", type=EntityGraphType.FETCH)
	@Query("select u from SystemUser u where u.loginName=?1")
	SystemUser readByLoginName(String login);

}
