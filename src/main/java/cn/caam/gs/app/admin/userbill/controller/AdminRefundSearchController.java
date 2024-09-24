package cn.caam.gs.app.admin.userbill.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.admin.userbill.form.RefundSearchForm;
import cn.caam.gs.app.admin.userbill.view.AdminRefundSearchViewHelper;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.service.impl.OrderService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminRefundSearchViewHelper.URL_BASE)
public class AdminRefundSearchController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
	
	@GetMapping(path=AdminRefundSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
	        RefundSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
	    
	    MOrder order = new MOrder();
        order.setCheckStatus(GlobalConstants.DFL_SELECT_ALL);
        pageForm.setOrder(order);
	    OrderListOutput listOutput = new OrderListOutput();

		return ControllerHelper.getModelAndView(
		        AdminRefundSearchViewHelper.getMainPage(request, pageForm, listOutput));
	}
	
	@GetMapping(path=AdminRefundSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            RefundSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    pageForm.setBillPageLinkIdPrefixIndex(0);
        pageForm.setOffset(pageForm.getLimit()*pageForm.getBillPageLinkIdPrefixIndex());
        pageForm.getOrder().setCheckStatus(CheckStatusType.REFUSED.getKey());
        OrderListOutput listOutput = orderService.getRefundList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminRefundSearchViewHelper.refeshTable(request, pageForm, listOutput));
    }
	
	@GetMapping(path=AdminRefundSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            RefundSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    pageForm.setOffset(pageForm.getLimit()*pageForm.getBillPageLinkIdPrefixIndex());
	    OrderListOutput listOutput = orderService.getRefundList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminRefundSearchViewHelper.refeshTable(request, pageForm, listOutput));
    }
	
}
