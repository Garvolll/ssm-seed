-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.1.37-community - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 o2o 的数据库结构
CREATE DATABASE IF NOT EXISTS `o2o` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `o2o`;

-- 导出  表 o2o.tb_area 结构
CREATE TABLE IF NOT EXISTS `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(20) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='区域表';

-- 正在导出表  o2o.tb_area 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `tb_area` DISABLE KEYS */;
INSERT INTO `tb_area` (`area_id`, `area_name`, `priority`, `create_time`, `last_edit_time`) VALUES
	(4, '郑州', 2, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(5, '武汉', 1, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(6, '上海', 3, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(7, '北京', 3, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(8, '天津', 1, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(9, '乌鲁木齐', 0, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(10, '南京', 2, '2018-03-22 10:54:25', '0000-00-00 00:00:00'),
	(11, '杭州', 2, '2018-03-22 10:54:25', '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `tb_area` ENABLE KEYS */;

-- 导出  表 o2o.tb_head_line 结构
CREATE TABLE IF NOT EXISTS `tb_head_line` (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='头条表';

-- 正在导出表  o2o.tb_head_line 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_head_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_head_line` ENABLE KEYS */;

-- 导出  表 o2o.tb_local_auth 结构
CREATE TABLE IF NOT EXISTS `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='本地账号表';

-- 正在导出表  o2o.tb_local_auth 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `tb_local_auth` DISABLE KEYS */;
INSERT INTO `tb_local_auth` (`local_auth_id`, `user_id`, `user_name`, `password`, `create_time`, `last_edit_time`) VALUES
	(14, 5, '卡卡罗特', '123', '2018-03-22 11:02:54', '0000-00-00 00:00:00'),
	(15, 6, '贝吉塔', '321', '2018-03-22 11:02:54', '0000-00-00 00:00:00'),
	(16, 7, '布欧', '456', '2018-03-22 11:02:54', '0000-00-00 00:00:00'),
	(17, 8, '弗利萨', '789', '2018-03-22 11:02:54', '0000-00-00 00:00:00'),
	(18, 9, '悟天', 'yyd', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(19, 10, '悟饭', 'ada', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(20, 11, '比克', 'aab', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(21, 12, '特兰克斯', 'xxy', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(22, 13, '雅木茶', 'ssg', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(23, 14, '库林', 'aahu', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(24, 15, '布尔玛', 'qqzb', '2018-03-22 11:21:48', '0000-00-00 00:00:00'),
	(25, 16, '琪琪', 'dda', '2018-03-22 11:21:48', '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `tb_local_auth` ENABLE KEYS */;

-- 导出  表 o2o.tb_product 结构
CREATE TABLE IF NOT EXISTS `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- 正在导出表  o2o.tb_product 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;

-- 导出  表 o2o.tb_product_category 结构
CREATE TABLE IF NOT EXISTS `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `shop_id` int(20) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`product_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='商品类别表';

-- 正在导出表  o2o.tb_product_category 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_category` ENABLE KEYS */;

-- 导出  表 o2o.tb_product_img 结构
CREATE TABLE IF NOT EXISTS `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- 正在导出表  o2o.tb_product_img 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_product_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_img` ENABLE KEYS */;

-- 导出  表 o2o.tb_shop 结构
CREATE TABLE IF NOT EXISTS `tb_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创始人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) NOT NULL,
  `shop_desc` varchar(1024) DEFAULT NULL,
  `shop_addr` varchar(200) DEFAULT NULL,
  `shop_img` varchar(1024) DEFAULT NULL,
  `advice` varchar(255) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `phone` varchar(128) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='商铺表';

