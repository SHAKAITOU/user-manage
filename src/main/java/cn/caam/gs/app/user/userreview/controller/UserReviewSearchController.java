package cn.caam.gs.app.user.userreview.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.output.UserCheckHistoryListOutput;
import cn.caam.gs.app.user.userreview.form.UserReviewSearchForm;
import cn.caam.gs.app.user.userreview.view.UserReviewSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.UserCheckHistoryService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=UserReviewSearchViewHelper.URL_BASE)
public class UserReviewSearchController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserCheckHistoryService userCheckHistoryService;
	
	@GetMapping(path=UserReviewSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
			UserReviewSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		 UserInfo sessionUserInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		 UserCheckHistoryListOutput userCheckHistoryListOutput = userCheckHistoryService.getUserCheckHistoryList(sessionUserInfo.getUser().getId());
		 pageForm.setUserPageLinkIdPrefixIndex(0);
		 UserInfo userInfo = userService.getUserInfo(sessionUserInfo.getUser().getId());

		return ControllerHelper.getModelAndView(
		        UserReviewSearchViewHelper.getMainPage(request, userInfo, userCheckHistoryListOutput));
	}
	
//	@PostMapping(path=UserReviewSearchViewHelper.URL_C_SEARCH)
//    public ModelAndView search(
//            UserSearchForm pageForm,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//		if (StringUtil.isBlank(pageForm.getSearchMode())) {
//			pageForm.setSearchMode(AdminUserReviewSearchViewHelper.SEARCH_MODE_WAIT_LIST);
//		}
//	    
//        pageForm.setUserPageLinkIdPrefixIndex(0);
//        UserListOutput userListOutput = userService.getUserList(pageForm);
//        return ControllerHelper.getModelAndView(
//                AdminUserReviewSearchViewHelper.refeshTable(request, pageForm, userListOutput));
//    }
//	
//	@PostMapping(path=UserReviewSearchViewHelper.URL_C_GROWING)
//    public ModelAndView growing(
//            UserSearchForm pageForm,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//
//	    pageForm.setOffset(pageForm.getLimit()*pageForm.getUserPageLinkIdPrefixIndex());
//	    UserListOutput userListOutput = userService.getUserList(pageForm);
//        return ControllerHelper.getModelAndView(
//                AdminUserReviewSearchViewHelper.refeshTable(request, pageForm, userListOutput));
//    }
}
