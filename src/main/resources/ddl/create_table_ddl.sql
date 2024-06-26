CREATE DATABASE shatest DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- 管理员信息 --
DROP TABLE IF EXISTS m_admin;
CREATE TABLE m_admin
( 
    id               VARCHAR(20)  NOT NULL COMMENT '管理员号(AYYMMDDHHmmSSR2)',
    name             VARCHAR(70)  NOT NULL COMMENT '管理员名称(max64)',
    user_type        VARCHAR(3)   NOT NULL COMMENT '管理员类型(F0000)',
    PRIMARY KEY (id)
) COMMENT='管理员信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 固定值管理 --
DROP TABLE IF EXISTS m_fixed_value;
CREATE TABLE m_fixed_value
( 
    code       VARCHAR(10)   NOT NULL COMMENT 'KEY识别码',
    value      VARCHAR(10)  NOT NULL COMMENT 'KEY识别码子值',
    name       VARCHAR(255)  NOT NULL COMMENT 'KEY识别码子名称',
    show_index INT           NOT NULL COMMENT '页面表示顺序',
    PRIMARY KEY (code, value)
) COMMENT='固定值管理' ENGINE=InnoDB DEFAULT CHARSET=utf8;

--管理员类型
INSERT INTO m_fixed_value VALUES ('F0000', '01', '超级管理员', 1);
INSERT INTO m_fixed_value VALUES ('F0000', '02', '高级管理员', 2);
INSERT INTO m_fixed_value VALUES ('F0000', '03', '一般管理员', 3);
--性别
INSERT INTO m_fixed_value VALUES ('F0001', '01', '男', 1);
INSERT INTO m_fixed_value VALUES ('F0001', '02', '女', 2);
--会员类型
INSERT INTO m_fixed_value VALUES ('F0002', '01', '个人会员',         1);
INSERT INTO m_fixed_value VALUES ('F0002', '0101', '普通会员',       1);
INSERT INTO m_fixed_value VALUES ('F0002', '0102', '普通会员(学生)', 2);
INSERT INTO m_fixed_value VALUES ('F0002', '0103', '外籍会员',       3);
INSERT INTO m_fixed_value VALUES ('F0002', '0104', '会士会员',       4);
INSERT INTO m_fixed_value VALUES ('F0002', '0105', '会士会员',       5);
INSERT INTO m_fixed_value VALUES ('F0002', '02', '团体会员单位',   2);
INSERT INTO m_fixed_value VALUES ('F0002', '0201', '团体会员单位',   2);

--政治面貌
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
--学历
INSERT INTO m_fixed_value VALUES ('F0004', '01', '博士后',         1);
INSERT INTO m_fixed_value VALUES ('F0004', '02', '博士',           2);
INSERT INTO m_fixed_value VALUES ('F0004', '03', '硕士',           3);
INSERT INTO m_fixed_value VALUES ('F0004', '04', '本科',           4);
INSERT INTO m_fixed_value VALUES ('F0004', '05', '大专',           5);
INSERT INTO m_fixed_value VALUES ('F0004', '06', '高中',           6);
INSERT INTO m_fixed_value VALUES ('F0004', '07', '中专',           7);
INSERT INTO m_fixed_value VALUES ('F0004', '08', '其他',           8);
--民族
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
--学位
INSERT INTO m_fixed_value VALUES ('F0006', '01', '博士学位',       1);
INSERT INTO m_fixed_value VALUES ('F0006', '02', '硕士学位',       2);
INSERT INTO m_fixed_value VALUES ('F0006', '03', '学士学位',       3);
INSERT INTO m_fixed_value VALUES ('F0006', '04', '其他',           4);
--职务
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
--单位性质
INSERT INTO m_fixed_value VALUES ('F0008', '01', '高校',           1);
INSERT INTO m_fixed_value VALUES ('F0008', '02', '科研院所',       2);
INSERT INTO m_fixed_value VALUES ('F0008', '03', '企业',           3);
INSERT INTO m_fixed_value VALUES ('F0008', '04', '医院',           4);
INSERT INTO m_fixed_value VALUES ('F0008', '05', '其他',           5);
--职称
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
--证件类型
INSERT INTO m_fixed_value VALUES ('F0010', '01', '身份证',           1);
INSERT INTO m_fixed_value VALUES ('F0010', '02', '港澳台居民居住证', 2);
INSERT INTO m_fixed_value VALUES ('F0010', '03', '军官证',           3);
INSERT INTO m_fixed_value VALUES ('F0010', '04', '护照',             4);
--所在地区
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
--入会途径
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
--审核状态
INSERT INTO m_fixed_value VALUES ('F0013', '01', '等待审核',  1);
INSERT INTO m_fixed_value VALUES ('F0013', '02', '审核中',    2);
INSERT INTO m_fixed_value VALUES ('F0013', '03', '已审核',    3);
INSERT INTO m_fixed_value VALUES ('F0013', '04', '审核通过',  4);
INSERT INTO m_fixed_value VALUES ('F0013', '05', '审核拒否',  5);

