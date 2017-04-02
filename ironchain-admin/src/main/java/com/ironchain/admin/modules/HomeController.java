package com.ironchain.admin.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ironchain.common.base.BaseController;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping
	public String index(){
		return "index";
	}
}
