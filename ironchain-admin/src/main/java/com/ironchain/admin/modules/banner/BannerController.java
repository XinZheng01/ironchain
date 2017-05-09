package com.ironchain.admin.modules.banner;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ironchain.common.base.ModelController;
import com.ironchain.common.dao.BannerDao;
import com.ironchain.common.domain.Banner;

@Controller
@RequestMapping("/banner")
public class BannerController extends ModelController<BannerDao, Banner>{
	
	/**
	 * 轮播图列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "banner/banner_list";
	}
	
	/**
	 * 轮播图编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Banner banner, Model model){
		return "banner/banner_form";
	}
	
	/**
	 * 轮播图编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Banner banner, Model model){
		return "banner/banner_form";
	}
	
	/**
	 * 保存轮播图
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Banner banner, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "banner/banner_form";
		}
		modelDao.save(banner);
		redirectAttributes.addFlashAttribute("message", "操作成功");
		return "redirect:list";
	}
	
	/**
	 * 删除轮播图
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
//		modelDao.delete(id);
	}
}
