package com.ironchain.intfc.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.kits.SqlKit;

@Service
public class HomeService extends BaseService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> findBannerList(Pageable pageable) {
		Date now = new Date();
		return SqlKit.create()
				.append("select id, title, img, url, DATE_FORMAT(show_time, '%Y-%m-%d') showTime from banner")
				.append(" where type = 2 and (show_time < ? or show_time is null) and (un_show_time > ? or un_show_time is null) order by sort_id, show_time desc", now, now)
				.query2Page(jdbcTemplate, pageable).getContent();
	}

}
