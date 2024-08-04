package cn.caam.gs.app.admin.userreview.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.userreview.view.AdminUserReviewMultiViewHelper;
import cn.caam.gs.app.admin.userreview.view.AdminUserReviewViewHelper;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.domain.db.base.entity.MUserCheckHistory;
import cn.caam.gs.service.impl.UserCheckHistoryService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminUserReviewViewHelper.URL_BASE)
public class AdminUserReviewController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserCheckHistoryService userCheckHistoryService;
	
	@PostMapping(path=AdminUserReviewViewHelper.URL_C_INIT)
	public ModelAndView init(
			IdForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		if (StringUtil.isBlank(pageForm.getId())) {
			throw new Exception(messageSourceUtil.getContext("admin.userReview.review.noSelected"));
		}
//		request.getSession().setAttribute(SessionConstants.USER_REVIEW_SEARCH_FORM.getValue(), pageForm);
		return ControllerHelper.getModelAndView(
				AdminUserReviewViewHelper.getMainPage(request,  userService.getUserInfo(pageForm.getId()), 
						userCheckHistoryService.getUserCheckHistoryList(pageForm.getId())));
	}
	
	@PostMapping(path=AdminUserReviewViewHelper.URL_C_REVIEW)
	@ResponseBody
	public int review(
			MUserCheckHistory pageForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		if (StringUtil.isBlank(pageForm.getUserId())) {
			throw new Exception(messageSourceUtil.getContext("admin.userReview.review.noSelected"));
		}
		
		return userCheckHistoryService.review(pageForm, pageForm.getUserId()) ?  ExecuteReturnType.OK.getId():ExecuteReturnType.NG.getId();
	}
	
	@PostMapping(path=AdminUserReviewMultiViewHelper.URL_C_INIT)
	public ModelAndView initMulti(
			IdForm idForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		if (idForm.getUser_check_() == null || idForm.getUser_check_().length == 0) {
			throw new Exception(messageSourceUtil.getContext("admin.userReview.review.noSelected"));
		}
		return ControllerHelper.getModelAndView(
				AdminUserReviewMultiViewHelper.getMainPage(request,  idForm.getUser_check_()));
	}
	
	@PostMapping(path=AdminUserReviewMultiViewHelper.URL_C_REVIEW)
	@ResponseBody
	public int reviewMulti(
			IdForm idForm,
			MUserCheckHistory pageForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		if (idForm.getUser_check_() == null || idForm.getUser_check_().length == 0) {
			throw new Exception(messageSourceUtil.getContext("admin.userReview.review.noSelected"));
		}
		
		return userCheckHistoryService.reviewMulti(pageForm, idForm.getUser_check_()) ?  ExecuteReturnType.OK.getId():ExecuteReturnType.NG.getId();
	}
}
