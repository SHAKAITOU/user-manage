CREATE DATABASE shatest DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE shatest;

-- 管理员信息 --
-- DROP TABLE IF EXISTS m_admin;
CREATE TABLE m_admin
( 
    id               VARCHAR(20)  NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    name             VARCHAR(70)  NOT NULL COMMENT '管理员名称(max64)',
    user_type        VARCHAR(3)   NOT NULL COMMENT '管理员类型(F0000)',
    password         VARCHAR(200) NOT NULL COMMENT '密码',
    phone            VARCHAR(20)           COMMENT '手机号(max11)',
    mail             VARCHAR(70)           COMMENT '电子邮箱(max64)',
    photo            MEDIUMBLOB            COMMENT '2寸证件照jpg/png/jpeg200k',
    photo_ext        VARCHAR(10)           COMMENT '证件照文件扩展名',
    created_by       VARCHAR(20)           COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)           COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    	   COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)           COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
    PRIMARY KEY (id)
) COMMENT='管理员信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `m_admin`(`id`,`name`,`user_type`,`password`,`phone`,`mail`,`photo`,`photo_ext`,`created_by`,`created_at`,`updated_by`,`updated_at`)VALUES('A24060914330223','超级管理员','01','VD2cOmWS0ly0K2NWXLszLQ==',null,null,null,null,null,null,null,null);


-- 固定值管理 --
-- DROP TABLE IF EXISTS m_fixed_value;
CREATE TABLE m_fixed_value
( 
    code       VARCHAR(10)   NOT NULL COMMENT 'KEY识别码',
    value      VARCHAR(10)  NOT NULL COMMENT 'KEY识别码子值',
    name       VARCHAR(255)  NOT NULL COMMENT 'KEY识别码子名称',
    show_index INT           NOT NULL COMMENT '页面表示顺序',
    PRIMARY KEY (code, value)
) COMMENT='固定值管理' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 管理员类型 --
INSERT INTO m_fixed_value VALUES ('F0000', '01', '超级管理员', 1);
INSERT INTO m_fixed_value VALUES ('F0000', '02', '高级管理员', 2);
INSERT INTO m_fixed_value VALUES ('F0000', '03', '一般管理员', 3);
-- 性别 --
INSERT INTO m_fixed_value VALUES ('F0001', '01', '男', 1);
INSERT INTO m_fixed_value VALUES ('F0001', '02', '女', 2);
-- 会员类型 --
-- INSERT INTO m_fixed_value VALUES ('F0002', '01', '个人会员',         1);
-- INSERT INTO m_fixed_value VALUES ('F0002', '0101', '普通会员',       1);
-- INSERT INTO m_fixed_value VALUES ('F0002', '0102', '普通会员(学生)', 2);
-- INSERT INTO m_fixed_value VALUES ('F0002', '0103', '外籍会员',       3);
-- INSERT INTO m_fixed_value VALUES ('F0002', '0104', '会士会员',       4);
-- INSERT INTO m_fixed_value VALUES ('F0002', '02', '团体会员单位',   2);
-- INSERT INTO m_fixed_value VALUES ('F0002', '0201', '团体会员单位',   1);
INSERT INTO m_fixed_value VALUES ('F0002', '01', '个人会员',         1);
INSERT INTO m_fixed_value VALUES ('F0002', '0101', '甘肃针灸学会会员',       1);
INSERT INTO m_fixed_value VALUES ('F0002', '0105', '中国针灸学会会员',       2);

-- 政治面貌 --
INSERT INTO m_fixed_value VALUES ('F0003', '01', '中共党员',       1);
INSERT INTO m_fixed_value VALUES ('F0003', '02', '中共预备党员',   2);
INSERT INTO m_fixed_value VALUES ('F0003', '03', '共青团员',       3);
INSERT INTO m_fixed_value VALUES ('F0003', '04', '民革党员',       4);
INSERT INTO m_fixed_value VALUES ('F0003', '05', '民盟盟员',       5);
INSERT INTO m_fixed_value VALUES ('F0003', '06', '民建党员',       6);
INSERT INTO m_fixed_value VALUES ('F0003', '07', '民进党员',       7);
INSERT INTO m_fixed_value VALUES ('F0003', '08', '农工党党员',     8);
INSERT INTO m_fixed_value VALUES ('F0003', '09', '致公党党员',     9);
INSERT INTO m_fixed_value VALUES ('F0003', '10', '九三学社社员',  10);
INSERT INTO m_fixed_value VALUES ('F0003', '11', '台盟盟员',      11);
INSERT INTO m_fixed_value VALUES ('F0003', '12', '无党派人士',    12);
INSERT INTO m_fixed_value VALUES ('F0003', '13', '群众',          13);
INSERT INTO m_fixed_value VALUES ('F0003', '14', '其他',          14);
-- 学历 --
INSERT INTO m_fixed_value VALUES ('F0004', '01', '博士后',         1);
INSERT INTO m_fixed_value VALUES ('F0004', '02', '博士',           2);
INSERT INTO m_fixed_value VALUES ('F0004', '03', '硕士',           3);
INSERT INTO m_fixed_value VALUES ('F0004', '04', '本科',           4);
INSERT INTO m_fixed_value VALUES ('F0004', '05', '大专',           5);
INSERT INTO m_fixed_value VALUES ('F0004', '06', '高中',           6);
INSERT INTO m_fixed_value VALUES ('F0004', '07', '中专',           7);
INSERT INTO m_fixed_value VALUES ('F0004', '08', '其他',           8);
-- 民族 --
INSERT INTO m_fixed_value VALUES ('F0005', '01', '汉族',           1);
INSERT INTO m_fixed_value VALUES ('F0005', '02', '壮族',           2);
INSERT INTO m_fixed_value VALUES ('F0005', '03', '满族',           3);
INSERT INTO m_fixed_value VALUES ('F0005', '04', '回族',           4);
INSERT INTO m_fixed_value VALUES ('F0005', '05', '苗族',           5);
INSERT INTO m_fixed_value VALUES ('F0005', '06', '维吾尔族',       6);
INSERT INTO m_fixed_value VALUES ('F0005', '07', '土家族',         7);
INSERT INTO m_fixed_value VALUES ('F0005', '08', '彝族',           8);
INSERT INTO m_fixed_value VALUES ('F0005', '09', '蒙古族',         9);
INSERT INTO m_fixed_value VALUES ('F0005', '10', '藏族',          10);
INSERT INTO m_fixed_value VALUES ('F0005', '11', '布依族',        11);
INSERT INTO m_fixed_value VALUES ('F0005', '12', '侗族',          12);
INSERT INTO m_fixed_value VALUES ('F0005', '13', '瑶族',          13);
INSERT INTO m_fixed_value VALUES ('F0005', '14', '朝鲜族',        14);
INSERT INTO m_fixed_value VALUES ('F0005', '15', '白族',          15);
INSERT INTO m_fixed_value VALUES ('F0005', '16', '哈尼族',        16);
INSERT INTO m_fixed_value VALUES ('F0005', '17', '哈萨克族',      17);
INSERT INTO m_fixed_value VALUES ('F0005', '18', '黎族',          18);
INSERT INTO m_fixed_value VALUES ('F0005', '19', '傣族',          19);
INSERT INTO m_fixed_value VALUES ('F0005', '20', '畲族',          20);
INSERT INTO m_fixed_value VALUES ('F0005', '21', '傈僳族',        21);
INSERT INTO m_fixed_value VALUES ('F0005', '22', '仡佬族',        22);
INSERT INTO m_fixed_value VALUES ('F0005', '23', '东乡族',        23);
INSERT INTO m_fixed_value VALUES ('F0005', '24', '高山族',        24);
INSERT INTO m_fixed_value VALUES ('F0005', '25', '拉祜族',        25);
INSERT INTO m_fixed_value VALUES ('F0005', '26', '水族',          26);
INSERT INTO m_fixed_value VALUES ('F0005', '27', '佤族',          27);
INSERT INTO m_fixed_value VALUES ('F0005', '28', '纳西族',        28);
INSERT INTO m_fixed_value VALUES ('F0005', '29', '羌族',          29);
INSERT INTO m_fixed_value VALUES ('F0005', '30', '土族',          30);
INSERT INTO m_fixed_value VALUES ('F0005', '31', '仫佬族',        31);
INSERT INTO m_fixed_value VALUES ('F0005', '32', '锡伯族',        32);
INSERT INTO m_fixed_value VALUES ('F0005', '33', '柯尔克孜族',    33);
INSERT INTO m_fixed_value VALUES ('F0005', '34', '达斡尔族',      34);
INSERT INTO m_fixed_value VALUES ('F0005', '35', '景颇族',        35);
INSERT INTO m_fixed_value VALUES ('F0005', '36', '毛南族',        36);
INSERT INTO m_fixed_value VALUES ('F0005', '37', '撒拉族',        37);
INSERT INTO m_fixed_value VALUES ('F0005', '38', '布朗族',        38);
INSERT INTO m_fixed_value VALUES ('F0005', '39', '塔吉克族',      39);
INSERT INTO m_fixed_value VALUES ('F0005', '40', '阿昌族',        40);
INSERT INTO m_fixed_value VALUES ('F0005', '41', '普米族',        41);
INSERT INTO m_fixed_value VALUES ('F0005', '42', '鄂温克族',      42);
INSERT INTO m_fixed_value VALUES ('F0005', '43', '怒族',          43);
INSERT INTO m_fixed_value VALUES ('F0005', '44', '京族',          44);
INSERT INTO m_fixed_value VALUES ('F0005', '45', '基诺族',        45);
INSERT INTO m_fixed_value VALUES ('F0005', '46', '德昂族',        46);
INSERT INTO m_fixed_value VALUES ('F0005', '47', '保安族',        47);
INSERT INTO m_fixed_value VALUES ('F0005', '48', '俄罗斯族',      48);
INSERT INTO m_fixed_value VALUES ('F0005', '49', '裕固族',        49);
INSERT INTO m_fixed_value VALUES ('F0005', '50', '乌孜别克族',    50);
INSERT INTO m_fixed_value VALUES ('F0005', '51', '门巴族',        51);
INSERT INTO m_fixed_value VALUES ('F0005', '52', '鄂伦春族',      52);
INSERT INTO m_fixed_value VALUES ('F0005', '53', '独龙族',        53);
INSERT INTO m_fixed_value VALUES ('F0005', '54', '塔塔尔族',      54);
INSERT INTO m_fixed_value VALUES ('F0005', '55', '赫哲族',        55);
INSERT INTO m_fixed_value VALUES ('F0005', '56', '珞巴族',        56);
INSERT INTO m_fixed_value VALUES ('F0005', '57', '其他族',        57);
-- 学位 --
INSERT INTO m_fixed_value VALUES ('F0006', '01', '博士学位',       1);
INSERT INTO m_fixed_value VALUES ('F0006', '02', '硕士学位',       2);
INSERT INTO m_fixed_value VALUES ('F0006', '03', '学士学位',       3);
INSERT INTO m_fixed_value VALUES ('F0006', '04', '其他',           4);
-- 职务 --
INSERT INTO m_fixed_value VALUES ('F0007', '01', '部长',           1);
INSERT INTO m_fixed_value VALUES ('F0007', '02', '副部长',         2);
INSERT INTO m_fixed_value VALUES ('F0007', '03', '局(司)长',       3);
INSERT INTO m_fixed_value VALUES ('F0007', '04', '副局(司)长',     4);
INSERT INTO m_fixed_value VALUES ('F0007', '05', '处长',           5);
INSERT INTO m_fixed_value VALUES ('F0007', '06', '副处长',         6);
INSERT INTO m_fixed_value VALUES ('F0007', '07', '科长',           7);
INSERT INTO m_fixed_value VALUES ('F0007', '08', '副科长',         8);
INSERT INTO m_fixed_value VALUES ('F0007', '09', '校长',           9);
INSERT INTO m_fixed_value VALUES ('F0007', '10', '副校长',        10);
INSERT INTO m_fixed_value VALUES ('F0007', '11', '书记',          11);
INSERT INTO m_fixed_value VALUES ('F0007', '12', '副书记',        12);
INSERT INTO m_fixed_value VALUES ('F0007', '13', '院长',          13);
INSERT INTO m_fixed_value VALUES ('F0007', '14', '副院长',        14);
INSERT INTO m_fixed_value VALUES ('F0007', '15', '主任',          15);
INSERT INTO m_fixed_value VALUES ('F0007', '16', '副主任',        16);
INSERT INTO m_fixed_value VALUES ('F0007', '17', '科主任',        17);
INSERT INTO m_fixed_value VALUES ('F0007', '18', '科副主任',      18);
INSERT INTO m_fixed_value VALUES ('F0007', '19', '所长',          19);
INSERT INTO m_fixed_value VALUES ('F0007', '20', '副所长',        20);
INSERT INTO m_fixed_value VALUES ('F0007', '21', '董事长',        21);
INSERT INTO m_fixed_value VALUES ('F0007', '22', '总经理',        22);
INSERT INTO m_fixed_value VALUES ('F0007', '23', '副总经理',      23);
INSERT INTO m_fixed_value VALUES ('F0007', '24', '总监',          24);
INSERT INTO m_fixed_value VALUES ('F0007', '25', '副总监',        25);
INSERT INTO m_fixed_value VALUES ('F0007', '26', '经理',          26);
INSERT INTO m_fixed_value VALUES ('F0007', '27', '主管',          27);
INSERT INTO m_fixed_value VALUES ('F0007', '28', '其他',          28);
-- 单位性质 --
INSERT INTO m_fixed_value VALUES ('F0008', '01', '高校',           1);
INSERT INTO m_fixed_value VALUES ('F0008', '02', '科研院所',       2);
INSERT INTO m_fixed_value VALUES ('F0008', '03', '企业',           3);
INSERT INTO m_fixed_value VALUES ('F0008', '04', '医院',           4);
INSERT INTO m_fixed_value VALUES ('F0008', '05', '其他',           5);
-- 职称 --
INSERT INTO m_fixed_value VALUES ('F0009', '01', '高级职称',       1);
INSERT INTO m_fixed_value VALUES ('F0009', '02', '副高级职称',     2);
INSERT INTO m_fixed_value VALUES ('F0009', '03', '中级职称',       3);
INSERT INTO m_fixed_value VALUES ('F0009', '04', '初级职称',       4);
INSERT INTO m_fixed_value VALUES ('F0009', '05', '其他',           5);
INSERT INTO m_fixed_value VALUES ('F0009', '06', '无',             6);
INSERT INTO m_fixed_value VALUES ('F0009', '0101', '教授',         1);
INSERT INTO m_fixed_value VALUES ('F0009', '0102', '正高级讲师',   2);
INSERT INTO m_fixed_value VALUES ('F0009', '0103', '研究员',       3);
INSERT INTO m_fixed_value VALUES ('F0009', '0104', '主任医师',     4);
INSERT INTO m_fixed_value VALUES ('F0009', '0105', '主任药师',     5);
INSERT INTO m_fixed_value VALUES ('F0009', '0106', '主任护师',     6);
INSERT INTO m_fixed_value VALUES ('F0009', '0107', '主任技师',     7);
INSERT INTO m_fixed_value VALUES ('F0009', '0108', '教授级高级工程师',8);
INSERT INTO m_fixed_value VALUES ('F0009', '0109', '研究馆员',     9);
INSERT INTO m_fixed_value VALUES ('F0009', '0110', '编审',        10);
INSERT INTO m_fixed_value VALUES ('F0009', '0111', '正高级实验师',11);
INSERT INTO m_fixed_value VALUES ('F0009', '0112', '正高级经济师',12);
INSERT INTO m_fixed_value VALUES ('F0009', '0113', '其他(正高级)',13);
INSERT INTO m_fixed_value VALUES ('F0009', '0201', '副教授',       1);
INSERT INTO m_fixed_value VALUES ('F0009', '0202', '高级讲师',     2);
INSERT INTO m_fixed_value VALUES ('F0009', '0203', '副研究员',     3);
INSERT INTO m_fixed_value VALUES ('F0009', '0204', '副主任医师',   4);
INSERT INTO m_fixed_value VALUES ('F0009', '0205', '副主任药师',   5);
INSERT INTO m_fixed_value VALUES ('F0009', '0206', '副主任护师',   6);
INSERT INTO m_fixed_value VALUES ('F0009', '0207', '副主任技师',   7);
INSERT INTO m_fixed_value VALUES ('F0009', '0208', '高级工程师',   8);
INSERT INTO m_fixed_value VALUES ('F0009', '0209', '副研究馆员',   9);
INSERT INTO m_fixed_value VALUES ('F0009', '0210', '副编审',      10);
INSERT INTO m_fixed_value VALUES ('F0009', '0211', '高级实验师',  11);
INSERT INTO m_fixed_value VALUES ('F0009', '0212', '高级经济师',  12);
INSERT INTO m_fixed_value VALUES ('F0009', '0213', '其他(副高级)',13);
INSERT INTO m_fixed_value VALUES ('F0009', '0301', '讲师',         1);
INSERT INTO m_fixed_value VALUES ('F0009', '0302', '助理研究员',   2);
INSERT INTO m_fixed_value VALUES ('F0009', '0303', '主治医师',     3);
INSERT INTO m_fixed_value VALUES ('F0009', '0304', '主管药师',     4);
INSERT INTO m_fixed_value VALUES ('F0009', '0305', '主管护师',     5);
INSERT INTO m_fixed_value VALUES ('F0009', '0306', '主管技师',     6);
INSERT INTO m_fixed_value VALUES ('F0009', '0307', '工程师',       7);
INSERT INTO m_fixed_value VALUES ('F0009', '0308', '馆员',         8);
INSERT INTO m_fixed_value VALUES ('F0009', '0309', '编辑',         9);
INSERT INTO m_fixed_value VALUES ('F0009', '0310', '实验师',      10);
INSERT INTO m_fixed_value VALUES ('F0009', '0311', '经济师',      11);
INSERT INTO m_fixed_value VALUES ('F0009', '0312', '其他(中级)',  12);
INSERT INTO m_fixed_value VALUES ('F0009', '0401', '助教',         1);
INSERT INTO m_fixed_value VALUES ('F0009', '0402', '助理讲师',     2);
INSERT INTO m_fixed_value VALUES ('F0009', '0403', '研究实习员',   3);
INSERT INTO m_fixed_value VALUES ('F0009', '0404', '医师',         4);
INSERT INTO m_fixed_value VALUES ('F0009', '0405', '医士',         5);
INSERT INTO m_fixed_value VALUES ('F0009', '0406', '药师',         6);
INSERT INTO m_fixed_value VALUES ('F0009', '0407', '药士',         7);
INSERT INTO m_fixed_value VALUES ('F0009', '0408', '护师',         8);
INSERT INTO m_fixed_value VALUES ('F0009', '0409', '护士',         9);
INSERT INTO m_fixed_value VALUES ('F0009', '0410', '技师',        10);
INSERT INTO m_fixed_value VALUES ('F0009', '0411', '技士',        11);
INSERT INTO m_fixed_value VALUES ('F0009', '0412', '助理工程师',  12);
INSERT INTO m_fixed_value VALUES ('F0009', '0413', '技术员',      13);
INSERT INTO m_fixed_value VALUES ('F0009', '0414', '助理馆员',    14);
INSERT INTO m_fixed_value VALUES ('F0009', '0415', '管理员',      15);
INSERT INTO m_fixed_value VALUES ('F0009', '0416', '助理编辑',    16);
INSERT INTO m_fixed_value VALUES ('F0009', '0417', '助理实验师',  17);
INSERT INTO m_fixed_value VALUES ('F0009', '0418', '实验员',      18);
INSERT INTO m_fixed_value VALUES ('F0009', '0419', '助理经济师',  19);
INSERT INTO m_fixed_value VALUES ('F0009', '0420', '其他(初级)',  20);
-- 证件类型 --
INSERT INTO m_fixed_value VALUES ('F0010', '01', '身份证',           1);
INSERT INTO m_fixed_value VALUES ('F0010', '02', '港澳台居民居住证',   2);
INSERT INTO m_fixed_value VALUES ('F0010', '03', '军官证',           3);
INSERT INTO m_fixed_value VALUES ('F0010', '04', '护照',             4);
INSERT INTO m_fixed_value VALUES ('F0010', '05', '团体证',           5);
-- 所在地区 --
INSERT INTO m_fixed_value VALUES ('F0011', '01', '北京',       1);
INSERT INTO m_fixed_value VALUES ('F0011', '0101', '东城区',       1);
INSERT INTO m_fixed_value VALUES ('F0011', '0102', '西城区',       2);
INSERT INTO m_fixed_value VALUES ('F0011', '0103', '朝阳区',       3);
INSERT INTO m_fixed_value VALUES ('F0011', '0104', '丰台区',       4);
INSERT INTO m_fixed_value VALUES ('F0011', '0105', '石景山区',     5);
INSERT INTO m_fixed_value VALUES ('F0011', '0106', '海淀区',       6);
INSERT INTO m_fixed_value VALUES ('F0011', '0107', '门头沟区',     7);
INSERT INTO m_fixed_value VALUES ('F0011', '0108', '房山区',       8);
INSERT INTO m_fixed_value VALUES ('F0011', '0109', '通州区',       9);
INSERT INTO m_fixed_value VALUES ('F0011', '0110', '顺义区',       10);
INSERT INTO m_fixed_value VALUES ('F0011', '0111', '昌平区',       11);
INSERT INTO m_fixed_value VALUES ('F0011', '0112', '大兴区',       12);
INSERT INTO m_fixed_value VALUES ('F0011', '0113', '怀柔区',       13);
INSERT INTO m_fixed_value VALUES ('F0011', '0114', '平谷区',       14);
INSERT INTO m_fixed_value VALUES ('F0011', '0115', '密云区',       15);
INSERT INTO m_fixed_value VALUES ('F0011', '0116', '延庆区',       16);

INSERT INTO m_fixed_value VALUES ('F0011', '02', '天津',       2);
INSERT INTO m_fixed_value VALUES ('F0011', '0201', '河西区',       1);
INSERT INTO m_fixed_value VALUES ('F0011', '0202', '南开区',       2);
INSERT INTO m_fixed_value VALUES ('F0011', '0203', '河北区',       3);
INSERT INTO m_fixed_value VALUES ('F0011', '0204', '红桥区',       4);
INSERT INTO m_fixed_value VALUES ('F0011', '0205', '东丽区',       5);
INSERT INTO m_fixed_value VALUES ('F0011', '0206', '西青区',       6);
INSERT INTO m_fixed_value VALUES ('F0011', '0207', '津南区',       7);
INSERT INTO m_fixed_value VALUES ('F0011', '0208', '北辰区',       8);
INSERT INTO m_fixed_value VALUES ('F0011', '0209', '武清区',       9);
INSERT INTO m_fixed_value VALUES ('F0011', '0210', '宝坻区',       10);
INSERT INTO m_fixed_value VALUES ('F0011', '0211', '滨海新区',     11);
INSERT INTO m_fixed_value VALUES ('F0011', '0212', '宁河区',       12);
INSERT INTO m_fixed_value VALUES ('F0011', '0213', '静海区',       13);
INSERT INTO m_fixed_value VALUES ('F0011', '0214', '蓟州区',       14);
INSERT INTO m_fixed_value VALUES ('F0011', '0215', '和平区',       15);
INSERT INTO m_fixed_value VALUES ('F0011', '0216', '河东区',       16);

INSERT INTO m_fixed_value VALUES ('F0011', '03', '河北',       3);
INSERT INTO m_fixed_value VALUES ('F0011', '0301', '石家庄',       1);
INSERT INTO m_fixed_value VALUES ('F0011', '0302', '唐山市',       2);
INSERT INTO m_fixed_value VALUES ('F0011', '0303', '秦皇岛',       3);
INSERT INTO m_fixed_value VALUES ('F0011', '0304', '邯郸',       4);
INSERT INTO m_fixed_value VALUES ('F0011', '0305', '邢台',       5);
INSERT INTO m_fixed_value VALUES ('F0011', '0306', '雄安新区',       6);
INSERT INTO m_fixed_value VALUES ('F0011', '0307', '保定',       7);
INSERT INTO m_fixed_value VALUES ('F0011', '0308', '张家口',       8);
INSERT INTO m_fixed_value VALUES ('F0011', '0309', '承德',       9);
INSERT INTO m_fixed_value VALUES ('F0011', '0310', '沧州',       10);
INSERT INTO m_fixed_value VALUES ('F0011', '0311', '廊坊',       11);
INSERT INTO m_fixed_value VALUES ('F0011', '0312', '衡水',       12);

INSERT INTO m_fixed_value VALUES ('F0011', '04', '山西',       4);
INSERT INTO m_fixed_value VALUES ('F0011', '0401', '太原',       1);
INSERT INTO m_fixed_value VALUES ('F0011', '0402', '大同',       2);
INSERT INTO m_fixed_value VALUES ('F0011', '0403', '阳泉',       3);
INSERT INTO m_fixed_value VALUES ('F0011', '0404', '长治',       4);
INSERT INTO m_fixed_value VALUES ('F0011', '0405', '晋城',       5);
INSERT INTO m_fixed_value VALUES ('F0011', '0406', '朔州',       6);
INSERT INTO m_fixed_value VALUES ('F0011', '0407', '晋中',       7);
INSERT INTO m_fixed_value VALUES ('F0011', '0408', '运城',       8);
INSERT INTO m_fixed_value VALUES ('F0011', '0409', '忻州',       9);
INSERT INTO m_fixed_value VALUES ('F0011', '0410', '临汾',       10);
INSERT INTO m_fixed_value VALUES ('F0011', '0411', '吕梁',       11);
INSERT INTO m_fixed_value VALUES ('F0011', '0412', '衡水',       12);

