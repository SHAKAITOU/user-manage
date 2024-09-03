package cn.caam.gs.app.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.PasswordChangeForm;
import cn.caam.gs.app.common.view.PasswordChangeViewHelper;
import cn.caam.gs.app.common.view.PasswordResetViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=PasswordResetViewHelper.URL_BASE)
public class PasswordResetController extends JcbcBaseController{
	
	@Autowired
	UserService userService;
	
    @PostMapping(path=PasswordResetViewHelper.URL_C_INIT)
	public ModelAndView init(
			HttpServletRequest request,
			HttpServletResponse response) {
        
		return ControllerHelper.getModelAndView(
		        PasswordChangeViewHelper.getMainPage(request));
	}
    
    @PostMapping(path=PasswordResetViewHelper.URL_C_EDIT)
    @ResponseBody
    public int reset(
    		PasswordChangeForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (userService.checkPasswordCorrect(request, pageForm.getOldPassword())) {
        	userService.updatePassword(request, pageForm.getNewPassword());
        }else {
        	return ExecuteReturnType.NG.getId();
        }
        
        return ExecuteReturnType.OK.getId();
    }
}
