package cn.caam.gs.common.enums;

public enum TaxRateType implements EnumImpl {

	TAX_FREE	(0, 		"0", 		true,		"TaxRateType.TAX_FREE", CssClassType.WARNING),
	TAX_EIGHT	(8, 		"8", 		true,		"TaxRateType.TAX_EIGHT", CssClassType.INFO),
	TAX_TEN		(10, 		"10", 		true,		"TaxRateType.TAX_TEN", CssClassType.PRIMARY),;
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private TaxRateType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public TaxRateType[] list() {
    	return TaxRateType.values();
    }
    
    public static TaxRateType valueOf(int id) {
    	for(TaxRateType type : TaxRateType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static TaxRateType keyOf(String key) {
    	for(TaxRateType type : TaxRateType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
