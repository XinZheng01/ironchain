package com.ironchain.admin.modules.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ironchain.common.base.BaseController;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	
	@GetMapping("/test")
	@ResponseBody
	public String test(@RequestParam String param){
		System.out.println(param);
		return param;
	}
	
	@PostMapping("/test2")
	@ResponseBody
	public String test2(@RequestParam String param){
		return param;
	}
	
}
