ALTER TABLE `sys_menu`
ADD COLUMN `cache` bit NULL DEFAULT b'0' AFTER `type`,
ADD COLUMN `redirect` varchar(255) NULL AFTER `cache`;
