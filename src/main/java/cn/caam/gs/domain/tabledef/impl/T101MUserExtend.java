package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T101MUserExtend extends BaseTableDef implements BaseDdl{
	
	public static final String TABLE_NAME 		= "m_user_extend";
	public static final String TABLE_NAME_OMT	= "T101";
	public static final String TABLE_SEQ 		= TABLE_NAME_OMT;
	public static final String TABLE_GROUP 	= GROUP_MASTER;
	public static final String COL_ID               = "id";
	public static final String COL_INTRODUCER1      = "introducer1";
	public static final String COL_INTRODUCER2      = "introducer2";
	public static final String COL_PHOTO            = "photo";
	public static final String COL_PHOTO_EXT        = "photo_ext";
	public static final String COL_MAJOR            = "major";
	public static final String COL_EDUCATIONAL_AT   = "educational_at";
	public static final String COL_EDUCATIONAL_AT_EXT  = "educational_at_ext";
	public static final String COL_BACHELOR_AT      = "bachelor_at";
	public static final String COL_BACHELOR_AT_EXT  = "bachelor_at_ext";
	public static final String COL_VOCATIONAL_AT    = "vocational_at";
	public static final String COL_VOCATIONAL_AT_EXT = "vocational_at_ext";
	public static final String COL_RESEARCH_DIR     = "research_dir";
	public static final String COL_LEARN_EXPERIENCE = "learn_experience";
	public static final String COL_WORK_EXPERIENCE  = "work_experience";
	public static final String COL_PAPERS           = "papers";
	public static final String COL_HONORS           = "honors";
	public static final String COL_APPLICATION_FORM		= "application_form";
	public static final String COL_APPLICATION_FORM_EXT	= "application_form_ext";
	public static final String COL_APPLICATION_FORM2	= "application_form2";
	public static final String COL_APPLICATION_FORM_EXT2= "application_form_ext2";
	
	public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
	        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
	        new ColumnInfoForm(COL_ID,               true,     MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_ID),                getPlaceholder(TABLE_NAME, COL_ID)),
	        new ColumnInfoForm(COL_INTRODUCER1,      false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_INTRODUCER1),       getPlaceholder(TABLE_NAME, COL_INTRODUCER1)),
	        new ColumnInfoForm(COL_INTRODUCER2,      false,    MySqlType.CHARACTER_VARYING.getType(), 50 , null, null, true , "", getLabelName(TABLE_NAME, COL_INTRODUCER2),       getPlaceholder(TABLE_NAME, COL_INTRODUCER2)),
	        new ColumnInfoForm(COL_PHOTO,            false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_PHOTO),             getPlaceholder(TABLE_NAME, COL_PHOTO)),
	        new ColumnInfoForm(COL_PHOTO_EXT,        false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_PHOTO_EXT),         getPlaceholder(TABLE_NAME, COL_PHOTO_EXT)),
	        new ColumnInfoForm(COL_MAJOR,            false,    MySqlType.CHARACTER_VARYING.getType(), 70 , null, null, false, "", getLabelName(TABLE_NAME, COL_MAJOR),             getPlaceholder(TABLE_NAME, COL_MAJOR)),
	        new ColumnInfoForm(COL_EDUCATIONAL_AT,   false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_EDUCATIONAL_AT),    getPlaceholder(TABLE_NAME, COL_EDUCATIONAL_AT)),
	        new ColumnInfoForm(COL_EDUCATIONAL_AT_EXT,false,   MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_EDUCATIONAL_AT_EXT),getPlaceholder(TABLE_NAME, COL_EDUCATIONAL_AT_EXT)),
            new ColumnInfoForm(COL_BACHELOR_AT,      false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_BACHELOR_AT),       getPlaceholder(TABLE_NAME, COL_BACHELOR_AT)),
            new ColumnInfoForm(COL_BACHELOR_AT_EXT,  false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_BACHELOR_AT_EXT),   getPlaceholder(TABLE_NAME, COL_BACHELOR_AT_EXT)),
            new ColumnInfoForm(COL_VOCATIONAL_AT,    false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_VOCATIONAL_AT),     getPlaceholder(TABLE_NAME, COL_VOCATIONAL_AT)),
            new ColumnInfoForm(COL_VOCATIONAL_AT_EXT,false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_VOCATIONAL_AT_EXT), getPlaceholder(TABLE_NAME, COL_VOCATIONAL_AT_EXT)),
            new ColumnInfoForm(COL_RESEARCH_DIR,     false,    MySqlType.CHARACTER_VARYING.getType(), 40 , null, null, false, "", getLabelName(TABLE_NAME, COL_RESEARCH_DIR),      getPlaceholder(TABLE_NAME, COL_RESEARCH_DIR)),
	        new ColumnInfoForm(COL_LEARN_EXPERIENCE, false,    MySqlType.CHARACTER_VARYING.getType(), 255, null, null, false, "", getLabelName(TABLE_NAME, COL_LEARN_EXPERIENCE),  getPlaceholder(TABLE_NAME, COL_LEARN_EXPERIENCE)),
	        new ColumnInfoForm(COL_WORK_EXPERIENCE,  false,    MySqlType.CHARACTER_VARYING.getType(), 255, null, null, false, "", getLabelName(TABLE_NAME, COL_WORK_EXPERIENCE),   getPlaceholder(TABLE_NAME, COL_WORK_EXPERIENCE)),
	        new ColumnInfoForm(COL_PAPERS,           false,    MySqlType.CHARACTER_VARYING.getType(), 255, null, null, true , "", getLabelName(TABLE_NAME, COL_PAPERS),            getPlaceholder(TABLE_NAME, COL_PAPERS)),
	        new ColumnInfoForm(COL_HONORS,           false,    MySqlType.CHARACTER_VARYING.getType(), 255, null, null, true , "", getLabelName(TABLE_NAME, COL_HONORS),            getPlaceholder(TABLE_NAME, COL_HONORS)),
	        new ColumnInfoForm(COL_APPLICATION_FORM,	false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_APPLICATION_FORM),    getPlaceholder(TABLE_NAME, COL_APPLICATION_FORM)),
	        new ColumnInfoForm(COL_APPLICATION_FORM_EXT,false,   MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_APPLICATION_FORM_EXT),getPlaceholder(TABLE_NAME, COL_APPLICATION_FORM_EXT)),
	        new ColumnInfoForm(COL_APPLICATION_FORM2,	false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_APPLICATION_FORM2),    getPlaceholder(TABLE_NAME, COL_APPLICATION_FORM2)),
	        new ColumnInfoForm(COL_APPLICATION_FORM_EXT2,false,   MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_APPLICATION_FORM_EXT2),getPlaceholder(TABLE_NAME, COL_APPLICATION_FORM_EXT2)),
	        
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
