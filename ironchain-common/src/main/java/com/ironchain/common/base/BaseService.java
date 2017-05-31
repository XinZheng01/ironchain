package com.ironchain.common.base;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public abstract class BaseService{
	
//	public Page<Map<String, Object>> queryPage(String sql, Pageable pageable, Object...params){
//		
//		
//	}
}
