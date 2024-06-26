package cn.caam.gs.app.admin.login.controller;


import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.JavaScriptSet;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.admin.login.view.AdminLoginViewHelper;
import cn.caam.gs.app.admin.menu.menu.AdminMenuViewHelper;
import cn.caam.gs.app.user.login.form.IndexForm;
import cn.caam.gs.app.user.login.form.LoginForm;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.service.impl.FixedValueService;
import cn.caam.gs.service.impl.OrderService;
import cn.caam.gs.service.impl.UserService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=AdminLoginViewHelper.URL_BASE)
public class AdminLoginController extends ScreenBaseController{
    
    private static final String PARAM_INVALID_LOGIN = "inVlidLogin"; 
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
    
	@Autowired
    UserService userService;
	
	@Autowired
	FixedValueService fixedValueService;
	
    @Autowired
    OrderService orderService;
    
    

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
			request.getSession().setAttribute(SessionConstants.JAVASCRIPT_SET_INFO.getValue(), javaScriptSetInfo.initAdmin());
			
			indexForm.setEnvMode(environment.getProperty("spring.datasource.mode"));
			
			setThemes(allRequestParams, request);
			
			setMobileDisp(allRequestParams, request);
			
			mav.addObject("indexForm", indexForm);
			mav.setViewName(AdminLoginViewHelper.HTML_INDEX);
		//}else {
		//	mav.setViewName(MenuViewHelper.HTML_MENU_MENU);
		//}
		return mav;
	}
	
	
	@PostMapping(path=AdminLoginViewHelper.URL_C_LOGIN_INIT)
	public ModelAndView initLogin(IndexForm indexForm,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().setAttribute(SessionConstants.MEDIA_HEIGHT.getValue(), indexForm.getMediaHeight());
		request.getSession().setAttribute(SessionConstants.MEDIA_WIDTH.getValue(), indexForm.getMediaWidth());
		request.getSession().setAttribute(SessionConstants.IS_MOBILE.getValue(), indexForm.getMobileDisplay());
		ModelAndView mav = new ModelAndView();
		LoginForm loginForm = new LoginForm();
		loginForm.setUserCode("A24060914330223");
		loginForm.setPassword("1");
		loginForm.setAuthImg(EncryptorUtil.generateAuthImgStr(request));
		if(!StringUtils.isEmpty(indexForm.getLoginFormJson())) {
			loginForm = JsonUtility.toObject(indexForm.getLoginFormJson(), LoginForm.class);
		}
		mav.addObject("loginForm", loginForm);
		mav.setViewName(AdminLoginViewHelper.HTML_LOGIN);
		return mav;
	}
	

	@PostMapping(path=AdminLoginViewHelper.URL_C_USER_LOGIN)
	public ModelAndView menu(
	        LoginForm loginForm, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean okFlag = false;
		boolean noSession = false;
		MAdmin userInfo = userService.getLoginAdminInfo(loginForm.getUserCode());
		
		String ePw = EncryptorUtil.encrypt(loginForm.getPassword());
		
		if (request.getSession().getAttribute(SessionConstants.AUTH_CODE.getValue()) == null) {
		    okFlag = false;
		    loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notRightLogin"));
		} else if (userInfo == null) {
            loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.notexist"));
            okFlag = false;
        } else if(!userInfo.getPassword().equals(ePw)) {
            loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.wrongPw"));
            okFlag = false;
        } else {
            okFlag = true;
        }
		
		// TODO auth check
        String authCode = (String)request.getSession().getAttribute(SessionConstants.AUTH_CODE.getValue());
		
		ModelAndView mav = new ModelAndView();
		if(noSession) {
			mav = new ModelAndView("redirect:"+AdminLoginViewHelper.URL_BASE);
		}
		else if(!okFlag) {
		    clearSession(request);
			loginForm.setReturnType(ExecuteReturnType.NG.getId());
			IndexForm indexForm = new IndexForm();
			indexForm.setLoginFormJson(JsonUtility.toJson(loginForm));
			mav.addObject("indexForm", indexForm);
			request.getSession().setAttribute(SessionConstants.LOGIN_ERROR_MSG.getValue(), 
			        loginForm.getErrorMsg());
			mav = new ModelAndView("redirect:"+AdminLoginViewHelper.URL_BASE + "?"+PARAM_INVALID_LOGIN+"=true");
		} else {
		    request.getSession().setAttribute(SessionConstants.LOGIN_ERROR_MSG.getValue(), null);
			loginForm.setReturnType(ExecuteReturnType.OK.getId());
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), userInfo);
			JavaScriptSet javaScriptSetInfo = new JavaScriptSet(request, environment, messageSourceUtil);
			request.getSession().setAttribute(SessionConstants.JAVASCRIPT_SET_INFO.getValue(), 
					javaScriptSetInfo.loginAdminSet());
			request.getSession().setAttribute(SessionConstants.FIXED_VALUE.getValue(), 
			        fixedValueService.getMap());
			
			int orderWaitCnt = orderService.getOrderWaitCount();
	        request.getSession().setAttribute(SessionConstants.ORDER_WAIT_CNT.getValue(), orderWaitCnt);
	        
	        int orderReviewCnt = orderService.getOrderReviewCount();
	        request.getSession().setAttribute(SessionConstants.ORDER_REVIEW_CNT.getValue(), orderReviewCnt);
	        
	        request.getSession().setAttribute(SessionConstants.ORDER_NOT_FINISH_CNT.getValue(), 
	                orderWaitCnt + orderReviewCnt);
	        
	        EncryptorUtil.clearAuthWhenLogined(request);
			
			mav.setViewName(AdminMenuViewHelper.HTML_MENU_MENU);
		}
		mav.addObject("loginForm", loginForm);
		return mav;
	}
	
	@PostMapping(path=AdminLoginViewHelper.URL_C_USER_LOGIN_REFRESH_IMG)
	@ResponseBody
    public String refreshImg(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
	    
	    return EncryptorUtil.generateAuthImgStr(request);
	}

	@GetMapping(path=AdminLoginViewHelper.URL_C_LOGOUT)
	public ModelAndView logout(Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		clearSession(request);
		mav.setViewName("redirect:"+UrlConstants.ADMIN);
		
		return mav;
	}
	
	
	
	private void setThemes(@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request) {
		if(request.getSession().getAttribute(SessionConstants.THEMES_TYPE.getValue()) == null) {
			if(allRequestParams.containsKey(SessionConstants.THEMES_TYPE.getValue())) {
				request.getSession().setAttribute(SessionConstants.THEMES_TYPE.getValue(), 
						allRequestParams.get(SessionConstants.THEMES_TYPE.getValue()));
			} else {
				request.getSession().setAttribute(SessionConstants.THEMES_TYPE.getValue(), "white");
			}
		} else {
			if(allRequestParams.containsKey(SessionConstants.THEMES_TYPE.getValue())) {
				request.getSession().setAttribute(SessionConstants.THEMES_TYPE.getValue(), 
						allRequestParams.get(SessionConstants.THEMES_TYPE.getValue()));
			}
		}
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
