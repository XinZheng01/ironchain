package com.ironchain.admin.modules.shop.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.ShopBannerDao;

/**
 * 
 * 
 * @author zheng xin
 * @email 
 */
@Service
public class ShopBannerService extends BaseService {
	
	@Autowired
	private ShopBannerDao shopBannerDao;
}
