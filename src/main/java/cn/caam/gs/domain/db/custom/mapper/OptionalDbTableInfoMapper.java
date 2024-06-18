package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.IndexInfoForm;

@Mapper
public interface OptionalDbTableInfoMapper {

	List<String> showTables();
	
	List<ColumnInfoForm> showColumns(@Param("tableName") String tableName);
	
	List<IndexInfoForm> showIndexs(@Param("tableName") String tableName);
	
	boolean existsTable(@Param("tableName") String tableName);
}
