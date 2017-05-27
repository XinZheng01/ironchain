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
)ENGINE=InnoDB COMMENT='首页轮播图';

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
)ENGINE=InnoDB COMMENT='商城轮播图';

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
`content`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容' ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='资讯';

CREATE TABLE `member` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
`head_img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
`type`  tinyint(2) NULL DEFAULT NULL COMMENT '类型',
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
`status`  tinyint(2) COMMENT '状态',
`last_login_time`  datetime NULL DEFAULT NULL COMMENT '最后登录时间',
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  datetime NULL DEFAULT NULL ,
`create_by`  bigint(20) NULL DEFAULT NULL ,
`update_by`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='会员';

CREATE TABLE `friendcircle` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`content`  varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容' ,
`member_id` bigint(20) NOT NULL COMMENT '发送人',
`send_time`  datetime NULL DEFAULT NULL COMMENT '发送时间' ,
PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='朋友圈';

CREATE TABLE `friendcircle_img` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`friendcircle_id` bigint(20) NOT NULL COMMENT '朋友圈id',
`img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
`thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '缩略图',
PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='朋友圈图片';
