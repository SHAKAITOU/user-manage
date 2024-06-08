package cn.caam.gs.manage.dbmaintenance.table.impl.g02order;

import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;
import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;

public class T201MBill extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_bill";
    public static final String  TABLE_NAME_OMT    = "T201";
    private static final String TABLE_SEQ         = TABLE_NAME_OMT;
    private static final String TABLE_GROUP       = GROUP_ORDER;
    private static final String COL_ID              = "id";
    private static final String COL_USER_ID         = "user_id";
    private static final String COL_BILL_CODE       = "bill_code";
    private static final String COL_BILL_AMOUNT     = "bill_amount";
    private static final String COL_BILL_TITLE      = "bill_title";
    private static final String COL_CREDIT_CODE     = "credit_code";
    private static final String COL_BILL_DATE       = "bill_date";
    private static final String COL_BILL_STATUS     = "bill_status";
    private static final String COL_MEMO            = "memo";
    
    public Object[][] columnInfos() {
        Object[][] cols = new Object[][] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            {COL_ID,           true,     MySqlType.CHARACTER_VARYING, 30, null, null, false, "", getContext(TABLE_NAME, COL_ID)},
            {COL_USER_ID,      false,    MySqlType.CHARACTER_VARYING, 20, null, null, false, "", getContext(TABLE_NAME, COL_USER_ID)},
            {COL_BILL_CODE,    false,    MySqlType.CHARACTER_VARYING, 50, null, null,  true, "", getContext(TABLE_NAME, COL_BILL_CODE)},
            {COL_BILL_AMOUNT,  false,    MySqlType.NUMERIC,        null , 13,   3   , false, "", getContext(TABLE_NAME, COL_BILL_AMOUNT)},
            {COL_BILL_TITLE,   false,    MySqlType.CHARACTER_VARYING, 20, null, null, false, "", getContext(TABLE_NAME, COL_BILL_TITLE)},
            {COL_CREDIT_CODE,  false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_CREDIT_CODE)},
            {COL_BILL_DATE,    false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_BILL_DATE)},
            {COL_BILL_STATUS,  false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_BILL_STATUS)},
            {COL_MEMO,         false,    MySqlType.CHARACTER_VARYING,255, null, null, true , "", getContext(TABLE_NAME, COL_MEMO)},
            
        };
        
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
}
