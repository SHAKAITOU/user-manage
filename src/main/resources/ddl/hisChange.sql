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
	id               VARCHAR(20)    NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
	user_id          VARCHAR(50)    NOT NULL COMMENT 'UUID',
	valid_status     VARCHAR(6)              COMMENT '有效状态',
	valid_start_date VARCHAR(20)             COMMENT '有效开始日期(yyyy-MM-dd HH:mm:ss)',
    valid_end_date   VARCHAR(20)             COMMENT '有效结束日期(yyyy-MM-dd HH:mm:ss)',
    created_by       VARCHAR(20)    NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)    NOT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    		 COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',

    PRIMARY KEY (id)
) COMMENT='会员扩展信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_card_idx1 ON m_user_card (user_id);

-- 会员审核历史信息 --
DROP TABLE IF EXISTS m_user_check_history;
CREATE TABLE m_user_check_history
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_id          VARCHAR(50)    NOT NULL COMMENT '会员号(UUID)',
    check_status     VARCHAR(3)     NOT NULL COMMENT '审核状态(F0024)',
    check_date       VARCHAR(20)    NOT NULL COMMENT '审核时间(yyyy-MM-dd HH:mm:ss)',
    memo             VARCHAR(255)            COMMENT '审核建议(max150)',
    created_by       VARCHAR(20)    NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)    NOT NULL COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    		 COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
    
    PRIMARY KEY (id)
) COMMENT='会员审核历史信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_check_history_idx1 ON m_user_check_history (user_id);

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
ALTER TABLE m_admin add COLUMN created_at VARCHAR(20) NOT NULL;
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
