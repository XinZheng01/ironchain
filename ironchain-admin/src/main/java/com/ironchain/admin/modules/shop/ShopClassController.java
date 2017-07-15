package com.ironchain.admin.modules.shop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ironchain.common.dao.ShopClassDao;
import com.ironchain.common.domain.R;
import com.ironchain.common.domain.ShopClass;


/**
 * 商品分类
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/shop/class")
public class ShopClassController extends ModelController<ShopClassDao, ShopClass> {
	
	@Autowired
	private ShopClassService shopClassService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("shopClassList", shopClassService.findTreeSortList(null));
		return "shop/shop_class_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute ShopClass shopClass, @RequestParam(required=false) Long parentId, Model model){
		if(parentId != null){
			ShopClass parent = modelDao.findOne(parentId);
			shopClass.setParent(parent);
		}
		model.addAttribute("shopClassList", modelDao.findByParentIsNullOrderBySortId());
		return "shop/shop_class_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute ShopClass shopClass, Model model){
		model.addAttribute("shopClassList", modelDao.findByParentIsNullOrderBySortId());
		return "shop/shop_class_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute ShopClass shopClass, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "shop/shop_class_form";
		}
		modelDao.save(shopClass);
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
		List<ShopClass> entitys = new ArrayList<>();
		ShopClass entity = null;
		for (Long id : ids) {
			entity = new ShopClass();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
