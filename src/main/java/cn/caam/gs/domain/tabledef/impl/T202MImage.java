package cn.caam.gs.domain.tabledef.impl;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.domain.tabledef.BaseDdl;
import cn.caam.gs.domain.tabledef.MySqlType;

public class T202MImage extends BaseTableDef implements BaseDdl{
    
    public static final String  TABLE_NAME           = "m_image";
    public static final String  TABLE_NAME_OMT       = "T202";
    public static final String TABLE_SEQ             = TABLE_NAME_OMT;
    public static final String TABLE_GROUP           = GROUP_ORDER;
    public static final String COL_ID                = "id";
    public static final String COL_ORDER_PHOTO       = "order_photo";
    public static final String COL_ORDER_PHOTO_EXT   = "order_photo_ext";
    public static final String COL_BILL_PHOTO        = "bill_photo";
    public static final String COL_BILL_PHOTO_EXT    = "bill_photo_ext";
    
    public static final ColumnInfoForm[] cols = new ColumnInfoForm[] {
        // name | pk | type | charMaxLength | numPrecision | numScale | nullable | default | comment
        new ColumnInfoForm(COL_ID,                  true,     MySqlType.CHARACTER_VARYING.getType(), 30, null, null, false,  "", getLabelName(TABLE_NAME, COL_ID),               getPlaceholder(TABLE_NAME, COL_ID)),
        new ColumnInfoForm(COL_ORDER_PHOTO,         false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_ORDER_PHOTO),      getPlaceholder(TABLE_NAME, COL_ORDER_PHOTO)),
        new ColumnInfoForm(COL_ORDER_PHOTO_EXT,     false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_ORDER_PHOTO_EXT),  getPlaceholder(TABLE_NAME, COL_ORDER_PHOTO_EXT)),
        new ColumnInfoForm(COL_BILL_PHOTO,          false,    MySqlType.BLOB.getType(),             null, null, null, true , "", getLabelName(TABLE_NAME, COL_BILL_PHOTO),       getPlaceholder(TABLE_NAME, COL_BILL_PHOTO)),
        new ColumnInfoForm(COL_BILL_PHOTO_EXT,      false,    MySqlType.CHARACTER_VARYING.getType(), 10 , null, null, true , "", getLabelName(TABLE_NAME, COL_BILL_PHOTO_EXT),   getPlaceholder(TABLE_NAME, COL_BILL_PHOTO_EXT)),
            
    };
    public ColumnInfoForm[] columnInfos() {
        
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
    
    public ColumnInfoForm getColumnInfo(String columnName) {
        for (ColumnInfoForm form : cols) {
            if (form.getName().equals(columnName)) {
                return form;
            }
        }
        return null;
    }
}
