package com.ironchain.intfc.modules.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	@RequestMapping("/home/banner")
	public R banner(){
		return R.ok(shopService.findApiBannerList(getPageable(1, 6, null)));
	}
	
	/**
	 * 商城首页商品列表
	 * @return
	 */
	@IgnoreAuth
	@IgnoreApiSecurity
	@RequestMapping("/home/list")
	public R homeList(){
		return R.ok(shopService.findHomeList());
	}
	
	/**
	 * 商品列表
	 * @param shopClass 商品分类
	 * @param pageable 分页
	 * @param sortName 排序方式 1综合 2销量 3评分 4价格
	 * @param sortType 排序类型 0 ASC 1 DESC
	 * @return
	 */
	@RequestMapping("/product/list")
	public R list(@RequestParam Long classId, Pageable pageable, @RequestParam(defaultValue="1") int sortName, @RequestParam(defaultValue="0") int sortType){
		
		return R.ok();
	}
	
	/**
	 * 商品分类
	 * @return
	 */
	@IgnoreAuth
	@IgnoreApiSecurity
	@RequestMapping("/class")
	public R shopClass(){
		return R.ok(shopService.findShopClassList());
	}
	
	/**
	 * 商品详情
	 * @param id
	 * @return
	 */
	@IgnoreAuth
	@IgnoreApiSecurity
	@RequestMapping("/product/details")
	public R details(@RequestParam Long id){
		return R.ok();
	}
}
