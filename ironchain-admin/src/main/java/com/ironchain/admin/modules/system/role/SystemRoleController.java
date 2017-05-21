package com.ironchain.admin.modules.system.role;

import java.util.Set;

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
import com.ironchain.common.dao.SystemPermissionDao;
import com.ironchain.common.dao.SystemRoleDao;
import com.ironchain.common.domain.R;
import com.ironchain.common.domain.SystemPermission;
import com.ironchain.common.domain.SystemRole;
import com.ironchain.common.kits.JsonKit;

@Controller
@RequestMapping("/system/role")
public class SystemRoleController extends ModelController<SystemRoleDao, SystemRole> {
	
	@Autowired
	private SystemPermissionDao systemPermissionDao;
	
	/**
	 * 角色列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		Specification<SystemRole> spec = bySearchFilter(request);
		model.addAttribute("rolePage", modelDao.findAll(spec, pageable));
		return "system/role/role_list";
	}
	
	/**
	 * 角色新增页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute SystemRole systemRole, Model model){
		//准备角色数据
		model.addAttribute("permissionList", systemPermissionDao.findAll());
		return "system/role/role_form";
	}
	
	/**
	 * 角色编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute SystemRole systemRole, Model model){
		model.addAttribute("permissionIds", JsonKit.normal().toJson(systemRole.getPermissionIds()));
		//准备角色数据
		model.addAttribute("permissionList", systemPermissionDao.findAll());
		return "system/role/role_form";
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute SystemRole systemRole, 
			@RequestParam(required=false) Set<SystemPermission> permissions,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "system/role/role_form";
		}
		
		if(permissions == null)
			systemRole.getPermissions().clear();
		modelDao.save(systemRole);
		redirectAttributes.addFlashAttribute(R.ok().setMsg("操作成功"));
		return "redirect:list";
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
		modelDao.delete(id);
	}
}
