package cn.caam.gs.app.admin.userorder.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.admin.userorder.view.AdminOrderSearchViewHelper;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.service.impl.OrderService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminOrderSearchViewHelper.URL_BASE)
public class AdminOrderSearchController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
	
	@GetMapping(path=AdminOrderSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
	        OrderSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
	    
	    MOrder order = new MOrder();
        order.setCheckStatus(GlobalConstants.DFL_SELECT_ALL);
        pageForm.setOrder(order);
	    OrderListOutput listOutput = new OrderListOutput();

		return ControllerHelper.getModelAndView(
		        AdminOrderSearchViewHelper.getMainPage(request, pageForm, listOutput));
	}
	
	@PostMapping(path=AdminOrderSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    pageForm.setOrderPageLinkIdPrefixIndex(0);
	    pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
        pageForm.getOrder().setCheckStatus(GlobalConstants.DFL_SELECT_ALL);
        OrderListOutput listOutput = orderService.getOrderList(pageForm);
        pageForm.setOffset(listOutput.getOrderList().size());
        return ControllerHelper.getModelAndView(
                AdminOrderSearchViewHelper.refeshTable(request, pageForm, listOutput));
    }
	
	@PostMapping(path=AdminOrderSearchViewHelper.URL_C_SEARCH_WAIT)
    public ModelAndView searchWait(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    pageForm.setOrderPageLinkIdPrefixIndex(0);
	    pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
        MOrder order = new MOrder();
        order.setCheckStatus(CheckStatusType.WAIT_FOR_REVIEW.getKey());
        order.setOrderType(GlobalConstants.DFL_SELECT_ALL);
        order.setBillStatus(GlobalConstants.DFL_SELECT_ALL);
        pageForm.setOrder(order);
        pageForm.setPayDateFrom(null);
        pageForm.setPayDateTo(null);
        pageForm.setInVisableSearch(true);
        pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
        OrderListOutput listOutput = orderService.getOrderList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminOrderSearchViewHelper.getMainPage(request, pageForm, listOutput));
    }
	
	@PostMapping(path=AdminOrderSearchViewHelper.URL_C_SEARCH_REVIEW)
    public ModelAndView searchReview(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    pageForm.setOrderPageLinkIdPrefixIndex(0);
	    pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
        MOrder order = new MOrder();
        order.setCheckStatus(CheckStatusType.REVIEW.getKey());
        order.setOrderType(GlobalConstants.DFL_SELECT_ALL);
        order.setBillStatus(GlobalConstants.DFL_SELECT_ALL);
        pageForm.setOrder(order);
        pageForm.setPayDateFrom(null);
        pageForm.setPayDateTo(null);
        pageForm.setInVisableSearch(true);
        OrderListOutput listOutput = orderService.getOrderList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminOrderSearchViewHelper.getMainPage(request, pageForm, listOutput));
    }
	
	@PostMapping(path=AdminOrderSearchViewHelper.URL_C_SEARCH_PASS)
    public ModelAndView searchPass(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    pageForm.setOrderPageLinkIdPrefixIndex(0);
        pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
        MOrder order = new MOrder();
        order.setCheckStatus(CheckStatusType.PASS.getKey());
        order.setOrderType(GlobalConstants.DFL_SELECT_ALL);
        order.setBillStatus(GlobalConstants.DFL_SELECT_ALL);
        pageForm.setOrder(order);
        pageForm.setPayDateFrom(null);
        pageForm.setPayDateTo(null);
        pageForm.setInVisableSearch(true);
        OrderListOutput listOutput = orderService.getOrderList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminOrderSearchViewHelper.getMainPage(request, pageForm, listOutput));
    }
	
	@PostMapping(path=AdminOrderSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            OrderSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    pageForm.setOffset(pageForm.getLimit()*pageForm.getOrderPageLinkIdPrefixIndex());
	    OrderListOutput listOutput = orderService.getOrderList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminOrderSearchViewHelper.refeshTable(request, pageForm, listOutput));
    }
	
	@PostMapping(path=AdminOrderSearchViewHelper.URL_C_PUT_TO_REVIEW)
	@ResponseBody
    public int putToReview(
            IdForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
	    orderService.updateOrderToReview(pageForm);
	    
	    return ExecuteReturnType.OK.getId();
	}
}
