package cn.caam.gs.common.enums;

public enum AvailabilityType implements EnumImpl {

	PRESENCE 		(EnumIndex.startIndex(1), 	"presence", 		true,	"AvailabilityType.PRESENCE", CssClassType.SUCCESS),
	ABSENCE			(EnumIndex.getNext(), 		"absence", 			true,	"AvailabilityType.ABSENCE", CssClassType.LIGHT),
	ALL				(EnumIndex.getNext(), 		"all", 				false, 	"AvailabilityType.ALL", CssClassType.PRIMARY);
	
    /** type. */
    private int id;
    private String key;
    private String msg;
    private boolean normalShow;
    private CssClassType classType;

    private AvailabilityType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public AvailabilityType[] list() {
    	return AvailabilityType.values();
    }
    
    public static AvailabilityType valueOf(int id) {
    	for(AvailabilityType type : AvailabilityType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static AvailabilityType keyOf(String key) {
    	for(AvailabilityType type : AvailabilityType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
