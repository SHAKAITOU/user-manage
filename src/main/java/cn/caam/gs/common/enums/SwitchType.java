package cn.caam.gs.common.enums;

public enum SwitchType implements EnumImpl {

	OPEN	(EnumIndex.startIndex(0), 	"open", 	true, "SwitchType.OPEN", CssClassType.SUCCESS),
	CLOSE	(EnumIndex.getNext(), 		"close", 	true, "SwitchType.CLOSE", CssClassType.DANGER);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private SwitchType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public SwitchType[] list() {
    	return SwitchType.values();
    }
    
    public static SwitchType valueOf(int id) {
    	for(SwitchType type : SwitchType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static SwitchType keyOf(String key) {
    	for(SwitchType type : SwitchType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
