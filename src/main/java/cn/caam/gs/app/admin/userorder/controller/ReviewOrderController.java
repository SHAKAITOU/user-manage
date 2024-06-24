package cn.caam.gs.app.admin.userorder.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.userorder.view.ReviewOrderViewHelper;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.service.impl.OrderService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=ReviewOrderViewHelper.URL_BASE)
public class ReviewOrderController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
	
	@PostMapping(path=ReviewOrderViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        ReviewOrderViewHelper.getMainPage(request, orderService.getOrder(pageForm.getId())));
	}
}
