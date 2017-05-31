package com.ironchain.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.DemandClass;

/**
 * 需求分类
 * 
 * @author zheng xin
 * @email 
 */
public interface DemandClassDao extends BaseDao<DemandClass, Long> {
	
	@Query(value="select c.id, c.name from demand_class c", nativeQuery=true)
	List<Object[]> findIdAndNameAll();
	
}