INSERT INTO m_fixed_value VALUES ('F0011', '05', '内蒙古',     5);
INSERT INTO m_fixed_value VALUES ('F0011', '06', '辽宁',       6);
INSERT INTO m_fixed_value VALUES ('F0011', '07', '吉林',       7);
INSERT INTO m_fixed_value VALUES ('F0011', '08', '黑龙江',     8);
INSERT INTO m_fixed_value VALUES ('F0011', '09', '上海',       9);
INSERT INTO m_fixed_value VALUES ('F0011', '10', '江苏',       10);
INSERT INTO m_fixed_value VALUES ('F0011', '11', '浙江',       11);
INSERT INTO m_fixed_value VALUES ('F0011', '12', '安徽',       12);
INSERT INTO m_fixed_value VALUES ('F0011', '13', '福建',       13);
INSERT INTO m_fixed_value VALUES ('F0011', '14', '江西',       14);
INSERT INTO m_fixed_value VALUES ('F0011', '15', '山东',       15);
INSERT INTO m_fixed_value VALUES ('F0011', '16', '河南',       16);
INSERT INTO m_fixed_value VALUES ('F0011', '17', '湖北',       17);
INSERT INTO m_fixed_value VALUES ('F0011', '18', '湖南',       18);
INSERT INTO m_fixed_value VALUES ('F0011', '19', '广东',       19);
INSERT INTO m_fixed_value VALUES ('F0011', '20', '广西',       20);
INSERT INTO m_fixed_value VALUES ('F0011', '21', '海南',       21);
INSERT INTO m_fixed_value VALUES ('F0011', '22', '重庆',       22);
INSERT INTO m_fixed_value VALUES ('F0011', '23', '四川',       23);
INSERT INTO m_fixed_value VALUES ('F0011', '24', '贵州',       24);
INSERT INTO m_fixed_value VALUES ('F0011', '25', '云南',       25);
INSERT INTO m_fixed_value VALUES ('F0011', '26', '西藏',       26);
INSERT INTO m_fixed_value VALUES ('F0011', '27', '陕西',       27);
INSERT INTO m_fixed_value VALUES ('F0011', '28', '甘肃',       28);
INSERT INTO m_fixed_value VALUES ('F0011', '29', '青海',       29);
INSERT INTO m_fixed_value VALUES ('F0011', '30', '宁夏',       30);
INSERT INTO m_fixed_value VALUES ('F0011', '31', '新疆',       31);
INSERT INTO m_fixed_value VALUES ('F0011', '32', '台湾',       32);
INSERT INTO m_fixed_value VALUES ('F0011', '33', '香港特别行政区', 33);
INSERT INTO m_fixed_value VALUES ('F0011', '34', '澳门',       34);
-- 入会途径 --
INSERT INTO m_fixed_value VALUES ('F0012', '001', '中国针灸学会',       1);
INSERT INTO m_fixed_value VALUES ('F0012', '002', '北京针灸学会',       2);
INSERT INTO m_fixed_value VALUES ('F0012', '003', '上海市针灸学会',       3);
INSERT INTO m_fixed_value VALUES ('F0012', '004', '天津市针灸学会',       4);
INSERT INTO m_fixed_value VALUES ('F0012', '005', '河北省针灸学会',       5);
INSERT INTO m_fixed_value VALUES ('F0012', '006', '山西省针灸学会',       6);
INSERT INTO m_fixed_value VALUES ('F0012', '007', '内蒙古自治区中医药学会针灸分会',       7);
INSERT INTO m_fixed_value VALUES ('F0012', '008', '辽宁省针灸学会',       8);
INSERT INTO m_fixed_value VALUES ('F0012', '009', '吉林省针灸学会',       9);
INSERT INTO m_fixed_value VALUES ('F0012', '010', '黑龙江省针灸学会',       10);
INSERT INTO m_fixed_value VALUES ('F0012', '011', '江苏省针灸学会',       11);
INSERT INTO m_fixed_value VALUES ('F0012', '012', '浙江省针灸学会',       12);
INSERT INTO m_fixed_value VALUES ('F0012', '013', '安徽省针灸学会',       13);
INSERT INTO m_fixed_value VALUES ('F0012', '014', '山东针灸学会',       14);
INSERT INTO m_fixed_value VALUES ('F0012', '015', '江西省针灸学会',       15);
INSERT INTO m_fixed_value VALUES ('F0012', '016', '福建省针灸学会',       16);
INSERT INTO m_fixed_value VALUES ('F0012', '017', '河南省针灸学会',       17);
INSERT INTO m_fixed_value VALUES ('F0012', '018', '湖北省针灸学会',       18);
INSERT INTO m_fixed_value VALUES ('F0012', '019', '湖南省针灸学会',       19);
INSERT INTO m_fixed_value VALUES ('F0012', '020', '广东省针灸学会',       20);
INSERT INTO m_fixed_value VALUES ('F0012', '021', '广西针灸学会',       21);
INSERT INTO m_fixed_value VALUES ('F0012', '022', '四川省针灸学会',       22);
INSERT INTO m_fixed_value VALUES ('F0012', '023', '贵州省针灸学会',       23);
INSERT INTO m_fixed_value VALUES ('F0012', '024', '云南省针灸学会',       24);
INSERT INTO m_fixed_value VALUES ('F0012', '025', '陕西省针灸学会',       25);
INSERT INTO m_fixed_value VALUES ('F0012', '026', '宁夏针灸学会',       26);
INSERT INTO m_fixed_value VALUES ('F0012', '027', '甘肃省针灸学会',       27);
INSERT INTO m_fixed_value VALUES ('F0012', '028', '新疆针灸医学学会',       28);
INSERT INTO m_fixed_value VALUES ('F0012', '029', '青海省中医药学会',       29);
INSERT INTO m_fixed_value VALUES ('F0012', '030', '重庆市针灸学会',       30);
INSERT INTO m_fixed_value VALUES ('F0012', '031', '海南省针灸学会',       31);
INSERT INTO m_fixed_value VALUES ('F0012', '032', '西藏自治区针灸学会',       32);
INSERT INTO m_fixed_value VALUES ('F0012', '033', '青岛市针灸学会',       33);
INSERT INTO m_fixed_value VALUES ('F0012', '034', '深圳市针灸学会',       34);
INSERT INTO m_fixed_value VALUES ('F0012', '035', '宁波市针灸学会',       35);
INSERT INTO m_fixed_value VALUES ('F0012', '036', '厦门市针灸学会',       36);
INSERT INTO m_fixed_value VALUES ('F0012', '037', '中国针灸学会针灸临床分会',       37);
INSERT INTO m_fixed_value VALUES ('F0012', '038', '中国针灸学会针法灸法分会',       38);
INSERT INTO m_fixed_value VALUES ('F0012', '039', '中国针灸学会实验针灸分会',       39);
INSERT INTO m_fixed_value VALUES ('F0012', '040', '中国针灸学会针刺麻醉分会',       40);
INSERT INTO m_fixed_value VALUES ('F0012', '041', '中国针灸学会经络分会',       41);
INSERT INTO m_fixed_value VALUES ('F0012', '042', '中国针灸学会腧穴分会',       42);
INSERT INTO m_fixed_value VALUES ('F0012', '043', '中国针灸学会耳穴诊治专业委员会',       43);
INSERT INTO m_fixed_value VALUES ('F0012', '044', '中国针灸学会针灸文献专业委员会',       44);
INSERT INTO m_fixed_value VALUES ('F0012', '045', '中国针灸学会针灸器材专业委员会',       45);
INSERT INTO m_fixed_value VALUES ('F0012', '046', '中国针灸学会针灸教育专业委员会',       46);
INSERT INTO m_fixed_value VALUES ('F0012', '047', '中国针灸学会标准化工作委员会',       47);
INSERT INTO m_fixed_value VALUES ('F0012', '048', '中国针灸学会腹针专业委员会',       48);
INSERT INTO m_fixed_value VALUES ('F0012', '049', '中国针灸学会砭石与刮痧专业委员会',       49);
INSERT INTO m_fixed_value VALUES ('F0012', '050', '中国针灸学会脑病科学专业委员会',       50);
INSERT INTO m_fixed_value VALUES ('F0012', '051', '中国针灸学会针灸康复学专业委员会',       51);
INSERT INTO m_fixed_value VALUES ('F0012', '052', '中国针灸学会经筋诊治专业委员会',       52);
INSERT INTO m_fixed_value VALUES ('F0012', '053', '中国针灸学会微创针刀专业委员会',       53);
INSERT INTO m_fixed_value VALUES ('F0012', '054', '中国针灸学会刺络与拔罐专业委员会',       54);
INSERT INTO m_fixed_value VALUES ('F0012', '055', '中国针灸学会循证针灸学专业委员会',       55);
INSERT INTO m_fixed_value VALUES ('F0012', '056', '中国针灸学会学科与学术工作委员会',       56);
INSERT INTO m_fixed_value VALUES ('F0012', '057', '中国针灸学会科普工作委员会',       57);
INSERT INTO m_fixed_value VALUES ('F0012', '058', '中国针灸学会针推结合专业委员会',       58);
INSERT INTO m_fixed_value VALUES ('F0012', '059', '中国针灸学会针灸医学影像专业委员会',       59);
INSERT INTO m_fixed_value VALUES ('F0012', '060', '中国针灸学会学术流派研究与传承专业委员会',       60);
INSERT INTO m_fixed_value VALUES ('F0012', '061', '中国针灸学会穴位贴敷专业委员会',       61);
INSERT INTO m_fixed_value VALUES ('F0012', '062', '中国针灸学会减肥与美容专业委员会',       62);
INSERT INTO m_fixed_value VALUES ('F0012', '063', '中国针灸学会针灸技术评估工作委员会',       63);
INSERT INTO m_fixed_value VALUES ('F0012', '064', '中国针灸学会合作单位',       64);
INSERT INTO m_fixed_value VALUES ('F0012', '065', '中国针灸学会穴位埋线专业委员会',       65);
INSERT INTO m_fixed_value VALUES ('F0012', '066', '中国针灸学会针灸装备设施工作委员会',       66);
INSERT INTO m_fixed_value VALUES ('F0012', '067', '中国针灸学会针灸治未病专业委员会',       67);
INSERT INTO m_fixed_value VALUES ('F0012', '068', '中国针灸学会基层适宜技术推广专业委员会',       68);
INSERT INTO m_fixed_value VALUES ('F0012', '069', '中国针灸学会针药结合专业委员会',       69);
INSERT INTO m_fixed_value VALUES ('F0012', '070', '中国中医科学院针灸研究所',       70);
INSERT INTO m_fixed_value VALUES ('F0012', '071', '中国针灸学会皮内针专业委员会',       71);
INSERT INTO m_fixed_value VALUES ('F0012', '072', '中国针灸学会青年委员会',       72);
INSERT INTO m_fixed_value VALUES ('F0012', '073', '中国针灸学会灸疗分会',       73);
INSERT INTO m_fixed_value VALUES ('F0012', '074', '中国针灸学会艾灸产学研创新联盟',       74);
INSERT INTO m_fixed_value VALUES ('F0012', '075', '中国针灸学会脑科学产学研创新联盟',       75);
INSERT INTO m_fixed_value VALUES ('F0012', '076', '中国针灸学会小儿推拿专业委员会',       76);
INSERT INTO m_fixed_value VALUES ('F0012', '077', '中国针灸学会新九针专业委员会',       77);
INSERT INTO m_fixed_value VALUES ('F0012', '078', '中国针灸学会痛症专业委员会',       78);
INSERT INTO m_fixed_value VALUES ('F0012', '079', '中国针灸学会火针专业委员会',       79);
INSERT INTO m_fixed_value VALUES ('F0012', '080', '中国针灸学会盆底病专业委员会',       80);
INSERT INTO m_fixed_value VALUES ('F0012', '081', '中国针灸学会小儿脑病专业委员会',       81);
INSERT INTO m_fixed_value VALUES ('F0012', '082', '中国针灸学会针灸与民族疗法分会',       82);
INSERT INTO m_fixed_value VALUES ('F0012', '083', '中国针灸学会综合医院针灸分会',       83);
INSERT INTO m_fixed_value VALUES ('F0012', '084', '中国针灸学会民间针灸分会',       84);
INSERT INTO m_fixed_value VALUES ('F0012', '085', '中国针灸学会睡眠健康管理专业委员会',       85);
INSERT INTO m_fixed_value VALUES ('F0012', '086', '中国针灸学会灸养专业委员会',       86);
INSERT INTO m_fixed_value VALUES ('F0012', '087', '中国针灸学会妇科生殖专业委员会',       87);
INSERT INTO m_fixed_value VALUES ('F0012', '088', '中国针灸学会手法量学专业委员会',       88);
INSERT INTO m_fixed_value VALUES ('F0012', '089', '中国针灸学会结构针灸专业委员会',       89);
INSERT INTO m_fixed_value VALUES ('F0012', '090', '中国针灸学会脾胃病专业委员会',       90);
INSERT INTO m_fixed_value VALUES ('F0012', '091', '中国针灸学会老年病专业委员会',       91);
INSERT INTO m_fixed_value VALUES ('F0012', '092', '中国针灸学会神志病专业委员会',       92);
INSERT INTO m_fixed_value VALUES ('F0012', '093', '中国针灸学会慢病管理专业委员会',       93);
INSERT INTO m_fixed_value VALUES ('F0012', '094', '中国针灸学会浮针专业委员会',       94);
INSERT INTO m_fixed_value VALUES ('F0012', '095', '中国针灸学会非物质文化遗产工作委员会',       95);
INSERT INTO m_fixed_value VALUES ('F0012', '096', '中国针灸学会岐黄针疗法专业委员会',       96);
INSERT INTO m_fixed_value VALUES ('F0012', '097', '中国针灸学会中医针灸技师工作委员会',       97);
INSERT INTO m_fixed_value VALUES ('F0012', '098', '中国针灸学会面针专业委员会',       98);
INSERT INTO m_fixed_value VALUES ('F0012', '099', '中国针灸学会针灸整脊分会',       99);
INSERT INTO m_fixed_value VALUES ('F0012', '100', '中国针灸学会电针专业委员会',       100);
INSERT INTO m_fixed_value VALUES ('F0012', '101', '中国针灸学会风湿免疫分会',       101);
INSERT INTO m_fixed_value VALUES ('F0012', '102', '中国针灸学会平衡针专业委员会',       102);
-- 审核状态 --
INSERT INTO m_fixed_value VALUES ('F0013', '01', '等待审核',  1);
INSERT INTO m_fixed_value VALUES ('F0013', '02', '审核中',    2);
INSERT INTO m_fixed_value VALUES ('F0013', '03', '已审核',    3);
INSERT INTO m_fixed_value VALUES ('F0013', '04', '审核通过',  4);
INSERT INTO m_fixed_value VALUES ('F0013', '05', '审核拒否',  5);

-- 缴费类型 --
INSERT INTO m_fixed_value VALUES ('F0014', '01', '线上支付',    1);
INSERT INTO m_fixed_value VALUES ('F0014', '02', '线下支付',    2);
-- 订单类型 --
INSERT INTO m_fixed_value VALUES ('F0015', '01', '入会订单',    1);
INSERT INTO m_fixed_value VALUES ('F0015', '02', '续费订单',    2);
INSERT INTO m_fixed_value VALUES ('F0015', '03', '入会订单(详情添加)',    3);
INSERT INTO m_fixed_value VALUES ('F0015', '04', '续费订单(详情添加)',    4);
INSERT INTO m_fixed_value VALUES ('F0015', '05', '续费订单(系统导入)',    5);
-- 退款状态 --
INSERT INTO m_fixed_value VALUES ('F0016', '01', '未定',    1);
INSERT INTO m_fixed_value VALUES ('F0016', '02', '退款中',  2);
INSERT INTO m_fixed_value VALUES ('F0016', '03', '已退款',  3);
-- 发票类型 --
INSERT INTO m_fixed_value VALUES ('F0017', '01', '全国性社会团体会费统一收据',    1);
INSERT INTO m_fixed_value VALUES ('F0017', '02', '增值税专用发票',                2);
INSERT INTO m_fixed_value VALUES ('F0017', '03', '增值税普通发票',                3);
-- 开票状态 --
INSERT INTO m_fixed_value VALUES ('F0018', '01', '待开票',    1);
INSERT INTO m_fixed_value VALUES ('F0018', '02', '已开票',    2);
INSERT INTO m_fixed_value VALUES ('F0018', '03', '开票申请',  3);
-- 取票方式 --
INSERT INTO m_fixed_value VALUES ('F0019', '01', '电子票据',    1);
-- 订单方式 --
INSERT INTO m_fixed_value VALUES ('F0020', '01', '个人订单',    1);
INSERT INTO m_fixed_value VALUES ('F0020', '02', '推荐订单',    2);
-- 站内消息类型 --
INSERT INTO m_fixed_value VALUES ('F0021', '01', '全体消息',    1);
INSERT INTO m_fixed_value VALUES ('F0021', '02', '个人消息',    2);

-- 会费计算方式类型 --
INSERT INTO m_fixed_value VALUES ('F0022', '01', '月额型计算',    1);
INSERT INTO m_fixed_value VALUES ('F0022', '02', '日额型计算',    2);
INSERT INTO m_fixed_value VALUES ('F0022', '03', '年额型计算',    3);

-- 中国，甘肃区分 --
INSERT INTO m_fixed_value VALUES ('F0023', '01', '中国学会',    1);
INSERT INTO m_fixed_value VALUES ('F0023', '02', '甘肃学会',    2);

-- 用户审核状态 --
INSERT INTO m_fixed_value VALUES ('F0024', '01', '新注册',		1);
INSERT INTO m_fixed_value VALUES ('F0024', '02', '等待审核',		2);
INSERT INTO m_fixed_value VALUES ('F0024', '03', '审核通过',		3);
INSERT INTO m_fixed_value VALUES ('F0024', '04', '审核不通过',	4);
INSERT INTO m_fixed_value VALUES ('F0024', '05', '返回修改',		5);

-- 短信模板类型 --
INSERT INTO m_fixed_value VALUES ('F0025', '01', '会员注册验证码通知模板',		1);
INSERT INTO m_fixed_value VALUES ('F0025', '02', '会员验证码通知模板', 		2);
INSERT INTO m_fixed_value VALUES ('F0025', '03', '用户审核通过通知模板',		3);
INSERT INTO m_fixed_value VALUES ('F0025', '04', '用户审核不通过通知模板',		4);
INSERT INTO m_fixed_value VALUES ('F0025', '05', '用户审核返回修改通知模板',	5);
INSERT INTO m_fixed_value VALUES ('F0025', '06', '管理员创建通知模板',			6);

-- 有效状态 --
INSERT INTO m_fixed_value VALUES ('F0026', '0', '无效',    1);
INSERT INTO m_fixed_value VALUES ('F0026', '1', '有效',    2);

-- 抬头类型 --
INSERT INTO m_fixed_value VALUES ('F0027', '01', '个人',    1);
INSERT INTO m_fixed_value VALUES ('F0027', '02', '单位',    2);

-- 会员基本信息 --
-- DROP TABLE IF EXISTS m_user;
CREATE TABLE m_user
( 
    id               VARCHAR(50)  NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
    name             VARCHAR(70)  NOT NULL COMMENT '会员名称(max64)',
    user_type        VARCHAR(6)   NOT NULL COMMENT '会员类型(F0002)',
    password         VARCHAR(200)          COMMENT '密码',
    membership_path  VARCHAR(6)            COMMENT '入会途径(F0012)',
    focus_on         VARCHAR(255)          COMMENT '关注(F0012)',
    sex              VARCHAR(6)           COMMENT '性别(F0001)',
    birth            VARCHAR(10)           COMMENT '出生年月',
    nationality      VARCHAR(6)            COMMENT '民族(F0005)',
    political        VARCHAR(6)            COMMENT '政治面貌(F0003)',
    edu_degree       VARCHAR(6)            COMMENT '学历(F0004)',
    bachelor         VARCHAR(6)            COMMENT '学位(F0006)',
    position         VARCHAR(6)            COMMENT '职务(F0007)',
    employer         VARCHAR(70)           COMMENT '工作单位(max64)',
    employer_type    VARCHAR(6)            COMMENT '单位性质(F0008)',
    job_title        VARCHAR(6)            COMMENT '职称(F0009)',
    certificate_type VARCHAR(6)            COMMENT '证件类型(F0010)',
    certificate_code VARCHAR(20)           COMMENT '证件号码(max18)',
    area             VARCHAR(6)            COMMENT '所在地区(F0011)',
    area_sub         VARCHAR(6)            COMMENT '所在地区市级(F0011)',
    address          VARCHAR(140)          COMMENT '通讯地址(max128)',
    postal_code      VARCHAR(10)           COMMENT '邮政编码(max10)',
    phone            VARCHAR(20)           COMMENT '手机号(max11)',
    mail             VARCHAR(70)           COMMENT '电子邮箱(max64)',
    application_date VARCHAR(20)           COMMENT '审请时间(yyyy-MM-dd)',
    check_date       VARCHAR(20)           COMMENT '审核时间(yyyy-MM-dd)',
    check_status     VARCHAR(20)           COMMENT '审核状态(F0024)',
    valid_status     VARCHAR(6)            COMMENT '有效状态',
    regist_date      VARCHAR(20)           COMMENT '入会时间(yyyy-MM-dd HH:mm:ss)',
    valid_start_date VARCHAR(20)           COMMENT '有效开始日期(yyyy-MM-dd HH:mm:ss)',
    valid_end_date   VARCHAR(20)           COMMENT '有效结束日期(yyyy-MM-dd HH:mm:ss)',
    society_type     VARCHAR(6)            COMMENT '学会区分(F0023)',
    group_name       VARCHAR(120)          COMMENT '团体证名称',
    deleted          int 	    DEFAULT 0  COMMENT '删除标记',
    credit_code      VARCHAR(120)          COMMENT '统一社会信用代码',
    created_by       VARCHAR(20)           COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)           COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)           COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)           COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
    PRIMARY KEY (id)
) COMMENT='会员基本信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_idx1 ON m_user (name);
CREATE INDEX m_user_idx2 ON m_user (phone);
-- 会员扩展信息 --
-- DROP TABLE IF EXISTS m_user_extend;
CREATE TABLE m_user_extend
( 
    id                 VARCHAR(50)  NOT NULL COMMENT 'UUID',
    introducer1        VARCHAR(50)           COMMENT '介绍人1(max32)',
    introducer2        VARCHAR(50)           COMMENT '介绍人2(max32)',
    photo              MEDIUMBLOB            COMMENT '2寸证件照jpg/png/jpeg200k',
    photo_ext          VARCHAR(10)           COMMENT '证件照文件扩展名',
    major              VARCHAR(70)           COMMENT '专业(max64)',
    educational_at     MEDIUMBLOB            COMMENT '学历证书附件',
    educational_at_ext VARCHAR(10)           COMMENT '学历证书附件文件扩展名',
    bachelor_at        MEDIUMBLOB            COMMENT '学位证书附件',
    bachelor_at_ext    VARCHAR(10)           COMMENT '学位证书附件文件扩展名',
    vocational_at      MEDIUMBLOB            COMMENT '职业证书附件',
    vocational_at_ext  VARCHAR(10)           COMMENT '职业证书附件文件扩展名',
    research_dir     VARCHAR(40)             COMMENT '研究方向(max36)',
    learn_experience VARCHAR(255)            COMMENT '主要学习经历(max250)',
    work_experience  VARCHAR(255)            COMMENT '主要工作经历(max250)',
    papers           VARCHAR(255)            COMMENT '代表性论文及著作(max250)',
    honors           VARCHAR(255)            COMMENT '获得科技奖励及荣誉情况(max250)',
    application_form MEDIUMBLOB              COMMENT '入会申请表',
    application_form_ext VARCHAR(10)         COMMENT '入会申请表文件扩展名',

    PRIMARY KEY (id)
) COMMENT='会员扩展信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 会员卡信息 --
-- DROP TABLE IF EXISTS m_user_card;
CREATE TABLE m_user_card
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_id          VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_code        VARCHAR(20)    NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
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
CREATE UNIQUE INDEX m_user_card_idx2 ON m_user_card (user_code);

-- 会员会费相关设定 --
-- DROP TABLE IF EXISTS m_user_type_settings;
CREATE TABLE m_user_type_settings
( 
    user_type        VARCHAR(6)    NOT NULL COMMENT '会员类型(F0002)',
    fee_amount       DECIMAL(10, 2)    NULL COMMENT '会费金额',
    effective_year   int 	       NOT NULL COMMENT '会费年数',
    created_by       VARCHAR(20)            COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)            COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)            COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)            COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
       
    PRIMARY KEY (user_type)
) COMMENT='会员会费相关设定' ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `m_user_type_settings`(`user_type`,`fee_amount`,`effective_year`,`created_by`,`created_at`,`updated_by`,`updated_at`) VALUES('0101', 300, 3, null,null,null,null);
INSERT INTO `m_user_type_settings`(`user_type`,`fee_amount`,`effective_year`,`created_by`,`created_at`,`updated_by`,`updated_at`) VALUES('0105', 500, 5, null,null,null,null);

-- 会员审核历史信息 --
-- DROP TABLE IF EXISTS m_user_check_history;
CREATE TABLE m_user_check_history
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
	user_id          VARCHAR(50)    NOT NULL COMMENT '会员号(UUID)',
    check_status     VARCHAR(3)     NOT NULL COMMENT '审核状态(F0024)',
    check_date       VARCHAR(20)    NOT NULL COMMENT '审核时间(yyyy-MM-dd HH:mm:ss)',
    memo             VARCHAR(255)            COMMENT '审核建议(max150)',
    created_by       VARCHAR(20)             COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)             COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)    		 COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
    
    PRIMARY KEY (id)
) COMMENT='会员审核历史信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_check_history_idx1 ON m_user_check_history (user_id);

-- 会员订单信息 --
-- DROP TABLE IF EXISTS m_order;
CREATE TABLE m_order
( 
    id               VARCHAR(30)    NOT NULL COMMENT '订单号(yyyyMMddHHmmssSSSR7)',
    user_id          VARCHAR(50)    NOT NULL COMMENT 'UUID',
    pay_path         VARCHAR(3)     NOT NULL COMMENT '缴费渠道(F0012)',
    order_method     VARCHAR(3)     NOT NULL COMMENT '订单方式(F0020)',
    order_type       VARCHAR(3)     NOT NULL COMMENT '订单类型(F0015)',
    pay_type         VARCHAR(3)     NOT NULL COMMENT '缴费类型(F0014)',
    order_amount     DECIMAL(10, 2) NOT NULL COMMENT '订单金额',
    pay_amount       DECIMAL(10, 2)          COMMENT '实收金额',
    pay_date         VARCHAR(20)    NOT NULL COMMENT '缴费时间(yyyy-MM-dd HH:mm:ss)',
    check_date       VARCHAR(20)           COMMENT '审核时间(yyyy-MM-dd)',
    check_status     VARCHAR(3)            COMMENT '审核状态(F0013)',
    refund_date      VARCHAR(20)           COMMENT '退款时间(yyyy-MM-dd HH:mm:ss)',
    refund_status    VARCHAR(3)            COMMENT '退款状态(F0016)',
    bill_status      VARCHAR(3)            COMMENT '开票状态(F0018)',
    memo             VARCHAR(255)          COMMENT '备注(max250)',
    ans              VARCHAR(255)          COMMENT '回执(max250)',
    invoice_type     VARCHAR(2)            COMMENT '发票抬头类型(F0027)',
    invoice_title    VARCHAR(64)           COMMENT '发票抬头',
    invoice_amount   DECIMAL(10, 2)        COMMENT '发票金额',
    credit_code      VARCHAR(20)           COMMENT '纳税人识别号',
    mail             VARCHAR(70)           COMMENT '电子邮箱(max64)',
    PRIMARY KEY (id)
) COMMENT='会员订单信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_order_idx1 ON m_order (user_id);

-- 会员发票信息 --
-- DROP TABLE IF EXISTS m_bill;
CREATE TABLE m_bill
( 
    id               VARCHAR(30)    NOT NULL COMMENT '订单号(yyyyMMddHHmmssSSSR7)',
    user_id          VARCHAR(50)    NOT NULL COMMENT 'UUID',
    bill_code        VARCHAR(50)             COMMENT '发票代码',
    bill_type        VARCHAR(3)              COMMENT '发票类型(F0017)',
    bill_amount      DECIMAL(10, 2) NOT NULL COMMENT '开票金额',
    invoice_type     VARCHAR(2)              COMMENT '发票抬头类型',
    invoice_title    VARCHAR(64)    NOT NULL COMMENT '发票抬头',
    credit_code      VARCHAR(20)             COMMENT '纳税人识别号',
    mail             VARCHAR(70)             COMMENT '电子邮箱(max64)',
    bill_date        VARCHAR(20)             COMMENT '开票时间(yyyy-MM-dd HH:mm:ss)',
    check_status     VARCHAR(3)              COMMENT '审核状态(F0013)',
    vote_method      VARCHAR(3)              COMMENT '取票方式(F0019)',
    bill_memo        VARCHAR(255)            COMMENT '备注(max250)',
    PRIMARY KEY (id)
) COMMENT='会员发票信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_bill_idx1 ON m_bill (user_id);

-- 注册认证验证码 --
-- DROP TABLE IF EXISTS m_auth_code;
CREATE TABLE m_auth_code
( 
    id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
    auth_method      VARCHAR(6)     NOT NULL COMMENT '验证方式01手机，02邮箱',
    recieved_by      VARCHAR(64)    NOT NULL COMMENT '手机号，邮箱地址',
    auth_code        VARCHAR(6)     NOT NULL COMMENT '验证码',
    invalid_date     VARCHAR(20)    NOT NULL COMMENT '过期时间(yyyyMMddHHmmss)',
    PRIMARY KEY (id)
) COMMENT='注册认证验证码' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_auth_code_idx1 ON m_auth_code (recieved_by);
CREATE INDEX m_auth_code_idx2 ON m_auth_code (invalid_date);

-- 订单发票图片信息 --
-- DROP TABLE IF EXISTS m_image;
CREATE TABLE m_image
( 
    id               VARCHAR(50)    NOT NULL COMMENT '订单号(yyyyMMddHHmmssSSSR7)',
    order_photo      MEDIUMBLOB              COMMENT '订单图片',
    order_photo_ext  VARCHAR(10)             COMMENT '订单图片文件扩展名',
    bill_photo       MEDIUMBLOB              COMMENT '发票文件',
    bill_photo_ext   VARCHAR(10)             COMMENT '发票文件扩展名',
    PRIMARY KEY (id)
) COMMENT='订单发票图片信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 站内消息 --
-- DROP TABLE IF EXISTS m_message;
CREATE TABLE m_message
( 
    id               VARCHAR(50)    NOT NULL COMMENT '站内消息ID(yyyyMMddHHmmssR3)',
    msg_type         VARCHAR(6)     NOT NULL COMMENT '站内消息类型(F0021)',
    user_id          VARCHAR(50)             COMMENT 'UUID',
    title            VARCHAR(255)   NOT NULL COMMENT '标题',
    msg              TEXT           NOT NULL COMMENT '消息内容',
    regist_date      VARCHAR(20)             COMMENT '消息时间(yyyy-MM-dd HH:mm:ss)',
    PRIMARY KEY (id)
) COMMENT='站内消息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_message_idx1 ON m_message (user_id);
CREATE INDEX m_message_idx2 ON m_message (msg_type);

-- 站内消息已读状态 --
-- DROP TABLE IF EXISTS m_message_read;
CREATE TABLE m_message_read
( 
    id               VARCHAR(50)    NOT NULL COMMENT '站内消息ID(yyyyMMddHHmmssR3)',
    user_id          VARCHAR(50)             COMMENT 'UUID',
    PRIMARY KEY (id)
) COMMENT='站内消息已读状态' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_message_read_idx1 ON m_message_read (user_id);

-- 短信模版设定 --·
-- DROP TABLE IF EXISTS m_sms_config;
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
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"03","20b7538df114416781a38e8dc3049b5a","用户审核通过通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"04","3377f1d924134e72bb75b16fbf219bbb","用户审核不通过通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"05","13cd8c937c82420b80db8c7fd4e8453d","用户审核返回修改通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"06","a258e93ca6b2475ea8728d95b946644d","管理员创建通知模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"07","12a6274e987c414c98bdef0c95627f7e","管理员密码重置模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);
INSERT INTO m_sms_config(id,template_type,template_id,template_name,api_url,app_key,app_secret,sender,signature,created_by,created_at,updated_by,updated_at) VALUES(UUID(),"08","ead4a059630848cdaaafcda1014a507f","会员密码重置模板","https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1","zn7A3K61J3bz808w7671r4Mw0Rl9","0x1Ey9niYOt4yEv9VvKkcxh1kOaI","8824082205510","甘肃省针灸学会","9999",NOW(),NULL,NULL);


