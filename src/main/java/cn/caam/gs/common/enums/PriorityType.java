package cn.caam.gs.common.enums;

public enum PriorityType implements EnumImpl {

    
    TOP             (EnumIndex.startIndex(1),     "top",                 true,    "PriorityType.TOP", CssClassType.DANGER),
    HIGH             (EnumIndex.getNext(),         "high",             true,    "PriorityType.HIGH", CssClassType.WARNING),
    MIDDLE            (EnumIndex.getNext(),         "middle",             true,    "PriorityType.MIDDLE", CssClassType.INFO),
    LOW                (EnumIndex.getNext(),         "low",                 true,     "PriorityType.LOW", CssClassType.SUCCESS),
    LOWEST            (EnumIndex.getNext(),         "lowest",             true,     "PriorityType.LOWEST", CssClassType.LIGHT);
    
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private PriorityType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public PriorityType[] list() {
        return PriorityType.values();
    }
    
    public static PriorityType valueOf(int id) {
        for(PriorityType type : PriorityType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public static PriorityType keyOf(String key) {
        for(PriorityType type : PriorityType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
