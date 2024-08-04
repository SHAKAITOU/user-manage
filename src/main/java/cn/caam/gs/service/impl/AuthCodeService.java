package cn.caam.gs.service.impl;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.user.regist.view.RegistStep1ViewHelper;
import cn.caam.gs.common.enums.MailSendResultType;
import cn.caam.gs.common.util.HuaWeiSMSUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.common.util.MailUtil;
import cn.caam.gs.common.util.MailUtil.MailDataSet;
import cn.caam.gs.config.MailConfig;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.base.mapper.MAuthCodeMapper;
import cn.caam.gs.domain.db.custom.mapper.OptionalAuthCodeInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthCodeService extends BaseService {
	
	@Autowired
	OptionalAuthCodeInfoMapper optionalAuthCodeInfoMapper;
	
	@Autowired
	MAuthCodeMapper mauthCodeMapper;
	
//	@Autowired
//    SmsConfig smsConfig;
	
	@Autowired
    MailConfig mailConfig;
	
	@Autowired
	TemplateEngine templateEngine;

	public MAuthCode addAuthCode(RegistForm pageForm) {
	    MAuthCode mauthCode = new MAuthCode();
	    String uuid = UUID.randomUUID().toString();
	    mauthCode.setId(uuid);
	    mauthCode.setAuthMethod(pageForm.getAuthMethod());
	    if (mauthCode.getAuthMethod().equals(RegistStep1ViewHelper.GET_AUTH_CODE_BY_PHONE)) {
	        mauthCode.setRecievedBy(pageForm.getUser().getPhone());
	    } else {
	        mauthCode.setRecievedBy(pageForm.getUser().getMail());
	    }
	    mauthCode.setAuthCode(getRandomAuthCode());
//	    mauthCode.setAuthCode("123456");
	    LocalDateTime expiredDt = LocalDateTime.now();
	    mauthCode.setInvalidDate(LocalDateUtility.formatDateTime(expiredDt.plusMinutes(GlobalConstants.USER_REGIST_EXPIRED_MINUTE), DateTimePattern.UUUUMMDDHHMISS));
	    mauthCodeMapper.insert(mauthCode);
    	return mauthCodeMapper.selectByPrimaryKey(uuid);
	}
	
	public MAuthCode addPhoneAuthCode(String phone, int expiredMinute) {
	    MAuthCode mauthCode = new MAuthCode();
	    String uuid = UUID.randomUUID().toString();
	    mauthCode.setId(uuid);
	    mauthCode.setAuthMethod(RegistStep1ViewHelper.GET_AUTH_CODE_BY_PHONE);
	    mauthCode.setRecievedBy(phone);
	    mauthCode.setAuthCode(getRandomAuthCode());
	    LocalDateTime expiredDt = LocalDateTime.now();
	    mauthCode.setInvalidDate(LocalDateUtility.formatDateTime(expiredDt.plusMinutes(expiredMinute), DateTimePattern.UUUUMMDDHHMISS));
	    mauthCodeMapper.insert(mauthCode);
    	return mauthCodeMapper.selectByPrimaryKey(uuid);
	}
	
	private String getRandomAuthCode() {
	    int length = 6;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        
        return randomString.toString();
	}
	
	public boolean isCanSendSms(String receiveBy, int expiredMinute, int sendInterval) {
		String maxInvalidDate = optionalAuthCodeInfoMapper.selectRecentlyInvalidDateByReceiveBy(receiveBy);
		if (!StringUtil.isBlank(maxInvalidDate)) {
			String invalidDate = LocalDateUtility.addMin(maxInvalidDate, DateTimePattern.UUUUMMDDHHMISS, expiredMinute*-1);
			invalidDate = LocalDateUtility.addMin(invalidDate, DateTimePattern.UUUUMMDDHHMISS, sendInterval);
	        
			String now = LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUMMDDHHMISS);
			return LocalDateUtility.isAfterDateTime(now, invalidDate,DateTimePattern.UUUUMMDDHHMISS);
		}
		
		return true;
	}
	
	public boolean sendAuthCode(SmsConfig smsConfig, String templateId, MAuthCode mauthCode) throws Exception{
		boolean result = false;
		if (mauthCode != null && !StringUtil.isBlank(mauthCode.getAuthCode())) {
			 if (RegistStep1ViewHelper.GET_AUTH_CODE_BY_PHONE.equals(mauthCode.getAuthMethod())) {
				 result = sendAuthCodeToPhone(smsConfig, templateId, mauthCode.getRecievedBy(), mauthCode.getAuthCode(), GlobalConstants.USER_REGIST_EXPIRED_MINUTE);
		    } else {
		    	result = sendAuthCodeToMail(mauthCode.getRecievedBy(), mauthCode.getAuthCode(), GlobalConstants.USER_REGIST_EXPIRED_MINUTE);
		    }
		}
	   
		return result;
	}
	
	public boolean sendAuthCodeToPhone(SmsConfig smsConfig, String templateId, String phoneNumber, String authCode, int validMinute)  throws Exception{
		return HuaWeiSMSUtil.sendAuthCode(smsConfig, templateId, phoneNumber, authCode, validMinute);
	}
	
	public boolean sendAuthCodeToMail(String mail, String authCode, int validMinute)  throws Exception{
		MailDataSet mailDataSet = new MailDataSet();
  	  /** 送信者の表示名. */
      mailDataSet.setFromName(mailConfig.getFrom());
      /** 送信アドレス. */
      mailDataSet.setFrom(mailConfig.getFrom());
      /** 宛先(TO). */
      mailDataSet.setToArray(new String[] {mail});
      /** 宛先(CC). */
      mailDataSet.setCcArray(new String[0]);
      /** 宛先(BCC). */
      mailDataSet.setBccArray(new String[0]);
      /** 認証(ユーザー名). */
      mailDataSet.setAuthUsername(mailConfig.getUsername());
      /** 認証(パスワード). */
      mailDataSet.setAuthPassword(mailConfig.getPassword());
      /** ホスト名. */
      mailDataSet.setHost(mailConfig.getHost());
      /** ポート. */
      mailDataSet.setPort(mailConfig.getPort());
      /** 暗号化(TLS保護接続への接続). */
      mailDataSet.setStarttls("true".equalsIgnoreCase(mailConfig.getStarttls()));
      /** 件名. */
      mailDataSet.setSubject("登录验证码");
      /** 本文*/
      Context context = new Context();
      // 设置上下文
      context.setVariable("verifyCode", authCode);
      context.setVariable("validMinute", validMinute);
      
	  String content = templateEngine.process(mailConfig.getRegistAuthcodeTemplate(), context);
      mailDataSet.setContent(content);
      MailSendResultType resultType = MailUtil.send(mailDataSet);
      log.info("验证码发送失败。邮箱="+mail+" MSG="+resultType.getMsg());
      return "ok".equals(resultType.getKey());
	}
	
	public boolean isAuthCodeExpired(MAuthCode mauthCode) {
		String now = LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUMMDDHHMISS);
		return !StringUtil.isBlank(mauthCode.getInvalidDate()) && now.compareTo(mauthCode.getInvalidDate()) > 0;
	}
	
}
