package com.ironchain.intfc.modules;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
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
	
	/**
	 * banner 列表
	 */
	@IgnoreAuth
	@IgnoreApiSecurity
	@GetMapping("/banner")
	public R bannerList(){
		//查询前6个banner
		return R.ok(homeService.findBannerList(getPageable(1, 6, null)));
	}
	
	/**
	 * 需求列表
	 * @return
	 */
	@IgnoreAuth
	@GetMapping("/list")
	public R demand(Pageable pageable){
		Page<Map<String, Object>> page = demandService.findDemandByParam(-1, null, -1,
				null, null, null, pageable);
		Map<String, Object> map = new HashMap<>();
		map.put("content", page.getContent());
		map.put("hasNext", page.hasNext()?1:0);
		return R.ok();
	}
	
}
