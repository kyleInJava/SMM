/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.12 : Database - myproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `sys_data_dictionary` */

DROP TABLE IF EXISTS `sys_data_dictionary`;

CREATE TABLE `sys_data_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `order` int(11) NOT NULL,
  `state` int(11) NOT NULL COMMENT '状态',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_data_dictionary` */

/*Table structure for table `sys_data_type` */

DROP TABLE IF EXISTS `sys_data_type`;

CREATE TABLE `sys_data_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `remark` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_data_type` */

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '部门的类型，可以用来做特殊的处理',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名字',
  `sort` int(11) NOT NULL COMMENT '同级部门排序',
  `parent_id` int(11) NOT NULL COMMENT '上级部门id',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门路径',
  `state` int(11) NOT NULL COMMENT '状态0：正常，1：删除，2：禁用',
  `updater` int(11) NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (1,0,'总公司',0,0,'/1/',0,1,'2019-04-12 01:51:53');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (2,1,'北京分公司',0,1,'/1/2/',0,1,'2019-03-19 15:15:31');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (4,1,'广州分公司',1,1,'/1/4/',0,1,'2019-03-19 15:16:44');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (5,1,'上海分公司',2,1,'/1/5/',0,1,'2019-03-19 15:18:29');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (6,2,'信息部',0,2,'/1/2/6/',0,1,'2019-03-19 15:20:01');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (7,2,'市场部',1,2,'/1/2/7/',0,1,'2019-03-19 15:20:38');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (8,2,'人事部',2,2,'/1/2/8/',0,1,'2019-03-19 15:21:37');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (9,2,'信息部',0,4,'/1/4/9/',0,1,'2019-03-19 15:22:39');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (10,2,'市场部',1,4,'/1/4/10/',0,1,'2019-03-19 15:23:14');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (11,3,'java项目组',0,9,'/1/4/9/11/',0,1,'2019-04-12 01:44:40');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (12,3,'前端项目组',1,9,'/1/4/9/12/',0,1,'2019-04-12 01:48:37');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (13,3,'.net开发组1',12,10,'/1/4/10/13/',0,1,'2019-04-12 02:53:58');
insert  into `sys_dept`(`id`,`type`,`name`,`sort`,`parent_id`,`path`,`state`,`updater`,`update_time`) values (15,3,'安卓开发组11',12,9,'/1/4/9/15/',0,1,'2019-04-12 11:15:35');

/*Table structure for table `sys_job` */

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `note` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态0：正常，1：删除，2：禁用',
  `updater` int(11) NOT NULL COMMENT '更新人',
  `udpate_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_job` */

insert  into `sys_job`(`id`,`name`,`note`,`state`,`updater`,`udpate_time`) values (1,'老板',NULL,0,1,'2019-03-29 11:31:22');
insert  into `sys_job`(`id`,`name`,`note`,`state`,`updater`,`udpate_time`) values (2,'总裁',NULL,0,1,'2019-03-29 11:32:21');
insert  into `sys_job`(`id`,`name`,`note`,`state`,`updater`,`udpate_time`) values (3,'副总裁',NULL,0,1,'2019-03-29 11:32:36');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源的名称',
  `type` int(11) NOT NULL COMMENT '资源的类型',
  `parent_id` int(11) NOT NULL COMMENT '资源的父id',
  `sort` int(11) NOT NULL COMMENT '排序号',
  `url` varchar(64) DEFAULT NULL COMMENT '前端路由',
  `request_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求该资源的路由',
  `need_permission` tinyint(1) NOT NULL COMMENT '是否需要权限控制0：不需要，1：需要',
  `note` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `state` int(11) NOT NULL COMMENT '状态0：正常，1：删除，2：禁用',
  `updater` int(11) NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (1,'目录一',0,0,0,NULL,'/dept/list',0,NULL,0,1,'2019-04-04 14:21:03');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (2,'菜单一',1,1,0,NULL,'/user/udpate',0,NULL,0,1,'2019-04-04 14:22:28');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (3,'菜单二',1,1,0,NULL,'/user/get',1,NULL,0,1,'2019-04-04 14:23:03');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (4,'菜单三',1,1,0,NULL,'/user/add',0,NULL,0,1,'2019-04-04 14:24:40');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (5,'目录二',0,0,0,NULL,'/login/logout',0,NULL,0,1,'2019-04-04 14:25:08');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (6,'菜单一',1,5,0,NULL,NULL,0,NULL,0,1,'2019-04-04 14:25:34');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (7,'菜单二',1,5,0,NULL,NULL,0,NULL,0,1,'2019-04-04 14:25:55');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (8,'菜单四',1,1,0,NULL,'/resource/add',0,NULL,0,1,'2019-04-04 14:26:16');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (9,'菜单三',1,5,0,NULL,'/user/get',0,NULL,0,1,'2019-04-04 14:28:48');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (10,'菜单一',1,13,0,NULL,'/user/list',0,NULL,0,1,'2019-04-04 14:29:21');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (11,'菜单二',1,13,0,NULL,'/resource/update',0,NULL,0,1,'2019-04-04 14:29:36');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (12,'菜单三',1,13,0,NULL,'/resource/delete',0,NULL,0,1,'2019-04-04 14:29:56');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (13,'目录三',0,0,0,NULL,NULL,0,NULL,0,1,'2019-04-04 14:30:32');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (14,'菜单四',1,13,0,NULL,NULL,0,NULL,0,1,'2019-04-04 14:31:04');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (15,'目录五',0,0,0,NULL,NULL,0,NULL,0,1,'2019-04-04 14:31:33');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (16,'菜单五',1,5,1,NULL,NULL,0,'1111',1,1,'2019-04-12 11:39:38');
insert  into `sys_resource`(`id`,`name`,`type`,`parent_id`,`sort`,`url`,`request_url`,`need_permission`,`note`,`state`,`updater`,`update_time`) values (17,'菜单liu',1,5,1,'','',0,'',1,1,'2019-04-13 17:06:06');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `note` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `data_permission` int(11) NOT NULL COMMENT '数据权限0为部门及子部门，1为只能查询本部门；默认',
  `state` int(11) NOT NULL COMMENT '状态0：正常，1：删除，2：禁用',
  `updater` int(11) NOT NULL COMMENT '更新人',
  `udpate_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`name`,`note`,`data_permission`,`state`,`updater`,`udpate_time`) values (1,'9999','超级管理员','',0,0,1,'2019-03-19 15:34:47');
