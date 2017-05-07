package com.ironchain.intfc.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.dao.BannerDao;
import com.ironchain.common.domain.Banner;
import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/home")
public class HomeController extends ApiBaseController{
	
	@Autowired
	private BannerDao bannerDao;
	
	/**
	 * banner 列表
	 */
	@IgnoreApiSecurity
	@IgnoreAuth
	@GetMapping("/banner")
	public R bannerList(){
		//查询前6个banner
		List<Banner> bannerList = bannerDao.findApiBannerList(
				getPageable(1, 6, new Sort(new Order("b.sortId"), new Order(Direction.DESC, "b.updateTime"))));
		return R.ok(bannerList);
	}
	
	/**
	 * 需求列表
	 * @return
	 */
	@IgnoreAuth
	@GetMapping("/demand")
	public R demand(){
		return R.ok();
	}
}
