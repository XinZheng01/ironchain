package com.ironchain.intfc.modules.demand;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.domain.Demand;
import com.ironchain.common.kits.DateKit;
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
	 * @param classId 分类id
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findDemandByTypeAndClassId(int type, Long classId, Pageable pageable) {
		Page<Map<String, Object>> page = SqlKit.create().append("select d.id, d.title, d.description,")
				.append(" c.name, d.start_date startDate, d.end_date endDate, d.progress")
				.append(" from demand d, demand_class c where d.class_id = c.id")
				.append(type > 0, " and d.type = ?", type)//类型
				.append(classId > 0, " and d.class_id = ?", classId)//分类
				.append(" and d.progress in(?,?,?,?,?) ",
						Demand.PROGRESS_PUBLISH, Demand.PROGRESS_BID, Demand.PROGRESS_SEND,
						Demand.PROGRESS_PAY, Demand.PROGRESS_COMPLETE)//已发布
				.query2PageMap(jdbcTemplate, pageable);
		//处理数据
		Date date = null;
		Number progress = null;
		for (Map<String, Object> map : page) {
			if((date = (Date) map.get("startDate")) != null)
				map.put("startDate", DateKit.formatDate(date, "yyyy/MM/dd"));
			if((date = (Date) map.get("endDate")) != null)
				map.put("endDate", DateKit.formatDate(date, "yyyy/MM/dd"));
			if((progress = (Number) map.get("progress")) != null)
				map.put("progress", Demand.parseProgress(progress.intValue()));
		}
		return page;
	}
}
