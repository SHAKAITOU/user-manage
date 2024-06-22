package cn.caam.gs.app.admin.message.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.message.form.AdminMessageSearchForm;
import cn.caam.gs.app.admin.message.output.AdminMessageListOutput;
import cn.caam.gs.app.admin.message.view.AdminMessageSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.service.impl.MessageService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminMessageSearchViewHelper.URL_BASE)
public class AdminMessageSearchController extends JcbcBaseController{
    
    @Autowired
    MessageService messageService;
	
	@GetMapping(path=AdminMessageSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
	        AdminMessageSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
	    
	    AdminMessageListOutput userListOutput = new AdminMessageListOutput();
	    request.getSession().setAttribute(SessionConstants.MESSAGE_LIST_OUT_PUT.getValue(), userListOutput);

		return ControllerHelper.getModelAndView(
		        AdminMessageSearchViewHelper.getMainPage(request, pageForm, userListOutput));
	}
	
	@PostMapping(path=AdminMessageSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            AdminMessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
        pageForm.setOffset(0);
        AdminMessageListOutput userListOutput = messageService.getMessageList(pageForm);
        pageForm.setOffset(userListOutput.getMessageList().size());
        request.getSession().setAttribute(SessionConstants.MESSAGE_LIST_OUT_PUT.getValue(), userListOutput);
        return ControllerHelper.getModelAndView(
                AdminMessageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@PostMapping(path=AdminMessageSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            AdminMessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    AdminMessageListOutput userListOutput = 
                (AdminMessageListOutput)request.getSession().getAttribute(SessionConstants.MESSAGE_LIST_OUT_PUT.getValue());
	    AdminMessageListOutput growing = messageService.getMessageList(pageForm);
	    userListOutput.setCount(growing.getCount());
	    userListOutput.getMessageList().addAll(growing.getMessageList());
        pageForm.setOffset(userListOutput.getMessageList().size());
        request.getSession().setAttribute(SessionConstants.MESSAGE_LIST_OUT_PUT.getValue(), userListOutput);
        return ControllerHelper.getModelAndView(
                AdminMessageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
}
