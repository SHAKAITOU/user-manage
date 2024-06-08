package cn.caam.gs.common.enums;

public enum ExecuteReturnType {

	OK(EnumIndex.startIndex(1)),
	NG(EnumIndex.getNext()),
	DEFAULT(EnumIndex.getNext());

    /** type. */
    private int id;

    private ExecuteReturnType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public ExecuteReturnType valueOf(int id) {
    	for(ExecuteReturnType type : ExecuteReturnType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}

