package cn.caam.gs.manage.dbmaintenance.table.impl;

import cn.caam.gs.common.html.HtmlBaseHelper;

public class BaseTableDef {
    
    protected String getContext(String tableName) {
        return HtmlBaseHelper.getContext(tableName);
    }
    protected String getContext(String tableName, String columName) {
        return HtmlBaseHelper.getContext(tableName + "." + columName);
    }
}
