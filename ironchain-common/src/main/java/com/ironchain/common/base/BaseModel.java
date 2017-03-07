package com.ironchain.common.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Administrator
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -1461917182838548671L;

    @Id
    @GeneratedValue
    protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}
