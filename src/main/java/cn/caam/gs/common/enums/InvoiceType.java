package cn.caam.gs.common.enums;

public enum InvoiceType implements EnumImpl {

	PERSON 		 (EnumIndex.startIndex(1), 	"01",  true,	"InvoiceType.PERSON",        CssClassType.SUCCESS),
	ORGANIZATION (EnumIndex.getNext(),      "02",  true,    "InvoiceType.ORGANIZATION", CssClassType.SUCCESS),
	UNKNOWN      (EnumIndex.getNext(),      "",    true,    "", CssClassType.DANGER),
	;
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private InvoiceType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public InvoiceType[] list() {
    	return InvoiceType.values();
    }
    
    public static InvoiceType valueOf(int id) {
    	for(InvoiceType type : InvoiceType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return UNKNOWN;
    }
    
    public static InvoiceType keyOf(String key) {
    	for(InvoiceType type : InvoiceType.values()) {
    		if(type.getKey().equals(key)) {
    			return type;
    		}
    	}
    	
    	return UNKNOWN;
    }
}
