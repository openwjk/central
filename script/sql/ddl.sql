CREATE TABLE `ct_config` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
 `creator` varchar(255) NOT NULL DEFAULT 'system' COMMENT '创建人',
 `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `modifier` varchar(255) NOT NULL DEFAULT 'system' COMMENT '修改人',
 `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
 `IS_DELETED` varchar(1) NOT NULL DEFAULT 'n' COMMENT '是否删除',
 `GROUP_CODE` varchar(32) NOT NULL COMMENT '组编码',
 `GROUP_NAME` varchar(64) NOT NULL COMMENT '组名称',
 `CODE` varchar(32) NOT NULL COMMENT '编码',
 `VALUE` varchar(255) NOT NULL COMMENT '值',
 PRIMARY KEY (`id`),
 KEY `IDX_GC_C` (`GROUP_CODE`,`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;