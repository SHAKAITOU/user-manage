package cn.caam.gs.app.admin.adminmanage.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.adminmanage.form.AdminUserDetailForm;
import cn.caam.gs.app.admin.adminmanage.view.AdminManageEditViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.util.PasswordGenerator;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.custom.entity.AdminUserInfo;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminManageEditViewHelper.URL_BASE)
public class AdminManageEditController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
    
	@PostMapping(path=AdminManageEditViewHelper.URL_C_INIT_LOGIN_ADMIN)
	public ModelAndView initLoginAdmin(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		MAdmin mAdmin = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    
	    AdminUserDetailForm userDetailForm = new AdminUserDetailForm();
	    userDetailForm.setId(mAdmin.getId());
	    userDetailForm.setUserInfo(userService.getAdminUserInfo(mAdmin.getId()));
	    
        return ControllerHelper.getModelAndView(AdminManageEditViewHelper.getMainPage(request, userDetailForm, AdminManageEditViewHelper.MODE_LOGINING_EDIT));
	}
	
	@PostMapping(path=AdminManageEditViewHelper.URL_C_INIT)
	public ModelAndView init(
			AdminUserDetailForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		AdminUserDetailForm userDetailForm = null;
		int mode = AdminManageEditViewHelper.MODE_EDIT;
		if (Strings.isBlank(pageForm.getId())) {
			mode = AdminManageEditViewHelper.MODE_ADD;
			userDetailForm = new AdminUserDetailForm();
			String initPwd = PasswordGenerator.generatePassword();
			userDetailForm.setUserInfo(new AdminUserInfo());
			userDetailForm.getUserInfo().setPassword(initPwd);
			userDetailForm.getUserInfo().setPasswordRep(initPwd);
			userDetailForm.getUserInfo().setUser(new MAdmin());
		}else {
		    userDetailForm = new AdminUserDetailForm();
		    userDetailForm.setId(pageForm.getId());
		    userDetailForm.setUserInfo(userService.getAdminUserInfo(pageForm.getId()));
		}
	    
        return ControllerHelper.getModelAndView(AdminManageEditViewHelper.getMainPage(request, userDetailForm, mode));
	}
	
	@PostMapping(path=AdminManageEditViewHelper.URL_C_EDIT)
	@ResponseBody
	public int edit(
			AdminUserDetailForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		if (!Strings.isBlank(pageForm.getId())) {
			userService.updateAdminUserInfo(pageForm);
			return ExecuteReturnType.OK.getId();
		}else {
			userService.insertAdminUserInfo(request, pageForm);
			return ExecuteReturnType.OK.getId();
		}
	}
	
}
