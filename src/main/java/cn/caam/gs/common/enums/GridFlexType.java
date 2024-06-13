package cn.caam.gs.common.enums;

public enum GridFlexType {

	LEFT		("d-flex justify-content-start"),
	RIGHT	    ("d-flex justify-content-end"),
	CENTER      ("d-flex justify-content-center"),
	BETWEEN     ("d-flex justify-content-between"),
	AROUND      ("d-flex justify-content-around"),
	;
	
    /** type. */
    private String key;

    private GridFlexType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public GridFlexType[] list() {
    	return GridFlexType.values();
    }
    
    public static GridFlexType keyOf(String key) {
    	for(GridFlexType type : GridFlexType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
