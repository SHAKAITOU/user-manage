package cn.caam.gs.app.admin.usersearch.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.usersearch.view.AdminUserSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
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
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        AdminUserSearchViewHelper.getMainPage(request, new ArrayList<UserInfo>()));
	}
	
	@PostMapping(path=AdminUserSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    List<UserInfo> list = userService.getUserList(new MUser());

        return ControllerHelper.getModelAndView(
                AdminUserSearchViewHelper.refeshTable(request, list));
    }
}
