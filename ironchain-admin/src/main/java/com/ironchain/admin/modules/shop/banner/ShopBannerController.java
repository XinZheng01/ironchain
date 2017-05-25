package com.ironchain.admin.modules.shop.banner;

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
import com.ironchain.common.dao.ShopBannerDao;
import com.ironchain.common.domain.ShopBanner;
import com.ironchain.common.domain.R;


/**
 * 商城轮播图
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/shop/banner")
public class ShopBannerController extends ModelController<ShopBannerDao, ShopBanner> {
	
	//@Autowired
	//private ShopBannerService shopBannerService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "shop/banner/shop_banner_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute ShopBanner shopBanner, Model model){
		return "shop/banner/shop_banner_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute ShopBanner shopBanner, Model model){
		return "shop/banner/shop_banner_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute ShopBanner shopBanner, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "shop/banner/shop_banner_form";
		}
		modelDao.save(shopBanner);
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
		List<ShopBanner> entitys = new ArrayList<>();
		ShopBanner entity = null;
		for (Long id : ids) {
			entity = new ShopBanner();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
