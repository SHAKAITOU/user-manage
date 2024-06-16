package cn.caam.gs.manage.dbmaintenance.table.impl.g02order;

import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;
import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;

public class T201MBill extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_bill";
    public static final String  TABLE_NAME_OMT    = "T201";
    public static final String TABLE_SEQ         = TABLE_NAME_OMT;
    public static final String TABLE_GROUP       = GROUP_ORDER;
    public static final String COL_ID              = "id";
    public static final String COL_USER_ID         = "user_id";
    public static final String COL_BILL_CODE       = "bill_code";
    public static final String COL_BILL_AMOUNT     = "bill_amount";
    public static final String COL_BILL_TITLE      = "bill_title";
    public static final String COL_CREDIT_CODE     = "credit_code";
    public static final String COL_BILL_DATE       = "bill_date";
    public static final String COL_BILL_STATUS     = "bill_status";
    public static final String COL_MEMO            = "memo";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,           true,     MySqlType.CHARACTER_VARYING.getType(), 30, null, null, false, "", getLabelName(TABLE_NAME, COL_ID),          getLabelName(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_USER_ID,      false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, false, "", getLabelName(TABLE_NAME, COL_USER_ID),     getLabelName(TABLE_NAME, COL_USER_ID)),
        new ColumnInfoForm(COL_BILL_CODE,    false,    MySqlType.CHARACTER_VARYING.getType(), 50, null, null,  true, "", getLabelName(TABLE_NAME, COL_BILL_CODE),   getLabelName(TABLE_NAME, COL_BILL_CODE)),
        new ColumnInfoForm(COL_BILL_AMOUNT,  false,    MySqlType.NUMERIC.getType(),        null , 13,   3   , false, "", getLabelName(TABLE_NAME, COL_BILL_AMOUNT), getLabelName(TABLE_NAME, COL_BILL_AMOUNT)),
        new ColumnInfoForm(COL_BILL_TITLE,   false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, false, "", getLabelName(TABLE_NAME, COL_BILL_TITLE),  getLabelName(TABLE_NAME, COL_BILL_TITLE)),
        new ColumnInfoForm(COL_CREDIT_CODE,  false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_CREDIT_CODE), getLabelName(TABLE_NAME, COL_CREDIT_CODE)),
        new ColumnInfoForm(COL_BILL_DATE,    false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_BILL_DATE),   getLabelName(TABLE_NAME, COL_BILL_DATE)),
        new ColumnInfoForm(COL_BILL_STATUS,  false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_BILL_STATUS), getLabelName(TABLE_NAME, COL_BILL_STATUS)),
        new ColumnInfoForm(COL_MEMO,         false,    MySqlType.CHARACTER_VARYING.getType(),255, null, null, true , "", getLabelName(TABLE_NAME, COL_MEMO),        getLabelName(TABLE_NAME, COL_MEMO)),
            
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