--缴费类型
INSERT INTO m_fixed_value VALUES ('F0014', '01', '线上支付',    1);
INSERT INTO m_fixed_value VALUES ('F0014', '02', '线下支付',    2);
--订单类型
INSERT INTO m_fixed_value VALUES ('F0015', '01', '入会订单',    1);
INSERT INTO m_fixed_value VALUES ('F0015', '02', '续费订单',    2);
INSERT INTO m_fixed_value VALUES ('F0015', '03', '入会订单(详情添加)',    3);
INSERT INTO m_fixed_value VALUES ('F0015', '04', '续费订单(详情添加)',    4);
INSERT INTO m_fixed_value VALUES ('F0015', '05', '续费订单(系统导入)',    5);
--退款状态
INSERT INTO m_fixed_value VALUES ('F0016', '01', '已退款',    1);
INSERT INTO m_fixed_value VALUES ('F0016', '02', '退款中',    2);
INSERT INTO m_fixed_value VALUES ('F0016', '03', '退款失败',  3);
--发票类型
INSERT INTO m_fixed_value VALUES ('F0017', '01', '全国性社会团体会费统一收据',    1);
INSERT INTO m_fixed_value VALUES ('F0017', '02', '增值税专用发票',                2);
INSERT INTO m_fixed_value VALUES ('F0017', '03', '增值税普通发票',                3);
--开票状态
INSERT INTO m_fixed_value VALUES ('F0018', '01', '待开票',    1);
INSERT INTO m_fixed_value VALUES ('F0018', '02', '已开票',    2);
--取票方式
INSERT INTO m_fixed_value VALUES ('F0019', '01', '电子票据',    1);
--订单方式
INSERT INTO m_fixed_value VALUES ('F0020', '01', '个人订单',    1);
INSERT INTO m_fixed_value VALUES ('F0020', '02', '推荐订单',    2);

