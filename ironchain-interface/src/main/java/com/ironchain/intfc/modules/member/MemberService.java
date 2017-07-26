package com.ironchain.intfc.modules.member;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.dao.MemberLevelDao;
import com.ironchain.common.dao.MemberLevelupDao;
import com.ironchain.common.domain.Constants;
import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.Constants.RegexConstants;
import com.ironchain.common.domain.EquipmentClass;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.MemberLevel;
import com.ironchain.common.domain.MemberLevelup;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.DigestKit;
import com.ironchain.common.kits.EncodeKit;
import com.ironchain.common.kits.IdcardKit;
import com.ironchain.common.kits.SqlKit;

@Service
public class MemberService extends BaseService{

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MemberLevelDao memberLevelDao;
	
	@Autowired
	private MemberLevelupDao memberLevelupDao;
	
	public Member findByMobilephoneAndPassword(String mobilephone, String password) {
		Member member = memberDao.findByMobilephoneAndPassword(mobilephone,
				Member.disgestPassword(password));
		if(member == null){
			throw new ServiceException(R.SC_PARAMERROR, "手机号码或密码错误");
		}
		if(member.getStatus() == Member.STATUS_LOCK)
			throw new ServiceException(R.SC_PARAMERROR, "账号被锁定");
		
//		if(member.getStatus() == Member.STATUS_AUDIT)
//			throw new ServiceException(R.SC_PARAMERROR, "企业用户需要审核，请耐心等待");
			
		return member;
	}
	
	public String getToken(Long userId, String loginName) {
		return EncodeKit.encodeHex2String(
				DigestKit.sha1((loginName + userId + System.currentTimeMillis())
						.getBytes(Charset.forName("UTF-8"))));
	}
	
	/**
	 * 手机号码是否存在
	 * @param mobilephone
	 * @return
	 */
	public boolean mobilephoneExists(String mobilephone) {
		return memberDao.findIdByMobilephone(mobilephone) != null;
	}
	
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 */
	@Transactional
	public void modifyPassword(Long userId, String oldPassword, String newPassword) {
		Member member = memberDao.findOne(userId);
		if(!member.getPassword().equals(Member.disgestPassword(oldPassword)))
			throw new ServiceException(R.SC_PARAMERROR, "密码错误");
		member.setPassword(Member.disgestPassword(newPassword));
		memberDao.save(member);
	}
	
	private static final SecureRandom random = new SecureRandom();
	
	/**
	 * 生成验证码
	 * @return
	 */
	public String createRandomCode() {
		char[] value = new char[6];
		for (int i = 0; i < 6; i++) {
		  value[i] = ((char)(random.nextInt(10) + 48));
		}
		return new String(value);
	}
	
	/**
	 * 检验用户是否登录
	 * @param userId
	 * @param token
	 * @return
	 */
	public boolean isLogin(Long userId, String token){
		if(userId == null || token == null || !token.equals(redisTemplate.opsForValue()
				.get(CacheConstants.LOGIN_TOKEN.getKey(userId.toString()))))
			return false;
		return true;
	}
	
	/**
	 * 创建用户
	 * @param member
	 * @return
	 */
	@Transactional
	public Member create(int type,
			String email,
			String mobilephone,
			String password,
			String idcard,
			Integer serviceType,
			Long levelId,
			String companyName,
			String companyLegal,
			String companyLegalPhone,
			String companyIdcard,
			String companyTel,
			BigDecimal companyPrecision,
			String companyLicenseImg,
			String companyAddress,
			Long[] companyEquipment) {
		
		Member member = new Member();
		//设置基本信息
		member.setName(mobilephone);
		member.setMobilephone(mobilephone);
		member.setEmail(email);
		member.setType(type);
		
		//根据类型设置信息
		if(type == Member.TYPE_PERSON){//个人用户
			Assert.isTrue(idcard != null && IdcardKit.validateCard(idcard), "请输入正确的身份证号码");
			member.setIdcard(idcard);
		}else if(type == Member.TYPE_COMPANY){
			Assert.hasText(companyName, "企业名称不能为空");
			Assert.hasText(companyLegal, "法人姓名不能为空");
			Assert.isTrue(companyTel != null && companyTel.matches(RegexConstants.TEL_REGEX), "企业电话格式不正确");
			Assert.isTrue(companyIdcard != null && IdcardKit.validateCard(companyIdcard), "请输入正确的法人身份证号码");
			Assert.hasText(companyAddress, "企业地址不能为空");
			Assert.hasText(companyLicenseImg, "企业营业执照不能为空");
			Assert.notNull(serviceType, "业务类型不能为空");
			member.setServiceType(serviceType);
			member.setStatus(Member.STATUS_AUDIT);
		}else
			throw new IllegalArgumentException("非法用户类型");
		//密码加密
		member.setPassword(Member.disgestPassword(password));
		member = memberDao.save(member);//入库
		
		//添加升级记录
		if(type == Member.TYPE_COMPANY){
			addLevelup(levelId, member, serviceType, companyName, companyLegal, companyLegalPhone,
					companyIdcard, companyTel, companyPrecision, companyLicenseImg, companyAddress, companyEquipment);
		}
		return member;
	}
	
