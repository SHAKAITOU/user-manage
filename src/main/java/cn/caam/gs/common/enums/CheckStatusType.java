package cn.caam.gs.common.enums;

public enum CheckStatusType implements EnumImpl {
    WAIT_FOR_REVIEW  (EnumIndex.startIndex(1), "01",  true,    CssClassType.INFO),
    REVIEW           (EnumIndex.getNext(),     "02",  true,    CssClassType.WARNING),
    REVIEWED         (EnumIndex.getNext(),     "03",  true,    CssClassType.PRIMARY),
    PASS             (EnumIndex.getNext(),     "04",  true,    CssClassType.SUCCESS),
    REFUSED          (EnumIndex.getNext(),     "05",  true,    CssClassType.DANGER),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private CheckStatusType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public CheckStatusType[] list() {
    	return CheckStatusType.values();
    }
    
    public static CheckStatusType valueOf(int id) {
    	for(CheckStatusType type : CheckStatusType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static CheckStatusType keyOf(String key) {
    	for(CheckStatusType type : CheckStatusType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
