-- -------------------------------------------------------------------------------------------------
-- program name:   init-database.sql
-- function:       init datebase 创建数据库和对应表
-- author:         wangc
-- release date:   21 August, 2017
-- -------------------------------------------------------------------------------------------------


-- step 1 ------------------------------------------------------------------------------------------
-- create datebase
-- -------------------------------------------------------------------------------------------------
-- 创建数据库时,设置数据库的编码方式 
-- CHARACTER SET:指定数据库采用的字符集,utf8不能写成utf-8
-- COLLATE:指定数据库字符集的排序规则,utf8的默认排序规则为utf8_general_ci（通过show character set查看）
drop database if EXISTS CRMDB;
create database IF NOT EXISTS CRMDB CHARACTER SET utf8 COLLATE utf8_general_ci;
use CRMDB;

-- step 2 ------------------------------------------------------------------------------------------
-- create table supplier 供应商表
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tsupplier (
	supplier_id int AUTO_INCREMENT,
	supplier_name char(40) UNIQUE comment '供应商名称',
	is_checked tinyint(1) comment '是否审核通过 1通过 0未通过',
	create_time DATETIME comment '创建时间',
	modify_time DATETIME comment '修改时间',
	is_disable tinyint(1) NOT NULL DEFAULT 1 comment '是否可使用 1可使用 0不可使用',
	PRIMARY KEY ( supplier_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 3 ------------------------------------------------------------------------------------------
-- create table employee 员工表
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS temployee (
	employee_id int AUTO_INCREMENT,
	employee_name char(40) comment '员工姓名',
	role_id int comment '角色id 关联员工角色表 employee role',
	supplier_id int comment '所属供应商 关联供应商表supplier',
	employee_mobile char(20) UNIQUE comment '手机号',
	password text comment '密码',
	create_time DATETIME comment '创建时间',
	modify_time DATETIME comment '修改时间',
	is_disable tinyint(1) NOT NULL DEFAULT 1, -- 是否可使用 1可使用 0不可使用
	PRIMARY KEY ( employee_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 4 ------------------------------------------------------------------------------------------
-- create table 客户表 customer
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tcustomer (
	customer_id int AUTO_INCREMENT,
	customer_name char(40) UNIQUE, -- 客户名称
	owner_id int, -- 关联供应商表 tsupplier supplier_id
	creator_id int, -- 关联 员工表 employee_id
	create_time DATETIME, -- 创建时间
	modify_time DATETIME, -- 修改时间
	is_disable tinyint(1) NOT NULL DEFAULT 1, -- 是否可使用 1可使用 0不可使用
	PRIMARY KEY ( customer_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

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
	password char(40),  -- 密码
	customer_id int, -- 关联 客户表 customer_id
	is_disable tinyint(1) NOT NULL DEFAULT 1, -- 是否可使用 1可使用 0不可使用
	PRIMARY KEY ( contactor_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

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
	is_disable tinyint(1) NOT NULL DEFAULT 1, -- 是否可使用 1可使用 0不可使用
	PRIMARY KEY ( templet_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 7 ------------------------------------------------------------------------------------------
-- create table 样板状态历史表 templet status
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ttempletstatus (
	status_id int AUTO_INCREMENT,
	create_time DATETIME, -- 创建时间
	templet_id int, -- 样板id 关联样板表 templet
	change_info varchar(255), -- 变更信息
	PRIMARY KEY ( status_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

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
	is_disable tinyint(1) NOT NULL DEFAULT 1, -- 是否可使用 1可使用 0不可使用
	PRIMARY KEY ( order_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 9 ------------------------------------------------------------------------------------------
-- create table 订单状态变更表 order status
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS torderstatus (
	status_id int AUTO_INCREMENT,
	create_time DATETIME, -- 创建时间
	order_id int, -- 订单id 关联订单表 order
	change_info varchar(255), -- 变更信息
	PRIMARY KEY ( status_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 10 ------------------------------------------------------------------------------------------
-- create table 员工角色表 employee role
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS temployeerole (
	role_id  int, -- 角色id
	role_name varchar(20) UNIQUE, -- 角色名
	PRIMARY KEY ( role_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 11 ------------------------------------------------------------------------------------------
-- create table 联系人角色表 contactor role
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tcontactorrole (
	role_id  int, -- 角色id
	role_name varchar(20) UNIQUE, -- 角色名
	PRIMARY KEY ( role_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;

-- step 12 ------------------------------------------------------------------------------------------
-- create table 字典表 tdictionary
-- -------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tdictionary (
	dict_id int AUTO_INCREMENT, -- 字典id
	dict_key varchar(20), -- 字典key
	dict_value varchar(255), -- 字典值
	dict_item varchar(20), -- 字典间 的 区别号码
	PRIMARY KEY ( dict_id )
)CHARACTER SET utf8 COLLATE utf8_general_ci;