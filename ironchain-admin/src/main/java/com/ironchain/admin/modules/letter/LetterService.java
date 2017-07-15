package com.ironchain.admin.modules.letter;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.admin.jpush.JpushService;
import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.LetterDao;
import com.ironchain.common.dao.LetterRecordDao;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Letter;
import com.ironchain.common.domain.LetterRecord;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;

/**
 * 站内信日志
 * 
 * @author zheng xin
 * @email 
 */
@Service
public class LetterService extends BaseService {

	@Autowired
	private LetterDao letterDao;
	
	@Autowired
	private JpushService jpushService;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private LetterRecordDao letterRecordDao;
	
	/**
	 * 保存并推送
	 * @param letter
	 */
	@Transactional
	public void saveAndSend(Letter letter) {
		//获取指定用户id
		Long[] ids = null;
		if(letter.getSendType() == Letter.SEND_TYPE_APPOINT){
			if(letter.getMembers() == null)
				throw new ServiceException(R.SC_PARAMERROR, "指定发送的用户不能为空");
			
			String[] phones = letter.getMembers().split("\\s");
			ids = memberDao.findIdByMobilephones(phones);
			letter.setNumber(ids.length);
		}else{
			letter.setNumber(memberDao.count());
		}
		
		//推送消息
		jpushService.push(letter, ids);
		
		//保存到数据库
		letter = letterDao.save(letter);
		if(ids != null){
			LetterRecord letterRecord = null;
			for (Long userId : ids) {
				letterRecord = new LetterRecord();
				letterRecord.setUserId(userId);
				letterRecord.setLetterId(letter.getId());
				letterRecordDao.save(letterRecord);
			}
		}
	}
	
}
