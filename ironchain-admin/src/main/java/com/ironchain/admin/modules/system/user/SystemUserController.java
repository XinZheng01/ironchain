package com.ironchain.admin.modules.system.user;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.dao.SystemUserDao;
import com.ironchain.common.domain.SystemUser;

@Controller
@RequestMapping("/system/user")
public class SystemUserController extends BaseController {
	
	@Autowired
	private SystemUserDao systemUserDao;
	
	/**
	 * 登录界面
	 * @return
	 */
	@GetMapping("/login/form")
	public String loginForm(){
		return "system/user/login";
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
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		Specification<SystemUser> spec = bySearchFilter(request);
		model.addAttribute("userPage", systemUserDao.findAll(spec, pageable));
		return "system/user/user_list";
	}
	
	/**
	 * 用户添加页面
	 * @return
	 */
	@GetMapping("/create")
	public String createForm(Model model){
		model.addAttribute("systemUser", new SystemUser());
		return "system/user/user_form";
	}
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable Long id, Model model){
		if(id != null)
			model.addAttribute("systemUser", systemUserDao.findOne(id));
		return "system/user/user_form";
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public String save(@Valid SystemUser systemUser, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			System.out.println(bindingResult);
		}
		if(systemUser.getId() == null){//新增
			
			
		}else{//修改
			
		}
		return "aaa";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public void delete(@PathVariable Long id){
		systemUserDao.delete(id);
	}
	
}
