package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T400MSystemConfig extends BaseTableDef implements BaseDdl{
	
	public static final String TABLE_NAME 			= "m_system_config";
	public static final String TABLE_NAME_OMT		= "T500";
	public static final String TABLE_SEQ 			= TABLE_NAME_OMT;
	public static final String TABLE_GROUP 			= GROUP_MASTER;
	public static final String COL_ID               = "id";
	public static final String COL_PAY_QRCODE       = "pay_qrcode";
	public static final String COL_OPENING_BANK     = "opening_bank";
	public static final String COL_ACCOUNT_NAME     = "account_name";
	public static final String COL_ACCOUNT_NUMBER   = "account_number";
	public static final String COL_CREDIT_CODE      = "credit_code";
	public static final String COL_CREATED_BY       = "created_by";
    public static final String COL_CREATED_AT       = "created_at";
    public static final String COL_UPDATED_BY       = "updated_by";
    public static final String COL_UPDATED_AT       = "updated_at";
	
	public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
	        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,               true,     MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, false, "", getLabelName(TABLE_NAME, COL_ID),              getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_PAY_QRCODE,       false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_PAY_QRCODE),      getPlaceholder(TABLE_NAME, COL_PAY_QRCODE)),
        new ColumnInfoForm(COL_OPENING_BANK,     false,    MySqlType.CHARACTER_VARYING.getType(), 128 ,null, null, true , "", getLabelName(TABLE_NAME, COL_OPENING_BANK),    getPlaceholder(TABLE_NAME, COL_OPENING_BANK)),
        new ColumnInfoForm(COL_ACCOUNT_NAME,     false,    MySqlType.CHARACTER_VARYING.getType(), 128 ,null, null, true , "", getLabelName(TABLE_NAME, COL_ACCOUNT_NAME),    getPlaceholder(TABLE_NAME, COL_ACCOUNT_NAME)),
        new ColumnInfoForm(COL_ACCOUNT_NUMBER,   false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true , "", getLabelName(TABLE_NAME, COL_ACCOUNT_NUMBER),  getPlaceholder(TABLE_NAME, COL_ACCOUNT_NUMBER)),
        new ColumnInfoForm(COL_CREDIT_CODE,      false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_CREDIT_CODE),     getPlaceholder(TABLE_NAME, COL_CREDIT_CODE)),
        new ColumnInfoForm(COL_CREATED_BY,       false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_CREATED_BY),     getPlaceholder(TABLE_NAME, COL_CREATED_BY)),
        new ColumnInfoForm(COL_CREATED_AT,       false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_CREATED_AT),     getPlaceholder(TABLE_NAME, COL_CREATED_AT)),
        new ColumnInfoForm(COL_UPDATED_BY,       false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_UPDATED_BY),     getPlaceholder(TABLE_NAME, COL_UPDATED_BY)),
        new ColumnInfoForm(COL_UPDATED_AT,       false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_UPDATED_AT),     getPlaceholder(TABLE_NAME, COL_UPDATED_AT)),
	        
	};
	public ColumnInfoForm[] columnInfos() {
		
		return cols;
	}
	
	public Object[][] indexInfos() {
		Object[][] cols = new Object[][] {
			// name | columnNames
		};
		
		return cols;
	}
	
	public SequenceInfoForm getSequenceInfo() {
		return null;
	}
	
	public String getTableSeq() {
		return TABLE_SEQ;
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	public String getTableNameOmt() {
		return TABLE_NAME_OMT;
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
