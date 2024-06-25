package cn.caam.gs.common.enums;

public enum VoteMethodType implements EnumImpl {

    ELECTRONIC          (EnumIndex.startIndex(1), "01",  true,    CssClassType.INFO),
	;
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;

    private VoteMethodType(int id, String key, boolean normalShow, CssClassType classType) {
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
    
    public VoteMethodType[] list() {
    	return VoteMethodType.values();
    }
    
    public static VoteMethodType valueOf(int id) {
    	for(VoteMethodType type : VoteMethodType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static VoteMethodType keyOf(String key) {
    	for(VoteMethodType type : VoteMethodType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
