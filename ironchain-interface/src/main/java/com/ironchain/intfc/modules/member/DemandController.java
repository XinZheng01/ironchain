package com.ironchain.intfc.modules.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.modules.demand.DemandService;
import com.ironchain.intfc.web.ApiBaseController;

/**
 * 
 * @author Administrator
 *
 */
@RestController("memberDemandController")
@RequestMapping("/api/member/demand")
public class DemandController extends ApiBaseController {
	
	@Autowired
	private DemandService demandService;
	
	
	/**
	 * 获取用户发布的发布的需求列表
	 * @param type 类型 1加工需求 2设备服务3塑胶4电子
	 * @param userId 用户id
	 * @param pageable 
	 * @param progress 进度
	 * @return
	 */
	@RequestMapping("/publish_list")
	public R publishList(@RequestParam int type, @RequestParam Long userId, Pageable pageable,
			@RequestParam(required=false, defaultValue="-1") int progress){
		return R.ok(page2Map(demandService.findListByUser(userId, type, progress, pageable)));
	}
	
	/**
	 * 获取用户参与竞标的需求
	 * @param type 类型 1加工需求 2设备服务3塑胶4电子
	 * @param userId 用户id
	 * @param pageable 
	 * @param progress 进度
	 * @return
	 */
	@RequestMapping("/bid_list")
	public R bidList(@RequestParam int type, @RequestParam Long userId, Pageable pageable,
			@RequestParam(required=false, defaultValue="-1") int progress){
		return R.ok(page2Map(demandService.findBidListByUser(userId, type, progress, pageable)));
	}
	
	/**
	 * 刷新需求
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping("/refresh")
	public R refresh(@RequestParam Long id, @RequestParam Long userId){
		return R.ok();
	}
}