-- 会员基本信息 --
DROP TABLE IF EXISTS m_user;
CREATE TABLE m_user
( 
    id               VARCHAR(20)  NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
    name             VARCHAR(70)  NOT NULL COMMENT '会员名称(max64)',
    user_type        VARCHAR(3)   NOT NULL COMMENT '会员类型(F0002)',
    membership_path  VARCHAR(3)   NOT NULL COMMENT '入会途径(F0012)',
    focus_on         VARCHAR(255)          COMMENT '关注(F0012)',
    sex              VARCHAR(3)   NOT NULL COMMENT '性别(F0001)',
    birth            VARCHAR(10)  NOT NULL COMMENT '出生年月',
    nationality      VARCHAR(3)   NOT NULL COMMENT '民族(F0005)',
    political        VARCHAR(3)   NOT NULL COMMENT '政治面貌(F0003)',
    edu_degree       VARCHAR(3)   NOT NULL COMMENT '学历(F0004)',
    bachelor         VARCHAR(3)   NOT NULL COMMENT '学位(F0006)',
    position         VARCHAR(3)            COMMENT '职务(F0007)',
    employer         VARCHAR(70)  NOT NULL COMMENT '工作单位(max64)',
    employer_type    VARCHAR(3)            COMMENT '单位性质(F0008)',
    job_title        VARCHAR(3)            COMMENT '职称(F0009)',
    certificate_type VARCHAR(3)   NOT NULL COMMENT '证件类型(F0010)',
    certificate_code VARCHAR(20)  NOT NULL COMMENT '证件号码(max18)',
    area             VARCHAR(3)   NOT NULL COMMENT '所在地区(F0011)',
    area_sub         VARCHAR(6)   NOT NULL COMMENT '所在地区市级(F0011)',
    address          VARCHAR(140) NOT NULL COMMENT '通讯地址(max128)',
    postal_code      VARCHAR(10)  NOT NULL COMMENT '邮政编码(max10)',
    phone            VARCHAR(20)  NOT NULL COMMENT '手机号(max11)',
    mail             VARCHAR(70)  NOT NULL COMMENT '电子邮箱(max64)',
    check_date       VARCHAR(20)  NOT NULL COMMENT '审核时间(yyyy-MM-dd)',
    check_status     VARCHAR(20)  NOT NULL COMMENT '审核状态(F0013)',
    regist_date      VARCHAR(20)  NOT NULL COMMENT '入会时间(yyyy-MM-dd HH:mm:ss)',
    valid_start_date VARCHAR(20)  NOT NULL COMMENT '有效开始日期(yyyy-MM-dd HH:mm:ss)',
    valid_end_date   VARCHAR(20)  NOT NULL COMMENT '有效结束日期(yyyy-MM-dd HH:mm:ss)',
    PRIMARY KEY (id)
) COMMENT='会员基本信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX m_user_idx1 ON m_user (name);
CREATE INDEX m_user_idx2 ON m_user (phone);
-- 会员扩展信息 --
DROP TABLE IF EXISTS m_user_extend;
CREATE TABLE m_user_extend
( 
    id               VARCHAR(20)  NOT NULL COMMENT '(M/TYYMMDDHHmmSSR2)',
    introducer1      VARCHAR(50)           COMMENT '介绍人1(max32)',
    introducer2      VARCHAR(50)           COMMENT '介绍人2(max32)',
    id_photo         BLOB                  COMMENT '2寸证件照jpg/png/jpeg200k',
    major            VARCHAR(70)  NOT NULL COMMENT '专业(max64)',
    educational_at   BLOB                  COMMENT '学历证书附件',
    bachelor_at      BLOB                  COMMENT '学位证书附件',
    vocational_at    BLOB                  COMMENT '职业证书',
    research_dir     VARCHAR(40)           COMMENT '研究方向(max36)',
    learn_experience VARCHAR(255) NOT NULL COMMENT '主要学习经历(max250)',
    work_experience  VARCHAR(255) NOT NULL COMMENT '主要工作经历(max250)',
    papers           VARCHAR(255)          COMMENT '代表性论文及著作(max250)',
    honors           VARCHAR(255)          COMMENT '获得科技奖励及荣誉情况(max250)',

    PRIMARY KEY (id)
) COMMENT='会员扩展信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 会员订单信息 --
DROP TABLE IF EXISTS m_order;
CREATE TABLE m_order
( 
    id               VARCHAR(30)    NOT NULL COMMENT '订单号(yyyyMMddHHmmssSSSR7)',
    user_id          VARCHAR(20)    NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
    pay_path         VARCHAR(3)     NOT NULL COMMENT '缴费渠道(F0012)',
    order_method     VARCHAR(3)     NOT NULL COMMENT '订单方式(F0020)',
    order_type       VARCHAR(3)     NOT NULL COMMENT '订单类型(F0015)',
    pay_type         VARCHAR(3)     NOT NULL COMMENT '缴费类型(F0014)',
    order_amount     DECIMAL(13, 3) NOT NULL COMMENT '订单金额',
    pay_amount       DECIMAL(13, 3) NOT NULL COMMENT '实收金额',
    pay_date         VARCHAR(20)    NOT NULL COMMENT '缴费时间(yyyy-MM-dd HH:mm:ss)',
    check_date       VARCHAR(20)           COMMENT '审核时间(yyyy-MM-dd)',
    check_status     VARCHAR(20)           COMMENT '审核状态(F0013)',
    refund_date      VARCHAR(20)           COMMENT '退款时间(yyyy-MM-dd HH:mm:ss)',
    refund_status    VARCHAR(20)           COMMENT '退款状态(F0016)',
    memo             VARCHAR(255)          COMMENT '备注(max250)',
    PRIMARY KEY (id),
    FOREIGN KEY user_id_foreign_key (user_id) references m_user (id)
) COMMENT='会员订单信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 会员发票信息 --
DROP TABLE IF EXISTS m_bill;
CREATE TABLE m_bill
( 
    id               VARCHAR(30)    NOT NULL COMMENT '订单号(yyyyMMddHHmmssSSSR7)',
    user_id          VARCHAR(20)    NOT NULL COMMENT '会员号(M/TYYMMDDHHmmSSR2)',
    bill_code        VARCHAR(50)             COMMENT '发票代码',
    bill_amount      DECIMAL(13, 3) NOT NULL COMMENT '开票金额',
    bill_title       VARCHAR(20)    NOT NULL COMMENT '发票抬头',
    credit_code      VARCHAR(20)             COMMENT '统一社会信用代码',
    bill_date        VARCHAR(20)             COMMENT '开票时间(yyyy-MM-dd HH:mm:ss)',
    bill_status      VARCHAR(20)             COMMENT '审核状态(F0013)',
    memo             VARCHAR(255)            COMMENT '备注(max250)',
    PRIMARY KEY (id),
    FOREIGN KEY user_id_foreign_key (user_id) references m_user (id)
) COMMENT='会员发票信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;