package cn.caam.gs.common.enums;

public enum BillType implements EnumImpl {

    UNIFIED             (EnumIndex.startIndex(1), "01",  true,    CssClassType.INFO),
    SPECIAL_VALUE_ADDED (EnumIndex.getNext(),     "02",  true,    CssClassType.DANGER),
    NORMAL_VALUE_ADDED  (EnumIndex.getNext(),     "03",  true,    CssClassType.INFO),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private BillType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public BillType[] list() {
    	return BillType.values();
    }
    
    public static BillType valueOf(int id) {
    	for(BillType type : BillType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static BillType keyOf(String key) {
    	for(BillType type : BillType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
