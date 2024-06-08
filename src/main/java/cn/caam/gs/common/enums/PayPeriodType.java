package cn.caam.gs.common.enums;

public enum PayPeriodType implements EnumImpl {

    N1            (300,         "N1",         true,        "PayPeriodType.N1", CssClassType.DANGER),
    N3            (850,         "N3",         true,        "PayPeriodType.N3", CssClassType.DANGER),
    N6            (1600,         "N6",         true,        "PayPeriodType.N6", CssClassType.DANGER),
    N12           (3000,         "N12",         true,        "PayPeriodType.N12", CssClassType.DANGER),
    P1            (600,         "P1",         true,        "PayPeriodType.P1", CssClassType.INFO),
    P3            (1700,         "P3",         true,        "PayPeriodType.P3", CssClassType.INFO),
    P6            (3200,         "P6",         true,        "PayPeriodType.P6", CssClassType.INFO),
    P12           (6000,         "P12",         true,        "PayPeriodType.P12", CssClassType.INFO),
    ;
    

    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private PayPeriodType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public PayPeriodType[] list() {
        return PayPeriodType.values();
    }
    
    public static PayPeriodType valueOf(int id) {
        for(PayPeriodType type : PayPeriodType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public static PayPeriodType keyOf(String key) {
        for(PayPeriodType type : PayPeriodType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
