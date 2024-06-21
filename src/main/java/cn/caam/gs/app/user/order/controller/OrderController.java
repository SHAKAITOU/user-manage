package cn.caam.gs.app.user.order.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.user.order.form.OrderForm;
import cn.caam.gs.app.user.order.view.OrderViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
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
	@ResponseBody
    public int add(
            OrderForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	    
	    orderService.addOrder(pageForm);

	    return ExecuteReturnType.OK.getId();
    }
}
