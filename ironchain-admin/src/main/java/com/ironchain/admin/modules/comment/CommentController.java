package com.ironchain.admin.modules.comment;

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
import com.ironchain.common.dao.CommentDao;
import com.ironchain.common.domain.Comment;

/**
 * 评论管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends ModelController<CommentDao, Comment>{
	/**
	 * 评论列表
	 * @return
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "comment/comment_list";
	}
	
	/**
	 * 评论编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute Comment comment, Model model){
		return "comment/comment_form";
	}
	
	/**
	 * 评论编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute Comment comment, Model model){
		return "comment/comment_form";
	}
	
	/**
	 * 保存评论
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Comment comment, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "comment/comment_form";
		}
		modelDao.save(comment);
		redirectAttributes.addFlashAttribute("message", "操作成功");
		return "redirect:list";
	}
	
	/**
	 * 删除评论
	 * @return
	 */
	@PostMapping("/delete")
	public void delete(@RequestParam Long id){
//		modelDao.delete(id);
	}
}
