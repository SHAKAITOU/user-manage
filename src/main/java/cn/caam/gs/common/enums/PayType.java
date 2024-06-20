package cn.caam.gs.common.enums;

public enum PayType implements EnumImpl {

    ONLINE           (EnumIndex.startIndex(1), "01",  true,	   CssClassType.SUCCESS),
    OFFLINE          (EnumIndex.getNext(),     "02",  true,    CssClassType.INFO),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private PayType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public PayType[] list() {
    	return PayType.values();
    }
    
    public static PayType valueOf(int id) {
    	for(PayType type : PayType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static PayType keyOf(String key) {
    	for(PayType type : PayType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
