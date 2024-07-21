package cn.caam.gs.app.user.message.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.MessageSearchForm;
import cn.caam.gs.app.common.output.MessageListOutput;
import cn.caam.gs.app.user.message.view.MessageSearchViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.MessageService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=MessageSearchViewHelper.URL_BASE)
public class MessageSearchController extends JcbcBaseController{
    
    @Autowired
    MessageService messageService;
	
	@GetMapping(path=MessageSearchViewHelper.URL_C_INIT)
	public ModelAndView init(
	        MessageSearchForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		return doSearch(pageForm, request, response, true);
	}
	
	@PostMapping(path=MessageSearchViewHelper.URL_C_SEARCH)
    public ModelAndView search(
            MessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        return doSearch(pageForm, request, response, false);
    }
	
	@PostMapping(path=MessageSearchViewHelper.URL_C_GROWING)
    public ModelAndView growing(
            MessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
        pageForm.setOffset(pageForm.getLimit()*pageForm.getMessagePageLinkIdPrefixIndex());
	    MessageListOutput userListOutput = messageService.getMessageList(userInfo.getId(), pageForm.getLimit(), pageForm.getOffset());
        return ControllerHelper.getModelAndView(
                MessageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
    }
	
	private ModelAndView doSearch(
	        MessageSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response,
            boolean init) {

        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
        pageForm.setMessagePageLinkIdPrefixIndex(0);
        pageForm.setOffset(pageForm.getLimit()*pageForm.getMessagePageLinkIdPrefixIndex());
        MessageListOutput userListOutput = messageService.getMessageList(userInfo.getId(), pageForm.getLimit(), pageForm.getOffset());
        pageForm.setOffset(userListOutput.getMessageList().size());
        if (init) {
            return ControllerHelper.getModelAndView(
                MessageSearchViewHelper.getMainPage(request, pageForm, userListOutput));
        } else {
            return ControllerHelper.getModelAndView(
                    MessageSearchViewHelper.refeshTable(request, pageForm, userListOutput));
        }
	}
}
