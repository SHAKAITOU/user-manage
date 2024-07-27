package cn.caam.gs.common.enums;

public enum SmsSendResultType implements EnumImpl {

	OK			(EnumIndex.startIndex(1), 	"ok", 		true, "MailSendResultType.OK", CssClassType.SUCCESS),
	NG_AUTH		(EnumIndex.getNext(), 		"ngAuth", 	true, "MailSendResultType.NG_AUTH", CssClassType.DANGER),
	NG_SERVER	(EnumIndex.getNext(), 		"ngServer", true, "MailSendResultType.NG_SERVER", CssClassType.DANGER),
	NG_OTHER	(EnumIndex.getNext(), 		"ngOther",  true, "MailSendResultType.NG_OTHER", CssClassType.DANGER),
	ALL			(EnumIndex.getNext(), 		"all", 		false, "MailSendResultType.ALL", CssClassType.PRIMARY);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private SmsSendResultType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public SmsSendResultType[] list() {
    	return SmsSendResultType.values();
    }
    
    public static SmsSendResultType valueOf(int id) {
    	for(SmsSendResultType type : SmsSendResultType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static SmsSendResultType keyOf(String key) {
    	for(SmsSendResultType type : SmsSendResultType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
