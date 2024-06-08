package cn.caam.gs.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.FileUtil;
import cn.caam.gs.domain.db.DBConfig;
import cn.caam.gs.domain.db.custom.mapper.OptionalDbTableInfoMapper;
import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.IndexInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.TableInfoForm;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DbMaintenanceService extends BaseService {
	
	@Autowired
	DBConfig dbConfig;
	
	@Autowired
	DataSource dataSource; 
	
	private OptionalDbTableInfoMapper optionalDbTableInfoMapper;
	

	public TableInfoForm getTableInfoForm(String tableName) {
		boolean existsFlg = existsTable(tableName);
    	if(!existsFlg) {
    		return null;
    	}
    	TableInfoForm tableInfoForm = new TableInfoForm();
    	tableInfoForm.setTableName(tableName);
    	List<ColumnInfoForm> columnInfos = showColumns(tableName);
    	if(columnInfos.size() == 0) {
    		return null;
    	}
    	tableInfoForm.setColumnInfos(columnInfos);    	
    	tableInfoForm.setIndexInfos(showIndexs(tableName));
    	return tableInfoForm;
	}

	public List<String> showTables() {

    	return optionalDbTableInfoMapper.showTables();
    }
	
	public InputStreamResource backup() throws Exception {

		String currentDt = DateUtility.getCurrentDateTime();
		File tempFile = File.createTempFile(currentDt, "data.sql");
		File zipFile = File.createTempFile(currentDt, "data.zip");
		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		stmt.executeQuery(String.format("SCRIPT TO '%s'", tempFile.getAbsoluteFile()));
		InputStreamResource resource;
		FileOutputStream fos = new FileOutputStream(zipFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
		ZipOutputStream outputStream = new ZipOutputStream(bos);
		ZipEntry zip1 = new ZipEntry(tempFile.getName());
		outputStream.putNextEntry(zip1);
		FileUtil.write(new FileInputStream(tempFile), outputStream);
		outputStream.closeEntry();
		outputStream.close();
		resource = new InputStreamResource(new FileInputStream(zipFile));
		tempFile.delete();
		zipFile.delete();
		return resource;
	}
    
    private List<ColumnInfoForm> showColumns(String tableName) {
        return optionalDbTableInfoMapper.showColumns(tableName);
    }
    
    private boolean existsTable(String tableName) {
        return optionalDbTableInfoMapper.existsTable(tableName);
    }
    
    private List<IndexInfoForm> showIndexs(String tableName) {
        return optionalDbTableInfoMapper.showIndexs(tableName);
    }
}
