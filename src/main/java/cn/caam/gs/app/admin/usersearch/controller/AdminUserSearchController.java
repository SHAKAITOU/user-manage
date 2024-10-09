package cn.caam.gs.app.admin.usersearch.controller;


import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.admin.usersearch.view.AdminUserSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminUserSearchViewHelper.URL_BASE)
public class AdminUserSearchController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
	
	@GetMapping(path=AdminUserSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
	        UserSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
		if (Objects.isNull(pageForm)) {
			pageForm = new UserSearchForm();
			pageForm.setUser(new MUser());
		}
		pageForm.setOffset(pageForm.getLimit()*pageForm.getUserPageLinkIdPrefixIndex());
	    UserListOutput userListOutput = userService.getUserList(pageForm);

		return ControllerHelper.getModelAndView(
		        AdminUserSearchViewHelper.getMainPage(request, pageForm, userListOutput));
	}
	
	@GetMapping(path=AdminUserSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    if (!Strings.isBlank(pageForm.getSelectedUserId())) {
	    	UserSearchForm userSearchForm = (UserSearchForm)request.getSession().getAttribute(SessionConstants.USER_SEARCH_FORM.getValue());
	    	if (userSearchForm != null) pageForm = userSearchForm;
	    }
	    pageForm.setUserPageLinkIdPrefixIndex(0);
	    request.getSession().setAttribute(SessionConstants.USER_SEARCH_FORM.getValue(), null);
	    
        UserListOutput userListOutput = userService.getUserList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminUserSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@GetMapping(path=AdminUserSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {

	    pageForm.setOffset(pageForm.getLimit()*pageForm.getUserPageLinkIdPrefixIndex());
	    UserListOutput userListOutput = userService.getUserList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminUserSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@GetMapping(path=AdminUserSearchViewHelper.URL_C_EXPORT)
    public void export(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	    pageForm.setUserPageLinkIdPrefixIndex(0);
	    pageForm.setLimit(99999999);
	    request.getSession().setAttribute(SessionConstants.USER_SEARCH_FORM.getValue(), null);
	    
        userService.exportUserInfo(response, pageForm);
    }
}
