package com.ironchain.intfc.modules.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.domain.ShopCartItem;
import com.ironchain.common.domain.ShopProduct;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.common.kits.SqlKit;

/**
 * 购物车
 * @author Administrator
 *
 */
@Service
public class ShopCartService extends BaseService{
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取购物车
	 * @param userId
	 * @return
	 */
	public List<ShopCartItem> findShopCartItems(Long userId){
		//获取redis中的购物车中的产品
		List<ShopCartItem> items = JsonKit.nonNull().jsonToList(stringRedisTemplate.opsForValue()
				.get(CacheConstants.SHOP_CART.getKey(userId.toString())), ShopCartItem.class);
		return items == null?new ArrayList<>():items;
	}
	
	/**
	 * 添加商品到购物车
	 * @param userId
	 * @param skuId
	 * @param num
	 */
	public void addProduct(Long userId, Long skuId, int num){
		//购物车子项
		List<ShopCartItem> items = findShopCartItems(userId);
		boolean tag = false;
		for (ShopCartItem item : items) {
			if(item.getSkuId().compareTo(skuId) == 0){
				tag = true;
				item.setNum(item.getNum() + num);//累加数量
				break;
			}
		}
		if(!tag){
			ShopCartItem item = findItemBySkuId(skuId);
			item.setNum(num);
			items.add(item);
		}
		//保存到缓存中
		saveItemByCache(userId, items);
	}
	
	
	/**
	 * 从数据库中查询商品项
	 * @param skuId
	 * @return
	 */
	public ShopCartItem findItemBySkuId(Long skuId){
		Map<String, Object> itemDB = SqlKit.create()
			.append("select s.id,s.product_id productId,p.title productTitle,s.price,s.stock,p.img,p.status,s.title skuTitle")
			.append(" from shop_product_sku s, shop_product p where s.product_id = p.id")
			.append(" and s.id = ?", skuId)
			.query2Single(jdbcTemplate);
		if(itemDB == null || itemDB.get("status") == null 
				|| ShopProduct.DISPLAY_SHOW != Integer.parseInt(itemDB.get("status").toString()))
			throw new ServiceException(R.SC_PARAMERROR, "商品已下架或不存在");
		
		ShopCartItem item = new ShopCartItem();
		item.setImg((String) itemDB.get("img"));
		item.setPrice((BigDecimal) itemDB.get("price"));
		item.setProductId(Long.valueOf(itemDB.get("productId").toString()));
		item.setSkuId(skuId);
		item.setProductTitle((String) itemDB.get("productTitle"));
		item.setSkuTitle((String) itemDB.get("skuTitle"));
		
		return item;
	}

	/**
	 * 保存购物车项到缓存
	 * @param userId
	 * @param items
	 */
	public void saveItemByCache(Long userId, List<ShopCartItem> items){
		//保存到缓存中
		stringRedisTemplate.opsForValue().set(
				CacheConstants.SHOP_CART.getKey(userId.toString()),
				JsonKit.nonNull().toJson(items),
				CacheConstants.SHOP_CART.getExpiredTime(), TimeUnit.SECONDS);
	}
	
	/**
	 * 修改购物车项数量
	 * @param userId
	 * @param skuId
	 * @param num
	 */
	public void updateProductNum(Long userId, Long skuId, int num) {
		//购物车子项
		List<ShopCartItem> items = findShopCartItems(userId);
		boolean tag = false;
		for (ShopCartItem item : items) {
			if(item.getSkuId().compareTo(skuId) == 0){
				tag = true;
				item.setNum(num);//修改数量
				break;
			}
		}
		if(!tag)
			throw new ServiceException(R.SC_PARAMERROR, "请刷新购物车页面");
		//保存到缓存中
		saveItemByCache(userId, items);
	}
	
	/**
	 * 删除商品项
	 * @param userId
	 * @param skuId
	 */
	public void deleteProduct(Long userId, Long skuId){
		//购物车子项
		List<ShopCartItem> items = findShopCartItems(userId);
		for (Iterator<ShopCartItem> it = items.iterator(); it.hasNext();) {
			if(it.next().getSkuId().compareTo(skuId) == 0)
				it.remove();
		}
		//保存到缓存中
		saveItemByCache(userId, items);
	}
}
