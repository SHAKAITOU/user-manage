package cn.caam.gs.common.enums;

public enum UserCheckStatusType implements EnumImpl {
	NEW              (EnumIndex.startIndex(1), "01",  true,    "UserCheckStatusType.NEW ", CssClassType.INFO),
    WAIT_FOR_REVIEW  (EnumIndex.getNext()    , "02",  true,    "UserCheckStatusType.WAIT_FOR_REVIEW", CssClassType.INFO),
    PASS             (EnumIndex.getNext(),     "03",  true,    "UserCheckStatusType.PASS", CssClassType.SUCCESS),
    REFUSED          (EnumIndex.getNext(),     "04",  true,    "UserCheckStatusType.REFUSED", CssClassType.DANGER),
    RETURN           (EnumIndex.getNext(),     "05",  true,    "UserCheckStatusType.RETURN", CssClassType.PRIMARY),
	;
    
    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;
    private String msg;

    private UserCheckStatusType(int id, String key, boolean normalShow,String msg,  CssClassType classType) {
        this.id = id;
        this.key = key;
        this.normalShow = normalShow;
        this.msg = msg;
        this.classType = classType;
    }
    
    public int getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getMsg() {
        return msg;
    }


    public boolean getNormalShow() {
        return normalShow;
    }
    
    public CssClassType getClassType() {
        return classType;
    }
    
    public UserCheckStatusType[] list() {
    	return UserCheckStatusType.values();
    }
    
    public static UserCheckStatusType valueOf(int id) {
    	for(UserCheckStatusType type : UserCheckStatusType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static UserCheckStatusType keyOf(String key) {
    	for(UserCheckStatusType type : UserCheckStatusType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
