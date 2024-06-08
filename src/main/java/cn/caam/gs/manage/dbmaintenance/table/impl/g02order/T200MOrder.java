package cn.caam.gs.manage.dbmaintenance.table.impl.g02order;

import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;
import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;

public class T200MOrder extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_order";
    public static final String  TABLE_NAME_OMT    = "T200";
    private static final String TABLE_SEQ         = TABLE_NAME_OMT;
    private static final String TABLE_GROUP       = GROUP_ORDER;
    private static final String COL_ID              = "id";
    private static final String COL_USER_ID         = "user_id";
    private static final String COL_PAY_PATH        = "pay_path";
    private static final String COL_ORDER_METHOD    = "order_method";
    private static final String COL_ORDER_TYPE      = "order_type";
    private static final String COL_PAY_TYPE        = "pay_type";
    private static final String COL_ORDER_AMOUNT    = "order_amount";
    private static final String COL_PAY_AMOUNT      = "pay_amount";
    private static final String COL_PAY_DATE        = "pay_date";
    private static final String COL_CHECK_DATE      = "check_date";
    private static final String COL_CHECK_STATUS    = "check_status";
    private static final String COL_REFUND_DATE     = "refund_date";
    private static final String COL_REFUND_STATUS   = "refund_status";
    private static final String COL_MEMO            = "memo";
    
    public Object[][] columnInfos() {
        Object[][] cols = new Object[][] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            {COL_ID,           true,     MySqlType.CHARACTER_VARYING, 30, null, null, false, "", getContext(TABLE_NAME, COL_ID)},
            {COL_USER_ID,      false,    MySqlType.CHARACTER_VARYING, 20, null, null, false, "", getContext(TABLE_NAME, COL_USER_ID)},
            {COL_PAY_PATH,     false,    MySqlType.CHARACTER_VARYING, 3 , null, null, false, "", getContext(TABLE_NAME, COL_PAY_PATH)},
            {COL_ORDER_METHOD, false,    MySqlType.CHARACTER_VARYING, 3 , null, null, false, "", getContext(TABLE_NAME, COL_ORDER_METHOD)},
            {COL_ORDER_TYPE,   false,    MySqlType.CHARACTER_VARYING, 3 , null, null, false, "", getContext(TABLE_NAME, COL_ORDER_TYPE)},
            {COL_PAY_TYPE,     false,    MySqlType.CHARACTER_VARYING, 3 , null, null, false, "", getContext(TABLE_NAME, COL_PAY_TYPE)},
            {COL_ORDER_AMOUNT, false,    MySqlType.NUMERIC,        null , 13,   3   , false, "", getContext(TABLE_NAME, COL_ORDER_AMOUNT)},
            {COL_PAY_AMOUNT,   false,    MySqlType.NUMERIC,        null , 13,   3   , false, "", getContext(TABLE_NAME, COL_PAY_AMOUNT)},
            {COL_PAY_DATE,     false,    MySqlType.CHARACTER_VARYING, 20, null, null, false, "", getContext(TABLE_NAME, COL_PAY_DATE)},
            {COL_CHECK_DATE,   false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_CHECK_DATE)},
            {COL_CHECK_STATUS, false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_CHECK_STATUS)},
            {COL_REFUND_DATE,  false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_REFUND_DATE)},
            {COL_REFUND_STATUS,false,    MySqlType.CHARACTER_VARYING, 20, null, null, true , "", getContext(TABLE_NAME, COL_REFUND_STATUS)},
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
