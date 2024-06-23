package cn.caam.gs.app.admin.message.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.message.view.AdminMessagePushViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.mapper.MMessageMapper;
import cn.caam.gs.service.impl.MessageService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminMessagePushViewHelper.URL_BASE)
public class AdminMessagePushController extends JcbcBaseController{
    
    @Autowired
    MessageService messageService;
    
    @Autowired
    MMessageMapper messageMapper;
	
    @PostMapping(path=AdminMessagePushViewHelper.URL_C_INIT)
	public ModelAndView init(
	        MMessage pageForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        AdminMessagePushViewHelper.getMainPage(request, pageForm));
	}
	
	@PostMapping(path=AdminMessagePushViewHelper.URL_C_ADD)
	@ResponseBody
    public int push(
            MMessage pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {

	    
	    messageService.addMessage(pageForm, MsgType.ALL);
	    
        return ExecuteReturnType.OK.getId();
    }
}
