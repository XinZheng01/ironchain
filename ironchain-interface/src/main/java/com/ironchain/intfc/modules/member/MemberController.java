package com.ironchain.intfc.modules.member;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/member")
public class MemberController extends ApiBaseController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/**
	 * 会员注册
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/register")
	public R register(@Valid Member member){
		memberDao.save(member);
		return R.ok(member);
	}
	
	/**
	 * 会员登录
	 * @param name
	 * @param password
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/login")
	public R login(@RequestParam String mobilephone, @RequestParam String password){
		Validate.notBlank(mobilephone, "用户名不能为空");
		Validate.notBlank(password, "密码不能为空");
		
		Member member = memberService.findByMobilephoneAndPassword(mobilephone, password);
//		redisTemplate.opsForValue().set(, value);
		
		return R.ok();
	}
	
	/**
	 * 重置密码 步骤1
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/reset_password_one")
	public R resetPasswordStepOne(){
		return R.ok();
	}
	
	/**
	 * 重置密码 步骤2
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/reset_password_two")
	public R resetPasswordStepTwo(@RequestParam String newPassword){
		return R.ok();
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@IgnoreAuth
//	@IgnoreApiSecurity
	@PostMapping("/modify_password")
	public R modifyPassword(@RequestParam String oldPassword, HttpServletRequest request){
		System.out.println("oldPassword:"+oldPassword);
//		System.out.println("newPassword:"+newPassword);
		return R.ok();
	}
}
