package cn.caam.gs.domain.tabledef;

import cn.caam.gs.common.enums.CssClassType;

public enum DbGroupTableType {

	G0_MAINTEN 					("维护系", CssClassType.DANGER),
	G1_MASTER 					("主信息系", CssClassType.INFO),
	G2_ORDER 					("订单系", CssClassType.WARNING),

	;
    private String group;
    private CssClassType cssClassType;


    private DbGroupTableType(String group, CssClassType cssClassType) {
        this.group = group;
        this.cssClassType = cssClassType;
    }

    public CssClassType getCssClassType() {
        return cssClassType;
    }
    
    public String getGroupName() {
        return group;
    }
   
    public DbGroupTableType[] list() {
    	return DbGroupTableType.values();
    }
    
    public static DbGroupTableType nameOf(String group) {
    	for(DbGroupTableType type : DbGroupTableType.values()) {
    		if(group.equals(type.getGroupName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
