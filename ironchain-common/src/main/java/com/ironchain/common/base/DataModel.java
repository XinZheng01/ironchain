package com.ironchain.common.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.ironchain.common.domain.SystemUser;

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
    @CreatedBy
    @OneToOne
    @JoinColumn(name = "create_by")
    protected SystemUser createBy;
    
    /** 更新时间 */
    @LastModifiedDate
    @Column(name = "update_time")
    protected Date updateTime;
    
    /** 更新者 */
    @LastModifiedBy
    @OneToOne
    @JoinColumn(name = "update_by")
    protected SystemUser updateBy;

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

	public SystemUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(SystemUser createBy) {
		this.createBy = createBy;
	}

	public SystemUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(SystemUser updateBy) {
		this.updateBy = updateBy;
	}
}
