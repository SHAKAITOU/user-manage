-- --------------
-- 20240620 --
ALTER TABLE m_bill CHANGE COLUMN memo bill_memo VARCHAR(255);
-- --------------

-- --------------
-- 20240721 --
ALTER TABLE m_user ADD COLUMN society_type VARCHAR(6) COMMENT '学会区分(F0023)';

ALTER TABLE m_user ADD COLUMN group_name VARCHAR(120) COMMENT '团体证名称';
ALTER TABLE m_user ADD COLUMN credit_code VARCHAR(120) COMMENT '统一社会信用代码';

-- 证件类型 团体证 --
INSERT INTO m_fixed_value VALUES ('F0010', '05', '团体证',      5);
-- 中国，甘肃区分 --
INSERT INTO m_fixed_value VALUES ('F0023', '01', '中国学会',    1);
INSERT INTO m_fixed_value VALUES ('F0023', '02', '甘肃学会',    2);
-- --------------

-- 20240727 --
ALTER TABLE m_user add COLUMN application_date  VARCHAR(20);

-- 用户审核状态 --
INSERT INTO m_fixed_value VALUES ('F0024', '01', '等待审核',		1);
INSERT INTO m_fixed_value VALUES ('F0024', '02', '审核通过',		2);
INSERT INTO m_fixed_value VALUES ('F0024', '03', '审核不通过',	3);
INSERT INTO m_fixed_value VALUES ('F0024', '04', '返回修改',		4);

-- 会员卡信息 --
DROP TABLE IF EXISTS m_user_card;
CREATE TABLE m_user_card
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_id          VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_code        VARCHAR(20)    NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
	valid_status     VARCHAR(2)              COMMENT '有效状态(F0026)',
	valid_start_date VARCHAR(20)             COMMENT '有效开始日期(yyyy-MM-dd HH:mm:ss)',
    valid_end_date   VARCHAR(20)             COMMENT '有效结束日期(yyyy-MM-dd HH:mm:ss)',
    created_by       VARCHAR(20)    NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)    NOT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    		 COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',

    PRIMARY KEY (id)
) COMMENT='会员卡信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_card_idx1 ON m_user_card (user_id);
CREATE UNIQUE INDEX m_user_card_idx2 ON m_user_card (user_code);

-- 会员审核历史信息 --
DROP TABLE IF EXISTS m_user_card;
CREATE TABLE m_user_card
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_id          VARCHAR(20)    NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
	valid_status     VARCHAR(2)              COMMENT '有效状态(F0026)',
	valid_start_date VARCHAR(20)             COMMENT '有效开始日期(yyyy-MM-dd HH:mm:ss)',
    valid_end_date   VARCHAR(20)             COMMENT '有效结束日期(yyyy-MM-dd HH:mm:ss)',
    created_by       VARCHAR(20)             COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)             COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    		 COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',

    PRIMARY KEY (id)
) COMMENT='会员卡信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_card_idx1 ON m_user_card (user_id);

-- 20240805 --
-- 会员会费相关设定 --
DROP TABLE IF EXISTS m_user_type_settings;
CREATE TABLE m_user_type_settings
( 
    user_type        VARCHAR(6)    NOT NULL COMMENT '会员类型(F0002)',
    fee_amount       DECIMAL(10, 2)    NULL COMMENT '会费金额',
    effective_year   int 	       NOT NULL COMMENT '会费年数',
    created_by       VARCHAR(20)   NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)   NOT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)            COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)            COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
       
    PRIMARY KEY (user_type)
) COMMENT='会员会费相关设定' ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 20240824 --
ALTER TABLE m_user_extend add COLUMN application_form MEDIUMBLOB;
ALTER TABLE m_user_extend add COLUMN application_form_ext VARCHAR(10);

ALTER TABLE m_admin add COLUMN phone VARCHAR(20);
ALTER TABLE m_admin add COLUMN mail VARCHAR(70);
ALTER TABLE m_admin add COLUMN photo MEDIUMBLOB;
ALTER TABLE m_admin add COLUMN photo_ext VARCHAR(10);
ALTER TABLE m_admin add COLUMN created_by VARCHAR(20);
ALTER TABLE m_admin add COLUMN created_at VARCHAR(20);
ALTER TABLE m_admin add COLUMN updated_by VARCHAR(20);
ALTER TABLE m_admin add COLUMN updated_at VARCHAR(20);

--20240901 --
-- 短信模板类型 --
INSERT INTO m_fixed_value VALUES ('F0025', '01', '会员注册验证码通知模板',		1);
INSERT INTO m_fixed_value VALUES ('F0025', '02', '会员验证码通知模板', 		2);
INSERT INTO m_fixed_value VALUES ('F0025', '03', '用户审核通过通知模板',		3);
INSERT INTO m_fixed_value VALUES ('F0025', '04', '用户审核不通过通知模板',		4);
INSERT INTO m_fixed_value VALUES ('F0025', '05', '用户审核返回修改通知模板',	5);
INSERT INTO m_fixed_value VALUES ('F0025', '06', '管理员创建通知模板',			6);

