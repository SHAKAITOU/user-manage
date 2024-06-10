package cn.caam.gs.manage.dbmaintenance.table.impl.g01master;

import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;
import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;

public class T100MUser extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_user";
    public static final String  TABLE_NAME_OMT    = "T100";
    private static final String TABLE_SEQ         = TABLE_NAME_OMT;
    private static final String TABLE_GROUP       = GROUP_MASTER;
    private static final String COL_ID               = "id";
    private static final String COL_NAME             = "name";
    private static final String COL_USER_TYPE        = "user_type";
    private static final String COL_PASSWORD         = "password";
    private static final String COL_MEMBERSHIP_PATH  = "membership_path";
    private static final String COL_FOCUS_ON         = "focus_on";
    private static final String COL_SEX              = "sex";
    private static final String COL_BIRTH            = "birth";
    private static final String COL_NATIONALITY      = "nationality";
    private static final String COL_POLITICAL        = "political";
    private static final String COL_EDU_DEGREE       = "edu_degree";
    private static final String COL_BACHELOR         = "bachelor";
    private static final String COL_POSITION         = "position";
    private static final String COL_EMPLOYER         = "employer";
    private static final String COL_EMPLOYER_TYPE    = "employer_type";
    private static final String COL_JOB_TITLE        = "job_title";
    private static final String COL_CERTIFICATE_TYPE = "certificate_type";
    private static final String COL_CERTIFICATE_CODE = "certificate_code";
    private static final String COL_AREA             = "area";
    private static final String COL_AREA_SUB         = "area_sub";
    private static final String COL_ADDRESS          = "address";
    private static final String COL_POSTAL_CODE      = "postal_code";
    private static final String COL_PHONE            = "phone";
    private static final String COL_MAIL             = "mail";
    private static final String COL_CHECK_DATE       = "check_date";
    private static final String COL_CHECK_STATUS     = "check_status";
    private static final String COL_REGIST_DATE      = "regist_date";
    private static final String COL_VALID_START_DATE = "valid_start_date";
    private static final String COL_VALID_END_DATE   = "valid_end_date";
    
    public Object[][] columnInfos() {
        Object[][] cols = new Object[][] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            {COL_ID,               true,     MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_ID)},
            {COL_NAME,             false,    MySqlType.CHARACTER_VARYING, 70 , null, null, false, "", getContext(TABLE_NAME, COL_NAME)},
            {COL_USER_TYPE,        false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_USER_TYPE)},
            {COL_PASSWORD,         false,    MySqlType.CHARACTER_VARYING, 200, null, null, false, "", getContext(TABLE_NAME, COL_PASSWORD)},
            {COL_MEMBERSHIP_PATH,  false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_MEMBERSHIP_PATH)},
            {COL_FOCUS_ON,         false,    MySqlType.CHARACTER_VARYING, 255, null, null, true , "", getContext(TABLE_NAME, COL_FOCUS_ON)},
            {COL_SEX,              false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_SEX)},
            {COL_BIRTH,            false,    MySqlType.CHARACTER_VARYING, 10 , null, null, false, "", getContext(TABLE_NAME, COL_BIRTH)},
            {COL_NATIONALITY,      false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_NATIONALITY)},
            {COL_POLITICAL,        false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_POLITICAL)},
            {COL_EDU_DEGREE,       false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_EDU_DEGREE)},
            {COL_BACHELOR,         false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_BACHELOR)},
            {COL_POSITION,         false,    MySqlType.CHARACTER_VARYING, 3  , null, null, true , "", getContext(TABLE_NAME, COL_POSITION)},
            {COL_EMPLOYER,         false,    MySqlType.CHARACTER_VARYING, 70 , null, null, false, "", getContext(TABLE_NAME, COL_EMPLOYER)},
            {COL_EMPLOYER_TYPE,    false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_EMPLOYER_TYPE)},
            {COL_JOB_TITLE,        false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_JOB_TITLE)},
            {COL_CERTIFICATE_TYPE, false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_CERTIFICATE_TYPE)},
            {COL_CERTIFICATE_CODE, false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_CERTIFICATE_CODE)},
            {COL_AREA,             false,    MySqlType.CHARACTER_VARYING, 3  , null, null, false, "", getContext(TABLE_NAME, COL_AREA)},
            {COL_AREA_SUB,         false,    MySqlType.CHARACTER_VARYING, 6  , null, null, false, "", getContext(TABLE_NAME, COL_AREA_SUB)},
            {COL_ADDRESS,          false,    MySqlType.CHARACTER_VARYING, 140, null, null, false, "", getContext(TABLE_NAME, COL_ADDRESS)},
            {COL_POSTAL_CODE,      false,    MySqlType.CHARACTER_VARYING, 10 , null, null, false, "", getContext(TABLE_NAME, COL_POSTAL_CODE)},
            {COL_PHONE,            false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_PHONE)},
            {COL_MAIL,             false,    MySqlType.CHARACTER_VARYING, 70 , null, null, false, "", getContext(TABLE_NAME, COL_MAIL)},
            {COL_CHECK_DATE,       false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_CHECK_DATE)},
            {COL_CHECK_STATUS,     false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_CHECK_STATUS)},
            {COL_REGIST_DATE,      false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_REGIST_DATE)},
            {COL_VALID_START_DATE, false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_VALID_START_DATE)},
            {COL_VALID_END_DATE,   false,    MySqlType.CHARACTER_VARYING, 20 , null, null, false, "", getContext(TABLE_NAME, COL_VALID_END_DATE)},
        };
        
        return cols;
    }
    
    public Object[][] indexInfos() {
        int i = 1;
        String name = TABLE_NAME+"_index";
        Object[][] cols = new Object[][] {
            // name | columnNames
            {name+(i++),     new String[] {"mail"}},
            {name+(i++),     new String[] {"authority_group_id"}},
            {name+(i++),     new String[] {"surname"}},
            {name+(i++),     new String[] {"family_name"}},
            {name+(i++),     new String[] {"surname_ja"}},
            {name+(i++),     new String[] {"family_name_ja"}},
            {name+(i++),     new String[] {"role"}},
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
