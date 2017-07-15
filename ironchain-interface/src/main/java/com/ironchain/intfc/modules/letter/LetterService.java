package com.ironchain.intfc.modules.letter;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.dao.LetterDao;
import com.ironchain.common.dao.LetterRecordDao;
import com.ironchain.common.domain.Letter;
import com.ironchain.common.domain.LetterRecord;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.SqlKit;

@Service
public class LetterService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private LetterRecordDao letterRecordDao;
	
	@Autowired
	private LetterDao letterDao;
	/**
	 * 查询用户消息列表
	 * @param status
	 * @param userId
	 * @param pageable
	 */
	public Page<Map<String, Object>> findListByStatusAndUserId(int status, Long userId, Pageable pageable) {
		Page<Map<String, Object>> page = SqlKit.create()
			.append("select l.id,l.title,date_format(l.create_time,'%m-%d') createTime,IFNULL(lr.status,0) `status`")
			.append(" from letter l LEFT JOIN letter_record lr on l.id = lr.letter_id and lr.user_id = ?", userId)
			.append(" where (l.send_type = 1 or lr.user_id = ?)", userId)
			.append(status > -1, " and lr.status = ?", status)//已读未读状态
			.query2Page(jdbcTemplate, pageable);
		return page;
	}

	@Transactional
	public Letter findLetterByIdAndUserId(Long id, Long userId) {
		Letter letter = letterDao.findOne(id);
		if(letter == null)
			throw new ServiceException(R.SC_PARAMERROR, "找不到消息");
		
		LetterRecord record = letterRecordDao.findByLetterIdAndUserId(id, userId);
		
		if(letter.getSendType() == Letter.SEND_TYPE_APPOINT && record == null)
			throw new ServiceException(R.SC_PARAMERROR, "没有权限查看此消息");
			
		if(record == null){
			record = new LetterRecord();
			record.setLetterId(id);
			record.setUserId(userId);
		}
		record.setStatus(LetterRecord.STATUS_READ);
		//修改为已读
		letterRecordDao.save(record);
		
		return letter;
	}

}
