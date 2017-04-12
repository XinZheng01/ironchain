package com.ironchain.common.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ironchain.common.persistence.dialect.Dialect;

@SuppressWarnings({"rawtypes","unchecked"})
@Component
@Scope("prototype")
public class SqlHelper{
	
	/** SQL语句*/
	private final StringBuilder sql = new StringBuilder();
	
	/** 参数列表*/
	private final List<Object> paramsList = new ArrayList<>();
	
	/** 实体管理器*/
	private EntityManager em;
	
	/** 返回类型*/
	private String resultSetMapping;
	
	/** 返回类型*/
	private Class resultClass;
	
	/** 方言*/
	private Dialect dialect;
	
	public SqlHelper(){}
	
	public SqlHelper(EntityManager em){
		this.em = em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	@Autowired
	@Qualifier("pageDialect")
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
	
	/**
	 * 设置返回类型
	 * @param resultSetMapping
	 * @return
	 */
	public SqlHelper from(String resultSetMapping){
		this.resultSetMapping = resultSetMapping;
		return this;
	}
	
	/**
	 * 设置返回类型
	 * @param resultSetMapping
	 * @See SqlResultSetMapping
	 * @return
	 */
	public SqlHelper from(Class resultClass){
		this.resultClass = resultClass;
		return this;
	}
	
	/**
	 * 添加SQL语句
	 * @param condition 语句
	 * @param params 参数
	 * @return
	 */
	public SqlHelper appendSql(String condition, Object... params){
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
	public SqlHelper appendSql(boolean append, String condition, Object... params){
		if(append)
			return appendSql(condition, params);
		return this;
	}
	
	/**
	 * 查询分页对象
	 * @param pageable
	 * @return
	 */
	public <M>Page<M> query2Page(Pageable pageable){
		return queryPageBySql(this.sql.toString(), pageable, this.resultSetMapping, this.resultClass, this.paramsList.toArray());
	}
	
	/**
	 * 查询单个对象
	 * @return
	 */
	public <M>M query2Single(){
		return querySingleBySql(this.sql.toString(), this.resultSetMapping, this.resultClass, this.paramsList.toArray());
	}
	
	/**
	 * 查询单个对象
	 * @return
	 */
	public <M>List<M> query2List(){
		return queryListBySql(this.sql.toString(), this.resultSetMapping, this.resultClass, this.paramsList.toArray());
	}
	
	/**
	 * 查询map
	 * @return
	 */
	public List<Map<String,Object>> query2Map(){
		return queryMapBySql(this.sql.toString(), this.paramsList.toArray());
	}
	
	/**
	 * 使用SQL查询分页泛型对象
	 * @param nativeSql
	 * @param pageable
	 * @param resultSetMapping
	 * @param resultClass
	 * @param params
	 * @return
	 */
	private <M>Page<M> queryPageBySql(String nativeSql, Pageable pageable, String resultSetMapping, Class resultClass, Object... params) {
		String limitSql = pageable == null? nativeSql : 
			dialect.getLimitString(nativeSql, pageable.getOffset(), pageable.getPageSize());
		Query query = createQuery(limitSql, resultSetMapping, resultClass);
		for (int i = 1, len = params.length; i <= len; i++) {
			query.setParameter(i, params[i - 1]);
		}
		return pageable == null ? new PageImpl<M>(query.getResultList())
				: new PageImpl<M>(query.getResultList(), pageable, getTotal(nativeSql, params));
	}
	
	/**
	 * 使用SQL查询单个泛型对象
	 * @param nativeSql
	 * @param resultSetMapping
	 * @param resultClass
	 * @param params
	 * @return
	 */
	private <M>M querySingleBySql(String nativeSql, String resultSetMapping, Class resultClass, Object... params) {
		Query query = createQuery(nativeSql, resultSetMapping, resultClass);
		for (int i = 1, len = params.length; i <= len; i++) {
			query.setParameter(i, params[i - 1]);
		}
		List<M> results = query.getResultList();
		if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException("result returns more than one elements");
	}
	
	/**
	 * 使用SQL查询泛型列表
	 * @param nativeSql
	 * @param resultSetMapping
	 * @param resultClass
	 * @param params
	 * @return
	 */
	private <M>List queryListBySql(String nativeSql, String resultSetMapping, Class resultClass, Object... params) {
		Query query = createQuery(nativeSql, resultSetMapping, resultClass);
		for (int i = 1, len = params.length; i <= len; i++) {
			query.setParameter(i, params[i - 1]);
		}
		return query.getResultList();
	}
	
	/**
	 * 使用SQL查询map列表
	 * @param nativeSql
	 * @param resultSetMapping
	 * @param resultClass
	 * @param params
	 * @return
	 */
	private List<Map<String, Object>> queryMapBySql(String nativeSql, Object... params) {
		Query query = createQuery(nativeSql, null, null);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (int i = 1, len = params.length; i <= len; i++) {
			query.setParameter(i, params[i - 1]);
		}
		return query.getResultList();
	}
	
	/**
	 * 获取总记录数
	 * @param nativeSql
	 * @param params
	 * @return
	 */
	private long getTotal(String nativeSql, Object... params) {
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM (" + nativeSql + ") AS _A");
		for (int i = 1, len = params.length; i <= len; i++) {
			query.setParameter(i, params[i - 1]);
		}
		return Long.parseLong(query.getSingleResult().toString());
	}
	
	/**
	 * 获取Query对象
	 * @param nativeSql
	 * @param resultSetMapping
	 * @param resultClass
	 * @return
	 */
	private Query createQuery(String nativeSql, String resultSetMapping, Class resultClass){
		if(resultClass != null && resultSetMapping != null)
			throw new IllegalArgumentException("Cannot transformation result to resultClass and resultSetMapping!");
		return resultClass == null?
					(resultSetMapping == null? em.createNativeQuery(nativeSql) 
							: em.createNativeQuery(nativeSql, resultSetMapping)) 
					: em.createNativeQuery(nativeSql, resultClass);
	}
	
	/**
	 * 清除数据
	 */
	public SqlHelper clear(){
		this.sql.setLength(0);
		this.paramsList.clear();
		this.resultClass = null;
		this.resultSetMapping = null;
		return this;
	}
}