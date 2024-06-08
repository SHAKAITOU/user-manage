package cn.caam.gs.common.enums;

public enum InOutType implements EnumImpl {

	IN	(EnumIndex.startIndex(1), 	"in", 	true, "InOutType.IN", CssClassType.PRIMARY),
	OUT	(EnumIndex.getNext(), 		"out", 	true, "InOutType.OUT", CssClassType.WARNING),
	ALL	(EnumIndex.getNext(), 		"all", 	false, "InOutType.ALL", CssClassType.INFO);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private InOutType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public InOutType[] list() {
    	return InOutType.values();
    }
    
    public static InOutType valueOf(int id) {
    	for(InOutType type : InOutType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static InOutType keyOf(String key) {
    	for(InOutType type : InOutType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
