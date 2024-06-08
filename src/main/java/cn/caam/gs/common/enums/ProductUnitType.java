package cn.caam.gs.common.enums;

public enum ProductUnitType implements EnumImpl {

	PCS	(EnumIndex.startIndex(1), 	"pieces", 	true, "ProductUnitType.PCS", CssClassType.INFO),
	CAP	(EnumIndex.getNext(), 		"capsule", 	true,	"ProductUnitType.CAP", CssClassType.WARNING),
	BOX	(EnumIndex.getNext(), 		"box", 		true,	"ProductUnitType.BOX", CssClassType.PRIMARY);
	
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private ProductUnitType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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

    public ProductUnitType[] list() {
    	return ProductUnitType.values();
    }
    
    public static ProductUnitType valueOf(int id) {
    	for(ProductUnitType type : ProductUnitType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static ProductUnitType keyOf(String key) {
    	for(ProductUnitType type : ProductUnitType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
