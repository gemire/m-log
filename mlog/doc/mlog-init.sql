/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.28-rc-community : Database - mlog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mlog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mlog`;

/*Table structure for table `mlog_album` */

DROP TABLE IF EXISTS `mlog_album`;

CREATE TABLE `mlog_album` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_cover` int(11) DEFAULT NULL,
  `_create_time` datetime DEFAULT NULL,
  `_description` text,
  `_name` varchar(200) NOT NULL,
  `_photo_count` int(11) DEFAULT NULL,
  `_sort_order` int(11) DEFAULT NULL,
  `_type` int(11) DEFAULT NULL,
  `_verifycode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id` (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_album` */

/*Table structure for table `mlog_article` */

DROP TABLE IF EXISTS `mlog_article`;

CREATE TABLE `mlog_article` (
  `_article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_article_commentnums` bigint(20) DEFAULT NULL,
  `_article_content` text,
  `_article_createtime` datetime DEFAULT NULL,
  `_article_intro` text,
  `_article_ip` varchar(15) DEFAULT NULL,
  `_article_isdel` int(11) DEFAULT NULL,
  `_article_istop` int(11) DEFAULT NULL,
  `_article_level` bigint(20) DEFAULT NULL,
  `_article_modifytime` datetime DEFAULT NULL,
  `_article_tag` varchar(255) DEFAULT NULL,
  `_article_title` varchar(255) DEFAULT NULL,
  `_article_url` varchar(255) DEFAULT NULL,
  `_article_viewnums` bigint(20) DEFAULT NULL,
  `_article_category` bigint(20) NOT NULL,
  PRIMARY KEY (`_article_id`),
  UNIQUE KEY `_article_id` (`_article_id`),
  KEY `FKB67FC6E60AB4A96` (`_article_category`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_article` */

insert  into `mlog_article`(`_article_id`,`_article_commentnums`,`_article_content`,`_article_createtime`,`_article_intro`,`_article_ip`,`_article_isdel`,`_article_istop`,`_article_level`,`_article_modifytime`,`_article_tag`,`_article_title`,`_article_url`,`_article_viewnums`,`_article_category`) values (2,0,'<p>Hello，M-Loger：</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 感谢使用M-LOG</p>','2012-04-18 00:00:00','','127.0.0.1',0,1,1,NULL,NULL,'Hello M-LOG','',0,1);

/*Table structure for table `mlog_article_category` */

DROP TABLE IF EXISTS `mlog_article_category`;

CREATE TABLE `mlog_article_category` (
  `_article_id` bigint(20) NOT NULL,
  `_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_article_id`,`_category_id`),
  KEY `FK23CA0DAFCC6F9C4D` (`_category_id`),
  KEY `FK23CA0DAFF23C5C47` (`_article_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_article_category` */

/*Table structure for table `mlog_article_tag` */

DROP TABLE IF EXISTS `mlog_article_tag`;

CREATE TABLE `mlog_article_tag` (
  `_tag_id` int(11) NOT NULL,
  `_article_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_article_id`,`_tag_id`),
  KEY `FKE545044985C2D847` (`_tag_id`),
  KEY `FKE5450449F23C5C47` (`_article_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_article_tag` */

insert  into `mlog_article_tag`(`_tag_id`,`_article_id`) values (1,2);

/*Table structure for table `mlog_authorities` */

DROP TABLE IF EXISTS `mlog_authorities`;

CREATE TABLE `mlog_authorities` (
  `_authority_id` int(11) NOT NULL AUTO_INCREMENT,
  `_authority_alias` varchar(100) DEFAULT NULL,
  `_authority_desc` varchar(100) DEFAULT NULL,
  `_authority_name` varchar(100) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`_authority_id`),
  UNIQUE KEY `_authority_id` (`_authority_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_authorities` */

insert  into `mlog_authorities`(`_authority_id`,`_authority_alias`,`_authority_desc`,`_authority_name`,`_enabled`) values (1,'博客管理','博客管理权限','AUTHORITY_BLOG','');

/*Table structure for table `mlog_authorities_resources` */

DROP TABLE IF EXISTS `mlog_authorities_resources`;

CREATE TABLE `mlog_authorities_resources` (
  `_authority_id` int(11) NOT NULL,
  `_resource_id` int(11) NOT NULL,
  PRIMARY KEY (`_authority_id`,`_resource_id`),
  KEY `FKD846D3FFC4796DCB` (`_resource_id`),
  KEY `FKD846D3FFE69A4229` (`_authority_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_authorities_resources` */

insert  into `mlog_authorities_resources`(`_authority_id`,`_resource_id`) values (1,1);

/*Table structure for table `mlog_authorities_tree` */

DROP TABLE IF EXISTS `mlog_authorities_tree`;

CREATE TABLE `mlog_authorities_tree` (
  `_tree_id` varchar(100) NOT NULL,
  `_authority_id` int(11) NOT NULL,
  PRIMARY KEY (`_authority_id`,`_tree_id`),
  KEY `FK58289D84E69A4229` (`_authority_id`),
  KEY `FK58289D84BE42944B` (`_tree_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_authorities_tree` */

insert  into `mlog_authorities_tree`(`_tree_id`,`_authority_id`) values ('200',1),('200001',1),('200001001',1),('200001002',1),('200001003',1),('200001004',1),('200001005',1),('200001006',1),('200002',1),('200002002',1),('200002003',1),('200002004',1),('200003',1),('200003001',1),('200003002',1),('200003003',1),('200004',1),('200004001',1),('200004002',1),('200004003',1),('200005',1),('200005001',1);

/*Table structure for table `mlog_category` */

DROP TABLE IF EXISTS `mlog_category`;

CREATE TABLE `mlog_category` (
  `_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_category_createtime` datetime DEFAULT NULL,
  `_category_intro` varchar(3000) DEFAULT NULL,
  `_category_modifytime` bigint(20) DEFAULT NULL,
  `_category_name` varchar(50) DEFAULT NULL,
  `_category_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`_category_id`),
  UNIQUE KEY `_category_id` (`_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_category` */

insert  into `mlog_category`(`_category_id`,`_category_createtime`,`_category_intro`,`_category_modifytime`,`_category_name`,`_category_order`) values (1,'2012-04-18 15:48:41','默认分类',NULL,'默认分类',NULL);

/*Table structure for table `mlog_comment` */

DROP TABLE IF EXISTS `mlog_comment`;

CREATE TABLE `mlog_comment` (
  `_comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_agent` varchar(200) DEFAULT NULL,
  `_comment_author` varchar(100) DEFAULT NULL,
  `_comment_content` text,
  `_comment_createtime` datetime DEFAULT NULL,
  `_comment_email` varchar(50) DEFAULT NULL,
  `_comment_homepage` varchar(255) DEFAULT NULL,
  `_comment_ip` varchar(15) DEFAULT NULL,
  `_comment_status` int(11) DEFAULT NULL,
  `_comment_article` bigint(20) NOT NULL,
  PRIMARY KEY (`_comment_id`),
  UNIQUE KEY `_comment_id` (`_comment_id`),
  KEY `FK6FB51DD725CFF67B` (`_comment_article`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_comment` */

insert  into `mlog_comment`(`_comment_id`,`_agent`,`_comment_author`,`_comment_content`,`_comment_createtime`,`_comment_email`,`_comment_homepage`,`_comment_ip`,`_comment_status`,`_comment_article`) values (1,'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; QQDownload 713; baiduds; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)','mspring','第一条评论！','2012-04-18 16:19:10','gaoyoubo@mspring.org','http://www.mspring.org','127.0.0.1',1,2);

/*Table structure for table `mlog_link` */

DROP TABLE IF EXISTS `mlog_link`;

CREATE TABLE `mlog_link` (
  `_link_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_link_description` text,
  `_link_display` bit(1) DEFAULT NULL,
  `_link_imageurl` varchar(3000) DEFAULT NULL,
  `_link_name` varchar(200) NOT NULL,
  `_link_target` varchar(200) DEFAULT NULL,
  `_link_url` varchar(500) DEFAULT NULL,
  `_link_type` bigint(20) NOT NULL,
  PRIMARY KEY (`_link_id`),
  UNIQUE KEY `_link_id` (`_link_id`),
  KEY `FK9926738220767A46` (`_link_type`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_link` */

insert  into `mlog_link`(`_link_id`,`_link_description`,`_link_display`,`_link_imageurl`,`_link_name`,`_link_target`,`_link_url`,`_link_type`) values (1,NULL,'','','慕春博客','_blank','http://www.mspring.org',1);

/*Table structure for table `mlog_linktype` */

DROP TABLE IF EXISTS `mlog_linktype`;

CREATE TABLE `mlog_linktype` (
  `_linktype_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_link_description` text,
  `_linktype_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`_linktype_id`),
  UNIQUE KEY `_linktype_id` (`_linktype_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_linktype` */

insert  into `mlog_linktype`(`_linktype_id`,`_link_description`,`_linktype_name`) values (1,'','默认分类');

/*Table structure for table `mlog_menu` */

DROP TABLE IF EXISTS `mlog_menu`;

CREATE TABLE `mlog_menu` (
  `_menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_menu_createtime` datetime DEFAULT NULL,
  `_menu_description` text,
  `_menu_name` varchar(225) NOT NULL,
  `_menu_order` int(11) DEFAULT NULL,
  `_menu_parent` bigint(20) DEFAULT NULL,
  `_menu_type` int(11) NOT NULL,
  `_menu_url` varchar(1000) DEFAULT NULL,
  `_menu_category` bigint(20) NOT NULL,
  PRIMARY KEY (`_menu_id`),
  UNIQUE KEY `_menu_id` (`_menu_id`),
  KEY `FK9926D8E7A645D7EF` (`_menu_category`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_menu` */

insert  into `mlog_menu`(`_menu_id`,`_menu_createtime`,`_menu_description`,`_menu_name`,`_menu_order`,`_menu_parent`,`_menu_type`,`_menu_url`,`_menu_category`) values (1,NULL,NULL,'搜索',1,NULL,0,'search.html',0),(2,NULL,NULL,'后台管理',2,NULL,0,'admin/admin_login.jsp',0);

/*Table structure for table `mlog_option` */

DROP TABLE IF EXISTS `mlog_option`;

CREATE TABLE `mlog_option` (
  `_option_key` varchar(100) NOT NULL,
  `_option_value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`_option_key`),
  UNIQUE KEY `_option_key` (`_option_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_option` */

insert  into `mlog_option`(`_option_key`,`_option_value`) values ('mspring_album_islimit_width','900'),('mspring_album_limit','16'),('mspring_album_islimit_size','1'),('mspring_album_islimit_height','300'),('mspring_config_base_secondtitle','Power by M-Log'),('mspring_config_base_title','M-Spring'),('mspring_config_base_owner','M-Spring'),('mspring_config_base_blogintro','Java'),('mspring_config_seo_description','个人博客'),('mspring_config_base_email','gaoyoubo@mspring.org'),('mspring_config_global_rebuildfolder','post'),('mspring_config_base_skin','style'),('mspring_config_global_autorebuild',''),('mspring_config_base_blogname','M-Spring'),('mspring_config_seo_keyworld','M-Spring'),('mspring_config_base_nickname','mspring'),('mspring_config_base_copyright','Copyright @ M-Spring'),('mspring_config_base_theme','default'),('mspring_config_global_language',''),('mspring_config_seo_enableseo','1'),('mspring_config_seo_keyword',''),('mspring_config_system_mail_password','gaoyoubo123?'),('mspring_config_system_mail_port','25'),('mspring_config_system_mail_username','gaoyoubo@mspring.org'),('mspring_config_system_mail_host','smtp.exmail.qq.com'),('mspring_config_base_auditcomment','1'),('mspring_cache_enable_page_cache','0'),('mspring_cache_enable_dbquery_cache','0');

/*Table structure for table `mlog_photo` */

DROP TABLE IF EXISTS `mlog_photo`;

CREATE TABLE `mlog_photo` (
  `_photo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_exif_iso` int(11) DEFAULT NULL,
  `_exif_aperture` varchar(20) DEFAULT NULL,
  `_color_bit` int(11) DEFAULT NULL,
  `_exif_color_space` varchar(20) DEFAULT NULL,
  `_create_time` datetime DEFAULT NULL,
  `_description` text,
  `_exif_exposure_bias` varchar(20) DEFAULT NULL,
  `_exif_exposure_time` varchar(20) DEFAULT NULL,
  `_file_name` varchar(100) DEFAULT NULL,
  `_exif_focal_length` varchar(20) DEFAULT NULL,
  `_photo_height` int(11) DEFAULT NULL,
  `_exif_manufacturer` varchar(50) DEFAULT NULL,
  `_exif_model` varchar(50) DEFAULT NULL,
  `_modify_time` datetime DEFAULT NULL,
  `_photo_name` varchar(1000) DEFAULT NULL,
  `_photo_date` smallint(6) DEFAULT NULL,
  `_photo_month` smallint(6) DEFAULT NULL,
  `_photo_year` smallint(6) DEFAULT NULL,
  `_preview_url` varchar(100) DEFAULT NULL,
  `_exif_shutter` varchar(20) DEFAULT NULL,
  `_photo_size` int(11) DEFAULT NULL,
  `_photo_url` varchar(100) DEFAULT NULL,
  `_photo_width` int(11) DEFAULT NULL,
  `_album_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_photo_id`),
  UNIQUE KEY `_photo_id` (`_photo_id`),
  KEY `FK8BDFEBAAA844ECE7` (`_album_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_photo` */

/*Table structure for table `mlog_plugin` */

DROP TABLE IF EXISTS `mlog_plugin`;

CREATE TABLE `mlog_plugin` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_author` varchar(200) DEFAULT NULL,
  `_description` text,
  `_email` varchar(200) DEFAULT NULL,
  `_home_page` varchar(300) DEFAULT NULL,
  `_name` varchar(200) DEFAULT NULL,
  `_pluginclass` varchar(500) DEFAULT NULL,
  `_version` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id` (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_plugin` */

/*Table structure for table `mlog_resources` */

DROP TABLE IF EXISTS `mlog_resources`;

CREATE TABLE `mlog_resources` (
  `_resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_resource_desc` varchar(200) DEFAULT NULL,
  `_resource_name` varchar(100) DEFAULT NULL,
  `_resource_string` varchar(200) DEFAULT NULL,
  `_resource_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`_resource_id`),
  UNIQUE KEY `_resource_id` (`_resource_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_resources` */

insert  into `mlog_resources`(`_resource_id`,`_enabled`,`_resource_desc`,`_resource_name`,`_resource_string`,`_resource_type`) values (1,'','后台管理的所有文件','后台文件','/admin/*',NULL);

/*Table structure for table `mlog_roles` */

DROP TABLE IF EXISTS `mlog_roles`;

CREATE TABLE `mlog_roles` (
  `_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_role_alias` varchar(100) DEFAULT NULL,
  `_role_desc` varchar(100) DEFAULT NULL,
  `_role_name` varchar(100) DEFAULT NULL,
  `_is_sys` bit(1) DEFAULT NULL,
  PRIMARY KEY (`_role_id`),
  UNIQUE KEY `_role_id` (`_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_roles` */

insert  into `mlog_roles`(`_role_id`,`_enabled`,`_role_alias`,`_role_desc`,`_role_name`,`_is_sys`) values (1,'','超级管理员','超级管理员','SUPER_ADMINISTRATOR','');

/*Table structure for table `mlog_roles_authorities` */

DROP TABLE IF EXISTS `mlog_roles_authorities`;

CREATE TABLE `mlog_roles_authorities` (
  `_role_id` int(11) NOT NULL,
  `_authority_id` int(11) NOT NULL,
  PRIMARY KEY (`_authority_id`,`_role_id`),
  KEY `FKA22106174FB9454B` (`_role_id`),
  KEY `FKA2210617E69A4229` (`_authority_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_roles_authorities` */

insert  into `mlog_roles_authorities`(`_role_id`,`_authority_id`) values (1,1);

/*Table structure for table `mlog_tag` */

DROP TABLE IF EXISTS `mlog_tag`;

CREATE TABLE `mlog_tag` (
  `_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `_tag_count` int(11) DEFAULT NULL,
  `_tag_intro` text,
  `_tag_name` varchar(255) DEFAULT NULL,
  `_tag_order` int(11) DEFAULT NULL,
  `_tag_parent` int(11) DEFAULT NULL,
  `_tag_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_tag_id`),
  UNIQUE KEY `_tag_id` (`_tag_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_tag` */

insert  into `mlog_tag`(`_tag_id`,`_tag_count`,`_tag_intro`,`_tag_name`,`_tag_order`,`_tag_parent`,`_tag_url`) values (1,NULL,NULL,'mlog',NULL,NULL,NULL);

/*Table structure for table `mlog_tree` */

DROP TABLE IF EXISTS `mlog_tree`;

CREATE TABLE `mlog_tree` (
  `_id` varchar(100) NOT NULL,
  `_deleted` bit(1) DEFAULT NULL,
  `_expanded` bit(1) DEFAULT NULL,
  `_icon` varchar(200) DEFAULT NULL,
  `_leaf` bit(1) DEFAULT NULL,
  `_parent` varchar(100) DEFAULT NULL,
  `_qtip` varchar(200) DEFAULT NULL,
  `_text` varchar(200) DEFAULT NULL,
  `_type` int(11) DEFAULT NULL,
  `_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id` (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_tree` */

insert  into `mlog_tree`(`_id`,`_deleted`,`_expanded`,`_icon`,`_leaf`,`_parent`,`_qtip`,`_text`,`_type`,`_url`) values ('200','\0','\0','','\0','0','博客管理','博客管理',1,''),('200001','\0','\0','','\0','200','内容管理','内容管理',2,''),('200001001','\0','\0','','','200001','新建文章','新建文章',2,'admin/article/toCreateArticle.action'),('200001002','\0','\0','','','200001','文章管理','文章管理',2,'admin/article/queryArticle.action'),('200001003','\0','\0','','','200001','分类管理','分类管理',2,'admin/category/queryCategory.action'),('200001004','\0','\0','','','200001','文章重建','文章重建',2,'admin/article/toBuildArticle.action'),('200001005','\0','\0','','','200001','标签管理','标签管理',2,'admin/tag/queryTag.action'),('200001006','\0','\0','','','200001','评论管理','评论管理',2,'admin/comment/queryComment.action'),('200002','\0','\0','admin/resources/images/tree/album.gif','\0','200','我的相册','我的相册',2,''),('200002002','\0','\0','','','200002','创建相册','创建相册',2,'admin/album/toCreateAlbum.action'),('200002003','\0','\0','','','200002','相册管理','相册管理',2,'admin/album/queryAlbum.action'),('200002004','\0','\0','','','200002','相册设置','相册设置',2,'admin/album/toEditAlbumConfig.action'),('200003','\0','\0','','\0','200','链接','链接',2,''),('200003001','\0','\0','','','200003','添加链接','添加链接',2,'admin/link/toCreateLink.action'),('200003002','\0','\0','','','200003','链接管理','链接管理',2,'admin/link/queryLink.action'),('200003003','\0','\0','','','200003','链接分类','链接分类',2,'admin/link/queryLinkType.action'),('200004','\0','\0','','\0','200','设置','工具',2,''),('200004001','\0','\0','','','200004','网站设置','网站设置',2,'admin/common/viewConfig.action'),('200004002','\0','\0','','','200004','主题管理','主题管理',2,'admin/theme/queryTheme.action'),('300','\0','\0','','\0','0','邮件','邮件',1,''),('300001','\0','\0','','','300','测试','测试',2,''),('200005','\0','\0','','\0','200','权限','权限',2,''),('200005001','\0','\0','','','200005','用户管理','用户管理',2,''),('200004003','\0','\0',NULL,'','200004','缓存管理','缓存管理',2,'admin/cache/listPageCache.action');

/*Table structure for table `mlog_upload` */

DROP TABLE IF EXISTS `mlog_upload`;

CREATE TABLE `mlog_upload` (
  `_id_` int(11) NOT NULL AUTO_INCREMENT,
  `_down_num_` int(11) DEFAULT NULL,
  `_file_name_` varchar(50) DEFAULT NULL,
  `_file_size_` int(11) DEFAULT NULL,
  `_post_time_` date DEFAULT NULL,
  `_quote_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id_`),
  UNIQUE KEY `_id_` (`_id_`),
  UNIQUE KEY `_file_name_` (`_file_name_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_upload` */

/*Table structure for table `mlog_users` */

DROP TABLE IF EXISTS `mlog_users`;

CREATE TABLE `mlog_users` (
  `_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `_user_email` varchar(50) DEFAULT NULL,
  `_user_gender` int(11) DEFAULT NULL,
  `_user_homepage` varchar(255) DEFAULT NULL,
  `_user_intro` text,
  `_user_ip` varchar(15) DEFAULT NULL,
  `_user_lastvisit` date DEFAULT NULL,
  `_user_level` int(11) DEFAULT NULL,
  `_user_msn` varchar(50) DEFAULT NULL,
  `_user_name` varchar(20) DEFAULT NULL,
  `_user_password` varchar(32) DEFAULT NULL,
  `_user_qq` varchar(50) DEFAULT NULL,
  `_user_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`_user_id`),
  UNIQUE KEY `_user_id` (`_user_id`),
  UNIQUE KEY `_user_name` (`_user_name`),
  UNIQUE KEY `_user_name_2` (`_user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `mlog_users` */

insert  into `mlog_users`(`_user_id`,`_user_email`,`_user_gender`,`_user_homepage`,`_user_intro`,`_user_ip`,`_user_lastvisit`,`_user_level`,`_user_msn`,`_user_name`,`_user_password`,`_user_qq`,`_user_status`) values (1,'gaoyoubo@mspring.org',1,'http://www.mspring.org','管理员',NULL,NULL,NULL,'gaoyoubo@hotmail.com','gaoyoubo','3be4183bcb1730f57be96b7b1c1aaf8a','330721072',NULL);

/*Table structure for table `mlog_users_roles` */

DROP TABLE IF EXISTS `mlog_users_roles`;

CREATE TABLE `mlog_users_roles` (
  `_user_id` int(11) NOT NULL,
  `_role_id` int(11) NOT NULL,
  PRIMARY KEY (`_role_id`,`_user_id`),
  KEY `FKAB478D3E4FB9454B` (`_role_id`),
  KEY `FKAB478D3EF4E4092B` (`_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mlog_users_roles` */

insert  into `mlog_users_roles`(`_user_id`,`_role_id`) values (1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
