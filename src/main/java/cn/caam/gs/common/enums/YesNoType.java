package cn.caam.gs.common.enums;

public enum YesNoType implements EnumImpl {

	NO	(EnumIndex.startIndex(0), 	"no", 	true, "YesNoType.NO", CssClassType.DANGER),
	YES	(EnumIndex.getNext(), 		"yes", 	true, "YesNoType.YES", CssClassType.SUCCESS),
	ALL	(EnumIndex.getNext(), 		"all", 		false, "YesNoType.ALL", CssClassType.PRIMARY);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private YesNoType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public YesNoType[] list() {
    	return YesNoType.values();
    }
    
    public static YesNoType valueOf(int id) {
    	for(YesNoType type : YesNoType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static YesNoType keyOf(String key) {
    	for(YesNoType type : YesNoType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
