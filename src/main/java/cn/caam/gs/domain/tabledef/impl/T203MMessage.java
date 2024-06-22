package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T203MMessage extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME           = "m_message";
    public static final String  TABLE_NAME_OMT       = "T203";
    public static final String TABLE_SEQ             = TABLE_NAME_OMT;
    public static final String TABLE_GROUP           = GROUP_ORDER;
    public static final String COL_ID                = "id";
    public static final String COL_MSG_TYPE          = "msg_type";
    public static final String COL_USER_ID           = "user_id";
    public static final String COL_TITLE             = "title";
    public static final String COL_MSG               = "msg";
    public static final String COL_REGIST_DATE       = "regist_date";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,                  true,     MySqlType.CHARACTER_VARYING.getType(), 50,  null, null, false,  "", getLabelName(TABLE_NAME, COL_ID),          getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_MSG_TYPE,            false,    MySqlType.CHARACTER_VARYING.getType(), 3 ,  null, null, false , "", getLabelName(TABLE_NAME, COL_MSG_TYPE),    getPlaceholder(TABLE_NAME, COL_MSG_TYPE)),
        new ColumnInfoForm(COL_USER_ID,             false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_USER_ID),     getPlaceholder(TABLE_NAME, COL_USER_ID)),
        new ColumnInfoForm(COL_TITLE,               false,    MySqlType.CHARACTER_VARYING.getType(), 255 , null, null, false ,  "", getLabelName(TABLE_NAME, COL_TITLE),     getPlaceholder(TABLE_NAME, COL_TITLE)),
        new ColumnInfoForm(COL_MSG,                 false,    MySqlType.TEXT.getType(),             null, null, null, false , "", getLabelName(TABLE_NAME, COL_MSG),         getPlaceholder(TABLE_NAME, COL_MSG)),
        new ColumnInfoForm(COL_REGIST_DATE,         false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_REGIST_DATE), getPlaceholder(TABLE_NAME, COL_REGIST_DATE)),
            
    };
    public ColumnInfoForm[] columnInfos() {
        
        return cols;
    }
    
    public Object[][] indexInfos() {
        int i = 1;
        String name = TABLE_NAME+"_idx";
        Object[][] cols = new Object[][] {
            // name | columnNames
            {name+(i++),     new String[] {COL_USER_ID}},
            {name+(i++),     new String[] {COL_MSG_TYPE}},
        };
        return cols;
    }
    
    public SequenceInfoForm getSequenceInfo() {
        return null;
    }
    
    public String getTableNameOmt() {
        return TABLE_NAME_OMT;
    }
    
    public String getTableSeq() {
        return TABLE_SEQ;
    }
    
    public String getTableName() {
        return TABLE_NAME;
    }
    
    public String getTableComment() {
        return getContext(TABLE_NAME);
    }
    
    public String getTableGroup() {
        return TABLE_GROUP;
    }
    
    public static ColumnInfoForm getColumnInfo(String columnName) {
        for (ColumnInfoForm form : cols) {
            if (form.getName().equals(columnName)) {
                return form;
            }
        }
        return null;
    }
}
