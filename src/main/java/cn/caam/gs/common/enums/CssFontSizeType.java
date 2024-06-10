package cn.caam.gs.common.enums;

public enum CssFontSizeType {

	LABEL_12		("label-12"),
	LABEL_12B		("label-12b"),
	LABEL_14      ("label-14"),
	LABEL_14B      ("label-14b"),
    LABEL_16      ("label-16"),
	LABEL_16B      ("label-16b"),
	LABEL_18      ("label-18"),
	LABEL_18B      ("label-18b"),
	;
	
    /** type. */
    private String key;

    private CssFontSizeType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public CssFontSizeType[] list() {
    	return CssFontSizeType.values();
    }
    
    public static CssFontSizeType keyOf(String key) {
    	for(CssFontSizeType type : CssFontSizeType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
