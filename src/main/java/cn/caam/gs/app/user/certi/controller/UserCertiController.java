package cn.caam.gs.app.user.certi.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.user.certi.view.UserCertiViewHelper;
import cn.caam.gs.app.user.detail.form.UserDetailForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Deprecated
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(path=UserCertiViewHelper.URL_BASE)
public class UserCertiController extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserService userService;

	@GetMapping(path=UserCertiViewHelper.URL_USER_CERTI_INIT)
    public ModelAndView init(
            HttpServletRequest request,
            HttpServletResponse response)  {
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userService.getUserInfo(userInfo.getUser().getId()));
	    
        return ControllerHelper.getModelAndView(UserCertiViewHelper.getMainPage(request, userDetailForm));

    }
}
