package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T001MFixedValue extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_fixed_value";
    public static final String  TABLE_NAME_OMT    = "T001";
    public static final String TABLE_SEQ         = TABLE_NAME_OMT;
    public static final String TABLE_GROUP       = GROUP_MAINTEN;
    public static final String COL_CODE            = "code";
    public static final String COL_VALUE           = "value";
    public static final String COL_NAME            = "name";
    public static final String COL_SHOW_INDEX      = "show_index";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            new ColumnInfoForm(COL_CODE,        true,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, false, "", getLabelName(TABLE_NAME, COL_CODE),       getPlaceholder(TABLE_NAME, COL_CODE)),
            new ColumnInfoForm(COL_VALUE,       true,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, false, "", getLabelName(TABLE_NAME, COL_VALUE),      getPlaceholder(TABLE_NAME, COL_VALUE)),
            new ColumnInfoForm(COL_NAME,        false,   MySqlType.CHARACTER_VARYING.getType(), 255, null, null, false, "", getLabelName(TABLE_NAME, COL_NAME),       getPlaceholder(TABLE_NAME, COL_NAME)),
            new ColumnInfoForm(COL_SHOW_INDEX,  false,   MySqlType.INTEGER.getType(),          null, null, null, false, "", getLabelName(TABLE_NAME, COL_SHOW_INDEX), getPlaceholder(TABLE_NAME, COL_SHOW_INDEX)),
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
