package com.ironchain.admin.modules.system.role;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.dao.SystemRoleDao;
import com.ironchain.common.domain.SystemRole;

@Controller
@RequestMapping("/system/role")
public class SystemRoleController extends BaseController {
	
	@Autowired
	private SystemRoleDao systemRoleDao;
	
	/**
	 * 角色列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		Specification<SystemRole> spec = bySearchFilter(request);
		model.addAttribute("rolePage", systemRoleDao.findAll(spec, pageable));
		return "system/role/role_list";
	}
	
	/**
	 * 角色编辑页面
	 * @return
	 */
	@GetMapping("/form")
	public String form(@RequestParam(required=false) Long id, Model model){
		if(id == null)
			model.addAttribute("systemRole", new SystemRole());
		else
			model.addAttribute("systemRole", systemRoleDao.findOne(id));
		//准备角色数据
		model.addAttribute("roleList", systemRoleDao.findAll());
		return "system/role/role_form";
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid SystemRole systemRole, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "system/role/role_form";
		}
		redirectAttributes.addFlashAttribute("message", "操作成功");
		return "redirect:list";
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
		systemRoleDao.delete(id);
	}
}
