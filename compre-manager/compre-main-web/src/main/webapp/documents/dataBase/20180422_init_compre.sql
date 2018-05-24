/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : origin

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-04-22 14:20:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bas_en_constant
-- ----------------------------
DROP TABLE IF EXISTS `bas_en_constant`;
CREATE TABLE `bas_en_constant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `namespace` varchar(100) NOT NULL,
  `is_valid` smallint(6) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `namespace` (`namespace`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_en_constant
-- ----------------------------
INSERT INTO `bas_en_constant` VALUES ('1', '出差', '1', 'eventInterval', '1', '', '2018-04-11 23:59:41', '2018-04-11 23:59:41');
INSERT INTO `bas_en_constant` VALUES ('2', '旅行', '2', 'eventInterval', '1', '', '2018-04-12 00:00:10', '2018-04-12 00:00:28');

-- ----------------------------
-- Table structure for com_event
-- ----------------------------
DROP TABLE IF EXISTS `com_event`;
CREATE TABLE `com_event` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fk_user_id` int(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史事件表';

-- ----------------------------
-- Records of com_event
-- ----------------------------

-- ----------------------------
-- Table structure for com_events_interval
-- ----------------------------
DROP TABLE IF EXISTS `com_events_interval`;
CREATE TABLE `com_events_interval` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fk_constant_id` int(20) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fk_user_id` int(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事件间隔';

-- ----------------------------
-- Records of com_events_interval
-- ----------------------------

-- ----------------------------
-- Table structure for com_person
-- ----------------------------
DROP TABLE IF EXISTS `com_person`;
CREATE TABLE `com_person` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `birthday_lunar` datetime DEFAULT NULL,
  `identity_card` varchar(20) DEFAULT NULL,
  `profession` varchar(20) DEFAULT NULL,
  `speciality` varchar(200) DEFAULT NULL COMMENT '专长',
  `mobile_phone1` varchar(15) DEFAULT NULL,
  `mobile_phone2` varchar(15) DEFAULT NULL,
  `mobile_phone3` varchar(15) DEFAULT NULL,
  `place_of_domicile` varchar(200) DEFAULT NULL COMMENT '户籍所在地',
  `address` varchar(200) DEFAULT NULL,
  `address_work` varchar(200) DEFAULT NULL,
  `last_graduate_school` varchar(100) DEFAULT NULL,
  `last_graduate_date` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT '',
  `fk_user_id` int(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息';

-- ----------------------------
-- Records of com_person
-- ----------------------------

-- ----------------------------
-- Table structure for example_bean
-- ----------------------------
DROP TABLE IF EXISTS `example_bean`;
CREATE TABLE `example_bean` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_hidden` varchar(255) DEFAULT NULL,
  `form_text` varchar(255) DEFAULT NULL,
  `form_password` varchar(255) DEFAULT NULL,
  `form_text_area` varchar(255) DEFAULT NULL,
  `form_html_editor` varchar(1000) DEFAULT NULL,
  `form_number_short` smallint(6) DEFAULT NULL,
  `form_number_short_base` smallint(6) DEFAULT NULL,
  `form_number_integer` int(11) DEFAULT NULL,
  `form_number_int_base` int(11) DEFAULT NULL,
  `form_number_long` bigint(20) DEFAULT NULL,
  `form_number_long_base` bigint(20) DEFAULT NULL,
  `form_number_float` float DEFAULT NULL,
  `form_number_float_base` double DEFAULT NULL,
  `form_number_double` double DEFAULT NULL,
  `form_number_double_base` double DEFAULT NULL,
  `form_boolean` int(11) DEFAULT NULL,
  `is_form_boolean_flag` int(11) DEFAULT NULL,
  `form_boolean_base` int(11) DEFAULT NULL,
  `form_date_string` varchar(255) DEFAULT NULL,
  `form_date_date` date DEFAULT NULL,
  `form_datetime_ymdhm` datetime DEFAULT NULL,
  `form_datetime_string` varchar(255) DEFAULT NULL,
  `form_datetime_date` datetime DEFAULT NULL,
  `form_radio` varchar(255) DEFAULT NULL,
  `form_checkbox` varchar(255) DEFAULT NULL,
  `form_combo_box_json` varchar(255) DEFAULT NULL,
  `form_combo_box_sql` varchar(255) DEFAULT NULL,
  `form_tag_json` varchar(255) DEFAULT NULL,
  `form_tag_sql` varchar(255) DEFAULT NULL,
  `form_combo_box_cascade_a` varchar(255) DEFAULT NULL,
  `form_combo_box_cascade_b` varchar(255) DEFAULT NULL,
  `form_combo_box_cascade1` varchar(255) DEFAULT NULL,
  `form_combo_box_cascade2` varchar(255) DEFAULT NULL,
  `form_combo_box_cascade3` varchar(255) DEFAULT NULL,
  `form_combo_box_tree1` varchar(255) DEFAULT NULL,
  `form_combo_box_tree2` varchar(255) DEFAULT NULL,
  `form_combo_box_tree3` varchar(255) DEFAULT NULL,
  `form_combo_box_tree4` varchar(255) DEFAULT NULL,
  `form_combo_box_tree5` varchar(255) DEFAULT NULL,
  `form_combo_box_tree6` varchar(255) DEFAULT NULL,
  `form_display` varchar(255) DEFAULT NULL,
  `form_file1_url` varchar(255) DEFAULT NULL,
  `form_file1_orig_file_name` varchar(255) DEFAULT NULL,
  `form_file2_url` varchar(255) DEFAULT NULL,
  `form_file2_orig_file_name` varchar(255) DEFAULT NULL,
  `form_file3_url` varchar(255) DEFAULT NULL,
  `form_file3_orig_file_name` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of example_bean
-- ----------------------------

-- ----------------------------
-- Table structure for example_bean_form_val
-- ----------------------------
DROP TABLE IF EXISTS `example_bean_form_val`;
CREATE TABLE `example_bean_form_val` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alpha` varchar(255) DEFAULT NULL,
  `alphanum` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `letter` varchar(255) DEFAULT NULL,
  `upper_case` varchar(255) DEFAULT NULL,
  `lower_case` varchar(255) DEFAULT NULL,
  `letter_num` varchar(255) DEFAULT NULL,
  `letter_num_underline` varchar(255) DEFAULT NULL,
  `chinese_letter_num` varchar(255) DEFAULT NULL,
  `chinese_letter_num_underline` varchar(255) DEFAULT NULL,
  `chinese` varchar(255) DEFAULT NULL,
  `character50` varchar(255) DEFAULT NULL,
  `email_platform` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `fixed_phone_no` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `id_number15` varchar(255) DEFAULT NULL,
  `id_number18` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `date_ymd` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `general_password` varchar(255) DEFAULT NULL,
  `strong_password` varchar(255) DEFAULT NULL,
  `regex` varchar(255) DEFAULT NULL,
  `regex_vtype` varchar(255) DEFAULT NULL,
  `val_text` varchar(255) DEFAULT NULL,
  `val_text_param` varchar(255) DEFAULT NULL,
  `val_password` varchar(255) DEFAULT NULL,
  `val_password_param` varchar(255) DEFAULT NULL,
  `val_text_area` varchar(255) DEFAULT NULL,
  `val_text_area_param` varchar(255) DEFAULT NULL,
  `val_number` double DEFAULT NULL,
  `val_number_param` double DEFAULT NULL,
  `val_tag` varchar(255) DEFAULT NULL,
  `val_tag_param` varchar(255) DEFAULT NULL,
  `val_date` varchar(255) DEFAULT NULL,
  `val_date_param` varchar(255) DEFAULT NULL,
  `val_combo_box` varchar(255) DEFAULT NULL,
  `val_combo_box_param` varchar(255) DEFAULT NULL,
  `val_tree` varchar(255) DEFAULT NULL,
  `val_tree_param` varchar(255) DEFAULT NULL,
  `val_file_url` varchar(255) DEFAULT NULL,
  `val_file_orig_file_name` varchar(255) DEFAULT NULL,
  `val_file_param_url` varchar(255) DEFAULT NULL,
  `val_file_param_orig_file_name` varchar(255) DEFAULT NULL,
  `regex_vtype_validator` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of example_bean_form_val
-- ----------------------------

-- ----------------------------
-- Table structure for example_bean_tree
-- ----------------------------
DROP TABLE IF EXISTS `example_bean_tree`;
CREATE TABLE `example_bean_tree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140729 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of example_bean_tree
-- ----------------------------
INSERT INTO `example_bean_tree` VALUES ('110000', '北京市', '-1');
INSERT INTO `example_bean_tree` VALUES ('110100', '市辖区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110101', '东城区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110102', '西城区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110105', '朝阳区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110106', '丰台区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110107', '石景山区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110108', '海淀区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110109', '门头沟区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110111', '房山区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110112', '通州区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110113', '顺义区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110114', '昌平区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110115', '大兴区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110116', '怀柔区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110117', '平谷区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110118', '密云区', '110000');
INSERT INTO `example_bean_tree` VALUES ('110119', '延庆区', '110000');
INSERT INTO `example_bean_tree` VALUES ('120000', '天津市', '-1');
INSERT INTO `example_bean_tree` VALUES ('120100', '市辖区', '120000');
INSERT INTO `example_bean_tree` VALUES ('120101', '和平区', '120000');
INSERT INTO `example_bean_tree` VALUES ('120102', '河东区', '120000');
INSERT INTO `example_bean_tree` VALUES ('120103', '河西区', '120000');
INSERT INTO `example_bean_tree` VALUES ('130000', '河北省', '-1');
INSERT INTO `example_bean_tree` VALUES ('130100', '石家庄市', '130000');
INSERT INTO `example_bean_tree` VALUES ('130101', '市辖区', '130100');
INSERT INTO `example_bean_tree` VALUES ('130102', '长安区', '130100');
INSERT INTO `example_bean_tree` VALUES ('130104', '桥西区', '130100');
INSERT INTO `example_bean_tree` VALUES ('130105', '新华区', '130100');
INSERT INTO `example_bean_tree` VALUES ('130200', '唐山市', '130000');
INSERT INTO `example_bean_tree` VALUES ('130201', '市辖区', '130200');
INSERT INTO `example_bean_tree` VALUES ('130202', '路南区', '130200');
INSERT INTO `example_bean_tree` VALUES ('130203', '路北区', '130200');
INSERT INTO `example_bean_tree` VALUES ('140000', '山西省', '-1');
INSERT INTO `example_bean_tree` VALUES ('140100', '太原市', '140000');
INSERT INTO `example_bean_tree` VALUES ('140101', '市辖区', '140100');
INSERT INTO `example_bean_tree` VALUES ('140105', '小店区', '140100');
INSERT INTO `example_bean_tree` VALUES ('140106', '迎泽区', '140100');
INSERT INTO `example_bean_tree` VALUES ('140107', '杏花岭区', '140100');
INSERT INTO `example_bean_tree` VALUES ('140200', '大同市', '140000');
INSERT INTO `example_bean_tree` VALUES ('140201', '市辖区', '140200');
INSERT INTO `example_bean_tree` VALUES ('140202', '城区', '140200');
INSERT INTO `example_bean_tree` VALUES ('140222', '天镇县', '140200');
INSERT INTO `example_bean_tree` VALUES ('140400', '长治市', '140000');
INSERT INTO `example_bean_tree` VALUES ('140401', '市辖区', '140400');
INSERT INTO `example_bean_tree` VALUES ('140402', '城区', '140400');
INSERT INTO `example_bean_tree` VALUES ('140411', '郊区', '140400');
INSERT INTO `example_bean_tree` VALUES ('140421', '长治县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140423', '襄垣县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140424', '屯留县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140425', '平顺县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140426', '黎城县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140427', '壶关县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140428', '长子县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140429', '武乡县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140430', '沁县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140431', '沁源县', '140400');
INSERT INTO `example_bean_tree` VALUES ('140481', '潞城市', '140400');
INSERT INTO `example_bean_tree` VALUES ('140700', '晋中市', '140000');
INSERT INTO `example_bean_tree` VALUES ('140701', '市辖区', '140700');
INSERT INTO `example_bean_tree` VALUES ('140702', '榆次区', '140700');
INSERT INTO `example_bean_tree` VALUES ('140727', '祁县', '140700');
INSERT INTO `example_bean_tree` VALUES ('140728', '平遥县', '140700');

-- ----------------------------
-- Table structure for expend
-- ----------------------------
DROP TABLE IF EXISTS `expend`;
CREATE TABLE `expend` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `exp_sum` double DEFAULT NULL,
  `exp_date` datetime DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fk_expend_type_id` int(11) DEFAULT NULL,
  `fk_user_id` int(20) DEFAULT NULL,
  `fk_com_event_id` int(20) DEFAULT NULL,
  `fk_com_person_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_expend_lookup` (`id`),
  KEY `fk_com_event_id` (`fk_com_event_id`),
  KEY `fk_com_person_id` (`fk_com_person_id`),
  CONSTRAINT `expend_ibfk_1` FOREIGN KEY (`fk_com_event_id`) REFERENCES `com_event` (`id`),
  CONSTRAINT `expend_ibfk_2` FOREIGN KEY (`fk_com_person_id`) REFERENCES `com_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expend
-- ----------------------------

-- ----------------------------
-- Table structure for expend_type
-- ----------------------------
DROP TABLE IF EXISTS `expend_type`;
CREATE TABLE `expend_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` int(11) DEFAULT NULL,
  `expend_type_name` varchar(20) DEFAULT NULL,
  `order_num` varchar(10) DEFAULT NULL,
  `is_sys_own` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `fk_user_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_expend_type_lookup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户支出类型';

-- ----------------------------
-- Records of expend_type
-- ----------------------------

-- ----------------------------
-- Table structure for expend_type_sys
-- ----------------------------
DROP TABLE IF EXISTS `expend_type_sys`;
CREATE TABLE `expend_type_sys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` int(11) DEFAULT NULL,
  `expend_type_name` varchar(20) DEFAULT NULL,
  `order_num` varchar(10) DEFAULT NULL,
  `is_sys_own` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_expend_type_lookup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='系统自带的支出类型';

-- ----------------------------
-- Records of expend_type_sys
-- ----------------------------
INSERT INTO `expend_type_sys` VALUES ('1', '-1', '餐饮支出', '001', '1', '', '2018-02-22 10:09:17', '2018-04-12 22:03:42');
INSERT INTO `expend_type_sys` VALUES ('2', '1', '三餐', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('3', '1', '食物作料', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('4', '1', '水果', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('5', '1', '零食饮料', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('6', '1', '烟酒茶', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('7', '-1', '服装鞋帽', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('8', '7', '衣服', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('9', '7', '鞋类', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('10', '7', '箱包饰品', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('11', '-1', '日常用品', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('12', '11', '个人用品', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('13', '11', '洗涤用品', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('14', '11', '其它日用', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('15', '-1', '个人支出', '004', '1', '', '2018-02-22 10:09:17', '2018-03-03 17:14:11');
INSERT INTO `expend_type_sys` VALUES ('16', '15', '洗理费', '001', '1', '', '2018-02-22 10:09:17', '2018-03-03 17:14:38');
INSERT INTO `expend_type_sys` VALUES ('17', '15', '化妆品', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('18', '15', '美容护肤', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('19', '-1', '交通支出', '005', '1', '', '2018-02-22 10:09:17', '2018-03-03 17:13:54');
INSERT INTO `expend_type_sys` VALUES ('20', '19', '公共交通', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('21', '19', '打的费', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('22', '19', '汽油费', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('23', '19', '汽车保养费', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('24', '19', '停车过路', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('25', '19', '年检保险', '006', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('26', '19', '违章罚款', '007', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('27', '19', '汽车修理', '008', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('28', '-1', '通信支出', '006', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('29', '28', '电话费', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('30', '28', '手机费', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('31', '28', '上网费', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('32', '28', '邮寄费', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('33', '-1', '居家支出', '007', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('34', '33', '房租', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('35', '33', '物业费', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('36', '33', '水费', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('37', '33', '电费', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('38', '33', '燃气费', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('39', '33', '卫生费', '006', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('40', '33', '取暖费', '007', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('41', '33', '厨房用具', '008', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('42', '33', '电器', '009', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('43', '33', '家具', '010', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('44', '-1', '教育支出', '008', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('45', '44', '书籍杂志', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('46', '44', '考试费', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('47', '44', '培训费', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('48', '44', '学杂费', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('49', '44', '书本文具', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('50', '-1', '文化娱乐', '009', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('51', '50', '休闲娱乐', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('52', '50', '旅游度假', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('53', '50', '博彩支出', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('54', '50', '数码电子', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('55', '-1', '医疗保健', '010', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('56', '55', '药费', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('57', '55', '保健', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('58', '55', '住院', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('59', '55', '门诊', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('60', '-1', '礼尚往来', '011', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('61', '60', '请客', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('62', '60', '红白喜事', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('63', '60', '人情交际', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('64', '60', '捐款捐赠', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('65', '-1', '抚养赡养', '012', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('66', '65', '孝敬长辈', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('67', '-1', '保险投资', '013', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('68', '67', '社保支出', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('69', '67', '利息税', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('70', '-1', '杂项支出', '014', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('71', '70', '其它支出', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('72', '-1', '购买投资', '015', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('73', '-1', '余额调整支出', '016', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('74', '19', '火车', '009', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('79', '60', '红包', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('81', '60', '送礼', '006', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `expend_type_sys` VALUES ('85', '19', '汽车', '010', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');

-- ----------------------------
-- Table structure for lend
-- ----------------------------
DROP TABLE IF EXISTS `lend`;
CREATE TABLE `lend` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `lend_or_borrow` int(11) DEFAULT NULL,
  `lend_sum` double DEFAULT NULL,
  `has_refunded_data` double DEFAULT NULL,
  `lend_person` varchar(20) DEFAULT NULL,
  `lend_purpose` varchar(100) DEFAULT NULL,
  `lend_date` datetime DEFAULT NULL,
  `plan_refund_date` datetime DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  `has_refunded` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fk_user_id` int(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_lend_lookup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lend
-- ----------------------------

-- ----------------------------
-- Table structure for receipts
-- ----------------------------
DROP TABLE IF EXISTS `receipts`;
CREATE TABLE `receipts` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rec_sum` double DEFAULT NULL,
  `rec_date` datetime DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fk_receipts_type_id` int(11) DEFAULT NULL,
  `fk_user_id` int(20) DEFAULT NULL,
  `fk_com_event_id` int(20) DEFAULT NULL,
  `fk_com_person_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_receipts_lookup` (`id`),
  KEY `fk_com_event_id` (`fk_com_event_id`),
  KEY `fk_com_person_id` (`fk_com_person_id`),
  CONSTRAINT `receipts_ibfk_1` FOREIGN KEY (`fk_com_event_id`) REFERENCES `com_event` (`id`),
  CONSTRAINT `receipts_ibfk_2` FOREIGN KEY (`fk_com_person_id`) REFERENCES `com_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of receipts
-- ----------------------------

-- ----------------------------
-- Table structure for receipts_type
-- ----------------------------
DROP TABLE IF EXISTS `receipts_type`;
CREATE TABLE `receipts_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` int(11) DEFAULT NULL,
  `receipts_type_name` varchar(20) DEFAULT NULL,
  `order_num` varchar(10) DEFAULT NULL,
  `is_sys_own` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `fk_user_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_receipts_type_lookup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收入类型';

-- ----------------------------
-- Records of receipts_type
-- ----------------------------

-- ----------------------------
-- Table structure for receipts_type_sys
-- ----------------------------
DROP TABLE IF EXISTS `receipts_type_sys`;
CREATE TABLE `receipts_type_sys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` int(11) DEFAULT NULL,
  `receipts_type_name` varchar(20) DEFAULT NULL,
  `order_num` varchar(10) DEFAULT NULL,
  `is_sys_own` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_receipts_type_lookup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='系统自带的收入类型';

-- ----------------------------
-- Records of receipts_type_sys
-- ----------------------------
INSERT INTO `receipts_type_sys` VALUES ('1', '-1', '职业工薪', '001', '1', '', '2018-02-22 10:09:17', '2018-04-12 22:03:50');
INSERT INTO `receipts_type_sys` VALUES ('2', '1', '工资', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('3', '1', '奖金', '002', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('4', '1', '加班', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('5', '1', '津贴补助', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('6', '1', '兼职', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('7', '1', '其它', '006', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('8', '-1', '投资保险', '002', '1', '', '2018-02-22 10:09:17', '2018-04-12 22:46:59');
INSERT INTO `receipts_type_sys` VALUES ('9', '8', '利息收入', '001', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('13', '-1', '出售资产', '003', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('14', '-1', '余额调整', '004', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');
INSERT INTO `receipts_type_sys` VALUES ('15', '-1', '其它收入', '005', '1', null, '2018-02-22 10:09:17', '2018-02-22 10:09:17');

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lend_or_borrow` int(11) DEFAULT NULL,
  `refund_person` varchar(20) DEFAULT NULL,
  `refund_sum` double DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fk_user_id` bigint(20) DEFAULT NULL,
  `fk_lend_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_refund_lookup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of refund
-- ----------------------------

-- ----------------------------
-- Table structure for sys_en_configuration
-- ----------------------------
DROP TABLE IF EXISTS `sys_en_configuration`;
CREATE TABLE `sys_en_configuration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conf_name` varchar(100) NOT NULL,
  `conf_key` varchar(100) DEFAULT NULL,
  `conf_value` varchar(500) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `fk_parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `conf_key` (`conf_key`),
  KEY `fk_parent_id` (`fk_parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_en_configuration
-- ----------------------------
INSERT INTO `sys_en_configuration` VALUES ('1', '管理端首页配置信息', 'manageIndexKey', 'manageIndexVal', '', '2018-04-22 14:09:16', '2018-04-22 14:09:16', '-1');
INSERT INTO `sys_en_configuration` VALUES ('2', '管理端首页欢迎语', 'manageIndexTopTitle', '欢迎使用数据管理系统', '', '2018-04-22 14:09:35', '2018-04-22 14:09:35', '1');
INSERT INTO `sys_en_configuration` VALUES ('3', '管理端首页底部版权', 'manageIndexBottomCopyright', 'Copyright 2018 ZhaoJianGuo. AllRightsReserved.', '', '2018-04-22 14:09:51', '2018-04-22 14:09:51', '-1');

-- ----------------------------
-- Table structure for sys_en_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_en_function`;
CREATE TABLE `sys_en_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` bigint(20) DEFAULT NULL,
  `fk_sys_en_menu_id` bigint(20) DEFAULT NULL,
  `func_identify` varchar(100) NOT NULL,
  `func_name` varchar(50) NOT NULL,
  `func_code` varchar(100) NOT NULL,
  `is_leaf` smallint(6) DEFAULT NULL,
  `is_expanded` smallint(6) DEFAULT NULL,
  `order_code` varchar(50) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parent_id` (`fk_parent_id`),
  KEY `fk_sys_en_menu_id` (`fk_sys_en_menu_id`),
  CONSTRAINT `sys_en_function_ibfk_2` FOREIGN KEY (`fk_sys_en_menu_id`) REFERENCES `sys_en_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_en_function
-- ----------------------------
INSERT INTO `sys_en_function` VALUES ('8', '-1', null, '', '系统管理', '', '0', '1', '002', '2017-09-17 16:55:04', '2017-09-17 16:55:04');
INSERT INTO `sys_en_function` VALUES ('9', '-1', null, '', '框架示例', '', '0', '1', '001', '2017-09-17 16:55:54', '2017-09-17 16:55:54');
INSERT INTO `sys_en_function` VALUES ('11', '9', '17', '', '表单控件示例', 'formExample', '0', '1', '001', '2017-10-11 12:50:43', '2017-10-11 12:50:43');
INSERT INTO `sys_en_function` VALUES ('12', '11', '17', '', '添加', 'baseAdd', '1', '1', '001', '2017-10-11 12:53:41', '2017-10-11 12:53:41');
INSERT INTO `sys_en_function` VALUES ('13', '11', '17', '', '编辑', 'baseEdit', '1', '1', '002', '2017-10-11 12:54:27', '2017-10-11 12:54:27');
INSERT INTO `sys_en_function` VALUES ('14', '11', '17', '', '多选删除', 'baseDelete', '1', '1', '003', '2017-10-11 12:55:14', '2017-10-11 12:56:41');
INSERT INTO `sys_en_function` VALUES ('15', '11', '17', '', '基本ajax异步请求', 'exampleBeanOpeSingDataAjaxReq', '1', '1', '504', '2017-10-11 12:56:21', '2017-10-11 13:04:52');
INSERT INTO `sys_en_function` VALUES ('16', '11', '17', '', '自定义操作(带参)', 'exampleBeanUserDefineOpe1', '1', '1', '505', '2017-10-11 13:00:16', '2017-10-11 13:04:46');
INSERT INTO `sys_en_function` VALUES ('17', '11', '17', '', '自定义操作(不带参)', 'exampleBeanUserDefineOpe2', '1', '1', '506', '2017-10-11 13:00:49', '2017-10-11 13:04:40');
INSERT INTO `sys_en_function` VALUES ('18', '11', '17', '', '打开新tab(带参，自定义页面)', 'openNewTab1', '1', '1', '507', '2017-10-11 13:01:25', '2017-10-11 13:04:34');
INSERT INTO `sys_en_function` VALUES ('19', '11', '17', '', '打开新tab(不带参，自定义页面)', 'openNewTab2', '1', '1', '508', '2017-10-11 13:02:08', '2017-10-11 13:04:25');
INSERT INTO `sys_en_function` VALUES ('20', '11', '17', '', '打开新tab(框架列表页面)', 'openNewTab3', '1', '1', '509', '2017-10-11 13:02:44', '2017-10-11 13:04:18');
INSERT INTO `sys_en_function` VALUES ('21', '11', '17', '', '打开浏览器窗口(带参)', 'openNewBrowserWindow1', '1', '1', '510', '2017-10-11 13:03:19', '2017-10-11 13:04:09');
INSERT INTO `sys_en_function` VALUES ('22', '11', '17', '', '打开浏览器窗口(不带参)', 'openNewBrowserWindow2', '1', '1', '511', '2017-10-11 13:03:57', '2017-10-11 13:03:57');
INSERT INTO `sys_en_function` VALUES ('23', '11', '17', '', '基本ajax请求', 'menu1', '1', '1', '004', '2017-10-11 13:05:41', '2017-10-11 13:05:41');
INSERT INTO `sys_en_function` VALUES ('24', '11', '17', '', '自定义操作', 'menu5', '1', '1', '008', '2017-10-11 13:06:26', '2017-10-11 13:08:58');
INSERT INTO `sys_en_function` VALUES ('25', '11', '17', '', '修改多个字段的值', 'menu2', '1', '1', '005', '2017-10-11 13:07:12', '2017-10-11 13:09:10');
INSERT INTO `sys_en_function` VALUES ('26', '11', '17', '', '修改Double值', 'menu3', '1', '1', '006', '2017-10-11 13:07:49', '2017-10-11 13:09:18');
INSERT INTO `sys_en_function` VALUES ('27', '11', '17', '', '修改TextArea值', 'menu4', '1', '1', '007', '2017-10-11 13:08:16', '2017-10-11 13:09:27');
INSERT INTO `sys_en_function` VALUES ('28', '-1', '34', 'account', '财务', 'account', '0', '1', '003', '2018-03-04 01:01:44', '2018-04-11 17:07:58');
INSERT INTO `sys_en_function` VALUES ('29', '28', '41', 'lendOrBorrow', '借贷管理', 'lendOrBorrow', '0', '1', '2', '2018-03-04 01:04:43', '2018-04-02 23:21:45');
INSERT INTO `sys_en_function` VALUES ('33', '29', '42', 'lend', '借款管理', 'lend', '1', '1', '1', '2018-04-02 23:23:30', '2018-04-02 23:24:09');
INSERT INTO `sys_en_function` VALUES ('34', '33', '42', '', '添加', 'baseAdd', '1', '1', '1', '2018-04-02 23:24:58', '2018-04-02 23:24:58');
INSERT INTO `sys_en_function` VALUES ('35', '33', '42', '', '多选删除', 'baseDelete', '1', '1', '3', '2018-04-02 23:25:33', '2018-04-11 17:15:58');
INSERT INTO `sys_en_function` VALUES ('36', '28', '38', 'receiptsAndExpend', '收支管理', 'receiptsAndExpend', '0', '1', '1', '2018-04-02 23:27:31', '2018-04-02 23:27:31');
INSERT INTO `sys_en_function` VALUES ('37', '36', '40', 'ReceiptsList', '收入管理', '1', '0', '1', '1', '2018-04-02 23:28:44', '2018-04-02 23:28:44');
INSERT INTO `sys_en_function` VALUES ('38', '37', '40', '', '添加', 'baseAdd', '1', '1', '1', '2018-04-02 23:30:19', '2018-04-02 23:30:19');
INSERT INTO `sys_en_function` VALUES ('39', '37', '40', '', '多选删除', 'baseDelete', '1', '1', '3', '2018-04-02 23:31:09', '2018-04-11 17:14:06');
INSERT INTO `sys_en_function` VALUES ('40', '36', '39', 'ExpendList', '支出管理', 'ExpendList', '0', '1', '2', '2018-04-02 23:32:51', '2018-04-02 23:32:51');
INSERT INTO `sys_en_function` VALUES ('41', '40', '39', '', '添加', 'baseAdd', '1', '1', '1', '2018-04-02 23:33:33', '2018-04-02 23:33:33');
INSERT INTO `sys_en_function` VALUES ('42', '40', '39', '', '多选删除', 'baseDelete', '1', '1', '3', '2018-04-02 23:34:04', '2018-04-11 17:14:59');
INSERT INTO `sys_en_function` VALUES ('43', '-1', '44', 'omnipotent', '生活大爆炸', 'omnipotent', '0', '1', '004', '2018-04-11 16:54:20', '2018-04-11 16:57:26');
INSERT INTO `sys_en_function` VALUES ('44', '43', '45', 'ComEventList', '事记', 'ComEventList', '0', '1', '02', '2018-04-11 17:09:49', '2018-04-11 21:42:26');
INSERT INTO `sys_en_function` VALUES ('45', '44', '45', '', '添加', 'baseAdd', '1', '1', '01', '2018-04-11 17:10:36', '2018-04-11 21:44:57');
INSERT INTO `sys_en_function` VALUES ('46', '44', '45', '', '多选删除', 'baseDelete', '1', '1', '03', '2018-04-11 17:11:11', '2018-04-11 21:45:08');
INSERT INTO `sys_en_function` VALUES ('47', '37', '40', '', '编辑', 'baseEdit', '1', '1', '2', '2018-04-11 17:13:57', '2018-04-11 17:13:57');
INSERT INTO `sys_en_function` VALUES ('48', '40', '39', '', '编辑', 'baseEdit', '1', '1', '2', '2018-04-11 17:14:50', '2018-04-11 17:14:50');
INSERT INTO `sys_en_function` VALUES ('49', '33', '42', '', '编辑', 'baseEdit', '1', '1', '2', '2018-04-11 17:15:49', '2018-04-11 17:15:49');
INSERT INTO `sys_en_function` VALUES ('51', '44', '45', '', '编辑', 'baseEdit', '1', '1', '02', '2018-04-11 17:17:08', '2018-04-11 21:45:03');
INSERT INTO `sys_en_function` VALUES ('52', '43', '44', 'ComPersonList', '通讯录', 'ComPersonList', '0', '1', '01', '2018-04-11 21:41:55', '2018-04-11 21:41:55');
INSERT INTO `sys_en_function` VALUES ('53', '52', '46', '', '添加', 'baseAdd', '1', '1', '01', '2018-04-11 21:43:36', '2018-04-11 21:43:36');
INSERT INTO `sys_en_function` VALUES ('54', '52', '46', '', '编辑', 'baseEdit', '1', '1', '02', '2018-04-11 21:44:05', '2018-04-11 21:44:05');
INSERT INTO `sys_en_function` VALUES ('55', '52', '46', '', '多选删除', 'baseDelete', '1', '1', '03', '2018-04-11 21:44:40', '2018-04-11 21:44:40');
INSERT INTO `sys_en_function` VALUES ('56', '43', '48', 'ComEventsIntervalList', '事频记录', 'ComEventsIntervalList', '0', '1', '03', '2018-04-11 22:57:38', '2018-04-11 22:57:38');
INSERT INTO `sys_en_function` VALUES ('57', '56', '48', '', '添加', 'baseAdd', '1', '1', '01', '2018-04-11 22:58:11', '2018-04-11 22:58:11');
INSERT INTO `sys_en_function` VALUES ('58', '56', '48', '', '编辑', 'baseEdit', '1', '1', '02', '2018-04-11 22:58:36', '2018-04-11 22:58:36');
INSERT INTO `sys_en_function` VALUES ('59', '56', '48', '', '多选删除', 'baseDelete', '1', '1', '03', '2018-04-11 22:59:04', '2018-04-11 22:59:04');
INSERT INTO `sys_en_function` VALUES ('60', '28', '34', 'typeManage', '收支类型', 'typeManage', '0', '1', '003', '2018-04-13 21:07:38', '2018-04-13 21:07:38');
INSERT INTO `sys_en_function` VALUES ('61', '60', '37', 'ReceiptsTypeList', '收入类型', 'ReceiptsTypeList', '0', '1', '001', '2018-04-13 21:09:18', '2018-04-13 21:09:18');
INSERT INTO `sys_en_function` VALUES ('62', '60', '36', 'ExpendTypeList', '支出类型', 'ExpendTypeList', '0', '1', '002', '2018-04-13 21:10:23', '2018-04-13 21:10:23');
INSERT INTO `sys_en_function` VALUES ('63', '60', '49', 'ReceiptsTypeSysList', '收入类型-系统自带', 'ReceiptsTypeSysList', '0', '1', '003', '2018-04-13 21:11:10', '2018-04-13 21:11:10');
INSERT INTO `sys_en_function` VALUES ('64', '60', '50', 'ExpendTypeSysList', '支出类型-系统自带', 'ExpendTypeSysList', '0', '1', '004', '2018-04-13 21:11:57', '2018-04-13 21:11:57');
INSERT INTO `sys_en_function` VALUES ('65', '61', '37', '', '添加', 'baseAdd', '1', '1', '001', '2018-04-13 21:13:07', '2018-04-13 21:13:07');
INSERT INTO `sys_en_function` VALUES ('66', '61', '37', '', '编辑', 'baseEdit', '1', '1', '002', '2018-04-13 21:13:39', '2018-04-13 21:13:39');
INSERT INTO `sys_en_function` VALUES ('67', '61', '37', '', '多选删除', 'baseDelete', '1', '1', '003', '2018-04-13 21:14:29', '2018-04-13 21:14:29');
INSERT INTO `sys_en_function` VALUES ('68', '62', '36', '', '添加', 'baseAdd', '1', '1', '001', '2018-04-13 21:15:00', '2018-04-13 21:15:00');
INSERT INTO `sys_en_function` VALUES ('69', '62', '36', '', '编辑', 'baseEdit', '1', '1', '002', '2018-04-13 21:15:31', '2018-04-13 21:15:31');
INSERT INTO `sys_en_function` VALUES ('70', '62', '36', '', '多选删除', 'baseDelete', '1', '1', '003', '2018-04-13 21:16:04', '2018-04-13 21:16:04');
INSERT INTO `sys_en_function` VALUES ('71', '63', '49', '', '添加', 'baseAdd', '1', '1', '001', '2018-04-13 21:16:42', '2018-04-13 21:16:42');
INSERT INTO `sys_en_function` VALUES ('72', '63', '49', '', '编辑', 'baseEdit', '1', '1', '002', '2018-04-13 21:17:07', '2018-04-13 21:17:07');
INSERT INTO `sys_en_function` VALUES ('73', '63', '49', '', '多选删除', 'baseDelete', '1', '1', '003', '2018-04-13 21:17:29', '2018-04-13 21:17:47');
INSERT INTO `sys_en_function` VALUES ('74', '64', '50', '', '添加', 'baseAdd', '1', '1', '001', '2018-04-13 21:18:36', '2018-04-13 21:18:36');
INSERT INTO `sys_en_function` VALUES ('75', '64', '50', '', '编辑', 'baseEdit', '1', '1', '002', '2018-04-13 21:19:14', '2018-04-13 21:19:14');
INSERT INTO `sys_en_function` VALUES ('76', '64', '50', '', '多选删除', 'baseDelete', '1', '1', '003', '2018-04-13 21:19:44', '2018-04-13 21:19:44');
INSERT INTO `sys_en_function` VALUES ('77', '33', '42', 'lendRefundMenu', '还款', 'lendRefundMenu', '1', '1', '003', '2018-04-13 23:39:05', '2018-04-13 23:39:05');
INSERT INTO `sys_en_function` VALUES ('78', '61', '37', '', '一键生成默认收入类型', 'copyReceiptsTypeFromSys', '1', '1', '004', '2018-04-14 13:46:08', '2018-04-14 17:02:45');
INSERT INTO `sys_en_function` VALUES ('79', '62', '36', '', '一键生成默认支出类型', 'copyExpendTypeFromSys', '1', '1', '004', '2018-04-14 13:46:49', '2018-04-14 17:03:01');
INSERT INTO `sys_en_function` VALUES ('80', '28', '51', 'accountStatisticsOverview', '统计概览', 'accountStatisticsOverview', '0', '1', '000', '2018-04-15 13:43:52', '2018-04-15 13:43:52');
INSERT INTO `sys_en_function` VALUES ('81', '80', '52', 'StatisticsList', '概览', 'StatisticsList', '0', '1', '001', '2018-04-15 13:45:53', '2018-04-15 13:48:05');
INSERT INTO `sys_en_function` VALUES ('82', '81', '52', '', '添加', 'baseAdd', '1', '1', '001', '2018-04-15 13:46:37', '2018-04-15 13:46:37');
INSERT INTO `sys_en_function` VALUES ('83', '81', '52', '', '编辑', 'baseEdit', '1', '1', '002', '2018-04-15 13:47:00', '2018-04-15 13:47:00');
INSERT INTO `sys_en_function` VALUES ('84', '81', '52', '', '多选删除', 'baseDelete', '1', '1', '003', '2018-04-15 13:47:37', '2018-04-15 13:47:37');

-- ----------------------------
-- Table structure for sys_en_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_en_menu`;
CREATE TABLE `sys_en_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `menu_identify` varchar(100) DEFAULT NULL COMMENT '菜单唯一字符串标识符',
  `text` varchar(50) NOT NULL COMMENT '菜单名称',
  `link` varchar(500) DEFAULT NULL COMMENT '菜单链接地址',
  `qtip` varchar(100) DEFAULT NULL COMMENT '菜单提示',
  `is_leaf` smallint(6) NOT NULL COMMENT '是否叶子节点(0否,1是)',
  `is_expanded` smallint(6) DEFAULT NULL COMMENT '是否展开子节点',
  `is_self_url` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否为平台内部链接地址(1：是，0：否)',
  `order_code` varchar(50) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sem_fk_parent_id` (`fk_parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_en_menu
-- ----------------------------
INSERT INTO `sys_en_menu` VALUES ('1', '-1', null, '根结点', '', 'root', '0', '1', '0', '0', null, null);
INSERT INTO `sys_en_menu` VALUES ('13', '1', null, '框架示例', null, '高级用户使用', '0', '1', '1', '001', null, null);
INSERT INTO `sys_en_menu` VALUES ('16', '13', 'ExampleBeanFormVal', '表单验证示例', 'exampleBeanFormVal/search.do', '表单验证示例', '1', '1', '1', '002', null, null);
INSERT INTO `sys_en_menu` VALUES ('17', '13', 'ExampleBeanList', '表单控件示例', 'exampleBean/search.do', '表单控件示例', '1', '1', '1', '003', null, null);
INSERT INTO `sys_en_menu` VALUES ('20', '1', 'systemManage', '系统管理', '', '系统管理', '0', '1', '1', '000', null, '2017-09-27 22:03:10');
INSERT INTO `sys_en_menu` VALUES ('21', '20', 'SysFunctionList', '功能配置管理', 'sysFunction/search.do', '功能配置管理', '1', '1', '1', '06', null, '2018-04-11 16:51:24');
INSERT INTO `sys_en_menu` VALUES ('22', '20', 'BaseConstantList', '常量管理', 'baseConstant/search.do', '常量管理', '1', '1', '1', '01', null, '2018-04-11 16:49:03');
INSERT INTO `sys_en_menu` VALUES ('23', '20', 'SysEnConfList', '系统配置', 'sysEnConf/search.do', '系统配置', '1', '1', '1', '02', null, '2018-04-11 16:49:20');
INSERT INTO `sys_en_menu` VALUES ('24', '20', 'SysMenuList', '菜单配置管理', 'sysMenu/search.do', '菜单配置管理', '1', '1', '1', '05', null, '2018-04-11 16:51:11');
INSERT INTO `sys_en_menu` VALUES ('32', '20', 'SysRoleList', '角色管理', 'sysRole/search.do', '', '1', '1', '1', '03', '2017-09-20 22:38:17', '2018-04-11 16:48:45');
INSERT INTO `sys_en_menu` VALUES ('33', '20', 'SysUserList', '用户管理', 'sysUser/search.do', '', '1', '1', '1', '04', '2017-09-28 22:02:59', '2018-04-11 16:49:57');
INSERT INTO `sys_en_menu` VALUES ('34', '1', 'account', '财务', '', '', '0', '1', '1', '3', '2018-03-01 13:22:26', '2018-03-01 13:30:30');
INSERT INTO `sys_en_menu` VALUES ('35', '34', 'typeManage', '收支类型', '', '', '0', '1', '1', '3', '2018-03-01 13:23:36', '2018-03-03 19:45:12');
INSERT INTO `sys_en_menu` VALUES ('36', '35', 'ExpendTypeList', '支出类型', 'expendType/search.do', '', '1', '1', '1', '2', '2018-03-01 13:24:21', '2018-03-01 13:24:21');
INSERT INTO `sys_en_menu` VALUES ('37', '35', 'ReceiptsTypeList', '收入类型', 'receiptsType/search.do', '', '1', '1', '1', '1', '2018-03-03 18:32:53', '2018-03-03 18:32:53');
INSERT INTO `sys_en_menu` VALUES ('38', '34', 'receiptsAndExpend', '收支管理', '', '', '0', '1', '1', '1', '2018-03-03 19:43:13', '2018-03-03 19:43:13');
INSERT INTO `sys_en_menu` VALUES ('39', '38', 'ExpendList', '支出管理', 'expend/search.do', '', '1', '1', '1', '2', '2018-03-03 19:43:59', '2018-03-03 19:43:59');
INSERT INTO `sys_en_menu` VALUES ('40', '38', 'ReceiptsList', '收入管理 ', 'receipts/search.do', '', '1', '1', '1', '1', '2018-03-03 22:38:14', '2018-03-03 22:38:14');
INSERT INTO `sys_en_menu` VALUES ('41', '34', 'lendOrBorrow', '借贷管理', '', '', '0', '1', '1', '2', '2018-03-03 23:50:43', '2018-03-03 23:50:43');
INSERT INTO `sys_en_menu` VALUES ('42', '41', 'LendList', '借款管理', 'lend/search.do', '', '1', '1', '1', '1', '2018-03-03 23:52:03', '2018-03-03 23:52:03');
INSERT INTO `sys_en_menu` VALUES ('44', '1', 'omnipotent', '生活大爆炸', '', '', '0', '1', '1', '4', '2018-04-10 00:06:57', '2018-04-10 00:06:57');
INSERT INTO `sys_en_menu` VALUES ('45', '44', 'ComEventList', '事记', 'comEvent/search.do', '事件记录', '1', '1', '1', '02', '2018-04-10 00:08:30', '2018-04-11 21:40:42');
INSERT INTO `sys_en_menu` VALUES ('46', '44', 'ComPersonList', '通讯录', 'comPerson/search.do', '', '1', '1', '1', '01', '2018-04-11 21:40:20', '2018-04-11 21:40:20');
INSERT INTO `sys_en_menu` VALUES ('48', '44', 'ComEventsIntervalList', '事频记录', 'comEventsInterval/search.do', '事件发生频率记录', '1', '1', '1', '03', '2018-04-11 22:56:57', '2018-04-11 22:56:57');
INSERT INTO `sys_en_menu` VALUES ('49', '35', 'ReceiptsTypeSysList', '收入类型-系统自带', 'receiptsTypeSys/search.do', '系统自带类型，平台管理员维护', '1', '1', '1', '3', '2018-04-12 21:50:08', '2018-04-12 21:50:08');
INSERT INTO `sys_en_menu` VALUES ('50', '35', 'ExpendTypeSysList', '支出类型-系统自带', 'expendTypeSys/search.do', '系统自带类型，平台管理员维护', '1', '1', '1', '4', '2018-04-12 21:52:01', '2018-04-12 21:53:06');
INSERT INTO `sys_en_menu` VALUES ('51', '34', 'accountStatisticsOverview', '统计概览', '', '', '0', '1', '1', '000', '2018-04-15 13:40:49', '2018-04-15 13:40:49');
INSERT INTO `sys_en_menu` VALUES ('52', '51', 'StatisticsList', '概览', 'statistics/search.do', '收入、支出、借款概况', '1', '1', '1', '001', '2018-04-15 13:42:34', '2018-04-15 13:42:34');

-- ----------------------------
-- Table structure for sys_en_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_en_role`;
CREATE TABLE `sys_en_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_parent_id` bigint(20) DEFAULT NULL,
  `role_code` varchar(100) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_status` varchar(2) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `order_code` varchar(100) DEFAULT NULL,
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parent_id` (`fk_parent_id`),
  KEY `role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_en_role
-- ----------------------------
INSERT INTO `sys_en_role` VALUES ('1', '-1', 'superAdmin', '超级管理员', '1', '超级管理员', '001', '2016-10-30 23:20:53', '2018-04-22 14:11:33');
INSERT INTO `sys_en_role` VALUES ('2', '-1', 'develop', '开发人员', '1', '', '002', '2018-03-05 13:01:13', '2018-04-22 14:12:05');
INSERT INTO `sys_en_role` VALUES ('4', '-1', 'financeGroup', '体验用户群', '1', '', '003', '2018-03-05 13:03:30', '2018-04-22 14:12:12');
INSERT INTO `sys_en_role` VALUES ('5', '4', 'general', '普通用户', '1', '', '001', '2018-03-05 13:05:19', '2018-04-22 14:12:39');

-- ----------------------------
-- Table structure for sys_en_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_en_user`;
CREATE TABLE `sys_en_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `login_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_status` varchar(2) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_en_user
-- ----------------------------
INSERT INTO `sys_en_user` VALUES ('1', '系统管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '', null, '2017-10-15 00:41:52');
INSERT INTO `sys_en_user` VALUES ('3', '普通用户1', 'pub1', 'e10adc3949ba59abbe56e057f20f883e', '1', '', '2018-04-02 23:01:12', '2018-04-13 21:31:04');
INSERT INTO `sys_en_user` VALUES ('4', '开发人员1', 'develop1', 'e10adc3949ba59abbe56e057f20f883e', '1', '', '2018-04-22 14:16:08', '2018-04-22 14:16:08');

-- ----------------------------
-- Table structure for sys_re_menu_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_menu_func`;
CREATE TABLE `sys_re_menu_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_menu_id` bigint(20) NOT NULL,
  `fk_func_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_srmf_fk_menu_id` (`fk_menu_id`),
  KEY `fk_srmf_fk_func_id` (`fk_func_id`),
  CONSTRAINT `fk_srmf_fk_func_id` FOREIGN KEY (`fk_func_id`) REFERENCES `sys_en_function` (`id`),
  CONSTRAINT `fk_srmf_fk_menu_id` FOREIGN KEY (`fk_menu_id`) REFERENCES `sys_en_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_re_menu_func
-- ----------------------------

-- ----------------------------
-- Table structure for sys_re_role_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_func`;
CREATE TABLE `sys_re_role_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_role_id` bigint(20) NOT NULL,
  `fk_func_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_srrf_fk_role_id` (`fk_role_id`),
  KEY `fk_srrf_fk_func_id` (`fk_func_id`),
  CONSTRAINT `fk_srrf_fk_role_id` FOREIGN KEY (`fk_role_id`) REFERENCES `sys_en_role` (`id`),
  CONSTRAINT `sys_re_role_func_ibfk_1` FOREIGN KEY (`fk_func_id`) REFERENCES `sys_en_function` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=926 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_re_role_func
-- ----------------------------
INSERT INTO `sys_re_role_func` VALUES ('800', '1', '9');
INSERT INTO `sys_re_role_func` VALUES ('801', '1', '11');
INSERT INTO `sys_re_role_func` VALUES ('802', '1', '12');
INSERT INTO `sys_re_role_func` VALUES ('803', '1', '13');
INSERT INTO `sys_re_role_func` VALUES ('804', '1', '14');
INSERT INTO `sys_re_role_func` VALUES ('805', '1', '23');
INSERT INTO `sys_re_role_func` VALUES ('806', '1', '25');
INSERT INTO `sys_re_role_func` VALUES ('807', '1', '26');
INSERT INTO `sys_re_role_func` VALUES ('808', '1', '27');
INSERT INTO `sys_re_role_func` VALUES ('809', '1', '24');
INSERT INTO `sys_re_role_func` VALUES ('810', '1', '15');
INSERT INTO `sys_re_role_func` VALUES ('811', '1', '16');
INSERT INTO `sys_re_role_func` VALUES ('812', '1', '17');
INSERT INTO `sys_re_role_func` VALUES ('813', '1', '18');
INSERT INTO `sys_re_role_func` VALUES ('814', '1', '19');
INSERT INTO `sys_re_role_func` VALUES ('815', '1', '20');
INSERT INTO `sys_re_role_func` VALUES ('816', '1', '21');
INSERT INTO `sys_re_role_func` VALUES ('817', '1', '22');
INSERT INTO `sys_re_role_func` VALUES ('818', '1', '28');
INSERT INTO `sys_re_role_func` VALUES ('819', '1', '60');
INSERT INTO `sys_re_role_func` VALUES ('820', '1', '63');
INSERT INTO `sys_re_role_func` VALUES ('821', '1', '71');
INSERT INTO `sys_re_role_func` VALUES ('822', '1', '72');
INSERT INTO `sys_re_role_func` VALUES ('823', '1', '73');
INSERT INTO `sys_re_role_func` VALUES ('824', '1', '64');
INSERT INTO `sys_re_role_func` VALUES ('825', '1', '74');
INSERT INTO `sys_re_role_func` VALUES ('826', '1', '75');
INSERT INTO `sys_re_role_func` VALUES ('827', '1', '76');
INSERT INTO `sys_re_role_func` VALUES ('828', '2', '9');
INSERT INTO `sys_re_role_func` VALUES ('829', '2', '11');
INSERT INTO `sys_re_role_func` VALUES ('830', '2', '12');
INSERT INTO `sys_re_role_func` VALUES ('831', '2', '13');
INSERT INTO `sys_re_role_func` VALUES ('832', '2', '14');
INSERT INTO `sys_re_role_func` VALUES ('833', '2', '23');
INSERT INTO `sys_re_role_func` VALUES ('834', '2', '25');
INSERT INTO `sys_re_role_func` VALUES ('835', '2', '26');
INSERT INTO `sys_re_role_func` VALUES ('836', '2', '27');
INSERT INTO `sys_re_role_func` VALUES ('837', '2', '24');
INSERT INTO `sys_re_role_func` VALUES ('838', '2', '15');
INSERT INTO `sys_re_role_func` VALUES ('839', '2', '16');
INSERT INTO `sys_re_role_func` VALUES ('840', '2', '17');
INSERT INTO `sys_re_role_func` VALUES ('841', '2', '18');
INSERT INTO `sys_re_role_func` VALUES ('842', '2', '19');
INSERT INTO `sys_re_role_func` VALUES ('843', '2', '20');
INSERT INTO `sys_re_role_func` VALUES ('844', '2', '21');
INSERT INTO `sys_re_role_func` VALUES ('845', '2', '22');
INSERT INTO `sys_re_role_func` VALUES ('886', '5', '28');
INSERT INTO `sys_re_role_func` VALUES ('887', '5', '60');
INSERT INTO `sys_re_role_func` VALUES ('888', '5', '61');
INSERT INTO `sys_re_role_func` VALUES ('889', '5', '65');
INSERT INTO `sys_re_role_func` VALUES ('890', '5', '66');
INSERT INTO `sys_re_role_func` VALUES ('891', '5', '67');
INSERT INTO `sys_re_role_func` VALUES ('892', '5', '78');
INSERT INTO `sys_re_role_func` VALUES ('893', '5', '62');
INSERT INTO `sys_re_role_func` VALUES ('894', '5', '68');
INSERT INTO `sys_re_role_func` VALUES ('895', '5', '69');
INSERT INTO `sys_re_role_func` VALUES ('896', '5', '70');
INSERT INTO `sys_re_role_func` VALUES ('897', '5', '79');
INSERT INTO `sys_re_role_func` VALUES ('898', '5', '36');
INSERT INTO `sys_re_role_func` VALUES ('899', '5', '37');
INSERT INTO `sys_re_role_func` VALUES ('900', '5', '38');
INSERT INTO `sys_re_role_func` VALUES ('901', '5', '47');
INSERT INTO `sys_re_role_func` VALUES ('902', '5', '39');
INSERT INTO `sys_re_role_func` VALUES ('903', '5', '40');
INSERT INTO `sys_re_role_func` VALUES ('904', '5', '41');
INSERT INTO `sys_re_role_func` VALUES ('905', '5', '48');
INSERT INTO `sys_re_role_func` VALUES ('906', '5', '42');
INSERT INTO `sys_re_role_func` VALUES ('907', '5', '29');
INSERT INTO `sys_re_role_func` VALUES ('908', '5', '33');
INSERT INTO `sys_re_role_func` VALUES ('909', '5', '77');
INSERT INTO `sys_re_role_func` VALUES ('910', '5', '34');
INSERT INTO `sys_re_role_func` VALUES ('911', '5', '49');
INSERT INTO `sys_re_role_func` VALUES ('912', '5', '35');
INSERT INTO `sys_re_role_func` VALUES ('913', '5', '43');
INSERT INTO `sys_re_role_func` VALUES ('914', '5', '52');
INSERT INTO `sys_re_role_func` VALUES ('915', '5', '53');
INSERT INTO `sys_re_role_func` VALUES ('916', '5', '54');
INSERT INTO `sys_re_role_func` VALUES ('917', '5', '55');
INSERT INTO `sys_re_role_func` VALUES ('918', '5', '44');
INSERT INTO `sys_re_role_func` VALUES ('919', '5', '45');
INSERT INTO `sys_re_role_func` VALUES ('920', '5', '51');
INSERT INTO `sys_re_role_func` VALUES ('921', '5', '46');
INSERT INTO `sys_re_role_func` VALUES ('922', '5', '56');
INSERT INTO `sys_re_role_func` VALUES ('923', '5', '57');
INSERT INTO `sys_re_role_func` VALUES ('924', '5', '58');
INSERT INTO `sys_re_role_func` VALUES ('925', '5', '59');

-- ----------------------------
-- Table structure for sys_re_role_func_exclude
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_func_exclude`;
CREATE TABLE `sys_re_role_func_exclude` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_role_id` bigint(20) NOT NULL,
  `fk_func_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_srrfe_fk_func_id` (`fk_func_id`),
  KEY `fk_srrfe_fk_role_id` (`fk_role_id`),
  CONSTRAINT `fk_srrfe_fk_func_id` FOREIGN KEY (`fk_func_id`) REFERENCES `sys_en_function` (`id`),
  CONSTRAINT `fk_srrfe_fk_role_id` FOREIGN KEY (`fk_role_id`) REFERENCES `sys_en_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_re_role_func_exclude
-- ----------------------------

-- ----------------------------
-- Table structure for sys_re_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_menu`;
CREATE TABLE `sys_re_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_role_id` bigint(20) NOT NULL,
  `fk_menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_srrm_fk_role_id` (`fk_role_id`),
  KEY `fk_srrm_fk_menu_id` (`fk_menu_id`),
  CONSTRAINT `fk_srrm_fk_menu_id` FOREIGN KEY (`fk_menu_id`) REFERENCES `sys_en_menu` (`id`),
  CONSTRAINT `fk_srrm_fk_role_id` FOREIGN KEY (`fk_role_id`) REFERENCES `sys_en_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=786 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_re_role_menu
-- ----------------------------
INSERT INTO `sys_re_role_menu` VALUES ('715', '1', '1');
INSERT INTO `sys_re_role_menu` VALUES ('716', '1', '20');
INSERT INTO `sys_re_role_menu` VALUES ('717', '1', '22');
INSERT INTO `sys_re_role_menu` VALUES ('718', '1', '23');
INSERT INTO `sys_re_role_menu` VALUES ('719', '1', '32');
INSERT INTO `sys_re_role_menu` VALUES ('720', '1', '33');
INSERT INTO `sys_re_role_menu` VALUES ('721', '1', '24');
INSERT INTO `sys_re_role_menu` VALUES ('722', '1', '21');
INSERT INTO `sys_re_role_menu` VALUES ('723', '1', '13');
INSERT INTO `sys_re_role_menu` VALUES ('724', '1', '16');
INSERT INTO `sys_re_role_menu` VALUES ('725', '1', '17');
INSERT INTO `sys_re_role_menu` VALUES ('726', '1', '34');
INSERT INTO `sys_re_role_menu` VALUES ('727', '1', '51');
INSERT INTO `sys_re_role_menu` VALUES ('728', '1', '52');
INSERT INTO `sys_re_role_menu` VALUES ('729', '1', '38');
INSERT INTO `sys_re_role_menu` VALUES ('730', '1', '40');
INSERT INTO `sys_re_role_menu` VALUES ('731', '1', '39');
INSERT INTO `sys_re_role_menu` VALUES ('732', '1', '41');
INSERT INTO `sys_re_role_menu` VALUES ('733', '1', '42');
INSERT INTO `sys_re_role_menu` VALUES ('734', '1', '35');
INSERT INTO `sys_re_role_menu` VALUES ('735', '1', '37');
INSERT INTO `sys_re_role_menu` VALUES ('736', '1', '36');
INSERT INTO `sys_re_role_menu` VALUES ('737', '1', '49');
INSERT INTO `sys_re_role_menu` VALUES ('738', '1', '50');
INSERT INTO `sys_re_role_menu` VALUES ('739', '1', '44');
INSERT INTO `sys_re_role_menu` VALUES ('740', '1', '46');
INSERT INTO `sys_re_role_menu` VALUES ('741', '1', '45');
INSERT INTO `sys_re_role_menu` VALUES ('742', '1', '48');
INSERT INTO `sys_re_role_menu` VALUES ('743', '2', '1');
INSERT INTO `sys_re_role_menu` VALUES ('744', '2', '20');
INSERT INTO `sys_re_role_menu` VALUES ('745', '2', '22');
INSERT INTO `sys_re_role_menu` VALUES ('746', '2', '23');
INSERT INTO `sys_re_role_menu` VALUES ('747', '2', '32');
INSERT INTO `sys_re_role_menu` VALUES ('748', '2', '33');
INSERT INTO `sys_re_role_menu` VALUES ('749', '2', '24');
INSERT INTO `sys_re_role_menu` VALUES ('750', '2', '21');
INSERT INTO `sys_re_role_menu` VALUES ('751', '2', '13');
INSERT INTO `sys_re_role_menu` VALUES ('752', '2', '16');
INSERT INTO `sys_re_role_menu` VALUES ('753', '2', '17');
INSERT INTO `sys_re_role_menu` VALUES ('770', '5', '1');
INSERT INTO `sys_re_role_menu` VALUES ('771', '5', '34');
INSERT INTO `sys_re_role_menu` VALUES ('772', '5', '51');
INSERT INTO `sys_re_role_menu` VALUES ('773', '5', '52');
INSERT INTO `sys_re_role_menu` VALUES ('774', '5', '38');
INSERT INTO `sys_re_role_menu` VALUES ('775', '5', '40');
INSERT INTO `sys_re_role_menu` VALUES ('776', '5', '39');
INSERT INTO `sys_re_role_menu` VALUES ('777', '5', '41');
INSERT INTO `sys_re_role_menu` VALUES ('778', '5', '42');
INSERT INTO `sys_re_role_menu` VALUES ('779', '5', '35');
INSERT INTO `sys_re_role_menu` VALUES ('780', '5', '37');
INSERT INTO `sys_re_role_menu` VALUES ('781', '5', '36');
INSERT INTO `sys_re_role_menu` VALUES ('782', '5', '44');
INSERT INTO `sys_re_role_menu` VALUES ('783', '5', '46');
INSERT INTO `sys_re_role_menu` VALUES ('784', '5', '45');
INSERT INTO `sys_re_role_menu` VALUES ('785', '5', '48');

-- ----------------------------
-- Table structure for sys_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_role`;
CREATE TABLE `sys_re_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_role_id` bigint(20) NOT NULL,
  `fk_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_srru_fk_role_id` (`fk_role_id`),
  KEY `fk_srru_fk_user_id` (`fk_user_id`),
  CONSTRAINT `fk_srru_fk_role_id` FOREIGN KEY (`fk_role_id`) REFERENCES `sys_en_role` (`id`),
  CONSTRAINT `fk_srru_fk_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `sys_en_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_re_user_role
-- ----------------------------
INSERT INTO `sys_re_user_role` VALUES ('74', '1', '1');
INSERT INTO `sys_re_user_role` VALUES ('78', '5', '3');
INSERT INTO `sys_re_user_role` VALUES ('79', '2', '4');
