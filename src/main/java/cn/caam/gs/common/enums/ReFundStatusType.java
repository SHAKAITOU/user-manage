package cn.caam.gs.common.enums;

public enum ReFundStatusType implements EnumImpl {

    NOT_CONFIRM    (EnumIndex.startIndex(1), "01",  true,    CssClassType.DANGER),
    WAIT_REFUND    (EnumIndex.getNext(),     "02",  true,    CssClassType.INFO),
    REFUND_OVER    (EnumIndex.getNext(),     "03",  true,    CssClassType.SUCCESS),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private ReFundStatusType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public ReFundStatusType[] list() {
    	return ReFundStatusType.values();
    }
    
    public static ReFundStatusType valueOf(int id) {
    	for(ReFundStatusType type : ReFundStatusType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static ReFundStatusType keyOf(String key) {
    	for(ReFundStatusType type : ReFundStatusType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
