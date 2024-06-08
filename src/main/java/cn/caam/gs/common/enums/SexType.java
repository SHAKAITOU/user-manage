package cn.caam.gs.common.enums;

public enum SexType implements EnumImpl {

	MAN			(EnumIndex.startIndex(0), 	"man", 	 	true,	"SexType.MAN", CssClassType.INFO),
	WOMEN		(EnumIndex.getNext(), 		"women", 	 true,	"SexType.WOMEN", CssClassType.DANGER);
	
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private SexType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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

    public SexType[] list() {
    	return SexType.values();
    }
    
    public static SexType valueOf(int id) {
    	for(SexType type : SexType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static SexType keyOf(String key) {
    	for(SexType type : SexType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
