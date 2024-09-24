package cn.caam.gs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.common.enums.SmsConfigType;
import cn.caam.gs.common.util.HuaWeiSMSUtil;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MSmsConfig;
import cn.caam.gs.domain.db.custom.mapper.OptionalSmsConfigMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SmsSendService extends BaseService {
	
	@Autowired
	OptionalSmsConfigMapper optionalSmsConfigMapper;
	
	private SmsConfig getSmsConfig(SmsConfigType smsConfigType) throws Exception{
		MSmsConfig mSmsConfig = optionalSmsConfigMapper.selectByTemplateType(smsConfigType.getKey());
		if (mSmsConfig == null) {
			throw new Exception("sms template is not existed!");
		}
		SmsConfig smsConfig = new SmsConfig();
		smsConfig.setApiUrl(mSmsConfig.getApiUrl());
		smsConfig.setAppKey(mSmsConfig.getAppKey());
		smsConfig.setAppSecret(mSmsConfig.getAppSecret());
		smsConfig.setSender(mSmsConfig.getSender());
		smsConfig.setSignature(mSmsConfig.getSignature());
		smsConfig.setTemplateId(mSmsConfig.getTemplateId());
		smsConfig.setTemplateName(mSmsConfig.getTemplateName());
		
		return smsConfig;
	}

	public boolean sendPasswordReset(SmsConfigType smsConfigType, String phoneNumber, String password) throws Exception{
		
		return HuaWeiSMSUtil.sendPasswordReset(getSmsConfig(smsConfigType), phoneNumber, password);
	}
	
	public boolean sendUserReviewMessage(SmsConfigType smsConfigType, String phoneNumber) throws Exception{
		
		return HuaWeiSMSUtil.sendUserReviewMessage(getSmsConfig(smsConfigType), phoneNumber);
	}
}
