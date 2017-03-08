package com.ironchain.common.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 
 * @author Administrator
 */
@MappedSuperclass
public class DataModel extends BaseModel {

    private static final long serialVersionUID = -1461917182838548671L;

    /** 创建时间 */
    @CreatedDate
    @Column(name = "create_time")
    protected Date createTime;
    
    /** 创建者 */
//    protected User createBy;
    
    /** 更新时间 */
    @LastModifiedDate
    @Column(name = "update_time")
    protected Date updateTime;
    
    /** 更新者 */
//  protected User updateBy;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}
