package cn.caam.gs.common.enums;

public enum UserType implements EnumImpl {

	FREE			(EnumIndex.startIndex(1), 	"free", 		true, "UserType.FREE", CssClassType.DANGER),
	NORMAL			(EnumIndex.getNext(), 		"normal", 		true, "UserType.NORMAL", CssClassType.INFO),
	PREMIUM			(EnumIndex.getNext(), 		"premium", 		true, "UserType.PREMIUM", CssClassType.PRIMARY);
	
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private UserType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public UserType[] list() {
    	return UserType.values();
    }
    
    public static UserType valueOf(int id) {
    	for(UserType type : UserType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
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
