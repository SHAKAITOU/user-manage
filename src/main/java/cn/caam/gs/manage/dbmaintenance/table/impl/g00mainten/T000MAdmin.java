package cn.caam.gs.manage.dbmaintenance.table.impl.g00mainten;

import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;
import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;

public class T000MAdmin extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_admin";
    public static final String  TABLE_NAME_OMT    = "T000";
    public static final String TABLE_SEQ         = TABLE_NAME_OMT;
    public static final String TABLE_GROUP       = GROUP_MAINTEN;
    public static final String COL_ID              = "id";
    public static final String COL_NAME            = "name";
    public static final String COL_USER_TYPE       = "user_type";
    public static final String COL_PASSWORD        = "password";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,           true,     MySqlType.CHARACTER_VARYING.getType(), 20, null, null, false, "", getLabelName(TABLE_NAME, COL_ID),        getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_NAME,         false,    MySqlType.CHARACTER_VARYING.getType(), 70, null, null, false, "", getLabelName(TABLE_NAME, COL_NAME),      getPlaceholder(TABLE_NAME, COL_NAME)),
        new ColumnInfoForm(COL_USER_TYPE,    false,    MySqlType.CHARACTER_VARYING.getType(), 3 , null, null, false, "", getLabelName(TABLE_NAME, COL_USER_TYPE), getPlaceholder(TABLE_NAME, COL_USER_TYPE)),
        new ColumnInfoForm(COL_PASSWORD,     false,    MySqlType.CHARACTER_VARYING.getType(),200, null, null, false, "", getLabelName(TABLE_NAME, COL_PASSWORD),  getPlaceholder(TABLE_NAME, COL_PASSWORD))
    };
    public ColumnInfoForm[] columnInfos() {
        
        return cols;
    }
    
    public Object[][] indexInfos() {
        Object[][] cols = new Object[][] {
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
    
    public ColumnInfoForm getColumnInfo(String columnName) {
        for (ColumnInfoForm form : cols) {
            if (form.getName().equals(columnName)) {
                return form;
            }
        }
        return null;
    }
}
