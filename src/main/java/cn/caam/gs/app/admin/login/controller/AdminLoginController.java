package cn.caam.gs.app.admin.login.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

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
import cn.caam.gs.domain.db.custom.entity.LoginResult;
import cn.caam.gs.service.impl.FixedValueService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=AdminLoginViewHelper.URL_BASE)
public class AdminLoginController extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	FixedValueService fixedValueService;

	@GetMapping("")
	public ModelAndView index(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response)  {

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
		loginForm.setUserCode("admin");
		loginForm.setPassword("1");
		loginForm.setAuthImg("data:image/;base64," + getAuthImgStr(request));
		if(!StringUtils.isEmpty(indexForm.getLoginFormJson())) {
			loginForm = JsonUtility.toObject(indexForm.getLoginFormJson(), LoginForm.class);
		}
		mav.addObject("loginForm", loginForm);
		mav.setViewName(AdminLoginViewHelper.HTML_LOGIN);
		return mav;
	}
	

	@PostMapping(path=AdminLoginViewHelper.URL_C_USER_LOGIN)
	public ModelAndView menu(LoginForm loginForm, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean okFlag = false;
		boolean noSession = false;
		String ePw = EncryptorUtil.encrypt(loginForm.getPassword());
		//if(!ePw.equals("VD2cOmWS0ly0K2NWXLszLQ==")) {
		//	loginForm.setErrorMsg(messageSourceUtil.getContext("login.fail.msg.wrongPw"));
		//} else if(Objects.isNull(request.getSession().getAttribute(SessionConstants.THEMES_TYPE.getValue()))) {
		//	okFlag = false;
		//	noSession = true;
		//}
		//else {
			okFlag = true;
		//}
		
		ModelAndView mav = new ModelAndView();
		if(noSession) {
			mav = new ModelAndView("redirect:"+AdminLoginViewHelper.URL_BASE);
		}
		else if(!okFlag) {
			loginForm.setReturnType(ExecuteReturnType.NG.getId());
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), null);
			IndexForm indexForm = new IndexForm();
			indexForm.setLoginFormJson(JsonUtility.toJson(loginForm));
			mav.addObject("indexForm", indexForm);
			mav.setViewName(AdminLoginViewHelper.HTML_INDEX);
		} else {
			loginForm.setReturnType(ExecuteReturnType.OK.getId());
			LoginResult loginResult = new LoginResult();
			request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), 
					JsonUtility.toJson(loginResult));
			JavaScriptSet javaScriptSetInfo = new JavaScriptSet(request, environment, messageSourceUtil);
			request.getSession().setAttribute(SessionConstants.JAVASCRIPT_SET_INFO.getValue(), 
					javaScriptSetInfo.loginAdminSet());
			request.getSession().setAttribute(SessionConstants.FIXED_VALUE.getValue(), 
			        fixedValueService.getMap());
			
			mav.setViewName(AdminMenuViewHelper.HTML_MENU_MENU);
		}
		mav.addObject("loginForm", loginForm);
		return mav;
	}

	@RequestMapping(path=AdminLoginViewHelper.URL_C_LOGOUT, method=RequestMethod.GET)
	public ModelAndView logout(Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+UrlConstants.ADMIN+"?lang="+loc.toLanguageTag());
		
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
	
	private String getAuthImgStr(HttpServletRequest request) throws IOException {
        
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        String text = defaultKaptcha.createText();
        HttpSession session = request.getSession();
        session.setAttribute("verify_code", text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        ImageIO.write(image, "jpg", baos);
        return Base64.encodeBase64String(baos.toByteArray());
    }
}
