package cn.caam.gs.common.enums;

public enum StatusType implements EnumImpl {

	NOT_CHECK	(EnumIndex.startIndex(0), 	"notCheck", 	true, "StatusType.NOT_CHECK", CssClassType.DANGER),
	CHECKED		(EnumIndex.getNext(), 		"checked", 	true, "StatusType.CHECKED", CssClassType.SUCCESS),
	CANCEL		(EnumIndex.getNext(), 		"cancel", 	true, "StatusType.CANCEL", CssClassType.SECONDARY),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private StatusType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public StatusType[] list() {
    	return StatusType.values();
    }
    
    public static StatusType valueOf(int id) {
    	for(StatusType type : StatusType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static StatusType keyOf(String key) {
    	for(StatusType type : StatusType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
