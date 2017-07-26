package com.ironchain.intfc.modules.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.dao.GdAreaDao;
import com.ironchain.common.domain.GdArea;
import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/common")
public class CommonController extends ApiBaseController {
	
	@Autowired
	private GdAreaDao gdAreaDao;
	
	/**
	 * 区域版本
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/area/version")
	public R areaVersion(){
		return R.ok("20170602");
	}
	
	/**
	 * 区域json
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/area/json")
	public R area(){
		List<GdArea> list = gdAreaDao.findAll();
		Map<String, Object> result = new LinkedHashMap<>();
		List<Map<String, Object>> p = new ArrayList<>();//省
		List<Map<String, Object>> c = new ArrayList<>();//市
//		Map<>
		//province city district
		Iterator<GdArea> it = list.iterator();
		Iterator<GdArea> cityIt = null;
		Map<String, Object> map = null;
		GdArea province = null;
		GdArea city = null;
		while(it.hasNext()){
			province = it.next();
			if("province".equals(province.getLevel())){//省
				map = new HashMap<>();
				map.put("adcode", province.getAdcode());
				map.put("name", province.getName());
				p.add(map);
				it.remove();
				cityIt = list.iterator();
				while(cityIt.hasNext()){
					city = cityIt.next();
					if("city".equals(city.getLevel())){//市
					
					}
				}
			}
		}
//		for (GdArea gdArea : list) {
//			if("province".equals(gdArea.getLevel())){//省
//				
//			}
//		}
		
		return R.ok(result);
	}
	
	/**
	 * 支付方式
	 * 
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/payment_method")
	public R paymentMethod(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("ali", 1);
		map.put("weixin", 1);
		map.put("union", 1);
		return R.ok(map);
	}
}
