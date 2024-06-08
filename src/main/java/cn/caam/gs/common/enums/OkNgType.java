package cn.caam.gs.common.enums;

public enum OkNgType implements EnumImpl {

    NG    (EnumIndex.startIndex(0),     "ng",     true, "OkNgType.NG", CssClassType.DANGER),
    OK    (EnumIndex.getNext(),         "ok",     true, "OkNgType.OK", CssClassType.SUCCESS),
    ALL    (EnumIndex.getNext(),        "all",    false, "OkNgType.ALL", CssClassType.PRIMARY);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private OkNgType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public OkNgType[] list() {
        return OkNgType.values();
    }
    
    public static OkNgType valueOf(int id) {
        for(OkNgType type : OkNgType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public static OkNgType keyOf(String key) {
        for(OkNgType type : OkNgType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
