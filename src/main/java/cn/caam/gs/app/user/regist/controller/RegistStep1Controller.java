package cn.caam.gs.app.user.regist.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.user.regist.view.RegistStep1ViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.service.impl.AuthCodeService;
import cn.caam.gs.service.impl.FixedValueService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=LoginViewHelper.URL_BASE)
public class RegistStep1Controller extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	AuthCodeService authCodeService;
	
    @Autowired
    FixedValueService fixedValueService;
    
    @Autowired
    SmsConfig smsConfig;
    
	private static final String FROMREDIRECT = "fromRedirect";
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST)
    public ModelAndView registInit(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
	    if (pageForm.getStepStatus() != null) {
	        RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
	        registForm.setErrorMsg("");
	        return ControllerHelper.getModelAndView(RegistStep1ViewHelper.getRegist1Page(registForm));
	    } else {
	        pageForm.setErrorMsg("");
	        pageForm.setStepStatus(LoginViewHelper.STEP_STS_STEP1_INIT);
	        pageForm.setUser(new MUser());
	        request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), pageForm);
	        if (request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue()) == null) {
	            request.getSession().setAttribute(SessionConstants.FIXED_VALUE.getValue(), 
                    fixedValueService.getMap());
	        }
	        return ControllerHelper.getModelAndView(RegistStep1ViewHelper.getRegist1Page(pageForm));
	    }
    }
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_SEND_AUTH_CODE)
	@ResponseBody
    public String sendAuthCode(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
	    registForm.setUser(pageForm.getUser());
	    
	    //X分钟以内只允许发送一次
	    if (registForm.getMauthCode() != null && !StringUtil.isBlank(registForm.getMauthCode().getRecievedBy()) && 
	    		!authCodeService.isCanSendSms(registForm.getMauthCode().getRecievedBy(), GlobalConstants.USER_REGIST_EXPIRED_MINUTE, GlobalConstants.USER_REGIST_SMS_SEND_INTERVAL)){
            return LoginViewHelper.STEP_STS_STEP1_NG_SEND_SMS;
	    }
	    
	    boolean result = false;
	    if (StringUtils.isEmpty(request.getParameter(FROMREDIRECT))) {
	        //入力エラーじゃない場合
	        MAuthCode mauthCode = authCodeService.addAuthCode(pageForm);
	        registForm.setMauthCode(mauthCode);
	        request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), registForm);
	        result = authCodeService.sendAuthCode(smsConfig, smsConfig.getUserRegistTemplateId(), mauthCode);
	    }

	    if (result) {
	    	//验证码送信成功
            return LoginViewHelper.STEP_STS_STEP2_INIT;
	    }else{
            //验证码送信失败
            return LoginViewHelper.STEP_STS_STEP1_NG;
        }
    }	
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_COMMIT)
    public ModelAndView registCommit(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
//	    registForm.setUser(pageForm.getUser());
//	    if (StringUtils.isEmpty(request.getParameter(FROMREDIRECT))) {
//	        //入力エラーじゃない場合
//	        MAuthCode mauthCode = authCodeService.addAuthCode(pageForm);
//	        registForm.setMauthCode(mauthCode);
//	        request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), registForm);
//	        boolean result = authCodeService.sendAuthCode(mauthCode);
//	    }

	    return ControllerHelper.getModelAndView(RegistStep1ViewHelper.getRegist1ConfirmPage(registForm));
    }
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_COMMIT_CONFIRM)
	@ResponseBody
    public String registConfirm(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
        
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
	    if (registForm == null || registForm.getMauthCode() == null) {
	    	//認証コード不正
            return LoginViewHelper.STEP_STS_STEP1_NG;
	    }else if (!pageForm.getAuthCode().equals(registForm.getMauthCode().getAuthCode())) {
            //認証コード不正
            return LoginViewHelper.STEP_STS_STEP1_NG;
        } else if (authCodeService.isAuthCodeExpired(registForm.getMauthCode())) {
        	 //認証コード过期
            return LoginViewHelper.STEP_STS_STEP1_NG_EXPIRED;
    	}else{
            //go to STEP2
            return LoginViewHelper.STEP_STS_STEP2_INIT;
        }

    }
}
