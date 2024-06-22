package cn.caam.gs.app.admin.message.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.message.view.AdminMessageDetailViewHelper;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.mapper.MMessageMapper;
import cn.caam.gs.service.impl.MessageService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminMessageDetailViewHelper.URL_BASE)
public class AdminMessageDetailController extends JcbcBaseController{
    
    @Autowired
    MessageService messageService;
    
    @Autowired
    MMessageMapper messageMapper;
	
    @PostMapping(path=AdminMessageDetailViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm idForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        AdminMessageDetailViewHelper.getMainPage(request, messageMapper.selectByPrimaryKey(idForm.getId())));
	}
}
