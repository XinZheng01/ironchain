package com.ironchain.common.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.DemandFile;

public interface DemandFileDao extends BaseDao<DemandFile, Long> {

	@Modifying
	@Query("delete from DemandFile f where f.id=?1 and f.demand.publisher.id=?2")
	void deleteByIdAndUserId(Long id, Long userId);

}
