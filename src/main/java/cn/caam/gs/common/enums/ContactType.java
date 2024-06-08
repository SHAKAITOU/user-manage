package cn.caam.gs.common.enums;

public enum ContactType implements EnumImpl {

	PAY			(1, 		"pay", 		true,		"ContactType.PAY", CssClassType.PRIMARY),
	OTHER		(2, 		"other", 	true,		"ContactType.OTHER", CssClassType.INFO),
	
	;
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private ContactType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public ContactType[] list() {
    	return ContactType.values();
    }
    
    public static ContactType valueOf(int id) {
    	for(ContactType type : ContactType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static ContactType keyOf(String key) {
    	for(ContactType type : ContactType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
