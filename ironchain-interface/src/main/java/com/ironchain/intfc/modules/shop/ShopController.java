package com.ironchain.intfc.modules.shop;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/shop")
public class ShopController extends ApiBaseController {
	
	@Autowired
	private ShopService shopService;
	
//	@Autowired
//	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private ShopCartService shopCartService;
	
	/**
	 * 商城轮播图
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/home/banner")
	public R banner(){
		return R.ok(shopService.findApiBannerList(getPageable(1, 6, null)));
	}
	
	/**
	 * 商城首页商品列表
	 * @return
	 */
	@IgnoreAuth
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
	public R list(@RequestParam Long classId, @RequestParam(defaultValue="1") int sortName, @RequestParam(defaultValue="0") int sortType, Pageable pageable){
		return R.ok(page2Map(shopService.findProductList(classId, sortName, sortType, null, pageable)));
	}
	
	/**
	 * 搜索商品
	 * @param key 查询key
	 * @return
	 */
	@RequestMapping("/product/search")
	public R search(@RequestParam Long classId, @RequestParam(defaultValue="1") int sortName, @RequestParam(defaultValue="0") int sortType, @RequestParam String key, Pageable pageable){
		return R.ok(page2Map(shopService.findProductList(classId, sortName, sortType, key, pageable)));
	}
	
	/**
	 * 商品分类
	 * @return
	 */
	@IgnoreAuth
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
	@RequestMapping("/product/details")
	public R details(@RequestParam Long id){
		return R.ok(shopService.findProductDetails(id));
	}
	
	/**
	 * 商品参数
	 * @param id
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/product/params")
	public R params(@RequestParam Long id){
		return R.ok(shopService.findProductParams(id));
	}
	
	/**
	 * 商品详情介绍
	 * @param id
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/product/content")
	public R content(@RequestParam Long id){
		return R.ok(shopService.findProductContent(id));
	}
	
	/**
	 * 商品评价
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/product/evaluates")
	public R evaluates(@RequestParam Long id, Pageable pageable){
		Page<Map<String,Object>> page = shopService.findProductEvaluate(id, pageable);
		Map<String, Object> result = page2Map(page);
		result.put("evaluates", page.getTotalElements());//总评论数
		return R.ok(result);
	}
	
	/**
	 * 商品规格
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/product/spec")
	public R spec(@RequestParam Long id){
		return R.ok(shopService.findProductSpec(id));
	}
	
	/**
	 * 购物车
	 * @param userId
	 * @return
	 */
	@RequestMapping("/cart")
	public R cart(@RequestParam Long userId){
		return R.ok(shopCartService.findShopCartItems(userId));
	}
	
	/**
	 * 添加到购物车
	 * @param userId
	 * @param skuId skuid
	 * @param num 数量 
	 * @return
	 */
	@RequestMapping("/add_cart")
	public R addCart(@RequestParam Long userId, @RequestParam Long skuId, @RequestParam int num){
		shopCartService.addProduct(userId, skuId, num);
		return R.ok();
	}
	
	/**
	 * 修改sku数量
	 * @param userId
	 * @param skuId skuid
	 * @param num 数量 
	 * @return
	 */
	@RequestMapping("/update_cart_num")
	public R updateCartNum(@RequestParam Long userId, @RequestParam Long skuId, @RequestParam int num){
		shopCartService.updateProductNum(userId, skuId, num);
		return R.ok();
	}
	
	/**
	 * 删除购物车商品
	 * @param userId
	 * @param skuId
	 * @return
	 */
	@RequestMapping("/delete_cart")
	public R deleteCart(@RequestParam Long userId, @RequestParam Long skuId){
		shopCartService.deleteProduct(userId, skuId);
		return R.ok();
	}
}
