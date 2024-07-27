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
