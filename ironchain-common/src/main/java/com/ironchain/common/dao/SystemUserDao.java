package com.ironchain.common.dao;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.SystemUser;

public interface SystemUserDao extends BaseDao<SystemUser, Long>{

	SystemUser findOneByLoginName(String login);

}
