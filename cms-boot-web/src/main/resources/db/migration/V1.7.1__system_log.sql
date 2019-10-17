
ALTER TABLE `sys_system_log`
ADD COLUMN `create_date` datetime(0) NULL COMMENT '创建时间' AFTER `exception_detail`;
