package cn.caam.gs.app.user.order.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.user.order.view.OrderSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.OrderService;
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
	        OrderSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
	    
	    OrderListOutput orderListOutput = new OrderListOutput();

		return ControllerHelper.getModelAndView(
		        OrderSearchViewHelper.getMainPage(request, pageForm, orderListOutput));
	}
	
	@PostMapping(path=OrderSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    pageForm.getOrder().setUserId(userInfo.getId());
	    pageForm.setOrderPageLinkIdPrefixIndex(0);
	    pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
	    OrderListOutput orderListOutput = orderService.getOrderList(pageForm);

        return ControllerHelper.getModelAndView(
                OrderSearchViewHelper.refeshTable(request, pageForm, orderListOutput));
    }
	
	@PostMapping(path=OrderSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
        pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
        pageForm.getOrder().setUserId(userInfo.getId());
        OrderListOutput orderListOutput = orderService.getOrderList(pageForm);

        return ControllerHelper.getModelAndView(
                OrderSearchViewHelper.refeshTable(request, pageForm, orderListOutput));
    }
}
