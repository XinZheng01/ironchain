-- 菜单SQL
INSERT INTO `system_permission` (`parent_id`, `code`, `icon`, `name`, `status`, `type`, `url`, `order_num`)
 VALUES ('4', '${tableNameLower}:list', 'icon-file-o', '${tableComment!}', '1', '2', '/${pathName}/list', '0');


-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `system_permission` (`parent_id`, `code`, `name`, `status`, `type`, `url`, `order_num`)
 SELECT @parentId, '${tableNameLower}:add', '新增', '1', '3', '/${pathName}/add', '0';
INSERT INTO `system_permission` (`parent_id`, `code`, `name`, `status`, `type`, `url`, `order_num`)
 SELECT @parentId, '${tableNameLower}:edit', '编辑', '1', '3', '/${pathName}/edit', '0';
INSERT INTO `system_permission` (`parent_id`, `code`, `name`, `status`, `type`, `url`, `order_num`)
 SELECT @parentId, '${tableNameLower}:delete', '删除', '1', '3', '/${pathName}/delete', '0';
