-- 修改财务相关模块表名
ALTER  TABLE expend RENAME TO account_expend;
ALTER  TABLE expend_type RENAME TO account_expend_type;
ALTER  TABLE expend_type_sys RENAME TO account_expend_type_sys;
ALTER  TABLE lend RENAME TO account_lend;
ALTER  TABLE receipts RENAME TO account_receipts;
ALTER  TABLE receipts_type RENAME TO account_receipts_type;
ALTER  TABLE receipts_type_sys RENAME TO account_receipts_type_sys;
ALTER  TABLE refund RENAME TO account_refund;

-- 修改排序字段类型
alter table account_receipts_type_sys change order_num order_num int;
alter table account_receipts_type change order_num order_num int;
alter table account_expend_type_sys change order_num order_num int;
alter table account_expend_type change order_num order_num int;