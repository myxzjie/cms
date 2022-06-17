
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `sys_social` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `user_id` bigint(11) NULL DEFAULT NULL COMMENT '系统用户ID',
   `uuid` varchar(64) DEFAULT NULL COMMENT '密码',
   `username` varchar(16) DEFAULT NULL COMMENT '名称',
   `nickname` varchar(12) DEFAULT NULL,
   `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
   `state` int DEFAULT '1' COMMENT '状态 1正常，0失败',
   `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `source` varchar(8) DEFAULT NULL COMMENT '用户来源 gitee',
   `gender` varchar(2) DEFAULT '1' COMMENT '性别 1女 0男',
   `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
   `blog` varchar(256) DEFAULT NULL COMMENT '用户网址',
   `company` varchar(256) DEFAULT NULL COMMENT '所在公司',
   `location` varchar(256) DEFAULT NULL COMMENT '位置',
   `remark` varchar(256) DEFAULT NULL COMMENT '用户备注（各平台中的用户个人介绍）',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='授权用户';

SET FOREIGN_KEY_CHECKS = 1;