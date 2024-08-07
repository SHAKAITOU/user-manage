package cn.caam.gs.app.admin.usertypesettings.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.admin.usertypesettings.form.UserTypeSettingsForm;
import cn.caam.gs.app.admin.usertypesettings.view.UserTypeSettingsViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.domain.db.custom.entity.UserTypeSettingsInfo;
import cn.caam.gs.service.impl.UserTypeSettingsService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=UserTypeSettingsViewHelper.URL_BASE)
public class UserTypeSettingsController extends JcbcBaseController{
    
    @Autowired
    UserTypeSettingsService userTypeSettingsService;
    
	@GetMapping(path=UserTypeSettingsViewHelper.URL_C_INIT)
	public ModelAndView init(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		List<UserTypeSettingsInfo> userTypeSettingsInfoList = userTypeSettingsService.getUserTypeSettingList();
		return ControllerHelper.getModelAndView(
				UserTypeSettingsViewHelper.getMainPage(request,  userTypeSettingsInfoList));
	}
	
	@PostMapping(path=UserTypeSettingsViewHelper.URL_C_EDIT)
	@ResponseBody
	public int review(
			UserTypeSettingsForm pageForm,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		return userTypeSettingsService.update(pageForm) ?  ExecuteReturnType.OK.getId():ExecuteReturnType.NG.getId();
	}

}
