package cn.caam.gs.common.enums;

public enum BuyPriceAnalysisType implements EnumImpl {

	STORE_TOTAL_AMOUNT_BAR	(EnumIndex.startIndex(1), 	"storeTotalAmtBar", 	true, "BuyPriceAnalysisType.STORE_TOTAL_AMOUNT_BAR", CssClassType.PRIMARY),
	STORE_TOTAL_AMOUNT_PIE	(EnumIndex.getNext(), 		"storeTotalAmtPie", 	true, "BuyPriceAnalysisType.STORE_TOTAL_AMOUNT_PIE", CssClassType.PRIMARY),
	STORE_BUY_PERIOD_LINE	(EnumIndex.getNext(), 		"storeBuyPeriodLine", 	true, "BuyPriceAnalysisType.STORE_BUY_PERIOD_LINE", CssClassType.PRIMARY),
	ALL	(EnumIndex.getNext(), 		"all", 		false, "BuyPriceAnalysisType.ALL", CssClassType.INFO);
    

    /** type. */
    private int id;
    private String key;
    private boolean normalShow;
    private String msg;
    private CssClassType classType;

    private BuyPriceAnalysisType(int id, String key, boolean normalShow, String msg, CssClassType classType) {
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
    
    public BuyPriceAnalysisType[] list() {
    	return BuyPriceAnalysisType.values();
    }
    
    public static BuyPriceAnalysisType valueOf(int id) {
    	for(BuyPriceAnalysisType type : BuyPriceAnalysisType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public static BuyPriceAnalysisType keyOf(String key) {
    	for(BuyPriceAnalysisType type : BuyPriceAnalysisType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
