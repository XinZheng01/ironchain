package com.ironchain.common.base;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ironchain.common.kits.SpringKit;
import com.ironchain.common.persistence.SqlHelper;

@NoRepositoryBean
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

	protected final EntityManager em;

	public BaseDaoImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
	}

	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}
	
	@Override
	public SqlHelper createSqlHelper(){
		return SpringKit.getBean(SqlHelper.class);
	}
}
