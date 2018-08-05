/*
 navicat premium data transfer

 source server         : 本地
 source server type    : mysql
 source server version : 50717
 source host           : localhost
 source database       : mj

 target server type    : mysql
 target server version : 50717
 file encoding         : utf-8

 date: 02/04/2018 17:26:16 pm
*/

set names utf8;
set foreign_key_checks = 0;

-- ----------------------------
--  table structure for `zamenu`
-- ----------------------------
drop table if exists `zamenu`;
create table `zamenu` (
  `id` int(11) not null,
  `parid` int(11) not null,
  `name` varchar(20) not null,
  `code` varchar(20) not null,
  `path` varchar(50) not null,
  `icon` varchar(50) not null,
  `status` int(1) default null,
  `addtime` datetime not null,
  `adduser` varchar(20) not null,
  `updatetime` datetime default null,
  `updateuser` varchar(20) default null,
  primary key (`id`)
) engine=innodb default charset=utf8;

-- ----------------------------
--  records of `zamenu`
-- ----------------------------
begin;
insert into `zamenu` values ('1', '0', '系统管理', 'cdgl', 'cdgl', 'a.png', '1', '2018-01-25 20:54:28', 'admin', null, null), ('2', '1', '基本设置', 'jbsz', 'jbsz', 'b.png', '1', '2018-01-25 20:55:47', 'admin', null, null);
commit;

-- ----------------------------
--  table structure for `zapermission`
-- ----------------------------
drop table if exists `zapermission`;
create table `zapermission` (
  `id` int(11) not null auto_increment,
  `roleid` int(11) not null,
  `menuid` int(11) default null,
  `addtime` datetime not null,
  `adduser` varchar(255) not null,
  `updatetime` datetime default null,
  `updateuser` varchar(255) default null,
  primary key (`id`)
) engine=innodb auto_increment=2 default charset=utf8;

-- ----------------------------
--  records of `zapermission`
-- ----------------------------
begin;
insert into `zapermission` values ('1', '1', '1', '2018-01-27 14:26:20', 'admin', null, null);
commit;

-- ----------------------------
--  table structure for `zarole`
-- ----------------------------
drop table if exists `zarole`;
create table `zarole` (
  `id` int(11) not null auto_increment,
  `name` varchar(20) not null,
  `description` varchar(100) default null,
  `adduser` varchar(20) not null,
  `addtime` datetime not null,
  `updateuser` varchar(20) default null,
  `updatetime` datetime default null,
  primary key (`id`)
) engine=innodb auto_increment=2 default charset=utf8;

-- ----------------------------
--  records of `zarole`
-- ----------------------------
begin;
insert into `zarole` values ('1', '普通用户', '普通用户', 'admin', '2018-01-27 14:25:48', null, null);
commit;

-- ----------------------------
--  table structure for `zauser`
-- ----------------------------
drop table if exists `zauser`;
create table `zauser` (
  `id` int(11) not null auto_increment,
  `username` varchar(20) not null,
  `password` varchar(50) not null,
  `roleid` int(11) default null,
  `head` varchar(50) default null,
  `email` varchar(50) default null,
  `phone` varchar(20) default null,
  `addtime` datetime not null,
  `adduser` varchar(20) not null,
  `updatetime` datetime default null,
  `updateuser` varchar(20) default null,
  primary key (`id`)
) engine=innodb auto_increment=3 default charset=utf8;

-- ----------------------------
--  records of `zauser`
-- ----------------------------
begin;
insert into `zauser` values ('1', 'root', '21232f297a57a5a743894a0e4a801fc3', '1', null, null, null, '2018-01-14 12:36:32', 'admin', null, null), ('2', 'admin', 'admin', '1', null, null, null, '2018-01-27 15:02:19', 'admin', null, null);
commit;

set foreign_key_checks = 1;
