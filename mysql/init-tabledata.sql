-- -------------------------------------------------------------------------------------------------
-- program name:   init-tabledata.sql
-- function:       init table data  初始化数据
-- author:         wangc
-- release date:   23 August, 2017
-- -------------------------------------------------------------------------------------------------

-- step 1 ------------------------------------------------------------------------------------------
-- create table 员工角色表 employee role
-- -------------------------------------------------------------------------------------------------
insert into temployeerole(role_id, role_name) values(1, '领导');
insert into temployeerole(role_id, role_name) values(2, '员工');

-- step 2 ------------------------------------------------------------------------------------------
-- create table 联系人角色表 contactor role
-- -------------------------------------------------------------------------------------------------
insert into tcontactorrole(role_id, role_name) values(1, '领导');
insert into tcontactorrole(role_id, role_name) values(2, '员工');

-- step 3 ------------------------------------------------------------------------------------------
-- create table 字典
-- -------------------------------------------------------------------------------------------------
-- insert into tdictionary(dict_key, dict_value, dict_item) values('#','联系人等级','1001');
-- insert into tdictionary(dict_key, dict_value, dict_item) values('1','领导','1001');
-- insert into tdictionary(dict_key, dict_value, dict_item) values('2','员工','1001');