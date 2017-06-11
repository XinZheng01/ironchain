package com.ironchain.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 订单表
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_order")
public class ShopOrder extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 订单编号*/
	@Column(name="order_no")
	private String orderNo;
	
	/** 外部交易号*/
	@Column(name="out_trade_no")
	private String outTradeNo;
	
	/** 支付类型*/
	@Column(name="payment_type")
	private int paymentType;
	
	/** 买家id*/
	@Column(name="buyer_id")
	private Long buyerId;
	
	/** 买家会员名称*/
	@Column(name="member_name")
	private String memberName;
	
	/** 买家ip*/
	@Column(name="buyer_ip")
	private String buyerIp;
	
	/** 买家附言*/
	@Column(name="buyer_message")
	private String buyerMessage;
	
	/** 收货人的手机号码*/
	@Column(name="receiver_mobile")
	private String receiverMobile;
	
	/** 收货人所在省*/
	@Column(name="receiver_province")
	private Long receiverProvince;
	
	/** 收货人所在市*/
	@Column(name="receiver_city")
	private Long receiverCity;
	
	/** 收货人所在区*/
	@Column(name="receiver_district")
	private Long receiverDistrict;
	
	/** 收货人详细地址*/
	@Column(name="receiver_address")
	private String receiverAddress;
	
	/** 收货人姓名*/
	@Column(name="receiver_name")
	private String receiverName;
	
	/** 商品总价*/
	@Column(name="products_price")
	private BigDecimal productsPrice;
	
	/** 订单运费*/
	@Column(name="freight")
	private BigDecimal freight;
	
	/** 订单总价*/
	@Column(name="order_price")
	private BigDecimal orderPrice;
	
	/** 订单状态*/
	@Column(name="status")
	private Integer status;
	
	/** 订单付款时间*/
	@Column(name="pay_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date payTime;
	
	/** 卖家发货时间*/
	@Column(name="consign_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date consignTime;
	
	/** 买家签收时间*/
	@Column(name="sign_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signTime;
	
	/** 订单创建时间*/
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/** 订单完成时间*/
	@Column(name="finish_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishTime;
	
}
