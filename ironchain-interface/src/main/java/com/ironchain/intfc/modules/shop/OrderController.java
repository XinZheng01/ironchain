package com.ironchain.intfc.modules.shop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.web.ApiBaseController;

@RestController("shopOrderController")
@RequestMapping("/shop/order")
public class OrderController extends ApiBaseController{
	
	@RequestMapping("/create")
	public R createOrder(){
		
		return R.ok();
	}
}
