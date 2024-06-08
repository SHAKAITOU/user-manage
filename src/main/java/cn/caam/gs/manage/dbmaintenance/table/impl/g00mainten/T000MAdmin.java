package cn.caam.gs.manage.dbmaintenance.table.impl.g00mainten;

import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;
import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;

public class T000MAdmin extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_admin";
    public static final String  TABLE_NAME_OMT    = "T000";
    private static final String TABLE_SEQ         = TABLE_NAME_OMT;
    private static final String TABLE_GROUP       = GROUP_MAINTEN;
    private static final String COL_ID              = "id";
    private static final String COL_NAME            = "name";
    private static final String COL_USER_TYPE       = "user_type";
    
    public Object[][] columnInfos() {
        Object[][] cols = new Object[][] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            {COL_ID,           true,     MySqlType.CHARACTER_VARYING, 20, null, null, false, "", getContext(TABLE_NAME, COL_ID)},
            {COL_NAME,         false,    MySqlType.CHARACTER_VARYING, 70, null, null, false, "", getContext(TABLE_NAME, COL_NAME)},
            {COL_USER_TYPE,    false,    MySqlType.CHARACTER_VARYING, 3 , null, null, false, "", getContext(TABLE_NAME, COL_USER_TYPE)}
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
