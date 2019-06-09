ALTER table account_expend add fk_expend_way_id int;


INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('3', '支付宝', '2', 'expendWay', '1', '', '2019-06-08 16:47:58', '2019-06-08 16:47:58');
INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('5', '微信', '1', 'expendWay', '1', '', '2019-06-08 16:48:14', '2019-06-08 16:48:14');
INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('6', '现金', '3', 'expendWay', '1', '', '2019-06-08 16:48:41', '2019-06-08 16:48:41');
INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('7', '银行卡', '4', 'expendWay', '1', '', '2019-06-08 16:49:00', '2019-06-08 16:49:00');
INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('8', '信用卡', '5', 'expendWay', '1', '', '2019-06-08 16:50:18', '2019-06-08 16:50:18');
INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('9', '优惠券', '6', 'expendWay', '1', '', '2019-06-08 16:51:02', '2019-06-08 16:51:02');
INSERT INTO sys_en_constant (`id`, `name`, `code`, `namespace`, `is_valid`, `remark`, `add_time`, `update_time`) VALUES ('10', '其它', '7', 'expendWay', '1', '', '2019-06-08 16:51:20', '2019-06-08 16:51:20');
