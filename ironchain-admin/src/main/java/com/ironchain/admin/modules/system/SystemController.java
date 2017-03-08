package com.ironchain.admin.modules.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ironchain.common.base.BaseController;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	
	@GetMapping("/test")
	@ResponseBody
	public String test(){
		return "hello world";
	}
}
