package com.ironchain.intfc.modules.demand;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

/**
 * 设备需求
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/demand")
public class DemandController extends ApiBaseController{
	
	@Autowired
	private DemandService demandService;
	
	/**
	 * 获取需求分类
	 * @return
	 */
	@IgnoreAuth
	@GetMapping("/class")
	public R demandClass(){
		return R.ok(demandService.findClassIdAndNameAll());
	}
	
	/**
	 * 需求列表
	 * @return
	 */
	@IgnoreAuth
	@GetMapping("/list")
	public R list(@RequestParam int type, Pageable pageable){
		return R.ok(demandService.findDemandByType(type, pageable));
	}
}
