package com.ironchain.common.dao;

import java.util.List;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.MemberLevel;

/**
 * 会员等级管理
 * 
 * @author zheng xin
 * @email 
 */
public interface MemberLevelDao extends BaseDao<MemberLevel, Long> {

	/**
	 * 查询生效的会员等级
	 * 根据价格升序
	 * @return
	 */
	List<MemberLevel> findByStatusOrderByPriceAsc(int status);
	
}
