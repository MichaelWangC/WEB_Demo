-- -------------------------------------------------------------------------------------------------
-- program name:   test-data.sql
-- function:       test date 插入数据 用于测试
-- author:         wangc
-- release date:   24 May, 2016
-- -------------------------------------------------------------------------------------------------

-- step 1 ------------------------------------------------------------------------------------------
-- create table supplier 供应商表
-- -------------------------------------------------------------------------------------------------
insert into tsupplier(
	supplier_id,
	supplier_name,
	owner_id,
	is_checked,
	create_time,
	modify_time) values(
		1,
		"巨人网络",
		1,
		0,
		sysdate(),
		sysdate());
-- step 2 ------------------------------------------------------------------------------------------
-- create table employee 员工表
-- -------------------------------------------------------------------------------------------------
insert into temployee(
	employee_id,
	employee_name,
	role_id,
	supplier_id,
	employee_mobile,
	login_token,
	password,
	create_time,
	modify_time) values(
		1,
		"李巨人",
		1,
		1,
		"18667666278",
		'',
		'',
		sysdate(),
		sysdate());
