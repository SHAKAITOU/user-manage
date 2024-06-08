package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.IndexInfoForm;

@Mapper
public interface OptionalDbTableInfoMapper {

	List<String> showTables();
	
	List<ColumnInfoForm> showColumns(@Param("tableName") String tableName);
	
	List<IndexInfoForm> showIndexs(@Param("tableName") String tableName);
	
	boolean existsTable(@Param("tableName") String tableName);
}
