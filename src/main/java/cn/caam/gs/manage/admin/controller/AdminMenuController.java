package cn.caam.gs.manage.admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.custom.entity.LoginResult;
import cn.caam.gs.manage.admin.view.menu.AdminMenuViewHelper;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminMenuViewHelper.URL_BASE)
public class AdminMenuController extends JcbcBaseController{
	
	@GetMapping(path=AdminMenuViewHelper.URL_C_INIT)
	public ModelAndView initMenu(
			HttpServletRequest request,
			HttpServletResponse response)  {
		ModelAndView mav = new ModelAndView();
		LoginResult loginResult = LoginInfoHelper.getLoginResult(request);
		request.getSession().setAttribute(SessionConstants.LOGIN_INFO.getValue(), loginResult);
		mav.setViewName(AdminMenuViewHelper.HTML_MENU_MENU);
		return mav;
	}
}
