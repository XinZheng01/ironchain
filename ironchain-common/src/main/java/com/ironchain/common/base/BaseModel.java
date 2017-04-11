package com.ironchain.common.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	@Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseModel that = (BaseModel) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
}
