package com.ironchain.intfc.modules.letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/letter")
public class LetterController extends ApiBaseController{
	
	@Autowired
	private LetterService letterService;
	
	/**
	 * 查询用户消息
	 * @param status
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/list")
	public R list(@RequestParam(required=false, defaultValue="-1") int status, @RequestParam Long userId, Pageable pageable){
		return R.ok(page2Map(letterService.findListByStatusAndUserId(status, userId, pageable)));
	}
	
	/**
	 * 消息详情
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping("/details")
	public R details(@RequestParam Long id, @RequestParam Long userId){
		return R.ok(letterService.findLetterByIdAndUserId(id, userId));
	}
	
}
