package com.ironchain.common.dao;

import java.util.List;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopClass;

/**
 * 商品分类
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopClassDao extends BaseDao<ShopClass, Long> {

	List<ShopClass> findByParentIsNull();
	
}
