package com.ironchain.admin.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.dao.SystemUserDao;

@Controller
@RequestMapping("/user")
public class SystemUserController extends BaseController {
	
	@Autowired
	private SystemUserDao systemUserDao;
	
	/**
	 * 登录界面
	 * @return
	 */
	@GetMapping("/login/form")
	public String loginForm(){
		return "user/login";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@PostMapping("/login")
	public String login(){
		return null;
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@PostMapping("/logout")
	public String logout(){
		return null;
	}
	
	/**
	 * 用户列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model){
		model.addAttribute("userPage", systemUserDao.findAll(getPageable(1, 10, null)));
		return "user/user_list";
	}
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@GetMapping("/form")
	public String form(){
		return null;
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@GetMapping("/save")
	public String save(){
		return null;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@GetMapping("/delete")
	public String delete(){
		return null;
	}
	
}
