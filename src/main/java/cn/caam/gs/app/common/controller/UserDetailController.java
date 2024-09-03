package cn.caam.gs.app.common.controller;


import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.common.view.UserDetailViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.LoginAccountType;
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
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_FROM_ADMIN_INIT)
    public ModelAndView adminInit(
    		UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
		request.getSession().setAttribute(SessionConstants.USER_SEARCH_FORM.getValue(), pageForm);
	    UserInfo userInfo = userService.getUserInfo(pageForm.getSelectedUserId());
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userInfo);
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm));

    }
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_EDIT)
    public ModelAndView edit(
    		@ModelAttribute UserDetailForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
		UserInfo userInfo = null;
		if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
	    	userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		}else {
			userInfo = userService.getUserInfo(pageForm.getUserInfo().getUser().getId());
		}
	    userService.updateUserInfo(pageForm);
	    
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userService.getUserInfo(userInfo.getUser().getId()));
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm));

    }
	
	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_PRINT)
    public void print(
    		@ModelAttribute UserDetailForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
		UserInfo userInfo = null;
		if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
	    	userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    	
	    	response.setHeader(HttpHeaders.PRAGMA, "No-cache");
		    response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
		    String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".docx","UTF-8");
		    response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
		    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8");
		    userService.outputAppliactionForm(userInfo.getUser().getId(), response.getOutputStream());
		}
    }
	
	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_DOWNLOAD+"/{id}")
    public void download(
    		@PathVariable("id") String id,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
    	response.setHeader(HttpHeaders.PRAGMA, "No-cache");
	    response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
	    String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".pdf","UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
	    response.setContentType("application/pdf;charset=UTF-8");
	    userService.downloadAppliactionForm(id, response.getOutputStream());
    }
}
