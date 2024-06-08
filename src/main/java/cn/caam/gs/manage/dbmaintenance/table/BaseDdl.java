package cn.caam.gs.manage.dbmaintenance.table;

import cn.caam.gs.manage.dbmaintenance.form.SequenceInfoForm;

public interface BaseDdl {
	
	public static final String GROUP_MAINTEN 		= DbGroupTableType.G0_MAINTEN.getGroupName();
	public static final String GROUP_MASTER 		= DbGroupTableType.G1_MASTER.getGroupName();
	public static final String GROUP_ORDER 			= DbGroupTableType.G2_ORDER.getGroupName();	
	
	String getTableSeq();
	
	String getTableName();
	
	String getTableNameOmt();
	
	String getTableComment();

	String getTableGroup();
	
	Object[][] columnInfos();
	
	Object[][] indexInfos();
	
	SequenceInfoForm getSequenceInfo();
}
