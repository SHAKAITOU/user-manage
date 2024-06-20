package cn.caam.gs.common.enums;

public enum OrderMethodType implements EnumImpl {

    PERSONAL  (EnumIndex.startIndex(1), "01",  true,	CssClassType.SUCCESS),
    RECOMMEND (EnumIndex.getNext(),     "02",  true,    CssClassType.INFO),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private OrderMethodType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public OrderMethodType[] list() {
    	return OrderMethodType.values();
    }
    
    public static OrderMethodType valueOf(int id) {
    	for(OrderMethodType type : OrderMethodType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static OrderMethodType keyOf(String key) {
    	for(OrderMethodType type : OrderMethodType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
