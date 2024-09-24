package cn.caam.gs.app.admin.message.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.message.view.AdminMessageSearchViewHelper;
import cn.caam.gs.app.common.form.MessageSearchForm;
import cn.caam.gs.app.common.output.MessageListOutput;
import cn.caam.gs.app.util.ControllerHelper;
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
	        MessageSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {
		pageForm.setMessagePageLinkIdPrefixIndex(0);
        pageForm.setOffset(pageForm.getLimit()*pageForm.getMessagePageLinkIdPrefixIndex());
        MessageListOutput userListOutput = messageService.getMessageList(pageForm);

		return ControllerHelper.getModelAndView(
		        AdminMessageSearchViewHelper.getMainPage(request, pageForm, userListOutput));
	}
	
	@GetMapping(path=AdminMessageSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            MessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
	    
        pageForm.setMessagePageLinkIdPrefixIndex(0);
        pageForm.setOffset(pageForm.getLimit()*pageForm.getMessagePageLinkIdPrefixIndex());
        MessageListOutput userListOutput = messageService.getMessageList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminMessageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	@GetMapping(path=AdminMessageSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            MessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        pageForm.setOffset(pageForm.getLimit()*pageForm.getMessagePageLinkIdPrefixIndex());
	    MessageListOutput userListOutput = messageService.getMessageList(pageForm);
        return ControllerHelper.getModelAndView(
                AdminMessageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
}
