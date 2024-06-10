package cn.caam.gs.manage.admin.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.custom.entity.LoginResult;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.manage.admin.view.menu.AdminMenuViewHelper;
import cn.caam.gs.manage.admin.view.user.AdminUserSearchViewHelper;
import cn.caam.gs.manage.dbmaintenance.view.DbMaintenanceViewHelper;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminUserSearchViewHelper.URL_BASE)
public class AdminUserSearchController extends JcbcBaseController{
	
	@GetMapping(path=AdminUserSearchViewHelper.URL_C_INIT)
	public ModelAndView initMenu(
			HttpServletRequest request,
			HttpServletResponse response)  {

		return ControllerHelper.getModelAndView(
		        AdminUserSearchViewHelper.getMainPage(request, new ArrayList<UserInfo>()));
	}
}
