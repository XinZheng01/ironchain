package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.ironchain.common.base.BaseModel;
import com.ironchain.common.domain.Constants.RegexConstants;

import lombok.Getter;
import lombok.Setter;
/**
 * 会员收货地址
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_express_address")
public class ShopExpressAddress extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 会员*/
	@Column(name="member_id")
	private Long memberId;
	
	/** 收件人*/
	@NotBlank(message="收件人不能为空")
	@Size(max=50, message="收件人过长")
	@Column(name="consigner")
	private String consigner;
	
	/** 手机*/
	@NotNull(message="手机号码不能为空")
	@Pattern(regexp=RegexConstants.MOBILE_REGEX, message="手机号码格式不正确")
	@Column(name="mobile")
	private String mobile;
	
	/** 区域*/
	@NotNull(message="区域不能为空")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adcode")
	private GdArea area;
	
	/** 详细地址*/
	@NotNull(message="详细地址不能为空")
	@Size(max=255, message="详细地址过长")
	@Column(name="address")
	private String address;
	
	/** 邮编*/
	@Column(name="zip_code")
	private String zipCode;
	
	/** 地址别名*/
	@Column(name="alias")
	private String alias;
	
	/** 默认收货地址*/
	@Column(name="is_default")
	private int isDefault = Constants.JUDGE_NO;
	
}
