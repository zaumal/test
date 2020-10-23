CREATE TABLE
    test_dmeo.test_mybatis_user
    (
        id INT unsigned NOT NULL AUTO_INCREMENT,
        name VARCHAR(20) COMMENT '姓名',
        age INT COMMENT '年龄',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci COMMENT='test table';
    
CREATE TABLE
    test_mybatis_apple
    (
        id INT unsigned NOT NULL,
        color VARCHAR(20) COMMENT '颜色',
        size INT COMMENT '大小',
        belong INT COMMENT '属于哪个people',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci;
insert into `test_demo`.`test_mybatis_apple`(`id`,`color`,`size`,`belong`) values
(101,'red',80,201),(102,'red',90,202),(103,'red',70,203),
(104,'green',80,204),(105,'green',90,205),(106,'green',70,206),
(107,'green',60,207)

CREATE TABLE
    test_mybatis_people
    (
        id INT unsigned NOT NULL,
        name VARCHAR(20) COMMENT '姓名',
        sex VARCHAR(20) COMMENT '性别',
        eat INT COMMENT '咬了哪个apple',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci;
insert into `test_demo`.`test_mybatis_people`(`id`,`name`,`sex`,`eat`) values
(201,'zhangsan','男',101),(202,'lisi','女',102),(203,'王五','男',102),
(204,'李六','男',102),(205,'jiayeuting','男',104),(206,'guotaim','女',103),
(207,'lijiaiq','女',104)