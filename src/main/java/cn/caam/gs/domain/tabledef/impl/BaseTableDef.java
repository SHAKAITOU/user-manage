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
        return HtmlBaseHelper.getContext(tableName + "." + columName + ".placeholder");
    }
}
