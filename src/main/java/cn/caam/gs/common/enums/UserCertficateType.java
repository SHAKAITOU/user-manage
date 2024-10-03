package cn.caam.gs.common.enums;

public enum UserCertficateType {

	  /** 中国协会证书 */
	  CHINA		("01", "china"),
	  /** 甘肃协会证书 */
	  GANSU 		("02", "gansu"),
	  /** 未知 */
	  UNKNOWN		("", "unknown");

    private String key;
    
    private String name;

    private UserCertficateType(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getKey() {
        return key;
    }
    
    public UserCertficateType[] list() {
    	return UserCertficateType.values();
    }
    
    public static UserCertficateType fileIdOf(String key) {
    	for(UserCertficateType type : UserCertficateType.values()) {
    		if(type.getKey().equals(key)) {
    			return type;
    		}
    	}
    	
    	return UNKNOWN;
    }
    
    public static UserCertficateType keyOf(String key) {
    	for(UserCertficateType type : UserCertficateType.values()) {
    		if(type.getKey().equals(key)) {
    			return type;
    		}
    	}
    	
    	return UNKNOWN;
    }
}
