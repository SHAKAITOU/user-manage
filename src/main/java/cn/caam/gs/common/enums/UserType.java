package cn.caam.gs.common.enums;

public enum UserType {

    /** 个人会员 */
    PERSON		("01"),
	/** 团体会员单位 */
    GROUP		("02"),
	;
	
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
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
