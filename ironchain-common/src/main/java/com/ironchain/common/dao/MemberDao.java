package com.ironchain.common.dao;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.Member;

public interface MemberDao extends BaseDao<Member, Long>{

	/**
	 * 根据手机号码和密码查找会员
	 * @param mobilephone
	 * @param password
	 * @return
	 */
	Member findByMobilephoneAndPassword(String mobilephone, String password);

}
