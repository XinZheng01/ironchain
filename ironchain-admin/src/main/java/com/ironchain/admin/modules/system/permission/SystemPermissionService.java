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
	
	public List<SystemPermission> findTreeSortList(Long parentId) {
		List<SystemPermission> source = systemPermissionDao.findAll();
		List<SystemPermission> result = new ArrayList<>();
		treeSort(source, result, parentId);
		return result;
	}
	
	/**
	 * 递归排序
	 * @param source
	 * @param result
	 * @param parentId
	 */
    public void treeSort(List<SystemPermission> source, List<SystemPermission> result, Long parentId) {
        Long pid = null;
    	for (SystemPermission node : source) {
        	pid = node.getParent() == null ? null : node.getParent().getId();
            if (pid == parentId) {
            	result.add(node);
            	treeSort(source, result, node.getId());
            }
        }
    }


}
