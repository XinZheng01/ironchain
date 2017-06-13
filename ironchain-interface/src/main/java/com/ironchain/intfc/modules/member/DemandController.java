package com.ironchain.intfc.modules.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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
	
//	/**
//	 * 参与的竞标
//	 * @return
//	 */
//	public R bidList(){
//		
//	}
	@Autowired
	private DemandService demandService;
	
	
	/**
	 * 发布的需求列表
	 * @return
	 */
	@GetMapping
	public R publishList(@RequestParam int type, @RequestParam Long userId, Pageable pageable,
			@RequestParam(required=false, defaultValue="-1") int progress){
		demandService.findListByUser(userId, type, progress, pageable);
		return R.ok();
	}
}
