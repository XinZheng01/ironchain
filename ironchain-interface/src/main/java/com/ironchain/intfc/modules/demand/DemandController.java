package com.ironchain.intfc.modules.demand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.dao.DemandClassDao;
import com.ironchain.common.domain.R;
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
	private DemandClassDao demandClassDao;
	
	@GetMapping("/class")
	public R demandClass(){
		demandClassDao.findAll();
		return R.ok();
	}
}
