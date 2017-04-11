package com.ironchain.common.base;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class ModelController<DAO extends BaseDao<M, Long>, M> extends BaseController {
	
	@Autowired
	protected DAO modelDao;
	
	private Class<M> modelClass = null;
	
    @SuppressWarnings("unchecked")
	public ModelController() {
		modelClass = (Class<M>)((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@ModelAttribute
	public M getModel(@RequestParam(required=false) Long id){
		try {
			if(id != null)
				return modelDao.findOne(id);
			else
				return modelClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public DAO getModelDao(){
		return modelDao;
	}
	
}
