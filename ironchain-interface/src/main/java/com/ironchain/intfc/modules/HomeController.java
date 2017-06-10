package com.ironchain.intfc.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/home")
public class HomeController extends ApiBaseController{
	
	@Autowired
	private HomeService homeService;
	
	/**
	 * banner 列表
	 */
	@IgnoreApiSecurity
	@IgnoreAuth
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
	public R demand(){
		return R.ok();
	}
	
}
