package cn.caam.gs.app.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.common.view.UserCertiCommonViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
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
@RequestMapping(path=UserCertiCommonViewHelper.URL_BASE)
public class UserCertiCommonController extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserService userService;

	@GetMapping(path=UserCertiCommonViewHelper.URL_USER_CERTI_INIT)
    public ModelAndView init(
    		IdForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
		String id = pageForm.getId();
		if (LoginInfoHelper.isUserLogin(request)) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
			id = userInfo.getUser().getId();
		}
	    
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(id);
	    userDetailForm.setUserInfo(userService.getUserInfo(id));
	    
        return ControllerHelper.getModelAndView(UserCertiCommonViewHelper.getMainPage(request, userDetailForm));

    }
	
//	@GetMapping(path=UserCertiCommonViewHelper.URL_USER_CERTI_DOWNLOAD)
//	public void downLoadList(
//			IdForm pageForm,
//            HttpServletRequest request,
//            HttpServletResponse response) throws Exception{
//		String id = pageForm.getId();
//		if (LoginInfoHelper.isUserLogin(request)) {
//			UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
//			id = userInfo.getUser().getId();
//		}
//	    userService.downloadFile(request, id, DownloadFileType.USER_CERTIFICATE, response);
//	}
}
