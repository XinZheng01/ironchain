package com.ironchain.admin.modules.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.SpecificationKit;


/**
 * 会员
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/member")
public class MemberController extends ModelController<MemberDao, Member> {
	
	//@Autowired
	//private MemberService memberService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		//用户类型不为子账户
		Specification<Member> spec = SpecificationKit.createBuilder()
			.addConditions(getParamsStartWith(request))
			.neq("type", Member.TYPE_CHILD)
			.bySearchFilter();
		model.addAttribute("page", modelDao.findAll(spec, pageable));
		return "member/member_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Member member, Model model){
		return "member/member_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Member member, Model model){
		return "member/member_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Member member, @RequestParam(required=false) String newPassword, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			setBindingErrorMsg(model, bindingResult);
			return "member/member_form";
		}
		if(member.getId() == null && StringUtils.isBlank(newPassword)){
			setErrorMsg(model, "密码不能为空");
			return "member/member_form";
		}
		if(StringUtils.isNotBlank(newPassword))
			member.setPassword(Member.disgestPassword(newPassword));
		
		modelDao.save(member);
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
		List<Member> entitys = new ArrayList<>();
		Member entity = null;
		for (Long id : ids) {
			entity = new Member();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
	/**
	 * 锁定
	 * @return
	 */
	@PostMapping("/lock")
	@ResponseBody
	public R lock(@RequestParam Long id){
		Member member = modelDao.findOne(id);
		member.setStatus(Member.STATUS_LOCK);
		modelDao.save(member);
		return R.ok().setMsg("操作成功");
	}
	
	/**
	 * 解锁
	 * @return
	 */
	@PostMapping("/un_lock")
	@ResponseBody
	public R unLock(@RequestParam Long id){
		Member member = modelDao.findOne(id);
		member.setStatus(Member.STATUS_SUCCESS);
		modelDao.save(member);
		return R.ok().setMsg("操作成功");
	}
	
	/**
	 * 子账户列表
	 * @return
	 */
	@GetMapping("/child/list")
	public String childList(@RequestParam Long id, Model model){
		List<Member> childs = modelDao.findByParentIdAndType(id, Member.TYPE_CHILD);
		model.addAttribute("childs", childs);
		return "member/member_child";
	}
	
	/**
	 * 新增子账户
	 * @return
	 */
	public R addChild(){
		return R.ok();
	}
	
	
}
