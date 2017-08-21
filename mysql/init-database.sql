-- -------------------------------------------------------------------------------------------------
-- program name:   init-database.sql
-- function:       init datebase 创建数据库和对应表
-- author:         Zhuyz
-- release date:   24 May, 2016
-- -------------------------------------------------------------------------------------------------


-- step 1 ------------------------------------------------------------------------------------------
-- create datebase
-- -------------------------------------------------------------------------------------------------
Create Database If Not Exists CRMDB;
use CRMDB;

-- step 2 ------------------------------------------------------------------------------------------
-- create table supplier 供应商表
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tsupplier (
	supplier_id int AUTO_INCREMENT,
	supplier_name char(40) UNIQUE, -- 供应商名称
	owner_id int, -- 关联员工表
	is_checked tinyint(1), -- 是否审核通过 1通过 0未通过
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	PRIMARY KEY ( supplier_id )
);

-- step 3 ------------------------------------------------------------------------------------------
-- create table employee 员工表
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS temployee (
	employee_id int AUTO_INCREMENT,
	employee_name char(40), -- 员工姓名
	role_id int, -- 角色id 关联员工角色表 employee role
	supplier_id int, -- 所属供应商 关联供应商表supplier
	employee_mobile char(20) UNIQUE, -- 手机号
	login_token char(20) UNIQUE, -- 登录校验token
	password char(40),  -- 密码
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	PRIMARY KEY ( employee_id )
);

-- step 4 ------------------------------------------------------------------------------------------
-- create table 客户表 customer
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tcustomer (
	customer_id int AUTO_INCREMENT,
	customer_name char(40) UNIQUE, -- 客户名称
	owner_id int, -- 关联联系人表 contactor
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	PRIMARY KEY ( customer_id )
);

-- step 5 ------------------------------------------------------------------------------------------
-- create table 联系人表 contactor
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tcontactor (
	contactor_id int AUTO_INCREMENT,
	contactor_name char(40),
	role_id int, -- 角色id 关联联系人角色表 employee role
	contactor_mobile char(20) UNIQUE, -- 手机号
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	login_token char(20) UNIQUE, -- 登录校验token
	password char(40),  -- 密码
	PRIMARY KEY ( contactor_id )
);

-- step 6 ------------------------------------------------------------------------------------------
-- create table 样板表 templet
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ttemplet (
	templet_id int AUTO_INCREMENT,
	supplier_id int, -- 供应商id 关联供应商表
	customer_id int,-- 客户id 关联客户表
	contactor_id int, -- 联系人id 关联联系人表contactor
	goods_id char(40), -- 实物编号
	templet_require text, -- 要求
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	created_id int, -- 创建人id  关联员工表 employee
	status varchar(40), -- 当前状态
	PRIMARY KEY ( templet_id )
);

-- step 7 ------------------------------------------------------------------------------------------
-- create table 样板状态历史表 templet status
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ttempletstatus (
	status_id int AUTO_INCREMENT,
	create_time DATETIME, -- 创建时间
	templet_id int, -- 样板id 关联样板表 templet
	change_info varchar(255), -- 变更信息
	PRIMARY KEY ( status_id )
);

-- step 8 ------------------------------------------------------------------------------------------
-- create table 订单表 order
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS torder (
	order_id int AUTO_INCREMENT,
	supplier_id int, -- 供应商id 关联供应商表
	customer_id int,-- 客户id 关联客户表
	order_num int, -- 数量
	style_number varchar(40), -- 款号
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	created_id int, -- 创建人id  关联员工表 employee
	status varchar(40), -- 当前状态
	PRIMARY KEY ( order_id )
);

-- step 9 ------------------------------------------------------------------------------------------
-- create table 订单状态变更表 order status
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS torderstatus (
	status_id int AUTO_INCREMENT,
	create_time DATETIME, -- 创建时间
	order_id int, -- 订单id 关联订单表 order
	change_info varchar(255), -- 变更信息
	PRIMARY KEY ( status_id )
);

-- step 10 ------------------------------------------------------------------------------------------
-- create table 员工角色表 employee role
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS temployeerole (
	role_id  int AUTO_INCREMENT, -- 角色id
	role_name varchar(20) UNIQUE, -- 角色名
	PRIMARY KEY ( role_id )
);

-- step 11 ------------------------------------------------------------------------------------------
-- create table 联系人角色表 contactor role
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tcontactorrole (
	role_id  int AUTO_INCREMENT, -- 角色id
	role_name varchar(20) UNIQUE, -- 角色名
	PRIMARY KEY ( role_id )
);