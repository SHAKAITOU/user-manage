package cn.caam.gs.common.enums;

public enum RowCntType implements EnumImpl {

    ROW_1          (EnumIndex.startIndex(1),      "1",           true, "RowCntType.ROW_1", CssClassType.INFO),
    ROW_10          (EnumIndex.startIndex(10),      "10",           true, "RowCntType.ROW_10", CssClassType.INFO),
	ROW_20			(EnumIndex.startIndex(20), 		"20", 			true, "RowCntType.ROW_20", CssClassType.INFO),
	ROW_50			(EnumIndex.startIndex(50), 		"50", 			true, "RowCntType.ROW_50", CssClassType.INFO),
	;
	
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private RowCntType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public RowCntType[] list() {
    	return RowCntType.values();
    }
    
    public static RowCntType valueOf(int id) {
    	for(RowCntType type : RowCntType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static RowCntType keyOf(String key) {
    	for(RowCntType type : RowCntType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
