package cn.caam.gs.app.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.view.MessageDetailViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.mapper.MMessageMapper;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.MessageService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=MessageDetailViewHelper.URL_BASE)
public class MessageDetailController extends JcbcBaseController{
    
    @Autowired
    MessageService messageService;
    
    @Autowired
    MMessageMapper messageMapper;
	
    @PostMapping(path=MessageDetailViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm idForm,
			HttpServletRequest request,
			HttpServletResponse response) {
        
        if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof UserInfo) {
            UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
            messageService.setMessageRead(idForm.getId(), userInfo.getId());
        }

		return ControllerHelper.getModelAndView(
		        MessageDetailViewHelper.getMainPage(request, messageMapper.selectByPrimaryKey(idForm.getId())));
	}
    
    @GetMapping(path=MessageDetailViewHelper.URL_C_GET_UN_READ_CNT)
    @ResponseBody
    public int getUnReadCnt(
            HttpServletRequest request,
            HttpServletResponse response) {
        
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
        return messageService.getUnReadCnt(userInfo.getId());
    }
}
