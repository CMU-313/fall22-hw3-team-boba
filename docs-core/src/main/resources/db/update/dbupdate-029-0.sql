insert into T_REVIEW (REV_ID_C, REV_IDDOC_C, REV_IDUSER_C, REV_CREATEDATE_D) values ('1', '082d7e55-7dd7-43c4-a181-4fac3ec74ddb', 'admin', now());
update T_CONFIG set CFG_VALUE_C = '29' where CFG_ID_C = 'DB_VERSION';