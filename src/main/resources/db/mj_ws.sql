/*
 navicat premium data transfer

 source server         : mj
 source server type    : mysql
 source server version : 50641
 source host           : 132.232.22.70
 source database       : mj_ws

 target server type    : mysql
 target server version : 50641
 file encoding         : utf-8

 date: 08/09/2018 16:02:53 pm
*/

set names utf8;
set foreign_key_checks = 0;

-- ----------------------------
--  table structure for `zcchatroom`
-- ----------------------------
drop table if exists `zcchatroom`;
create table `zcchatroom` (
  `id` bigint(20) not null,
  `name` varchar(50) collate utf8_bin not null,
  `notice` varchar(400) collate utf8_bin not null,
  `memberid` bigint(20) not null,
  `gag` int(11) default null,
  `privatechat` int(11) default null,
  `memo` varchar(400) collate utf8_bin default null,
  `prop1` varchar(50) collate utf8_bin default null,
  `prop2` varchar(50) collate utf8_bin default null,
  `prop3` varchar(200) collate utf8_bin default null,
  `prop4` varchar(200) collate utf8_bin default null,
  `adduser` varchar(50) collate utf8_bin not null,
  `addtime` datetime not null,
  `modifyuser` varchar(50) collate utf8_bin default null,
  `modifytime` datetime default null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_bin;

-- ----------------------------
--  records of `zcchatroom`
-- ----------------------------
begin;
insert into `zcchatroom` values ('1', '聊天室', '1', '55', '0', '0', null, null, null, null, null, 'admin', '2018-03-16 16:04:03', null, '2018-03-23 13:27:03'), ('2', '聊天室2', '聊天室2', '55', '0', '0', null, null, null, null, null, 'admin', '2018-03-16 16:04:19', null, '2018-03-23 13:27:03'), ('3', '聊天室3', '3', '55', '0', '0', null, null, null, null, null, 'admin', '2018-03-16 16:40:43', null, '2018-03-23 13:27:03'), ('4', '聊天室4', '4', '55', '0', '0', null, null, null, null, null, 'admin', '2018-03-16 16:40:53', null, '2018-03-23 13:27:03'), ('5', '聊天室5', '<p>1</p>', '57', '0', '0', null, null, null, null, null, 'admin', '2018-03-16 16:41:06', 'admin', '2018-03-23 13:27:03');
commit;

-- ----------------------------
--  table structure for `zcchatroommember`
-- ----------------------------
drop table if exists `zcchatroommember`;
create table `zcchatroommember` (
  `memberid` varchar(50) collate utf8_bin not null,
  `chatroomid` varchar(50) collate utf8_bin not null,
  `membertype` varchar(10) collate utf8_bin default null,
  `gag` int(11) default null,
  `prohibit` int(11) default null,
  `prop1` varchar(50) collate utf8_bin default null,
  `prop2` varchar(50) collate utf8_bin default null,
  `prop3` varchar(200) collate utf8_bin default null,
  `prop4` varchar(200) collate utf8_bin default null,
  `adduser` varchar(50) collate utf8_bin not null,
  `addtime` datetime not null,
  `modifyuser` varchar(50) collate utf8_bin default null,
  `modifytime` datetime default null,
  primary key (`memberid`,`chatroomid`)
) engine=innodb default charset=utf8 collate=utf8_bin;

-- ----------------------------
--  records of `zcchatroommember`
-- ----------------------------
begin;
insert into `zcchatroommember` values ('56', '1', 'assistant', '0', '0', null, null, null, null, 'benxiaohai', '2018-04-08 18:12:15', 'benxiaohai', '2018-04-09 15:09:07'), ('56', '3', 'common', '0', '0', null, null, null, null, 'benxiaohai', '2018-03-30 16:07:23', 'benxiaohai', '2018-03-30 16:07:31'), ('57', '1', 'common', '0', '0', null, null, null, null, 'zhangyc', '2018-04-08 18:42:50', 'benxiaohai', '2018-04-09 15:10:09'), ('57', '3', 'assistant', '0', '0', null, null, null, null, 'benxiaohai', '2018-03-30 15:08:06', 'benxiaohai', '2018-03-30 16:20:07'), ('58', '3', 'assistant', '0', '0', null, null, null, null, 'benxiaohai', '2018-03-30 14:31:56', 'benxiaohai', '2018-03-30 14:35:12'), ('60', '3', null, '0', '1', null, null, null, null, 'benxiaohai', '2018-03-30 14:32:34', 'xiongdan', '2018-03-30 14:34:57'), ('60', '4', 'common', '0', '0', null, null, null, null, 'xiongdan', '2018-03-30 14:37:19', 'benxiaohai', '2018-03-30 19:06:21'), ('63', '3', null, '0', '0', null, null, null, null, 'dinghu', '2018-03-30 14:35:11', 'xiongdan', '2018-03-30 14:35:26'), ('64', '3', null, '1', '0', null, null, null, null, 'xiongdan', '2018-03-30 14:35:36', null, null);
commit;

-- ----------------------------
--  table structure for `zcmessagerecord`
-- ----------------------------
drop table if exists `zcmessagerecord`;
create table `zcmessagerecord` (
  `id` bigint(20) not null,
  `chatroomid` varchar(50) collate utf8_bin not null,
  `frommemberid` bigint(20) default null,
  `fromnick` varchar(50) collate utf8_bin default null,
  `fromavator` varchar(100) collate utf8_bin default null,
  `fromusername` varchar(50) collate utf8_bin default null,
  `message` varchar(1000) collate utf8_bin default null,
  `msgtype` varchar(20) collate utf8_bin default null,
  `msgtimestamp` varchar(20) collate utf8_bin default null,
  `memo2` varchar(400) collate utf8_bin default null,
  `prop5` varchar(50) collate utf8_bin default null,
  `prop6` varchar(50) collate utf8_bin default null,
  `prop7` varchar(200) collate utf8_bin default null,
  `prop8` varchar(200) collate utf8_bin default null,
  `adduser` varchar(50) collate utf8_bin not null,
  `addtime` datetime not null,
  `modifyuser` varchar(50) collate utf8_bin default null,
  `modifytime` datetime default null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_bin;

-- ----------------------------
--  records of `zcmessagerecord`
-- ----------------------------
begin;
insert into `zcmessagerecord` values ('1214', '1', '55', '', '头像', 'benxiaohai', '  菜菜', null, '1523271553191', null, null, null, null, null, 'system', '2018-04-09 18:59:13', null, null), ('1215', '1', '55', '', '头像', 'benxiaohai', '菜菜', null, '1523271576014', null, null, null, null, null, 'system', '2018-04-09 18:59:36', null, null), ('1216', '1', '55', '', '头像', 'benxiaohai', '菜菜', null, '1523271585029', null, null, null, null, null, 'system', '2018-04-09 18:59:45', null, null), ('1217', '1', '55', '', '头像', 'benxiaohai', '1', null, '1523271587317', null, null, null, null, null, 'system', '2018-04-09 18:59:47', null, null), ('1218', '3', '58', '不知道', '头像', 'celina', 'im/3/24946_120x120.png', 'imagemessage', '1523271850560', null, null, null, null, null, 'system', '2018-04-09 19:04:11', null, null), ('1219', '3', '58', '不知道', '头像', 'celina', 'im/3/549946.zip', 'filemessage', '1523271946762', null, null, null, null, null, 'system', '2018-04-09 19:05:47', null, null), ('1220', '3', '58', '不知道', '头像', 'celina', 'im/3/1d33492087345f0b74cc1071b97ffad3.jpg', 'imagemessage', '1523271959603', null, null, null, null, null, 'system', '2018-04-09 19:06:00', null, null), ('1221', '3', '58', '不知道', '头像', 'celina', 'im/3/孙子涵 - 这孤独世界幸好有你 (《这孤独世界，幸好有你》同名书主题曲).mp3', 'audiomessage', '1523271973886', null, null, null, null, null, 'system', '2018-04-09 19:06:14', null, null), ('1222', '3', '58', '不知道', '头像', 'celina', 'im/3/25648.mp4', 'videomessage', '1523271986208', null, null, null, null, null, 'system', '2018-04-09 19:06:26', null, null), ('1223', '3', '58', '不知道', '头像', 'celina', 'l;\'l\';lyhhhhhh\n\n\n', null, '1523272030512', null, null, null, null, null, 'system', '2018-04-09 19:07:11', null, null), ('1224', '3', '58', '不知道', '头像', 'celina', '啦啦啦啦\n', null, '1523272036455', null, null, null, null, null, 'system', '2018-04-09 19:07:16', null, null), ('1225', '3', '55', '', '头像', 'benxiaohai', '1', null, '1523443889127', null, null, null, null, null, 'system', '2018-04-11 18:51:29', null, null), ('1226', '3', '55', '', '头像', 'benxiaohai', 'im/3/we.jpg', 'imagemessage', '1523443893957', null, null, null, null, null, 'system', '2018-04-11 18:51:34', null, null), ('1227', '3', '55', '', '头像', 'benxiaohai', 'im/3/神马乐团-爱河.mp3', 'audiomessage', '1523443903095', null, null, null, null, null, 'system', '2018-04-11 18:51:43', null, null), ('1228', '3', '55', '', '头像', 'benxiaohai', '123', null, '1523443912622', null, null, null, null, null, 'system', '2018-04-11 18:51:53', null, null), ('1229', '3', '55', '', '头像', 'benxiaohai', 'im/3/1.mp4', 'videomessage', '1523443917770', null, null, null, null, null, 'system', '2018-04-11 18:51:58', null, null), ('1230', '3', '55', '', '头像', 'benxiaohai', 'im/3/we.jpg.zip', 'filemessage', '1523443923354', null, null, null, null, null, 'system', '2018-04-11 18:52:03', null, null), ('1231', '2', '55', '', '头像', 'benxiaohai', '1', null, '1523522227325', null, null, null, null, null, 'system', '2018-04-12 16:37:07', null, null), ('1232', '2', '55', '', '头像', 'benxiaohai', 'im\\2\\tim图片20180412142905.png', 'imagemessage', '1523522235807', null, null, null, null, null, 'system', '2018-04-12 16:37:16', null, null), ('1233', '2', '55', '', '头像', 'benxiaohai', 'im\\2\\tim图片20180412142905.png', 'imagemessage', '1523522317614', null, null, null, null, null, 'system', '2018-04-12 16:38:38', null, null), ('1245', '1', '55', '', '头像', 'benxiaohai', '1', null, '1524043900341', null, null, null, null, null, 'system', '2018-04-18 17:31:40', null, null), ('1246', '1', '55', '', '头像', 'benxiaohai', '2', null, '1524043914244', null, null, null, null, null, 'system', '2018-04-18 17:31:54', null, null), ('1247', '1', '55', '', '头像', 'benxiaohai', '3', null, '1524043936291', null, null, null, null, null, 'system', '2018-04-18 17:32:16', null, null), ('1248', '1', '55', '', '头像', 'benxiaohai', '5', null, '1524043979625', null, null, null, null, null, 'system', '2018-04-18 17:33:00', null, null), ('1249', '1', '55', '', '头像', 'benxiaohai', '6', null, '1524043983865', null, null, null, null, null, 'system', '2018-04-18 17:33:04', null, null), ('1250', '1', '55', '', '头像', 'benxiaohai', 'null/${chatroomid}/we.jpg', 'imagemessage', '1524046159695', null, null, null, null, null, 'system', '2018-04-18 18:09:20', null, null), ('1251', '1', '55', '', '头像', 'benxiaohai', 'null/1/we.jpg', 'imagemessage', '1524046298942', null, null, null, null, null, 'system', '2018-04-18 18:11:39', null, null), ('1252', '1', '55', '', '头像', 'benxiaohai', 'im/1/we.jpg', 'imagemessage', '1524046411818', null, null, null, null, null, 'system', '2018-04-18 18:13:32', null, null), ('1253', '1', '55', '', '头像', 'benxiaohai', 'im/1/神马乐团-爱河.mp3', 'audiomessage', '1524046417645', null, null, null, null, null, 'system', '2018-04-18 18:13:38', null, null), ('1254', '1', '55', '', '头像', 'benxiaohai', 'im/1/1.mp4', 'videomessage', '1524046424524', null, null, null, null, null, 'system', '2018-04-18 18:13:45', null, null), ('1255', '1', '55', '', '头像', 'benxiaohai', 'im/1/we.jpg.zip', 'filemessage', '1524046429451', null, null, null, null, null, 'system', '2018-04-18 18:13:49', null, null), ('1256', '1', '55', '', '头像', 'benxiaohai', 'im/1/神马乐团-爱河.mp3', 'audiomessage', '1524105377351', null, null, null, null, null, 'system', '2018-04-19 10:36:17', null, null), ('1257', '1', '55', '', '头像', 'benxiaohai', '123', null, '1525837871421', null, null, null, null, null, 'system', '2018-05-09 11:51:11', null, null), ('1258', '1', '55', '', '头像', 'benxiaohai', '123', null, '1525837875828', null, null, null, null, null, 'system', '2018-05-09 11:51:16', null, null), ('1259', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837891435', null, null, null, null, null, 'system', '2018-05-09 11:51:31', null, null), ('1260', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837891996', null, null, null, null, null, 'system', '2018-05-09 11:51:32', null, null), ('1261', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837892187', null, null, null, null, null, 'system', '2018-05-09 11:51:32', null, null), ('1262', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837892371', null, null, null, null, null, 'system', '2018-05-09 11:51:32', null, null), ('1263', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837892539', null, null, null, null, null, 'system', '2018-05-09 11:51:33', null, null), ('1264', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837892732', null, null, null, null, null, 'system', '2018-05-09 11:51:33', null, null), ('1265', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837892955', null, null, null, null, null, 'system', '2018-05-09 11:51:33', null, null), ('1266', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837893139', null, null, null, null, null, 'system', '2018-05-09 11:51:33', null, null), ('1267', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837893307', null, null, null, null, null, 'system', '2018-05-09 11:51:33', null, null), ('1268', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837893483', null, null, null, null, null, 'system', '2018-05-09 11:51:33', null, null), ('1269', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837893643', null, null, null, null, null, 'system', '2018-05-09 11:51:34', null, null), ('1270', '1', '55', '', '头像', 'benxiaohai', '', null, '1525837893788', null, null, null, null, null, 'system', '2018-05-09 11:51:34', null, null), ('1271', '1', '55', '', '头像', 'benxiaohai', '1', null, '1533123028375', null, null, null, null, null, 'system', '2018-08-01 19:30:28', null, null), ('1272', '1', '55', '', '头像', 'benxiaohai', '2', null, '1533123040462', null, null, null, null, null, 'system', '2018-08-01 19:30:40', null, null), ('1273', '1', '55', '', '头像', 'benxiaohai', '3', null, '1533123074396', null, null, null, null, null, 'system', '2018-08-01 19:31:14', null, null);
commit;

-- ----------------------------
--  table structure for `zcsensitiveword`
-- ----------------------------
drop table if exists `zcsensitiveword`;
create table `zcsensitiveword` (
  `id` bigint(20) not null,
  `name` varchar(50) collate utf8_bin not null,
  `count` int(11) not null,
  `prop1` varchar(50) collate utf8_bin default null,
  `prop2` varchar(50) collate utf8_bin default null,
  `prop3` varchar(200) collate utf8_bin default null,
  `prop4` varchar(200) collate utf8_bin default null,
  `adduser` varchar(50) collate utf8_bin not null,
  `addtime` datetime not null,
  `modifyuser` varchar(50) collate utf8_bin default null,
  `modifytime` datetime default null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_bin;

-- ----------------------------
--  records of `zcsensitiveword`
-- ----------------------------
begin;
insert into `zcsensitiveword` values ('1', '菜菜', '0', null, null, null, null, 'admin', '2018-03-16 17:20:14', null, null);
commit;

-- ----------------------------
--  table structure for `zacode`
-- ----------------------------
drop table if exists `zacode`;
create table `zacode` (
  `id` int(11) not null,
  `codetype` varchar(255) not null,
  `codevalue` varchar(255) not null,
  `parid` int(11) default null,
  `addtime` datetime not null,
  `adduser` varchar(255) not null,
  `updatetime` datetime default null,
  `updateuser` varchar(255) default null,
  primary key (`id`)
) engine=innodb default charset=utf8;

-- ----------------------------
--  table structure for `zamaxno`
-- ----------------------------
drop table if exists `zamaxno`;
create table `zamaxno` (
  `code` varchar(255) not null,
  `type` varchar(255) not null,
  `maxno` int(11) default null,
  `maxinnercode` varchar(255) default null,
  primary key (`code`,`type`)
) engine=innodb default charset=utf8;

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
  `orderflag` int(11) default '0',
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
insert into `zamenu` values ('1', '0', '系统管理', 'xtgl', 'xtgl', 'a.png', '1', '1', '2018-01-25 20:54:28', 'admin', null, null), ('2', '1', '用户管理', 'yhgl', 'yhgl', 'b.png', '1', '1', '2018-05-05 10:53:35', 'admin', null, null), ('3', '1', '菜单管理', 'cdgl', 'cdgl', 'a.png', '1', '2', '2018-05-05 10:54:37', 'admin', null, null), ('4', '1', '角色管理', 'jsgl', 'jsgl', 'a.png', '1', '3', '2018-05-05 10:55:40', 'admin', null, null), ('5', '0', '考试管理', 'ksgl', 'ksgl', 'b.png', '1', '2', '2018-05-09 22:07:30', 'admin', null, null);
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
) engine=innodb auto_increment=6 default charset=utf8;

-- ----------------------------
--  records of `zapermission`
-- ----------------------------
begin;
insert into `zapermission` values ('1', '1', '1', '2018-01-27 14:26:20', 'admin', null, null), ('2', '1', '2', '2018-05-05 10:56:40', 'admin', null, null), ('3', '1', '3', '2018-05-05 10:56:54', 'admin', null, null), ('4', '1', '4', '2018-05-05 10:57:08', 'admin', null, null), ('5', '1', '5', '2018-05-09 22:08:02', 'admin', null, null);
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
  `realname` varchar(255) default null,
  `head` varchar(50) default null,
  `email` varchar(50) default null,
  `phone` varchar(20) default null,
  `addtime` datetime not null,
  `adduser` varchar(20) not null,
  `updatetime` datetime default null,
  `updateuser` varchar(20) default null,
  primary key (`id`)
) engine=innodb auto_increment=8 default charset=utf8;

-- ----------------------------
--  records of `zauser`
-- ----------------------------
begin;
insert into `zauser` values ('1', 'root', '21232f297a57a5a743894a0e4a801fc3', '1', null, null, null, null, '2018-01-14 12:36:32', 'admin', null, null), ('2', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', null, null, null, null, '2018-01-27 15:02:19', 'admin', null, null), ('3', 'zhangsan', 'admin', '1', null, null, null, null, '2018-05-08 18:09:48', 'admin', null, null), ('4', 'lisi', 'admin', '1', null, null, null, null, '2018-05-08 18:13:43', 'admin', null, null), ('5', 'wangwu', 'admin', '1', null, null, null, null, '2018-05-08 18:14:03', 'admin', null, null), ('6', '1', '1', '1', null, null, null, null, '2018-05-08 18:32:22', 'admin', null, null), ('7', '2', '2', '2', null, null, null, null, '2018-05-08 18:32:44', 'admin', null, null);
commit;

set foreign_key_checks = 1;
