package cn.caam.gs.app.util;

public enum SessionConstants {
	
	LOGIN_INFO("loginInfo", true),
	
	JAVASCRIPT_SET_INFO("javaScriptSetInfo", true),
	
	MEDIA_WIDTH("mediaWidth", false),
	
	MEDIA_HEIGHT("mediaHeight", false),
	
	THEMES_TYPE("themesType", false),
	
	LOCAL_SERVICE_URL("localServiceUrl", false),
	
	IS_MOBILE("isMobile", false),
	
	CATEGORY_DIV_ID("categoryDiv", true),
	
	MAIN_FORM_ID("mainForm", true),
	
	CATEGORY_DIV_HEIGHT("categoryDivHeight", true),

	CART_LIST("cartList", true),
	
	USER_REGIST("userRegist", true),
	
	RECEIPT_IMG_ANALYSIS_RESULT("receiptImgAnalysisResult", true),
	
	FIXED_VALUE("fixedValue", true),
	   
    USER_LIST_OUT_PUT("userListOutput", true),
	
	ORDER_LIST_OUT_PUT("orderListOutput", true),
    
    UN_READ_MESSAGE_CNT("unReadMsgCnt", true),
    
    ORDER_NOT_FINISH_CNT("orderNotFinishCnt", true),
    
    ORDER_WAIT_CNT("orderWaitCnt", true),
    
    ORDER_REVIEW_CNT("orderReviewCnt", true),
	
	MESSAGE_LIST_OUT_PUT("messageListOutput", true),
	
	
	;
	
	private String value;
	private boolean logoutClearFLg;
	private SessionConstants(String value, boolean logoutClearFLg) {
		this.value = value;
		this.logoutClearFLg = logoutClearFLg;
	}
	
	public String getValue() {
        return value;
    }
	
	public boolean getLogoutClearFLg() {
        return logoutClearFLg;
    }
    
    public static SessionConstants keyOf(String value) {
    	for(SessionConstants type : SessionConstants.values()) {
    		if(value == type.getValue()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
