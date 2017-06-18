package com.ironchain.common.dao;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopProductParam;

/**
 * 商品参数
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopProductParamDao extends BaseDao<ShopProductParam, Long> {

	Long deleteByProductId(Long id);
	
}
