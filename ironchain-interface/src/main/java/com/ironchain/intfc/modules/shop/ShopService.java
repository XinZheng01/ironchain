package com.ironchain.intfc.modules.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.ShopClassDao;
import com.ironchain.common.domain.Constants;
import com.ironchain.common.domain.ShopClass;
import com.ironchain.common.kits.SqlKit;

@Service
public class ShopService extends BaseService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ShopClassDao shopClassDao;
	
	/**
	 * 查询商城所有banner
	 * @param pageable
	 * @return
	 */
	public List<Map<String, Object>> findApiBannerList(Pageable pageable) {
		Date now = new Date();
		return SqlKit.create()
				.append("select id, title, img, url, DATE_FORMAT(show_time, '%Y-%m-%d') showTime from shop_banner")
				.append(" where type = 2 and (show_time < ? or show_time is null) and (un_show_time > ? or un_show_time is null) order by sort_id, show_time desc", now, now)
				.query2Page(jdbcTemplate, pageable).getContent();
	}

	/**
	 * 查询商品分类
	 * @return
	 */
	public List<Map<String, Object>> findShopClassList() {
		return SqlKit.create()
			.append("select id, name, parent_id parentId from shop_class order by sort_id")
			.query2Map(jdbcTemplate);
	}

	/**
	 * 获取商城首页商品列表
	 */
	public List<Map<String, Object>> findHomeList() {
		List<ShopClass> topClassList = shopClassDao.findByParentIsNullAndStatus(Constants.DISPLAY_SHOW, new Sort("sortId"));
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> record = null;
		for (ShopClass shopClass : topClassList) {
			record = new HashMap<>();
			record.put("id", shopClass.getId());
			record.put("name", shopClass.getName());
			//分类最新的几个产品
			record.put("newList", 
				SqlKit.create()
					.append("select id,title from shop_product where class_id = ?", shopClass.getId())
					.query2Map(jdbcTemplate)
			);
		}
		return list;
	}

}
