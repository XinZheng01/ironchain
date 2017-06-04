package com.ironchain.intfc.modules.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.cache.CacheService;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.Constants.RegexConstants;
import com.ironchain.common.domain.EquipmentClass;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.IdcardKit;
import com.ironchain.common.sms.SmsService;
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
	
	@Autowired
	private SmsService smsService;
	
	/**
	 * 会员注册
	 * @return
	 */
	@IgnoreAuth
	@PostMapping("/register")
	public R register(@Valid Member member, @RequestParam String verifyCode){
		LOGGER.debug("请求会员注册接口 member：{} verifyCode：{}", member, verifyCode);
		if (verifyCode == null || verifyCode.length() != 6)
			throw new IllegalArgumentException("非法验证码");
		if (!cacheService.check(CacheConstants.VERIFYCODE, member.getMobilephone(), verifyCode))
			throw new IllegalArgumentException("验证码不正确或已过期");
		if (memberService.mobilephoneExists(member.getMobilephone()))
			throw new IllegalArgumentException("该手机号码已存在");
		if (member.getPassword().length() < 6 || member.getPassword().length() > 20)
			throw new IllegalArgumentException("密码不能小于6位，大于20位");
		
		member.setName(member.getMobilephone());
		if(member.getType() == Member.TYPE_PERSON){//个人用户注册完自动登录
			if (member.getIdcard() == null || !IdcardKit.validateCard(member.getIdcard()))
				throw new IllegalArgumentException("请输入正确的身份证号码");
		}else if(member.getType() == Member.TYPE_COMPANY){//企业用户等待后台审核
			Validate.notBlank(member.getCompanyName(), "企业名称不能为空");
			Validate.notBlank(member.getCompanyLegal(), "法人姓名不能为空");
			Validate.matchesPattern(member.getCompanyTel(), RegexConstants.TEL_REGEX, "企业电话格式不正确");
			if (member.getCompanyIdcard() == null || !IdcardKit.validateCard(member.getCompanyIdcard()))
				throw new IllegalArgumentException("请输入正确的法人身份证号码");
			Validate.notBlank(member.getCompanyAddress(), "企业地址不能为空");
			Validate.notBlank(member.getCompanyLicenseImg(), "企业营业执照不能为空");
		}else
			throw new IllegalArgumentException("非法用户类型");
		
		member = memberDao.save(member);
		Long uid = member.getId();
		String mobile = member.getMobilephone();
		String token = memberService.getToken(uid, mobile);
		cacheService.set(CacheConstants.LOGIN_TOKEN, uid.toString(), token);
		cacheService.set(CacheConstants.LOGIN_NAME, uid.toString(), mobile);
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("name", member.getName());
		map.put("token", token);
		
		return R.ok(map);
		
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
		LOGGER.debug("请求会员登录接口 mobilephone：{} password：{}", mobilephone, password);
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
		LOGGER.debug("请求发送验证码接口 mobilephone：{} type：{}", mobilephone, type);
		//检验手机号码
		Validate.matchesPattern(mobilephone, RegexConstants.MOBILE_REGEX, "手机号码格式不正确");
		
		String code = memberService.createRandomCode();
		Map<String, Object> param = new HashMap<>();
		param.put("code", code);
		switch (type) {
		case 1://注册
			if(memberService.mobilephoneExists(mobilephone))
				throw new IllegalArgumentException("手机号码已存在");
			
			smsService.send("sms.register-verify-code", new Object[]{code}, mobilephone);
			break;
		case 2://忘记密码
			if(!memberService.mobilephoneExists(mobilephone))
				throw new IllegalArgumentException("手机号码不存在");
			
			smsService.send("sms.reset-verify-code", new Object[]{code}, mobilephone);
			break;
		default:
			throw new IllegalArgumentException("非法type类型");
		}
		//保存到缓存
		cacheService.set(CacheConstants.VERIFYCODE, mobilephone, code);
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
		LOGGER.debug("请求重置密码 步骤2接口 newPassword：{}", newPassword);
		return R.ok();
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@PostMapping("/modify_password")
	public R modifyPassword(@RequestParam Long userId, @RequestParam String oldPassword, @RequestParam String newPassword){
		LOGGER.debug("请求修改密码接口 userId：{} oldPassword：{} newPassword：{}", userId, oldPassword, newPassword);
		memberService.modifyPassword(userId, oldPassword, newPassword);
		return R.ok();
	}
	
	/**
	 * 查看企业信息
	 */
	@GetMapping("/company_info")
	public R companyInfo(@RequestParam Long id, Long userId){
		Member member = memberDao.findOne(id);
		if(member == null || member.getType() != Member.TYPE_COMPANY)
			throw new ServiceException(R.SC_PARAMERROR, "用户不存在或用户类型非法， id:" + id + ", userId:" + userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("id", member.getId());
		result.put("name", member.getCompanyName());
		result.put("legal", member.getCompanyLegal());
		result.put("legalPhone", member.getCompanyLegalPhone());
		result.put("idcard", member.getCompanyIdcard());
		//TODO 企业评级 交易数量 
		List<Map<String, Object>> companyEqus = new ArrayList<>();
		for (EquipmentClass equClass : member.getCompanyEquipment()) {
			companyEqus.add(equClass.toMap());
		}
		result.put("equipment", companyEqus);
		result.put("precision", member.getCompanyPrecision());
		result.put("address", member.getCompanyAddress());
		result.put("tel", member.getCompanyTel());
		result.put("licenseImg", member.getCompanyLicenseImg());
		return R.ok(result);
	}
}
