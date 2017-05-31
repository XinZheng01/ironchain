package com.ironchain.intfc.modules.demand;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.persistence.SqlHelper;

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
	public List<Map<String, Object>> findDemandByType(int type, Pageable pageable) {
//		SqlHelper.queryPage(jdbcTemplate, "select id", pageable, params)
		
		return null;
	}
}
