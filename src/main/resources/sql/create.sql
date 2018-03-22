use o2o;
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area`(
`area_id` int(2) NOT NULL AUTO_INCREMENT,
`area_name` VARCHAR (20) NOT NULL ,
`priority` int(2) NOT NULL DEFAULT '0',
`create_time` datetime DEFAULT null,
`last_edit_time` datetime DEFAULT null,
PRIMARY KEY (area_id),
UNIQUE KEY `UK_AREA`(area_name)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='区域表';

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`(
`user_id` int(10) NOT NULL AUTO_INCREMENT,
`name` VARCHAR (32) DEFAULT NULL,
`profileImg` VARCHAR (1024) DEFAULT NULL ,
`phone` VARCHAR (32) DEFAULT NULL ,
`gender` int (2) DEFAULT NULL ,
`enable_status` int (2) NOT NULL DEFAULT '0' COMMENT '0.禁止使用本商城 1.允许使用本商城',
`user_type` int (2) NOT NULL DEFAULT '1' COMMENT '1.顾客 2.店家 3.超级管理员' ,
`create_time` datetime DEFAULT null,
`last_edit_time` datetime DEFAULT null,
PRIMARY KEY (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth`(
`wecaht_auth_id` int(10) NOT NULL AUTO_INCREMENT,
`open_id` VARCHAR (80) NOT NULL ,
`create_time` datetime DEFAULT null,
`user_id` int(10) NOT NULL ,
PRIMARY KEY (wecaht_auth_id),
UNIQUE KEY `uk_wechat_profile`(open_id)
-- CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (user_id) REFERENCES `tb_user`(user_id)
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='微信账号表';

DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth`(
`local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
`user_id` int(10) NOT NULL,
`user_name` VARCHAR (128) NOT NULL ,
`password` VARCHAR (128) NOT NULL ,
`create_time` datetime DEFAULT NULL,
`last_edit_time` datetime DEFAULT NULL,
PRIMARY KEY (local_auth_id),
UNIQUE KEY `uk_local_profile`(user_name)
-- CONSTRAINT `fk_localauth_profile` FOREIGN KEY (user_id) REFERENCES `tb_user`(user_id)
)ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='本地账号表';


DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line`(
`line_id` int(100) NOT NULL AUTO_INCREMENT,
`line_name` VARCHAR (1000) DEFAULT NULL,
`line_link` VARCHAR (2000) NOT NULL ,
`line_img` VARCHAR (2000) NOT NULL ,
`priority` int(2) DEFAULT NULL ,
`enable_status` int(2) NOT NULL DEFAULT '0',
`create_time` datetime DEFAULT NULL,
`last_edit_time` datetime DEFAULT NULL,
PRIMARY KEY (line_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='头条表';



DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category`(
`shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
`shop_category_name` VARCHAR (100) DEFAULT NULL,
`shop_category_desc` VARCHAR (2000) NOT NULL ,
`shop_category_img` VARCHAR (2000) NOT NULL ,
`priority` int(2) NOT NULL DEFAULT '0' ,
`parent_id` int(11) DEFAULT NULL ,
`create_time` datetime DEFAULT NULL,
`last_edit_time` datetime DEFAULT NULL,
PRIMARY KEY (shop_category_id)
-- CONSTRAINT `K=fk_shop_category_self` FOREIGN KEY (parent_id) REFERENCES `tb_shop_category`(shop_category_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商铺类别表';




DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop`(
`shop_id` int(11) NOT NULL AUTO_INCREMENT,
`owner_id` int(10) NOT NULL COMMENT '店铺创始人',
`area_id` int(5) DEFAULT NULL ,
`shop_category_id` int(11) DEFAULT NULL ,
`shop_name` VARCHAR (256) NOT NULL,
`shop_desc` VARCHAR (1024) DEFAULT NULL ,
`shop_addr` VARCHAR (200) DEFAULT NULL ,
`shop_img` VARCHAR (1024) DEFAULT NULL ,
`advice` VARCHAR (255) DEFAULT NULL ,
`priority` int(2) DEFAULT '0' ,
`phone` VARCHAR (128) DEFAULT NULL ,
`enable_status` int(2) NOT NULL DEFAULT '0',
`create_time` datetime DEFAULT NULL,
`last_edit_time` datetime DEFAULT NULL,
PRIMARY KEY (shop_id)
--   KEY `fk_shop_area` (area_id),
--   KEY `fk_shop_profile` (owner_id),
--   KEY `fk_shop_shopcate` (shop_category_id),
-- CONSTRAINT `K=fk_shop_area` FOREIGN KEY (area_id) REFERENCES `tb_area`(area_id),
-- CONSTRAINT `K=fk_shop_profile` FOREIGN KEY (owner_id) REFERENCES `tb_user`(user_id),
-- CONSTRAINT `K=fk_shop_shopcate` FOREIGN KEY (shop_category_id) REFERENCES `tb_shop_category`(shop_category_id)
)ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='商铺表';



DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category`(
`product_category_id` int(11) NOT NULL AUTO_INCREMENT,
`product_category_name` VARCHAR (100) DEFAULT NULL,
`priority` int(2) DEFAULT '0' ,
`shop_id` int(20)NOT NULL DEFAULT '0' ,
`create_time` datetime DEFAULT NULL,
PRIMARY KEY (product_category_id)
-- CONSTRAINT `K=fk_prodcate_shop` FOREIGN KEY (shop_id) REFERENCES `tb_shop`(shop_id)
)ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='商品类别表';



DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
--   KEY `fk_product_procate` (product_category_id),
  -- KEY `fk_product_shop` (shop_id),
  -- CONSTRAINT `fk_product_procate` FOREIGN KEY (product_category_id) REFERENCES `tb_product_category` (product_category_id),
  -- CONSTRAINT `fk_product_shop` FOREIGN KEY (shop_id) REFERENCES `tb_shop` (shop_id)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`)
  -- KEY `fk_proimg_product` (product_id),
  -- CONSTRAINT `fk_proimg_product` FOREIGN KEY (product_id) REFERENCES `tb_product` (product_id)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;