insert  into `sys_role`(`id`,`code`,`name`,`note`,`data_permission`,`state`,`updater`,`udpate_time`) values (2,'8888','经理','',0,0,1,'2019-03-19 15:34:51');
insert  into `sys_role`(`id`,`code`,`name`,`note`,`data_permission`,`state`,`updater`,`udpate_time`) values (3,'总监1','7777','2222',0,1,1,'2019-04-12 14:30:14');

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (1,2,1);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (2,2,2);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (3,2,3);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (4,2,4);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (5,2,5);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (6,2,6);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (7,2,7);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (18,1,1);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (19,1,2);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (20,1,3);
insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (21,1,4);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的密码',
  `salt` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `age` int(11) NOT NULL COMMENT '年纪',
  `gender` int(11) NOT NULL COMMENT '性别0：女，1：男',
  `dept` int(11) NOT NULL COMMENT '部门id',
  `job` int(11) NOT NULL COMMENT '职位id',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态0：正常，1：删除，2：禁用',
  `updater` int(11) NOT NULL COMMENT '最近修改人',
  `update_time` datetime NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`account`,`password`,`salt`,`name`,`age`,`gender`,`dept`,`job`,`state`,`updater`,`update_time`) values (1,'80000001','7e9a90cc17ebc84fb889303f1106f557','abcd','kyle',28,1,2,1,0,1,'2019-03-29 11:29:45');
insert  into `sys_user`(`id`,`account`,`password`,`salt`,`name`,`age`,`gender`,`dept`,`job`,`state`,`updater`,`update_time`) values (2,'80000002','5a53d2bff064896fd533b2711635bd92','abcd','stan',28,1,2,2,0,1,'2019-03-29 11:29:51');
insert  into `sys_user`(`id`,`account`,`password`,`salt`,`name`,`age`,`gender`,`dept`,`job`,`state`,`updater`,`update_time`) values (3,'80000003','5a0c9ffa95c4fc680d324652450db3f2','TLfA','张小饭',15,1,10,3,0,1,'2019-04-10 08:58:46');
insert  into `sys_user`(`id`,`account`,`password`,`salt`,`name`,`age`,`gender`,`dept`,`job`,`state`,`updater`,`update_time`) values (4,'8000004','2b63ffab237b432b93e3cbd3072f20c6','unIY','陆雪琪',15,0,10,3,0,1,'2019-04-10 09:03:41');
insert  into `sys_user`(`id`,`account`,`password`,`salt`,`name`,`age`,`gender`,`dept`,`job`,`state`,`updater`,`update_time`) values (5,'8000005','b1a01861f56fe5452f9bbfef4e9cb477','7MiW','大芝麻',15,0,10,3,0,1,'2019-04-10 09:06:31');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`dept_id`) values (1,1,2,4);
insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`dept_id`) values (2,2,2,7);
insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`dept_id`) values (3,1,1,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
