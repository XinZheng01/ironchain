package com.ironchain.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.ironchain.common.kits.SpringKit;
import com.ironchain.common.persistence.dialect.Dialect;

@NoRepositoryBean
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

	protected final EntityManager em;
	
	private Dialect dialect;
	
	public BaseDaoImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
		this.dialect = (Dialect) SpringKit.getBean("pageDialect");
	}

	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.dialect = (Dialect) SpringKit.getBean("pageDialect");
	}
	
	/**
	 * 使用SQL查询分页泛型对象
	 * @param nativeSql
	 * @param pageable
	 * @param params
	 * @return
	 */
	public Page<T> queryPageBySql(String nativeSql, Pageable pageable, List<Object> params) {
		String limitSql = pageable == null? nativeSql : 
			dialect.getLimitString(nativeSql, pageable.getOffset(), pageable.getPageSize());
		Query query = em.createNativeQuery(limitSql, getDomainClass());
		for (int i = 1, len = params.size(); i <= len; i++) {
			query.setParameter(i, params.get(i - 1));
		}
		return PageableExecutionUtils.getPage(query.getResultList(), pageable, ()->{return getTotalBySql(nativeSql, params);});
	}
	
	/**
	 * 使用SQL查询单个泛型对象
	 * @param nativeSql
	 * @param params
	 * @return
	 */
	public T querySingleBySql(String nativeSql, List<Object> params) {
		Query query = em.createNativeQuery(nativeSql, getDomainClass());
		for (int i = 1, len = params.size(); i <= len; i++) {
			query.setParameter(i, params.get(i - 1));
		}
		List<T> results = query.getResultList();
		if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException("result returns more than one elements");
	}
	
	/**
	 * 使用SQL查询Map列表
	 * @param nativeSql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryMapBySql(String nativeSql, List<Object> params) {
		Query query = em.createNativeQuery(nativeSql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (int i = 1, len = params.size(); i <= len; i++) {
			query.setParameter(i, params.get(i - 1));
		}
		return query.getResultList();
	}
	
	/**
	 * 使用SQL查询泛型列表
	 * @param nativeSql
	 * @param params
	 * @return
	 */
	public List<T> queryListBySql(String nativeSql, List<Object> params) {
		Query query = em.createNativeQuery(nativeSql, getDomainClass());
		for (int i = 1, len = params.size(); i <= len; i++) {
			query.setParameter(i, params.get(i - 1));
		}
		return query.getResultList();
	}
	
	private long getTotalBySql(String nativeSql, List<Object> params) {
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM (" + nativeSql + ") AS _A");
		for (int i = 1, len = params.size(); i <= len; i++) {
			query.setParameter(i, params.get(i - 1));
		}
		return ((Number) query.getResultList().get(0)).longValue();
	}
	
}
