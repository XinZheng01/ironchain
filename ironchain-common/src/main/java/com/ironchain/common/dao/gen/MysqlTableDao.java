package com.ironchain.common.dao.gen;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:32:04
 */
@Repository
public class MysqlTableDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询所有表
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findAllTable(Pageable pageable){
		long count = jdbcTemplate.queryForObject("select count(*) from information_schema.`TABLES` where table_schema = (select database())", long.class);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select table_schema tableSchema, table_name tableName, engine, table_comment tableComment, create_time createTime" + 
				" from information_schema.`TABLES`" +
				" where table_schema = (select database()) limit ?, ?",
				pageable.getOffset(), pageable.getPageSize());
		return new PageImpl<>(list, pageable, count);
	}
	
	/**
	 * 根据表名查询表
	 * @param tableName
	 * @return
	 */
	public Map<String, Object> findTableByName(String tableName){
		return jdbcTemplate.queryForMap("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.`TABLES`" +
			" where table_schema = (select database()) and table_name = ?", tableName);
	}
	
	/**
	 * 根据表名查询字段
	 * @param tableName
	 * @return
	 */
	public List<Map<String, Object>> findColumnByTableName(String tableName){
		return jdbcTemplate.queryForList("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.`COLUMNS`" +
				" where table_schema = (select database()) and table_name = ? order by ordinal_position", tableName);
	}
}
