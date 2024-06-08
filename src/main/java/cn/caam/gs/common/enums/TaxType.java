package cn.caam.gs.common.enums;

public enum TaxType implements EnumImpl {

	TAX_EXCLUDED	(EnumIndex.startIndex(0), 	"taxExcluded", 		true,		"TaxType.TAX_EXCLUDED", CssClassType.INFO),
	TAX_INCLUDED	(EnumIndex.getNext(), 		"taxIncluded", 		true,		"TaxType.TAX_INCLUDED", CssClassType.SUCCESS),
	TAX_FREE		(EnumIndex.getNext(), 		"taxFree", 			true,		"TaxType.TAX_FREE", CssClassType.WARNING),
	ALL				(EnumIndex.getNext(), 		"all", 				false, 		"TaxType.ALL", CssClassType.PRIMARY);
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private TaxType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public TaxType[] list() {
    	return TaxType.values();
    }
    
    public static TaxType valueOf(int id) {
    	for(TaxType type : TaxType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static TaxType keyOf(String key) {
    	for(TaxType type : TaxType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
