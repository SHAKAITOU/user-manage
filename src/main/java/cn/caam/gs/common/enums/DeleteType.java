package cn.caam.gs.common.enums;

public enum DeleteType {
	  UNDELETED		(0, "正常"),
	  DELETED 		(1, "已删除")
	  ;

    /** id. */
    private int key;
    
    private String msg;

    private DeleteType(int key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public int getkey() {
        return key;
    }
    
    public String getMsg() {
        return msg;
    }

    public DeleteType[] list() {
    	return DeleteType.values();
    }
    
    public static DeleteType keyOf(int key) {
    	for(DeleteType type : DeleteType.values()) {
    		if(key == type.key) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
