package cn.caam.gs.domain.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

	public static final String JCBC_MODE = "${spring.datasource.mode}";
	public static final String JCBC_MODE_LOCAL = "local-MySQL";
	public static final String JCBC_MODE_CLOUD = "cloud-MySQL";
	
	public static final String JCBC_YML_L_MYSQL_DB_DRIVER = "${spring.datasource.local-MySQL.driver-class-name}";
	public static final String JCBC_YML_L_MYSQL_DB_URL = "${spring.datasource.local-MySQL.jdbc-url}";
	public static final String JCBC_YML_L_MYSQL_DB_USER = "${spring.datasource.local-MySQL.username}";
	public static final String JCBC_YML_L_MYSQL_DB_PASS = "${spring.datasource.local-MySQL.password}";
	
    public static final String JCBC_YML_C_MYSQL_DB_DRIVER = "${spring.datasource.cloud-MySQL.driver-class-name}";
    public static final String JCBC_YML_C_MYSQL_DB_URL = "${spring.datasource.cloud-MySQL.jdbc-url}";
    public static final String JCBC_YML_C_MYSQL_DB_USER = "${spring.datasource.cloud-MySQL.username}";
    public static final String JCBC_YML_C_MYSQL_DB_PASS = "${spring.datasource.cloud-MySQL.password}";

	public static final String JCBC_SOURCE_BEAN_NAME = "jcbcDataSource";
	public static final String JCBC_BASE_MAPPER_PACKAGE = "cn.caam.gs.domain.db.base.mapper";
	public static final String JCBC_OPTIONAL_MAPPER_PACKAGE = "cn.caam.gs.domain.db.custom.mapper";
	public static final String JCBC_SQLSESSION_BEAN_NAME = "jcbcSqlSessionTemplate";
	public static final String JCBC_BASE_MAPPER_XML_PATH = "classpath:cn/caam/gs/domain/db/base/sql/*.xml";
	public static final String JCBC_OPTIONAL_MAPPER_XML_PATH = "classpath:cn/caam/gs/domain/db/custom/sql/*.xml";
	
	
	@Value(DBConfig.JCBC_MODE)
	private String MODE;
	
	@Value(DBConfig.JCBC_YML_C_MYSQL_DB_DRIVER)
	private String DB_DRIVER;
	
	@Value(DBConfig.JCBC_YML_C_MYSQL_DB_USER)
	private String DB_USER;
	
	@Value(DBConfig.JCBC_YML_C_MYSQL_DB_PASS)
	private String DB_PASS;
	
	@Value(DBConfig.JCBC_YML_C_MYSQL_DB_URL)
	private String DB_URL;
	
	
	@Value(DBConfig.JCBC_YML_L_MYSQL_DB_DRIVER)
	private String L_DB_DRIVER;
	
	
	@Value(DBConfig.JCBC_YML_L_MYSQL_DB_USER)
	private String L_DB_USER;
	
	@Value(DBConfig.JCBC_YML_L_MYSQL_DB_PASS)
	private String L_DB_PASS;
	
	@Value(DBConfig.JCBC_YML_L_MYSQL_DB_URL)
	private String L_DB_URL;
	
	public String getDbMode() {
		return MODE;
	}
	
	public String getDriverName() {
		if (getDbMode().equals(JCBC_MODE_LOCAL)) {
			return L_DB_DRIVER;
		} else {
			return DB_DRIVER;
		}
	}
	
	public String getUrl() {
		if (getDbMode().equals(JCBC_MODE_LOCAL)) {
			return L_DB_URL;
		} else {
			return DB_URL;
		}
	}
	
	public String getUser() {
		if (getDbMode().equals(JCBC_MODE_LOCAL)) {
			return L_DB_USER;
		} else {
			return DB_USER;
		}
	}
	public String getPass() {
		if (getDbMode().equals(JCBC_MODE_LOCAL)) {
			return L_DB_PASS;
		} else {
			return DB_PASS;
		}
	}
}
