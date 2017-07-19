CREATE TABLE `banner` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
`img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片' ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接' ,
`type`  int(11) NULL DEFAULT NULL COMMENT '类型' ,
`sort_id`  int(11) NULL DEFAULT NULL COMMENT '排序值' ,
`show_time`  datetime NULL DEFAULT NULL COMMENT '上架时间' ,
`un_show_time`  datetime NULL DEFAULT NULL COMMENT '下架时间' ,
`create_by`  bigint(20) NULL DEFAULT NULL COMMENT '创建人',
`update_by`  bigint(20) NULL DEFAULT NULL COMMENT '更新人',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页轮播图';

CREATE TABLE `shop_banner` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
`img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片' ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接' ,
`type`  int(11) NULL DEFAULT NULL COMMENT '类型' ,
`sort_id`  int(11) NULL DEFAULT NULL COMMENT '排序值' ,
`show_time`  datetime NULL DEFAULT NULL COMMENT '上架时间' ,
`un_show_time`  datetime NULL DEFAULT NULL COMMENT '下架时间' ,
`create_by`  bigint(20) NULL DEFAULT NULL COMMENT '创建人',
`update_by`  bigint(20) NULL DEFAULT NULL COMMENT '更新人',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城轮播图';

CREATE TABLE `information` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题' ,
`source`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章来源' ,
`img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图' ,
`order_num`  int(11) NULL DEFAULT NULL COMMENT '排序值' ,
`keywords`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页关键字' ,
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页描述' ,
`type`  int(11) NULL DEFAULT NULL COMMENT '类型' ,
`status`  int(11) NULL DEFAULT NULL ,
`show_time`  datetime NULL DEFAULT NULL COMMENT '上架时间' ,
`un_show_time`  datetime NULL DEFAULT NULL COMMENT '下架时间' ,
`content`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容' ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯';

CREATE TABLE `member` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
`head_img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
`type`  tinyint(2) NULL DEFAULT NULL COMMENT '类型',
`service_type`  tinyint(2) NULL DEFAULT NULL COMMENT '业务类型',
`mobilephone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
`idcard`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
`company_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
`company_legal`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人',
`company_legal_phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人电话',
`company_idcard`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人身份证',
`company_tel`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业固定电话',
`company_license_img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业营业执照',
`company_address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业地址',
`company_precision`  decimal(13,2) NULL DEFAULT NULL COMMENT '企业精度',
`status`  tinyint(2) COMMENT '状态',
`level_id`  bigint(20) NULL COMMENT '会员等级',
`parent_id`  bigint(20) NULL COMMENT '父账号',
`last_login_time`  datetime NULL DEFAULT NULL COMMENT '最后登录时间',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员';

CREATE TABLE `factory_location` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`member_id` bigint(20) NOT NULL COMMENT '企业',
`adcode` varchar(10) NOT NULL COMMENT '区域',
`address` varchar(500) NOT NULL COMMENT '详细地址',
`location` varchar(50) NOT NULL COMMENT '坐标',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工厂位置';

CREATE TABLE `friendcircle` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`content`  varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容' ,
`member_id` bigint(20) NOT NULL COMMENT '发送人',
`send_time`  datetime NULL DEFAULT NULL COMMENT '发送时间' ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='朋友圈';

CREATE TABLE `friendcircle_img` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`friendcircle_id` bigint(20) NOT NULL COMMENT '朋友圈id',
`img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
`thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '缩略图',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='朋友圈图片';

CREATE TABLE `gd_area` (
  `adcode` varchar(10) NOT NULL COMMENT '区域编码',
  `provcode` varchar(10) DEFAULT NULL COMMENT '所属省编码',
  `citycode` varchar(10) DEFAULT NULL COMMENT '所属市编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `provname` varchar(50) DEFAULT NULL COMMENT '省名称',
  `cityname` varchar(50) DEFAULT NULL COMMENT '市名称',
  `center` varchar(50) DEFAULT NULL COMMENT '中心区位置',
  `level` varchar(50) DEFAULT NULL COMMENT '级别 country province city district',
  PRIMARY KEY (`adcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='高德省市区';

CREATE TABLE `demand_class` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`type` TINYINT(2) COMMENT '类型',
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求分类';

CREATE TABLE `equipment_class` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`type` TINYINT(2) COMMENT '类型',
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备分类';

