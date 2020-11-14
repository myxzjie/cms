ALTER TABLE `sys_menu` ADD COLUMN `always_show` bit(1) NULL DEFAULT 0 COMMENT '是否展开' AFTER `state`;
