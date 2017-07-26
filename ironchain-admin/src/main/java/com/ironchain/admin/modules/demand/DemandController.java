package com.ironchain.admin.modules.demand;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
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
import com.ironchain.common.dao.DemandDao;
import com.ironchain.common.domain.Demand;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.SpecificationKit;


/**
 * 需求
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/demand")
public class DemandController extends ModelController<DemandDao, Demand> {
	
	//@Autowired
	//private DemandService demandService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(@SortDefaults({@SortDefault(sort="progress"),
		@SortDefault(sort="createTime", direction=Direction.DESC)}) Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "demand/demand_list";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Demand demand, Model model){
		return "demand/demand_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Demand demand, Model model){
		return "demand/demand_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Demand demand, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			setBindingErrorMsg(model, bindingResult);
			return "demand/demand_form";
		}
		modelDao.save(demand);
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
		List<Demand> entitys = new ArrayList<>();
		Demand entity = null;
		for (Long id : ids) {
			entity = new Demand();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
