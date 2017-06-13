package com.ironchain.intfc.modules.information;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.domain.Information;
import com.ironchain.common.kits.SqlKit;

@Service
public class InformationService extends BaseService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 资讯列表
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> findAll(Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		Page<Map<String, Object>> page = SqlKit.create().append("select id,type,title,img,DATE_FORMAT(update_time, '%m-%d') updateTime, 0 comment")
				.append(" from information where status = ? order by sort_id, update_time desc", Information.STATUS_SHOW)
				.query2Page(jdbcTemplate, pageable);
		map.put("content", page.getContent());
		map.put("hasNext", page.hasNext()?1:0);
		return map;
	}

}
