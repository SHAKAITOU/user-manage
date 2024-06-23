package cn.caam.gs.app.user.login.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import cn.caam.gs.app.JavaScriptSet;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.user.login.form.IndexForm;
import cn.caam.gs.app.user.login.form.LoginForm;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.user.regist.view.RegistStep1ViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.MailSendResultType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.custom.entity.LoginResult;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.FixedValueService;
import cn.caam.gs.service.impl.MessageService;
import cn.caam.gs.service.impl.UserService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=LoginViewHelper.URL_BASE)
public class LoginController extends ScreenBaseController{
	
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

	@GetMapping("")
	public ModelAndView index(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response)  {

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
		LoginForm loginForm = new LoginForm();
		loginForm.setUserCode("M24060914330223");
		loginForm.setPassword("1");
		loginForm.setAuthImg("data:image/;base64," + EncryptorUtil.generateAuthImgStr(request));
		if(!StringUtils.isEmpty(indexForm.getLoginFormJson())) {
			loginForm = JsonUtility.toObject(indexForm.getLoginFormJson(), LoginForm.class);
		}
		mav.addObject("loginForm", loginForm);
		mav.setViewName(LoginViewHelper.HTML_LOGIN);
		return mav;
	}
	

	@PostMapping(path=LoginViewHelper.URL_C_USER_LOGIN)
	public ModelAndView menu(LoginForm loginForm, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean okFlag = false;
		boolean noSession = false;
		
		UserInfo userInfo = userService.getLoginUserInfo(loginForm.getUserCode());
		
		String ePw = EncryptorUtil.encrypt(loginForm.getPassword());
		if (userInfo == null) {
		    loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notexist"));
		    okFlag = false;
		} else if(!userInfo.getUser().getPassword().equals(ePw)) {
		    loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.wrongPw"));
		    okFlag = false;
		} else {
			okFlag = true;
		}
		
		ModelAndView mav = new ModelAndView();
		if(noSession) {
			mav = new ModelAndView("redirect:"+LoginViewHelper.URL_BASE);
		}
		else if(!okFlag) {
			loginForm.setReturnType(ExecuteReturnType.NG.getId());
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), null);
			IndexForm indexForm = new IndexForm();
			indexForm.setLoginFormJson(JsonUtility.toJson(loginForm));
			mav.addObject("indexForm", indexForm);
			mav.setViewName(LoginViewHelper.HTML_INDEX);
		} else {
			loginForm.setReturnType(ExecuteReturnType.OK.getId());
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), userInfo);
			JavaScriptSet javaScriptSetInfo = new JavaScriptSet(request, environment, messageSourceUtil);
			request.getSession().setAttribute(SessionConstants.JAVASCRIPT_SET_INFO.getValue(), 
					javaScriptSetInfo.loginAdminSet());
			request.getSession().setAttribute(SessionConstants.FIXED_VALUE.getValue(), 
			        fixedValueService.getMap());
			
			request.getSession().setAttribute(SessionConstants.UN_READ_MESSAGE_CNT.getValue(), 
			        messageService.getUnReadCnt(userInfo.getId()));
			
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

    @GetMapping(path=LoginViewHelper.URL_C_LOGOUT)
	public ModelAndView logout(Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+UrlConstants.USER+"?lang="+loc.toLanguageTag());
		
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
}
