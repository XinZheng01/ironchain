package com.ironchain.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>{
	
	Page<T> queryPageBySql(String nativeSql, Pageable pageable, List<Object> params);
	
	T querySingleBySql(String nativeSql, List<Object> params);
	
	List<Map<String, Object>> queryMapBySql(String nativeSql, List<Object> params);
	
	List<T> queryListBySql(String nativeSql, List<Object> params);
}
