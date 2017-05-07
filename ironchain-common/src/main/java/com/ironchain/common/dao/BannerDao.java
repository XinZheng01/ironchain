package com.ironchain.common.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.Banner;

public interface BannerDao extends BaseDao<Banner, Long> {

	@Query("select b from Banner b where b.type = 2 and b.showTime < CURRENT_TIMESTAMP and b.unShowTime > CURRENT_TIMESTAMP")
	List<Banner> findApiBannerList(Pageable pageable);

}
