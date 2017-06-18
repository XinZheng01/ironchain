package com.ironchain.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopProductSpec;

/**
 * 商品规格
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopProductSpecDao extends BaseDao<ShopProductSpec, Long> {

	@Query("select s from ShopProductSpec s where s.id in(?1)")
	List<ShopProductSpec> findByIdIn(List<Long> ids);
	
}
