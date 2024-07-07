package cn.caam.gs.app.user.detail.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.user.detail.form.UserDetailForm;
import cn.caam.gs.app.user.detail.view.UserDetailViewHelper;
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
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(path=UserDetailViewHelper.URL_BASE)
public class UserDetailController extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserService userService;

	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_INIT)
    public ModelAndView init(
            HttpServletRequest request,
            HttpServletResponse response)  {
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userService.getUserInfo(userInfo.getUser().getId()));
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm));

    }
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_EDIT)
    public ModelAndView edit(
    		@ModelAttribute UserDetailForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    
	    userService.updateUserInfo(pageForm);
	    
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userService.getUserInfo(userInfo.getUser().getId()));
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm));

    }
}
