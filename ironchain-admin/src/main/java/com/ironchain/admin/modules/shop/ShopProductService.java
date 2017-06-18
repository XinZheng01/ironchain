package com.ironchain.admin.modules.shop;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.ShopProductDao;
import com.ironchain.common.dao.ShopProductParamDao;
import com.ironchain.common.dao.ShopProductSkuDao;
import com.ironchain.common.domain.ShopProduct;
import com.ironchain.common.domain.ShopProductParam;
import com.ironchain.common.domain.ShopProductSku;

@Service
public class ShopProductService extends BaseService {
	
	@Autowired
	private ShopProductDao shopProductDao;
	
	@Autowired
	private ShopProductParamDao shopProductParamDao;
	
	@Autowired
	private ShopProductSkuDao shopProductSkuDao;
	
	@Transactional
	public void save(ShopProduct shopProduct, List<ShopProductParam> params, List<ShopProductSku> skus) {
		shopProduct = shopProductDao.save(shopProduct);
		
		shopProductParamDao.deleteByProductId(shopProduct.getId());
		for (ShopProductParam param : params) {
			param.setProduct(shopProduct);
			shopProductParamDao.save(param);
		}
		
		shopProductSkuDao.deleteByProductId(shopProduct.getId());
		for (ShopProductSku sku : skus) {
			sku.setProduct(shopProduct);
			shopProductSkuDao.save(sku);
		}
	}

}
