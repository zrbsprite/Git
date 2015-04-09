create database ease;
DROP TABLE IF EXISTS `ease_licence`;
CREATE TABLE `ease_licence` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT 'uuid主键',
  `macAddress` varchar(30) DEFAULT NULL COMMENT 'mac地址',
  `type` tinyint(4) DEFAULT NULL COMMENT '认证类型',
  `registDate` datetime DEFAULT NULL COMMENT '注册时间',
  `lastDate` datetime DEFAULT NULL COMMENT '有效期止',
  `serializeCode` varchar(40) DEFAULT NULL COMMENT '注册号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
