package cn.caam.gs.app.user.regist.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.user.regist.view.RegistStep2ViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.service.impl.AuthCodeService;
import cn.caam.gs.service.impl.FixedValueService;
import cn.caam.gs.service.impl.UserService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=LoginViewHelper.URL_BASE)
public class RegistStep2Controller extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	AuthCodeService authCodeService;
	
    @Autowired
    FixedValueService fixedValueService;
    
    @Autowired
	UserService userService;
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_STEP2)
    public ModelAndView registStep2Init(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
	    return ControllerHelper.getModelAndView(RegistStep2ViewHelper.getRegist2Page(request, registForm));
    }
	
	@PostMapping(path=LoginViewHelper.URL_C_USER_REGIST_STEP_FINAL)
	@ResponseBody
    public int registStepFinal(
            RegistForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	    RegistForm registForm = (RegistForm)request.getSession().getAttribute(SessionConstants.USER_REGIST.getValue());
	    
	    if (userService.isPhoneNumberExist(registForm.getUser().getPhone())) {
	    	return ExecuteReturnType.NG.getId();
	    }
	    
//	    if (userService.isEmailExist(registForm.getUser().getMail())) {
//	    	return ExecuteReturnType.NG.getId();
//	    }
	    
	    pageForm.getUser().setPhone(registForm.getUser().getPhone());
	    pageForm.getUser().setMail(registForm.getUser().getMail());
	    userService.insertUserInfo(pageForm);
	    return ExecuteReturnType.OK.getId();
    }
	

}