CREATE TABLE `demand_file` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`demand_id`  bigint(20) NOT NULL COMMENT '需求id',
##`type`  tinyint(2) NULL DEFAULT NULL COMMENT '类型',
`path`  varchar(255) NOT NULL COMMENT '文件路径',
PRIMARY KEY (`id`),
INDEX `idx_demand_id` (`demand_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求文档';

CREATE TABLE `demand_offer` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
##`hour_fee`  int(11) NULL DEFAULT NULL COMMENT '工时费用',
##`material_fee`  int(11) NULL DEFAULT NULL COMMENT '材料费用',
`price`  decimal(13,2) NULL DEFAULT NULL COMMENT '价格',
`demand_id`  bigint(20) NULL DEFAULT NULL COMMENT '需求',
`member_id`  bigint(20) NULL DEFAULT NULL COMMENT '报价人',
`status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
INDEX `idx_demand_id` (`demand_id`) USING BTREE ,
INDEX `idx_member_id` (`member_id`) USING BTREE 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求报价';

CREATE TABLE `demand` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
`number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数量',
`adcode`  varchar(10) NULL COMMENT '区域',
`type`  tinyint(2) NULL DEFAULT NULL COMMENT '类型',
`bid_number`  int(11) NULL DEFAULT NULL COMMENT '竞方数量',
`start_date`  date NULL DEFAULT NULL COMMENT '开始时间',
`end_date`  date NULL DEFAULT NULL COMMENT '结束时间',
`member_id`  bigint(20) NOT NULL COMMENT '发布人',
`class_id`  bigint(20) NOT NULL COMMENT '需求分类',
`progress`  int(11) NULL DEFAULT NULL COMMENT '进度',
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
`machined`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '材料',
`term`  int(5) NULL DEFAULT NULL COMMENT '交期天数',
`payment`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款描述',
`brand`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
`kinds`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
`life`  int(11) NULL DEFAULT NULL COMMENT '年限',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
INDEX `idx_class_id` (`class_id`) USING BTREE ,
INDEX `idx_member_id` (`member_id`) USING BTREE 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求';

CREATE TABLE `demand_equ_ref` (
`demand_id`  bigint(20) NOT NULL ,
`equ_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`demand_id`, `equ_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求设备分类关系';

CREATE TABLE `member_equ_ref` (
`member_id`  bigint(20) NOT NULL ,
`equ_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`member_id`, `equ_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员设备分类关系';

CREATE TABLE `shop_class` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称' ,
`parent_id`  bigint(20) NULL COMMENT '上级分类',
`sort_id` int(11) COMMENT '排序值',
`status` TINYINT(2) COMMENT '状态',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类';

CREATE TABLE `shop_product` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题' ,
`class_id`  bigint(20) NULL COMMENT '所属分类',
`code` varchar(255) NOT NULL COMMENT '商品编号',
`price` decimal(13,2) NOT NULL COMMENT '商品价格',
`img` varchar(255) NOT NULL COMMENT '商品主图',
`img_array` varchar(2000) NOT NULL COMMENT '商品图片列表',
`stock` INT(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
`freight` decimal(13,2) NOT NULL DEFAULT 0.00 COMMENT '商品运费',
`status` TINYINT(2) COMMENT '状态',
`sort_id` int(11) COMMENT '排序值',
`content` text NOT NULL COMMENT '商品详情',
`sale_date` datetime COMMENT '上下架时间',
`sales` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
`evaluates` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评价数',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

CREATE TABLE `shop_product_param` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`product_id`  bigint(20) NOT NULL COMMENT '商品' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数名' ,
`value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数值' ,
`sort_id` int(11) COMMENT '排序值',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品参数';

CREATE TABLE `shop_product_spec` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格名' ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格';

CREATE TABLE `shop_product_spec_value` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`spec_id`  bigint(20) NOT NULL COMMENT '商品规格' ,
`value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格值' ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格值';

CREATE TABLE `shop_product_sku` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`product_id`  bigint(20) NOT NULL COMMENT '商品' ,
`spec_items` varchar(2000) NOT NULL COMMENT '商品规格值' ,
`code` varchar(255) NOT NULL COMMENT '商品编号',
`price` decimal(13,2) NOT NULL COMMENT '商品价格',
`stock` INT(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SKU';


CREATE TABLE `shop_order` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`order_no` VARCHAR(255) NOT NULL COMMENT '订单编号',
`out_trade_no` VARCHAR(100) NULL COMMENT '外部交易号',
`payment_type` TINYINT(2) NOT NULL COMMENT '支付类型',
`buyer_id` bigint(20) NOT NULL COMMENT '买家id',
`member_name` VARCHAR(50) NOT NULL COMMENT '买家会员名称',
`buyer_ip` VARCHAR(100) NULL COMMENT '买家ip',
`buyer_message` VARCHAR(255) NULL DEFAULT NULL COMMENT '买家附言',
`receiver_mobile` VARCHAR(11) NULL COMMENT '收货人的手机号码',
`receiver_province` bigint(20) NOT NULL COMMENT '收货人所在省',
`receiver_city` bigint(20) NOT NULL COMMENT '收货人所在市',
`receiver_district` bigint(20) NULL COMMENT '收货人所在区',
`receiver_address` VARCHAR(255) NOT NULL COMMENT '收货人详细地址',
`receiver_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '收货人姓名',
`products_price` DECIMAL(13, 2) NOT NULL COMMENT '商品总价',
`freight` DECIMAL(13, 2) NOT NULL COMMENT '订单运费',
`order_price` DECIMAL(13, 2) NOT NULL COMMENT '订单总价',
`status` TINYINT(2) NOT NULL DEFAULT 1 COMMENT '订单状态',
`pay_time` DATETIME NULL DEFAULT NULL COMMENT '订单付款时间',
`consign_time` DATETIME NULL COMMENT '卖家发货时间',
`sign_time` DATETIME NULL COMMENT '买家签收时间',
`create_time` DATETIME NULL COMMENT '订单创建时间',
`finish_time` DATETIME NULL COMMENT '订单完成时间',
PRIMARY KEY (id)
)ENGINE = INNODB COMMENT = '订单表';
##支付类型 1支付宝 2微信 3银联
##订单状态 1 待付款 2待发货 3待收货 4待收货 5已完成 6已关闭

