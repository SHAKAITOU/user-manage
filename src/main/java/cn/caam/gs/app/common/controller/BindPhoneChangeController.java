package cn.caam.gs.app.common.controller;


import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.common.form.BindPhoneChangeForm;
import cn.caam.gs.app.common.view.BindPhoneChangeViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.LoginAccountType;
import cn.caam.gs.common.enums.SmsConfigType;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.AuthCodeService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=BindPhoneChangeViewHelper.URL_BASE)
public class BindPhoneChangeController extends JcbcBaseController{
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthCodeService authCodeService;
	
	@Autowired
	SmsConfig smsConfig;
	
    @PostMapping(path=BindPhoneChangeViewHelper.URL_C_INIT)
	public ModelAndView init(
			BindPhoneChangeForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
    	if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
    		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
    		pageForm.setId(userInfo.getUser().getId());
    	}
    	pageForm.setErrorMsg("");
    	
		return ControllerHelper.getModelAndView(
				BindPhoneChangeViewHelper.getMainPage(request, pageForm));
	}
    
    @PostMapping(path=BindPhoneChangeViewHelper.URL_C_EDIT)
    @ResponseBody
    public String edit(
    		BindPhoneChangeForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
    	if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
    		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
    		MAuthCode mauthCode = (MAuthCode)request.getSession().getAttribute(SessionConstants.AUTH_CODE.getValue());
			if (userService.isPhoneNumberExist(pageForm.getPhone(), userInfo.getUser().getId())) {
				return messageSourceUtil.getContext("BindPhoneChange.phone.existed.msg");
		    }else if (mauthCode == null || StringUtil.isBlank(mauthCode.getAuthCode())) {
		    	return messageSourceUtil.getContext("login.authCode.request.send.msg");
		    }else if (authCodeService.isAuthCodeExpired(mauthCode)){
		    	return messageSourceUtil.getContext("login.confirm.authCodeExpired");
		    }else if (!mauthCode.getAuthCode().equals(pageForm.getAuthCode())) {
		    	return messageSourceUtil.getContext("login.confirm.wrongAuthCode");
		    }else if (!mauthCode.getRecievedBy().equals(pageForm.getPhone())) {
		    	return messageSourceUtil.getContext("BindPhoneChange.fail.msg");
		    }else {
		    	userService.updateUserPhone(request, userInfo.getUser().getId(), pageForm.getPhone());
		    }
		}
    	
    	return "";
    }
    
    @PostMapping(path=BindPhoneChangeViewHelper.URL_C_SEND_AUTH_CODE)
	@ResponseBody
    public String sendAuthCode(
    		BindPhoneChangeForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String result = "";
		if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
			if (!StringUtil.isBlank(pageForm.getPhone())) {
				if (userService.isPhoneNumberExist(pageForm.getPhone(), pageForm.getId())) {
					result = messageSourceUtil.getContext("BindPhoneChange.phone.existed.msg");
				}
			    //X分钟以内只允许发送一次
				else if (pageForm.getAuthCode() != null && !StringUtil.isBlank(pageForm.getAuthCode()) && 
			    		!authCodeService.isCanSendSms(pageForm.getPhone(), GlobalConstants.AUTH_CODE_EXPIRED_MINUTE, GlobalConstants.AUTH_CODE_SEND_INTERVAL_MINUTE)){
			    	result = MessageFormat.format(messageSourceUtil.getContext("login.authCode.send.prohibit"), GlobalConstants.AUTH_CODE_SEND_INTERVAL_MINUTE);
			    }else {
				    //入力エラーじゃない場合
			        MAuthCode mauthCode = authCodeService.addPhoneAuthCode(pageForm.getPhone(), GlobalConstants.AUTH_CODE_EXPIRED_MINUTE);
			        request.getSession().setAttribute(SessionConstants.AUTH_CODE.getValue(), mauthCode);
			        boolean isOk = authCodeService.sendAuthCode(SmsConfigType.COMMON_VERIFICATION_CODE, mauthCode, GlobalConstants.AUTH_CODE_EXPIRED_MINUTE);
			        if (!isOk) {
			        	//result = messageSourceUtil.getContext("login.authCode.send.error");
			        }
			    }
			}else {
				result = messageSourceUtil.getContext("notinputphone");
			}
		}
	        
	    return result;
    }
}
