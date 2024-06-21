package cn.caam.gs.common.enums;

public enum BillStatusType implements EnumImpl {

    TO_BE_INVOICED    (EnumIndex.startIndex(1), "01",  true,    CssClassType.DANGER),
    INVOICED          (EnumIndex.getNext(),     "02",  true,    CssClassType.SUCCESS),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private BillStatusType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public BillStatusType[] list() {
    	return BillStatusType.values();
    }
    
    public static BillStatusType valueOf(int id) {
    	for(BillStatusType type : BillStatusType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static BillStatusType keyOf(String key) {
    	for(BillStatusType type : BillStatusType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
