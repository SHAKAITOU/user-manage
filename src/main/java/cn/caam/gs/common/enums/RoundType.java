package cn.caam.gs.common.enums;

public enum RoundType implements EnumImpl {

	FLOOR	(EnumIndex.startIndex(0), 	"floor", 	true,			"RoundType.FLOOR", CssClassType.DANGER),
	ROUND	(EnumIndex.getNext(), 		"round", 	true,			"RoundType.ROUND", CssClassType.INFO),
	CEIL	(EnumIndex.getNext(), 		"ceil", 	true,			"RoundType.CEIL", CssClassType.WARNING),
	ALL		(EnumIndex.getNext(), 		"all", 		false, 			"RoundType.ALL", CssClassType.PRIMARY);
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private RoundType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public RoundType[] list() {
    	return RoundType.values();
    }
    
    public static RoundType valueOf(int id) {
    	for(RoundType type : RoundType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static RoundType keyOf(String key) {
    	for(RoundType type : RoundType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
