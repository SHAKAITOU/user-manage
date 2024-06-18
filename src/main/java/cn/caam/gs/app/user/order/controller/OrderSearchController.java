package cn.caam.gs.app.user.order.controller;


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
import cn.caam.gs.app.user.order.view.OrderSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.OrderService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=OrderSearchViewHelper.URL_BASE)
public class OrderSearchController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
	
	@GetMapping(path=OrderSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        OrderSearchViewHelper.getMainPage(request, new ArrayList<OrderInfo>()));
	}
	
	@PostMapping(path=OrderSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    List<OrderInfo> list = orderService.getOrderList(userInfo.getUser());

        return ControllerHelper.getModelAndView(
                OrderSearchViewHelper.refeshTable(request, list));
    }
}
