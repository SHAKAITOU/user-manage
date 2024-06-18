package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T200MOrder extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_order";
    public static final String  TABLE_NAME_OMT    = "T200";
    public static final String TABLE_SEQ         = TABLE_NAME_OMT;
    public static final String TABLE_GROUP       = GROUP_ORDER;
    public static final String COL_ID              = "id";
    public static final String COL_USER_ID         = "user_id";
    public static final String COL_PAY_PATH        = "pay_path";
    public static final String COL_ORDER_METHOD    = "order_method";
    public static final String COL_ORDER_TYPE      = "order_type";
    public static final String COL_PAY_TYPE        = "pay_type";
    public static final String COL_ORDER_AMOUNT    = "order_amount";
    public static final String COL_PAY_AMOUNT      = "pay_amount";
    public static final String COL_PAY_DATE        = "pay_date";
    public static final String COL_CHECK_DATE      = "check_date";
    public static final String COL_CHECK_STATUS    = "check_status";
    public static final String COL_REFUND_DATE     = "refund_date";
    public static final String COL_REFUND_STATUS   = "refund_status";
    public static final String COL_MEMO            = "memo";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            new ColumnInfoForm(COL_ID,           true,     MySqlType.CHARACTER_VARYING.getType(), 30, null, null, false, "", getLabelName(TABLE_NAME, COL_ID),              getPlaceholder(TABLE_NAME, COL_ID)),
            new ColumnInfoForm(COL_USER_ID,      false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, false, "", getLabelName(TABLE_NAME, COL_USER_ID),         getPlaceholder(TABLE_NAME, COL_USER_ID)),
            new ColumnInfoForm(COL_PAY_PATH,     false,    MySqlType.CHARACTER_VARYING.getType(), 3 , null, null, false, "", getLabelName(TABLE_NAME, COL_PAY_PATH),        getPlaceholder(TABLE_NAME, COL_PAY_PATH)),
            new ColumnInfoForm(COL_ORDER_METHOD, false,    MySqlType.CHARACTER_VARYING.getType(), 3 , null, null, false, "", getLabelName(TABLE_NAME, COL_ORDER_METHOD),    getPlaceholder(TABLE_NAME, COL_ORDER_METHOD)),
            new ColumnInfoForm(COL_ORDER_TYPE,   false,    MySqlType.CHARACTER_VARYING.getType(), 3 , null, null, false, "", getLabelName(TABLE_NAME, COL_ORDER_TYPE),      getPlaceholder(TABLE_NAME, COL_ORDER_TYPE)),
            new ColumnInfoForm(COL_PAY_TYPE,     false,    MySqlType.CHARACTER_VARYING.getType(), 3 , null, null, false, "", getLabelName(TABLE_NAME, COL_PAY_TYPE),        getPlaceholder(TABLE_NAME, COL_PAY_TYPE)),
            new ColumnInfoForm(COL_ORDER_AMOUNT, false,    MySqlType.NUMERIC.getType(),        null , 13,   3   , false, "", getLabelName(TABLE_NAME, COL_ORDER_AMOUNT),    getPlaceholder(TABLE_NAME, COL_ORDER_AMOUNT)),
            new ColumnInfoForm(COL_PAY_AMOUNT,   false,    MySqlType.NUMERIC.getType(),        null , 13,   3   , false, "", getLabelName(TABLE_NAME, COL_PAY_AMOUNT),      getPlaceholder(TABLE_NAME, COL_PAY_AMOUNT)),
            new ColumnInfoForm(COL_PAY_DATE,     false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, false, "", getLabelName(TABLE_NAME, COL_PAY_DATE),        getPlaceholder(TABLE_NAME, COL_PAY_DATE)),
            new ColumnInfoForm(COL_CHECK_DATE,   false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_CHECK_DATE),      getPlaceholder(TABLE_NAME, COL_CHECK_DATE)),
            new ColumnInfoForm(COL_CHECK_STATUS, false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_CHECK_STATUS),    getPlaceholder(TABLE_NAME, COL_CHECK_STATUS)),
            new ColumnInfoForm(COL_REFUND_DATE,  false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_REFUND_DATE),     getPlaceholder(TABLE_NAME, COL_REFUND_DATE)),
            new ColumnInfoForm(COL_REFUND_STATUS,false,    MySqlType.CHARACTER_VARYING.getType(), 20, null, null, true , "", getLabelName(TABLE_NAME, COL_REFUND_STATUS),   getPlaceholder(TABLE_NAME, COL_REFUND_STATUS)),
            new ColumnInfoForm(COL_MEMO,         false,    MySqlType.CHARACTER_VARYING.getType(),255, null, null, true , "", getLabelName(TABLE_NAME, COL_MEMO),            getPlaceholder(TABLE_NAME, COL_MEMO)),
            
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
    public static ColumnInfoForm[] getColumnInfos() {
        return cols;
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
