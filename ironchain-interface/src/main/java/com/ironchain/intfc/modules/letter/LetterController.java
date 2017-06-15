package com.ironchain.intfc.modules.letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.R;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/letter")
public class LetterController extends ApiBaseController{
	
	@Autowired
	private LetterService letterService;
	
	@GetMapping("/list")
	public R list(Pageable pageable){
//		letterService.findApiList()
		return R.ok();
	}
}