-- 短信模版设定 --·
DROP TABLE IF EXISTS m_sms_config;
CREATE TABLE m_sms_config
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
	template_type    VARCHAR(3)     NOT NULL COMMENT '模板类型(F0025)',
	template_id      VARCHAR(50)    NOT NULL COMMENT '模板ID',
	template_name    VARCHAR(50)    NOT NULL COMMENT '模板名称',
	api_url          VARCHAR(255)   NOT NULL COMMENT 'Application Url',
    app_key          VARCHAR(50)    NOT NULL COMMENT 'Application Key',
    app_secret       VARCHAR(50)    NOT NULL COMMENT 'Application Secret',
    sender           VARCHAR(50)    NOT NULL COMMENT '通道号',
    signature        VARCHAR(50)    NOT NULL COMMENT '签名名称',
    created_by       VARCHAR(20)    NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)    NOT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    		 COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
    
    PRIMARY KEY (id)
) COMMENT='短信模版设定信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX m_sms_config_idx1 ON m_sms_config (template_type);

-- 验证码类
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"01","ca9ac74114b04d2da7b257c39b89d98d","会员注册验证码通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205484","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"02","a31e0b64caa1446ca21f5a7642abb45b","会员验证码通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205484","甘肃省针灸学会","9999",NOW(),NULL,NULL);
-- 通知类
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"03","","用户审核通过通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"04","","用户审核不通过通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"05","","用户审核返回修改通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"06","","管理员创建通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);

--20240907 --
DELETE FROM m_fixed_value WHERE code='F0002';
INSERT INTO m_fixed_value VALUES ('F0002', '01', '个人会员',         1);
INSERT INTO m_fixed_value VALUES ('F0002', '0101', '甘肃针灸学会会员',       1);
INSERT INTO m_fixed_value VALUES ('F0002', '0105', '中国针灸学会会员',       2);

ALTER TABLE m_user modify COLUMN  id  VARCHAR(50), ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_user_extend modify COLUMN  id  VARCHAR(50), ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_order modify COLUMN  user_id  VARCHAR(50), ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_bill modify COLUMN  user_id  VARCHAR(50), ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_message modify COLUMN  user_id  VARCHAR(50), ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_message_read modify COLUMN  user_id  VARCHAR(50), ALGORITHM=INPLACE, LOCK=NONE;
/*
 * -- 会员卡信息
 * -- 会员ID调整为UUID
update m_user  set credit_code=uuid();

insert into m_user_card(id,user_id,user_code,valid_status,valid_start_date,valid_end_date,created_by,created_at) 
select uuid(),u.credit_code,u.id,u.valid_status,u.valid_start_date,u.valid_end_date,a.id,NOW() from m_user u,m_admin a where a.user_type='01';

update m_user  set id=credit_code,credit_code=null;

update m_user_check_history a, m_user_card b set a.user_id=b.user_id where a.user_id=b.user_code;
update m_order a, m_user_card b set a.user_id=b.user_id where a.user_id=b.user_code;
update m_bill a, m_user_card b set a.user_id=b.user_id where a.user_id=b.user_code;
update m_message a, m_user_card b set a.user_id=b.user_id where a.user_id=b.user_code;
update m_message_read a, m_user_card b set a.user_id=b.user_id where a.user_id=b.user_code;
*/

--20240908 --
ALTER TABLE m_order add COLUMN invoice_type VARCHAR(2);
ALTER TABLE m_order add COLUMN invoice_title VARCHAR(64);
ALTER TABLE m_order add COLUMN invoice_amount   DECIMAL(10, 2);
ALTER TABLE m_order add COLUMN credit_code VARCHAR(20);
ALTER TABLE m_order add COLUMN mail VARCHAR(70);

ALTER TABLE m_bill change COLUMN  bill_title invoice_title  VARCHAR(64), ALGORITHM=INPLACE, LOCK=NONE;

-- 抬头类型 --
INSERT INTO m_fixed_value VALUES ('F0027', '01', '个人',    1);
INSERT INTO m_fixed_value VALUES ('F0027', '02', '单位',    2);

INSERT INTO m_fixed_value VALUES ('F0018', '03', '开票申请',  3);

ALTER TABLE m_user add COLUMN deleted          int 	    DEFAULT 0  COMMENT '删除标记';
ALTER TABLE m_user add COLUMN created_by       VARCHAR(20)     COMMENT '管理员号(AYYMMDDHHmmSSR2)';
ALTER TABLE m_user add COLUMN created_at       VARCHAR(20)     COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)';
ALTER TABLE m_user add COLUMN updated_by       VARCHAR(20)     COMMENT '管理员号(AYYMMDDHHmmSSR2)';
ALTER TABLE m_user add COLUMN updated_at       VARCHAR(20)     COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)';

ALTER TABLE `m_user` CHANGE `password` `password` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '密码';
ALTER TABLE `m_user` CHANGE `sex` `sex` VARCHAR(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '性别(F0001)';


-- 用户审核状态 --
delete from m_fixed_value where code='F0024';
INSERT INTO m_fixed_value VALUES ('F0024', '01', '新注册',		1);
INSERT INTO m_fixed_value VALUES ('F0024', '02', '等待审核',		2);
INSERT INTO m_fixed_value VALUES ('F0024', '03', '审核通过',		3);
INSERT INTO m_fixed_value VALUES ('F0024', '04', '审核不通过',	4);
INSERT INTO m_fixed_value VALUES ('F0024', '05', '返回修改',		5);


ALTER TABLE m_user_extend modify COLUMN  research_dir  VARCHAR(64), ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_user_extend add COLUMN  application_form2  MEDIUMBLOB, ALGORITHM=INPLACE, LOCK=NONE;
ALTER TABLE m_user_extend add COLUMN  application_form_ext2  VARCHAR(10), ALGORITHM=INPLACE, LOCK=NONE;