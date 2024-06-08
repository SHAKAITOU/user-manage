package cn.caam.gs.manage.dbmaintenance.table;

import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.manage.dbmaintenance.form.TableInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.impl.g00mainten.T000MAdmin;
import cn.caam.gs.manage.dbmaintenance.table.impl.g00mainten.T001MFixedValue;
import cn.caam.gs.manage.dbmaintenance.table.impl.g01master.T100MUser;
import cn.caam.gs.manage.dbmaintenance.table.impl.g01master.T101MUserExtend;
import cn.caam.gs.manage.dbmaintenance.table.impl.g02order.T200MOrder;
import cn.caam.gs.manage.dbmaintenance.table.impl.g02order.T201MBill;

public enum DbTableType {

	//mainten
	T000 		(new T000MAdmin()),
	T001 		(new T001MFixedValue()),
	
	
	//master
	T100 		(new T100MUser()),
	T101 		(new T101MUserExtend()),
	
	//Order
	
	T200 		(new T200MOrder()),
	T201 		(new T201MBill()),
	
	;
    private BaseDdl baseDdl;

    private DbTableType(BaseDdl baseDdl) {
        this.baseDdl = baseDdl;
    }

    public String getSeq() {
        return baseDdl.getTableSeq();
    }
    
    public String getName() {
        return baseDdl.getTableName();
    }
    
    public String getNameOmt() {
        return baseDdl.getTableNameOmt();
    }
    
    public String getComment() {
        return baseDdl.getTableComment();
    }
    
    public String getGroup() {
        return baseDdl.getTableGroup();
    }
    
    public String getJsonFileName() {
        return getSeq() + UtilConstants.DOT + getComment() + UtilConstants.DOT + getName() + UtilConstants.EXT_JSON;
    }
    
    public TableInfoForm getTableInfoForm() {
    	TableInfoForm tableInfoForm = new TableInfoForm();
		tableInfoForm.setComment(getComment());
		tableInfoForm.setTableName(getName());
		tableInfoForm.setColumnInfos(DbTableUtil.getColumnInfos(baseDdl.columnInfos()));
		tableInfoForm.setIndexInfos(DbTableUtil.getIndexInfos(baseDdl.indexInfos()));
		tableInfoForm.setSequenceInfo(baseDdl.getSequenceInfo());
    	return tableInfoForm;
    }

    public DbTableType[] list() {
    	return DbTableType.values();
    }
    
    public static DbTableType nameOf(String name) {
    	for(DbTableType type : DbTableType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
