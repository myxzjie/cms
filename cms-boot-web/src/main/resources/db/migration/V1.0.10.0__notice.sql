

create table sys_notice (
    id bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    title varchar(64) NOT NULL COMMENT '标题',
    type varchar(16) NOT NULL COMMENT '类型(1通知 2公告)',
    `content` varchar(512) DEFAULT NULL COMMENT '公告内容',
    `state` int DEFAULT '1' COMMENT '状态 1正常，0失败',
    `start_date` datetime DEFAULT NULL COMMENT '开始时间',
    end_date datetime DEFAULT NULL COMMENT '结束时间',
    author bigint(11) DEFAULT NULL COMMENT '创建者' ,
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    mod_author bigint(11) DEFAULT NULL COMMENT '更新者',
    update_date datetime DEFAULT NULL COMMENT '修改时间',
    remark varchar( 255) COMMENT '备注',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='通知公告';


