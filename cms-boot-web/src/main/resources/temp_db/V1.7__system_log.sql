create table sys_system_log (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
 name varchar(16) DEFAULT NULL COMMENT '名称',
  `username` varchar(16) DEFAULT NULL COMMENT '用户名称',
  `request_ip` varchar(24) DEFAULT NULL COMMENT 'IP',
  address varchar(255) DEFAULT NULL COMMENT '地址',
  type int(2) default null Comment '类型',
  level varchar(8) default null comment '日志级别',
  description varchar(255) default null  comment '说明',
  method varchar(255) default null  comment '请求方法',
  url varchar(255) default null  comment 'url',
  params text default null  comment '参数',
  browser varchar(255) default null  comment '浏览器',
  start_time datetime default null  comment '开始时间',
  finish_time datetime default null  comment '完成时间',
  time bigint(11) default null  comment '请求耗时',
  exception_detail text default null  comment '异常详细',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='图床管理组';
