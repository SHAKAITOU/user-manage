package cn.caam.gs.app.admin.userreview.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.userreview.view.AdminUserReviewViewHelper;
import cn.caam.gs.app.common.form.IdForm;
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
@RequestMapping(path=AdminUserReviewViewHelper.URL_BASE)
public class AdminUserReviewController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
	
	@PostMapping(path=AdminUserReviewViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println(pageForm.toString());
		return ControllerHelper.getModelAndView(
				AdminUserReviewViewHelper.getMainPage(request, userService.getUserInfo(pageForm.getId())));
	}
}
