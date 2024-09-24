package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T105MUserCard extends BaseTableDef implements BaseDdl{
	
	public static final String TABLE_NAME			= "m_user_card";
	public static final String TABLE_NAME_OMT		= "T105";
	public static final String TABLE_SEQ			= TABLE_NAME_OMT;
	public static final String TABLE_GROUP			= GROUP_MASTER;
	public static final String COL_ID				= "id";
	public static final String COL_USER_ID			= "user_id";
	public static final String COL_USER_CODE		= "user_code";
	public static final String COL_VALID_STATUS		= "valid_status";
	public static final String COL_VALID_START_DATE	= "valid_start_date";
	public static final String COL_VALID_END_DATE	= "valid_end_date";
	public static final String COL_CREATED_BY		= "created_by";
    public static final String COL_CREATED_AT		= "created_at";
    public static final String COL_UPDATED_BY		= "updated_by";
    public static final String COL_UPDATED_AT		= "updated_at";
	
	public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
	        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
	        new ColumnInfoForm(COL_ID,                true,     MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, false,  "", getLabelName(TABLE_NAME, COL_ID),                getPlaceholder(TABLE_NAME, COL_ID)),
	        new ColumnInfoForm(COL_USER_ID,           false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, false , "", getLabelName(TABLE_NAME, COL_USER_ID),           getPlaceholder(TABLE_NAME, COL_USER_ID)),
	        new ColumnInfoForm(COL_USER_CODE,         false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false , "", getLabelName(TABLE_NAME, COL_USER_CODE),           getPlaceholder(TABLE_NAME, COL_USER_CODE)),
	        new ColumnInfoForm(COL_VALID_STATUS,      false,    MySqlType.CHARACTER_VARYING.getType(), 2  , null, null, false , "", getLabelName(TABLE_NAME, COL_VALID_STATUS),      getPlaceholder(TABLE_NAME, COL_VALID_STATUS)),
	        new ColumnInfoForm(COL_VALID_START_DATE,  false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_VALID_START_DATE),  getPlaceholder(TABLE_NAME, COL_VALID_START_DATE)),
	        new ColumnInfoForm(COL_VALID_END_DATE,    false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true,   "", getLabelName(TABLE_NAME, COL_VALID_END_DATE),    getPlaceholder(TABLE_NAME, COL_VALID_END_DATE)),
	        new ColumnInfoForm(COL_CREATED_BY,        false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_CREATED_BY),        getPlaceholder(TABLE_NAME, COL_CREATED_BY)),
	        new ColumnInfoForm(COL_CREATED_AT,        false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_CREATED_AT),        getPlaceholder(TABLE_NAME, COL_CREATED_AT)),
	        new ColumnInfoForm(COL_UPDATED_BY,        false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_UPDATED_BY),        getPlaceholder(TABLE_NAME, COL_UPDATED_BY)),
	        new ColumnInfoForm(COL_UPDATED_AT,        false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, true ,  "", getLabelName(TABLE_NAME, COL_UPDATED_AT),        getPlaceholder(TABLE_NAME, COL_UPDATED_AT)),
	        
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
