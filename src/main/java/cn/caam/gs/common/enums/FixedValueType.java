package cn.caam.gs.common.enums;

public enum FixedValueType {

    /** 管理员类型(F0000) */
	AUTH_LEVEL		("F0000"),
	/** 性别(F0001) */
	SEX		        ("F0001"),
	/** 会员类型(F0002) 2桁*/
	USER_TYPE       ("F0002"),
	/** 政治面貌(F0003) */
	POLITICAL       ("F0003"),
	/** 学历(F0004) */
	EDU_DEGREE      ("F0004"),
	/** 民族(F0005) */
	NATIONALITY     ("F0005"),
	/** 学位(F0006) */
	BACHELOR        ("F0006"),
	/** 职务(F0007) */
	POSITION        ("F0007"),
	/** 单位性质(F0008) */
	EMPLOYER_TYPE   ("F0008"),
	/** 职称(F0009) */
	JOB_TITLE       ("F0009"),
	/** 证件类型(F0010) */
	CERTIFICATE_TYPE("F0010"),
	/** 所在地区(F0011) */
	AREA            ("F0011"),
	/** 入会途径/缴费渠道(F0012) */
	MEMBERSHIP_PATH ("F0012"),
	/** 审核状态(F0013) */
	CHECK_STATUS    ("F0013"),
	/** 缴费类型(F0014) */
	PAY_TYPE        ("F0014"),
	/** 订单类型(F0015) */
	ORDER_TYPE      ("F0015"),
	/** 退款状态(F0016) */
	REFUND_STATUS   ("F0016"),
	/** 发票类型(F0017) */
	BILL_TYPE       ("F0017"),
	/** 开票状态(F0018) */
	BILL_STATUS     ("F0018"),
	/** 取票方式(F0019) */
	VOTE_METHOD     ("F0019"),
	/** 订单方式(F0020) */
	ORDER_METHOD    ("F0020"),
	/** 站内消息类型(F0021) */
	MSG_TYPE        ("F0021"),
    /** 会费计算方式类型(F0022) */
    FEE_TYPE        ("F0022"),
    /** 学会区分(F0023) */
    SOCIETY_TYPE    ("F0023"),
	/** 会员审核状态(F0013) */
	USER_CHECK_STATUS    ("F0024"),
	;
	
    /** type. */
    private String key;

    private FixedValueType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public FixedValueType[] list() {
    	return FixedValueType.values();
    }
    
    public static FixedValueType keyOf(String key) {
    	for(FixedValueType type : FixedValueType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
