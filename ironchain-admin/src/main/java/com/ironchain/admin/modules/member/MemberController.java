package com.ironchain.admin.modules.member;

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
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;

/**
 * 会员管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController extends ModelController<MemberDao, Member>{
	
	/**
	 * 会员列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("memberPage", modelDao.findAll(bySearchFilter(request), pageable));
		return "member/member_list";
	}
	
	/**
	 * 会员编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Member member, Model model){
		return "member/member_form";
	}
	
	/**
	 * 会员编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Member member, Model model){
		return "member/member_form";
	}
	
	/**
	 * 保存会员
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "member/member_form";
		}
		modelDao.save(member);
		redirectAttributes.addFlashAttribute(R.ok().setMsg("操作成功"));
		return "redirect:list";
	}
	
	/**
	 * 删除会员
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
//		modelDao.delete(id);
	}
}
