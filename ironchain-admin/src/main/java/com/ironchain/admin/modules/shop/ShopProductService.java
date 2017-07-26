package com.ironchain.admin.modules.shop;

import java.util.ArrayList;
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
		
//		shopProductSkuDao.deleteByProductId(shopProduct.getId());
		//如果没有sku则初始化一条记录
		if(skus.size() == 0){
			ShopProductSku sku = shopProductSkuDao.findBySpecItemsIsNullAndProductId(shopProduct.getId());
			if(sku == null)
				sku = new ShopProductSku();
			
			sku.setPrice(shopProduct.getPrice());
			sku.setStock(shopProduct.getStock());
			sku.setCode(shopProduct.getCode());
			sku.setProduct(shopProduct);
			shopProductSkuDao.save(sku);
		}else{
			List<Long> skuIds = new ArrayList<>();
			for (ShopProductSku sku : skus) {
				sku.setProduct(shopProduct);
				if(sku.getId() != null)
					skuIds.add(sku.getId());
			}
			shopProductSkuDao.deleteByProductIdAndIdNotIn(shopProduct.getId(), skuIds);
			shopProductSkuDao.save(skus);
		}
	}

}
