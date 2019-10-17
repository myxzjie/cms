
ALTER TABLE sys_menu ADD COLUMN `state` int(0) NOT NULL DEFAULT 1 COMMENT '1 正常，0无效' AFTER `redirect`;

