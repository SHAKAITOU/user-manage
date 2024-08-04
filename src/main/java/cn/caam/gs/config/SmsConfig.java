package cn.caam.gs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

	@Value("${spring.sms.api_url}")
	private String apiUrl;
	
	@Value("${spring.sms.app_key}")
	private String appKey;
	
	@Value("${spring.sms.app_secret}")
	private String appSecret;
	
	@Value("${spring.sms.sender}")
	private String sender;
	
	@Value("${spring.sms.user_regist_template_id}")
	private String userRegistTemplateId;
	
	@Value("${spring.sms.user_login_template_id}")
	private String userLoginTemplateId;
	
	@Value("${spring.sms.user_review_template_id}")
	private String userReviewTemplateId;
	
	
	@Value("${spring.sms.signature}")
	private String signature;

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

	public String getUserRegistTemplateId() {
		return userRegistTemplateId;
	}

	public void setUserRegistTemplateId(String userRegistTemplateId) {
		this.userRegistTemplateId = userRegistTemplateId;
	}

	public String getUserLoginTemplateId() {
		return userLoginTemplateId;
	}

	public void setUserLoginTemplateId(String userLoginTemplateId) {
		this.userLoginTemplateId = userLoginTemplateId;
	}

	public String getUserReviewTemplateId() {
		return userReviewTemplateId;
	}

	public void setUserReviewTemplateId(String userReviewTemplateId) {
		this.userReviewTemplateId = userReviewTemplateId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
