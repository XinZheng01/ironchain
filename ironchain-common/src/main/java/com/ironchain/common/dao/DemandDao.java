package com.ironchain.common.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Lock;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.Demand;

public interface DemandDao extends BaseDao<Demand, Long> {
	
	@EntityGraph(attributePaths={"d.gdArea", "d.mainEquipment", "d.demandClass"},
		type=EntityGraphType.FETCH)
	@Query("select d from Demand d where d.id=?1")
	public Demand readById(Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select d from Demand d where d.id=?1")
	public Demand lockById(Long id);
}
