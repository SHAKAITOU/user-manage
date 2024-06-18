package cn.caam.gs.app.dbmainten.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.google.common.base.CaseFormat;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.domain.tabledef.DbTableType;

public class CommonMapperBuild {

	private static String RESULT_FILE_NAME = "CommonResultMapper";
	private static String RESULT_XML_FILE_NAME = RESULT_FILE_NAME+".xml";
	private static String RESULT_JAVA_FILE_NAME = RESULT_FILE_NAME+".java";
	private static String ENTITY_PACKAGE_PATH = "cn.caam.gs.domain.db.base.entity";
	public static void build(String rootPath, String packagePath, String packageName) {
		File folder = new File(rootPath+packagePath+System.getProperty("file.separator"));
		if(!folder.exists()) {
			folder.mkdirs();
		}
		buildResultMapXml(rootPath, packagePath, packageName);
		buildJavaMapClass(rootPath, packagePath, packageName);
	}
	
	private static void buildJavaMapClass(String rootPath, String packagePath, String packageName) {
		File ddlFile = new File(rootPath+packagePath+System.getProperty("file.separator")+RESULT_JAVA_FILE_NAME);
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageName).append(";\n");
		sb.append("\n");
		sb.append("import org.apache.ibatis.annotations.Mapper;").append("\n");
		sb.append("import "+ENTITY_PACKAGE_PATH+".*;").append("\n");
		sb.append("import java.util.List;").append("\n");
		sb.append("\n");
		sb.append("/** Common Sql for ibatis mapper. */").append("\n");
		sb.append("@Mapper").append("\n");
		sb.append("public interface "+RESULT_FILE_NAME+"{").append("\n");
		for(DbTableType type : DbTableType.values()) {
			String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
			sb.append("	/** get all data from table "+type.getName()+" */").append("\n");
			sb.append("	List<"+className+"> getAll"+className+"();").append("\n");
			sb.append("\n");
		}
		sb.append("}").append("\n");
		try (PrintWriter out = new PrintWriter(ddlFile)) {
			out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void buildResultMapXml(String rootPath, String packagePath, String packageName) {

		File ddlFile = new File(rootPath+packagePath+System.getProperty("file.separator")+RESULT_XML_FILE_NAME);
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\n");
		sb.append("<mapper namespace=\""+packageName+"."+RESULT_FILE_NAME+"\">").append("\n");
		sb.append("<!-- ========== resultMap ========== -->").append("\n");
		for(DbTableType type : DbTableType.values()) {
			sb.append(getResultMap(type));
			sb.append("\n");
		}
		sb.append("<!-- ========== table HeadPrefix ========== -->").append("\n");
		for(DbTableType type : DbTableType.values()) {
			sb.append(getTableHeadPrefixSql(type));
			sb.append("\n");
		}
		sb.append("<!-- ========== sql column ========== -->").append("\n");
		for(DbTableType type : DbTableType.values()) {
			sb.append(getBaseColumnList(type));
			sb.append("\n");
		}
		sb.append("<!-- ========== select all ========== -->").append("\n");
		for(DbTableType type : DbTableType.values()) {
			sb.append(getSelectSql(type));
			sb.append("\n");
		}
		
		sb.append("</mapper>").append("\n");
		
		try (PrintWriter out = new PrintWriter(ddlFile)) {
			out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String getSelectSql(DbTableType type) {
		StringBuffer sb = new StringBuffer();
		String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
		String headPrefix = getHeadPrefix(type);
		sb.append("	<select id=\"getAll"+className+"\" resultMap=\""+className+"Map\">").append("\n");
		sb.append("		 SELECT ").append("\n");
		sb.append("			<include refid=\""+className+"BaseColList\" />").append("\n");
		sb.append("		 FROM "+type.getName()+" AS "+headPrefix).append("\n");
		sb.append("	</select>").append("\n");
		return sb.toString();
	}
	
	private static String getHeadPrefix(DbTableType type) {
		return type.getNameOmt();
	}
	
	private static String getResultMap(DbTableType type) {
		StringBuffer sb = new StringBuffer();
		String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
		String headPrefix = getHeadPrefix(type);
		sb.append("	<!-- table "+type.getName()+" resultMap -->").append("\n");
		sb.append("	<resultMap id=\""+className+"Map\" type=\""+ENTITY_PACKAGE_PATH+"."+className+"\" >").append("\n");
		for(ColumnInfoForm columnInfoForm : type.getTableInfoForm().getColumnInfos()) {
			String propName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnInfoForm.getName());
			String propResultMapId = propName.replaceFirst(propName.substring(0, 1), propName.substring(0, 1).toLowerCase());
			if(columnInfoForm.getPkFlg()) {
				sb.append("		<id column=\""+headPrefix+"_"+columnInfoForm.getName()+"\" property=\""+propResultMapId+"\"/>").append("\n");
			} else {
				sb.append("		<result column=\""+headPrefix+"_"+columnInfoForm.getName()+"\" property=\""+propResultMapId+"\"/>").append("\n");
			}
		}
		sb.append("	</resultMap>").append("\n");
		return sb.toString();
	}
	
	private static String getBaseColumnList(DbTableType type) {
		StringBuffer sb = new StringBuffer();
		String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
		String headPrefix = getHeadPrefix(type);
		sb.append("	<!-- table "+type.getName()+" base column list -->").append("\n");
		sb.append("	<sql id=\""+className+"BaseColList\">").append("\n");
		int index = 0;
		for(ColumnInfoForm columnInfoForm : type.getTableInfoForm().getColumnInfos()) {
			sb.append("		"+headPrefix+"."+columnInfoForm.getName()+" AS "+headPrefix+"_"+columnInfoForm.getName());
			index++;
			if(index == type.getTableInfoForm().getColumnInfos().size()) {
				sb.append("").append("\n");
			}else {
				sb.append(",").append("\n");
			}
		}
		sb.append("	</sql>").append("\n");
		return sb.toString();
	}
	
	private static String getTableHeadPrefixSql(DbTableType type) {
		StringBuffer sb = new StringBuffer();
		String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
		String headPrefix = getHeadPrefix(type);
		sb.append("	<sql id=\""+className+"T\">");
		sb.append(type.getName());
		sb.append("</sql>").append("\n");
		sb.append("	<sql id=\""+className+"P\">");
		sb.append(headPrefix);
		sb.append("</sql>").append("\n");
		sb.append("	<sql id=\""+className+"TP\">");
		sb.append(type.getName() + " AS " + headPrefix);
		sb.append("</sql>").append("\n");
		return sb.toString();
	}
}
