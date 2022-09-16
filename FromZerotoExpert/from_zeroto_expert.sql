DROP TABLE IF EXISTS user;

CREATE TABLE user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username varchar(255) DEFAULT(NULL),
	password varchar(255) DEFAULT(NULL),
	email varchar(255) DEFAULT(NULL),
	salt varchar(255) DEFAULT(NULL),
	activeStatus INT DEFAULT(0)
)ENGINE=INNODB;


DROP TABLE IF EXISTS disallow_word;

CREATE TABLE disallow_word(
	id INT PRIMARY KEY AUTO_INCREMENT,
	value varchar(255) NOT NULL,
	type INT DEFAULT(0)
)ENGINE=INNODB;

INSERT INTO disallow_word(value) VALUES("尼玛");
INSERT INTO disallow_word(value) VALUES("站长");
INSERT INTO disallow_word(value) VALUES("国家领导人");
INSERT INTO disallow_word(value) VALUES("操");