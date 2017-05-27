package com.ironchain.admin.modules.friendcircle;

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
import com.ironchain.common.dao.FriendcircleDao;
import com.ironchain.common.domain.Friendcircle;
import com.ironchain.common.domain.R;


/**
 * 朋友圈
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/friendcircle")
public class FriendcircleController extends ModelController<FriendcircleDao, Friendcircle> {
	
	//@Autowired
	//private FriendcircleService friendcircleService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "friendcircle/friendcircle_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Friendcircle friendcircle, Model model){
		return "friendcircle/friendcircle_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Friendcircle friendcircle, Model model){
		return "friendcircle/friendcircle_form";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Friendcircle friendcircle, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "friendcircle/friendcircle_form";
		}
		modelDao.save(friendcircle);
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
		List<Friendcircle> entitys = new ArrayList<>();
		Friendcircle entity = null;
		for (Long id : ids) {
			entity = new Friendcircle();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
}
