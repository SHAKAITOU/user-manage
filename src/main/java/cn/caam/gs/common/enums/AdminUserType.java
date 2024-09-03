package cn.caam.gs.common.enums;

public enum AdminUserType {
	/** 超级管理员 */
	SUPER				(EnumIndex.startIndex(1), "01",  true,    "AdminUserType.SUPER", CssClassType.DANGER),
	/** 高级管理员 */
	SENIOR				(EnumIndex.getNext(),     "02",  true,    "AdminUserType.SENIOR", CssClassType.SUCCESS),
	 /**一般管理员 */
	GENERAL				(EnumIndex.getNext(),     "03",  true,    "AdminUserType.GENERAL", CssClassType.INFO);

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;
    private String msg;

    private AdminUserType(int id, String key, boolean normalShow,String msg,  CssClassType classType) {
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
    
    public AdminUserType[] list() {
    	return AdminUserType.values();
    }
    
    public static AdminUserType valueOf(int id) {
    	for(AdminUserType type : AdminUserType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static AdminUserType keyOf(String key) {
    	for(AdminUserType type : AdminUserType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
