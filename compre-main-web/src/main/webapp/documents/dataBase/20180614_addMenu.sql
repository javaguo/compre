-- 添加收入拆线统计菜单
INSERT INTO sys_en_menu (id, fk_parent_id, menu_identify, text, link, qtip, is_leaf, is_expanded, is_self_url, order_num, add_time, update_time)
VALUES ('55', '51', 'StatisticsReceiptEChart', '收入统计图', 'statisticsReceiptEChart/searchModel.do', '', '1', '1', '1', '3', '2018-06-11 00:17:11', '2018-06-11 00:17:11');

-- 添加支出拆线统计菜单
INSERT INTO sys_en_menu (id, fk_parent_id, menu_identify, text, link, qtip, is_leaf, is_expanded, is_self_url, order_num, add_time, update_time)
VALUES ('57', '51', 'StatisticsExpendEChart', '支出统计图', 'statisticsExpendEChart/searchModel.do', '', '1', '1', '1', '3', '2018-06-15 10:16:10', '2018-06-15 10:16:42');