CREATE TABLE `shop_order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `product_title` varchar(255) NOT NULL COMMENT '商品标题',
  `product_img` INT(11) NOT NULL DEFAULT 0 COMMENT '商品图片',
  `sku_id` bigint(20) NOT NULL COMMENT 'skuID',
  `price` DECIMAL(13, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `num` int(11) NOT NULL DEFAULT 0 COMMENT '购买数量',
  `adjust_price` DECIMAL(13, 2) NOT NULL DEFAULT 0.00 COMMENT '调整金额',
  `total_price` DECIMAL(13, 2) NOT NULL DEFAULT 0.00 COMMENT '商品总价',
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';

CREATE TABLE `letter_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户',
  `letter_id` bigint(20) NULL COMMENT '站内信id',
  `status`  TINYINT(2) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (id),
  INDEX `idx_letter_id` (`letter_id`) USING BTREE ,
  INDEX `idx_user_id` (`user_id`) USING BTREE 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内信记录';
##status 0 未读 1已读
##type 1内容 2链接 3需求

CREATE TABLE `letter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` varchar(2000) NULL COMMENT '内容',
  `number` INT(11) NOT NULL COMMENT '接收人数',
  `members` text COMMENT '指定发送的用户',
  `type` TINYINT(2) NOT NULL COMMENT '类型',
  `send_type` TINYINT(2) NOT NULL COMMENT '发送类型',
  `service_type` TINYINT(2) NOT NULL COMMENT '业务类型',
  `url`  varchar(255) NULL COMMENT '链接',
  `attr`  varchar(255) NULL COMMENT '属性',
  `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT NULL ,
  `create_by`  bigint(20) NULL DEFAULT NULL ,
  `update_by`  bigint(20) NULL DEFAULT NULL ,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内信';
##type 1内容 2链接 3需求
##send_type 1群发 2指定用户发送

CREATE TABLE `member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `price` decimal(13,2) NOT NULL COMMENT '价格',
  `status` TINYINT(2) NOT NULL COMMENT '状态',
  `reset_count` int(11) NOT NULL DEFAULT 0 COMMENT '需求重置次数',
  `description`  varchar(255) NULL DEFAULT NULL COMMENT '描述',
  `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT NULL ,
  `create_by`  bigint(20) NULL DEFAULT NULL ,
  `update_by`  bigint(20) NULL DEFAULT NULL ,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级管理';

CREATE TABLE `member_levelup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '会员',
  `member_level_id` decimal(13,2) NOT NULL COMMENT '会员等级',	
  `start_date`  datetime NOT NULL COMMENT '会员开始时间' ,
  `end_date`  datetime NOT NULL COMMENT '会员结束时间' ,
  `income`   decimal(13,2) NOT NULL COMMENT '支付金额',
  `company_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `company_legal`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人',
  `company_legal_phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人电话',
  `company_idcard`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人身份证',
  `company_tel`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业固定电话',
  `company_license_img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业营业执照',
  `company_address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业地址',
  `company_precision`  decimal(13,2) NULL DEFAULT NULL COMMENT '企业精度',
  `status`  tinyint(2) COMMENT '状态',
  `level_id`  bigint(20) NULL COMMENT '会员等级',
  `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT NULL ,
  `create_by`  bigint(20) NULL DEFAULT NULL ,
  `update_by`  bigint(20) NULL DEFAULT NULL ,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员开通记录';

CREATE TABLE `levelup_equ_ref` (
`levelup_id`  bigint(20) NOT NULL ,
`equ_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`levelup_id`, `equ_id`),
INDEX `IDX_equ_id` (`equ_id`) USING BTREE 
)
ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COMMENT='升级记录设备关系';