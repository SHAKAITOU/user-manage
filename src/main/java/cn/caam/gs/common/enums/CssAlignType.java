package cn.caam.gs.common.enums;

public enum CssAlignType {

    LEFT        ("left"),
    CENTER      ("center"),
    RIGHT       ("right"),
    ;
    
    /** type. */
    private String key;

    private CssAlignType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public CssAlignType[] list() {
        return CssAlignType.values();
    }
    
    public static CssAlignType keyOf(String key) {
        for(CssAlignType type : CssAlignType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
