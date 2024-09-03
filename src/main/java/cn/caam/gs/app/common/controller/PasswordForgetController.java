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
import cn.caam.gs.app.common.form.PasswordForgetForm;
import cn.caam.gs.app.common.view.PasswordForgetViewHelper;
import cn.caam.gs.app.user.login.form.LoginForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.SmsConfigType;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.service.impl.AuthCodeService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=PasswordForgetViewHelper.URL_BASE)
public class PasswordForgetController extends JcbcBaseController{
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthCodeService authCodeService;
	
	@Autowired
	SmsConfig smsConfig;
	
    @PostMapping(path=PasswordForgetViewHelper.URL_C_INIT)
	public ModelAndView init(
			PasswordForgetForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
    	pageForm.setErrorMsg("");
		return ControllerHelper.getModelAndView(
				PasswordForgetViewHelper.getPasswordForgetStep1(pageForm));
	}
    
    @PostMapping(path=PasswordForgetViewHelper.URL_C_STEP2)
    public ModelAndView step2(
    		PasswordForgetForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
//        if (userService.checkPasswordCorrect(request, pageForm.getOldPassword())) {
//        	userService.updatePassword(request, pageForm.getNewPassword());
//        }else {
//        	return ExecuteReturnType.NG.getId();
//        }
    	return ControllerHelper.getModelAndView(
				PasswordForgetViewHelper.getPasswordForgetStep2(pageForm));
        
    }
    
    @PostMapping(path=PasswordForgetViewHelper.URL_C_EDIT)
    @ResponseBody
    public int edit(
    		PasswordForgetForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (!pageForm.getNewPassword().equals(pageForm.getNewPasswordRep()) || 
        		!userService.isPhoneNumberExist(pageForm.getPhone())) {
        	return ExecuteReturnType.NG.getId();
        }
        userService.updateUserPassword(request, pageForm.getPhone(), pageForm.getNewPassword());
    	
    	return ExecuteReturnType.OK.getId();
    }
    
    @PostMapping(path=PasswordForgetViewHelper.URL_C_SEND_AUTH_CODE)
	@ResponseBody
    public String sendAuthCode(
    		PasswordForgetForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String result = "";
		if (!StringUtil.isBlank(pageForm.getPhone())) {
			if (!userService.isPhoneNumberExist(pageForm.getPhone())) {
				result = messageSourceUtil.getContext("login.fail.msg.notexistphone");
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
	        
	    return result;
    }
}
