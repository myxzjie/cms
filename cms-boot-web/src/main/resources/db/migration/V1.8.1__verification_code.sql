ALTER TABLE sys_verify_code ADD COLUMN `state` int(0) NOT NULL DEFAULT 1 COMMENT '1 正常，0无效' AFTER `message`;
