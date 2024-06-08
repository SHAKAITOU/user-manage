package cn.caam.gs.manage.dbmaintenance.table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.IndexInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.TableInfoForm;

public class DbTableUtil {
	
	public static final int COL_NAME 				= 0;
	public static final int COL_PK 					= 1;
	public static final int COL_TYPE 				= 2;
	public static final int COL_CHAR_MAX_LENGTH 	= 3;
	public static final int COL_NUMERIC_PRECISION 	= 4;
	public static final int COL_NUMERIC_SCALE 		= 5;
	public static final int COL_NULLABLE 			= 6;
	public static final int COL_DEFAULT_VALUE 		= 7;
	public static final int COL_COMMENT		 		= 8;
	
	public static final int INDEX_NAME		 		= 0;
	public static final int INDEX_COLS		 		= 1;
	
	public static final String OWNER				= "jcbcadmin";

	public static void createAllDdl(String schemaName, String putPutFilePath) throws FileNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("--+--------------------------------------------------------------------------------------------------+--").append("\n");
		sb.append("--| Table:  List").append("\n");
		for(DbTableType type : DbTableType.values()) {
			sb.append("--| Table: "+withSchemaName(schemaName)+type.getName()).append("\n");
		}
		sb.append("\n");
		sb.append("\n");
		sb.append("--+--------------------------------------------------------------------------------------------------+--").append("\n");
		for(DbTableType type : DbTableType.values()) {
			sb.append(DbTableUtil.createDdl(schemaName, type.getTableInfoForm()));
		}
		sb.append("\n");
		sb.append("\n");
		File ddlFile = new File(putPutFilePath+System.getProperty("file.separator")+"create_table.sql");
		
