package cn.caam.gs.app.admin.usersearch.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.admin.usersearch.view.AdminUserSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
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
	    
	    UserListOutput userListOutput = new UserListOutput();
	    request.getSession().setAttribute(SessionConstants.USER_LIST_OUT_PUT.getValue(), userListOutput);

		return ControllerHelper.getModelAndView(
		        AdminUserSearchViewHelper.getMainPage(request, pageForm, userListOutput));
	}
	
	@PostMapping(path=AdminUserSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
        pageForm.setOffset(0);
        UserListOutput userListOutput = userService.getUserList(pageForm);
        pageForm.setOffset(userListOutput.getUserList().size());
        request.getSession().setAttribute(SessionConstants.USER_LIST_OUT_PUT.getValue(), userListOutput);
        return ControllerHelper.getModelAndView(
                AdminUserSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@PostMapping(path=AdminUserSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    UserListOutput userListOutput = 
                (UserListOutput)request.getSession().getAttribute(SessionConstants.USER_LIST_OUT_PUT.getValue());
	    UserListOutput growing = userService.getUserList(pageForm);
	    userListOutput.setCount(growing.getCount());
	    userListOutput.getUserList().addAll(growing.getUserList());
        pageForm.setOffset(userListOutput.getUserList().size());
        request.getSession().setAttribute(SessionConstants.ORDER_LIST_OUT_PUT.getValue(), userListOutput);
        return ControllerHelper.getModelAndView(
                AdminUserSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
}