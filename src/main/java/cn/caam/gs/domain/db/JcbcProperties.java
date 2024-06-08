package cn.caam.gs.domain.db;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class JcbcProperties {
	
	@Autowired
	DBConfig dbConfig;
	
	@Autowired
	ResourceLoader resourceLoader;

	
	private DataSource dataSource;
	
	public DataSource getDataSource() throws FileNotFoundException, SQLException, IOException {
		if (dataSource == null) {
			dataSource = getGcpDataSource();
		}
		return dataSource;
	}
	
	private DataSource getGcpDataSource() throws FileNotFoundException, SQLException, IOException {

		// [START cloud_sql_postgres_servlet_create]
		// The configuration object specifies behaviors for the connection pool.
		HikariConfig config = new HikariConfig();

		// Configure which instance and what database user to connect with.
		if (dbConfig.getDbMode().equals(DBConfig.JCBC_MODE_LOCAL)) {
			// local test
			config.setDriverClassName(dbConfig.getDriverName());
			config.setJdbcUrl(dbConfig.getUrl());
			config.setUsername(dbConfig.getUser()); // e.g. "root", "postgres"
			config.setPassword(dbConfig.getPass()); // e.g. "my-password"
			config.setSchema(null);

		} else {
		    config.setDriverClassName(dbConfig.getDriverName());
            config.setJdbcUrl(dbConfig.getUrl());
            config.setUsername(dbConfig.getUser()); // e.g. "root", "postgres"
            config.setPassword(dbConfig.getPass()); // e.g. "my-password"
			// GCP evn
			//config.setDriverClassName(dbConfig.getDriverName());
			//config.setJdbcUrl(String.format("jdbc:postgresql:///%s", dbConfig.getDataBaseName()));
			//config.setUsername(dbConfig.getUser()); // e.g. "root", "postgres"
			//config.setPassword(dbConfig.getPass()); // e.g. "my-password"
			//config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
			//config.addDataSourceProperty("cloudSqlInstance", dbConfig.getUrl());


			// The ipTypes argument can be used to specify a comma delimited list of preferred IP types 
			// for connecting to a Cloud SQL instance. The argument ipTypes=PRIVATE will force the 
			// SocketFactory to connect with an instance's associated private IP. 
			//config.addDataSourceProperty("ipTypes", "PUBLIC,PRIVATE");
		}
		
		// ... Specify additional connection properties here.
		// [START_EXCLUDE]

		// [START cloud_sql_postgres_servlet_limit]
		// maximumPoolSize limits the total number of concurrent connections this pool will keep. Ideal
		// values for this setting are highly variable on app design, infrastructure, and database.
		config.setMaximumPoolSize(5);
		// minimumIdle is the minimum number of idle connections Hikari maintains in the pool.
		// Additional connections will be established to meet this value unless the pool is full.
		config.setMinimumIdle(5);
		// [END cloud_sql_postgres_servlet_limit]

		// [START cloud_sql_postgres_servlet_timeout]
		// setConnectionTimeout is the maximum number of milliseconds to wait for a connection checkout.
		// Any attempt to retrieve a connection from this pool that exceeds the set limit will throw an
		// SQLException.
		config.setConnectionTimeout(10000); // 10 seconds
		// idleTimeout is the maximum amount of time a connection can sit in the pool. Connections that
		// sit idle for this many milliseconds are retried if minimumIdle is exceeded.
		config.setIdleTimeout(600000); // 10 minutes
		// [END cloud_sql_postgres_servlet_timeout]

		// [START cloud_sql_postgres_servlet_backoff]
		// Hikari automatically delays between failed connection attempts, eventually reaching a
		// maximum delay of `connectionTimeout / 2` between attempts.
		// [END cloud_sql_postgres_servlet_backoff]

		// [START cloud_sql_postgres_servlet_lifetime]
		// maxLifetime is the maximum possible lifetime of a connection in the pool. Connections that
		// live longer than this many milliseconds will be closed and reestablished between uses. This
		// value should be several minutes shorter than the database's timeout value to avoid unexpected
		// terminations.
		config.setMaxLifetime(1800000); // 30 minutes
		// [END cloud_sql_postgres_servlet_lifetime]

		// [END_EXCLUDE]

		// Initialize the connection pool using the configuration object.
		DataSource pool = new HikariDataSource(config);
		// [END cloud_sql_postgres_servlet_create]
		return pool;
	}
}
