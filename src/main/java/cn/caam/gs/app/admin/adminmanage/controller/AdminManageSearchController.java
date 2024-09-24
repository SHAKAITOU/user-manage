package cn.caam.gs.app.admin.adminmanage.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.adminmanage.form.AdminManageSearchForm;
import cn.caam.gs.app.admin.adminmanage.output.AdminUserListOutput;
import cn.caam.gs.app.admin.adminmanage.view.AdminManageSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.entity.MSmsConfig;
import cn.caam.gs.domain.db.custom.mapper.OptionalSmsConfigMapper;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminManageSearchViewHelper.URL_BASE)
public class AdminManageSearchController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
	
	@GetMapping(path=AdminManageSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
			AdminManageSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
		 pageForm.setUserPageLinkIdPrefixIndex(0);
	     AdminUserListOutput userListOutput = userService.getAdminUserList(pageForm);
	     
		return ControllerHelper.getModelAndView(
		        AdminManageSearchViewHelper.getMainPage(request, pageForm, userListOutput));
	}
	
	@GetMapping(path=AdminManageSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            AdminManageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
        pageForm.setUserPageLinkIdPrefixIndex(0);
        AdminUserListOutput userListOutput = userService.getAdminUserList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminManageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@GetMapping(path=AdminManageSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            AdminManageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {

	    pageForm.setOffset(pageForm.getLimit()*pageForm.getUserPageLinkIdPrefixIndex());
	    AdminUserListOutput userListOutput = userService.getAdminUserList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminManageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
}
