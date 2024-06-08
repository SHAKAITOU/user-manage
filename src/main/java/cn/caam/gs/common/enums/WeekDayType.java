package cn.caam.gs.common.enums;

import java.util.Calendar;



public enum WeekDayType implements EnumImpl {

    SUNDAY        (Calendar.SUNDAY,         "SUNDAY",     true, "WeekDayType.SUNDAY", CssClassType.DANGER),
    MONDAY        (Calendar.MONDAY,         "MONDAY",     true, "WeekDayType.MONDAY", CssClassType.CONTEXT),
    TUESDAY        (Calendar.TUESDAY,         "TUESDAY",     true, "WeekDayType.TUESDAY", CssClassType.CONTEXT),
    WEDNESDAY    (Calendar.WEDNESDAY,     "WEDNESDAY",true, "WeekDayType.WEDNESDAY", CssClassType.CONTEXT),
    THURSDAY    (Calendar.THURSDAY,     "THURSDAY", true, "WeekDayType.THURSDAY", CssClassType.CONTEXT),
    FRIDAY        (Calendar.FRIDAY,         "FRIDAY",     true, "WeekDayType.FRIDAY", CssClassType.CONTEXT),
    SATURDAY    (Calendar.SATURDAY,     "SATURDAY", true, "WeekDayType.SATURDAY", CssClassType.INFO);
    
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private WeekDayType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public WeekDayType[] list() {
        return WeekDayType.values();
    }
    
    public static WeekDayType valueOf(int id) {
        for(WeekDayType type : WeekDayType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public static WeekDayType keyOf(String key) {
        for(WeekDayType type : WeekDayType.values()) {
            if(key.equals(type.getKey())) {
                return type;
            }
        }
        
        return null;
    }
}
