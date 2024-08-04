package cn.caam.gs.common.enums;

public enum LoginAccountType {

    USER     (EnumIndex.startIndex(1), "user"),
    ADMIN    (EnumIndex.getNext(),     "admin"),
    UNKNOWN  (EnumIndex.getNext(),     "unknown"),
	; 

    /** type. */
    private int id;
    private String key;

    private LoginAccountType(int id, String key) {
        this.id = id;
        this.key = key;

    }
    
    public int getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getMsg() {
        return key;
    }
    
    public static LoginAccountType valueOf(int id) {
    	for(LoginAccountType type : LoginAccountType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static LoginAccountType keyOf(String key) {
    	for(LoginAccountType type : LoginAccountType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
