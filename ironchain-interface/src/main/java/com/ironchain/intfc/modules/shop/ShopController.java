package com.ironchain.intfc.modules.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/shop")
public class ShopController extends ApiBaseController {
	
	@Autowired
	private ShopService shopService;
	
	/**
	 * 商城轮播图
	 * @return
	 */
	@IgnoreAuth
	@IgnoreApiSecurity
	@GetMapping("/home/banner")
	public R banner(){
		return R.ok(shopService.findApiBannerList(getPageable(1, 6, null)));
	}
	
	/**
	 * 商城首页商品列表
	 * @return
	 */
	@IgnoreAuth
	@IgnoreApiSecurity
	@GetMapping("/home/list")
	public R homeList(){
		return R.ok();
	}
	
	/**
	 * 商品列表
	 * @param shopClass 商品分类
	 * @param pageable 分页
	 * @param sortType 排序类型 1综合 2销量 3评分 4价格
	 * @return
	 */
	@GetMapping("/goods/list")
	public R list(@RequestParam int shopClass, Pageable pageable, @RequestParam(defaultValue="1") int sortType){
		return R.ok();
	}
	
	/**
	 * 商品分类
	 * @return
	 */
	@GetMapping("/goods/class")
	public R goodsClass(){
		return R.ok();
	}
}
