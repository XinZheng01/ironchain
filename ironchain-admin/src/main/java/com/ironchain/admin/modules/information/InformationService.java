package com.ironchain.admin.modules.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.InformationDao;

/**
 * 资讯
 * 
 * @author zheng xin
 * @email 
 */
@Service
public class InformationService extends BaseService {
	
	@Autowired
	private InformationDao informationDao;
}
