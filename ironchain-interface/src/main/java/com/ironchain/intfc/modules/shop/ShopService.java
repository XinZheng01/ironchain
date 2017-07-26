package com.ironchain.intfc.modules.shop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.ShopClassDao;
import com.ironchain.common.domain.Constants;
import com.ironchain.common.domain.ShopClass;
import com.ironchain.common.domain.ShopProduct;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.DateKit;
import com.ironchain.common.kits.SqlKit;

@Service
public class ShopService extends BaseService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ShopClassDao shopClassDao;
	
	/**
	 * 查询商城所有banner
	 * @param pageable
	 * @return
	 */
	public List<Map<String, Object>> findApiBannerList(Pageable pageable) {
		Date now = new Date();
		return SqlKit.create()
				.append("select id, title, img, url, DATE_FORMAT(show_time, '%Y-%m-%d') showTime from shop_banner")
				.append(" where type = 2 and (show_time < ? or show_time is null) and (un_show_time > ? or un_show_time is null) order by sort_id, show_time desc", now, now)
				.query2Page(jdbcTemplate, pageable).getContent();
	}

	/**
	 * 查询商品分类
	 * @return
	 */
	public List<Map<String, Object>> findShopClassList() {
		return SqlKit.create()
			.append("select id, name, parent_id parentId from shop_class where status = ? order by sort_id", Constants.DISPLAY_SHOW)
			.query2Map(jdbcTemplate);
	}

	/**
	 * 获取商城首页商品列表
	 */
	public List<Map<String, Object>> findHomeList() {
		//顶级分类
		List<ShopClass> topClassList = shopClassDao.findByParentIsNullAndStatus(Constants.DISPLAY_SHOW, new Sort("sortId"));
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> record = null;
		for (ShopClass shopClass : topClassList) {
			record = new HashMap<>();
			record.put("id", shopClass.getId());
			record.put("name", shopClass.getName());
			//分类最新的几个产品
			record.put("newList", 
				SqlKit.create()
					.append("select p.id,p.title from shop_product p, shop_class c where p.class_id = c.id")
					.append(" and (p.class_id = ? or c.parent_id = ?) and p.status = ?", shopClass.getId(), shopClass.getId(), Constants.DISPLAY_SHOW)
					.query2Map(jdbcTemplate)
			);
			list.add(record);
		}
		return list;
	}

	/**
	 * 获取商品参数
	 * @param id
	 */
	public List<Map<String, Object>> findProductParams(Long id) {
		return SqlKit.create()
			.append("select name,value from shop_product_param where product_id = ? order by sort_id", id)
			.query2Map(jdbcTemplate);
	}

	/**
	 * 获取商品列表
	 * @param classId 分类id
	 * @param sortName 排序名称
	 * @param sortType 排序类型
	 * @param key 搜索条件
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findProductList(Long classId, int sortName, int sortType, String key, Pageable pageable) {
		return SqlKit.create()
			.append("select p.id,p.title,p.img,p.price,p.sales,p.evaluates from shop_product p, shop_class c")
			.append(" where p.class_id = c.id")
			.append(StringUtils.hasText(key), " and (p.title like ? or p.content like ?)", "%"+key+"%", "%"+key+"%")
			.append(classId != null, " and (p.class_id = ? or c.parent_id = ?)", classId, classId)
			//1综合 2销量 3评分 4价格
			.append(sortName == 1, " order by p.sort_id")
			.append(sortName == 2, " order by p.sales")
			.append(sortName == 3, " order by p.evaluates")
			.append(sortName == 4, " order by p.price")
			//0 ASC 1 DESC
			.append(sortType == 1?" desc, p.update_time desc":" asc, p.update_time desc")
			.query2Page(jdbcTemplate, pageable);
	}

	/**
	 * 查看商品详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> findProductDetails(Long id) {
		Map<String, Object> product = SqlKit.create()
			.append("select id,title,price,img_array imgArray,evaluates,")
			.append("(select 1 from shop_product_param where product_id = p.id limit 1) existParam,")//是否存在产品参数
			.append("(select 1 from shop_product_sku where product_id = p.id and spec_items is not null limit 1) existSpec")//是否存在多种规格
			.append(" from shop_product p")
			.append(" where status = ?", ShopProduct.DISPLAY_SHOW)
			.append(" and id = ?", id)
			.query2Single(jdbcTemplate);
		if(product == null)
			throw new ServiceException("商品不存在或已下架");
		//图片处理 700*700
		String imgArrayStr = (String) product.get("imgArray");
		String[] imgSourceArray = StringUtils.split(imgArrayStr, ",");
		product.put("imgArray", convertImg(imgSourceArray, 1));
		product.put("imgSourceArray", imgSourceArray);
		product.put("existParam", product.get("existParam") == null?0:1);
		product.put("existSpec", product.get("existSpec") == null?0:1);
		
		//获取评价数据
		List<Map<String, Object>> evaluateList = SqlKit.create()
			.append("select e.id,m.name,m.head_img headImg,e.content,e.explain,e.create_time createTime")
			.append(" from shop_product_evaluate e, member m where e.member_id = m.id and e.product_id = ?", id)
			.append(" order by e.create_time desc limit 6")
			.query2Map(jdbcTemplate);
		String headImg = null;
		for (Map<String, Object> evaluate : evaluateList) {
			//用户名、时间、头像处理
//			evaluate.put("name", evaluate.get("name"));
			headImg = (String) evaluate.get("headImg");
			if(StringUtils.hasText(headImg))
				evaluate.put("headImg", convertImg((String) evaluate.get("headImg"), 3));
			evaluate.put("createTime", DateKit.dateFormat((Timestamp)evaluate.get("createTime")));
		}
		product.put("evaluateList", evaluateList);
		return product;
	}

	/**
	 * 根据id查询商品评价
	 * @param id
	 * @return
	 */
	public Page<Map<String,Object>> findProductEvaluate(Long id, Pageable pageable) {
		Page<Map<String,Object>> page = SqlKit.create()
			.append("select e.id,m.name,m.head_img headImg,e.content,e.explain,e.create_time createTime")
			.append(" from shop_product_evaluate e, member m where e.member_id = m.id and e.product_id = ?", id)
			.append(" order by e.create_time desc")
			.query2Page(jdbcTemplate, pageable);
		String headImg = null;
		for (Map<String, Object> evaluate : page) {
			headImg = (String) evaluate.get("headImg");
			if(StringUtils.hasText(headImg))
				evaluate.put("headImg", convertImg(headImg, 3));
		}
		return page;
	}

	/**
	 * 查询商品详情介绍
	 * @param id
	 * @return
	 */
	public String findProductContent(Long id) {
		Map<String, Object> content = SqlKit.create()
			.append("select content from shop_product where id = ?", id)
			.query2Single(jdbcTemplate);
		if(content == null)
			return null;
		return HtmlUtils.htmlUnescape((String)content.get("content"));
	}

	/**
	 * 查询商品规格
	 * @param id
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findProductSpec(Long id) {
		//商品sku
		List<Map<String, Object>> skus = SqlKit.create()
			.append("select id,product_id productId,spec_items specItems,code,price,stock,title")
			.append(" from shop_product_sku")
			.append(" where product_id = ?", id)
			.query2Map(jdbcTemplate);
		if(skus.isEmpty())
			throw new ServiceException("商品已下架");
		
		String specItemsStr = null;
		String[] specItems = null;
		Set<Long> specValues = new LinkedHashSet<>();//商品选中的规格值
		
		for (Map<String, Object> sku : skus) {
			specItemsStr = (String) sku.get("specItems");
			if(StringUtils.hasText(specItemsStr)){
				specItems = StringUtils.delimitedListToStringArray(specItemsStr, ",");
				for (String specItem : specItems) {
					specValues.add(Long.valueOf(StringUtils.delimitedListToStringArray(specItem, ":")[1]));
				}
			}
		}
		//查询规格值
		List<Map<String, Object>> specsDB = SqlKit.create()
			.append("select v.spec_id specId, s.name,v.id specValId, v.value")
			.append(" from shop_product_spec_value v, shop_product_spec s where v.spec_id = s.id")
			.append(" and v.id in(").in(specValues.toArray()).append(")")
			.query2Map(jdbcTemplate);
		
		Map<Object, Object> specs = new LinkedHashMap<>();
		Map<String, Object> spec = null;
		Map<String, Object> specVal = null;
		Object specId = null;
		for (Map<String, Object> specDB : specsDB) {
			specId = specDB.get("specId");
			if((spec = (Map<String, Object>) specs.get(specId)) == null){
				spec = new HashMap<>();
				spec.put("children", new ArrayList<>());
				specs.put(specId, spec);
			}
			spec.put("id", specDB.get("specId"));
			spec.put("name", specDB.get("name"));
			//规格值
			specVal = new HashMap<>();
			specVal.put("id", specDB.get("specValId"));
			specVal.put("value", specDB.get("value"));
			((List<Object>)spec.get("children")).add(specVal);
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("skus", skus);
		result.put("specs", specs.values());
		return result;
	}

}
