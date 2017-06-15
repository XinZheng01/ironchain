package com.ironchain.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopProductSpec;

/**
 * 商品规格
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopProductSpecDao extends BaseDao<ShopProductSpec, Long> {

	@EntityGraph(attributePaths="specValues", type=EntityGraphType.FETCH)
	List<ShopProductSpec> findByIdIn(Long[] ids);
	
}
