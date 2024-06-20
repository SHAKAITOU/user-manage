package cn.caam.gs.app.user.order.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.user.order.form.OrderForm;
import cn.caam.gs.app.user.order.view.OrderViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.OrderService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=OrderViewHelper.URL_BASE)
public class OrderController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
	
    @PostMapping(path=OrderViewHelper.URL_C_INIT)
	public ModelAndView init(
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        OrderViewHelper.getMainPage(request, new OrderInfo()));
	}
	
	@PostMapping(path=OrderViewHelper.URL_C_ADD)
    public ModelAndView add(
            OrderForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    pageForm.getOrder().setUserId(userInfo.getId());

	    return ControllerHelper.getModelAndView(
                OrderViewHelper.getMainPage(request, new OrderInfo()));
    }
}
