package cn.caam.gs.manage.dbmaintenance.table.impl.g01master;

import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;
import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;

public class T101MUserExtend extends BaseTableDef implements BaseDdl{
	
	public static final String TABLE_NAME 		= "m_user_extend";
	public static final String TABLE_NAME_OMT	= "T101";
	private static final String TABLE_SEQ 		= TABLE_NAME_OMT;
	private static final String TABLE_GROUP 	= GROUP_MASTER;
	private static final String COL_ID               = "id";
	private static final String COL_INTRODUCER1      = "introducer1";
	private static final String COL_INTRODUCER2      = "introducer2";
	private static final String COL_ID_PHOTO         = "id_photo";
	private static final String COL_MAJOR            = "major";
	private static final String COL_EDUCATIONAL_AT   = "educational_at";
	private static final String COL_BACHELOR_AT      = "bachelor_at";
	private static final String COL_VOCATIONAL_AT    = "vocational_at";
	private static final String COL_RESEARCH_DIR     = "research_dir";
	private static final String COL_LEARN_EXPERIENCE = "learn_experience";
	private static final String COL_WORK_EXPERIENCE  = "work_experience";
	private static final String COL_PAPERS           = "papers";
	private static final String COL_HONORS           = "honors";
	
	public Object[][] columnInfos() {
		Object[][] cols = new Object[][] {
			// name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
		    {COL_ID,               true,     MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_ID)},
            {COL_INTRODUCER1,      false,    MySqlType.CHARACTER_VARYING, 50 , null, null, true , "", getContext(TABLE_NAME, COL_INTRODUCER1)},
            {COL_INTRODUCER2,      false,    MySqlType.CHARACTER_VARYING, 50 , null, null, true , "", getContext(TABLE_NAME, COL_INTRODUCER2)},
            {COL_ID_PHOTO,         false,    MySqlType.BLOB,             null, null, null, true , "", getContext(TABLE_NAME, COL_ID_PHOTO)},
            {COL_MAJOR,            false,    MySqlType.CHARACTER_VARYING, 70 , null, null, false, "", getContext(TABLE_NAME, COL_MAJOR)},
            {COL_EDUCATIONAL_AT,   false,    MySqlType.BLOB,             null, null, null, true , "", getContext(TABLE_NAME, COL_EDUCATIONAL_AT)},
            {COL_BACHELOR_AT,      false,    MySqlType.BLOB,             null, null, null, true , "", getContext(TABLE_NAME, COL_BACHELOR_AT)},
            {COL_VOCATIONAL_AT,    false,    MySqlType.BLOB,             null, null, null, true , "", getContext(TABLE_NAME, COL_VOCATIONAL_AT)},
            {COL_RESEARCH_DIR,     false,    MySqlType.CHARACTER_VARYING, 40 , null, null, false, "", getContext(TABLE_NAME, COL_RESEARCH_DIR)},
            {COL_LEARN_EXPERIENCE, false,    MySqlType.CHARACTER_VARYING, 255, null, null, false, "", getContext(TABLE_NAME, COL_LEARN_EXPERIENCE)},
            {COL_WORK_EXPERIENCE,  false,    MySqlType.CHARACTER_VARYING, 255, null, null, false, "", getContext(TABLE_NAME, COL_WORK_EXPERIENCE)},
            {COL_PAPERS,           false,    MySqlType.CHARACTER_VARYING, 255, null, null, true , "", getContext(TABLE_NAME, COL_PAPERS)},
            {COL_HONORS,           false,    MySqlType.CHARACTER_VARYING, 255, null, null, true , "", getContext(TABLE_NAME, COL_HONORS)},
			
		};
		
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
}
