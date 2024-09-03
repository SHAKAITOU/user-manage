package cn.caam.gs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

	private String apiUrl;
	
	private String appKey;
	
	private String appSecret;
	
	private String sender;
	
	private String templateId;
	
	private String templateName;
	
	private String signature;

	
	public SmsConfig(String apiUrl, String appKey, String appSecret, String sender, String signature, String templateId,
			String templateName) {
		super();
		this.apiUrl = apiUrl;
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.sender = sender;
		this.templateId = templateId;
		this.templateName = templateName;
		this.signature = signature;
	}
	
	public SmsConfig() {
		super();
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
