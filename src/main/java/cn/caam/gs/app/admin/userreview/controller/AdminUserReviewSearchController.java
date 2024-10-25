package cn.caam.gs.app.admin.userreview.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.userreview.view.AdminUserReviewSearchViewHelper;
import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminUserReviewSearchViewHelper.URL_BASE)
public class AdminUserReviewSearchController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
	
	@GetMapping(path=AdminUserReviewSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
			UserSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtil.isBlank(pageForm.getSearchMode())) {
			pageForm.setSearchMode(AdminUserReviewSearchViewHelper.SEARCH_MODE_WAIT_LIST);
		}
		 pageForm.setUserPageLinkIdPrefixIndex(0);
	     UserListOutput userListOutput = userService.getUserList(request, pageForm);

		return ControllerHelper.getModelAndView(
		        AdminUserReviewSearchViewHelper.getMainPage(request, pageForm, userListOutput));
	}
	
	@GetMapping(path=AdminUserReviewSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
		
		if (StringUtil.isBlank(pageForm.getSearchMode())) {
			pageForm.setSearchMode(AdminUserReviewSearchViewHelper.SEARCH_MODE_WAIT_LIST);
		}
	    
        pageForm.setUserPageLinkIdPrefixIndex(0);
        UserListOutput userListOutput = userService.getUserList(request, pageForm);
        return ControllerHelper.getModelAndView(
                AdminUserReviewSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@GetMapping(path=AdminUserReviewSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {

	    pageForm.setOffset(pageForm.getLimit()*pageForm.getUserPageLinkIdPrefixIndex());
	    UserListOutput userListOutput = userService.getUserList(request, pageForm);
        return ControllerHelper.getModelAndView(
                AdminUserReviewSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
}
