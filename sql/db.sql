CREATE TABLE `shop_banner` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
`picture_path`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片' ,
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
)ENGINE=InnoDB;