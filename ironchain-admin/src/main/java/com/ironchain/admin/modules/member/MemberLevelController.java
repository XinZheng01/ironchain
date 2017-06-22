package com.ironchain.admin.modules.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import com.ironchain.common.dao.MemberLevelDao;
import com.ironchain.common.domain.MemberLevel;
import com.ironchain.common.domain.R;


/**
 * 会员等级管理
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/member/level")
public class MemberLevelController extends ModelController<MemberLevelDao, MemberLevel> {
	
	//@Autowired
	//private MemberLevelService memberLevelService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(@PageableDefault(sort="price", direction=Direction.DESC) Pageable pageable,
			HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "member/member_level_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute MemberLevel memberLevel, Model model){
		return "member/member_level_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute MemberLevel memberLevel, Model model){
		return "member/member_level_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute MemberLevel memberLevel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "member/member_level_form";
		}
		modelDao.save(memberLevel);
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
		List<MemberLevel> entitys = new ArrayList<>();
		MemberLevel entity = null;
		for (Long id : ids) {
			entity = new MemberLevel();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
