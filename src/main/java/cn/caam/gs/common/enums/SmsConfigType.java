package cn.caam.gs.common.enums;

public enum SmsConfigType {
	REGIST_VERIFICATION_CODE	(EnumIndex.startIndex(1), "01",  true,    "SmsTemplateType.REGIST_VERIFICATION_CODE", CssClassType.INFO),
	COMMON_VERIFICATION_CODE	(EnumIndex.getNext(),     "02",  true,    "SmsTemplateType.COMMON_VERIFICATION_CODE", CssClassType.INFO),
	USER_REVIEW_OK				(EnumIndex.getNext(),     "03",  true,    "SmsTemplateType.USER_REVIEW_OK", CssClassType.INFO),
	USER_REVIEW_NG				(EnumIndex.getNext(),     "04",  true,    "SmsTemplateType.USER_REVIEW_NG", CssClassType.INFO),
	USER_REVIEW_RETURN			(EnumIndex.getNext(),     "05",  true,    "SmsTemplateType.USER_REVIEW_RETURN", CssClassType.INFO),
	ADMIN_CREATED_NOTIFY		(EnumIndex.getNext(),     "06",  true,    "SmsTemplateType.ADMIN_CREATED_NOTIFY", CssClassType.INFO);

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private CssClassType classType;
    private String msg;

    private SmsConfigType(int id, String key, boolean normalShow,String msg,  CssClassType classType) {
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
    
    public SmsConfigType[] list() {
    	return SmsConfigType.values();
    }
    
    public static SmsConfigType valueOf(int id) {
    	for(SmsConfigType type : SmsConfigType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static SmsConfigType keyOf(String key) {
    	for(SmsConfigType type : SmsConfigType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
