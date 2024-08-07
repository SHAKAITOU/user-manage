package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T104MUserTypeSettings extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME           = "m_user_type_settings";
    public static final String  TABLE_NAME_OMT       = "T104";
    public static final String TABLE_SEQ             = TABLE_NAME_OMT;
    public static final String TABLE_GROUP           = GROUP_ORDER;
    public static final String COL_USER_TYPE         = "user_type";
    public static final String COL_FEE_AMOUNT        = "fee_amount";
    public static final String COL_EFFECTIVE_YEAR    = "effective_year";
    public static final String COL_CREATED_BY        = "created_by";
    public static final String COL_CREATED_AT        = "created_at";
    public static final String COL_UPDATED_BY        = "updated_by";
    public static final String COL_UPDATED_AT        = "updated_at";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_USER_TYPE,           true,     MySqlType.CHARACTER_VARYING.getType(), 6 , null, null, false ,  "", getLabelName(TABLE_NAME, COL_USER_TYPE),      getPlaceholder(TABLE_NAME, COL_USER_TYPE)),
        new ColumnInfoForm(COL_FEE_AMOUNT,          false,    MySqlType.NUMERIC.getType(),         null , 10,   2  , false,   "", getLabelName(TABLE_NAME, COL_FEE_AMOUNT),     getPlaceholder(TABLE_NAME, COL_FEE_AMOUNT)),
        new ColumnInfoForm(COL_EFFECTIVE_YEAR,      false,    MySqlType.INTEGER.getType(),         null , 2,    0,   false ,  "", getLabelName(TABLE_NAME, COL_EFFECTIVE_YEAR), getPlaceholder(TABLE_NAME, COL_EFFECTIVE_YEAR)),
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
            {name+(i++),     new String[] {COL_USER_TYPE}},
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
