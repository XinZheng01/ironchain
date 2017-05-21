package com.ironchain.admin.modules.information;

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
import com.ironchain.common.dao.InformationDao;
import com.ironchain.common.domain.Information;
import com.ironchain.common.domain.R;

/**
 * 资讯管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/information")
public class InformationController extends ModelController<InformationDao, Information> {
	
	/**
	 * 资讯列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "information/information_list";
	}
	
	/**
	 * 资讯编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Information information, Model model){
		return "information/information_form";
	}
	
	/**
	 * 资讯编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Information information, Model model){
		return "information/information_form";
	}
	
	/**
	 * 保存资讯
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Information information, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "information/information_form";
		}
		modelDao.save(information);
		redirectAttributes.addFlashAttribute(R.ok().setMsg("操作成功"));
		return "redirect:list";
	}
	
	/**
	 * 删除资讯
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
//		modelDao.delete(id);
	}
}
