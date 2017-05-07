package com.ironchain.intfc.modules;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/user")
public class UserController extends ApiBaseController{
	
	@IgnoreAuth
	@RequestMapping("/test")
	public Map<String,Object> test(String asd){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "aaa");
		map.put("id", 1);
		return map;
	}
}
