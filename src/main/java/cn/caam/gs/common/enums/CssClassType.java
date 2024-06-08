package cn.caam.gs.common.enums;

public enum CssClassType {

    DANGER        ("danger"),
    INFO        ("info"),
    SUCCESS        ("success"),
    WARNING        ("warning"),
    PRIMARY        ("primary"),
    SECONDARY    ("secondary"),
    DARK        ("dark"),
    GRAY        ("gray"),
    LIGHT        ("light"),
    PURPLE        ("purple"),
    COFFEE        ("coffee"),
    CONTEXT        ("context"),
    ;
    
    /** type. */
    private String key;

    private CssClassType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public CssClassType[] list() {
        return CssClassType.values();
    }
    
    public static CssClassType keyOf(String key) {
        for(CssClassType type : CssClassType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
