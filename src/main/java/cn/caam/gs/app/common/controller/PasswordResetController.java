package cn.caam.gs.app.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.view.PasswordResetViewHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping(path=PasswordResetViewHelper.URL_BASE)
public class PasswordResetController extends JcbcBaseController{
	
	@Autowired
	UserService userService;
	
    @PostMapping(path=PasswordResetViewHelper.URL_C_USER_RESET)
    @ResponseBody
    public int resetUser(
    		IdForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        
    	try {
    		return userService.resetUserPassword(request, pageForm.getId()) ? ExecuteReturnType.OK.getId():ExecuteReturnType.NG.getId();
    	}catch(Exception ex) {
    		log.error(ex.getMessage(), ex);
    		return ExecuteReturnType.OK.getId();
    	}
    }
    
    @PostMapping(path=PasswordResetViewHelper.URL_C_ADMIN_RESET)
    @ResponseBody
    public int resetAdmin(
    		IdForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
    	try {
    		return userService.resetAdminPassword(request, pageForm.getId()) ? ExecuteReturnType.OK.getId():ExecuteReturnType.NG.getId();
    	}catch(Exception ex) {
    		log.error(ex.getMessage(), ex);
    		return ExecuteReturnType.OK.getId();
    	}
    }
}
