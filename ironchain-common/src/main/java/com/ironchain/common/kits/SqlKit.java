package com.ironchain.common.kits;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.persistence.dialect.Dialect;
import com.ironchain.common.persistence.dialect.MySQLDialect;

public class SqlKit {
	
	/** SQL语句*/
	private final StringBuilder sql = new StringBuilder();
	
	/** 参数列表*/
	private final List<Object> paramsList = new ArrayList<>();
	
	/** 方言*/
	private Dialect dialect;
	
	private static Dialect defaultDialect = new MySQLDialect();
	
	private SqlKit(){
		this.dialect = defaultDialect;
	}
	
	public static SqlKit create(){
		return new SqlKit();
	}
	
	/**
	 * 添加SQL语句
	 * @param condition 语句
	 * @param params 参数
	 * @return
	 */
	public SqlKit append(String condition, Object... params){
		sql.append(" ").append(condition);
		if(params != null && params.length > 0)
			Collections.addAll(paramsList, params);
		return this;
	}
	
	/**
	 * 添加SQL语句
	 * @param append 是否添加
	 * @param condition 语句
	 * @param params 参数
	 * @return
	 */
	public SqlKit append(boolean append, String condition, Object... params){
		if(append)
			return append(condition, params);
		return this;
	}
	
	/**
	 * 
	 * @param dao
	 * @param pageable
	 * @return
	 */
	public <T, ID extends Serializable> Page<T> query2Page(BaseDao<T, ID> dao, Pageable pageable){
		return dao.queryPageBySql(this.sql.toString(), pageable, this.paramsList);
	}
	
	/**
	 * 
	 * @param dao
	 * @return
	 */
	public <T, ID extends Serializable> T query2Single(BaseDao<T, ID> dao){
		return dao.querySingleBySql(this.sql.toString(), this.paramsList);
	}
	
	/**
	 * 
	 * @param dao
	 * @return
	 */
	public <T, ID extends Serializable> List<T> query2List(BaseDao<T, ID> dao){
		return dao.queryListBySql(this.sql.toString(), this.paramsList);
	}
	
	/**
	 * 
	 * @param dao
	 * @return
	 */
	public <T, ID extends Serializable> List<Map<String,Object>> query2Map(BaseDao<T, ID> dao){
		return dao.queryMapBySql(this.sql.toString(), this.paramsList);
	}
	
	/**
	 * 
	 * @param dao
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> query2PageMap(JdbcTemplate jdbcTemplate, Pageable pageable){
		String nativeSql = sql.toString();
		String limitSql = pageable == null? nativeSql : 
			dialect.getLimitString(nativeSql, pageable.getOffset(), pageable.getPageSize());
		
		return PageableExecutionUtils.getPage(jdbcTemplate.queryForList(limitSql, paramsList.toArray()), pageable,
				()->{return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM (" + nativeSql + ") AS _A", long.class, paramsList.toArray());});
	}
	
	public String getSql() {
		return sql.toString();
	}
	
	public List<Object> getParamsList() {
		return paramsList;
	}
}
