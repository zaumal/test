--创建数据库
create database test_demo;

--创建库存表
CREATE TABLE `test_demo`.`second_kill_stock` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `count` int NOT NULL COMMENT '库存',
  `sale` int NOT NULL COMMENT '已售',
  `version` int NOT NULL COMMENT '乐观锁，版本号',
  PRIMARY KEY (`id`)
) COMMENT '库存表' ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--创建订单表
CREATE TABLE `test_demo`.`second_kill_order` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sid` int NOT NULL COMMENT '库存ID',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT '订单表' ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;

--插入库存
insert into `test_demo`.`second_kill_stock`(name,count,sale,version) value('手机',10,0,1);
