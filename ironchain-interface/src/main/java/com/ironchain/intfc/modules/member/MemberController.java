package com.ironchain.intfc.modules.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ironchain.common.cache.CacheService;
import com.ironchain.common.dao.DemandOfferDao;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.dao.MemberLevelDao;
import com.ironchain.common.domain.Constants;
import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.Constants.RegexConstants;
import com.ironchain.common.domain.EquipmentClass;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.IdcardKit;
import com.ironchain.common.sms.SmsService;
import com.ironchain.common.upload.UploadService;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
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
	private MemberLevelDao memberLevelDao;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private DemandOfferDao demandOfferDao;
	
	/**
	 * 会员注册
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/register")
	public R register(@RequestParam int type,
			@RequestParam String email,
			@RequestParam String mobilephone,
			@RequestParam String password,
			@RequestParam String idcard,
			@RequestParam(required=false) Integer serviceType,
			@RequestParam(required=false) Long levelId,
			@RequestParam(required=false) String companyName,
			@RequestParam(required=false) String companyLegal,
			@RequestParam(required=false) String companyLegalPhone,
			@RequestParam(required=false) String companyIdcard,
			@RequestParam(required=false) String companyTel,
			@RequestParam(required=false) BigDecimal companyPrecision,
			@RequestParam(required=false) String companyLicenseImg,
			@RequestParam(required=false) String companyAddress,
			@RequestParam(required=false) Long[] companyEquipment,
			@RequestParam String verifyCode){
		
		Assert.isTrue(verifyCode != null && verifyCode.length() == 6, "非法验证码");
//		Assert.isTrue(cacheService.check(CacheConstants.VERIFYCODE, mobilephone, verifyCode), "验证码不正确或已过期");
		Assert.isTrue(mobilephone.matches(RegexConstants.MOBILE_REGEX), "手机号码格式不正确");
		Assert.isTrue(!memberService.mobilephoneExists(mobilephone), "该手机号码已存在");
		Assert.isTrue(password.length() >= 6 && password.length() <= 20, "密码不能小于6位，大于20位");
		
		Member member = memberService.create(type, email, mobilephone, password, idcard, serviceType, levelId,
				companyName, companyLegal, companyLegalPhone, companyIdcard, companyTel, companyPrecision, companyLicenseImg,
				companyAddress, companyEquipment);
				
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
	@RequestMapping("/login")
	public R login(@RequestParam String mobilephone, @RequestParam String password){
		LOGGER.debug("请求会员登录接口 mobilephone：{} password：{}", mobilephone, password);
		Assert.hasText(mobilephone, "用户名不能为空");
		Assert.hasText(password, "密码不能为空");
		
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
	@RequestMapping("/send_verify_code")
	public R sendVerifyCode(@RequestParam String mobilephone, @RequestParam int type){
		LOGGER.debug("请求发送验证码接口 mobilephone：{} type：{}", mobilephone, type);
		//检验手机号码
		Assert.isTrue(mobilephone.matches(RegexConstants.MOBILE_REGEX), "手机号码格式不正确");
		
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
	@RequestMapping("/reset_password_one")
	public R resetPasswordStepOne(){
		return R.ok();
	}
	
	/**
	 * 重置密码 步骤2
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/reset_password_two")
	public R resetPasswordStepTwo(@RequestParam String newPassword){
		LOGGER.debug("请求重置密码 步骤2接口 newPassword：{}", newPassword);
		return R.ok();
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping("/modify_password")
	public R modifyPassword(@RequestParam Long userId, @RequestParam String oldPassword, @RequestParam String newPassword){
		LOGGER.debug("请求修改密码接口 userId：{} oldPassword：{} newPassword：{}", userId, oldPassword, newPassword);
		memberService.modifyPassword(userId, oldPassword, newPassword);
		return R.ok();
	}
	
	/**
	 * 查看企业信息
	 */
	@RequestMapping("/company_info")
	public R companyInfo(@RequestParam Long id, Long userId){
		Member member = memberDao.findOne(id);
		Assert.isTrue(member != null && member.getType() == Member.TYPE_COMPANY, "用户不存在或用户类型非法， id:" + id + ", userId:" + userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("id", member.getId());
		result.put("companyName", member.getCompanyName());//公司名称
		result.put("companyLegal", member.getCompanyLegal());//法人
		result.put("companyLegalPhone", member.getCompanyLegalPhone());
		result.put("companyIdcard", member.getCompanyIdcard());
		result.put("levelName", member.getLevel().getName());//会员等级名称
		result.put("bidCount", demandOfferDao.countByOfferId(id));//竞标数量
		
		List<Map<String, Object>> companyEqus = new ArrayList<>();
		for (EquipmentClass equClass : member.getCompanyEquipment()) {
			companyEqus.add(equClass.toMap());
		}
		result.put("companyEquipment", companyEqus);
		result.put("companyPrecision", member.getCompanyPrecision());
		result.put("companyAdress", member.getCompanyAddress());
		result.put("companyTel", member.getCompanyTel());
		result.put("companyLicenseImg", member.getCompanyLicenseImg());
		return R.ok(result);
	}
	
	/**
	 * 个人账户信息
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestParam Long userId){
		Member member = memberDao.findOne(userId);

		Map<String, Object> result = new HashMap<>();
		result.put("name", member.getName());
		result.put("headImg", member.getHeadImg());
		result.put("status", member.getLevel() != null?"已开通":"未开通");
		result.put("type", member.getTypeStr());
		return R.ok(result);
	}
	
	/**
	 * 修改头像
	 * @return
	 */
	@IgnoreApiSecurity
	@RequestMapping("/modify_head_img")
	public R modifyHeadImg(@RequestParam Long userId, @RequestParam MultipartFile headImg){
		String fileName = headImg.getOriginalFilename();
		int idx = fileName.lastIndexOf(".");
		if(idx == -1 || !UploadService.imgExt.contains(fileName.substring(idx + 1).toLowerCase())){
			return R.error("上传文件扩展名是不允许的扩展名。\n只允许" + StringUtils.join(UploadService.imgExt, ",")+ "格式。");
		}
		String uploadPath = uploadService.store(headImg)[0];
		Member member = memberDao.findOne(userId);
		member.setHeadImg(uploadPath);
		memberDao.save(member);
		return R.ok();
	}
	
	/**
	 * 修改用户名
	 * @param userId
	 * @param name
	 * @return
	 */
	@RequestMapping("/modify_name")
	public R modifyName(@RequestParam Long userId, @RequestParam String name){
		Member member = memberDao.findOne(userId);
		member.setName(name);
		memberDao.save(member);
		return R.ok();
	}
	
	/**
	 * 会员等级列表
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/level/list")
	public R levelList(){
		return R.ok(memberLevelDao.findByStatusOrderByPriceAsc(Constants.DISPLAY_SHOW));
	}
	
	/**
	 * 升级会员
	 * @return
	 */
	@RequestMapping("/levelup")
	public R levelup(@RequestParam Long userId, @RequestParam Long id,
			@RequestParam Integer serviceType,
			@RequestParam String companyName,
			@RequestParam String companyLegal,
			@RequestParam String companyLegalPhone,
			@RequestParam String companyIdcard,
			@RequestParam String companyTel,
			@RequestParam BigDecimal companyPrecision,
			@RequestParam String companyLicenseImg,
			@RequestParam String companyAddress,
			@RequestParam Long[] companyEquipment
			){
		Assert.hasText(companyName, "企业名称不能为空");
		Assert.hasText(companyLegal, "法人姓名不能为空");
		Assert.isTrue(companyTel != null && companyTel.matches(RegexConstants.TEL_REGEX), "企业电话格式不正确");
		Assert.isTrue(companyIdcard != null && IdcardKit.validateCard(companyIdcard), "请输入正确的法人身份证号码");
		Assert.hasText(companyAddress, "企业地址不能为空");
		Assert.hasText(companyLicenseImg, "企业营业执照不能为空");
		
		memberService.addLevelup(id, memberDao.findOne(userId), serviceType, companyName, companyLegal, companyLegalPhone,
				companyIdcard, companyTel, companyPrecision, companyLicenseImg, companyAddress, companyEquipment);
		
		return R.ok();
	}
}
