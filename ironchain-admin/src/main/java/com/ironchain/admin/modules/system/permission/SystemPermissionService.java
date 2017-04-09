package com.ironchain.admin.modules.system.permission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.SystemPermissionDao;
import com.ironchain.common.domain.SystemPermission;

@Service
public class SystemPermissionService extends BaseService {

	@Autowired
	private SystemPermissionDao systemPermissionDao;
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SystemPermission> findTreeSortList(Long parentId) {
		List<SystemPermission> source = systemPermissionDao.findAll();
		List<SystemPermission> result = new ArrayList<>();
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
    public void treeSort(List<SystemPermission> source, List<SystemPermission> result, Long parentId, String prefix) {
        Long pid = null;
        SystemPermission p = null;
    	for (SystemPermission node : source) {
        	pid = node.getParent() == null ? null : node.getParent().getId();
            if (pid == parentId) {
            	if(prefix == null){
            		result.add(node);
            	}else{
            		p = new SystemPermission();
            		p.setId(node.getId());
            		p.setName(prefix + "　" + node.getName());
            		result.add(p);
            	}
            	treeSort(source, result, node.getId(), prefix == null?null : "　　" + prefix);
            }
        }
    }

    /**
     * 
     * @param parentId
     * @return
     */
	public List<SystemPermission> findTreeSelectList(Long parentId) {
		List<SystemPermission> source = systemPermissionDao.findAll();
		List<SystemPermission> result = new ArrayList<>();
		SystemPermission p = new SystemPermission();
		p.setId(null);
		p.setName("顶级菜单");
		result.add(p);
		treeSort(source, result, parentId, "├");
		return result;
	}
	
}
