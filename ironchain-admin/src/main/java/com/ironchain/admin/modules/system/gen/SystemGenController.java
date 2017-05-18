package com.ironchain.admin.modules.system.gen;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ironchain.common.base.BaseController;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/system/gen")
public class SystemGenController extends BaseController{
	
	@Autowired
	private SystemGenService sysGenService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, Model model){
		model.addAttribute("page", sysGenService.findAllTable(pageable));
		return "system/gen/gen_list";
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/gen_code")
	public void genCode(String[] tableNames, HttpServletRequest request, HttpServletResponse response) throws IOException{
		byte[] data = sysGenService.generatorCode(tableNames);
		response.reset();
        response.setHeader("Content-Disposition	", "attachment; filename=\"gen_code.zip\"");  
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");  
        response.getOutputStream().write(data);
	}
}
