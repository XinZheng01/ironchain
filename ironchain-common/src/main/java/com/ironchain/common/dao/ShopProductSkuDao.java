package com.ironchain.common.dao;

import java.util.List;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopProductSku;

/**
 * 商品SKU
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopProductSkuDao extends BaseDao<ShopProductSku, Long> {

	List<ShopProductSku> findByProductId(Long id);

	Long deleteByProductId(Long id);
	
}
