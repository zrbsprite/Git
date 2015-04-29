SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `ease_licence`;
CREATE TABLE `ease_licence` (
  `id` varchar(36) NOT NULL COMMENT 'uuid主键',
  `macAddress` varchar(30) DEFAULT NULL COMMENT 'mac地址',
  `type` tinyint(4) DEFAULT NULL COMMENT '认证类型',
  `registDate` datetime DEFAULT NULL COMMENT '注册时间',
  `lastDate` datetime DEFAULT NULL COMMENT '有效期止',
  `serializeCode` varchar(40) DEFAULT NULL COMMENT '注册号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ease_permission`;
CREATE TABLE `ease_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限蒙城',
  `description` varchar(100) DEFAULT NULL,
  `resources` varchar(100) DEFAULT NULL COMMENT '资源',
  `priority` int(11) DEFAULT NULL COMMENT '顺序',
  `type` varchar(50) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL COMMENT '权限路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ease_role`;
CREATE TABLE `ease_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '角色说明',
  `avaliable` enum('1','0') DEFAULT NULL COMMENT '是否可用：0-不可用，1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ease_user`;
CREATE TABLE `ease_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `user_status` enum('2','1','0') DEFAULT NULL COMMENT '用户状态:0-正常，1-锁定，2-废弃',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ease_user_role`;
CREATE TABLE `ease_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;