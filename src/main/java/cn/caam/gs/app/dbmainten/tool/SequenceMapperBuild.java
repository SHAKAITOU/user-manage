package cn.caam.gs.app.dbmainten.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;

import com.google.common.base.CaseFormat;

import cn.caam.gs.domain.tabledef.DbTableType;

public class SequenceMapperBuild {

	private static String RESULT_FILE_NAME_PG = "OptionalSequenceMapper";
	private static String RESULT_JAVA_FILE_NAME_PG = RESULT_FILE_NAME_PG+".java";
	
	private static String RESULT_FILE_NAME_H2 = "OptionalH2SequenceMapper";
	private static String RESULT_JAVA_FILE_NAME_H2 = RESULT_FILE_NAME_H2+".java";

	public static void build(String rootPath, String packagePath, String packageName) {
		File folder = new File(rootPath+packagePath+System.getProperty("file.separator"));
		if(!folder.exists()) {
			folder.mkdirs();
		}
		buildJavaMapClassPG(rootPath, packagePath, packageName);
		buildJavaMapClassH2(rootPath, packagePath, packageName);
	}
	
	private static void buildJavaMapClassPG(String rootPath, String packagePath, String packageName) {
		File ddlFile = new File(rootPath+packagePath+System.getProperty("file.separator")+RESULT_JAVA_FILE_NAME_PG);
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageName).append(";\n");
		sb.append("\n");
		sb.append("import org.apache.ibatis.annotations.Mapper;").append("\n");
		sb.append("import org.apache.ibatis.annotations.Select;").append("\n");
		sb.append("\n");
		sb.append("/** sequence Sql for ibatis mapper. */").append("\n");
		sb.append("@Mapper").append("\n");
		sb.append("public interface "+RESULT_FILE_NAME_PG+"{").append("\n");
		for(DbTableType type : DbTableType.values()) {
			String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
			if(Objects.nonNull(type.getTableInfoForm().getSequenceInfo())) {
				sb.append("	/** get next id from table "+type.getName()+" */").append("\n");
				sb.append("	@Select(\"SELECT nextval('"+
				type.getTableInfoForm().getSequenceInfo().getSeqName()+"');\")").append("\n");
				sb.append("	int getNext"+className+"Id();").append("\n");
				sb.append("\n");
			}
		}
		sb.append("}").append("\n");
		try (PrintWriter out = new PrintWriter(ddlFile)) {
			out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void buildJavaMapClassH2(String rootPath, String packagePath, String packageName) {
		File ddlFile = new File(rootPath+packagePath+System.getProperty("file.separator")+RESULT_JAVA_FILE_NAME_H2);
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageName).append(";\n");
		sb.append("\n");
		sb.append("import org.apache.ibatis.annotations.Mapper;").append("\n");
		sb.append("import org.apache.ibatis.annotations.Select;").append("\n");
		sb.append("\n");
		sb.append("/** sequence Sql for ibatis mapper. */").append("\n");
		sb.append("@Mapper").append("\n");
		sb.append("public interface "+RESULT_FILE_NAME_H2+"{").append("\n");
		for(DbTableType type : DbTableType.values()) {
			String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName());
			if(Objects.nonNull(type.getTableInfoForm().getSequenceInfo())) {
				sb.append("	/** get next id from table "+type.getName()+" */").append("\n");
				sb.append("	@Select(\"VALUES NEXT VALUE FOR "+
				type.getTableInfoForm().getSequenceInfo().getSeqName()+";\")").append("\n");
				sb.append("	int getNext"+className+"Id();").append("\n");
				sb.append("\n");
			}
		}
		sb.append("}").append("\n");
		try (PrintWriter out = new PrintWriter(ddlFile)) {
			out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
