package cn.caam.gs.common.enums;

public enum CssGridsType {

    G1        (1),
    G2        (2),
    G3        (3),
    G4        (4),
    G5        (5),
    G6        (6),
    G7        (7),
    G8        (8),
    G9        (9),
    G10        (10),
    G11        (11),
    G12        (12),
    ;
    
    /** type. */
    private int key;

    private CssGridsType(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public CssGridsType[] list() {
        return CssGridsType.values();
    }
    
    public static CssGridsType keyOf(int key) {
        for(CssGridsType type : CssGridsType.values()) {
            if(key == type.getKey()) {
                return type;
            }
        }
        
        return null;
    }
}
