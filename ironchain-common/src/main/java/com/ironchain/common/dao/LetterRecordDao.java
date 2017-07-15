package com.ironchain.common.dao;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.LetterRecord;

/**
 * 站内信
 * 
 * @author zheng xin
 * @email 
 */
public interface LetterRecordDao extends BaseDao<LetterRecord, Long> {

	LetterRecord findByLetterIdAndUserId(Long id, Long userId);
	
}
