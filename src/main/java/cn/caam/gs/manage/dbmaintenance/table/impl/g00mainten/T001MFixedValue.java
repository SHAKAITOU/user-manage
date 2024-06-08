package cn.caam.gs.manage.dbmaintenance.table.impl.g00mainten;

import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.BaseDdl;
import cn.caam.gs.manage.dbmaintenance.table.MySqlType;
import cn.caam.gs.manage.dbmaintenance.table.impl.BaseTableDef;

public class T001MFixedValue extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME        = "m_fixed_value";
    public static final String  TABLE_NAME_OMT    = "T001";
    private static final String TABLE_SEQ         = TABLE_NAME_OMT;
    private static final String TABLE_GROUP       = GROUP_MAINTEN;
    private static final String COL_CODE            = "code";
    private static final String COL_VALUE           = "value";
    private static final String COL_NAME            = "name";
    private static final String COL_SHOW_INDEX      = "show_index";
    
    public Object[][] columnInfos() {
        Object[][] cols = new Object[][] {
            // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
            {COL_CODE,        true,    MySqlType.CHARACTER_VARYING, 10 , null, null, false, "", getContext(TABLE_NAME, COL_CODE)},
            {COL_VALUE,       true,    MySqlType.CHARACTER_VARYING, 10 , null, null, false, "", getContext(TABLE_NAME, COL_VALUE)},
            {COL_NAME,        false,   MySqlType.CHARACTER_VARYING, 255, null, null, false, "", getContext(TABLE_NAME, COL_NAME)},
            {COL_SHOW_INDEX,  false,   MySqlType.INTEGER,          null, null, null, false, "", getContext(TABLE_NAME, COL_SHOW_INDEX)},
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