-- 正在导出表  o2o.tb_shop 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `tb_shop` DISABLE KEYS */;
INSERT INTO `tb_shop` (`shop_id`, `owner_id`, `area_id`, `shop_category_id`, `shop_name`, `shop_desc`, `shop_addr`, `shop_img`, `advice`, `priority`, `phone`, `enable_status`, `create_time`, `last_edit_time`) VALUES
	(1, 15, 4, NULL, '布尔玛书店', '少年jump50周年纪念书店', NULL, NULL, '多来点', 0, NULL, 0, '2018-03-22 11:26:12', '0000-00-00 00:00:00'),
	(2, 12, 5, NULL, '特兰克斯数码', NULL, NULL, NULL, NULL, 0, NULL, 0, '2018-03-22 11:26:12', '0000-00-00 00:00:00'),
	(3, 14, 6, NULL, '库林coco', NULL, NULL, NULL, NULL, 0, NULL, 0, '2018-03-22 11:26:12', '0000-00-00 00:00:00'),
	(4, 13, 7, NULL, '雅木茶小吃', NULL, NULL, NULL, NULL, 0, NULL, 0, '2018-03-22 11:26:12', '0000-00-00 00:00:00'),
	(5, 8, 8, NULL, '弗利萨鲜花', NULL, NULL, NULL, NULL, 0, NULL, 0, '2018-03-22 11:26:12', '0000-00-00 00:00:00'),
	(38, 15, 4, 5, '布尔玛礼品店', 'test', 'sss', '', 'test', 3, '18599999556', 1, '2018-03-22 16:31:19', '2018-03-22 16:31:19'),
	(51, 15, 4, 5, '布尔玛小店', 'test', 'ttt', '\\upload\\item\\shop\\51\\2018032315073351734.jpg', 'test', 3, '18599999556', 0, '2018-03-23 15:07:33', '2018-03-23 15:07:33');
/*!40000 ALTER TABLE `tb_shop` ENABLE KEYS */;

-- 导出  表 o2o.tb_shop_category 结构
CREATE TABLE IF NOT EXISTS `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(2000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT '',
  `priority` int(2) NOT NULL DEFAULT '0',
  `parent_id` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商铺类别表';

-- 正在导出表  o2o.tb_shop_category 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `tb_shop_category` DISABLE KEYS */;
INSERT INTO `tb_shop_category` (`shop_category_id`, `shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `parent_id`, `create_time`, `last_edit_time`) VALUES
	(1, '书籍', '', '', 0, NULL, '2018-03-22 11:13:19', '0000-00-00 00:00:00'),
	(2, '数码产品配件', '', '', 0, NULL, '2018-03-22 11:13:19', '0000-00-00 00:00:00'),
	(3, '咖啡饮品', '', '', 0, NULL, '2018-03-22 11:13:19', '0000-00-00 00:00:00'),
	(4, '小吃', '', '', 0, NULL, '2018-03-22 11:13:19', '0000-00-00 00:00:00'),
	(5, '鲜花礼品', '', '', 0, NULL, '2018-03-22 11:13:19', '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `tb_shop_category` ENABLE KEYS */;

-- 导出  表 o2o.tb_user 结构
CREATE TABLE IF NOT EXISTS `tb_user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `profileImg` varchar(1024) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0.禁止使用本商城 1.允许使用本商城',
  `user_type` int(2) NOT NULL DEFAULT '1' COMMENT '1.顾客 2.店家 3.超级管理员',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_edit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  o2o.tb_user 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`user_id`, `name`, `profileImg`, `phone`, `gender`, `enable_status`, `user_type`, `create_time`, `last_edit_time`) VALUES
	(5, '卡卡罗特', NULL, '18530009404', NULL, 1, 3, '2018-03-22 10:59:37', '0000-00-00 00:00:00'),
	(6, '贝吉塔', NULL, '15859401221', NULL, 1, 1, '2018-03-22 10:59:37', '0000-00-00 00:00:00'),
	(7, '布欧', NULL, '18120031004', NULL, 0, 1, '2018-03-22 10:59:37', '0000-00-00 00:00:00'),
	(8, '弗利萨', NULL, '13815592233', NULL, 1, 2, '2018-03-22 10:59:37', '0000-00-00 00:00:00'),
	(9, '悟天', NULL, '13659844563', NULL, 1, 1, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(10, '悟饭', NULL, '18266933078', NULL, 1, 1, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(11, '比克', NULL, '15032869921', NULL, 1, 1, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(12, '特兰克斯', NULL, '18894655596', NULL, 1, 2, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(13, '雅木茶', NULL, '13966891223', NULL, 1, 2, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(14, '库林', NULL, '13895523115', NULL, 1, 2, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(15, '布尔玛', NULL, '13902030599', NULL, 1, 2, '2018-03-22 11:20:04', '0000-00-00 00:00:00'),
	(16, '琪琪', NULL, '18630669231', NULL, 1, 1, '2018-03-22 11:20:04', '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

-- 导出  表 o2o.tb_wechat_auth 结构
CREATE TABLE IF NOT EXISTS `tb_wechat_auth` (
  `wecaht_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(80) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(10) NOT NULL,
  PRIMARY KEY (`wecaht_auth_id`),
  UNIQUE KEY `uk_wechat_profile` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='微信账号表';

-- 正在导出表  o2o.tb_wechat_auth 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_wechat_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_wechat_auth` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
