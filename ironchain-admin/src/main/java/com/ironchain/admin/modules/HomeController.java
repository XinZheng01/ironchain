package com.ironchain.admin.modules;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ironchain.admin.common.security.SecurityUtils;
import com.ironchain.common.base.BaseController;
import com.ironchain.common.dao.SystemPermissionDao;

@Controller
public class HomeController extends BaseController{
	
	@Autowired
	private SystemPermissionDao systemPermissionDao;
	
	@GetMapping({"/", "/index"})
	public String index(HttpSession session){
		if(session.getAttribute("userMenu") == null)
			session.setAttribute("userMenu", systemPermissionDao.findMenuByUserId(SecurityUtils.getCurrentUser().getId()));
		return "index";
	}
}
