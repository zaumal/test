CREATE database ssd1;

DROP TABLE IF EXISTS ssd1.t_user0;

CREATE TABLE
    ssd1.t_user0
    (
        id bigint NOT NULL AUTO_INCREMENT,
        age INT,
        name VARCHAR(100),
        is_deleted INT,
        create_time DATETIME,
        last_update_time DATETIME,
        PRIMARY KEY (id)
    );
    
DROP TABLE IF EXISTS ssd1.t_user1;

CREATE TABLE
    ssd1.t_user1
    (
        id bigint NOT NULL AUTO_INCREMENT,
        age INT,
        name VARCHAR(100),
        is_deleted INT,
        create_time DATETIME,
        last_update_time DATETIME,
        PRIMARY KEY (id)
    );
    
CREATE database ssd2;

DROP TABLE IF EXISTS ssd2.t_user0;

CREATE TABLE
    ssd2.t_user0
    (
        id bigint NOT NULL AUTO_INCREMENT,
        age INT,
        name VARCHAR(100),
        is_deleted INT,
        create_time DATETIME,
        last_update_time DATETIME,
        PRIMARY KEY (id)
    );
    
DROP TABLE IF EXISTS ssd2.t_user1;

CREATE TABLE
    ssd2.t_user1
    (
        id bigint NOT NULL AUTO_INCREMENT,
        age INT,
        name VARCHAR(100),
        is_deleted INT,
        create_time DATETIME,
        last_update_time DATETIME,
        PRIMARY KEY (id)
    );