package cn.caam.gs.common.enums;

public enum ValidType implements EnumImpl {

	INVALID	(EnumIndex.startIndex(0), 	"invalid", 	true, "ValidType.INVALID", CssClassType.LIGHT),
	VALID	(EnumIndex.getNext(), 		"valid", 	true, "ValidType.VALID", CssClassType.SUCCESS),
	ALL		(EnumIndex.getNext(), 		"all", 		false, "ValidType.ALL", CssClassType.PRIMARY);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private ValidType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public ValidType[] list() {
    	return ValidType.values();
    }
    
    public static ValidType valueOf(int id) {
    	for(ValidType type : ValidType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static ValidType keyOf(String key) {
    	for(ValidType type : ValidType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
