DROP TABLE IF EXISTS user;

CREATE TABLE user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username varchar(255) DEFAULT(NULL),
	password varchar(255) DEFAULT(NULL),
	email varchar(255) DEFAULT(NULL),
	salt varchar(255) DEFAULT(NULL),
	activeStatus INT DEFAULT(0)
)ENGINE=INNODB;