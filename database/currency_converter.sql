#-------------------------------------------------------------------------------#
#--- Author: Mahaboob Subahan J                                              ---#
#--- Application: Currency Converter                                         ---#
#--- Schema: currency_converter                                               ---#
#--- Created Date: 06-December-2019                                          ---#
#--- Purpose: We need to create the following tables in the database         ---#
#--- for internal purposes of currency converter Java Assignment             ---#
#-------------------------------------------------------------------------------#
CREATE SCHEMA IF NOT EXISTS `currency_converter` DEFAULT CHARACTER SET utf8 ;
USE currency_converter;

-- CREATE QUERY
-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_authority`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`m_authority` (
   authority_id  	INT				NOT NULL 	AUTO_INCREMENT,
   authority_name 	VARCHAR(50)		NOT NULL,
   PRIMARY KEY (authority_id),
   CONSTRAINT `uc_authority_authorityname` UNIQUE (authority_name)
);

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_role`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`m_role` (
   role_id  		INT			 	NOT NULL 	AUTO_INCREMENT,
   role_name 		VARCHAR(50)		NOT NULL,
   PRIMARY KEY (role_id),
   CONSTRAINT `uc_role_rolename` UNIQUE (role_name)
);

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_user`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`m_user` (
   user_id  			INT(11) 		NOT NULL AUTO_INCREMENT,
   role_id  			INT(11) 		NOT NULL,
   account_expired 		TINYINT			NOT NULL,
   account_locked 		TINYINT			NOT NULL,
   credentials_expired 	TINYINT			NOT NULL,
   base_user 			TINYINT			NOT NULL,
   enabled 				TINYINT			NOT NULL,
   password 			VARCHAR(255)	NOT NULL,
   username 			VARCHAR(255)	NOT NULL,	
   created_date 		TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
   updated_date 		TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
   updated_by 			VARCHAR(255),
   PRIMARY KEY (user_id),
   CONSTRAINT `uc_user_username` UNIQUE (username),
   CONSTRAINT `fk_user_roleid` FOREIGN KEY (role_id) REFERENCES `m_role` (role_id)
);

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_user_authority`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`m_user_authority` (
   user_id 				INT(11)	 	NOT NULL,
   authority_id 		INT(11)	 	NOT NULL,
   CONSTRAINT `fk_userauthority_userid` FOREIGN KEY (user_id) REFERENCES `m_user` (user_id),
   CONSTRAINT `fk_userauthority_authorityid` FOREIGN KEY (authority_id) REFERENCES `m_authority` (authority_id)
);

-- INSERT QUERY
-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_authority`
-- ------------------------------------------------------------------------------
INSERT INTO m_authority(authority_id, authority_name) VALUES (1, 'admin');
INSERT INTO m_authority(authority_id, authority_name) VALUES (3, 'admin-read-user');
INSERT INTO m_authority(authority_id, authority_name) VALUES (4, 'admin-read-currency');
INSERT INTO m_authority(authority_id, authority_name) VALUES (2, 'user');
INSERT INTO m_authority(authority_id, authority_name) VALUES (5, 'user-read-currency');

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_role`
-- ------------------------------------------------------------------------------
INSERT INTO m_role(role_id, role_name) VALUES (1, 'Admin');
INSERT INTO m_role(role_id, role_name) VALUES (2, 'User');

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_user`
-- ------------------------------------------------------------------------------
INSERT INTO m_user( 
	user_id
	, role_id
	, account_expired
	, account_locked
	, credentials_expired
	, base_user
	, enabled
	, password
	, username
	, created_date
	, updated_date
	, updated_by ) 
VALUES (
	1
	, 1
	, false
	, false
	, false
	, true
	, true
	, '{noop}admin'
	, 'admin'
	, '2019-12-06 23:34:04'
	, '2019-12-06 23:34:04'
	, 'Mahaboob Subahan'
);

INSERT INTO m_user( 
	user_id
	, role_id
	, account_expired
	, account_locked
	, credentials_expired
	, base_user
	, enabled
	, password
	, username
	, created_date
	, updated_date
	, updated_by ) 
VALUES (
	2
	, 2
	, false
	, false
	, false
	, true
	, true
	, '{noop}user'
	, 'user'
	, '2019-12-06 23:34:04'
	, '2019-12-06 23:34:04'
	, 'Mahaboob Subahan'
);
	
-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`m_user_authority`
-- ------------------------------------------------------------------------------
INSERT INTO m_user_authority(user_id, authority_id) VALUES (1, 1);
INSERT INTO m_user_authority(user_id, authority_id) VALUES (1, 3);
INSERT INTO m_user_authority(user_id, authority_id) VALUES (1, 4);
INSERT INTO m_user_authority(user_id, authority_id) VALUES (2, 2);
INSERT INTO m_user_authority(user_id, authority_id) VALUES (2, 5);