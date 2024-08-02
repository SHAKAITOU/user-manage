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
