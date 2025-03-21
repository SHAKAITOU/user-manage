package cn.caam.gs.common.enums;

public enum UserType {

	  /** 个人会员 */
	  PERSON		("01"),
	  /** 甘肃协会会员 */
	  PERSON_GANSU 		("0101"),
	  /** 中国协会会员 */
	  PERSON_CHINA      ("0105"),
//    /** 普通会员 */
//    PERSON_REGULAR 		("0101"),
//    /** 普通会员(学生) */
//    PERSON_REGULAR_STUDENT		("0102"),
//    /** 外籍会员 */
//    PERSON_FOREIGN		("0103"),
//    /** 会士会员 */
//    PERSON_FELLOW		("0104"),
    /** 团体会员单位 */
    GROUP		("02"),
    /** 团体会员单位 */
    GROUP_MEMBER		("0201");

    /** type. */
    private String key;

    private UserType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public UserType[] list() {
    	return UserType.values();
    }
    
    public static UserType keyOf(String key) {
    	for(UserType type : UserType.values()) {
    		if(type.getKey().equals(key)) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
