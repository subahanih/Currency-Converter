#-------------------------------------------------------------------------------#
#--- Author: Mahaboob Subahan J                                              ---#
#--- Application: Currency Converter                                         ---#
#--- Schema: currency_converter                                               ---#
#--- Created Date: 06-December-2019                                          ---#
#--- Purpose: We need to create the following tables in the database         ---#
#--- for internal purposes of currency converter Java Assignment             ---#
#-------------------------------------------------------------------------------#
CREATE SCHEMA IF NOT EXISTS `currency_converter` DEFAULT CHARACTER SET utf8 ;

-- CREATE QUERY
-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_access_token`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`oauth_access_token` (
  `token_id` 			VARCHAR(255)	NULL 	DEFAULT NULL,
  `token` 				BLOB 			NULL	DEFAULT NULL,
  `authentication_id` 	VARCHAR(255) 	NULL	DEFAULT NULL,
  `user_name` 			VARCHAR(255) 	NULL 	DEFAULT NULL,
  `client_id` 			VARCHAR(255) 	NULL 	DEFAULT NULL,
  `authentication` 		BLOB 			NULL 	DEFAULT NULL,
  `refresh_token` 		VARCHAR(255) 	NULL 	DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_approvals`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`oauth_approvals` (
  `user_id` 			VARCHAR(255) 	NULL 		DEFAULT NULL,
  `client_id` 			VARCHAR(255) 	NULL 		DEFAULT NULL,
  `scope` 				VARCHAR(255) 	NULL 		DEFAULT NULL,
  `status` 				VARCHAR(10) 	NULL 		DEFAULT NULL,
  `expires_at` 			TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_at` 	TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_client_details`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`oauth_client_details` (
  `client_id` 				VARCHAR(255) 	NOT NULL,	
  `resource_ids` 			VARCHAR(255) 	NULL		DEFAULT NULL,
  `client_secret` 			VARCHAR(255) 	NULL		DEFAULT NULL,
  `scope` 					VARCHAR(255) 	NULL		DEFAULT NULL,
  `authorized_grant_types` 	VARCHAR(255) 	NULL		DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(255) 	NULL		DEFAULT NULL,
  `authorities` 			VARCHAR(255) 	NULL		DEFAULT NULL,
  `access_token_validity` 	INT(11) 		NULL		DEFAULT NULL,
  `refresh_token_validity` 	INT(11) 		NULL		DEFAULT NULL,
  `additional_information` 	VARCHAR(4096) 	NULL		DEFAULT NULL,
  `autoapprove` 			TINYINT(4) 	NULL 		DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_client_token`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`oauth_client_token` (
  `token_id` 			VARCHAR(255) 	NULL 	DEFAULT NULL,
  `token` 				BLOB 			NULL 	DEFAULT NULL,
  `authentication_id` 	VARCHAR(255) 	NULL	DEFAULT NULL,
  `user_name` 			VARCHAR(255) 	NULL 	DEFAULT NULL,
  `client_id` 			VARCHAR(255) 	NULL 	DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_code`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`oauth_code` (
  `code` 			VARCHAR(255) 	NULL 	DEFAULT NULL,
  `authentication` 	BLOB 			NULL 	DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_refresh_token`
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `currency_converter`.`oauth_refresh_token` (
  `token_id` 		VARCHAR(255) 	NULL 	DEFAULT NULL,
  `token` 			BLOB 			NULL 	DEFAULT NULL,
  `authentication` 	BLOB 			NULL 	DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------------------------------------

-- INSERT QUERY
-- ------------------------------------------------------------------------------
-- Table `currency_converter`.`oauth_client_details`
-- ------------------------------------------------------------------------------
INSERT INTO oauth_client_details(
	client_id
	, resource_ids
	, client_secret
	, scope
	, authorized_grant_types
	, authorities
	, access_token_validity
	, refresh_token_validity)
VALUES (
	'client-currconverter'
	, 'resource-currconverter'
	, '{noop}client-currconverter'
	, 'read,write'
	, 'password,authorization_code,refresh_token,implicit'
	, 'USER'
	, 10800
	, 2592000);