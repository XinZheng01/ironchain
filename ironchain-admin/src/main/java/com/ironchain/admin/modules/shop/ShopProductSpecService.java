package com.ironchain.admin.modules.shop;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.ShopProductSpecDao;
import com.ironchain.common.dao.ShopProductSpecValueDao;
import com.ironchain.common.domain.ShopProductSpec;
import com.ironchain.common.domain.ShopProductSpecValue;

/**
 * 商品规格
 * 
 * @author zheng xin
 * @email 
 */
@Service
public class ShopProductSpecService extends BaseService {

	@Autowired
	private ShopProductSpecDao shopProductSpecDao;
	
	@Autowired
	private ShopProductSpecValueDao shopProductSpecValueDao;
	
	@Transactional
	public void save(ShopProductSpec shopProductSpec, Set<String> value) {
		shopProductSpec = shopProductSpecDao.save(shopProductSpec);
		
		Set<ShopProductSpecValue> specValues = new LinkedHashSet<>();
		ShopProductSpecValue specVal = null;
		for (String valStr : value) {
			specVal = new ShopProductSpecValue();
			specVal.setSpec(shopProductSpec);
			specVal.setValue(valStr);
			specValues.add(specVal);
		}
		
		shopProductSpecValueDao.save(specValues);
	}
	
}
