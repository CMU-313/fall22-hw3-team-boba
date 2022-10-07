alter table T_REVIEW add column REV_DELETEDATE_D datetime;
update T_CONFIG set CFG_VALUE_C = '29' where CFG_ID_C = 'DB_VERSION';