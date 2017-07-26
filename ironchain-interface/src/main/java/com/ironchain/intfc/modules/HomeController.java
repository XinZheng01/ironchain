package com.ironchain.intfc.modules;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.modules.demand.DemandService;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/home")
public class HomeController extends ApiBaseController{
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private DemandService demandService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * banner 列表
	 */
	@IgnoreAuth
	@RequestMapping("/banner")
	public R bannerList(){
		//查询前6个banner
		return R.ok(homeService.findBannerList(getPageable(1, 6, null)));
	}
	
	/**
	 * 需求列表
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/list")
	public R demand(Pageable pageable){
		return R.ok(page2Map(demandService.findDemandByParam(-1, null, -1,
				null, null, null, pageable)));
	}
	
	@RequestMapping("/test")
	public R test(){
		Map<String, Object> result = new HashMap<>();
		result.put("name", "zhengxin");
		result.put("name2", "郑鑫");
		result.put("name3", "郑鑫3");
		result.put("name4", "马棉欢");
		stringRedisTemplate.opsForValue().set("haha", JsonKit.nonNull().toJson(result));
		return R.ok();
	}
}
