package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.common.html.HtmlBaseHelper;

public class BaseTableDef {
    
    protected static String getContext(String tableName) {
        return HtmlBaseHelper.getContext(tableName);
    }
    protected static String getLabelName(String tableName, String columName) {
        return HtmlBaseHelper.getContext(tableName + "." + columName);
    }
    
    protected static String getPlaceholder(String tableName, String columName) {
    	try {
    		return HtmlBaseHelper.getContext(tableName + "." + columName + ".placeholder");
    	}catch(Error exception) {
    		System.out.println(tableName+"."+columName+".placeholder is not existed!");
    		throw exception;
    	}
    }
}
