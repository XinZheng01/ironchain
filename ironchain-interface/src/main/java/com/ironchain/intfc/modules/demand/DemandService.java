package com.ironchain.intfc.modules.demand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.kits.SqlKit;

@Service
public class DemandService extends BaseService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取需求分类
	 * @return
	 */
	public List<Map<String, Object>> findClassIdAndNameAll(){
		return jdbcTemplate.queryForList("select id, name from demand_class");
	}

	/**
	 * 根据类型获取需求列表
	 * @param type 类型
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findDemandByType(int type, Pageable pageable) {
		return SqlKit.create().append("select id, name from demand_class")
				.append(type > 0, " where type = ?", type)
				.query2PageMap(jdbcTemplate, pageable);
	}
}
