package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T103MUserCheckHistory extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME           = "m_user_check_history";
    public static final String  TABLE_NAME_OMT       = "T103";
    public static final String TABLE_SEQ             = TABLE_NAME_OMT;
    public static final String TABLE_GROUP           = GROUP_ORDER;
    public static final String COL_ID                = "id";
    public static final String COL_USER_ID           = "user_id";
    public static final String COL_CHECK_STATUS      = "check_status";
    public static final String COL_CHECK_DATE        = "check_date";
    public static final String COL_MEMO              = "memo";
    public static final String COL_CREATED_BY        = "created_by";
    public static final String COL_CREATED_AT        = "created_at";
    public static final String COL_UPDATED_BY        = "updated_by";
    public static final String COL_UPDATED_AT        = "updated_at";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,                  true,     MySqlType.CHARACTER_VARYING.getType(), 50,  null, null, false,  "", getLabelName(TABLE_NAME, COL_ID),          getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_USER_ID,             false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false , "", getLabelName(TABLE_NAME, COL_USER_ID),     getPlaceholder(TABLE_NAME, COL_USER_ID)),
        new ColumnInfoForm(COL_CHECK_STATUS,        false,    MySqlType.CHARACTER_VARYING.getType(), 3 ,  null, null, false , "", getLabelName(TABLE_NAME, COL_CHECK_STATUS),    getPlaceholder(TABLE_NAME, COL_CHECK_STATUS)),
        new ColumnInfoForm(COL_CHECK_DATE,          false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false , "", getLabelName(TABLE_NAME, COL_CHECK_DATE),     getPlaceholder(TABLE_NAME, COL_CHECK_DATE)),
        new ColumnInfoForm(COL_MEMO,                false,    MySqlType.TEXT.getType(),             null, null, null, true ,  "", getLabelName(TABLE_NAME, COL_MEMO),         getPlaceholder(TABLE_NAME, COL_MEMO)),
        new ColumnInfoForm(COL_CREATED_BY,          false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_CREATED_BY),     getPlaceholder(TABLE_NAME, COL_CREATED_BY)),
        new ColumnInfoForm(COL_CREATED_AT,          false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_CREATED_AT),     getPlaceholder(TABLE_NAME, COL_CREATED_AT)),
        new ColumnInfoForm(COL_UPDATED_BY,          false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_UPDATED_BY),     getPlaceholder(TABLE_NAME, COL_UPDATED_BY)),
        new ColumnInfoForm(COL_UPDATED_AT,          false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_UPDATED_AT),     getPlaceholder(TABLE_NAME, COL_UPDATED_AT)),
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
