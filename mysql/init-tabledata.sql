-- -------------------------------------------------------------------------------------------------
-- program name:   init-tabledata.sql
-- function:       init table data  初始化数据
-- author:         wangc
-- release date:   23 August, 2017
-- -------------------------------------------------------------------------------------------------

-- step 1 ------------------------------------------------------------------------------------------
-- create table 员工角色表 employee role
-- -------------------------------------------------------------------------------------------------
insert into temployeerole(role_name) values('Agent');
insert into temployeerole(role_name) values('Admins');
insert into temployeerole(role_name) values('Members');

-- step 2 ------------------------------------------------------------------------------------------
-- create table 联系人角色表 contactor role
-- -------------------------------------------------------------------------------------------------
insert into tcontactorrole(role_name) values('Agent');
insert into tcontactorrole(role_name) values('Admins');
insert into tcontactorrole(role_name) values('Members');