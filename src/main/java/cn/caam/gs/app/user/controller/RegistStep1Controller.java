package cn.caam.gs.app.user.controller;


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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import cn.caam.gs.JavaScriptSet;
import cn.caam.gs.app.form.IndexForm;
import cn.caam.gs.app.form.LoginForm;
import cn.caam.gs.app.form.RegistForm;
import cn.caam.gs.app.user.view.LoginViewHelper;
import cn.caam.gs.app.user.view.RegistStep1ViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.app.util.UrlConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.MailSendResultType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.custom.entity.LoginResult;
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
	
	private static final String FROMREDIRECT = "fromRedirect";
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST)
    public ModelAndView registInit(
            HttpServletRequest request,
            HttpServletResponse response)  {
	    RegistForm pageForm = new RegistForm();
	    pageForm.setErrorMsg("");
        if (request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue()) != null) {
            pageForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
        } else {
            request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), pageForm);
        }

        return ControllerHelper.getModelAndView(RegistStep1ViewHelper.getRegist1Page(pageForm));
    }
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_COMMIT)
    public ModelAndView registCommit(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
	    
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
	    if (StringUtils.isEmpty(request.getParameter(FROMREDIRECT))) {
	        //入力エラーじゃない場合
	        MAuthCode mauthCode = authCodeService.addAuthCode(pageForm);
	        registForm.setMauthCode(mauthCode);
	        request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), registForm);
	    }

	    return ControllerHelper.getModelAndView(RegistStep1ViewHelper.getRegist1ConfirmPage(registForm));
    }
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_COMMIT_CONFIRM)
    public ModelAndView registConfirm(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
        
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
        if (!pageForm.getAuthCode().equals(registForm.getMauthCode().getAuthCode())) {
            registForm.setErrorMsg(getContext("login.regist.step1.confirm.wrongAuthCode"));
            ModelAndView mav = new ModelAndView();
            request.setAttribute(
                    View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
            mav.addObject(FROMREDIRECT, true);
            mav.setViewName("redirect:"+LoginViewHelper.URL_BASE + LoginViewHelper.URL_C_USER_REGIST_COMMIT);
            
            return mav;
        }

        request.getSession().setAttribute(SessionConstants.USER_REGIST.getValue(), registForm);
        return ControllerHelper.getModelAndView(RegistStep1ViewHelper.getRegist1ConfirmPage(registForm));
    }
}
