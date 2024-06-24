package cn.caam.gs.app.admin.userorder.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.userorder.view.ReviewOkViewHelper;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.mapper.MMessageMapper;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.OrderService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=ReviewOkViewHelper.URL_BASE)
public class ReviewOkController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    MMessageMapper messageMapper;
	
    @PostMapping(path=ReviewOkViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm idForm,
			HttpServletRequest request,
			HttpServletResponse response) {
        
        OrderInfo orderInfo = orderService.getOrder(idForm.getId());
        UserInfo userInfo = userService.getBaseUserInfo(orderInfo.getOrder().getUserId());
        
		return ControllerHelper.getModelAndView(
		        ReviewOkViewHelper.getMainPage(request, userInfo, orderInfo));
	}
    
    @PostMapping(path=ReviewOkViewHelper.URL_C_COMMIT)
    @ResponseBody
    public int commit(
            IdForm idForm,
            HttpServletRequest request,
            HttpServletResponse response) {

        return ExecuteReturnType.OK.getId();
    }
}