		try (PrintWriter out = new PrintWriter(ddlFile)) {
			out.println(sb.toString());
		}
	}
	
	public static String createDdl(String schemaName, TableInfoForm fileForm) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("--+--------------------------------------------------------------------------------------------------+--").append("\n");
		sb.append("--| "+fileForm.getComment()).append("\n");
		sb.append("--| "+withSchemaName(schemaName)+fileForm.getTableName()).append("\n");
		sb.append("--+--------------------------------------------------------------------------------------------------+--").append("\n");
		sb.append("\n");
		sb.append("DROP TABLE IF EXISTS "+withSchemaName(schemaName)+fileForm.getTableName()+";").append("\n");
		sb.append("CREATE TABLE "+withSchemaName(schemaName)+fileForm.getTableName()).append("\n");
		sb.append("( ").append("\n");
		for(ColumnInfoForm columnInfoForm : fileForm.getColumnInfos()) {
			sb.append("    -- ").append(columnInfoForm.getComment()).append("\n");
			sb.append("    ");
			sb.append(columnInfoForm.getName()+getTypeSql(columnInfoForm));
			if(!StringUtils.isBlank(columnInfoForm.getDefaultValue())) {
				sb.append(" DEFAULT '"+columnInfoForm.getDefaultValue()+"'");
			}
			
			if(!columnInfoForm.getNullable().booleanValue()) {
				sb.append(" NOT NULL ");
			}
			sb.append(",").append("\n");
		}
		sb.append("    CONSTRAINT "+fileForm.getTableName()+"_pkey PRIMARY KEY (");
		int index = 0;
		for(ColumnInfoForm columnInfoForm : fileForm.getColumnInfos()) {
			if(columnInfoForm.getPkFlg().booleanValue()) {
				if(index>0) {
					sb.append(",");
				}
				sb.append(columnInfoForm.getName());
				index++;
			}
		}
		sb.append(") ").append("\n");
		sb.append(") ").append("\n");
		//sb.append("WITH ( ").append("\n");
		//sb.append("    OIDS = FALSE").append("\n");
		//sb.append(") ").append("\n");
		//sb.append("TABLESPACE pg_default;").append("\n");
		//sb.append("ALTER TABLE "+withSchemaName(schemaName)+fileForm.getTableName()+" OWNER to "+OWNER+";").append("\n");
		sb.append(";\n");
		if(Objects.nonNull(fileForm.getIndexInfos())) {
			for(IndexInfoForm indexInfoForm : fileForm.getIndexInfos()) {
				sb.append(addIndexSQL(schemaName, fileForm.getTableName(), indexInfoForm));
			}
		}
		sb.append("\n");
		if(Objects.nonNull(fileForm.getSequenceInfo())) {
			sb.append("DROP SEQUENCE IF EXISTS "+fileForm.getSequenceInfo().getSeqName()+";").append("\n");
			sb.append("CREATE SEQUENCE "+fileForm.getSequenceInfo().getSeqName()+" START "+fileForm.getSequenceInfo().getStart()+";").append("\n");
			//sb.append("ALTER SEQUENCE "+fileForm.getSequenceInfo().getSeqName()+" OWNER to "+OWNER+";").append("\n");
		}
		
		sb.append("COMMENT ON TABLE "+withSchemaName(schemaName)+fileForm.getTableName()+" IS '"+fileForm.getComment()+"';").append("\n");
		for(ColumnInfoForm columnInfoForm : fileForm.getColumnInfos()) {
			sb.append(addColumnComment(schemaName, fileForm.getTableName(), columnInfoForm));
		}
		
		return sb.toString();
	}
	

	public static void createJson(String ddlFolderPath, String fileName, TableInfoForm fileForm) {
		File file = new File(ddlFolderPath + System.getProperty("file.separator") + fileName);
		JsonUtility.writeValue(file, fileForm);
	}
	
	public static String judgeDiff(String schemaName, TableInfoForm fileForm, TableInfoForm dbForm) {
		StringBuffer sabun = new StringBuffer();
		if(Objects.isNull(dbForm)) {
			return createDdl(schemaName, fileForm);
		}
		judgeDiffColumnInfos(schemaName, fileForm.getTableName(), fileForm.getColumnInfos(), dbForm.getColumnInfos(), sabun);
		judgeDiffIndexInfos(schemaName, fileForm.getTableName(), fileForm.getIndexInfos(), dbForm.getIndexInfos(), sabun);
		
		return sabun.toString();
	}
	
	public static List<ColumnInfoForm> getColumnInfos(Object[][] columnInfos) {
		List<ColumnInfoForm> columnInfoList = new ArrayList<>();
		for(Object[] col : columnInfos) {
			MySqlType type = (MySqlType)col[COL_TYPE];
			columnInfoList.add(ColumnInfoForm.builder()
					.name((String)col[COL_NAME]).pkFlg((boolean)col[COL_PK]).type(type.getType())
					.characterMaxLength(Objects.isNull(col[COL_CHAR_MAX_LENGTH]) ? null : (int)col[COL_CHAR_MAX_LENGTH])
					.numericPrecision(Objects.isNull(col[COL_NUMERIC_PRECISION]) ? null : (int)col[COL_NUMERIC_PRECISION])
					.numericScale(Objects.isNull(col[COL_NUMERIC_SCALE]) ? null : (int)col[COL_NUMERIC_SCALE])
					.nullable((boolean)col[COL_NULLABLE]).defaultValue((String)col[COL_DEFAULT_VALUE]).comment((String)col[COL_COMMENT])
					.build());
		}
		return columnInfoList;
	}
	
	public static List<IndexInfoForm> getIndexInfos(Object[][] indexInfos) {
		List<IndexInfoForm> indexInfoList = new ArrayList<>();
		for(Object[] col : indexInfos) {
			indexInfoList.add(IndexInfoForm.builder()
					.indexName((String)col[INDEX_NAME])
					.columnNames(Arrays.asList((String[])col[INDEX_COLS]))
					.build());
		}
		return indexInfoList;
	}
	
	private static void judgeDiffColumnInfos(String schemaName, String tableName, 
			List<ColumnInfoForm> fColumnInfos, List<ColumnInfoForm> dColumnInfos, StringBuffer sabun) {
		if(fColumnInfos.size() > dColumnInfos.size()) {
			for(int i=dColumnInfos.size(); i<fColumnInfos.size(); i++) {
				sabun.append(alterColumnAddSQL(schemaName, tableName, fColumnInfos.get(i), true));
			}
		}else {
			for(int i=0; i<fColumnInfos.size(); i++) {
				if(!fColumnInfos.get(i).getName().equalsIgnoreCase(dColumnInfos.get(i).getName())) {
					sabun.append(alterColumnReNameSQL(schemaName, tableName, fColumnInfos.get(i).getName(), dColumnInfos.get(i).getName()));
				}
				
				if(!fColumnInfos.get(i).getType().equalsIgnoreCase(dColumnInfos.get(i).getType()) ||
							(Objects.nonNull(fColumnInfos.get(i).getCharacterMaxLength()) && Objects.nonNull(dColumnInfos.get(i).getCharacterMaxLength()) &&
									fColumnInfos.get(i).getCharacterMaxLength().intValue() != dColumnInfos.get(i).getCharacterMaxLength().intValue()) ||
							(Objects.nonNull(fColumnInfos.get(i).getNumericPrecision()) && Objects.nonNull(dColumnInfos.get(i).getNumericPrecision()) &&
									fColumnInfos.get(i).getNumericPrecision().intValue() != dColumnInfos.get(i).getNumericPrecision().intValue()) ||
							(Objects.nonNull(fColumnInfos.get(i).getNumericScale()) && Objects.nonNull(dColumnInfos.get(i).getNumericScale()) &&
									fColumnInfos.get(i).getNumericScale().intValue() != dColumnInfos.get(i).getNumericScale().intValue())){
					sabun.append(alterColumnChangeTypeSQL(schemaName, tableName, fColumnInfos.get(i)));
					sabun.append(alterColumnChangeNullableSQL(schemaName, tableName, fColumnInfos.get(i)));
					sabun.append(alterColumnChangeDefaultSQL(schemaName, tableName, fColumnInfos.get(i)));
				}
				
				if(fColumnInfos.get(i).getNullable().booleanValue() != dColumnInfos.get(i).getNullable().booleanValue()) {
					sabun.append(alterColumnChangeNullableSQL(schemaName, tableName, fColumnInfos.get(i)));
				}
				
				if(Objects.nonNull(fColumnInfos.get(i).getDefaultValue()) && Objects.nonNull(dColumnInfos.get(i).getDefaultValue()) &&
						(!fColumnInfos.get(i).getDefaultValue().equals(trimDbDefaultValue(dColumnInfos.get(i).getDefaultValue())))) {
					sabun.append(alterColumnChangeDefaultSQL(schemaName, tableName, fColumnInfos.get(i)));
				}
			}
		}
	}
	
	private static String alterColumnAddSQL(String schemaName, String tableName, ColumnInfoForm columnInfoForm, boolean addFlg) {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE "+withSchemaName(schemaName)+tableName);
		sb.append(" ADD ");
		sb.append(columnInfoForm.getName()+getTypeSql(columnInfoForm));
		if(!StringUtils.isBlank(columnInfoForm.getDefaultValue())) {
			sb.append(" DEFAULT "+columnInfoForm.getDefaultValue());
		}
		
		if(!columnInfoForm.getNullable().booleanValue()) {
			sb.append(" NOT NULL ");
		}
		sb.append(";\n");
		sb.append(addColumnComment(schemaName, tableName, columnInfoForm));
		return sb.toString();
	}
	
	private static String alterColumnReNameSQL(String schemaName, String tableName, String orgName, String trgName) {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE "+withSchemaName(schemaName)+tableName+" RENAME "+orgName+" TO "+trgName+";");
		sb.append("\n");
		return sb.toString();
	}
	
	private static String alterColumnChangeTypeSQL(String schemaName, String tableName, ColumnInfoForm columnInfoForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE "+withSchemaName(schemaName)+tableName);
		sb.append(" ALTER COLUMN ");
		sb.append(columnInfoForm.getName());
		sb.append(" TYPE ");
		sb.append(getTypeSql(columnInfoForm));
		sb.append(";\n");
		return sb.toString();
	}
	
	
	
	private static String alterColumnChangeNullableSQL(String schemaName, String tableName, ColumnInfoForm columnInfoForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE "+withSchemaName(schemaName)+tableName);
		sb.append(" ALTER COLUMN ");
		sb.append(columnInfoForm.getName());
		if(!columnInfoForm.getNullable()) {
			sb.append(" SET ");
		}else {
			sb.append(" DROP ");
		}
		sb.append(" NOT NULL");
		sb.append(";\n");
		return sb.toString();
	}
	
	private static String alterColumnChangeDefaultSQL(String schemaName, String tableName, ColumnInfoForm columnInfoForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE "+withSchemaName(schemaName)+tableName);
		sb.append(" ALTER COLUMN ");
		sb.append(columnInfoForm.getName());
		if(StringUtils.isBlank(columnInfoForm.getDefaultValue())) {
			sb.append(" SET DEFAULT '"+columnInfoForm.getDefaultValue()+"'");
		}else {
			sb.append(" DROP DEFAULT");
		}
		sb.append(";\n");
		return sb.toString();
	}
	
	private static String addColumnComment(String schemaName, String tableName, ColumnInfoForm columnInfoForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("COMMENT ON COLUMN "+withSchemaName(schemaName)+tableName+"."+columnInfoForm.getName()+" IS '"+columnInfoForm.getComment()+"';").append("\n");
		return sb.toString();
	}
	
	public static String getTypeSql(ColumnInfoForm columnInfoForm) {
		if(columnInfoForm.getType().equals(MySqlType.CHARACTER_VARYING.getType())) {
			return (" "+MySqlType.CHARACTER_VARYING.getType()+"("+columnInfoForm.getCharacterMaxLength()+")");
		}else if(columnInfoForm.getType().equals(MySqlType.INTEGER.getType())) {
			return (" "+MySqlType.INTEGER.getType());
		}else if(columnInfoForm.getType().equals(MySqlType.NUMERIC.getType())) {
			return (" "+MySqlType.NUMERIC.getType()+"("+columnInfoForm.getNumericPrecision()+", "+columnInfoForm.getNumericScale()+")");
		}else {
			return (" "+MySqlType.TEXT.getType());
		}
	}
	
	private static void judgeDiffIndexInfos(String schemaName, String tableName, 
			List<IndexInfoForm> fIndexInfoForms, List<IndexInfoForm> dIndexInfoForms, StringBuffer sabun) {
		if(fIndexInfoForms.size() > dIndexInfoForms.size()) {
			for(int i=dIndexInfoForms.size(); i<fIndexInfoForms.size(); i++) {
				sabun.append(addIndexSQL(schemaName, tableName, fIndexInfoForms.get(i)));
			}
		}
	}
	
	private static String addIndexSQL(String schemaName, String tableName, IndexInfoForm indexInfoForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE INDEX IF NOT EXISTS "+indexInfoForm.getIndexName());
		sb.append(" ON "+withSchemaName(schemaName)+tableName);
		sb.append(" (");
		for(int i=0; i<indexInfoForm.getColumnNames().size(); i++) {
			if(i>0) {
				sb.append(",");
			}
			sb.append(indexInfoForm.getColumnNames().get(i));
		}
		sb.append(");\n");
		return sb.toString();
	}
	
	private static String trimDbDefaultValue(String dbDefaultValue) {
		return dbDefaultValue.replaceAll("'", "")
				.replaceAll("::character varying", "")
				.replaceAll("::integer", "")
				.replaceAll("::bigint", "")
				.replaceAll("::numeric", "");
	}
	
	private static String withSchemaName(String schemaName) {
		//return schemaName+".";
		return "";
	}
}
