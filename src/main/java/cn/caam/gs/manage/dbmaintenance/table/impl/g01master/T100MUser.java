package cn.caam.gs.manage.dbmaintenance.table.impl.g01master;

import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;
import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;

public class T100MUser extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_user";
    public static final String  TABLE_NAME_OMT    = "T100";
    public static final String TABLE_SEQ         = TABLE_NAME_OMT;
    public static final String TABLE_GROUP       = GROUP_MASTER;
    /** 会员号(M/TYYMMDDHHmmSSR2) */
    public static final String COL_ID               = "id";
    public static final String COL_NAME             = "name";
    public static final String COL_USER_TYPE        = "user_type";
    public static final String COL_PASSWORD         = "password";
    public static final String COL_MEMBERSHIP_PATH  = "membership_path";
    public static final String COL_FOCUS_ON         = "focus_on";
    public static final String COL_SEX              = "sex";
    public static final String COL_BIRTH            = "birth";
    public static final String COL_NATIONALITY      = "nationality";
    public static final String COL_POLITICAL        = "political";
    public static final String COL_EDU_DEGREE       = "edu_degree";
    public static final String COL_BACHELOR         = "bachelor";
    public static final String COL_POSITION         = "position";
    public static final String COL_EMPLOYER         = "employer";
    public static final String COL_EMPLOYER_TYPE    = "employer_type";
    public static final String COL_JOB_TITLE        = "job_title";
    public static final String COL_CERTIFICATE_TYPE = "certificate_type";
    public static final String COL_CERTIFICATE_CODE = "certificate_code";
    public static final String COL_AREA             = "area";
    public static final String COL_AREA_SUB         = "area_sub";
    public static final String COL_ADDRESS          = "address";
    public static final String COL_POSTAL_CODE      = "postal_code";
    public static final String COL_PHONE            = "phone";
    public static final String COL_MAIL             = "mail";
    public static final String COL_CHECK_DATE       = "check_date";
    public static final String COL_CHECK_STATUS     = "check_status";
    public static final String COL_REGIST_DATE      = "regist_date";
    public static final String COL_VALID_START_DATE = "valid_start_date";
    public static final String COL_VALID_END_DATE   = "valid_end_date";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | label
        new ColumnInfoForm(COL_ID,               true,     MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_ID),               getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_NAME,             false,    MySqlType.CHARACTER_VARYING.getType(), 70 , null, null, false, "", getLabelName(TABLE_NAME, COL_NAME),             getPlaceholder(TABLE_NAME, COL_NAME)),
        new ColumnInfoForm(COL_USER_TYPE,        false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_USER_TYPE),        getPlaceholder(TABLE_NAME, COL_USER_TYPE)),
        new ColumnInfoForm(COL_PASSWORD,         false,    MySqlType.CHARACTER_VARYING.getType(), 200, null, null, false, "", getLabelName(TABLE_NAME, COL_PASSWORD),         getPlaceholder(TABLE_NAME, COL_PASSWORD)),
        new ColumnInfoForm(COL_MEMBERSHIP_PATH,  false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_MEMBERSHIP_PATH),  getPlaceholder(TABLE_NAME, COL_MEMBERSHIP_PATH)),
        new ColumnInfoForm(COL_FOCUS_ON,         false,    MySqlType.CHARACTER_VARYING.getType(), 255, null, null, true , "", getLabelName(TABLE_NAME, COL_FOCUS_ON),         getPlaceholder(TABLE_NAME, COL_FOCUS_ON)),
        new ColumnInfoForm(COL_SEX,              false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_SEX),              getPlaceholder(TABLE_NAME, COL_SEX)),
        new ColumnInfoForm(COL_BIRTH,            false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, false, "", getLabelName(TABLE_NAME, COL_BIRTH),            getPlaceholder(TABLE_NAME, COL_BIRTH)),
        new ColumnInfoForm(COL_NATIONALITY,      false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_NATIONALITY),      getPlaceholder(TABLE_NAME, COL_NATIONALITY)),
        new ColumnInfoForm(COL_POLITICAL,        false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_POLITICAL),        getPlaceholder(TABLE_NAME, COL_POLITICAL)),
        new ColumnInfoForm(COL_EDU_DEGREE,       false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_EDU_DEGREE),       getPlaceholder(TABLE_NAME, COL_EDU_DEGREE)),
        new ColumnInfoForm(COL_BACHELOR,         false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_BACHELOR),         getPlaceholder(TABLE_NAME, COL_BACHELOR)),
        new ColumnInfoForm(COL_POSITION,         false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, true , "", getLabelName(TABLE_NAME, COL_POSITION),         getPlaceholder(TABLE_NAME, COL_POSITION)),
        new ColumnInfoForm(COL_EMPLOYER,         false,    MySqlType.CHARACTER_VARYING.getType(), 70 , null, null, false, "", getLabelName(TABLE_NAME, COL_EMPLOYER),         getPlaceholder(TABLE_NAME, COL_EMPLOYER)),
        new ColumnInfoForm(COL_EMPLOYER_TYPE,    false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_EMPLOYER_TYPE),    getPlaceholder(TABLE_NAME, COL_EMPLOYER_TYPE)),
        new ColumnInfoForm(COL_JOB_TITLE,        false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_JOB_TITLE),        getPlaceholder(TABLE_NAME, COL_JOB_TITLE)),
        new ColumnInfoForm(COL_CERTIFICATE_TYPE, false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_CERTIFICATE_TYPE), getPlaceholder(TABLE_NAME, COL_CERTIFICATE_TYPE)),
        new ColumnInfoForm(COL_CERTIFICATE_CODE, false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_CERTIFICATE_CODE), getPlaceholder(TABLE_NAME, COL_CERTIFICATE_CODE)),
        new ColumnInfoForm(COL_AREA,             false,    MySqlType.CHARACTER_VARYING.getType(), 3  , null, null, false, "", getLabelName(TABLE_NAME, COL_AREA),             getPlaceholder(TABLE_NAME, COL_AREA)),
        new ColumnInfoForm(COL_AREA_SUB,         false,    MySqlType.CHARACTER_VARYING.getType(), 6  , null, null, false, "", getLabelName(TABLE_NAME, COL_AREA_SUB),         getPlaceholder(TABLE_NAME, COL_AREA_SUB)),
        new ColumnInfoForm(COL_ADDRESS,          false,    MySqlType.CHARACTER_VARYING.getType(), 140, null, null, false, "", getLabelName(TABLE_NAME, COL_ADDRESS),          getPlaceholder(TABLE_NAME, COL_ADDRESS)),
        new ColumnInfoForm(COL_POSTAL_CODE,      false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, false, "", getLabelName(TABLE_NAME, COL_POSTAL_CODE),      getPlaceholder(TABLE_NAME, COL_POSTAL_CODE)),
        new ColumnInfoForm(COL_PHONE,            false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_PHONE),            getPlaceholder(TABLE_NAME, COL_PHONE)),
        new ColumnInfoForm(COL_MAIL,             false,    MySqlType.CHARACTER_VARYING.getType(), 70 , null, null, false, "", getLabelName(TABLE_NAME, COL_MAIL),             getPlaceholder(TABLE_NAME, COL_MAIL)),
        new ColumnInfoForm(COL_CHECK_DATE,       false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_CHECK_DATE),       getPlaceholder(TABLE_NAME, COL_CHECK_DATE)),
        new ColumnInfoForm(COL_CHECK_STATUS,     false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_CHECK_STATUS),     getPlaceholder(TABLE_NAME, COL_CHECK_STATUS)),
        new ColumnInfoForm(COL_REGIST_DATE,      false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_REGIST_DATE),      getPlaceholder(TABLE_NAME, COL_REGIST_DATE)),
        new ColumnInfoForm(COL_VALID_START_DATE, false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_VALID_START_DATE), getPlaceholder(TABLE_NAME, COL_VALID_START_DATE)),
        new ColumnInfoForm(COL_VALID_END_DATE,   false,    MySqlType.CHARACTER_VARYING.getType(), 20 , null, null, false, "", getLabelName(TABLE_NAME, COL_VALID_END_DATE),   getPlaceholder(TABLE_NAME, COL_VALID_END_DATE)),
    };
    
    public ColumnInfoForm[] columnInfos() {
        return cols;
    }
    
    public Object[][] indexInfos() {
        int i = 1;
        String name = TABLE_NAME+"_idx";
        Object[][] cols = new Object[][] {
            // name | columnNames
            {name+(i++),     new String[] {COL_NAME}},
            {name+(i++),     new String[] {COL_PHONE}},
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
