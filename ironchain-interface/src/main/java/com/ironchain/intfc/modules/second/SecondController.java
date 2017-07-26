package com.ironchain.intfc.modules.second;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

/**
 * 唤醒
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/second")
public class SecondController extends ApiBaseController{
	
	/**
	 * 唤醒商城轮播图
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/home/banner")
	public R banner(){
		return R.ok();
	}
	
	/**
	 * 唤醒首页商品列表
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/home/list")
	public R homeList(){
		return R.ok();
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
		return R.ok();
	}
	
	/**
	 * 唤醒商城分类
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/class")
	public R secondClass(){
		return R.ok();
	}
}
