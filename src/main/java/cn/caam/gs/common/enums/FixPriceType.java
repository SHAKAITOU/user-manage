package cn.caam.gs.common.enums;

public enum FixPriceType implements EnumImpl {

	FIX_AMOUNT	(EnumIndex.startIndex(1), 		"fixAmount", 	true, "FixPriceType.FIX_AMOUNT", CssClassType.PRIMARY),
	FIX_PRICE	(EnumIndex.getNext(), 			"fixPrice", 	true, "FixPriceType.FIX_PRICE", CssClassType.SUCCESS),
	ALL	(EnumIndex.getNext(), 		"all", 		false, "FixPriceType.ALL", CssClassType.INFO);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private FixPriceType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public FixPriceType[] list() {
    	return FixPriceType.values();
    }
    
    public static FixPriceType valueOf(int id) {
    	for(FixPriceType type : FixPriceType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static FixPriceType keyOf(String key) {
    	for(FixPriceType type : FixPriceType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
