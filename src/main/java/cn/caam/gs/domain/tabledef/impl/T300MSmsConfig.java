package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T300MSmsConfig extends BaseTableDef implements BaseDdl{
	
	public static final String TABLE_NAME 			= "m_sms_config";
	public static final String TABLE_NAME_OMT		= "T300";
	public static final String TABLE_SEQ 			= TABLE_NAME_OMT;
	public static final String TABLE_GROUP 			= GROUP_MASTER;
	public static final String COL_ID               = "id";
	public static final String COL_TEMPLATE_TYPE    = "template_type";
	public static final String COL_TEMPLATE_ID      = "template_id";
	public static final String COL_TEMPLATE_NAME    = "template_name";
	public static final String COL_API_URL          = "api_url";
	public static final String COL_APP_KEY			= "app_key";
	public static final String COL_APP_SECRET		= "app_secret";
	public static final String COL_SENDER			= "sender";
	public static final String COL_SIGNATURE		= "signature";
	public static final String COL_CREATED_BY       = "created_by";
    public static final String COL_CREATED_AT       = "created_at";
    public static final String COL_UPDATED_BY       = "updated_by";
    public static final String COL_UPDATED_AT       = "updated_at";
	
	public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
	        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,               true,     MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, false, "", getLabelName(TABLE_NAME, COL_ID),              getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_TEMPLATE_TYPE,    false,    MySqlType.CHARACTER_VARYING.getType(), 3 ,  null, null, true , "", getLabelName(TABLE_NAME, COL_TEMPLATE_TYPE),   getPlaceholder(TABLE_NAME, COL_TEMPLATE_TYPE)),
        new ColumnInfoForm(COL_TEMPLATE_ID,      false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_TEMPLATE_ID),     getPlaceholder(TABLE_NAME, COL_TEMPLATE_ID)),
        new ColumnInfoForm(COL_TEMPLATE_NAME,    false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_TEMPLATE_NAME),   getPlaceholder(TABLE_NAME, COL_TEMPLATE_NAME)),
        new ColumnInfoForm(COL_API_URL,          false,    MySqlType.CHARACTER_VARYING.getType(),255 , null, null, false, "", getLabelName(TABLE_NAME, COL_API_URL),         getPlaceholder(TABLE_NAME, COL_API_URL)),
        new ColumnInfoForm(COL_APP_KEY,          false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_APP_KEY),         getPlaceholder(TABLE_NAME, COL_APP_KEY)),
        new ColumnInfoForm(COL_APP_SECRET,       false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_APP_SECRET),      getPlaceholder(TABLE_NAME, COL_APP_SECRET)),
        new ColumnInfoForm(COL_SENDER,           false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_SENDER),          getPlaceholder(TABLE_NAME, COL_SENDER)),
        new ColumnInfoForm(COL_SIGNATURE,        false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, false, "", getLabelName(TABLE_NAME, COL_SIGNATURE),       getPlaceholder(TABLE_NAME, COL_SIGNATURE)),
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
