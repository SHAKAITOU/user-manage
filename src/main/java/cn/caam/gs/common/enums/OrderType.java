package cn.caam.gs.common.enums;

public enum OrderType implements EnumImpl {

    JOIN             (EnumIndex.startIndex(1), "01",  true,	   CssClassType.SUCCESS),
    RENEWAL          (EnumIndex.getNext(),     "02",  true,    CssClassType.INFO),
    JOIN_WITH_IMG    (EnumIndex.getNext(),     "03",  true,    CssClassType.SUCCESS),
    RENEWAL_WITH_IMG (EnumIndex.getNext(),     "04",  true,    CssClassType.INFO),
    RENEWAL_BY_SYS   (EnumIndex.getNext(),     "05",  true,    CssClassType.INFO),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private OrderType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public OrderType[] list() {
    	return OrderType.values();
    }
    
    public static OrderType valueOf(int id) {
    	for(OrderType type : OrderType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static OrderType keyOf(String key) {
    	for(OrderType type : OrderType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
