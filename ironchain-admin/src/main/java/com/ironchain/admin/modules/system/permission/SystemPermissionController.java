package com.ironchain.admin.modules.system.permission;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ironchain.common.dao.SystemPermissionDao;
import com.ironchain.common.domain.SystemPermission;

@Controller
@RequestMapping("/system/permission")
public class SystemPermissionController extends ModelController<SystemPermissionDao, SystemPermission>{
	
	@Autowired
	private SystemPermissionService systemPermissionService;
	
	/**
	 * 权限列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model){
		model.addAttribute("permissionList", systemPermissionService.findTreeSortList(null));
		return "system/permission/permission_list";
	}
	
	/**
	 * 权限编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute SystemPermission systemPermission, Model model){
		//权限列表
		model.addAttribute("permissionList", systemPermissionService.findTreeSelectList(null));
		return "system/permission/permission_form";
	}
	
	/**
	 * 权限编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute SystemPermission systemPermission, Model model){
		//权限列表
		model.addAttribute("permissionList", systemPermissionService.findTreeSelectList(null));
		return "system/permission/permission_form";
	}
	
	/**
	 * 保存权限
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute SystemPermission systemPermission, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "system/permission/permission_form";
		}
		modelDao.save(systemPermission);
		redirectAttributes.addFlashAttribute("message", "操作成功");
		return "redirect:list";
	}
	
	/**
	 * 删除权限
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
		modelDao.delete(id);
	}
}
