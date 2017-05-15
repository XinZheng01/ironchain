package com.ironchain.common.dao;

import java.util.List;
import java.util.Map;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.TableEntity;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:32:04
 */
public interface SysGeneratorDao extends BaseDao<TableEntity, Long>{
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
