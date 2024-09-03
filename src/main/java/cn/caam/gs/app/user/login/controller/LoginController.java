package cn.caam.gs.app.user.login.controller;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.JavaScriptSet;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.user.login.form.IndexForm;
import cn.caam.gs.app.user.login.form.LoginForm;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.SmsConfigType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.AuthCodeService;
import cn.caam.gs.service.impl.FixedValueService;
import cn.caam.gs.service.impl.MessageService;
import cn.caam.gs.service.impl.UserService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(value = { "/", LoginViewHelper.URL_BASE})
public class LoginController extends ScreenBaseController{
    
    private static final String PARAM_INVALID_LOGIN = "inVlidLogin"; 
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	FixedValueService fixedValueService;
	
	@Autowired
    UserService userService;
	
   @Autowired
   MessageService messageService;
   
   @Autowired
   AuthCodeService authCodeService;
   
   @Autowired
   SmsConfig smsConfig;

	@GetMapping("")
	public ModelAndView index(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response)  {
	    
	    if (allRequestParams.isEmpty()) {
            request.getSession().setAttribute(SessionConstants.LOGIN_ERROR_MSG.getValue(), null);
        }

		ModelAndView mav = new ModelAndView();
		//if(!LoginInfoHelper.isLogined(request)) {
			IndexForm indexForm = new IndexForm();
			JavaScriptSet javaScriptSetInfo = new JavaScriptSet(request, environment, messageSourceUtil);
			request.getSession().setAttribute(SessionConstants.JAVASCRIPT_SET_INFO.getValue(), javaScriptSetInfo.init());
			
			indexForm.setEnvMode(environment.getProperty("spring.datasource.mode"));
			
			setMobileDisp(allRequestParams, request);
			
			mav.addObject("indexForm", indexForm);
			mav.setViewName(LoginViewHelper.HTML_INDEX);
		//}else {
		//	mav.setViewName(MenuViewHelper.HTML_MENU_MENU);
		//}
		return mav;
	}
	
	
	@PostMapping(path=LoginViewHelper.URL_C_LOGIN_INIT)
	public ModelAndView initLogin(IndexForm indexForm,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().setAttribute(SessionConstants.MEDIA_HEIGHT.getValue(), indexForm.getMediaHeight());
		request.getSession().setAttribute(SessionConstants.MEDIA_WIDTH.getValue(), indexForm.getMediaWidth());
		request.getSession().setAttribute(SessionConstants.IS_MOBILE.getValue(), indexForm.getMobileDisplay());
		ModelAndView mav = new ModelAndView();
		LoginForm loginForm = (LoginForm)request.getSession().getAttribute(SessionConstants.LOGIN_FORM.getValue());
		if (loginForm == null ) {
			loginForm = new LoginForm();
			loginForm.setUserCode("M24060914330223");
			loginForm.setPassword("1");
		}
		
		loginForm.setAuthImg(EncryptorUtil.generateAuthImgStr(request));
		if(!StringUtils.isEmpty(indexForm.getLoginFormJson())) {
			loginForm = JsonUtility.toObject(indexForm.getLoginFormJson(), LoginForm.class);
		}
		mav.addObject("loginForm", loginForm);
		mav.setViewName(LoginViewHelper.HTML_LOGIN);
		return mav;
	}
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_SEND_AUTH_CODE)
	@ResponseBody
    public String sendAuthCode(
    		LoginForm loginForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String result = "";
		if (!StringUtil.isBlank(loginForm.getPhoneUserCode())) {
			if (!userService.isPhoneNumberExist(loginForm.getPhoneUserCode())) {
				result = messageSourceUtil.getContext("login.fail.msg.notexistphone");
			}
		    //X分钟以内只允许发送一次
			else if (loginForm.getPhoneAuthCode() != null && !StringUtil.isBlank(loginForm.getPhoneUserCode()) && 
		    		!authCodeService.isCanSendSms(loginForm.getPhoneUserCode(), GlobalConstants.AUTH_CODE_EXPIRED_MINUTE, GlobalConstants.AUTH_CODE_SEND_INTERVAL_MINUTE)){
		    	result = MessageFormat.format(messageSourceUtil.getContext("login.authCode.send.prohibit"), GlobalConstants.AUTH_CODE_SEND_INTERVAL_MINUTE);
		    }else {
			    //入力エラーじゃない場合
		        MAuthCode mauthCode = authCodeService.addPhoneAuthCode(loginForm.getPhoneUserCode(), GlobalConstants.AUTH_CODE_EXPIRED_MINUTE);
		        request.getSession().setAttribute(SessionConstants.AUTH_CODE.getValue(), mauthCode);
		        boolean isOk = authCodeService.sendAuthCode(SmsConfigType.COMMON_VERIFICATION_CODE, mauthCode, GlobalConstants.AUTH_CODE_EXPIRED_MINUTE);
		        if (!isOk) {
		        	result = messageSourceUtil.getContext("login.authCode.send.error");
		        }
		    }
		}else {
			result = messageSourceUtil.getContext("notinputphone");
		}
	        
	    return result;
    }	
	

	@PostMapping(path=LoginViewHelper.URL_C_USER_LOGIN)
	public ModelAndView menu(LoginForm loginForm, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean okFlag = false;
		boolean noSession = false;
		
		UserInfo userInfo = null;
		if (LoginViewHelper.LOGIN_BY_AUTH_CODE_TAB.equals(loginForm.getLoginBy())) {//手机号+验证码登录
			MAuthCode mauthCode = (MAuthCode)request.getSession().getAttribute(SessionConstants.AUTH_CODE.getValue());
			if (!userService.isPhoneNumberExist(loginForm.getPhoneUserCode())) {
				loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notexistphon"));
			    okFlag = false;
		    }else if (mauthCode == null || StringUtil.isBlank(mauthCode.getAuthCode())) {
		    	loginForm.setErrorMsg(messageSourceUtil.getContext("login.authCode.request.send.msg"));
			    okFlag = false;
		    }else if (authCodeService.isAuthCodeExpired(mauthCode)){
		    	loginForm.setErrorMsg(messageSourceUtil.getContext("login.confirm.authCodeExpired"));
			    okFlag = false;
		    }else if (!mauthCode.getAuthCode().equals(loginForm.getPhoneAuthCode())) {
		    	loginForm.setErrorMsg(messageSourceUtil.getContext("login.confirm.wrongAuthCode"));
			    okFlag = false;
		    }else {
				 userInfo = userService.getLoginUserInfoByPhone(loginForm.getPhoneUserCode());
				 if (userInfo == null) {
				    loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notexist"));
				    okFlag = false;
				}  else {
					okFlag = true;
				}
		    }
		}else {//会员号登录
			 userInfo = userService.getLoginUserInfo(loginForm.getUserCode());
			 
			 String ePw = EncryptorUtil.encrypt(loginForm.getPassword());
			if (request.getSession().getAttribute(SessionConstants.VERIFY_CODE.getValue()) == null) {
	            okFlag = false;
	            loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notRightLogin"));
	        } else if (userInfo == null) {
			    loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notexist"));
			    okFlag = false;
			} else if(!userInfo.getUser().getPassword().equals(ePw)) {
			    loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.wrongPw"));
			    okFlag = false;
			} else {
				okFlag = true;
			}
		}
		
		// TODO auth check
        String authCode = (String)request.getSession().getAttribute(SessionConstants.VERIFY_CODE.getValue());
		
		ModelAndView mav = new ModelAndView();
		if(noSession) {
			mav = new ModelAndView("redirect:"+LoginViewHelper.URL_BASE);
		}
		else if(!okFlag) {
		    clearSession(request);
			loginForm.setReturnType(ExecuteReturnType.NG.getId());
			loginForm.setPhoneAuthCode("");
			request.getSession().setAttribute(SessionConstants.LOGIN_FORM.getValue(), loginForm);
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), null);
			IndexForm indexForm = new IndexForm();
			indexForm.setLoginFormJson(JsonUtility.toJson(loginForm));
			mav.addObject("indexForm", indexForm);
			request.getSession().setAttribute(SessionConstants.LOGIN_ERROR_MSG.getValue(), 
                    loginForm.getErrorMsg());
			mav = new ModelAndView("redirect:"+LoginViewHelper.URL_BASE + "?"+PARAM_INVALID_LOGIN+"=true");
		} else {
		    request.getSession().setAttribute(SessionConstants.LOGIN_ERROR_MSG.getValue(), null);
		    request.getSession().setAttribute(SessionConstants.LOGIN_FORM.getValue(), null);
		    request.getSession().setAttribute(SessionConstants.AUTH_CODE.getValue(), null);
			loginForm.setReturnType(ExecuteReturnType.OK.getId());
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), userInfo);
			JavaScriptSet javaScriptSetInfo = new JavaScriptSet(request, environment, messageSourceUtil);
			request.getSession().setAttribute(SessionConstants.JAVASCRIPT_SET_INFO.getValue(), 
					javaScriptSetInfo.loginAdminSet());
			request.getSession().setAttribute(SessionConstants.FIXED_VALUE.getValue(), 
			        fixedValueService.getMap());
			
			request.getSession().setAttribute(SessionConstants.UN_READ_MESSAGE_CNT.getValue(), 
			        messageService.getUnReadCnt(userInfo.getId()));
			
			EncryptorUtil.clearAuthWhenLogined(request);
			
			mav.setViewName(LoginViewHelper.HTML_MENU_MENU);
		}
		mav.addObject("loginForm", loginForm);
		return mav;
	}

    
    @PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_STEP3)
    public ModelAndView registStep3(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws GeneralSecurityException  {
        
        if (request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue()) == null) {
            
            pageForm.setErrorMsg(messageSourceUtil.getContext("login.regist.noSessionError"));
            return ControllerHelper.getModelAndView(LoginViewHelper.getRegist1Page(pageForm));
        } else {
            RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
            
            if(registForm.getExpiredDt().isBefore(LocalDateTime.now())) {
                registForm.setErrorMsg(messageSourceUtil.getContext("login.regist.expiredError"));
                return ControllerHelper.getModelAndView(LoginViewHelper.getRegist1Page(registForm));
            } else if(!registForm.getOrgAuthCode().equals(pageForm.getAuthCode())) {
                registForm.setErrorMsg(messageSourceUtil.getContext("login.regist.authCodeError"));
                return ControllerHelper.getModelAndView(LoginViewHelper.getRegist2Page(registForm));
            } else {
                registForm.setErrorMsg("");
                request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), registForm);
                return ControllerHelper.getModelAndView(LoginViewHelper.getRegist3Page(registForm));
            }
        }
        
    }
    
    @PostMapping(path=LoginViewHelper.URL_C_USER_LOGIN_REFRESH_IMG)
    @ResponseBody
    public String refreshImg(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        return EncryptorUtil.generateAuthImgStr(request);
    }

    @GetMapping(path=LoginViewHelper.URL_C_LOGOUT)
	public ModelAndView logout(Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		clearSession(request);
		mav.setViewName("redirect:"+UrlConstants.INDEX);
		
		return mav;
	}
	
	private void setMobileDisp(@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request) {
		if(request.getSession().getAttribute(SessionConstants.IS_MOBILE.getValue()) == null) {
			if(allRequestParams.containsKey(SessionConstants.IS_MOBILE.getValue())) {
				request.getSession().setAttribute(SessionConstants.IS_MOBILE.getValue(), 
						allRequestParams.get(SessionConstants.IS_MOBILE.getValue()));
			} else {
				request.getSession().setAttribute(SessionConstants.IS_MOBILE.getValue(), "false");
			}
		} else {
			if(allRequestParams.containsKey(SessionConstants.IS_MOBILE.getValue())) {
				request.getSession().setAttribute(SessionConstants.IS_MOBILE.getValue(), 
						allRequestParams.get(SessionConstants.IS_MOBILE.getValue()));
			}
		}
	}
	
	private void clearSession(HttpServletRequest request) {
        for(SessionConstants field : SessionConstants.values()) {
            if(field.getLogoutClearFLg()) {
                request.getSession().setAttribute(field.getValue(), null);
            }
        }
    }
}
