package cn.caam.gs.common.enums;

public enum AdminMenuType {

    ACCOUNT                (EnumIndex.startIndex(1),     "account",                 "AdminMenuType.ACCOUNT"),
    CONTACT                (EnumIndex.getNext(),         "contact",                 "AdminMenuType.CONTACT"),
    WORK                (EnumIndex.getNext(),         "work",                 "AdminMenuType.WORK"),
    OTHER                (EnumIndex.getNext(),         "orther",                 "AdminMenuType.OTHER"),
    SYSTEM                (EnumIndex.getNext(),         "system",                 "AdminMenuType.SYSTEM"),
    LOGOUT                (EnumIndex.getNext(),         "logout",                 "AdminMenuType.LOGOUT"),
    ;
    
    /** type. */
    private int id;
    private String key;
    private String msg;

    private AdminMenuType(int id, String key, String msg) {
        this.id = id;
        this.key = key;
        this.msg = msg;
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
    
    public static AdminMenuType valueOf(int id) {
        for(AdminMenuType type : AdminMenuType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public static AdminMenuType keyOf(String key) {
        for(AdminMenuType type : AdminMenuType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
