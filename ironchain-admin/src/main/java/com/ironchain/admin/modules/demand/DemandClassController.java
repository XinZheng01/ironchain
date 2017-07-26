package com.ironchain.admin.modules.demand;

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
import com.ironchain.common.dao.DemandClassDao;
import com.ironchain.common.domain.DemandClass;
import com.ironchain.common.domain.R;
import com.ironchain.common.domain.enums.DemandType;


/**
 * 需求分类
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/demand/class")
public class DemandClassController extends ModelController<DemandClassDao, DemandClass> {
	
	//@Autowired
	//private DemandClassService demandClassService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "demand/class/demand_class_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute DemandClass demandClass, Model model){
		model.addAttribute("demandTypes", DemandType.values());
		return "demand/class/demand_class_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute DemandClass demandClass, Model model){
		model.addAttribute("demandTypes", DemandType.values());
		return "demand/class/demand_class_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute DemandClass demandClass, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "demand/class/demand_class_form";
		}
		modelDao.save(demandClass);
		redirectAttributes.addFlashAttribute(R.ok().setMsg("操作成功"));
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
		List<DemandClass> entitys = new ArrayList<>();
		DemandClass entity = null;
		for (Long id : ids) {
			entity = new DemandClass();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
