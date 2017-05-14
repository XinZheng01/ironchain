package com.ironchain.intfc.modules.member;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.cache.CacheService;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.Constants.RegexConstants;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

@RestController
@RequestMapping("/api/member")
public class MemberController extends ApiBaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CacheService cacheService;
	
	/**
	 * 会员注册
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/register")
	public R register(@Valid Member member, @RequestParam String verifyCode){
		if(verifyCode == null || verifyCode.length() != 6)
			throw new ServiceException(R.SC_PARAMERROR, "非法验证码");
		if(!cacheService.check(CacheConstants.VERIFYCODE, member.getMobilephone(), verifyCode))
			throw new ServiceException(R.SC_PARAMERROR, "验证码不正确或已过期");
		if(memberService.mobilephoneExists(member.getMobilephone()))
			throw new ServiceException(R.SC_PARAMERROR, "该手机号码已存在");
		
		if(member.getType() == Member.TYPE_PERSON){//个人用户注册完自动登录
			member = memberDao.save(member);
			Long uid = member.getId();
			String mobile = member.getMobilephone();
			String token = memberService.getToken(uid, mobile);
			cacheService.set(CacheConstants.LOGIN_TOKEN, uid.toString(), token);
			cacheService.set(CacheConstants.LOGIN_NAME, uid.toString(), mobile);
			
			Map<String, Object> map = new HashMap<>();
			map.put("userId", uid);
			map.put("mobilephone", mobile);
			map.put("name", member.getName());
			map.put("token", token);
			
			return R.ok(map);
		}else if(member.getType() == Member.TYPE_COMPANY){//企业用户等待后台审核
			Validate.notBlank(member.getCompanyName(), "企业名称不能为空");
			Validate.notBlank(member.getCompanyLegal(), "法人姓名不能为空");
			Validate.matchesPattern(member.getCompanyTel(), RegexConstants.TEL_REGEX, "企业电话格式不正确");
			Validate.notBlank(member.getCompanyIdcard(), "企业法人身份证不能为空");
			Validate.notBlank(member.getCompanyAddress(), "企业地址不能为空");
			Validate.notBlank(member.getCompanyLicense(), "企业营业执照不能为空");
			
			member = memberDao.save(member);
			return R.ok();
		}else
			throw new ServiceException(R.SC_PARAMERROR, "非法用户类型");
		
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
		Long uid = member.getId();
		String mobile = member.getMobilephone();
		String token = memberService.getToken(uid, mobile);
		cacheService.set(CacheConstants.LOGIN_TOKEN, uid.toString(), token);
		cacheService.set(CacheConstants.LOGIN_NAME, uid.toString(), mobile);
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("mobilephone", mobile);
		map.put("name", member.getName());
		map.put("token", token);
		
		return R.ok(map);
	}
	
	/**
	 * 发送验证码
	 * @param mobilephone
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/send_verify_code")
	public R sendVerifyCode(@RequestParam String mobilephone, @RequestParam int type){
		//检验手机号码
		Validate.matchesPattern(mobilephone, RegexConstants.MOBILE_REGEX, "手机号码格式不正确");
		if(type == 1 && memberService.mobilephoneExists(mobilephone))//注册
			throw new ServiceException(R.SC_PARAMERROR, "手机号码已存在");
		else if(type == 2 && !memberService.mobilephoneExists(mobilephone))
			throw new ServiceException(R.SC_PARAMERROR, "手机号码不存在");
		else if(type < 1 || type > 2)
			throw new ServiceException(R.SC_PARAMERROR, "非法类型");
		
		String code = getRandomCode();
		//send code
		
		LOGGER.info("手机号码：{}， 验证码为：{}", mobilephone, code);
		//set cache
		cacheService.set(CacheConstants.VERIFYCODE, mobilephone, code);
		return R.ok();
	}
	
	public String getRandomCode(){
		return "";
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
	@PostMapping("/modify_password")
	public R modifyPassword(@RequestParam Long userId, @RequestParam String oldPassword, @RequestParam String newPassword){
		memberService.modifyPassword(userId, oldPassword, newPassword);
		return R.ok();
	}
}
