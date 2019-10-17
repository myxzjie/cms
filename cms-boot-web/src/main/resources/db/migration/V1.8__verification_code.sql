create table sys_verify_code (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
 scenes varchar(16) NOT NULL COMMENT '业务名称',
 value varchar(16) NOT NULL COMMENT '值',
 target varchar(24) NOT NULL COMMENT '目标者',
 type varchar(16) NOT NULL COMMENT '类型',
 create_date datetime NOT null  comment '时间',
 message varchar(255) DEFAULT NULL COMMENT '信息说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='验证code';
