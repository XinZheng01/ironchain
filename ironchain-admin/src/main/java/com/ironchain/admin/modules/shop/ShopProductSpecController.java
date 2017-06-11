package com.ironchain.admin.modules.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.ironchain.common.dao.ShopProductSpecDao;
import com.ironchain.common.domain.R;
import com.ironchain.common.domain.ShopProductSpec;


/**
 * 商品规格
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/shop/product/spec")
public class ShopProductSpecController extends ModelController<ShopProductSpecDao, ShopProductSpec> {
	
	@Autowired
	private ShopProductSpecService shopProductSpecService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "shop/shop_product_spec_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute ShopProductSpec shopProductSpec, Model model){
		return "shop/shop_product_spec_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute ShopProductSpec shopProductSpec, Model model){
		return "shop/shop_product_spec_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute ShopProductSpec shopProductSpec, @RequestParam Set<String> value, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "shop/shop_product_spec_form";
		}
		shopProductSpecService.save(shopProductSpec, value);
		
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
		List<ShopProductSpec> entitys = new ArrayList<>();
		ShopProductSpec entity = null;
		for (Long id : ids) {
			entity = new ShopProductSpec();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
