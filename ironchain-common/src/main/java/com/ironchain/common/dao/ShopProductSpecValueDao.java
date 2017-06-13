package com.ironchain.common.dao;

import java.util.List;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopProductSpecValue;
import com.ironchain.common.domain.ShopProductSpecValue.SpecValueVO;

/**
 * 商品规格值
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopProductSpecValueDao extends BaseDao<ShopProductSpecValue, Long> {

	List<SpecValueVO> findBySpecId(Long id);
	
}