	/**
	 * 添加会员开通记录
	 * @param levelId
	 * @param userId
	 * @param serviceType
	 * @param companyName
	 * @param companyLegal
	 * @param companyLegalPhone
	 * @param companyIdcard
	 * @param companyTel
	 * @param companyPrecision
	 * @param companyLicenseImg
	 * @param companyAddress
	 * @param companyEquipment
	 */
	@Transactional
	public MemberLevelup addLevelup(Long levelId,
			Member member,
			Integer serviceType,
			String companyName,
			String companyLegal,
			String companyLegalPhone,
			String companyIdcard,
			String companyTel,
			BigDecimal companyPrecision,
			String companyLicenseImg,
			String companyAddress,
			Long[] companyEquipment){
		
		MemberLevel level = memberLevelDao.findOne(levelId);
		Assert.isTrue(level!= null && level.getStatus() == Constants.DISPLAY_SHOW, "非法会员等级");
		
		MemberLevelup levelup = new MemberLevelup();
		levelup.setMember(member);
		levelup.setLevel(level);
		
		levelup.setServiceType(serviceType);
		levelup.setCompanyName(companyName);
		levelup.setCompanyLegal(companyLegal);
		levelup.setCompanyLegalPhone(companyLegalPhone);
		levelup.setCompanyIdcard(companyIdcard);
		levelup.setCompanyTel(companyTel);
		levelup.setCompanyPrecision(companyPrecision);
		levelup.setCompanyLicenseImg(companyLicenseImg);
		levelup.setCompanyAddress(companyAddress);
		if(companyEquipment != null){
			Set<EquipmentClass> equs = new LinkedHashSet<>();
			for (Long equId : companyEquipment) {
				equs.add(new EquipmentClass(equId));
			}
			levelup.setCompanyEquipment(equs);
		}
//		levelup.setStatus(MemberLevelup.STATUS_AUDIT);
		member.setStatus(Member.STATUS_AUDIT);
		memberDao.save(member);
		
		return memberLevelupDao.save(levelup);
	}

	/**
	 * 获取用户收货地址
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findExpressAddressById(Long userId) {
		List<Map<String, Object>> addressList = SqlKit.create()
			.append("select id,consigner,mobile,is_default isDefault,a.provname,a.cityname,a.`name` adname,address")
			.append(" from shop_express_address e, gd_area a where e.adcode = a.adcode and member_id = ? order by is_default desc, id desc", userId)
			.query2Map(jdbcTemplate);
		String mobile = null;
		StringBuilder buff = new StringBuilder();
		for (Map<String, Object> address : addressList) {
			
			buff.setLength(0);
			buff.append(address.remove("provname"));
			if(address.get("cityname") != null)
				buff.append(address.remove("cityname"));
			buff.append(address.remove("adname")).append(address.get("address"));
			address.put("address", buff.toString());
			
			buff.setLength(0);
			mobile = (String) address.get("mobile");
			address.put("mobile", buff.append(mobile.substring(0, 3)).append("****").append(mobile.substring(8)).toString());
		}
		return addressList;
	}

	/**
	 * 查看用户收货地址
	 * @param userId
	 * @param id
	 */
	public Map<String, Object> findExpressAddressInfo(Long userId, Long id) {
		return SqlKit.create()
				.append("select id,consigner,mobile,is_default isDefault,a.provcode,a.provname,a.citycode,a.cityname,e.adcode,a.`name` adname,address")
				.append(" from shop_express_address e, gd_area a where e.adcode = a.adcode and member_id = ? and e.id = ?", userId, id)
				.query2Single(jdbcTemplate);
	}
	
	/**
	 * 查询显示的会员等级
	 * @return
	 */
//	public List<Map<String, Object>> findShowLevel() {
//		return SqlKit.create()
//			.append("select l.id, l.name from member_level l where status = ?",
//					MemberLevel.STATUS_SHOW)
//			.query2Map(jdbcTemplate);
//	}
}
