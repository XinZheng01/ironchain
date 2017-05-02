package com.ironchain.intfc.modules;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.annotation.IgnoreAuth;

@RestController
@RequestMapping("/api/user")
public class UserController{
	
	@IgnoreAuth
	@IgnoreApiSecurity
	@RequestMapping("/test")
	public Map<String,Object> test(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "aaa");
		map.put("id", 1);
		return map;
	}
}
