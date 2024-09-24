package cn.caam.gs.common.enums;

public enum PageModeType {

	VIEW(EnumIndex.startIndex(1), "view"),
	EDIT_BY_USER(EnumIndex.getNext(), "edit_by_user"),
	EDIT_BY_ADMIN(EnumIndex.getNext(), "edit_by_admin"),
	INSERT_BY_ADMIN(EnumIndex.getNext(), "insert_by_admin");

    /** type. */
    private int id;
    
    private String key;

    private PageModeType(int id, String key) {
        this.id = id;
        this.key = key;
    }
    
    public int getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }
    
    public PageModeType valueOf(int id) {
    	for(PageModeType type : PageModeType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}

