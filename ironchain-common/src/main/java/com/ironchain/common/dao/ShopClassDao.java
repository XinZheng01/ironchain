package com.ironchain.common.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.ShopClass;

/**
 * 商品分类
 * 
 * @author zheng xin
 * @email 
 */
public interface ShopClassDao extends BaseDao<ShopClass, Long> {

	/**
	 * 获取顶级分类并按照sortid排序
	 * @return
	 */
	List<ShopClass> findByParentIsNullOrderBySortId();
	
	/**
	 * 查询显示的顶级分类并排序
	 * @param status
	 * @param sort
	 * @return
	 */
	List<ShopClass> findByParentIsNullAndStatus(int status, Sort sort);
}
