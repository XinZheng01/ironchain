package com.ironchain.intfc.modules.member;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.DigestKit;
import com.ironchain.common.kits.EncodeKit;

@Service
public class MemberService extends BaseService{

	@Autowired
	private MemberDao memberDao;
	
	public Member findByMobilephoneAndPassword(String mobilephone, String password) {
		Member member = memberDao.findByMobilephoneAndPassword(mobilephone,
				disgestPassword(password));
		if(member == null){
			throw new ServiceException(R.SC_PARAMERROR, "手机号码或密码错误");
		}
		return member;
	}
	
	/**
	 * sha-256 1024加密
	 * @param password
	 * @return
	 */
	public static String disgestPassword(String password){
		byte[] passwordB = password.getBytes(Charset.forName("UTF-8"));
		byte[] salt = new byte[8];
		for (int i = 0, j = 0, len = passwordB.length; i < 8; i++, j++) {
			if(j >= len) j = 0;
			salt[i] = passwordB[j];
		}
		return EncodeKit.encodeHex2String(DigestKit.sha256(
				passwordB, salt, 1024));
	}
	
	public String getToken(Long userId, String loginName) {
		return EncodeKit.encodeHex2String(
				DigestKit.sha1((loginName + userId + System.currentTimeMillis())
						.getBytes(Charset.forName("UTF-8"))));
	}
}
