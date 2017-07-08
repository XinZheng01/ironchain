package com.ironchain.admin.modules.${packageName};

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ironchain.common.base.ModelController;
import com.ironchain.common.dao.${className}Dao;
import com.ironchain.common.domain.${className};
import com.ironchain.common.domain.R;


/**
 * ${tableComment!}
 * 
 * @author ${author}
<#if email??>
 * @email ${email}
</#if>
 */
@Controller
@RequestMapping("/${pathName}")
public class ${className}Controller extends ModelController<${className}Dao, ${className}> {
	
	//@Autowired
	//private ${className}Service ${modelName}Service;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "${pathName}/${tableNameLower}_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute ${className} ${modelName}, Model model){
		return "${pathName}/${tableNameLower}_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute ${className} ${modelName}, Model model){
		return "${pathName}/${tableNameLower}_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute ${className} ${modelName}, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			setBindingErrorMsg(model, bindingResult);
			return "${pathName}/${tableNameLower}_form";
		}
		modelDao.save(${modelName});
		setSuccessMsg(redirectAttributes, "操作成功");
		return "redirect:list";
	}
	
	/**
	 * 删除
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public R delete(@RequestParam Long[] ids){
		if(ids == null || ids.length == 0)
			return R.error("请选择删除数据");
		List<${className}> entitys = new ArrayList<>();
		${className} entity = null;
		for (Long id : ids) {
			entity = new ${className}();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
