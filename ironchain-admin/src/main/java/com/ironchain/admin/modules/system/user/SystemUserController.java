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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ironchain.common.base.ModelController;
import com.ironchain.common.dao.SystemRoleDao;
import com.ironchain.common.dao.SystemUserDao;
import com.ironchain.common.domain.SystemUser;

@Controller
@RequestMapping("/system/user")
public class SystemUserController extends ModelController<SystemUserDao, SystemUser> {
	
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemRoleDao systemRoleDao;
	
	/**
	 * 登录界面
	 * @return
	 */
	@GetMapping("/login/form")
	public String loginForm(){
		return "system/user/login";
	}
	
	/**
	 * 用户列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		Specification<SystemUser> spec = bySearchFilter(request);
		model.addAttribute("userPage", modelDao.findAll(spec, pageable));
		return "system/user/user_list";
	}
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute SystemUser systemUser, Model model){
		//准备角色数据
		model.addAttribute("roleList", systemRoleDao.findAll());
		return "system/user/user_form";
	}
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute SystemUser systemUser, Model model){
		//准备角色数据
		model.addAttribute("roleList", systemRoleDao.findAll());
		return "system/user/user_form";
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid SystemUser systemUser, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "system/user/user_form";
		}
//		systemUserService.saveOrUpdate(systemUser);
		redirectAttributes.addFlashAttribute("message", "操作成功");
		return "redirect:list";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
		modelDao.delete(id);
	}
	
}
