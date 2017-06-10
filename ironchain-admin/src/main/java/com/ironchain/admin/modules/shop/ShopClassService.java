package com.ironchain.admin.modules.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.ShopClassDao;
import com.ironchain.common.domain.ShopClass;

/**
 * 商品分类
 * 
 * @author zheng xin
 * @email 
 */
@Service
public class ShopClassService extends BaseService {
	
	@Autowired
	private ShopClassDao shopClassDao;
	
	public List<ShopClass> findTreeSortList(Long parentId) {
		List<ShopClass> source = shopClassDao.findAll();
		List<ShopClass> result = new ArrayList<>();
		treeSort(source, result, parentId, null);
		return result;
	}
	
	/**
	 * 递归排序
	 * @param source
	 * @param result
	 * @param parentId
	 * @param prefix
	 */
    public void treeSort(List<ShopClass> source, List<ShopClass> result, Long parentId, String prefix) {
        Long pid = null;
        ShopClass p = null;
    	for (ShopClass node : source) {
        	pid = node.getParent() == null ? null : node.getParent().getId();
            if (pid == parentId) {
            	if(prefix == null){
            		result.add(node);
            	}else{
            		p = new ShopClass();
            		p.setId(node.getId());
            		p.setName(prefix + "　" + node.getName());
            		result.add(p);
            	}
            	treeSort(source, result, node.getId(), prefix == null?null : "　　" + prefix);
            }
        }
    }
}
