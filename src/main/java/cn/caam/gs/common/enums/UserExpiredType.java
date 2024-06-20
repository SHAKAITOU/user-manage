package cn.caam.gs.common.enums;

public enum UserExpiredType implements EnumImpl {

	VALID		 (EnumIndex.startIndex(1), 	"01",  true,	"UserExpiredType.VALID",        CssClassType.SUCCESS),
	EXPIRED_SOON (EnumIndex.getNext(),      "02",  true,    "UserExpiredType.EXPIRED_SOON", CssClassType.INFO),
	EXPIRED		 (EnumIndex.getNext(), 		"03",  true,	"UserExpiredType.EXPIRED",      CssClassType.WARNING),
	VARY_EXPIRED (EnumIndex.getNext(),      "04",  true,    "UserExpiredType.VARY_EXPIRED", CssClassType.DANGER),
	;
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private UserExpiredType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
        this.id = id;
        this.key = key;
        this.msg = msg;
        this.normalShow = normalShow;
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
    
    public UserExpiredType[] list() {
    	return UserExpiredType.values();
    }
    
    public static UserExpiredType valueOf(int id) {
    	for(UserExpiredType type : UserExpiredType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static UserExpiredType keyOf(String key) {
    	for(UserExpiredType type : UserExpiredType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
