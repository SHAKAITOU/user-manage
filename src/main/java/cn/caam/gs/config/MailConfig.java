package cn.caam.gs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.from}")
	private String from;
	
	@Value("${spring.mail.properties.smtp.auth}")
	private String auth;
	
	@Value("${spring.mail.properties.smtp.starttls.enable}")
	private String starttls;
	
	@Value("${spring.mail.templates.regist_authcode_template}")
	private String registAuthcodeTemplate;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getStarttls() {
		return starttls;
	}

	public void setStarttls(String starttls) {
		this.starttls = starttls;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getRegistAuthcodeTemplate() {
		return registAuthcodeTemplate;
	}

	public void setRegistAuthcodeTemplate(String registAuthcodeTemplate) {
		this.registAuthcodeTemplate = registAuthcodeTemplate;
	}
	
	
}
