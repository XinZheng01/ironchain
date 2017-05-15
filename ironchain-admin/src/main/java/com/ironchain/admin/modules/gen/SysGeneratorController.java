package com.ironchain.admin.modules.gen;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ironchain.common.domain.R;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(){
//		//查询列表数据
//		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
//		int total = sysGeneratorService.queryTotal(query);
//		
//		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return null;
//		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		String[] tableNames = new String[]{};
//		//获取表名，不进行xss过滤
//		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
//		String tables = orgRequest.getParameter("tables");
//		tableNames = JSON.parseArray(tables).toArray(tableNames);
//		
//		byte[] data = sysGeneratorService.generatorCode(tableNames);
//		
//		response.reset();  
//        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");  
//        response.addHeader("Content-Length", "" + data.length);  
//        response.setContentType("application/octet-stream; charset=UTF-8");  
//  
//        IOUtils.write(data, response.getOutputStream());  
	}
}