-- 系统信息设定 --·
-- DROP TABLE IF EXISTS m_system_config;
CREATE TABLE m_system_config
( 
	id               VARCHAR(50)    NOT NULL COMMENT 'UUID',
    pay_qrcode       MEDIUMBLOB              COMMENT '支付二维码',
    opening_bank     VARCHAR(128)            COMMENT '开户行',
    account_name     VARCHAR(128)            COMMENT '账户名',
    account_number   VARCHAR(20)             COMMENT '账号',
    credit_code      VARCHAR(20)             COMMENT '纳税人识别号',
    created_by       VARCHAR(20)             COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    created_at       VARCHAR(20)             COMMENT '创建时间(yyyy-MM-dd HH:mm:ss)',
    updated_by       VARCHAR(20)             COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    updated_at       VARCHAR(20)             COMMENT '更新时间(yyyy-MM-dd HH:mm:ss)',
    
    PRIMARY KEY (id)
) COMMENT='系统信息设定' ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO m_system_config(id,pay_qrcode,opening_bank,account_name,account_number,credit_code,created_at) values('360b8bda-8856-11ef-9994-900f0c240974'
,0xFFD8FFE000104A46494600010100000100010000FFDB00430006040506050406060506070706080A100A0A09090A140E0F0C1017141818171416161A1D251F1A1B231C1616202C20232627292A29191F2D302D283025282928FFDB0043010707070A080A130A0A13281A161A2828282828282828282828282828282828282828282828282828282828282828282828282828282828282828282828282828FFC0001108028B01E003012200021101031101FFC4001F0000010501010101010100000000000000000102030405060708090A0BFFC400B5100002010303020403050504040000017D01020300041105122131410613516107227114328191A1082342B1C11552D1F02433627282090A161718191A25262728292A3435363738393A434445464748494A535455565758595A636465666768696A737475767778797A838485868788898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE1E2E3E4E5E6E7E8E9EAF1F2F3F4F5F6F7F8F9FAFFC4001F0100030101010101010101010000000000000102030405060708090A0BFFC400B51100020102040403040705040400010277000102031104052131061241510761711322328108144291A1B1C109233352F0156272D10A162434E125F11718191A262728292A35363738393A434445464748494A535455565758595A636465666768696A737475767778797A82838485868788898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE2E3E4E5E6E7E8E9EAF2F3F4F5F6F7F8F9FAFFDA000C03010002110311003F00F7CF1CF8BED3C2B6224957CDBB901F2611D588EE7D16BC17C43E30D6F5F959AEEF658E1CF10C44A201F41D7F1CD47E38D69F5EF125DDE97DD1162908DDC08D785C7D7AFD49AC2CD5451F2D8CC74EB49C62EC8526928A2B43CE0A28A2980514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140AE6FE81E30D6F43995ACEFA4688758643BE33F81E9F860D7BE7813C5B69E2AB067897CABB8B02788FF00093DC1EEA6BE64ADDF056B6FA07892C6F91FF75BC24A07468DB86FCBAFD40A4D5CF47078E9D1928C9DE2CC26E5F8E828E9C50838F7A5EB491E73128A2B5FC2DA0DD788F568EC6CFE527E67908CAA28EE682E3094E4A31576CC8A2BE89D0FE19787EC2051756E6FA7FE2926CE09F6507007E7F5AD9FF841BC35FF00405B3FFBE28E63D48E51564AEDA47CBB457D45FF00083786BFE80B67FF007C527FC20DE1AFFA02D9FF00DF14B98AFEC6A9FCC8F97A8AFA8BFE105F0D7FD016CFFEF8A3FE105F0D7FD016CFFEF8A39C3FB1AA7F323E5DA2BEA2FF008417C35FF405B3FF00BE28FF008417C35FF405B3FF00BE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFF00EF8A3FE105F0D7FD016CFF00EF8A39C3FB1AA7F323E5DA2BEA2FF8417C35FF00405B3FFBE28FF8417C35FF00405B3FFBE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFFEF8A3FE105F0D7FD016CFFEF8A39C3FB1AA7F323E5DA2BEA2FF008417C35FF405B3FF00BE28FF008417C35FF405B3FF00BE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFF00EF8A3FE105F0D7FD016CFF00EF8A39C3FB1AA7F323E5DA2BEA2FF8417C35FF00405B3FFBE28FF8417C35FF00405B3FFBE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFFEF8A3FE105F0D7FD016CFFEF8A39C3FB1AA7F323E5DA2BEA2FF008417C35FF405B3FF00BE28FF008417C35FF405B3FF00BE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFF00EF8A3FE105F0D7FD016CFF00EF8A39C3FB1AA7F323E5DA2BEA2FF8417C35FF00405B3FFBE28FF8417C35FF00405B3FFBE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFFEF8A3FE105F0D7FD016CFFEF8A39C3FB1AA7F323E5DA2BEA2FF008417C35FF405B3FF00BE28FF008417C35FF405B3FF00BE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFF00EF8A3FE105F0D7FD016CFF00EF8A39C3FB1AA7F323E5DA2BEA2FF8417C35FF00405B3FFBE28FF8417C35FF00405B3FFBE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFFEF8A3FE105F0D7FD016CFFEF8A39C3FB1AA7F323E5DA2BEA2FF008417C35FF405B3FF00BE28FF008417C35FF405B3FF00BE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFF00EF8A3FE105F0D7FD016CFF00EF8A39C3FB1AA7F323E5DA2BEA2FF8417C35FF00405B3FFBE28FF8417C35FF00405B3FFBE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFFEF8A3FE105F0D7FD016CFFEF8A39C3FB1AA7F323E5DA2BEA2FF008417C35FF405B3FF00BE28FF008417C35FF405B3FF00BE28E70FEC6A9FCC8F9768AFA8BFE105F0D7FD016CFF00EF8A3FE105F0D7FD016CFF00EF8A39C3FB1AA7F323E5DA2BEA2FF8417C35FF00405B3FFBE2B1B5DF85FE1FD46DD96D603613E3E596027F5527047E5F5A39C9964F552D1A3E76A2B5BC53A0DD786F5892C6F3E6006E8DC74753D08AC91D2ACF2A709424E32566828A28A04145145200AF72F8096312E85A85E6DC4B25C7944F7DAAA081F9B1AF0DAF7EF80DFF00227DD7FD7E3FFE8095323D4CAA29D7BBE88F49518A5A28ACCFA60A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A2AAEAB6D2DE697796D6F72F6B34D0BC693C7F7A26652038F704E7F0AF92EEB5CBDD0BC77E23F0F78ABE2CEB9A641A69885B4FE4BCAD7059373642E76E323EB9A00FAFA8AF92BC31ACEA5E20F8ABA3F87FC37F14B5CD574C9A06B89AE5E268CAB26E6F2F6B6090428E7DEBBED2F56F8BBAD5D5EAE8BACF80EF63B5B87864557919E32091B5C2AF078A00F77A2BC6FE0978CFC5FE29F12789ECFC46FA53D9E8D27D93CDB08D82493EE39DAC4F20053DBF8857B250014572DF11FC67A6F817C3173AC6AAD958C6D8A153879E43F7517DCFE8327B57847C3FF0017F8ABC11AEDBEB9F130DCAF87BC58C668A432B18EC2666CA8643F7015C71FDDFF00758000FA828A8ADA78AE2049A07592270195D4E4106A5A0028AE3FC3BE33D17C73E15D4B51F0E5CCB3DAC5E65BBBB44F110E1031037007A30E6B8FFD96EF6EF50F8496B75A85DCF73335CCF992690BB60363A93401EC1457CCDF0C7C6363AE7C63F16789F55F15DBE9BA346FF66B2B3B8D41614B9C0D8AFB0B00C02AE7A7DE71E95EE1FF000B07C169FF00337787BFF0650FFF0015401D4D15C8F8D3C5773A2F87ECF56D0745BCF12ADCCA889169E77131B23309410082BC0E7A7CC2BC17E337C44F116A69E17F37C21E22F0F987548DC1964D9F6AFF00A64300673EF401F54515E5BA37C4EF106A1ACD8D95CFC37F1158C17132C6F7530CC70827966C2F4158FE10F15EAFAFFC7AF154316AED1F85B47892DDAD9B6EC92E31B4E323230C242707F8466803DAA8AAF1DF5AC8E122B889DCF455704D790FC4DF13FC51F0B41AE6B36769E136F0ED912F11B8F39A768F200C856037648F4A00F66A2BC63C1DAEFC5CD7ED346D52E2C3C2116917DE4CEDB7CF59C42F82480588DDB4F154EE63F8909E287D1D7E2478753519774D05949649E6888924606D05B001FCA803DCE8AF9EF5AD67E25F863E20F83344D5FC4BA76A10EB577B245B7B158CAC48C9BF248EE18F4F4AFA12800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2803CA3E3E58C4DA169F785479D1DCF97BB1FC2CA491F9A8AF0EAF7DF8F5FF2285B7FD7E2FF00E80F5E055AC763E63344956D3B05145154796145145030AF7EF80BFF00227DD7FD7E3FFE8095E035EFDF013FE44FBAFF00AFC7FF00D012A267AB957F1BE47A48E94B483A52D667D28514514005145140051451400514514005145140051451400C9A548632F2B2A20EA58E057CAD75A8DE43F1B3E205C68DAB782AD11E4B52CFE219308E0443FD511DC739FC2BE9CF106976DADE8B7BA5DFA17B4BC85E09941C12ACA41C1EC79AF95FC5BE1083C0FE298F4CB0F03F8566B4BA9445A7DE6B3A9B335CFCA0F31B4A3183C13B40CE3D464022D0FC5D6F63F1EF40D4FC49ABF85E78E3B292013F87A3768033EF0AADC64B92DD7A608AD4F88B61A2C5E3DB587E0A5C5D43E359E42B751692E3EC6919CEE697F857071C0E063900E2B17E2069BE33F01C963E339F42F05E8B340C2C621A642DC170C7718F2509FBDF31E7A574B2780359D2FC3D73ADEA3F1534FD0F4BBF637535CE956690ACE5B9E1A3285C9C9C0E7D00A07637BE01788ACFC1CF1FC39F1269571A378904CF22C926644D459893E62B81E8303B617AE78AEFBE267C56F0EF802029A8DC1BAD55803169D6E434CD9E84FF00747B9FC01AAFF07FC4961E35D12DEF8DADCCF79A48FB1C5A9DE59794D72A54032A31CE376DF99431C10338C8AEAF55F0EE8D77ABDBEBB77A55ADCEA9631B0B7B878C1741D700FD4707B64E31939047CD76DE22D2357F8811F883E37EA434CB8B455974BF0EC96D33A408C032BC98420E720E0F248E70005AF4DF127C5AF84BE23D0EEB49D6B5C86E6C2E5363A3595C0FA107CBE083C823A115CC68FF0013352F1BDBB6AFA67C1D87588CC8616B96BC899B7281F292D167804568FF00C241E24E9FF0A16DBFF02ADFFF008D50070FE0EF1DEB5F0F6D6EA6F0CDC4BE35F8736B279624314914D61900ED2594100023B143D8A92457D0DF0DFC7BA47C40D15B52D0D6ED2147F2DD2E612855B00E33CA9EBD89AF3FF00FC49B89FC7F0F83AFBC011785EE6E616B97DB708D9014904AAC601CEDC75AF579058787745B99A2B78ED2C2D924B9912DE1C0039772154724F2781926803C4FF64FFF009235AF7FD846E7FF0044455C27863C612F87FF00668B0D2349DCFAF6BF777163671467E7C349B5D87E0C147BB0F4A6FC04F8930786BE1EEABA0DB681AE6B1A94F7734E16C6DC3A223448A0B36723953DABD3BF65CD3ACEFBE11E8D3DED9C134F69797124124B186689B711952470707B500617C06F057813C4DE1DBCD3F55F0B4475CD0E7FB1DF3DCB967924E496E3181B83A81E8B59DF053E1E7853C47E31F88CF7FA1DADC69767A9FD96C1086DB1AABCB9DB83E812AC78AFC427E0EFC6CF11EA13C4CDA4788B4D6BB8950641BA40700FD5C367D3CD06BBFF00D993409745F85967737808BED5A57D4662DD4EFC05FCD541FC6803A9F1B5AAE95F0F2E2CF49D6A2F0C456D1430C17ECA192D115D140C31E7E51B793DEBC1B5ED26D3C49F611ADFC7AD22E96C665B9B70D6B02F9728E8DC4833F8D7B97C59B9D460F0E470E9DE1587C511DC4C23B8B29E658A358C02DBD8B0230182D786EBDAFA787134EFED7F815E19B74D466582D4FDA6D6412B9E80158CF5CF53C50074FA66B7AF69B6DAC5F691F1163F8817B6D612343A458DA421F79655129D8C495524646327B561F80BE15F8121D3747B4F894DBFC65ADE6ED6DE6BA922721DBE54014805BB9CF3926BB2F01BEB563E30B703E10695E1B86E14C5737F67776E5A38C8DD82B1A8241655AE57E2FF00882CEC3F69BF027DA36B45691C49213D23695E4504FD01534017FE15FC378B44F8E9E21D4AC741BAD3341D360F22C1A7DE44D2300ACE8CE4EE1C49DFA30AB5FB5D78892CFC17A7F87A1676B8D62E54BAC632DE4C6431C0F52DB31EB835EF75F1FF00C73D07C4771E336F15789956DE14D6EDB4BD32047DCAD6E03BEF07DC81FF0002661D85007AFF00857E2F69936B5A0F8597C2BE25D21EE80B6B4FED0B558576A2FBB648000E82A8787634D4BF6ABF13DCBFCC34AD222855BB2B388CFF002671F9D4DF172458BE37FC2A77F9577DE1DDEC1149AC0F10E85F08B5EF12EA1AECFE3E6B6BDD41B74DF67D5A3897800018DB9C703A9A00DDF177FC4C7F6A5F05DA13BA3D3F4B9AED87A1612A8FD4257B757C99E2CF08FC2FB7D0B57D43C3DE34BFBDD7E0B2965B609AA2C8CC510B6385C91C648CD7B9FC055917E11F865A692492492D7CD67918B312CECDC93F5A00F40A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2803CD3E3D7FC8A16DFF5F8BFFA03D78157BEFC7AFF009142DBFEBF17FF00407AF02ADA3B1F339AFF001BE414514533CA0A28A281857BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095123D5CA7F8DF23D285140A2B33E9428A28CD0014525140AE2D1494A2818514514005145140051451401CD78F53C46FE17BCFF843A7B38359E0C4D789B9319E7BE01C7209047B7A7CCB05D69FA578809D3A63F127E275D70B39CCB6762DEAB9E1B6F63C01FECF7FAFEB0F42F0B687E1D92EE4D0B4AB3B192EE432CEF0C6159D89CF27D3D0741D85007CEBF19743F11E83F03EC61F18EB6755D46E35B865395C88772C84C61BAB0CE4F400741C62ACF8C3E0ADDF84FC430F88BC1BA5DAF8874AB76673E1FD40971164E4F9593861E80E4E4F46EDEFFE2DF0B68DE2ED362B1F11D8A5EDA47289963766501C0201F9483D09FCEB6874A06791FC3DF8D5A478935CB6F0E4BA16ADA4EB4D9536AF08648F68C9E4608000EEA2BD6A6C0B697FDD3FCAA16B3B6FB6ADE1B684DDAA14598A0DE14F501BAE381C55965124655BA11834033E4FFD9DBC75ACF86BC00F65A7781B5CD72237D2B9BAB35FDD82553E5E879181F9D7A8FF00C2DCF137FD129F14FF00DF3FFD8D7A2F843C2BA3783F4A3A67872CC59D8995A6F2848EFF0039C64E5893D877ADCA047CC5E15D7EF7C49FB5269F79A9E857BA14FF00D96E9F64BCFF0059808FF3741C1FE95F4DB00C0A90083C107BD651F0DE90DE244D7DEC213AC247E4ADD63E7098236E7D3935AF4019373676BA6681756FA7DB41696F1C0E122850220F94F451815E0FF02BC7FE1CF057C0FB1B8F106A5141299AE1D6D94EE9A5F9CFDD4EA7EBD3D4D7D1373124F04914AA1A3914A329EE08C62B904F86BE0E1A7D858B787EC5ED6C673716F1C885C239EBD49C83FDD391C0E381401E11AAF877C59FB41DD9D66E615D03C3D6914834A5B85DCD33B63E627AE0ED1961C0C0001E4D759E0BF8D7FF0008BCA9E17F8AF653E8DABDA288D6F5612609D4701B0A38CE3AA82A79E9D2BDF5400A0280140C003A0ACCD7740D2B5F8A18B59D3ED2F521712462E220FB18107233D3A0A00E17E39DEF88AF3E1FC769E08D3E6BE9B5A64B56B98C8020864C0DE41E7041C6718504938C0AF1DF8891687A1D97C36F03E91A9A5EEA7A56B110BB8C6E2EB2161B8907A02C4E07A63EB5F5728C2E38C76C0AC11E0DF0F8F11C9E207D1EC5F5B90AE6F24883483680A0A93F74ED006462803C975E6BAF851F1275DF17DC5EA1F08EAF07993DBCB39333DDE0ED8E15E4939E73C28573CF02B9EF087C34BEF895E19F1678ABC531A5A6B3E25DAFA66ECE6D63421A33EBB4ED45F5DAB9EF5F43F88342D2FC4362969ADD8417B6C92A4CB14CB901D4E437F9EA090782456900142AAED000E303B50078EFC18F89936A971FF086F8C6392D3C65A7830B0607174A83EF83D376393D8F51D7020FDA8B47BED73C31E1DB4D3ED2F2E5BFB622693ECB1B3BC69B1C17E01C633D6BD7D34BB15D51F534B38175078840D73E58F30C60E42EEEB8CF6ABC2803C087C16BBD17E23F84F57D2352D5756D3ED6494DE9D5AF5656894A80046368E0E4E7E82BD33FE15A781D7FE64FD00FFDB845FF00C4D76341A00F993E37DD7C2FD33C05A9C3E1683C39FF000914E56DA14D3523F3937381267672BF2EF1CFAE3BD7BDF8074B7D17C17A0E9B32ED96D2C208241FEDAC6037EB9AC8B3F861E0DB4F10DC6B70E836ADAA4F3B5CBCF29693F78CDB8B0562554E79E00C576F4005145140051451400514514005145140051451400514668A0028A283401E69F1EBFE44FB5FFAFD5FFD01EBC0ABDF7E3D7FC89F6BFF005FABFF00A03D78156D1D8F99CDBF8DF20A28A299E585145140057BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095133D5CA7F8FF0023D26969296B33E942B26EAF7508B5882DA0D30CD62EB992EFCF55119E78D8793DBA7AD6B52501639F4D535A36D7D23680EB2C2C04111BB8F33827939E8B81CF34E9354D63CED3D1742252600DCB7DAD3FD1BD78FE3C7B56F52E2833E47FCCFF000FF2301354D649D401D058083FE3DCFDB23FF48E7FF1DE39E691B54D616DB4F90682CD2CED8B88FED918FB38CF5CF47E39E2B7E8A0391FF33FC3FC8C41A8EADF6EBC88E8ADF668A32D0CFF006A4FDF30C6176F55CF3C9F4AAD2EAFAE2E9D14F1F8759EE99CABDB7DB630517B36EE873E95D25140723FE67F87F918ED7FAA7F6A4900D1CFD882656EBED29F3363EEECEA39E33551757D77FB2FCFFF00846DCDE79BB3ECDF6D8FEE633BF7F4EBC63AD74746280E47FCCFF0FF00231CDEEA8355860FEC926C9E3DCF75F685F91B07E5D9D4FD47AD53FED4D73FB3EE26FF008479FED11B81141F6D8FF7AA7AB6EE831E86BA4A4A07C8FF0099FE1FE4627F69EAA2E6C53FB11BC89A30D3CBF6A4FF00476EEA57AB63D4545FDABAC986FDDB406568180B653771FF00A48C9C9CFF000600CF3EB5D0D1407B37FCCFF0FF002307FB4B57CD87FC489B6CDFF1F07ED71FFA3FFF0017C64F140D4F58135F29D0C88E2526D9C5DA1FB41EC31FC39F7ADEA280F66FF99FE1FE4738FAAEB62CACE51E1D633CAC7CE87ED91FEE141E1B7746CF5C0AB51EA1A99D46EE03A4116B1C7BA1B8FB4A1F39B03E5DBD57B8C9F4AD9A280E47FCCFF0FF00239F3AB6B634A4B8FF008475BED864DAD6BF6D8FE55FEF6FE9F85596D47521AC35B0D209B2099FB5FDA53EF63EEECEBD78CD6BD1407B37FCCFF0FF00239D4D635D3A5BCEDE1B65BC59760B5FB6C6494FEFEEE9F854C6FF00531A9DAC3FD907ECB247BA4B8FB4A7EE9B07E5DBD5BB0C8F5ADCA280F66FF99FE1FE473CBAAEB26CEF247F0FB2DCC4C0430FDB23FDF82719DDD17039C1A7B6A3AC09B4F51A1318E6506E1C5DA7FA39EE31FC78F6ADEA280F66FF0099FE1FE46047A9EB0C6FF7682EBE4FFC7B7FA5C7FE93C9FF00BE3800F3EB4B26A7ABA2E9E7FB05899DB1703ED71FFA30CF53FDEE39E2B7A8A0391FF33FC3FC8C4FED0D57ED17A9FD8A7CB8632D6F27DA93FD21BB281FC39F5355DF55D70585BCCBE1D77B991D8496E2F23FDD28E8DBBA1CFA0AE8E8A0391FF33FC3FC8C37D4753FED29E01A337D91232D1DCFDA53F78D8042ECEA3272327D2ABFF6AEB834A5B85F0E937BE6EC36BF6D8F84FEFEFE9F875AE928A0391FF33FC3FC8C837FA91D552DFF00B21BEC453735D1B85F95B1F77675EBC66A9AEADAEFF67CB31F0E3FDA964D8B6DF6D8FE65FEF6EE83E95D1D140B91FF0033FC3FC8C41A96AA6FAD22FEC52209630D34DF6A4FDC31EABB7AB638E47AD42355D68DADEC8DA03ACD0B05822FB647FE91938241E8B81CF35D0D140723FE67F87F9184752D5C3E9E0686CC2703ED2C2EA3FF00463DC7FB78F6A69D4B5ADFA8674260B067ECE7ED71FF00A4F38FF807AF35BF4501C8FF0099FE1FE473A756D696DAC5C787A469666617117DB23CDB807839FE2C8E78A9D751D54DEDF467472208A32D04BF6A4FDFB765DBD57EA6B6E8A0391FF33FC3FC8E6DB56D7469B0CEBE1C76BB790ABDA8BD8F28A3F8B7743F415725D43535D55EDC6904D984DCB77F694F99B1F776751CF19AD8A280E47FCCFF000FF239BFED7D77FB33CFFF008470FDB3CDD9F65FB747F7319DFBFA75E315706A3A9FF6AC16E7486FB1BA6E92EBED09FBB6C7DDD9D4F3C6456C5140723FE67F87F91CF47AAEB6DA7DCCC7C3CE2E6370B1DBFDB23FDEAF76DDD063D0D4D26A5AB0BAB18C6887C895019E61749FB863D571D5B1EA2B6F34B9A0391FF33FC3FC8E7A3D535A315FB3680C1E1FF8F74FB5C67ED3C9EFFC3C0079F5A7B6A7ABE34EC685FEBF1F69FF004B4FF46E47FDF7C64F1E95BD9A3340723FE67F87F9195A7DEEA13EA57705D6966DAD63FF0053726E15C4DFF011CAFE35A945141485A0D141A0A3CD3E3D7FC89F6BFF005FABFF00A03D78157BEFC7AFF913ED7FEBF57FF407AF02ADA3B1F339B7F1BE414514533CB0A28A2800AF7EF809FF002285D7FD7E3FFE8095E035EFDF013FE450BAFF00AFC7FF00D012A247AB94FF001BE47A50A28145667D285252D25002D1451400954ED2FEDAF1A6169730CFE4B98E4F2DC36C61D54E3A1F6A835D9AF60D2E7974CB51777831E5C05C206E403C9E9C64FE15E3BE14F146A1E1F5F11DE5DE9F1A42D792B48DE682CB727A4407719EE3B034D2B9CB5F12A8B4A5B33D95759D39BED18BEB6FF466DB3FEF57F74738C373C7E3579595943290548C823BD78269D16BB1FC3FD56F1ED2C27D3F51DF71713C8E7CE2738C81D321B24576DF0EE4F140D2F478E786C5B4930A9F3BCC266D846578E9E83E94588A58BE69A8DB7573D1EAA5B6A16976F3A5A5D433BC0FE5CAB1B8631B7F75B1D0FB579F7C409F468F5D55D4BC51ACE99398548B7B377098C9F9B0148C9FE95E7DA05C68B6FAAEA4971E24D6AD20924DF15D5B33AF9DCFF18DB92DCF523D684AE2AB8C709F225F89F40C3A85ACD7735AC5730BDCC383244AE0B28232091DBAD5BAF9EA03A043AEDF5D49E32D523B6955764C9E70B97200C873E5E08E38F6C57AF780DEC64D143E99A9DE6A56FBDB135D162F9E323E600E29B8974715ED1B4D7E2740D796EB78968D3C22E5977884B8DE473CEDEB8E0F3ED516A5A85A69D12497F7515B46CC1034AC14163D066BCA2CA7FF008487C4FE29D762D4FEC11D8C696F6F74A9E6EC4DDCB05EF90ADFF7DD735E37D48DCD85AA0F187F6B62E1730FD93CAF2F83FBCCF7C7A7BD233A98CB2BA5F8AEF63E8746C8C77A75793E837AF79ABD9C36BF109AEDCC99FB3FD8B1E601C95C93C700D6C7C6BFF9134FFD774FEB48D56293A6E696DE6BF4B9E8145782F89F46F085B787AE2E34DD54CD7AA8AD1466E41DCD91DB1CF19A5B5D1FC1AFE1C8669B5765D45AD43B47F681FEB76648C63D6831FAFBFE55F7FF00C03DE68AE17E0E861E02B2C92C77CBD4FF00B6D581A2DDF8C7C437BAB1D375CB7B5B7B4B97802496B1B74638E76FA6283A3EB368C65CADDFB7F48F4C3A95A7F697D805CC26F36799E4EF1BF6FAE3D2AE5782784975EF10F8C353B9B2D7ADD751863F2BCF6814F9B106C6546DC01C2F6EF5D64F278D349F11E876F7FAA8BEB4BC9F6C9E4DA22845057EF1DBC6777AF6355CA654716E6AFCAED7F2FF0033D3B35145710CC5C432C7218D8A3EC6076B0EA0FA1F6AE63C757BADDBDB5B597876D1A6BBBD7319B86FBB6E00CEE3FD33E9DF8071BC29A068E746D5745B2D46597513228D42EA16C4BE66777048E99C8FCFBD49ACEB7BDCA91E899A7D79E7FC2B4B3FFA0FEBFF00F8123FF89AE9BC2FA1268360F6B15EDDDE2B48640F7526F7190063381C71FA9A070A927F12B7CCD19AF6DEDE78A2B8B88A29263B624770A643E8A0F5FC29D797B6D636E66BC9E18211C1795C228FC4D7996A328F14FC507B3B5B8F2FFB22D6431498DC04E703763BED665E3D52B9FD63548B53B19ACF50F1E2CF6ED8DD1FF65376208E42E7A81D28319E32DF67F147B9238750C8C0A919041C8229F9AF15D275493169A6E9BF10B3F720823FECA61E8AA3247D3A9AF42F880AEBE03D5433969041CBF4C9E39A0D215F9A0E56DBD0EA09A757825AE8DE0C7F0A47713EAECBAA7D977B47F6A03F79B738C63D6A3F09691E0FBDF0F5B5CEB5AA1835062FE647F690B8C31038FA62839D6335B596D7DFFE01EFF45799FC12541A5EAE2162D17DB982367AAED1835E97D3BD075D1ABED23CC538353B3B8BE9ACE1B889EE611992256CB27D476ABB5E316D0EB737C51F122F87EF6DED65DA9BDA74DC0AED5E0707BD759A769FE385BC81B50D674F96D15FF791A400332F700EDEB55CA634F14E57BC5EED74FF0033BBA462154963802B0FC496BABDCE9CA9A16A11D8DD8704CB2461C6DC1E3041EF8AE526D1FC762162FE28B4200CB66D13FF0089A5635A95F93ECB3BCD3B52B4D4ADFCFB0B986E21C95DF13865C8EA322AE5788FC34D3FC5173E1B57D0B5CB6B2B53339F29ADD5C939E7920D7A1F86B4EF14DB5FBC9AF6B705F5A98C858D2DD5086C8C1C803B67F3A2C674312EA453E57F85BF33A2BEBB82D2DDA7B99A38615C6E92460AA3270324FB9A923757456460CAC03020E4106BCC7E2DEA6B7D7FA4785E0976BDE5C46672A7EEA960141FC727FE0357E4F17EA9A5CAD636DE0FD425B6B63E4C6F116DACABC023E4E98028B0FEB115369EC8EDE5D46CE1BD8AD25BB812EA419485A401D87A85EA7A1ABB5E15AC7897509FE2068DA949E1DBD8AE2DE1758ED189DF2821B91F2FB9EDDABD9B46BA96FB4CB7BAB8B592D25950334127DE8CFA1E050D58AA188555BB74343145028A4740514514005068A0D0079A7C7AFF00913ED7FEBF57FF00407AF02AF7DF8F5FF227DAFF00D7EAFF00E80F5E055B4763E6736FE37C828A28A67961451450015EFDF013FE450BAFFAFC7FFD012BC06BDFBE027FC8A175FF005F8FFF00A02544CF5729FE3FC8F4A145028ACCFA50A434B48680168345140195E24BBBAB1D1E7B8B0B36BEBA5DBB2DD5B697CB01D707A673F8578B685A7DFEABA7F89E7D422B71670CF717135BB72E2E763631ECB9F5FCEBDB35EBE6D374B9EED2DA6BA68C03E4C232EDC81C0FC6BCF3C2769787C25E30B9BBB29ED5EFA4B89A2866421F050F6EBD4E3F0A68F3B174F9EA4579338285BC3C3C10165D67505D5BCA61F63567F2B76E381F771D307AD763F0CE4F0F1BAD2160D6F51975610E0D9B3B98B7796770C6DC600DD8E7B555D26CBC5377E014D26DBC3F0186589A31732CCA8FCB9392A79AEABC25FF00093E9F1E9BA7DE6856B1DA408B13CEB70A5F00637601A673D0A72552326B4B767FE652F8A5E31FEC9866D334D8D9F5131EE9650848810F7CFAF231E99AABA6DBC3A7F86F488748F12E9FA3486059AE52E12391E46755604EE39181D3DB15D37C4BD3927F08EAEF6F6DBEF658D1331A65D807040E3938ACBBBD2F48B4F0C6997FA9F871F54B936F0C5224506F947C80648F6C629DEC6D569CBDA49BEDFD6C725AAEBBAD43AA25969FE29B1BF97F8E66B68638631EF21E09E7A0CFE7C57A4949757F05CF6B63A85BDC5E3DB342D736E40432EDC1231D39AE23C17E18B5D5BC4BAC5EDF787E4B4D30A46B6F05D43B30703381F867F1AF51D374BB3D2ED841A75B476F0E49D88303345EE3C25192E672D9FA9E3FE15D224874BF1E6916A7CF962090A1660BB88DFC924E07E359DE21975193C2FE1FB29F42B78D5658562B98AEA393ED0CAA401F2F4CF5EB5D4E99753689AB78D2EA6D26FAF629AEE35F2E38B3E606DE3BF55E838CF5AC3D3FC27E2512DBEA76FA6225ADB5C1B8B5D2EE2E4E533CF1D31CE3AE0F1C8A472CA9592493EBB2E973760B6D6AF7C77A0DE5E786CE996F6EB28768E54907287A951C738FCEB47E379FF8A2B8FE2B841FCCFF004AB9A6F8BB569F53B7B0BEF0AEA36CCEDB0CCA77C6BEE5B0063F1AA7F11F4FD435DD6BC3FA4C36929D3CCFE75CCFB7E5503B67B1C6EEBD72291D4E115464A376E5E5FF00011C6F8A35AF09CFE1CBA874FD05A0BE655549DAC913041193BBB719FCEA4B7D6BC251F86E1B77F0EB35F2DAAA19FEC487326CC16DD9CF5E735E95F10AD27BCF06EA505AC4F2DC3C6AAAB1A92C7E607815CE5DEABA868FE12D334D5F0F6A575349A6A23341192237D9B4AB71C114194F0F2836B4B5BB1A5F071B1E02B227FBF27FE86D5CDFC44F1E45F66BAD2FC3CDE66EF96EAEE25CAA29E3008EA4F4CFE5EDD5FC38D1E7B4F01DAD86A96DB1DD64F3217FEEBB1383F81AC1F883E1A8B4AF030D3B40D32593CD9E30FE502EC79FBCDDCF381ED9A11ACD545423C9D16BDFD119BADE8F3F81EDF49F10786E332C76F0791748C31BD5B90CC07B9FC30B5D1FC3FB3D42F65FF848B53D61AEE5BA4C25B5BB7EE235CF423FBC3F4E7AD33C3967E2CD42E2D64F101B4B5D2E342AD62AA18CC0A951BFAFA838CF6E951AF82354D0B594B9F08EA296F633CA0DC594EBB900CF257D78CF1C1F7AAB934E9B569A8BB76DB5EF62CFC52D7353D3AC56D347B495E6B886577B95FBB0A20058FB1C1EBEFC73599E0DF08E99AE7C38D32DEF2374F3246B890C6DF33B82EA093CF6AEDBC5B0BCBE18D55114976B4942803272508ACCF86F14D69E00D311E27F3D236FDDBFCAD9DEDC1F4A46D2A77AF796CE2FF43CE3E24781348F0CE890DE69CD73E73DC2C47CC9030C1563E9EA057A5F84FC1F61E1B5BB3A59981BA550FE6306C6338C71FED1AE1BC5D7BAEF8D6D2D74C8BC337D623ED02433CE48500023BA8F5CFE15EC38C0C0E9489C3D28FB4938AB2D0F21F87DA1B683F12AEAD6599E7BA6D37CC95CF42ECEA4E3F4E6ABB6BED97FF8AFECFEF1FBDA38FF00E26BAAB58275F8C17B3B4320B73A6051291F296DC9C67D6B92F0347E20B2D2274B7F09C57CAD70EC25B895216EDC6D7E71EF48C1C39172ABEEFBFE85BD1F5C32EAF6310F1BDB5D6F990792BA5EC2FF0030F943638CF4CD76BF131F6780F57FFAE3B7F3205709AEAEB775E20F0CB5F786974D892FE36F32DD965CFCCB9DDB3EE81EA6BA9F8B116A579A041A66956B2CCD7B3AC723A8F9635072377A0271CFB1A0D69732A538B5E9BF5F5BB387D3F5BF0B2784A185FC3D24BA87D976F9FF0062423CCDB8DDBBEBDEA1F066B5E1AB5F0CDA41AA680F79789BF74CB64B2039624724F6181F857AFDCD97D97C2B358DAA6E31D998635EE70981C7AD71BE1BD42FFC31E02D2639B42BFBAB82F2AC91C311DF1FEF1882463B834184B0CE0D36D5927D3D077C12647D2F5768936466F98AAE318181818ED57A7F86B6335C4B29D5B5A56762C42DD00067D3E5A4F847A7DF5AE97A84DA8DA4B66F7576F324528C30520751F5CD77F41DB429274929743E7CB8D3F42D3BC47AC5B5E5D6B71C569B15AE61B80EEDBB1F7BE4E00CF73DABAFD1BC11A0EAF6C2E34DF116AF3C27A94BA5E3D88DB907D8D5BF0DD933FC4EF14B5CDBB7D9E48954164F91F85C8F435A29E02D3ED3C456DAAE953DC69E51B32DBDB9C4720F4C7619C6474C761D6ADBB1CD470ED6AD5D5DFA9D29D32093443A64C5E4B76B7FB3392DF332EDDA79F5C77AF1DB8F07E8DA178C8D96B71CC749BB5CDA5C7985423F7563F8F5FA7A9AF5AF12EB0FA1E966EA2B0B8BE2182F9707DEE7807F3C0E33D6B83D5E2F1678E6D0D9CBA45B693A7B3021EECEF9401DC0EA0FE03EB4AE6D8A51764B56B6D2E62F8DBC35E1DD2FC8D3B46B7B8B8D6EED87969E716DA3FBCDFE7DFA0AF56F08E98746F0ED969ECFBCC29866F562493F8649AF3AD3FC15E24F066A0F79A09B1D5B72856170BB24C77DA49E3FEFAFC2BB5F0AF88354D56EEE2DF54F0FDCE986150C6477DCAE4F65E067A1E99A08C3C7966DB8D9BF2D0E03C4FE1F6D2FC73A3EA57372F3CFA86AD95E721230E9B57F2FE95D46BDA9EA9E1BF185BDDDC3CD7BA15FED83CB54C9B693B6001939EBEA79F4149F12E19E5F117839E08649152FB7485572146E8F93E95D67886FE6D37489AEEDAC65BE963DB8822FBEF96038E0F4CE7F0A0A8524A53B74699C5F8A3FE4B1785FDE093FF4192BD2874AF27D31F55F127C46D2B559F46BCD3AD2CA1756372A4124AB018C81DD870335EB22866B86D5CA5DDFE88514514549D614514500141A283401E69F1EBFE44FB5FF00AFD5FF00D01EBC0ABDF7E3D7FC89F6BFF5FABFFA03D78156D1D8F99CDBF8DF20A28A299E585145140057BF7C04FF009142EBFEBF1FFF00404AF01AF7EF809FF2285D7FD7E3FF00E8095123D4CA3F8FF23D285140A2B33E98290D2D21A005145028A00314628A2800A28A2800A28A2800C518A28A0028A28A00314628A2800C5145140051451400628A28A0028A28A0028A28A0028C0A28A003029368F4A5A280B09B4518A5A280B0628A28A00314628A2800C518A28A00314628A2800C5262968A004C7B52D1450014514500145145001451450079A7C7AFF913ED7FEBF57FF407AF02AF7DF8F5FF00227DAFFD7EAFFE80F5E055B4763E6736FE3FC828A28A6794145145030AF7EF809FF2285D7FD7E3FF00E8095E035EFDF013FE450BAFFAFC7FFD012A267A994FF1FE47A50A28145667D30521A5A43400A28A0514005145140051451400514514005145140051451400571BF143C572F85BC1BA8EA9A71B492FAD5A2DB0CED90774A8A720107EEB1FC715D9579A7C7DB5857E15F886648A259A436E59F00163E7C4393F4005006B78FBC5CFE1EB0D2A6D3E4B19E5BAD4ED6CA5F364E15257D85860F04641E78EB5D35A6AFA7DEACEF677B6D3A5BC86299A2995846E3195620F0464706BCF3E2CF9FF00D9BE1DDF650C6BFF00090E9BCAC818FF00C7C2F18C57A4B410B23C6F0A18DBF84A8C1FC280384F891E3DB8F0D7836FF56D3AD15EE2DA489505C4913238699109C2C9BB90C71F866ADDD7C47D26C3C4D6FA46AB9B0F3AD4DD2DD5C4F08840F31630A4873C9675C7F8026BC4BC6DE2CB9D73E13DF5ADE1B8B8BD9D0DD978A281218E28F52581377CA1B2DB491827A7A66BBCD0F54B3D4FE2EE85756E679E59F49BDB49E29CC6AD69245346648D95146583003AF4E46411401E93E34F11DBF85741B8D56ED249B662386DE21992E2563848D07766381FFEAAE4DBE264361ADF886D35B582C16C858ADA5BCAE44F24B73196F2DB696190C08CAE40009E83356FE24B5D5859DEEB7A6E99F6BBFD334F9E6B7B8BA982DBDA908C4BAA725A4C0C7DDE9C6E504E793B3D3EFADBC3F75E3D9ED16EFC4773A4FDB05ECD691620FDCEE0910130DAA0719DA588EA4F4A00F4EF0B789EC3C44B789682686F2CA5F22F2CEE1364D6F263386192082390CA4A91D09AC0F0BF8F65D5358D6A29F4CB9FECC8356FECBB1B9B5B59A6F34A8C48F21552AA81C11BF38F5C63267F077DA755F87563ABA3431788757D260B89EF21B750CF334036B15C80C573C0240E3B0AE0BC1DA77D8FC4FADE890DAEA1FD9FA25DDA5BDAACBAB3406D966B75695BE46C4923BB16C1E0963823A5007A5F87BC4575AA78CFC57A3CB0C296DA43DAAC2EB9DEFE6C21DB776E0F4C57417773159DACD737532436F0A192491DB0A8A064927D00AF2FF0EE8563A9FC43F895657A2EDEDD9B4F42A2F6642C3ECC0E0B2B827F3F6AE93E2388E0F0E4B7576FA7C5A6E9E9F6979750124EB941F2E620CA2439C63731F9B1C67140185A7FC4CBF50971A8E83746DF57BE10E836F00517375000374AE8EC0A8C65F2400148CE3209F53AF999753B083E234571E228F50D716FB42B76B813C4B2CB0DF4C5A482DA3DAA3C925558009B72C54924F35ECDF0BF49D7748F0D08FC497B2DC5CCB2F9B1C134DE7BDA2103F72663CC98209C9E467196C648041E26F185E69BE234D234EB08EF64BCB5B86B39D18BAC7750A6F68A703EEA90530739C9C63A553D17C7B7DAF5EF87FEC1A50B6B59EC4EA1AC7DB9CC6DA7C6C088C67BB3157232065573C541A2787F4ED1AD2FA64BB9B516BCB896FA6B8BBD37CD91E5739386DA005C0000038C572D6E6DFC5BA33EB7A8412C3E12BA854269967A233DDDCA123E6792353B738C6D427E427E604F001EE30CD1DC431CB0C8B244EA195D1B2A41E4106BCD35AF88F359F8E9EDA0B4497C29A5620D675408585B5C38CA2E41FBA981BCE0EDDE338C66BA6D52CA54F0FD9D8684E748D30A08CB5ADB1F3E28F030B1263119C67E66076E3EEE4E5798D3E6F0F69F6975E0DD3B54D4AD1ED6D879D1259AE63129C0676788A9762D9F9892C4E706803D32DE78EE2149617578DC0656539041EE2A5AF37F01F862DFC3FE21961D2A5F135BE9915BA9582E9D3EC4EE7A98D3EF23772A02AF3C0E2BD1E80168A28A0028A28A0028A28A0028A28A0028A28A0028A28A0028A28A0028A28A00F34F8F5FF227DAFF00D7EAFF00E80F5E055EFBF1EBFE44FB5FFAFD5FFD01EBC0AB68EC7CCE6DFC7F90514514CF2828A28A01857BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095133D5CA7F8FF0023D285140A2B33E98290D2D21A005145028A0028A28A0028A2B3F5ED5ED342D1AFB54D45D92CECE169E66552C4228C9200EB401A14578E7FC3487C39FF00A095E7FE0149FE147FC3487C39FF00A095E7FE0149FE1401EC79A335E39FF0D21F0E7FE82579FF0080527F851FF0D21F0E7FE82579FF0080527F85007B1E68CD78E7FC3487C39FFA095E7FE0149FE147FC3487C39FFA095E7FE0149FE1401EC75CA7C4FF000CCFE30F05DEE876B731DACB72F0912C8A485093248781DF0871EF8AE7BC27F1BBC15E2BF105A68BA2DEDCCBA85D161123DABA03B54B1E48F4535D9F8BBC47A7F84FC3B77ADEB523C5A7DAECF35D50B91B9D5070393CB0A00C5F18F846F35FB3D3A24D5E656B4D46DAF899A38D8111481F1F2AA9C9C7AE2BA2D723BE9B4CB88B4A96286F655D91CB2825622782F81D481920719200C8CE47967FC3487C39FF00A08DEFFE0149FE147FC3487C39FF00A08DEFFE0149FE1401A1AAFC24B55F01EA9E1FD12E7CB9AFBEC91FDA6E46E2914122305E3AF491BDDA463DF8D387E1DDAD8FC535F16E9EE9009A09C5DC45998CB2C8230180E8A311F3EA6B9CFF008691F873FF00412BDFFC0293FC2B6FC1DF19FC17E32D7A1D1B43BDB996FE65664492D9D010AA58F247A03401D878B34D9358F0AEB1A640E892DED9CD6E8ED9C2B3A1504E3B735CBB78017FE10A3A3096DFED1FD9BF62131B58B1BBCAD9BB3B37633CF5CD741E33F14E95E0DD065D675D9648AC627546648CB9CB1C0E0579E7FC347FC39FFA095EFF00E0149FE1401E95E11D1DF42F0B68BA434C253A7D94368640BB4398D02EEC76CE3358DA77806C2D3C75ACF8A6E279AEEEAF9E278A193FD5DA948963CA2E705885FBC4640381D4E73BC0FF0018BC1FE35D6C691A05E5C4B7C636942496EF182ABD792315D178EBC63A47823431AB6BF2C915999561DD1C65CEE6048E07D0D0041E1FF0ECBA678BBC51ACC97292C5AC496AF1C4A8418BCA8446727BE48CFB568788A1D4EE6036DA6358429229F326B98CCDB7D844301BEA5BF035E69FF0D1FF000E7FE82579FF0080527F85743E05F8BBE11F1C6B6DA5F87AEEE27BD585A6DB25BBA0DA0807923DC5005C83E1D687FF0008EEA9A55F89EFA4D524F3EFAF277CCF34DC6D9370C6D2B81B4280171C0EB9B3E0BD1BC45A319ECF59F1026B3A7A285B5925B5D97607FD349036D7C0C73B727A9F7EBB03D0525006378A2CB51BCD16E61D16F22B5BF642B13CD17991124630E3AE3DC1C8F7E861F046863C31E10D2344F385C7D82D9203284DBBCA8C671CE2A0F1FF008DB46F0269106A5E229A58AD269C5BAB471973BCAB30181ECA6B82FF00868DF873FF00411BCFFC0393FC2803D53568EEE7D2EEA2D36E12DAF5A26582678F7AC6F8E18AF700E38AE33C31E01BBD33429F4DD435A17DF6C6692FE5FB1206BC66E18C85CBEEC8E3D00000000C573DFF000D1BF0E7FE82379FF80727F852FF00C3477C3AFF00A08DE7FE01C9FE1401DC7C3EF0DEA1E16B0BDD3AF7569B53B14B9274E6B83BA682DCAAE2366FE2C36EC7B63E83AAAE17C03F153C2FE3DD42E6CBC37753CF716F179CE2481A3017207523D4D5FF001FF8FB41F01595ADD789279A18AE6431C66289A42580C9E9D38A00EB28AF1BFF00868FF873FF00413BDFFC0393FC293FE1A3FE1CFF00D04EF7FF0000E4FF000A00F65A2BC6BFE1A3FE1CFF00D04EF7FF0000E4FF000A3FE1A3FE1CFF00D04EF7FF0000E4FF000A00F65A2BC6BFE1A3FE1CFF00D04EF7FF0000E4FF000A5FF868FF00873FF413BDFF00C0393FC2803D928AE3FE1E7C46F0EFC405BF6F0CDCCD38B12826F32168F1BF76DC67AFDD35D85001451450014514500145145001451450079A7C7AFF00913ED7FEBF57FF00407AF02AF7DF8F5FF227DAFF00D7EAFF00E80F5E055B4763E6736FE3FC828A28A6794145145030AF7EF809FF002285D7FD7E3FFE8095E035EFDF013FE450BAFF00AFC7FF00D012A267A994FF001FE47A50A28145667D30521A5A43400A28A051400514514005715F1ABFE49378C3FEC173FF00E806BB5AE2BE357FC926F187FD82E7FF00D00D007E7A786746BBF116BD63A469AA8D7979288620EDB54B1E993DABD5BFE19ABE21FF00CFAE9FFF00818B5C7FC0DFF92BDE12FF00B08C5FCEBF41BC4FAE5A786F40BED6351320B3B388CD2F96BB9B68F41DE803E29FF866AF887FF3EBA7FF00E062D1FF000CD5F10FFE7D74FF00FC0C5AF7AFF869AF87FF00DFD5BFF013FF00B2A3FE1A6BE1FF00F7F56FFC04FF00ECA803C17FE19ABE21FF00CFAE9FFF00818B591E2DF819E34F0A787AF35AD620B24B0B450D298EE55D802C1781DF922BEA8F0A7C7BF06F89FC4165A3696DA89BCBC7F2E2F32DF6AE704F273C74ABDFB477FC913F14FF00D708FF00F46A5007C8DFB33FFC971F0C7FBF37FE8892BECBF8DBE1BD47C5DF0C35AD0F4658DAFEEBC9F2C48FB54ED991CE4FD14D7C69FB337FC971F0C7FBF37FE8892BF412803E16FF00866AF887FF003E9A6FFE062D1FF0CD5F10FF00E7D34EFF00C0C5AFB33C6FE27B0F07786EEB5CD5FCE1636C53CC3126F61B9D5071F5615C67823E37F847C67E23B6D0F457BF6BE9C394F36DF62E154B1E73E80D007C97E32F823E31F07F876E75BD6E0B24B0B72824315C876F99828C0EFC9154BE05789F4DF06FC49D3F5BD69A55B1863995CC51EF605A3651C7D48AFADFF6A7FF009221AFFF00BD6DFF00A511D7C4DE07F0AEA3E34F115BE89A37926F67576412BEC5C2A963CFD01A00FA93E22F8FB42F8CFE169FC19E0992E26D6EE9D268D2E6230A158CEF6CB1E0702BC97FE19AFE217FCFA69DFF00818BFE15D3F80BE1EEB5F05FC45178D3C682D868D6B1BC327D8E5F3A4DD20D8B85C0EE477AF52FF869AF87FF00DFD5BFF013FF00B2A00E43F67DF837E2DF03FC448F58D7A0B34B25B5962262B80E773631C015EA1FB43783B56F1CFC3F1A4E80913DE0BC8E7C4B2041B54303C9FA8AE7BFE1A67E1FFF007B56FF00C04FFECABA6F87DF197C29E3CD75B48D05EFBED82169F13DBEC52AA403CE4F3C8A00F8F7C75F06FC5BE07D0BFB5F5F82D23B2F3561DD15C073B9B38E07D2BAEFD8DFFE4ADCDFF60C9BFF00438EBDBFF6C0FF00923EDFF5FF0007FECD5F35FECF1E34D2BC07E3D7D5B5D338B46B3920FDCA6F6DCCC8471F81A00FD03AE5FE2078DF46F0168F06A7E219268ED26B816CA628CC87795661C0F6435E73FF000D31F0FF00FBFAAFFE02FF00F655E55FB46FC60F0BF8FF00C1165A5E80D7A6EA1D412E5BCE8362ED11C8A79CFAB8A007FED25F167C2FE3EF05E9FA678766BA92E61BF5B87F360283608E45EA7BE58578C7C3FF00026B5E3ED56E34EF0EC70C97304267712CA106D0C0753EEC2B931D6BE87FD8AFFE4A1EB1FF0060C6FF00D1B150060FFC335FC43FF9F4D3BFF03168FF00866BF887FF003EBA77FE062D7D7DF11BC75A3FC3FD220D4F5FFB47D9669C5BAF911EF6DC559BA6476535E7BFF0D33F0FB38DDAB7FE027FF6540189FB34FC28F13FC3FF00136AD7DE238AD6382E2CC431F93389096DE1BA0F61553F6DAFF9153C39FF005FB27FE8BAF53F875F167C35F10752BAB2F0F1BC33DAC5E73F9F0F9636E40E0E4F735E59FB6D7FC8A9E1CFFAFD93FF0045D007CDDF0EBE1EEBBF106EEF6DBC391DBBCB691AC9209A5118C1381826BBBFF866AF889FF3EBA77FE062D75BFB10FF00C8C9E27FFAF48BFF004335F457C49F88FA17C3DB6B29BC42D7212F1D922F222DE72A0139E463A8A00F923FE19AFE227FCFAE9DFF00818B47FC335FC44FF9F5D3BFF0316BDEBFE1A63E1F7F7B55FF00C05FFECA8FF8698F87DFDED57FF017FF00B2A00F05FF00866BF889FF003EBA77FE062D717F11FE1AF88BE1E0D3FF00E1258ADE3FB7F99E4F9330933B36EECE3A7DF5AFB8FE1AFC4FF0F7C4493505F0E7DACFD844666F3E2D9F7F7631C9CFDC35E23FB72FFABF057D6F7FF6850049FB0D7FC7BF8CFF00DFB3FE5357D4B5F2D7EC35FF001EDE33FF007ECFF94D5F52D001451450014514500145145001451450079A7C7AFF00913ED7FEBF57FF00407AF02AF7DF8F5FF227DAFF00D7EAFF00E80F5E055B4763E6736FE3FC828A28A679414514500C2BDFBE027FC8A175FF005F8FFF00A025780D7BF7C04FF9142EBFEBF1FF00F404A899EAE53FC7F91E9428A051599F4C14869690D0028A28145001451450015C57C6AFF924DE30FF00B05CFF00FA01AED6B8AF8D5FF249BC61FF0060B9FF00F403401F0D7C0DFF0092BDE12FFB08C5FCEBEDCF8F5FF2477C59FF005E2FFCC57C47F037FE4AF784BFEC2317F3AFB73E3D7FC91DF167FD78BFF31401F9D345145007A3FECEFF00F259BC2DFF005F27FF00406AFB0BF68FFF009227E29FFAE11FFE8D4AF8F7F677FF0092CDE16FFAF93FFA0357D85FB47FFC913F14FF00D708FF00F46A5007C8DFB337FC971F0C7FBF37FE8892BF412BF3EFF666FF0092E3E18FF7E6FF00D11257E8250070DF1B7C2FA878CFE1A6AFA0E8E6017D77E4F966672A9F2CC8E72403D94D7CEDE09F86FAD7C15F11DB78E3C60F65268D601E39458B9965065531AE14851F79C679E99AFB08F5AF23FDAA3FE489EBBFF5D2DBFF0047A500796FC6CF8E9E13F1A7C37D5741D223D4D6FAE4C453CE81553E4951CE4863D94F6AF37FD94BFE4B6E8BFF005C6E7FF44BD7911EA6BBEF817E2BD37C15F1234ED735AF3FEC504732BF929BDBE68D9460647722803ED1F8E9E10D4BC6DF0F2EF44D11A05BC96689D4CEE5570AE09E403E95F20FC40F827E29F037879F59D6A4D39ED12458CFD9E72ED96381C1515F58F80BE36F84FC71E228B45D13FB445EC88D22F9F0045C28C9E771AB3F1EBC1FA9F8E3E1ECFA3687F67FB63CF14A3CF7D8B85393CE0D007C37F0FBC17A9F8F3C44BA3688D6EB7663697370E5576AE33C807D6BE98F801F05BC4FE01F1EB6B1AE4BA6B5A1B49211F67999DB7315C705471C543F003E0BF8ABC0BE3F5D675DFB00B316D245FB89CBB65B18E303D2BDCBE20F8D34AF01E80358D705C1B4332C1FB88F7B6E6048E323FBA68039EF8FDE0BD53C79F0FDB46D11ADC5E7DAA29879EE5176AE73C8079E6BE68FF8661F1F7F7F45FF00C0A6FF00E22BDC3FE1A6BC03FDDD63FF000107FF00155D37C3DF8C9E16F1EEBCDA4684B7E2ED6169CFDA2008BB54807904FF00785007CD3FF0CC3E3EFEFE8BFF00814DFF00C451FF000CC3E3EFEFE8BFF814DFFC457DC145007C3FFF000CC3E3EFEFE8BFF814DFFC45757F0EBC3B7DFB3DEA973E22F1F185B4EBF87FB3E11A7B79CFE6960FC8217036A3739AFA2BE23F8F347F87BA35BEA9AFFDA3ECB34E2DD7C840EDB8AB374C8ECA6BC37E25788ACBF682D26D7C39E00F33FB42C671A84DF6F1E42F9414C7C1E727322F1F5A005F883E24B2FDA0F4683C33E0313A6A76338D4653A8A8863F2955A3382A5BE6DD2AF18F5AF3DFF8662F1E7FCF4D17FF00029BFF0088AF53FD9D3E10F89BE1EF8BF50D4FC41F61FB34F62D6E82DE62EDB8C88DC820718535EB7F11BC7DA37C3ED2AD750F100BA36F7137D9D0DBC5BCEEDA5B9E471853401E59FB387C23F11FC3BF12EA97DE207B030DCDA082316D3173BB783CE5476159FF00B6D7FC8A9E1CFF00AFD93FF45D7A87C38F8B3E1BF885A8DD59F87FEDBE75AC62693CF8820DA582F1C9EE6BCBFF006DBFF9153C3BFF005FB27FE8BA00E57F621FF9193C4FFF005E917FE866B7FF006E0FF9037853FEBE2E3FF414AC0FD887FE464F13FF00D7A45FFA19ADFF00DB83FE40DE14FF00AF8B8FFD052803E49A28A2803EA4FD873FE3EFC63FEE5A7FED6A9FF6E5FB9E0AFADEFF00ED0A83F61CFF008FCF18FF00B969FCE6A9FF006E5FF57E0AFADEFF00ED0A0093F61AFF008F6F19FF00BF67FCA6AFA96BE5AFD86BFE3DBC67FEFD9FF29ABEA5A0028A28A0028A28A0028A28A0028A28A00F34F8F5FF00227DAFFD7EAFFE80F5E055EFBF1EBFE44FB5FF00AFD5FF00D01EBC0AB68EC7CCE6DFC6F90514514CF2C28A28A002BDFBE027FC8A175FF5F8FF00FA025780D7BF7C04FF009142EBFEBF1FFF00404A899EA653FC7F91E9428A051599F4C14869690D0028A28145001451450015C57C6AFF00924DE30FFB05CFFF00A01AED6B8AF8D5FF00249BC61FF60B9FFF00403401F0D7C0DFF92BDE12FF00B08C5FCEBEF1F89FA15DF89BC01AEE8BA71885DDEDB34511958AA64E3A900E3F2AFCFCF86FADDAF86BC77A16B57C92BDAD95DA4D2AC20172A0F3804819FC457D63FF000D4BE06FF9F0F10FFE0345FF00C76803C77FE1983C79FF003F1A0FFE053FFF001BA3FE1983C79FF3F1A0FF00E053FF00F1BAF61FF86A4F02FF00CF87883FF01A2FFE3B47FC3527817FE7C3C41FF80D17FF001DA00E2BE13FC00F187853E21E89ADEA53692F6767317944170CCD8DA4700A0CF5F5AF66FDA3BFE489F8A7FEB847FF00A352B8FF00F86A5F02FF00CF87883FF01A2FFE3B5C87C5AFDA07C25E2FF879ACE83A65A6B51DDDEC4A91BCF046A808756E48909E80F6A00F25FD99BFE4B8F863FDF9BFF44495FA095F9F7FB33FFC971F0C7FBF37FE8892BEE5F1D78A6CBC17E15BDD7F548AE25B3B408644B750CE773AA0C0240EAC3BD001E3BF1558782BC3179AF6AE93BD95A9412081433FCCEA830091DD877AF0CF19FC49D17E36787AE7C0DE0E8AF63D675164789AFA211C2044C246DCCA588F950E38EB8A3C57F14744F8D9A05CF80BC2906A56BACEA850C32EA112C70AF94C266DCC8CE47CB1B630A79C5727E12F86BACFC11D761F1DF8AE7B1BAD22C0347247A7C8CF3932A98D76ABAA03F338CFCC38CF5A00E0BC75F023C57E0AF0CDDEBBAC4FA53D95B6C0E2DE7667F99D5060141DD85715E02F08EA1E37F135B685A3BC09793ABB2B4EC5500552C724027A0F4AFA6BC59F13745F8D5A1CFE03F09DBEA16DABEA654C32EA11AC702F94C256DC519C8F96320614F24572FE12F86FAD7C10D761F1D78B26B0BAD26C43C5247A74AD24E4CAA635DAAEA80F2C33F30E334007827E1EEB1F037C41178DBC65259CDA35AA3C2E34F91A594B48362E159546327D6BD23FE1A87C05FF003EFAE7FE02A7FF001CAE7FC5FF0010F49F8E3A14BE07F095BDF5AEAF76CB3C726A48B1C216321DB2519DB381C7CB5E2FF113E0678A3C05E1A935CD62EB4996CD2448D96DA5919C16381C32018FC6803E88FF0086A1F017FCF0D73FF0153FF8BAF36F8F9F1A7C31E3EF032691A145A92DDADE473FFA4C2A8BB54303C863EB5E29F0E7C13A978FFC46345D166B48AECC4D36EBA7654DAB8CF2AAC7BFA5753F11FE09F897E1F680359D66EF4A9ED0CCB062D6591983302470C806383DE803CB49AF76FD8E3FE4AE4FFF0060B9BFF438EBCCFE1CF82352F1FF0088FF00B1B4596D22BAF29A6DD74ECA9B5719E5558E79F4AF71F04783351FD9FF005A3E30F1A4D6977A5C913586CD2DDA5956472194957541B70879CFA5007D1DF11FC73A57C3FD063D5F5C4B96B579D6DC0B740CDB981238247653581F0DFE31F86FE20EB93E95A145A8A5CC36ED72C6E61545D81954F218F3975AF2DF1C78BAC3F684D253C23E098EE2D752B69975267D4D56388C480A100A173BB32AF18F5E6B13C15E17BFFD9E35597C57E36782EF4CBC80E9689A5B1964595D96404870836ED85F9CE738E2803B4FDB5BFE498E93FF006188FF00F44CD5E0DFB3978FF48F879E2CD4751D763BB920B8B236E9F668C3B06F311B9048E30A6BD6FC71E27B1FDA2B488BC2BE0859ED352B29C6A723EAAA2288C4AAD1900A173BB74ABC6318CF3EBE2FF12FE0E7887E1CE8F6DA9EB975A5CD05C4E2DD05A4AEEDB8AB37219178C29A00FA47FE1A87C09FF3EFAEFF00E0227FF1CAE5FE207886CFF686D2EDFC3FE0313437DA74DF6F98EA6042863DA5382A5B272E3B57CFBF0C7E1FEABF11759B9D33439ACA1B882DCDCB35DBB22ED0CABC1553CE5876AF6EF037876EFF00676D42E3C45E3A782EEC7518BEC108D298CB22C8487CB090200B843C8279C71401D7FECE3F08FC45F0EFC49AADFEBF269EF05D5A082316D2B39DDBC3739518181543F6DBFF00914FC37FF5FB27FE8BAF4AF863F177C3DF11752BBB1D0ADF528A6B5884CE6EE34552BB82F055DB9C9AF35FDB6FFE454F0D7FD7EC9FFA2E80395FD887FE465F13FF00D7A45FFA19AF56FDA47E1AEB7F1234FD0E0D024B347B29A5797ED32141860A06300FA1AF9D3F674F893A37C36D5B58BBD760BF9A3BC812241691AB904313CEE65AF76FF86A7F02FF00D03FC43FF80D17FF001DA00F1FFF00865DF1D7FCFCE85FF814FF00FC6E8FF865DF1DFF00CFCE85FF00814FFF00C6EBD87FE1A9BC0DFF003E3E21FF00C068BFF8ED1FF0D4DE06FF009F1F10FF00E0345FFC76802C7ECD9F0B35EF86D71AFBF8824B1717EB008BECB2B3E367999CE5463EF8AE27F6E5FB9E0AFADEFF00ED0AEBBFE1A9BC0DFF003E3E21FF00C068BFF8ED78AFED1BF14B42F894BE1F5D060D422FECFF00B4197ED71221264F2B6EDDACD9FB873D3B5007A07EC35FF1EDE33FF7ECFF0094D5F52D7CB5FB0D7FC7BF8CFF00DFB3FE5357D4B40051451400514514005145140051451401E69F1EBFE44FB5FF00AFD5FF00D01EBC0ABDF7E3D7FC89F6BFF5FABFFA03D78156D1D8F99CDBF8DF20A28A299E5851451409857BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095133D5CA7F8FF0023D285140A2B33E98290D2D21A005145028A0028A28A002B9CF887A35CF88BC11AF68D62D125D5FD9C96F1BCC4845665201620138FA035D1D64F8AB5B83C39E1DD4759BB8A596DEC606B8912200BB2A8C903240CFE3401F217FC32B78D7FE829E1CFFBFF003FFF0019A3FE1957C6BFF415F0E7FDFF009FFF008CD7A7FF00C355F843FE80FAF7FDFB87FF008E56BF843F68CF0D78A7C4DA768963A56B31DC5F4A22479A38C2293DCE1C9C7E1401E33FF0CABE35FF00A0AF873FEFFCFF00FC668FF8655F1AFF00D057C39FF7FE7FFE335F5C78C7C476BE14F0C6A1AE5F4534B6B651F9B224382E4640E3240EFEB5E2DFF0D57E10FF00A036BDFF007C43FF00C72803CC3FE1957C6BFF00415F0E7FDFF9FF00F8CD1FF0CADE35FF00A0AF873FEFFCFF00FC66BDA7C19FB44F867C59E26D3F43B1D335786E6F64F2D1E65882038279C393DBD2BD33C6FE27B4F08785AFF5DD4229A6B6B340EF1C201760582F19207561DE803E7AF843FB3FF8A7C17F11B46F106A7A868935959B48644B79A5321DD13A0C068C0EAC3BD7A87ED3DFF2437C4DFEEDBFFE94C5597E04FDA0BC3BE34F1658681A7E97AB4373785C2493A461176A1739C393D14F6AECFE30785AEFC6BF0EF56F0FE9B3410DD5E0882493921176CA8E73804F453DA803E19F833E2DB1F047C46D2F5FD562B996CED44BBD2D9559CEE89D0603103AB0EFD2BE81F13FC4DD23E38E8B3F80FC276BA8D9EB1A8957866D45523814447CD6DC519DBA21C614F38E9D6BCA7E20FC00F11F82BC297BAFEA3A9691716968503A5BBC85FE79150601403AB0EF507ECA7FF25BB42FFAE773FF00A21E803D67E0C7C05F14781FE23697AFEAF7FA34D696AB3074B69A5690EF899060346A3AB0EF5DDFED59FF00244F57FF00AED6FF00FA392BD888CD78F7ED5BFF00245358FF00AED6FF00FA392803E4BF81FE31D3FC07F106D35DD5E1BA9AD228658D96D9559F2C840C06603AFBD7BDF8B7E20E97F1E34593C0FE1082FAD355B865BA59753448E00B11DCC098D9DB3E9F2D7CDDF0E3C197BE3DF1443A1E99716D6F732A3C81EE0B05C28C9FBA09AFA6BE08FC0BF107C3FF001EC3ADEA9A8E95716CB6F2C452D9E42F96181F7900FD680395F07781354F805ACFFC26BE2F9ECEF34A48DACCC5A5BB4936E931838915171F29FE2AABF1E7E37F86FE20F81068BA358EAF05D0BA8E7DF77144A9B541046564639E7D2BD5BF6BEFF923D27FD7F41FD6BE1C1D2803DB7F63FF00F92BA3FEC1F3FF00ECB5EE1FB627FC9274FF00B0943FFA0BD7CC7F033C7361F0FBC7235AD52DAE6E6DBECD241B2D8297CB6307E620638F5AF71F14F8DAC3F684D297C1BE1582E74FD491C5F19B530AB1148C104650B1CFCE3B76A00F18F803E3CD37E1DF8DE7D6359B7BC9ED64B292DB6DA2AB3866746070CCA31F21EF5ED5E33F1558FED17A545E11F04C57361A8D9CC35579356558E231206888063321DDBA65382B8C03CF407C7FE277C13D7BE1E78763D6756D474AB8B67B85B6096CF217DCCAC47DE4031853DEBA7FD8BBFE4AA6A7FF0060797FF47C1401EA7FB3DFC16F10FC39F17DF6ADADDE6957104F64D6AAB69248CC18C88D93B91463087BD45FB6B7FC93CD1BFEC283FF0045495EA7F14BE20E9BF0E342B6D5757B5BCB9827B916CAB6A14B062ACDFC4C0630A6BE5DFDA0BE33687F123C2D61A6E8F63A95B4B6F782E19AE950023632E06D63CE5A80396FD9EFE20697F0EBC597FA9EB76F7B3DBDC58B5AAADA22B306322364EE6518F94F7AEB3F683F8C3A07C48F0BE9FA6E8567AAC13DB5E0B87377146AA57632E06C76E72C3B579AFC2BF87DA97C47D6AEB4BD22EACEDA6B7B7372CD74CC14A8655E36A939CB0ADBF8A3F0675BF86FA3DAEA5ACDF69B7305CCFF006755B5790B06DA5B27720E30A6803BFF00D89FFE475F10FF00D83C7FE8C5AF68FDA23E1B6ADF12744D22CB44B9B1B796D2E1A67376EEAA415C606D56AF17FD89FF00E475F10FFD83C7FE8C5AFA2BE2AFC49D2FE1B69D6379AC5ADEDCC777298516D5549040CF3B985007CCFF00F0CABE36FF00A0B7873FEFFCFF00FC6A8FF8654F1AFF00D05BC39FF7FE7FFE355F43FC29F8C1A2FC4ABFBEB4D1EC751B57B38C4AE6E9500209C71B58D7A5D007C5BFF0CA9E35FF00A0B7873FEFFCFF00FC6A8FF8654F1AFF00D05BC39FF7FE7FFE355F69668CD007C5BFF0CA9E35FF00A0B7873FEFFCFF00FC6A8FF8655F1B7FD05BC39FF7FE7FFE355F69579E7C58F8ADA4FC33FECBFED8B2BEBAFED03288FECA10EDD9B339DCC3FBE28039BFD9C7E166B5F0CD3C4035DB9D36E3FB40DB98BEC6EEC57CBF3321B722FF007C6319EF5ECF5E7DF09FE2A691F133FB57FB1ECAFED7FB3BCAF33ED4A837799BF1B76B1FEE1FCC57A0D00145145001451450014514500145607893C49168735A43F61BED42EAE83B476F65187936201BDCE481B46E51D739600039AA3E1DF1AD96BF7B0C36D67A95B417713CF6375730848EF2352A19A3F98B0FBC0E1D5490720100D0060FC7AFF913ED7FEBF57FF407AF02AF7DF8F3FF002275AFFD7E2FFE80F5E055B4763E6736FE37C828A28A67961451450015EFDF013FE450BAFF00AFC7FF00D012BC06BDFBE027FC8A175FF5F8FF00FA02544CF5329FE3FC8F4A145028ACCFA60A434B48680145140A2800A28A2800AE2BE33FFC927F17FF00D82E7FFD00D76B5C5FC67FF924FE2FFF00B05CFF00FA01A00FCE0AE97E1BEBF0F85BC73A36B77714B341653895E38B1B980EC33C573745007D3FF12FF689F0FF008AFC09ACE8567A46A90DC5FC1E52493797B54E41C9C313DABE5FC56F781BC37378BBC57A6E856B3C76F35EC9E5A4B202554E09E71CF6AF71FF008652D7FF00E862D2FF00EFDC9FE1401E6BFB3D7FC966F0AFFD7D1FFD01ABEE0F8ADE17B9F18FC3FD6341B196182E6F63544926CEC5C3AB73804FF0D7CE7A67C18D4FE11DF43E3BD5354B3D42C74461712DB5BABAC9203F2614B0C7F177F4AEA7FE1ABF40FF00A17755FF00BEE3FF001A00ADF09BF67CD7FC17F10748D7F51D5B4B9ED6CDA42F1C1E66E6DD1BA0C6540EAC2BE98C57CE1FF0D5DA07FD0B9AAFFDF71FF8D1FF000D5FA07FD0BBAAFF00DF71FF008D007AE7C60F08DD78DFE1E6A9E1FB09E182E2ECC25649B3B06C951CE7009E8B5F3E787FE18EA5F02F5587C7BAFDED96A361A70647B5B12DE6B19479431BC01C1704F3D0574FFF000D5FA07FD0BBAAFF00DF71FF008D52D63E28587C75D3E5F0068D6175A5DF6A443A5D5D9568D3CA225390BCF2108FC6803B7F879F1F742F1B78B2D341B1D2F53B6B9BA0E5649FCBD8362339CE18F6535D67C67F085DF8EFC017DA0E9F7105B5C4EF132C936768DB22B1CE39E82BCA7E117C01D5FC0BE3FD3B5FBBD674FBA82D5655686247566DF1B20C1231D5B3F857B2FC4AF17DB7813C2577AFDE5ACD7505BBC68628480C77B85E33C77A00F1CF82BF01F5CF00F8F6DB5ED4754D36EADE28648CC76FE66E259703AA815EC3F133C6965E01F0CC9AE6A56D73736E9224652DC02D96381D4815C2FC35F8F3A478F3C550E8561A46A16B3CB1BC9E64CC85405193D0E69BFB5C9FF8B3577FF5F96FFF00A1500715E22F1E58FED03A69F047872D6E74CBF661782E2FF6F95B63EA3E424E4EE1DAB95FF8652F13FF00D07B44FCE5FF00E26BCD3E08F8E6D3E1EF8D575CBFB49EEE25B792111C2C01CB639E7E95F58FC2BF8E3A4FC44F13368B63A4DF59CE2069F7CCE8CB852011C1F7A00F19FF008651F13FFD07B44FFC8BFF00C4D6AF873C0F7BFB3CEA3FF0997896E6DB54B0910D8793A7EEF30349C86F9C018F90F7AFAC768AF3EF8E1E03BBF889E0C4D174FBB82CE51751DC799302570A18638FF7A803E72F8F1F1B344F889E0C8746D334ED4AD678EF23B9DF7213690A8EB8F958F3F38FCAB89F809E3EB0F871E31BBD6353B4BABB866B17B5096FB770669236CFCC40C610FE75E87FF0CA1E22FF00A1874AFF00BF727F852FFC328F88BFE862D2BFEF893FC28037FC55E2AB5FDA3ECA2F09F85E09F4ABDB193FB4DE6D480F2D914188A8D858E73303D3A035E47F15BE0CEAFF000E344B4D4B53D4AC2EE3B8B8FB384B7DFB94ED66C9DC071F29AFA1BE047C14D57E1B78B2F356D4354B2BD8A7B26B511C2AE0825D1B3C8E9F27EB5D67C78F87379F12BC3561A669F7B6F6525B5D8B92F329208D8CB8E3FDEA00F9F7F62BFF009293AC7FD821FF00F47435E8FF00B6C7FC93FD13FEC28BFF00A2A4AE67C3BE189BF66EBC97C55E21B98B58B5BE8CE9890D882AEAEC44818EFC718888FC455AF10F88E2FDA4ACE3F0D787E193469F4E7FED179AF88657500A6D0132739707F0A00F25FD9FFE24E9DF0D35FD4EFF0054B3BBBB8EEED840AB6DB72A7706C9DC47A56FFED01F18F48F897A2E9767A569D7D69259DC34CCD73B30415C606D26B73FE1943C41FF00431693FF007C49FE15C0FC5DF83BA97C33D374FBCD4753B2BD4BC95A255B756054819C9C8A00B3FB3FFC4CD3BE1A6ADAB5D6A96377771DE429128B6DB95209393B88F5AF6FFF0086ACF0B7FD00B5BFCA2FFE2EBE77F843F0CAFF00E265F6A36BA75F5B59B5946B2B34EAC430624718FA55EF8B7F08F51F86B69A6CFA8EA767782FA47455B70C0AED009273F5A00F79FF0086ACF0BFFD00B5BFCA2FFE2E8FF86ADF0BFF00D00B5AFCA2FF00E2EBE79F847F0CAFFE25DF6A36DA6DF5B599B28D24779D588218903A7D2BD34FECA3E21FFA18B48FFBE24FF0A00F7CF849F1674AF89B2EA89A5585EDA1B01197373B3E6DFBB18DA4FF0074D78EFEDC7D3C17F5BDFF00DA149E1CB76FD990DC4FE23235A5F10058E11A7FCBE518724EEDF8EBE68C63D0D79CFED07F15F4FF0089C3421A6E9F7565FD9E672E27653BFCCF2F18C7A6C3F9D007A67EC3DF73C67F5B3FFDAF5F52D7CB5FB0E1FDC78CBFDEB3FE53D7D4A3A50014514500145145001451450071BE32BCD3ECF5ED0E4BF8E559A18AEAEA2B886728EA23542F1EDFE357046549C6541EA0118BE15B99A6F15E8F3EADA25B69FF006BD31FFB292D2F8CB1DBC40C6CEAD16C45472193E65DC30BB723F8AFFC4FB8B669F47D2EE6D341737A6665B9D72212DBC5B1572A1491B9DB770370E158F6AA3E10BB16FE31B5B09FFE116BDB8974F68E2B9D161313DB43132ED8990BBE233BF8C301918C1EA000F8F3FF002275AFFD7EAFFE80F5E055EFBF1E7FE44EB5FF00AFD5FF00D01EBC0AB68EC7CCE6DFC6F90514514CF2C28A28A04C2BDFBE027FC8A175FF005F8FFF00A025780D7BF7C04FF9142EBFEBF1FF00F404A899EAE53FC7F91E9428A051599F4C14869690D0028A28145001451450015CF78FB4697C47E0DD6B45B791229AFED24B7491F3B54B0C0271CE2BA1AC6F176B71786FC33A9EB33C2F3C7636EF70D121019C28C9033401F1D78C3F670D7BC31E18D4B5ABAD674C9A0B185A778E3126E603B0C8C5785D7D71A87C73D2BE26D8CDE0AB0D22F2C6EF5E536315CCD22B244CFC06603923E95CCFFC327EBDFF00432699FF007E64A00F14F86BE2287C25E39D1F5DBA8649E0B29BCC78E3C6E618238CFD6BEB6F05FED17A178A7C4DA768B6BA36A70DC5ECA214791A3DA0904E4E0E71C578BF8E7F672D5FC23E13D4B5DB9D76C2E21B18FCD68A389C330C81C13F5AE2FE0173F18BC29FF5FABFFA0B5007D99FB42FFC917F15FF00D7A8FF00D0D6BE11F01F86EE3C61E2CD3F41B39E382E2F58A2492025410A5B9C7B0AFBBBF685FF00922FE2AFFAF51FFA1AD7C39F0B7C5107833C79A4EBF756F25CC564ECE628C80CD9465E09FF007A803D8FFE194BC4BFF41FD23FEF993FF89AE7BE20FECFFAE7823C1FA87882FB57D36E6DECFCBDD1C41F7B6F915063231D5C57A8FF00C358E87FF42D6A5FF7FD2AAEADF166C3E37E9F2FC3ED234CB9D32FB58C04BAB97568E3F2984E72179E44447D48A00F9D7E1D784EE7C71E2FB1F0FD95C456D7177E61596504AAEC8D9CE71ECB5F4CFC20F807AD7817C7DA7EBF7FABE9D7305B2CAAD1441C31DC8578C8C77AE6F4AF8577FF0003F508FE20EAFA8DB6A765A4E564B5B5565924F3418460B71C1901FC2BA1FF0086AFD07FE85DD57FEFE47401F48900D715F193C1F75E3AF015EE8361730DB5C5C3C6CB24C0951B1C373807D2B8AF871FB40E95E38F17D968167A2DF5B4F74242B2CB22151B119CE40FF77F5AF6CA00F9CBE0D7C06D6BC05E3BB6D7B51D5B4EB98228A48CC5007DC772E3F88015E9DF1B3C1577F103C0B3685A7DCDBDACD24F1CBE6CF9DA029CE3806B4FE2778CEDBC05E139F5DBDB59AEE085D10C709018EE38EA78EF5E2FFF000D61A17FD0B7AA7FDFD8E803C83E26FC09D67E1F785CEB7A96ABA7DD4226487CBB70FBB2D9C1F980F4AD1FD8F47FC5DD7FFB074FFCD2B47E357C77D2BE217825F44B3D1EFACE66B88E6F3657465C2E78C0FAD67FEC79FF002579FF00EC1D3FFE849401F70D71DF147C7767F0F3C389ACEA36B3DD40D7096FB21201058139E7E95D89AF0BFDB1FF00E49243FF0061383FF419280323FE1AB3C35FF401D5FF00EFA8FF00F8AAECFE14FC6CD23E23F88AE348D374DBEB49A1B56BA2F70536950E8B8E09E7E71F957C0B815E8FF02BE20DAFC37F165DEAF7B633DEC73D935A88E160A412E8D9E7B7C87F3A00FD0BA4AF9BC7ED61A0639F0E6A7FF7F5293FE1AC3C3FFF0042E6A9FF007F63FF001A00F44F8F7F0F6FBE247852CB4AD32EED6D2582F56E8BDC0254808EB8E01FEFFE95E3BE1AF0E4FF00B37DDC9E24F144B1EAD6DA8A7F67C7169E08657CF99B8EFC0C6108FC6B7BFE1AC3C3FF00F42E6ABFF7F63FF1ACDD7BC470FED296B1F86F40824D1A6D39FF00B41E6BD21D5D7053680BCE72F9FC28035BFE1AB3C33FF42FEB1FF7D45FFC557957ED01F18B49F897A2E9767A669B7D67259DC34CCD70530415C606D26B13E2EFC1AD47E1AE91637FA86A7697A97539815614652A7696C9CFD2BCA88C5007D2FF00B117FC8C7E27FF00AF48BFF4335BFF00B6F7FC81FC2BFF005DEE3FF414AC0FD887FE463F13FF00D7A45FFA19ADFF00DB7BFE40FE15FF00AF8B8FFD052803C8FF0067FF0089BA7FC34D4B57B8D4ECAEAED2F628E3516E572A5493CE48F5AF6CFF0086AEF0D76D0358FCE2FF00E2ABE39A2803DA7F683F8B5A67C4DB7D0A3D2F4FBCB3FECF799A4FB4EDF9B784C63693FDD35E2E796AF45F83DF0B6FFE26CDAA47A7EA16D6434F58D9CCEACDBB7EEC631FEE1A93E30FC28BEF86434837FA95B5F7F6979DB3C9465D9E5ECCE73EBE60FCA803D87F61CFF57E32FF007ACFF94F5F5457CAFF00B0E7FABF197FBD67FCA7AFAA2800A28A2800A28A2800A28A280381F8A57CD0FF006659DDDD41A7E8F73E6FDAAFA6B2174B1B285F2D30C0A296CB1DCC08F931D48AC5F87779041E23165E1FD52DF5AD32582492E67874E8EDBECCEA57665E24546DDB9BE5233C673806BA1F88935FC72696A92EB36FA41690DE4DA3C066B90C00F2C6D5566087E7C95527217A026AA7C38B5F135B0B69353BBD42EB4FBA4B9629A96C1736E566C404E003F3C4496539DACA3A648A00ABF1E3FE450B6FFAFC4FFD01EBC0EBDF7E3CFF00C8A16DFF005F8BFF00A03D78156D1D8F99CDBF8DF20A28A299E585145140057BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095133D4CA7F8FF0023D285140A2B33E98290D2D21A005145028A0028A28A002B07C77A249E24F06EB5A2C32AC32DFDA496CB232E429652326B7A8A00F92ACFE05EA1F0C6E22F1B5DEB56D7F6FA137DBE4B58A164794273B412700D6F1FDAC747CFFC8B1A87FE0427F857AF7C72FF009243E2EFFB074BFCABE03F03F87DFC57E2CD2F438AE16D9EFA61089997704CF7C6466803DE3E247ED1DA678BBC0FABE830681796D2DF43E52CB24EACABC839200F6AF0FF00871E218BC29E37D1F5C9E06B88AC67133448C14B8008C027EB5EF1FF000C99A87FD0D969FF00804DFF00C5D1FF000C99A87FD0D969FF00804DFF00C5D006C6A1F1A2C7E2ED9CBE03D3F48B9D3AEB5B5FB3C7753CAAE9191F364AA8C9FBB5CF7FC327EAFF00F434587FE02BFF008D5DB5F82D73F07EE23F1E5D6B30EA90E8A7CF6B48ADCC4D2E7E5C062C40FBDE86B53FE1AD34EFFA14EEBFF0357FF88A00F3DF885FB3BEA5E0BF06EA5E21B9D7ED2EA2B2542D0C76ECACDB9D53824FFB55E79F093C550F81FC7FA5F882EADA4BA86CFCDDD0C6C159B7C4F1F04F1FC79AF5BF8A3FB4459F8DFC09AA787E0F0F5C5A3DEAA2F9AD74AE176C8AFD3683FC35F39D007D1DF16BF680D3BC73E01D47C3F6BA1DE59CB74622B34932B2AEC915F9007FB38AF9C8673D68CF15D57C30F07C9E3CF1959F87E0BC4B296E55D96678CB81B54B74C8EC2803AFFD95BFE4B9681FEE5CFF00E93C95F677C4FF001843E04F07DD6BF736B2DD456ED1A18A360A4EF70BD4FD6BC93E12FECF979E02F1E69FE219F5FB7BD8ED565530A5B142DBE364EA58FF007B35EA5F183C1D278F3C097BA0417896725C3C6E2578F781B5C374047A500788EA9F126DFE3EDA3780B4CD3A6D1AEAF313ADDDC482545117CE415001E718AF36F8A5F01750F87DE13975DBBD6ED6F238E548BCA8A1652771C6724D7B4FC20F80375E01F1B5B6BF71AF417C90C5247E4A5B32125D4AE7258FAD6CFED75FF246EEFF00EBF20FFD0A803E46F857E059FE2178A4689697B159CA6079FCD910B0C2E38C0FAD7B9691E059FF00677BB1E36D5AF23D66DDD4E9FF0066B74313EE9390DB989181B0FE75C5FEC7FF00F257D7FEBC27FF00D96BDCBF6C3FF9242BFF0061283F93D005DF857F1DF4FF00885E2A1A1DB68B75652985E6F365995C6171C600F7AE93E38780EE3E22F831345B4BD8ACA45BA4B8F32542E30A186303FDEAF96BF641FF0092C11FFD78CDFF00B2D7DCD401F217FC3276B1FF00433E9FFF0080CFFE35C4FC5BF81F7FF0DBC3506B379ACDADF4735DADA08A2859482C8ED9C93FEC7EB5F5CFC5FF001FC3F0DFC310EB371A7C97F1C972B6DE5A4A2320B2B1CE483FDD35F2CFC6EF8E16BF12BC276BA35B6893E9ED0DEA5D991EE048182A3AEDC051FDFCFE1401C37C22F87973F12BC4575A4D9DF43652416AD7464950B8203A2E300FFB63F2AF5DFF00864ED5BFE867B0FF00C076FF001ACAFD8AFF00E4A66AFF00F60893FF0047435F48FC62F88F07C33D02CF54B9D3A4BF5B9B916C238E511904AB367241FEED007827FC326EAFFF00433D87FE03BFF8D5ED27C372FECD52BF893569D75D87525FECF582D479451B3E66E25B3918423F1AF4DF839F1B2D7E25F886EF4983449F4E7B7B5375E6493890300EAB8C051FDFFD2B5BE39FC389FE257876C34CB6D4A2D3DADAEBED06492232061B19718047F7A803C8B55D723FDA5E38F40D2216D0A5D2DBEDCF2DD1F395C1F93680B8C1F9B35E53F18FE0DDF7C32D374FBCBDD5ADAFD2F266855628990A903393935EA561A03FECCF23F883509D7C431EA83EC2B0C0BF67319077EE2496CFDDC629F7FACAFED34ABA369F03680FA47FA634B3B7DA0481BE5DA00DB8FAD00794FC02F89F69F0CB54D5AEAFB4F9EF96F614895619153695627273F5AF56D66F93F69B58AC34646D09B4426677BAC4A2512FCA00DB8C6367EB547FE193751FFA1AAD3FF00DBFF8BAF53F813F07AE7E195EEAD3DCEAF0EA1F6D48D1447018F66D2C7BB1CFDEA00F98BE317C1DBDF86563A6DCDEEAD6F7C2F6478D56289936ED00E4E4FBD795D7D79FB6EFFC803C2DFF005F53FF00E80B5E1BF063E15DC7C50BAD561B6D522D3DAC123726484C9BF7961D88C636D00697C01F8AB67F0C65D6DEF74DB9BFFED05842885D5766CDF9CE7D778FCAA5F8FDF162CFE278D085969773A7FF006779FBBCE9036FF33CBC6303B6C3F9D7783F64DD43FE86CB4FFC036FFE2E9DFF000C99A87FD0D96BFF00806DFF00C5D00687EC3BFF001EFE32FF007ACFF94D5F52D7937C06F85171F0BE3D6D6E7558B51FED13095F2E131ECF2F7F5C939CEFFD2BD66800A28A2800A28A2800A28A280387F893677373FD9933DADF6A1A342D27DB6CECAF05ACAEC42F96DB9A48C32A90D952E396079DB8AA5F0CBC3FA869105ADCFDAA54B29A3B933D84D7CD77E5319B741B5C961B9632C8FB4E09C1E48C95F8B11D9A0D1B51D4DB419EC2D65911AC75BB916F6F3BBA80AC18AB0DE9B5B00A9E19BA100D67FC2BD2B42D335530691AA68CBAA451DC7F6969DA5B03112F3878F1D33E482620DB7386C1C60000163E3C7FC8A16DFF005F89FF00A03D781D7BEFC79FF9142DBFEBF17FF417AF02ADA3B1F339B7F1BE414514533CB0A28A28130AF7EF809FF2285D7FD7E3FF00E8095E035EFDF013FE450BAFFAFC7FFD012A267AB94FF1FE47A50A28145667D30521A5A43400A28A0514005145140051456278D75C3E1AF09EAFAD8B7FB57D82D64B8F277ECDFB4671BB071F91A00C0F8E5FF2487C5DFF0060E97F957C13F0FF00C44BE13F19693AEBDB7DA96C67598C21F66FC678DD838EBE95ED5E39FDA5C78A3C21ABE843C2A6DBEDF6ED079E751DFB33DF6F9433F4C8AF12F02787BFE12BF17E95A17DA7EC9F6E984227F2FCCD99EFB7233F9D007D21FF000D696BFF00427CFF00F8311FFC6EB7FC05FB4941E2EF18697A1278625B56BE98442637C1F6704E71E58CF4F5AE5FFE1929FF00E8731FF82AFF00EDD4A3E071F84C478EDBC43FDABFD85FE97F61165E479E071B7CCF31B6F5EBB4D007D0DF11BC34DE30F056ABA0A5D7D91AF6211898A6FD9F3039DB919E9EB5F3A7FC3255CFFD0E10FF00E0B8FF00F1CAEA7E1EFED22BE30F19E95A02F858DA1BE97CBF3CEA1E66CE09CEDF2867A7A8AF61F88DE28FF8433C19A9F880D99BD1648AE6012797BF2EABF7B071F7B3D3B5007CEBFF000C9573FF0043843FF82E3FFC728FF864AB9FFA1C61FF00C171FF00E395D87C35FDA2C78DBC71A67874785CD91BD6702E3FB43CDD9B519FEEF9633F771D7BD7D01401F137C4EFD9E27F02782750F10BF88E2BE5B4318300B33196DF22A7DEDE718DD9E9DAB07F655FF92DFA1FFD73B8FF00D12F5F4FFED4FF00F243BC43FEF5B7FE94475F307ECABFF25BF42FFAE771FF00A21E803EF73D2B91F8A7E324F01782EEFC412591BD5B768D7C912F965B7305FBD83EBE947C4FF180F02782F50F10BD91BE5B5318F204BE5EEDF22A7DEC1C63767A76AF95FE2C7ED04BE3EF055E787BFE11AFB07DA1E36FB40BFF00376ED70DF77CB5CE718EBDE803B41FB5ADB7FD09F37FE0C47FF1BAE1FE30FC7D8BE2178365D063F0EBD8799324BE7B5E0931B4E71B760EBF5AF38F851E0AFF008581E33B6D03FB4069E668E4713987CDC6D52D8DBB97D3D6BDD7FE1927FEA765FF00C15FFF006EA00F13F837E3B8FE1DF8C46B92D835FA881E1F2565F2CFCD8E7383E9E95EE375E3B4FDA3223E08B4B06D02453FDA1F6B925FB48C47C6DDA02F5DFD73DAB86F8BBF00CFC3BF08B6B87C4A350027487C9FB0F95F7B3CEEF31BD3D2B86F83BE3EFF00856FE2F6D73FB37FB4B36CF6FE4F9FE4FDE2A73BB6B7F77A63BD007D43F06FE024DF0EFC62BAECBE218EFF00103C3E42DA18FEF639DDBCFA7A57BC66BE55FF0086B65FFA128FFE0D47FF0019A5FF0086B71FF4259FFC1A8FFE33401D8FED99FF0024A2CFFEC2D0FF00E8B96BE265FBD5F53C9E353FB4883E0C5D3FFE11CFB3FF00C4CBED866FB5EED9F26CD9B63C67CDCE7776E9CD701F197E069F869E17B5D67FE120FED3F3AF16D0C22CBC9DBB91DB76EF31B3F7318C77A00E67E08FC454F867E28BCD5DF4D6D485C59B5A79427F2B6E5D1B7676B7F7318C77AE9FE36FC6D87E25F876CB4C4D05B4E36D742E7CC6BA136EF919718D831F7B39F6AE53E0BFC3A3F12FC4D77A48D50699E45A35D79A60F3B38745DB8DCBFDFCE73DABA5F8D5F048FC31F0FD96ABFDBE354FB4DD0B6F2BEC7E4EDF919B767CC6CFDDC631DE8039FF00827F11A3F86BE25BDD564D39B5017166D6BE52CC22C65D1B76769CFDCC63DEBDB7FE1AD6CFFE8509FF00F03C7FF1BAF937AF5A5A00F68F8E5F1AE1F89BA169FA741A1C9A71B5B833991AE44BBBE52B8C6D1EB5D5FEC49FF236788BFEBC53FF004657CD95E95F047E278F861AB6A57C749FED3FB640B0ECFB4F93B30D9CE76366803F41EBCD7E357C538BE17D9697712E94DA91BE91E30AB71E56CDA14E7EEB67EF565FC12F8CFF00F0B3F52D4ED7FB07FB2C5944926FFB679FBF712318D8B8E9EF57FE397C2B3F146CB4A817571A61B09247DDF66F3B7EE0A31F7D718DBEF401E4D73A87FC34EE34DB688F86CE899B9324A7ED5E7093E5C0036631B7DFAD16967FF0CC25AF6E5FFE124FEDD1E4AA463EC9E4F95CE7277EECF99ED8C57A57C0EF834DF0BF51D52E9B5D1AA7DB6148B6FD93C9D9B4939CEF6CF5F6ABFF001C3E14FF00C2D1B5D221FEDAFECBFECF791F77D97CFF003378518FBEB8C6DF7EB401E5A3F6B4B6C73E109FFF000603FF008DD7A6FC14F8B49F141B59F2B487D37FB34424EEB812F99E66FF004518C6CFD6BE5BF8E1F087FE15743A33FF006E7F6AFF0068B4ABFF001E9E4797B367FB6D9CEFF6E9517C0FF8B43E171D6FFE24BFDA9FDA4211FF001F7E4797E5F99FEC3673E67B631EF401F7F515E5BF03BE2D0F8A4BAC91A29D2FFB34C20E6EBCEF33CCDFFEC2E31B3DFAD7A9500145145001451450014514500723E34D25AEAEF4CD46D757B3D2EF6D3CE8924BCB71711BA481438D85D3E6F917041E391820D63781FC3434CD434A87FE120D3B51B3D1ED25B5B086DADD63942394CB4ACAE4310100F95541CE4F38AD0F88D1E957573A15B6A1A3DC6B37E97125CDA58C31C2FE6058CA485FCD2102012AE7241C95C7355FC2526936BE205B4FF842CF867529E07785DADAD809914AEF51240EDC8CA9DAD8CF519C1C0053F8F3FF002285B7FD7E2FFE80F5E055EFBF1E7FE450B6FF00AFC5FF00D01EBC0AB68EC7CCE6DFC6F90514514CF2C28A28A002BDFBE027FC8A175FF5F8FF00FA025780D7BF7C04FF009142EBFEBF1FFF00404A899EA653FC7F91E9428A051599F4C14869690D0028A28145001451450015C5FC69E3E12F8BCF7FECB9FF00F4035DA561F8DB45FF008497C27ABE862E3ECDFDA16D25B79DB37F97B971BB6E4671E991401F991DEBA2F007884784FC65A46BAD6DF6A1613898C024D9BF1DB760E3F2AFA1BFE1923FEA753FF829FF00EDD583E3CFD9A4F853C1DAB6BA3C586EFEC1019BC8FECEF2FCCC76DDE69C7E46803A4FF86B587FE84E93FF000643FF008D573DF103F69387C5BE0ED57425F0BC96A6FA130F9E6F83ECE41CEDF2C67A7A8AF15F87BE1AFF0084C3C65A5E83F6BFB1FDBA5F2BCFF2FCCD9C139DB919E9EA2BE86FF86481FF0043B0FF00C157FF006EA00F9E7E1DF894783FC69A5EBE6D7ED7F6194C9E4799E5EFF948C6EC1C75F435F43C9F1997E3327FC2009A1B68EDADFEEBEDCD75E78876FEF33B362EECECC7DE1D6B9DF885FB35FF00C221E0DD535E1E2AFB67D8A3F33C8FECEF2F7F2063779A71D7D0D78EFC37F13FFC219E35D2FC41F6437BF61767FB3F9BE5EFCA32FDEC1C7DECF43D2803EA7F863FB3BC9E08F1C697E226F1325E8B1323183EC3E5EEDD1B27DEF30E3EF67A76AFA1ABE7CF869FB450F1BF8DB4DF0F1F0BFD88DE171F681A879BB36A33FDDF2973F771D7BD7AC7C4CF16FF00C20DE07D4BC45F63FB77D8FCBFF47F37CBDFBE454FBDB5B18DD9E87A50037E2B7841BC79E05D43C3AB7A2C4DD988FDA0C5E66CD922BFDDC8CE76E3AF7AF9FE0F85ADF019FF00E1604FAB8D7174D1E59B14B7FB317F37F759F30B3631BF3F74E718AEC3E18FED0C7C77E38D3BC39FF08C7D83ED7E67FA47F6879BB7646CFF0077CA5CE76E3AF7AE83F6AAFF009223AE7FD74B6FFD1E9401E13F153F6858FC79E07D43C3ABE1A6B1FB518CFDA0DEF99B7648AFF77CB19CEDC75EF5E61F0B7C1A7C7DE32B4F0FC77A2C4CE923F9E62F302EC42DF7723D3D699F0B3C203C77E35B1F0F1BDFB0FDA9643F68F27CDD9B2367FBBB9739DB8EBDEBDED3E18FFC284CF8FF00FB58EBE34FFDD7D845B7D97CCF37F779F337BE31BB3F74E718E2803ACF84DF005FC03E34B5D7CF88D750F263923F205918B3BD4AE777987A67D2BDFBA8E95F2BFF00C35BAFFD0947FF0006BFFDA68FF86B81FF004251FF00C1AFFF0069A00F6FF8C5E043F10FC1CDA12EA034F26749FCEF27CDC6DCF1B772FAFAD7CA5F17FE03C9F0E7C26BADB7889351437096FE50B3311F98139CEF6F4E95DE1FDADC67FE44AFFCAAFF00F69A078F07ED1A0F81D74DFF00846F6FFC4C3EDBE7FDB3FD5F1B3CBDB1F5DFD77718E9401F2AD15EE5F17FE028F873E113AE37893FB43FD2120101B0F27EF679DDE6374C7A5715F07BC003E2478ADF441A90D38ADB3DCF9DE479D90A54636EE5FEF7AF6A00EFBF634FF92A97BFF60997FF0046455F4BFC6CF878FF0012BC296BA347A97F67186F16E8CBE479DBB6A3AEDC6E5FEFE739ED5CA7C19F815FF0ADBC553EB47C45FDA5E6DA3DAF93F61F271B991B76EF31BFB9D31DEBDB6803C43E09FC1093E1A789AEF56935D5D485C5A35AF942D3CADB9747DD9DEDFDCC631DEBA9F8D7F0DCFC4DF0ED8E963531A69B6BAFB4F9A60F3B77C8CBB71B971F7BAE7B57A3579EFC69F88FFF000ACFC3D65AA7F657F69FDA6E85B795F68F276E519B7676B67EEE318EF401F2A7C66F81EDF0CFC3967AB3788135117176B6BE57D8CC3B728EDBB3BDB3F7318F7AF1AAF68F8D9F1BFF00E166F86ECF48FF00847BFB2FECF76B75E6FDB7CEDD8475DB8F2D71F7F39CF6AE6FE0B7C35FF8599AF5F69BFDADFD97F65B6FB479BF66F3B77CEABB71BD71F7BD6800F82DF0CDFE26EB77DA7C7AA2E9BF65804E64683CDDDF305C6372FAD6C7C6CF83327C30D2F4DBC7D6D7521793343B05AF93B30B9CE77B66BD30681FF0CC6C3C43F691E27FED5FF40FB3ECFB17958F9F7EECC9BBEEE3181D7AD79C7C6DF8CDFF000B3F49D3AC8E83FD99F6399A60FF006BF3B7E5718C6C5C50076BFB11FF00C8C7E26FFAF487FF004335F5E57C89FB11FF00C8C7E26FFAF487FF004335F5DD0079B7C6CF8A49F0BF4FD32E5F496D4CDF48F185171E4ECDA01CE76B67AD791FFC35AC1FF426BFFE0CC7FF001AAF59F8DFF0B3FE168E9FA5DAFF006C7F65FD8657937FD97CFDFB8018C6F5C74F7AF21FF8646FFA9DBFF293FF00DBA8009A71FB508582253E18FF00847BE72CC7ED827F3F8C7FCB3DB8F2BDF3BBB62A1FF864A9BFE8714FFC161FFE3B538B6FF865CFDFEEFF0084A47884ECC63EC22DFC8E7AFEF376EF37DB1B7BE78F54F81FF16C7C513AD03A2FF65FF67791D2F3CFF33CCF33FD85C63CBF7CE7DA8013E04FC2693E16AEB41F595D4C6A2613C5B793E5F97BFF00DB6CE77FB74AF56A4A5A0028A28A0028A28A0028A28A00E0FE23A2DDDFE8B6369A7DCDD6B3279D2DB4906A2D60D0C68104A7CD5C939DE8366083C138DB9199E0D9B495F1758C508D5E7D55ADAE5274D5EF649A7B0286126308C4A80FBC1DEBC305182456EFC40D4F4CD1C69777AD58A4B6F14CF2C174CFB04170A85A31BBF877E19724804900E7762B9CF056BC758F17693777B2787352BDBCD2E49567D2A361359465A33E5CAC5DB7292DC1213E65385EB800B5F1E7FE450B6FFAFC5FFD01EBC0ABDF3E3C7FC8A16DFF005F89FF00A03D781D6D1D8F99CDBF8DF20A28A299E5851451409857BF7C04FF009142EBFEBF1FFF00404AF01AF7EF809FF2285D7FD7E3FF00E8095133D5CA7F8FF23D285140A2B33E98290D2D21A005145028A0028A28A002B0BC6BAD1F0CF84F58D6D6DC5C9B0B592E7CADFB37ED19DBBB0719F5C56ED715F1ABFE49278BFF00EC193FFE806803C1BFE1ADA6FF00A13E3FFC191FFE35583E3BFDA565F15F84355D09BC2E96BF6E81A0338BF2FB33DF6F9633F9D78C780B424F1378C748D1249DADD6FEE52032AA6E2993D71915F4AFFC325E9FFF00436DD7FE012FFF0017401F367803C467C23E31D2B5F5B51766C65F34405F66FE08C6EC1C75F435F51F803F69397C59E31D2B426F0BA5AFDBA61179DF6FDFB38273B7CB19E9EB59FF00F0C9961FF436DD7FE012FF00F17515C7C10B6F84D19F1D5B6B936A72E860DD8B392DC4426C7182C18E3AFA1A00FA03C7DE191E2FF076A9A135D7D93EDD188FCFF2F7ECF981CEDC8CF4F515F3F7FC324C3FF43949FF0082D1FF00C76B3FFE1AD6F7FE852B6FFC0D6FFE228FF86B5BDFFA14ADBFF035BFF88A00BEDF0822F82807C428F597D65B45F9FEC26DBECFE7799FBAC799BDB6E3CCCFDD3D315C77C4DFDA1E4F1C782753F0EB786D6C96F7CAFDF8BFF30A6C911FA796339D98EA3AD74B6FF17E6F8D930F87D77A3C5A445AC9DA6F63B832987CBFDEFDC2A339F2F1D475AC5F8A5FB3E5B7827C07A9F8863F10CD786CBCAC42D6823DDBE544FBDBCE3EF67A76A00F23F85FE2F3E05F1C69FE221642F8DA093F7064F2F76F8D93EF60E31BB3D3B57BD43F14DFE3BB1F87F2E943431A97EF05F0B8FB4F97E57EF71E5ED4CE76633B86339AF0AF855E128FC75E3BD3BC3B25DB592DD894F9EB1F9857646CFD323AEDC75EF5F56FC2FF00D9FADFC09E33B3F1047E209AF5AD964510B5A88C1DC857390E7D7D28038C7F854BF02A23F10A3D5CEB6749F97EC0D6FF006712F9DFB9FF0059B9B18F333F74E718E3AD4517C4F6F8F5FF001404BA4AE842FCF9A2F56E3ED3B3CAFDE63CBDA99CECC7DE18CD7A9FED4FFF002433C41FEFDB7FE94475F327ECA7FF0025B347FF00AE371FFA25A8037BE2BFC014F00F832E75E5F1135FF952471F92D6422CEE38CEEF30FF002AF39F84BE081F103C6516826FCD81921925F3843E6E368CE36EE1FCEBEBBFDAC3FE48C6A3FF005F36FF00FA3057CEFF00B24FFC965B5FFAF3B8FF00D06802EFC60F80D1FC3CF079D707881F50227484C3F63117DECF3BB79F4F4A67EC73FF002575FF00EC1D37FE8495EE5FB5E7FC91D97FEBFA0FFD9ABE50F847E3D97E1CF8ACEB70D82DFB1B77B730B4A631862A73900FF77D2803EE5F8BDE031F117C247437D44E9EA6749FCE10F9BCAE78C6E5F5F5AE27E107C074F873E2D6D6E3F10B6A00DB3DBF906C845F78A9CEEDE7FBBD31DEA97C1AF8EF71F117C63FD892E85169EBF6779FCD5BA3213B71C60A8F5AEF3E33F8F64F873E104D6A2B14BE66B94B7F29E429F7831CE403FDDA00EFEBCFBE367C436F86BE15B6D6574C1A979B78B69E519FCADBB91DB7676B7F7318C77AF0C3FB5A5FF6F095AFFE06B7FF00115359F8BE5FDA51DBC1F79669E1F4B31FDAA2EA273725CA7EEB66D21719F3B39CFF000FBD007A07C11F8E12FC4CF12DD69126809A77D9ED1AEBCE5BB32EEC3A2EDDBB07F7F39CF6AC6FDB5FFE49E68DFF006145FF00D152573B75E188FF0066A51E2CB4B96F10497DFF0012BFB3C89F670818799BF702D9FF00558C63BF5E2BCDBE327C6CB8F899A059E973E89169E2DAE45C8912E4C9BBE465C6368FEF67F0A00F1EC9AF44F82FF125BE19EBB7DA92E99FDA5F6AB5FB3797F68F276FCCADBB3B5B3F77A6297E087C3A87E2578A2EF499F527D3D60B36BA12244242C43A2EDC123FBFFA57B7FF00C32659FF00D0DB73FF00802BFF00C5D00795FC67F8D0FF0013344B1D3DF425D345ADC19C482EFCEDDF295C6362E3AD667C0FF868BF13757D46C5F543A70B38166DE20F377E5B18C6E5C57B3FFC32659FFD0DB73FF802BFFC5D57BCD1C7ECCA8BACD94E7C42DAC1FB2345328B71105F9F7646ECFA638A00F4AF82BF0753E18EA3A9DDA6B6DA99BD8922DA6D7C9D9B4939FBED9EB5EBB5F257FC359DF7FD0A76DFF81CDFFC457A97C09F8C171F13EFF56826D1E2D3BEC31C6E0A5C1937EE2C3A1518C63F5A00F63A28A2803E5DFDB8BFE3CBC1FF00F5D2EFF94555FF0061AEBE35FF00B72FFDAF5EBBF1B3E14C3F13E0D2127D5A4D3869ED2B02900977EF0BEAC318DBFAD47F04BE1341F0BBFB67C8D5A4D47FB48439DF008B6797BFD18E73E67E9401EA14B494B400514514005145140051451401C6F8F6EF5217BA469B6175058417ED2C725DCF6BF6843200BE5C257200DF96393FDCC0E48AC3F0349AC69BE23B3D3EFD74E8E5BFB17BEBDD3ECAC5615D3DB72841E6293B81CBAFCD924A92300115D5F8AEE3C4311D393C349645A7B831DCC9770BCA214D8CC1F08EA71B942FFC087A1A3438BC52979FF13EBCD166B4D870B656B2C4FBF231CB48C318CF18A00E5BE3C7FC89F6BFF5F89FFA03D781D7BE7C78FF00913ED7FEBF13FF00407AF03ADA3B1F339B7F1BE414514533CB0A28A2800AF7EF809FF2285D7FD7E3FF00E8095E035EFDF013FE450BAFFAFC7FFD012A267A994FF1FE47A50A28145667D30521A5A43400A28A051400514514005715F1ABFE49278BFF00EC193FFE806BB5AE2BE357FC924F17FF00D8327FFD00D007C35F037FE4AFF847FEC2317F3AFBDBE217881FC2BE0AD5B5C8A05B87B180CC22662A1B1DB23A57C13F037FE4AFF847FEC2317F3AFBF7C6BE1F87C55E15D4F43B899E08AFA130B4A8016507B806803E64FF0086B2D4BFE857B3FF00C0A6FF00E26B0BC71FB475FF008BBC29A9E8737876D6DE3BE80C2654B9625324738DBCF4AF40FF00864FD0FF00E866D4FF00EFC25739F113F670D23C29E09D5F5D835EBFB896C60332C52448A1B040C67F1A00F996BA8F865E198FC65E39D2B409AE1ED92F5D94CA8BB8AE119BA7FC0693E1B78762F1778E747D06E2792DE2BE9BCB695002CA304F00FD2BE8DD47E0F69FF072D24F1EE9BA9DE6A375A2E264B59D15524DC447824723EFE7F0A008AFBE10DA7C17B56F88369AACFAA4FA2E1D2CA58563597CC3E572C092302427A76AE13E247ED0B7BE38F066A5E1D9F41B6B48AF7CBCCC970CC57648B2700AF39DB8FC6BAAD37E2F5EFC68BE4F87FAA6996BA5DA6B3947BB81D9DE3F2C79A300F0726303F1ADFF00F864FD13FE865D4BFF0001D2803E6AF86BE2E7F03F8CAC3C436F689772DA0902C2EE501DF1B2751FEF66BDCBFE1AC754FF00A15ECBFF0002DBFF0089AE8FFE194343FF00A19752FF00C074AE2FE2FF00C00D2FC09E02BFF1059EB579772DB346A229625507748ABD47D680362C3E2ADCFC73B95F87F7FA641A45B6A9966BC86532B47E50F38614800E4C6075EF572F3E18DBFC08B57F1FD86A536B171618885A4D1089584BFBB277024F1BB3D3B57CF1F0DFC5D3F81BC6163E20B4B68EE67B412058A462AADBD1939C73FC59AF77D27E275E7C77BD5F00EAF61069569A80F35AEAD58BBA18BF78301B8E76E3F1A00E3FE267C7DBFF001F7846E740B9D06D6D229A4493CE49D988DAD9E84545FB24FF00C965B6FF00AF3B8FFD06B7BE327C06D33C01E03B9D7ACF5ABDBB9A29A28C452C6A14866C1E4560FEC91FF2592D7FEBCAE3FF0041A00FADBE2B781A2F887E133A1DC5F4963199926F35230E72B9ED91EB5F2D7C69F81567F0E7C1CBADDBEB9717EE6E52DFCA920541F30639C827FBB5F4D7C69F1C5C7C3DF041D72D2CE2BC945C470F952B155F9B3CE47D2BE4FF008ADF1D751F889E161A25EE8B676710B84B8F3629999B2A08C60FFBD4016BF63FFF0092BCBFF60F9FFF0065AF70FDB1BFE49347FF0061287FF407AF0FFD903FE4AF2FFD83E7FF00D96BDC3F6C6FF924D1FF00D84A1FFD01E803E62F823F0FE1F891E2D9B46B8BF96C563B47B9F32388393B595718247F7ABDB754F09C3FB36DBA78BB4BBA975E9AF5BFB29EDA7510AAAB8326F0CB9E41840FC6B8AFD8CFFE4ABDE7FD82A6FF00D19157D45F16BE1FDAFC48F0DDBE8F7B7D3D9470DD2DD092150C4908EB8E7B7CE7F2A00F0AD33C532FED2723784B54B64D0A0B15FED459ED9CCCCECBFBAD8430000C4C4E7DAB8AF8E9F056CFE1A786AC753B5D5EE2FDAE2EC5B18E585502E519B3907FD9AFA17E127C12D3BE1B7886E357B0D5EEEF649ED5AD4C7346AA002E8D9E3BFC9FAD745F173E1D5A7C4AD06D34BBFBEB8B28EDEE45C8785431242B2E39FF007A803E26F841F10E7F86BE21B9D5AD6C23BF927B56B531C9214001746CE403FDC1F9D7D4BF02FE35DE7C4AF125FE9977A3DBD825BDA9B80F1CC5CB1DEAB8C103FBD5CD7FC327E87FF4326A9FF7E63ACFD6BC3907ECD5047E26D127975A9B516FECE786F008D5011E66E057BFEEF1F8D007D475E7BF18FE195B7C4DD2F4FB2BBD4A6B05B398CC1A3883EE257183922BC10FED5FAE7FD0B9A67FDFE7A69FDABF5DFF00A1734DFF00BFCF401CAFC7AF83B67F0CF4BD2AEAD3569AF9AF6678CAC9088F6855072304E7AD767FB0F7FC86FC57FF005EF6FF00FA13D79A7C5FF8C3A87C4DB0D3AD6FF4CB5B25B291A45685D98B160060E7E954FE0F7C51BDF86375A9CF61A75B5F35F222309DD9768524F18FAD007E8757957C7AF8A573F0C6CB479ED34C8AFCDFC9223092529B36053D81CE735E29FF000D63AEFF00D0B5A67FDFE92B5743BB7FDA6E496CF5E55D1174202689ACBF79E6997821B774C7963A7AD007A6FC06F8B575F13E6D692F349834F1A7AC257CB98BEFDFBFAE40C6367EB4EF8F9F15EEBE178D0CDA6950EA0351F3C3799294D9E5F978C601CE779FCABCCF5C87FE198D629F4471AE1F1012928BC1E5F93E46082BB7AE7CD3D7D052684CDFB4E9986BDFF1235F0E95F2BEC7FBCF3BED19CEEDDD31E40C63FBC6803D37E01FC56BAF89E35D6BBD2E1B01A779017CA94BEFF33CCCE720631B07E75EB95E6BF06FE14D87C311ABAE9FA95D5F7F68795BFCF455D9E5EFC631EBBCFE55E95400514514005145140051451401CDF8BAD35DBB3A62F87F513A7AFDA0ADE3AC51C8C2228D820382321C274EC4FA52E85A4EB5637A65D4FC493EA70142A2192CE18803C61B2801EC78F7AE8E9BBBFDDFCE803CD7E3D7FC89F6DFF5F89FFA03D781D7BE7C7AFF00913EDBFEBF13FF00407AF03ADA3B1F339B7F1BE414514533CB0A28A28130AF7EF809FF002285D7FD7E3FFE8095E035EFDF013FE450BAFF00AFC7FF00D012A267AB94FF001FE47A50A28145667D30521A5A43400A28A051400514514005715F1ABFE49278BFFEC193FF00E806BB5AE2BE357FC924F17FFD8327FF00D00D007C35F037FE4AFF00847FEC2317F3AFD19AFCC6F076BD2F863C4FA66B76F0A4F358CEB3A46E485623B1C57BC7FC357EBDFF0042E697FF007F64FF001A00FA73E2478924F08F82356D761B74B892CA2F316272407E40C647D6BE70B2F8DB7FF15EF23F03DEE916DA7DBEB67EC8D730CA5DA2079CE08C1E951E9DF1AB53F8B5791F81B52D2AD2C2CF5A3F6692E6DDD9A48C7DECA83C7F0F7AF40F047ECE7A37853C53A6EBB6DAEDFCF35949E62C52471ED63823071F5A004F03FECE5A6F84FC59A66BB6FAF5DCF2594BE6089E1501F8230483C75AF56F881E178BC65E10D4741B8B97B68EF50234C8A1994060DC03FEE8A8FE24788E6F09781758D76D608EE26B18BCC58A4242B7CC0738FAD7CCDFF0D5DAF7FD0B9A67FDFD92803A1BFF0084367F05AD5FE2069DAADCEA975A361D6D27458D24F30888E5867181213F85697C27FDA1350F1BF8FF004CF0F4DA0DA5A4579E6E658A6662BB2277E98FF62B96D33E2E6A1F1A6FE2F87FAB69D6BA6596B39492EADE46678FCB065180DC1C98C0FC6B6355F84FA7FC12B093E20E93A8DCEA77BA3E0A5ADCAAAC7279A44272579181293F502803E9FAE53E26F83A1F1E783EEFC3F737725A4570F1B196340C46D60DD0FD2BC6FE127ED03AB78E3E20E97E1FBAD1AC6D60BB12EE962918B0DB13B8C03C755C7E35EB5F183C5D73E05F015F6BF656B15DCF6EF1288A56215B7C8ABD47D6803E6DF8B5FB3DE99E06F01EA1E20B6D72EEEA4B531010BC0AA1B7C8A9D41FF6ABC77E1978C65F0178C6D35FB6B48EF24B74914452315077215EA3EB5E89F11BF680D5BC6DE10BEF0FDEE8B656B0DD18CB4B0CAE59763AB8C67DD457889EB401ED7F143E3F5FF8FF00C213E8377A1DAD9C72C89279B1CECC46D39C608A6FEC91FF002592D7FEBCAE3FF41AF1615ED1FB23FF00C964B4FF00AF49FF00F41A00FAE7E29F81A0F885E15FEC4BABD96CA3F3926F3238C39CAE70307EB5E35FF0C9DA47FD0D17FF00F80A9FE35EB3F1A3C7173F0FBC10DAE5959C3772ADC470F972B155C3679E3E95E7FF0004BE3A6A7F10FC68DA2DEE91676912DABDC7990BB31CA9518E7FDEA00E6F53F015B7ECF36E3C6DA5DF4BAD5C03F62FB35CA089712756CAE4F1B6A969FE37B8FDA22E0F83355B487468107F680BAB66333131F1B76B6073BFAFB57BFF00C52F035AFC43F0BFF625F5DCD690F9C937990A82D95CF1CFD6BC335CF035AFECF1643C67A1DE4FABDDC8E2C3ECD76A1136C8092D95E72360FCE801BA9F83E0FD9D2DD3C65A55ECBAD5C5C1FECC6B5B841128593E72DB972723CA03F1AEAFE07FC6BBFF0088DE2FB9D22EB47B5B18A1B27BADF14CCE49578D71823FDB3F95782FC57F8DDAA7C44F0DC3A3DFE9165671C772B73E640EC5895565C73DB0E6B98F84DF102EFE1CF88E7D62C2CA0BC926B46B531CAC54056746CF1DFE41F9D007E8D8A2BC1FE037C69D4FE2478AEF749D434AB4B38E0B26BA0F0BB124891171CF6F9FF4AEB3E3C7C46BBF869E1AB1D4EC6C60BD92E6EC5B6C999940F919B3C7FBB401E995F3AFEDAFFF0024FF0044FF00B0A0FF00D152570FFF000D5FAFFF00D0BBA5FF00DFD93FC6B85F8B9F1A350F895A25A69BA8695696696F71F680F0BB31276B2E39EDF350057F80FF000DADBE25EB9A8E9F77A8CD6096B6E2E03C51872C7705C60FD6B6FE3DFC1CB2F867A3E957969AADCDF35E4ED115962540B85CE7835C9FC22F89179F0D757BEBFB0B1B7BD7BA8040C93315006E0D9E3E95ADF177E316A3F12F4DD3ECF51D2ECEC92CE6332B40EC4B1231839A003E027C32B5F897A96AD6B75A8CF63F628925568A30DBB71239CFD2B4BE3CFC21B3F86963A45C5A6AD3DF9BD9244612C4A9B76853C60F3D6B9AF843F13EFBE19DF6A373A7D85BDEB5EC6B132CECC028524F18FAD5CF8BFF0017F51F89B69A65BEA1A6DA592D8C8F22981998B1600739FA50058F80DF0C2D7E266A5AADBDD6A53D8AD944920314618BEE24639FA57D59F073E12597C33B9D526B3D52E6FDAF9234612C6A8136163918EBF7ABC5FF00622FF9187C4FFF005ED0FF00E84D5F5D11DE803E5AFDB8BFE3D7C1FF00EFDDFF00286BC83E0CFC57BAF861FDAE6D34B83503A8F93BBCD94A6CF2F7E3181CE7CC3F957AFF00EDC5FF001E7E0FFF00AE977FCA2AF9481E2803EF6F805F15AF7E278D71AF74AB7D3C69FE46CF2A52FE6799E66739E98D83F3AF5CAF967F61AFF53E33FAD9FF00ED7AFA9A800A28A2800A28A2800A28A28032352D6AD6C2EE3B4912E9EE9C29458ADA59132C4AAEE7552A99208CB11EB585A38F0C5BEBF08B7B0165AA6E98445EDA48C3BCD89A511C8C02396C0621493F29EC0D6AEA76FAA7F6B09EC12D1ADE458565F3656565D92337CA0290721BB914687A4CBF60B46D65E2BCBC82413C52F91E5EC6F2F603B771F9B69604F1D4F0280393F8F5FF00227DB7FD7E27FE80F5E055EFBF1EBFE44FB5FF00AFC4FF00D01EBC0AB68EC7CCE6DFC6F90514514CF2C28A28A002BDFBE027FC8A175FF5F8FF00FA025780D7BF7C04FF009142EBFEBF1FFF00404A899EA653FC7F91E9428A051599F4C14869690D0028A2814500145145001591E2CD162F11F86F52D1AE6592186FADDEDDE48F1B9430C12335AF5CF7C40D66E3C39E09D6F5AB348A4B8B0B492E234972519954900E0838FC6803C4FF00E1947C33FF0041FD67F28BFF0089A3FE1947C33FF41FD67F28BFF89AF3FF00F86ACF16FF00D01742FF00BE26FF00E3947FC35678B7FE80BA17FDF137FF001CA00F5BF057ECE7A0F84FC55A76BB67ACEA93DC5949E6A47288F6B1C11CE173DEBDCB00F515F197FC35678B7FE80BA0FF00DF137FF1CA3FE1AB3C5BFF00405D0BFEF89BFF008E5007D5FE38F0E41E2DF0A6A3A0DDCD2C16F7B1F96F2458DCA320F19E3B5787FF00C3297867FE83DAD7E517FF00135C07FC35678B7FE80BA0FF00DF137FF1CA3FE1AB3C5BFF00405D0BFEF89BFF008E5007AFF807F67CD13C15E2DB0D7EC357D4E7B8B32E5629D536B6E464E7001E8C6B67F6A1FF009215E25FA5B7FE94C55E6DF097F683F11F8D3E21691E1FBFD2F4882D6F1A40F240926F1B636718CB91D57D2BDEBC7FE13B4F1AF84EFB40D4669E1B5BC081E48480EBB645718C823AA8ED401F13FECB7FF25CBC39F4B9FF00D2796BEA0FDAABFE489EB7FF005D6DBFF47A556F007ECFFE1DF04F8B6CBC41A76A7AADC5DDA89364770D1943BD190E7080F463DEBD03E21F842CFC75E14BAD03539EE2DED6E1A3667B72A1C6C70C31904751E9401F04FC20F09DBF8E3E2269DE1FBDB89ADA0BA494B4B0E372EC899C633EEA2BE8FF00F8652F0D7FD07F58FF00BE62FF00E26AA788BE17691F0434A93C7DE1DBDBFBFD4F4C2AB1DBDFB2185FCD2223908AADC09091CF515C57FC355F8BBFE80DA1FF00DFB97FF8E5004BF1A7E02E8BE02F01DCEBBA7EADA8DD4F14B1A08E611ED21982F600F7AF1FF867E34BBF0078A23D734FB5B7BA9D22788473E76E18633C106BB3F88DF1E7C41E3DF0BCDA16A9A6E9705B4AE9217B7590382A723EF391FA573FF047C1763E3EF1DC3A1EA973736D6EF0492EFB72A1B2A33DC11FA5007B07877C7B7DFB405F9F047886D20D32C5D0DE7DA2C4B197747D07CE48C1DDE95EADF0BFE07E8FF0F7C4A75AD3754D46E67681E0F2EE026DC31049E0039F94579EF89FC0563F0034B6F1B785EEAEB50BF4616621D44A98F6C9D4E1029CFCBEB5C9FF00C35578B3FE809A17FDF337FF001CA00FB2EB8FF8A5E04B2F887E1C4D1B52BAB9B581675B8F32DF6EECA8618E4118F9ABE63FF86AAF177FD01742FF00BE26FF00E395E8DF023E37EBFF0010FC6EFA36ABA76996D6EB6925C6FB6593712A5463E67231F37A500799FC78F825A3FC38F07DB6B1A6EA7A85D4B2DE25A94B8D9B406476CFCAA0FF0005719F017C0161F11BC6373A3EA579736B0C564F741EDF6EE255E35C7CC08C7CFF00A57D19FB677FC929B2FF00B0B43FFA2E5AF96BE167C40D43E1C788A7D634AB5B4BA9E6B56B564B90C542B323646D20E7283F5A00FB23E157C14D1FE1BEBF73AAE97A95FDDCB716C6D992E766029756C8DA0739415C7FEDABFF0024FB43FF00B0A0FF00D13255AF801F1A35BF891E2CBED2F56D3F4EB582DEC5AE95AD95C3161222E0EE63C618D54FDB57FE49EE87FF006141FF00A264A00F8CE8AF4FF801F0F74EF88FE2BBFD2F56BABBB682DEC9AE55ED8A862C2445C7CC08C618D759F1FBE0D687F0E3C31A7EA3A45FEA37535C5D8B765BA28542EC66C8DAA39F9680398F801F0E34FF00895AFEA5A7EA97977691DADA89D5ADF6E58EE0B83B81F5AF74FF008653F0C7FD07B5AFFC85FF00C4D70BFB137FC8F3AFFF00D8387FE8D5AF6DFDA0FE246A5F0DB44D2EF349B4B4B996F2E1A161721885017391B48A00E33FE194BC33FF0041ED67F28BFF0089AF23FDA13E11E95F0CEC7459B4BD42F6F1AFA4951C5CEC014285231B40FEF57BA7ECF7F17F59F895ABEAF6DAC5869F6C9670A48AD6A1C162CC473B98FA572BFB707FC82BC27FF5DAE3FF00414A00C4FD88BFE461F147FD7A43FF00A1B57ADFED01F14752F86967A34BA5D85A5E35F492A38B8DDF2840A463691FDEAF23FD887FE460F14FFD7AC1FF00A1357BCFC57F85BA57C4A8B4D4D62F6FAD458348D19B5280B6F001CEE53FDD1401F1AFC5EF8B1A9FC4C8F4B5D534FB4B3FECF690A7D9CB1DDBF6E73B89FEE0FCEBCDEBECBFF8655F08E79D6B5EFF00BEE2FF00E374EFF8654F08FF00D06B5EFF00BEE2FF00E37401CFFEC35FEA7C67FEF59FF29EBEA615E7BF093E15693F0CBFB57FB1EF6FAE86A1E5799F6A2A76F97BF18DAA3FBE7F215E85400514514005145140051451401CB789EF16CAF6CA5BE6B94B2420A35A99D99A5DEB90E910C15DB9FBD907278F5E33E1F5CCD7BE2258EE6E352DB165D617BBBBC8F9768F3229140DBD7AF19C574FE3D4B19A26B7B94D49A578CA86B61B95786653B5CEC2D9438206E046415EB55FC3B3C0BABDB936DAEED99D9636BD8D19518A9917E652586177F27AEF218938A00A3F1EBFE44FB5FF00AFC4FF00D01EBC0ABDF7E3D7FC89F6BFF5F89FFA03D78156D1D8F99CDBF8DF20A28A299E5851451409857BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095133D5CA7F8FF0023D285140A2B33E98290D2D21A005145028A0028A28A002B8AF8D3FF00248FC5FF00F60C9FFF004035DAD715F1A7FE49378C3FEC173FFE806803E04F873A25BF897C71A268B7AD325B5F5D24123424070A4F3824119FA835F577FC32AF82FF00E82BE23FFBFF0007FF001AAF9A3E067FC95FF097FD8422FE75F787C4AD7EEFC2FE04D6F5AB04864BAB1B63346B30250918EA0107F51401E4DFF0CABE0BFF00A0AF88FF00EFFC1FFC6A8FF8655F05FF00D057C47FF7FE0FFE355E563F6A7F1C7FD03BC3DFF80F37FF001DA5FF0086A7F1CFFD03BC39FF0080F37FF1DA00F53FF8655F05FF00D057C47FF7FE0FFE355C97C58FD9EFC2BE0EF87BAC6BBA7EA1ADCB75668AD1A4F34450E5D57902307BFA8A97E16FED0BE2DF15F8FF0046D1352B2D0E3B4BC98A48D6F04A1C0DA4F04C84678F4AF5FF00DA479F823E28FF00AE31FF00E8D4A00F91FF00666FF92E1E18FF00AE93FF00E8892BF40ABF3F7F667FF92E1E18FF007E6FFD11257D9BF193C517DE0BF873ABF8834B4824BBB311144B852C8774C88720107A31EF401DAD15F307C1AF8F5E29F1BFC47D2BC3FAAD9E8D1D9DD094BC96F0C8B20D913B8C13211D5476AFA7E803C9FF006A8FF921DAFF00FBF6DFFA511D7C85F05BC2563E38F88363A16AB3DCC3693A4ACCF6ECAAE0AC6CC305811D47A57DE9F103C2963E36F0ADE681AACB73159DD6C2EF6CCAB20D8EAE305811D5476AE0FC01F01FC2FE08F135B6BBA4DEEB12DE5B87545B896364F994A9C858C1E87D68039BFF008655F067FD05BC43FF007FE1FF00E355D3FC3AF815E1CF00789535CD2350D567BB489E20975246C986183C2A29FD6BD6B68AF3DF8E5E32D43C05E019F5CD221B59AE9278E20B74ACC986383C2B29FD680394FDAF7FE48E3FFD7F41FF00B357CC3F023C0FA77C41F1C1D1B589EEE0B616B24FBAD5955CB2950065958639F4AD1F891F1C7C4BE3FF000D1D1358B2D1E0B53324DBAD22915F2B9C72D230C73E95C9FC35F1D6A3F0FBC44DACE8F05A4D72616836DCAB326D6209E1594E78F5A00F61F8EDF03BC3BF0FFC0E75AD1EF7579EE85D470ECBB9A364DAD9CF0B1A9CF1EB591FB1BFFC95B9BFEC1937FE871D73FF0011BE37F893C7FE1E3A36B167A445686649B75B4322BE5738E5A4618E7D2B96F869E39D4BE1EF88DB59D1A1B49AE8C2D015BA4664DAC413C2B29FE11DE803EACFDB3BFE494D97FD85A1FF00D172D7C4C0F35F4FF83BC5FA87ED09AABF83FC6715AD9E970427515934C568E532232A004C8CE36E246ED9E073EB8FF1FF00E0BF873E1DF82AD756D1AEF559AEA6BF8ED4ADDCB1B20564918E02A29CE5077F5A0067EC55FF00252B57FF00B0449FFA3A1AFA6FE287C3DD2FE23E8D69A66B5737B6F6F6F702E55AD19158B0565C1DCAC31863DABE62FD8B38F899ABFF00D8224FFD1D0D7DA03A0A00F983C65E17B3FD9CF4C8BC4FE0C96E2FEFEFE51A64A9AB11244B1B032120461086CC4A324918278AF1BF8A7F1975DF88FA35AE99AD596990436D702E11ED239118B6D65C1DCEDC618D7DA5F143C03A5FC44D12DB4BD6E7BD82DE0B9172AD68EAADB82B2F25958630C7B57CC1FB42FC1AF0E7C3AF0B69FA9E8977AACF7173782DDC5DCB1B285D8ED9015179CA8EF4016FF626FF0091E75FFF00B070FF00D1AB5F46FC54F86DA4FC49D36CACB5ABABEB68AD2532A1B46456248C60EE56AF86BE17FC45D5BE1C6A9797FA1436334F750F90E2F236750BB8371B5979C8AF48FF0086A6F1C7FD03BC39FF0080D37FF1DA00FA37E16FC21D0BE1B5EDFDCE8779A95C3DE46B1B8BC911C0DA49046D45F5AF29FDB83FE415E13FFAED71FF00A0A5711FF0D4BE38FF00A06F873FF01A6FFE3B5C37C51F8B3AEFC49B6D3E0D7AD74D812C9DDE336713A92580073B9DBD050055F857F13757F86B797F73A25AD85C3DEC6B1C82ED1D800A4918DACBEB5F547ECE7F16B5CF8977DADC3AD59E9B6C963144E9F644752C5CB039DCEDFDDAF9FBF674F869A2FC49D4F5AB6D76E2FE18ECE18E44369222125988E772B7A57D59F0B7E13E85F0DAE7509B41BBD4E76BE444905E4A8E005248C6D45F53401E8A052D145001451450014514500145145001451450079FFC4FB2D2AE6259351D192EE58A191D6E5ACC4BB408E4C26F28C17E623EF6073DFA564F80F47B38B5CB79EDF44B9B7DAD30170FA7DAC4A9B09451BD230C3728E707AE4720D761E26D4E5B59A486DBEDDF6936733466DED5E640E76843C290581078F7E460E69B6375A8DC6B30CCB77FF12C95E4516F2D934522855E39621BAF3CAD00739F1EBFE44FB5FF00AFC4FF00D01EBC0ABDF7E3CFFC89D6BFF5F89FFA03D78156D1D8F99CD7F8DF20A28A299E585145140057BF7C04FF009142EBFEBF1FFF00404AF01AF7EF809FF2285D7FD7E3FF00E8095133D4CA7F8FF23D285140A2B33E98290D2D21A005145028A0028A28A002B8AF8D3FF249BC61FF0060B9FF00F4035DAD715F1A7FE49378C3FEC173FF00E806803E1BF819FF00257FC25FF6118BF9D7DB3F1F3FE48F78B3FEBC5BF98AF89BE067FC95FF00097FD8462FE75F6CFC7CFF00923DE2CFFAF16FE62803F3B3349451401E8FFB3C7FC967F0AFFD7D1FFD16F5F617ED21FF00244BC51FF5C63FFD1C95F1EFECF1FF00259FC2BFF5F47FF45BD7D85FB487FC912F147FD718FF00F4725007C8FF00B347FC970F0C7FD749BFF44495F57FED3FFF002433C4DF4B7FFD298ABE50FD9A3FE4B87863FEBA4DFF00A224AFB8FC75E16B2F1A785EF340D55E74B1BBD9E6340C15C6D757182411D5476A00FCF0F87DE2BBEF0478AECFC41A5456D35E5A890225CAB321DE8C8721483D18F7AF5BFF0086A7F1BFFD02BC39FF0080F3FF00F1EAF5BFF865BF03FF00CFFF00887FF0262FFE355C27C6CF813E16F03FC3CBFD7748BBD5E5BC81E2444B99A3643BA4553C0407A13DE8039EFF0086A8F1BFFD02BC37FF0080F3FF00F1EA51FB54F8DC74D2BC37FF0080F3FF00F1EAF00E6BBBF827E13B0F1C7C44D3F42D5A4B88ECEE125666B760AF958D9860904751E9401E89FF000D55E38FFA05786FFF0001E7FF00E3D5CCFC43F8EBE25F1F78665D0F59B0D1E0B579125DF6B0CAAF95391CB48C31F857D01FF0CB7E07FF009FDD7FFF000262FF00E3547FC32DF81FFE7F75FF00FC098BFF008D5007CDBF027C13A6F8FF00C78BA26B33DE416A6DA49B7DA3AABEE5C63965618E7D2BE8DFF8655F047FD053C49FF81107FF0019AC6F1AF8174BF809A29F19F8364BBB9D55645B409A9BACB0EC93EF1DA8A873C0C735C07FC35378E7FE7CBC3FFF0080D2FF00F1DA00D3F8F1F037C35F0FBC0675AD1AF7589AEFED51C3B6EE68D936B06CF0B1A9CF1EB5E6FF00017C11A6FC41F1D1D175A9EF20B516B24FBED1D55F729503965618F9BD2BD4FC15E3AD53E3E6B5FF00086F8D22B4B6D2BCA6BDDFA6A3452EF8F1B7976718F98F6AF67F871F04BC35E00F101D6B44BAD564BB685A02B7332326D6209E1501CFCA3BD0079A78C7C1FA7FECF3A5A78BBC1B2DD5F6A37128D35A2D599658446E0B92046A8776625E776304F1E991E0CF15DFFED11ABBF843C6B0DAD869B6911D5124D215A294C88446013234836E2627A672073D41EEFF006CEFF924F67FF61687FF00454B5E47FB16FF00C953D4BFEC0F2FFE8E86803E84F861F063C3FF000E75DBAD5343BCD567B89ED9AD996F258DD029656C80A8A73941DFD6A3FDA13E216ABF0E7C27A7EA7A25BD8CF3CF782D9D6ED1D97698DDB80ACA73F2FAD7AA57CF3FB6AFFC93BD17FEC2ABFF00A264A00F32FF0086A7F1B7FD02FC39FF0080F3FF00F1EAE8BC13E21B9FDA37509FC3BE378EDEC2CB4D8FFB4216D214C5233E447863219015C39E801CE39AF32FD9E7E1FE91F113C59A8699AE4D790C16F62D728D6B22A36E1222F25958630C7B57AEF8EFC3F6BFB3A69F6FE22F02BCF757BA8CBFD9F32EAAC258D63C17CA840843650772319E2803A31FB2C7823FE82BE23FFC0883FF008CD1FF000CADE08FFA0AF897FF000220FF00E334BFB3B7C5EF107C46F10EA963AEDB69B0C36B6A26436913AB16DE073B9DB8E6BDF4500780FF00C32B7823FE82BE25FF00C0883FF8CD1FF0CADE08FF00A0AF897FF0220FFE335BFF00B467C4AD63E1BE95A35CE870D8CD25E4F246E2EE3671855078DACBEB5E0DFF000D4BE39FF9F0F0F7FE03CBFF00C76803E92F85BF08B41F86B7B7D73A15DEA970F791AC720BC9637002924636A2FAFBD617ED15F13358F86B61A24DA1DB69F70F7D24A920BC8DDC00A14F1B5D7FBDDF3597FB397C5AF107C49D5759B6D76DF4C862B2863913EC913A9258907259DBD05777F147E1968BF122DF4E835FB8BE896C59DA3FB248A8496001CEE56FEE8A00F9A3FE1AA7C6DFF40AF0E7FE03CFFF00C7ABDA3F670F8A7AE7C4C3E21FEDCB5D36DFFB3FECFE57D8E374DDE679BBB76E76CFDC18C63BD7847ED1DF0AF42F86F6DA0BE833EA129BE79C4BF6B911F1B02636ED55C7DE3EBDAB8FF859F1475AF86A7526D06DF4F9BEDFE589BED71B3E3CBDFB76ED65C7DF39EBDA803F44E8AF15FD9BFE28EB7F12D7C4275D874F80E9E6DFCA167132677F9B9DDB99B3F707A77AF6AA0028A28A0028A28A0028A28A00CCD574D9AF8C4D6DA95E69D247B86FB64858B038E0F988E3B76C566E99E1B169AC2DFCD3437B29C933DC5AC6B70188C64491851D38236FE3EBD2D1401E69F1EBFE44FB5FFAFC4FFD01EBC0ABDF7E3CFF00C8A16BFF005FA9FF00A03D78156D1D8F9ACDBF8DF20A28A299E5051451409857BF7C04FF009142EBFEBF1FFF00404AF01AF7EF809FF2285D7FD7E3FF00E8095133D5CA7F8FF23D285140A2B33E98290D2D21A005145028A0028A28A002B8AF8D3FF249BC61FF0060B9FF00F4035DAD715F1A7FE49378C3FEC173FF00E806803E1BF819FF00257FC25FF6118BF9D7E80F8AF43B4F13F87AFB46D44CAB677B118A53190ADB4FA120E0FE15F9B1E19D6AEBC39AF58EB1A7797F6CB2944D1798BB9770E991DEBD6FFE1A6FE207F7B48FFC043FFC55007B6FFC32FF0080FF00E7BEB7FF008149FF00C6E8FF00865FF01FFCF7D73FF0293FF8DD7897FC34DF8FFF00BDA47FE021FF00E2A8FF00869BF1FF00F7B48FFC043FFC55007D09E12F803E11F09F88EC35BD2A6D55AEECE4F323135C2B2670472020F5AD5FDA3FFE48978A3FEB8C7FFA392BE66FF869BF1FFF007B48FF00C043FF00C5564F8B3E3D78C3C57E1CBED1357FECD6B2BC50B27956E55B860C30777A814015BF668FF92E1E18FF00AE937FE8892BECCF8D1E27BFF06FC35D635ED21606BDB4F24A09D4B21DD3221C8047663DEBE33FD9A3FE4B87863FEBA4DFFA224AFAC3F69EFF009219E26FF76DBFF4A62A00F2DF829F1D7C57E35F895A5683AC43A5AD95D094B9820657F922771825CF751DABD23F6AAFF9229ACFFD76B6FF00D1C95F30FECB5FF25C7C3DFEEDCFFE93C95F4EFED57FF244759FFAED6FFF00A392803E46F82BE14D3FC6BF1274BD075769D6CEE84C5CC0C15FE489DC60907BA8ED5F41F8D3E1CE8BF04BC3F3F8E3C1CD7B26B362CB1C4B7D20962DB2B08DB2AA149E18E39EB5E37FB2CFFC973D03FDCB9FFD2792BE99FDAABFE4896B5FF5D6DBFF00472500703F02FE37F8ABC73F10ED744D662D316D2586590B5BC0C8F955247258FF002AF58F8F3E33D4FC09F0F66D6F455B76BC4B88A202E10BA618E0F008FE75F09F817C5BA9F827C430EB5A21845EC68C8A664DEB86183C67D2BABF1E7C67F1578DFC3EFA3EBA6C4D9348B211041B1B2A78E726803D2BC0BE3AD57E3BEBC3C1BE364B58B4A789AECB69D198A52F1FDDF998B0C73E94CF8FDF05BC31E02F01AEAFA2CBA9BDD9BB8E1C5CCCAEBB58313C051CF15E23E00F196A9E05F102EB1A1F91F6B58DA2FDFA6F5DADD78C8AE9FE20FC66F14F8F3421A46BBF60FB209566FDC41B1B728207393C726803A1FD8FFF00E4AFAFFD83E7FF00D96BEE51D2BF34BC01E32D53C09AF0D6342FB3FDB044D16678F7AED6EBC647A57A67FC34DF8FFD748FFC043FFC55007D6DF123C0DA57C40D0A1D275C7B94B58EE16E41B7708DB80651C9078C31AF0EF1FF0085AC3F67CD1E2F167814CD36A7773AE98EBA9389A3F29D5A4242A853BB7429CE7D78AF3AFF00869BF1FF00AE91FF008087FF008AAE5BE227C62F13FC40D0E1D2BC41F61FB2C570B72BF6780A36F55651CE4F1873401F417ECEBF18BC49F10BC5FA8699AFC7A7A5BC162D729F668590EE1246BC92C78C31A5FDB53FE49DE8BFF6155FFD132579B7EC57FF00253757FF00B0449FFA3A1AFA87E22F80F46F881A4DAE9DE201726DEDE71709E449B0EE0A579383C618D007C15F0DBC7DABFC3CD62E753D016D5AE67B736CDF698CBAED2CADC004739515EDDF0F75FBCFDA1B55B9F0F78F0451585843F6F84E9CA617F30304E4B160461CF18F4ACEFDA43E10F867C01E11D3B53F0F2DE8B89EFD6D9FCF9B7AED31BB703039CA8A8FF628FF009281ADFF00D830FF00E8D8E803E86F86BF08BC3DF0F353BBBED065BF79AE61F25FED32AB8DBB81E30A39C8AC4FDA3FE226B3F0EB42D22EF415B4696EAE5A27FB4465C602E78C114BFB497C40D6BE1F786F4ABDF0FF00D97CFB9BB30B9B88F78DA109E0647A57C9DF123E2BF88BE2169D656BE21FB17956B29963FB3C250E48C1CF27B5001F137E2C7887E22DA58DB6BF1D82C766ED247F6688A1CB000E72C7D2BCF0D7B2FECDBF0F344F887AD6B36FE2017262B481248FC89761C9620E783E95A9FB4BFC2EF0F7C3DD3F419BC3CB761AF259525F3E6DFC285231C0C753401D07EC41FF0023078A7FEBD61FFD0DABD4BF692F893AE7C38B1D126F0FA5933DF492A49F6988BE0205231823FBC6BE49F86BF1175BF875757B73E1FF00B2F99788B1C9F6888B8C29246391EB567E247C52F107C43B7B08BC43F63DB64EEF17D9E1F2F960339E4E7EE8A00F67F877349FB46B5FC5F101BCA4D0BCB7B5FECC1E4E4CDB83EF2DBB3FEA9718C77AEE07ECC1E02C7FAED73FF0293FF88AE17F61CFF8FBF18FFB969FCE5AFAC074A00E17E17FC31D0BE1BAEA4BE1F7BE717E63329BA9439F9376DC600C7DF35DD5252D001451450014514500145145001451450079A7C79FF9142D7FEBF53FF407AF02AF7DF8F3FF002285AFFD7EA7FE80F5E055B4763E6B35FE37C828A28A67941451450015EFDF013FE450BAFF00AFC7FF00D012BC06BDFBE027FC8A175FF5F8FF00FA02544CF5329FE3FC8F4A145028ACCFA60A434B48680145140A2800A28A2800ACCF11E916BAFE877FA46A0AED677B0B412846DA76B0C1C1ED5A749401E2DFF0CD5F0FBFE7DB52FF00C0B3FE149FF0CD1F0F7FE7DF53FF00C0C3FE15ED7450078A7FC3347C3DFF009F7D4FFF00030FF851FF000CD1F0F7FE7DF53FFC0C3FE15ED7450078A7FC3347C3DFF9F7D4FF00F030FF00851FF0CD1F0F7FE7DF53FF00C0C3FE15ED74500796F83FE05F837C25E23B3D734786FD6FED0B188C972597E652A7231E8C6BB5F1AF86AC7C5DE1BBBD0F57591AC6EF609046DB5BE5757183DB9515BB45007977833E08F843C1DE23B3D734582F56FEDB788CCB705D46E42878C7A31AEC7C6FE16D3BC67E1BB8D0F59598D8CEC8CE227D8DF2B061CFD40AE828A00F2FF067C12F08783BC4969AE68B15F2DF5B070865B82EBF32953C63D09AEBFC6FE16D3BC67E1C9F44D69656B19D919C44FB1B2A430E7EA0574349401E2A3F669F87DFF3C754FF00C0C3FE147FC3347C3EFF009E3AA7FE061FF0AF6AA5A00F14FF008668F87DFF003C754FFC0C3FE147FC3347C3EFF9E3AA7FE061FF000AF6BA2803C53FE199FE1F7FCF1D53FF0002CFF851FF000CCFF0FBFE78EA9FF8167FC2BDAE8A00F14FF8667F87DFF3C754FF00C0B3FE147FC3347C3EFF009E3AA7FE059FF0AF6BA2803CF7E1DFC22F0BFC3FD627D4FC3D1DDADCCF01B67334FBC6C2CADC0C7AA8AF41A5A280395F885E05D17C7DA55B69DE218E692D609C5C288A428770565EBF46358FF0FBE12F863C03A9DCDFF8762BB4B8B88BC97334E5C6DDC1B81F502BD0A9280392F88BE01D17E2069F6963E214B86B7B69BCE410CBB0EEDA5793F8D707FF000CD3F0F3FE7DB52FFC0B3FE15ED54500705F0EFE16F86BE1F5DDE5C78722BA496E916390CD3171804918FCEACFC47F875A17C4082C61F10A5CBA59BB3C5E4CA63E580073EBD057698A2803C57FE19ABE1F7FCFB6A3FF008167FC28FF008669F87DFF003EDA8FFE059FF0AF6AC51C50070FF0E7E18F87BE1E497EDE1B8EE50DF041379D31933B37631E9F78D771471466800A28CD140051451400514514005145140051451401E69F1E7FE450B5FF00AFD4FF00D01EBC0ABDF7E3CFFC8A16BFF5FA9FFA03D78156D1D8F9ACD7F8DF20A28A299E5051451409857BF7C04FF9142EBFEBF1FF00F404AF01AF7EF809FF002285D7FD7E3FFE8095133D6CA7F8FF0023D285140A2B33E94290D2D21A005145028A0028A28CD002554BFD42D74F87CEBDB88A08BFBF23851FAD731E3CF18C1E18B4F2E3512EA128FDD459E17FDA6F6FE7F991E31750EBBE258AEF56B969A786DD4BBCB21C220FEEA8E9F80AB51B9E6E2B308D17C905797E5EA7BAEAFE31D234BB1B4BCB8B82D6D760985E342C180EA7F5A9A0F14E90FA65A5FB5EA436974C5627981404824739E9D0F5AF22F1A8C7803C218FF9E527FECB4ED62269BE18785228F9796E9D17DC96702851317984D4E4ACB449FDE7BA413C73462485D5D1B90CA720D4B5F39586A9AF780F54104C1D22279824398DC7A8EDF88FFEB57BA785F5FB3F1169897962F91D1D09F991BFBA686AC74E131B1C43707A497436A8A074A2A0EF0A28A2801B4B5E73E3CF89B6BE1AD4FF00B3EDECDAF2E900690799B02023239C1E71CD731FF0BBA4FF00A0127FE051FF00E22B275E11766CF428E558BAD055210D1F9A3DB68CD7CFB37ED0CC8EC91F86D5C038CFDB71FF00B2547FF0D1337FD0B09FF81FFF00DAEBBA385AB35CC91E4D4AF4E9C9C64F547D0D9A5CD784DAFC769A78C39F0EA203FF004F99FF00D92A7FF85E32FF00D0053FF02FFF00B0A9786A89D9A39DE63875F68F6FA2BC4ADFE386E9D05CE86C9167E664BAC903B900A0CFE75ED48C194303904641F5ACE54E50DCDE86269E21374DDEC3E8A28A8370A2993CA90C6D248C151465989C002BC87C5DF150A4ED6DE1F456038375272A7FDD5FEA7F2ACEA558D35766556B4692BC8F60CD377AE6BE5DD47C4BACEA419AEF53B9218FDC572ABFF7C8E2B3A1BDBB8E4DC97332B7AAB906B99E362B6479F2CD609E91B9F5AF5A2BE6AD23C77E20D31C15BF7B94FEE5CFEF07E6791F9D7B0F827C7765E224104B8B6BF51930B36437BA9EFF004EB5A52C542A3B6C7450C7D2ACEDB3F33B5A280723228AE93B428A283400D7709F7AB3BFB774AFFA08D9FF00DFE5FF001AF25F8D5AEDCB6AC9A4C6CC96D122C8CAA71BD8E7AFA8181C579A29392472C6BD3C3E5B2AD0E76EC78D8ACDE342A3A6A37B1F52FF006EE97FF412B3FF00BFC9FE347F6EE97FF411B3FF00BFC9FE35F2F41135CDCC50C637492B845C9C6493815BDE24F07EA9E1EB38EE750487CA77D83CB7DC41C13CF1ED572CB611928CA7AB3359BCE51728D3BA5E67D07FDBBA57FD04ACFF00EFFA7F8D4F6BA959DDB95B5BA82661C911C8AC47E46BE53E3D2A4B69E4B69D2682468A54395743823E86B479476919C33B727AC3F1FF00807D694573BE05D5A5D6BC3163793E3CE742B263A16562A4FE38CFE35D0D78D38B849C5F43DEA73538A92EA2D145148B0A28A2800A28A2803CD3E3CFFC8A16BFF5FA9FFA03D78157BEFC79FF009142D7FEBF53FF00407AF02ADA3B1F359AFF001BE414514533CA0A28A2800AF7EF809FF2285D7FD7E3FF00E8095E035EFDF013FE450BAFFAFC7FFD012A267A994FF1FE47A50A28145667D30521A5A43400A28A051400553D42E92CAD27B998E22850BB1F40064D5CAE43E29DC35B781754643866558FF067553FA134D2BB32AD3F674E52EC99E41A4DB5D78E7C6723DC3384918C9330FE08C7451FA0FC6BD83C5D67069FF0FF0053B5B58962862B4655451C018AE47E03DAC62DB55B9206EDE91027B0009FEA3F2AEEFC6F03DCF84B548610A65781954330504FD4F15A49FBD63C9C1D2FF669D67ACA499E557D0C1E23F0A786F4ED3F50B217D6A8EAF0CD2143C8FA7FB345FDE58AF873C2FA4C37D6D75736D79BA510312AA0B13D71FED0AC8F0BF87B54B5D76D257B75F2D776764A8E7EE9EC0935574BF0E6A49A9DA31811635990B1FB446300303FDEABB58F363567FCBABB2FBAC7BA78BBC396BE24D25ED674512819865C731BF63F4AF1EF87BA9CFE18F190B1B825229DFECD3231E03670A7F03FA135F4128F96BE79F8B96E2CFC752C901DAD224730C766C633F9AE6B38BBE87A79943D8F26221BA7AF99F445155AC25F3ACE0941CEF40DFA559ACD9ECA77570A4738527DA96B07C6DA87F66F85B53BB53B5E3858A9FF0068F03F522949D95CB845CE4A2BA9F39F8A2D6FB56B8D57C49B10E9F25EB44AE5C64F6500753C63F2AC3F14E9B75E1E925B5BF458E758C49B4306C061C74EFC8AF43B35D2750F04F85ACC6B1616F0DACED717C92BED73863C05EA78247E556EE350F096A4973A96B634F9E6BB1733FEFA7D8EB1C402C718E47CCDF2903AF5F4AE0A74A33AB17E773EB6B6633A386953E5D1269696B74577F79F3A938A58D72EAA3F88D7BADAE97E0713AC52DC6853B5941676CDE6CF1A46E09DD712FCA57CD7C1DA3924114905978006933AD9C5A4CB2BDADD5CC4EF707CD0ED27FA3A005B8210F208C8C0F7CFD67D71256E567E6B530F295DB9239ED3FC01AEDDD9DB4B690C044F17990C4F3A2BBA8EE149CD730E8558AB02181C107B1AF60BDF16E99A778BF4CB0D3E2B47FB2C11582EABE71658A3206F651F77201FBDCF4E78A7369DE15975ED16D20B2D3A67BABF955C5BDC198F928AC10BE18E09CAB1FA73DEB93DABBDDA38658484FF00872D767FD7A9E63E16B0FED4F11E996446566B8446FF00733F37E80D7D7EA3F2AF0EF87DA769B3FC4F29A342BF60D26D99565CE4CD2FDC2E7EB96C76C0AF73AE6C44F999EA65587F63096B7BB0A28A2B9CF54F19F8D7E2795AE5741B490AC41564B92A70589E550FB6307F115E4F5B1E349E49BC5DAC3484922EE45FC03607E80568FC39B9B35D7C5A6A7047359DEAFD9D8483EEB1FBA7DB9E3F1AF1AB4DD5A9667CCD69CABD7716FAD91CBD15EB1AAFC23964D4C369B7B1C760FC91202CE9EC3FBC3EA457496FE1AF0B782ACD6EF513134B9C09AEBE7627D157A67E83354B0B2BFBDA2348E02A36F9B44BA9E51E1DF056B5AE38305B18203FF2DA71B148F6EE7F0AECD741F0CF81648AEF58BC7BED5A321E3822F9707B10A0E47D49C7B550F16FC4FBABD0D6FA0A35A5BF43330FDE37D3B2FF003FA579C493493C8D24F23CAEC7259CE49A5294292F7357DC2552961FE05CD2EFD0FA5FC19E248BC49A47DB228CC2CAED1BC65B76D23DF03B60FE35D20E95E39F00E672FACC2C494C44C33D8FCE0FF9F6AF6119FC2BD3A1373A69B3DAC2CE552929CB763A8A28AD4E83E7DF8C3FF23A4DFF005C63FE55CF681FD89FBDFEDDFB77DE1E5FD94A7E39DDF85743F18BFE4749BFEB8C7FCAA8783F45F113C8B7DA2D8B9217E5925550A411838DFC1AFA5834B0D1BBB69DEC7C8545278A9595F5ED734B4CFF008437FB56CC4035BF37CE4D9BFCADBBB70C67DB35E8BF154E96BA0DB1D6BED7F671382BF66DBBB76D6EBBB8C75AE46EA4F885647CC92CA36039062B785C8FC1726B322F1778BAF755B7D3AE0849E5955024D6A80827BE0AF6E6BCF9D294E4A6A49DBFBDFF0000F4A9CE3084A1CAD37FDDB1981FC11E9AF7E71572F2F95E73F95BBCB2C4A6EEB8ED9F7AF69F8C17305878752CC46825BB70A4AA8076A90C4FE781F8D789577E06F38F3ABFCCF331D0F653F67A69E47D0FF08FFE444D3FFDE97FF46357655C6FC22FF91134FF00F7A5FF00D18D5D957CFE23F8D3F53EA70BFC187A2168A28ACCDC28A28A0028A28A00F34F8F3FF2285AFF00D7EA7FE80F5E055EFBF1E7FE450B5FFAFD4FFD01EBC0AB68EC7CD66BFC6F90514514CF2828A28A04C2BDFBE027FC8A175FF5F8FF00FA025780D7BF7C04FF009142EBFEBF1FFF00404A899EAE53FC7F91E9428A051599F4C14869690D0028A281450015CC7C46B13A8783B5481465FCAF300FF7486FE95D3535F6B0218669A76667561ED20E3DCF1CF813A9C6B71A8E9AE7124816741F4E1BF9AFE46BD17C7314937847558A1479246B760AA83249F615E31E29D32EFC0DE2F4BAB0F96DBCC32DBBF5057BA1FCC823D2BD8FC23E29B0F12D9096D1C2DC281E6C04FCC87FA8F435A3DF98F270534A12C2D4D1ABAF54CF08F0FD9CFA7F886C64BE824B652CD869D4C60FCA7B9C7B541A069D7F36A3613C36772D119D1B7AC4DB48DC3BE2BD1FE3C7FC7BE91FEFCBFC96BADF85BFF222E95FEEBFFE8C6AA72D0E3A5814EBFB1E6F8753AD1F76BE74F1ECFF00F0907C419A1B360FBA54B446CF191807FF001E26BD1FE22F8EA1D1EDE6D3F4A9564D4E4054B2B6441EE7FDAF41F89F7E63E0E786A4BBBF3AE5E4444316561DFF00C6DD0B7D073CFAFD2B38E9A9D78DA8B1538E1E9EBAEA7B45BC6B14491A0C2A8C0152D03A51507B495B410D7997C78D4BEC7E128ED41C35DCC10FFBABF37F30B5E9A6BC07F682D4BCED7EC6C01F96084C98F77FFF0066B2AEED4D9E9E5147DAE2E1E5AFDC796AF6AC4F10C9BEE123FEEAD6D8E00AE5F5194CD772B7BE07E1C5191D173C473BE87B7C635FD9609423BC9AFB8EDBE0DF87EDB5EF10DD35FC11C905A5B3CDFBD50D106EC5D772965C67804738CF15EBFE1AF07F87A38E29469962B2DC4C6774F219996224460001A4014B2B1FBD8E73C76F9EFC37E22BCF0E9BB360968EB75118264B88448A509C91835D758F8E3579FC3C9673C7A7981AD56CF8B55C9893EE027DBA8F735F418AA3539AF17A1F9AFD6214E9FBCAECF51F0C68BA24FA75C6AB75A6DA1B6B89EE2ED55E34DB1DAC7950BCB0DB96E72011EBEB55FC549A7787B425D52C2D2DACF58BCB5F26230F94A5549C170165209604E1D436075C570B69E3BD62DEC45A136725B0885B847B58F88FFB9C0C907BE6A96B9E26D4B5F8218B55952610BB3C6E230A537632A08E8BC0E3DAB8BD9CEF76734B174B95A82D5FE67AC7ECF7A718B49D4EFDC0CCD32C2BF45193FABFE95EC03A571DF0BB4C3A5F81F4A85C112491F9EF9EB9725B9FC081F857635C951DE47BB83A7ECE8C530A0D141A83A4F9DBE2E68EFA678B659D1716D783CD43DB77461F5CF3F8D7168C410CBD457D43E2CF0EDA78974B6B2BC0463E68E45FBC8DEA2BE6DF11E8F73A06B12E9F72559A3C10EA78653C835E562693A72E6E87CFE3B0DEC66EA2D9FE67A14FF15EE1744B782CED4FF696C55966908D81B1D540EBF8E3F1AF39D5754BED56F1AE352B892798F1963D07A01D00F615528AE79569CF46CE5AB8AA951252614515E89E01F87771A94EB79AD44D0D8A90CB1370F2FB11D97F5FE7442939BB2269519D6972A4765F05746361E1D92F2542B2DEB865CF5F2D4617F33B8FE22BD1A990C490C6A91A8555180076A7D7B54E1C91513E9E8D3F6705116929692ACD4F19F1769F1EA7F172DAD6E01681A356700672150B63F1DB8AE4B5AF18EB1A85CB3457D2DA5B8E23860631851D871D6B7BE265F4DA6FC445BCB6204B0A44E33D0F1D295ADFC17E246370B7B2E8976E77BC4794CF7C64631F423E82BDDA6F923194E37565D2F63E5EA272A938C6567CCFADAEBD4E7F49F1BF8834D915A3BF9675EF1DC9322B0FC791F857B6F8675496F3428F53D72D63B27E5C163D171F7B9E573CF1E95C1D945E04F0D1FB4B5DFF0069DE272B805F07D801B7F3AE53C6BE37BEF12C86140D6DA7AFDD801FBDEEC7BFD3B5455A0B1534A94395757B1AD3AEF09172A93BBE893BFDECF53F15F842C7C5ED1DF437CE92797B51A3C49195CFA7F81AF38D63E1AEBD61BDE08A2BC8579DD03FCD8FF74E0E7D866B98D235BD4747977E9B7B340FE99CA9FAA9E0D7A47877E2C950B16BF6A5867FD7DB8E3F15FF000FCAABD8E2F0BFC3778F617B7C262DDEAA6A5DCEC7E16413DAF82ECE1BB8A486656937248A548FDE37635D8567E8DA85A6AB631DEE9F2896DE4CED7008CE0907AFB835A15E2CE4E53936ACEE7BF462A14E318BBAB0B4514549A85145140051451401E69F1E7FE450B5FF00AFD4FF00D01EBC0ABDF7E3CFFC8A36BFF5FA9FFA03D78156D1D8F9ACD7F8DF20A28A299E505145140057BF7C04FF009142EBFEBF1FFF00404AF01ADBF0D789F55F0E4BBF4CB9291EEDCF0BFCD1B9F75F5F7183512D4EDC0622342A73CB6D8FAB4515E57E19F8B7A7DF6D8B5B84D8CFD3CD5CBC47FAAFEBF5AF4AB1BBB7BD8166B49A39E26E8F1B0653F88A8B1F4F4B114EB2BC196A90D2D252361451451400507A51450064EBFA2D9EB962F697F109226E41EEA7B107B1AF1AD77E1FEB9A15D1B9D21A4B98D7949206DB32FD71CFE59AF7AA31551958E3C4E069E2357A3EE8F987C45AB6B37F1416DAE3CADE413B7CE8F6B0CE33938C9E83AD59D3356F135D69D0E95A4B5EB5AC60854B48C838249396033D49EA715F499453D547E54A140E800AAF69E470ACA24A6E5ED1EBF7FDE78C784FE16DCDC4A975E222218739FB3A365DFD998741F4E7E95EC36B6F15AC0905BC6B1C4836AAA8C002A7C5150DDCF4B0D85A7878DA0BE628A28A291D02572BE26F04E8DE22BA4B9D4ADD9A641B43A39538F438EB5D5D2115328F32B32E9D59D27CD076679D8F84FE19CFFA99FF00EFEB5559BE0AF83A690BBDB5C863E93B0AF4EA0D5535ECDDE1A1588AD5312946B49C92EE7969F821E0DFF9F6BCFF00C096AB36FF0006FC276E816382EF68EC6E58D4BAAF8AB57B43753C6F0ADB446EE4291E9E6631C504AC8CECC674C93B73803BD431E8765E34D735ABC9B50BF8A3B5BB58234884602EC8D33F7918E376EE3383E9C9AD9D6A8FED1C8F0F49FD944FFF000A8FC2DFF3C2EBFEFF00B54B67F0B3C2F677714C2CA49994E5566959973EE3A1FA1E2B6BC070B5A787BECC6792E0437B7910925C6E2AB732819C0038000E0015D2547B49F5647D528FF22111028181F41E94EA28A83A428A28A000D79A5C7C386D63C4F79A9EBB701ADDE4CC5044C725470371EDC01C0FCEBD28D15138467A332A94A352DCC8E36F3E1BF86AE5306C046718DD13B29FE75423F84FE1D57DC45DB2FF0074CBC7E82BD068A5EC69F621E1A93FB28C0D1FC23A2E8CC1EC2C218E41C6FC65BFEFA3935BCAA00E38A5A2AD454764691A7187C2828A0D14CD05A0F4A28A00F36F89DE0C9F5C923D434DC1BC8D36344C70245078C1E80F27EB5E6A7C13E24FFA055CFE6BFE35F49639A2BBE8661568C7916A8F36BE594AB4DD4774D9F357FC213E24FF00A05DCFE63FC68FF8427C47FF0040AB9FCD7FC6BE95A2B5FED6ABFCABF1FF00330FEC5A5FCCCF9A7FE108F1177D26E7FF001DFF001AB9A5FC3EF105E5D2C7259B5B45FC524C54607D33935F455149E6B55AB597E3FE63593525D5997E1ED2A1D1B48B5B0B7C98E04DB93D58E7249FA924FE35A94515E6B6DBBB3D68C54528A168A28A450514514005155AFAEADACE032DE4F14112F57918281F89AF35F13FC5BD3ACB7C5A2C46FA7E9E61CAC63FA9FD3EB418D4AF4E92BCDD8B1F1EBFE452B5FF00AFD4FF00D01EBC0AB6BC49E2AD5BC47213A95D3347BB72C31FCB1AFD17D7DCF358B5B4763E5F1F888E22A734028A28A6718514514005145140056868FACEA3A34FE6E997935BB770A786FA8E87F1ACFA2A6C5427283BC5D99EBBE1CF8BF3C6562D76CC48BFF3DADB86FC54F07F023E95E9BE1FF14E8FAE28FECEBF8647EF193B5C7FC04F35F2AF7A72332B6E5DCAC0E4107A54F29E951CD6AD3569EA8FB168AF99F44F887E23D295634BDFB5423F82EBE7FF00C7BEF7EB5E87A27C61D3E6DA9ACDA4B68E78DF1FEF13F11D47EB53CACF568E6546AEEEDEA7AB51591A46BFA66AE33A75F413F192A8FF0030FAAF515AD458EF8C94F542D149452285A28A2800A28A2800A28A2800A28A2900514514C0F1DF1D4F6D35AC8F1C296A90C1A93CC8DA8980CCA93912281B0EEDE416DA7039AC982E7C41657D751787EF5ED926D4D16432CD02994991237211A063962490C3E5508D9191F37A8CFE145925768B54D42185E579442B15B3AA33B166C17899B9624F5FD2ACEA1E1EFB5DF0BA8752BBB4700616186D9B691DC17899B3F8D0053F87427FF843EC27BA72F2DD996F09660CD89A5795412AAA32038070A06735D4D53D2AC23D374BB4B180B18ADA14850B1C92AA0019F7E2AE50C428A292945001494B49403168A28A06145145001451450014514500145145001451466800A28A2800A28CD140051499ACAD5F5ED33478F76A57D05BF7C3BFCC7E83A9A09724B766B668CD7956BBF1874EB6DD1E936B2DDB8FF968FF00227E5D4FE95E79AEFC45F11EAC8D19BDFB2C27F82D86CE3FDEFBDFAD34AE70D6CCA852EB77E47BD7883C57A3E8484EA37F146C06446A77487FE0239AF30F127C61964DD168366225FF009EF71C9FC1471F993F4AF253F31249C93D4D34AF157CA7935B36AB5348688D1D5F5AD43599FCED52EE6BA61CA876F957E83A0FC2B3E8A2A946C79B29CA6EF2770A28A282428A28A0028A28A60145145001451450014514500145145021F0CAF0C824898A38E4329C115D5E89F10BC47A5600BE37510FF96772378FCCFCDFAD723454D8DA15A74DDE0EC7B468FF0018E16DABABE992467BC96EC1C1FF00809C63F335DAE91E3BF0F6AA42C1AA40B27FCF39731B7FE3D8CFE15F31514729DF4F35AD0D1D99F624720750D1B0753C820E69F5F2369FAB6A3A7366C2FAE6DFD44721507EA075AEB74DF8A9E24B2004D341783D278C03F9AE2A394EFA79C536ED24D1F4652D78FE9BF1A202A06A9A54A8DDDA09037E871FCEBA3D3BE297866E71E65CCD6A4FFCF688FF0035C8A56676C31B427B491DED158FA7F88F47D4702CB52B39D8FF000A4CA5BF2EB5AD91458DD4E32D98EA28A3348B0A28CD1400514514001A4A5A281094B40A3340C29297345001451450014519A33400514668A0028A28A002929ACEAA0966000F5ACAD43C43A469E48BCD4ACE16FEEBCCA0FE5D6826538C7766BE69335C36A1F14BC3169911DCCB74E3F86088FF0036C0FD6B9AD47E33C0B91A669523FA35C4817F45CFF3A695CC258CA30DE48F60151C8E23059D9557D49C57CF1A9FC54F125E02B6F3C1680FFCF18C67F36CD721A9EADA96A926ED46FAEAE4F502590B01F41D05572338AA66F4A3F0A6CFA3F57F1DF8774D0C27D4E0771FC10E646FC9738FC6B89D5FE32C2819348D39E43FF3D2E5F681FF000119CFE62BC628A7C879F3CD6B4B6B23AED63E21F88F542C1EFBECD11E896D98F1F88E7F5AE4E49249599A5919DD8E4B31EB4DA29A8A3CFA95AA54D672B8628A28AA330A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800A28A2800ABF69AC6A7678FB16A1776E0768A6651F9035428A0A8CE51D99D6597C43F13DA6026A72483D258D5F3F98CD6DDAFC60D7E1C09E0B19BD494653FA1AF38A29591D10C6D78ED267AF5A7C687E05DE8CA4F768E7C7E854FF003AD7B7F8C9A437FC7C585F47FEEED61FCC5785514B951B2CD710BADFE47D0B0FC59F0D49F7A4BB8BFDF809FE59AD18BE2478565E9AAAAFFBF0C8BFCD6BE69A28E5468B37ACBA23EA28BC6FE1B97EE6B569FF00027DBFCEACA78AF406FBBACE9FFF007FD47F5AF9528A3951B2CE2A758A3EAFFF00849B43FF00A0C69DFF008129FE3527F6FE8FFF00415B0FFC084FF1AF9328A5C886B3997F21F59FF6FE8FFF00415B0FFBFE9FE34DFF00848F455FBDABE9FF00F8129FE35F275147220FEDA97F27E27D56FE2BD063FBDADE9DFF008109FE355A5F1BF86E3FBDAD599FF75F77F2AF9768A7C88979CCFF00951F4BCBF11FC2B1F5D594FF00BB0C87F92D675C7C57F0CC5F767BA9BFDC848FE78AF9E68A39510F38ABD123DD6E3E31E903FE3DEC2FE5FF0078228FE66B2AE3E33B9C8B5D1957FDA7B8CFE8147F3AF1FA28E54632CD2BCBAD8F47BBF8C1E209722082C6107BF96CC7F53FD2B12F7E21F89EF339D51E303B448A9FC866B93A28E54612C65796F365EBBD6352BEFF008FBBEBB9FF00EBACACDFCCD52FAF5A4A28B184AA4E5BB14D25145512145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451450014514500145145001451562EECE7B4481E746549E312C6720865248C8C7B83415CBA5CAF450783566DEC6E6E2D6E6E208CBC36CA1A5208F941381C753C91D28128B7B15A8A2B4E5D035886DFCE9B4CBC48BE5C48D0B6D3B88039C77247E740D4252D9199456A5DE81AC59DB3DC5E6997704298DD24919551938E49F7354ECACEE2F6EE2B6B48DA59E438555A039249D9AD4AF453E446490AB0C11C11EF4B6D04B75750DBC0BBA695C468B9C6589C01F9D04B4EF623A2AD4161753CF711450967B78DDE5008F9557EF1FC2AAD03716B70A2ACC961751CF6F0CB0BC725C0478830FBEADF748F634DBCB1B9B3765BB85E365764C91C6E538600F43834072B20A28A281051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451401DC7806F6EECACBED297B7FE45BDCC51A595B26E12B481CE08DC3FBB5D243A85C94D62D62D4B5A9A78E540E5248E46B6504E76812648E80B738AE1B46BE6B3F0C5FFD9EE7ECF77F6B81A3D926D72A1650C4639C7CC01FAD74375E2AB6D7679ED219AE2DDAE6E56556BD99121842EE2CAA40E320E307AE054B3D4A35211A7157FEB5306F03CBF1052D353B892FB65FADB34929C974126DC1FC2BA8BDB4D76D2FAFA2B0F0C698B6CCEF1EED801963DDC06FDE0CF41F956159DD69CDE3FD4B57B8B953656D7125E4439CDC10C4A2AFB93B7AF6CD72573335CDC492CBF7A462EFF5273418CA71A69BDEEFA1D8F8CEC5ADBC3BA6CF77A65A69F7AF3CAAEB6C800651B719C13EA6BABD46DFCB6D566FB05DC45C69E3ED2EDFB9930F1708BB4631F535E516AC269EDAD6E2E1E2B332866249658F380CC17D71E9E95DC0D66D2E26D72F5EFA355BCBC863820E772A24AADBCF180368F5EB9A0BA5522EFD3EE2F788B4AFB2DA789922B9B47B9BB2F7521F3242440B3F0A1766DDDBC6DFBDFE358FE0B17FA55AA6A167169B6F348D85BEBDB9401501F99163CEEC9C609C138E9D6B4B5ED4F4B9B4CD474C5D4E36B99A69A74B948CB4622F34BADB9239E4E5B20633815CFF00851FECB666E6E35D8AC6CDA4FDEC318F32E2423B05C719E9B89C50CAA8E2AB465E5D19DA6B77F22437B6BA4EB67ED77B1A968F50BC7558A265071097001CE4FCCC4301DB9CD727E0DB27D3BC5B0DB5D4DA5A4893C3BC4F898B12C0811950C3774E72315B1A5F8B26D475FB8D5750D46DED74D8D9B6D8C996775D84200029CF38C9C8AE4FC2B3C76325F6A46E04135B427ECC9B559DE52405C0604607249EA31C5087567194A324EFABFB9791DD692A26B8F11CDA57D867B84468444DA7247BDDF80149241E8DF29FBDE86B98F1159DB41E31335ED9491E905E20E2D63080E6256DA0700139CE3DCD44F2DADC59DAD8CBAA32DB35B3DD8200522F39E242065BEE900F5018525C6B2AF6DE1A91B529D56D062489407781D48C3AE400411B700F4DA450454A9194527D35FC7B1D1DD45A7DD7892346B6BAB8BD465BC9A506385A1C6CDB09C92AAA8B8CF53938E39A87C730281AC46458411A5CC930DF7724D26F6390422FC88580E8DD0566DF6A561E206297BAA6A091424C867BE712123A154891400C78EADDAAFEB9ABE99E20D2DDA2D4DB4F8A156335A4880CB772280B1C84A8018900641FBB8CFBD3B9A39C250695B5F4388874BBC9B4BB8D46287759DBB2ACB2EE1F296200E3393D474154AB5A0B8D3E3D0AF6095AF3EDECE9E50523CA2011F7C773D71F8564D33CC9A4B636FC39630B79DA9EA6ACDA759105933833C87EE443EB8C9F400D75DAB6DB3F10F8BB568E18C5C59C710B6050111B49B1778078C819C571BA7F88753D3EC7EC76D3C5F650E6411C96F1B80C7827E653E95BD7DE318F51D67507BE8247D2EF6210BC718459100DAC1810305830CF39E38A4CEBA3529A8D93D7F5B3342CA38B535D1F5BD4A18E79D2DAF24B852802CED6E328580E09F9803EBB6B2EE65BAF1478744D72AB2EA715FA5B4522A2A164954E10E00E032F1E993511F135A5ADE69A9A75A4AFA659C7244D15C300F30972242C47032080319C60524DAFD8E9D0D941E1E86E84705E2DEC8F76577BBAF0ABF2F1B40CFD73DA8345384B46F4EBF722F789B488974E3A768D796B731E92864BB8A3DC24793A4927DD0180385C027007D6A5D174FB5D2B42D66DAE63DFABCDA63DC37FD3BC794D898FEF36EDC7D0607AD67B6BDA458B6A573A25BDF2DEDF46D1117050C702B905B6E396F419C71EB4FD3FC75A8A35FB5FBC734B716EF1A30B5841F30904337CBC8E0F073F4A62E7A2A7AEFE5AAFEAC745A141A8FF00C53D0E8D6B0BE832C119BE6F291D19BACDE6B11C11C8009F4C565E8D75A758E9779F60D5ED34D9A7BF7F2CCD079C7C851F202369DA09CF27D3BD733A66AEB63A56A5142AFF006FBDC42640005487A903FDA63C74E99F5A974EB9D09F4EB78758B5BC49A091889AD0AE6653FC2E1BA63B11EBD281BAB093497E3FA7C8E834F2DA4EA5E21935CD4AD2CF550628A399A21286DCC18B2205C105547381C3559B582F4EB975349359EA1A8B69DE769722C6A124CB7554C01BC0DFC119C8F6AC1BBD774BD66FB50935AB29A28E7646824B520BC2A8BB021DDC30231E9C8AA9AD6A965A8EA1A7C71C3716FA4D9C6B022821A52818B33761B8927DBA502F6904BD1E9F7BDCEC841E6DCF869BC55041FDAD2C939613A2A19102FEEC4A38EAFC723A5666BE9AA49E10BA9FC556C23BC4B98D2C0989637FE2F300000F9318F6CD72FE25D55B58D51EE42F97128F2A08F3911C4BC2A8FA0FD7352F88F558EFC59DA59F989A75944238564203331E5DD8027963EFD00A053AF0B4AFF2FBADA98B45145070051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451401FFFD9B84AF73F00000000B9EEDF2EDCB70FA37EEC3BF6E11C7E5E
,'农行兰州瓜州路支行','甘肃省针灸学会','27016001040005829','5162000052103699X4',now()
);
