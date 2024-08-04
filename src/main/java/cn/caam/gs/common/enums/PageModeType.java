package cn.caam.gs.common.enums;

public enum PageModeType {

	VIEW(EnumIndex.startIndex(1)),
	EDIT(EnumIndex.getNext());

    /** type. */
    private int id;

    private PageModeType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
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

