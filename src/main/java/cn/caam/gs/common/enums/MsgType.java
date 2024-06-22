package cn.caam.gs.common.enums;

public enum MsgType implements EnumImpl {

    ALL           (EnumIndex.startIndex(1), "01",  true,	CssClassType.DANGER),
    PERSONAL      (EnumIndex.getNext(),     "02",  true,    CssClassType.INFO),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private MsgType(int id, String key, boolean normalShow, CssClassType classType) {
        this.id = id;
        this.key = key;
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
        return key;
    }


    public boolean getNormalShow() {
        return normalShow;
    }
    
    public CssClassType getClassType() {
        return classType;
    }
    
    public MsgType[] list() {
    	return MsgType.values();
    }
    
    public static MsgType valueOf(int id) {
    	for(MsgType type : MsgType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static MsgType keyOf(String key) {
    	for(MsgType type : MsgType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
