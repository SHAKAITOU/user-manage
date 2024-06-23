package cn.caam.gs.app.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.view.OrderDetailViewHelper;
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
@RequestMapping(path=OrderDetailViewHelper.URL_BASE)
public class OrderDetailController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
	
    @PostMapping(path=OrderDetailViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm idForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        OrderDetailViewHelper.getMainPage(request, orderService.getOrder(idForm.getId())));
	}
}
