package cn.caam.gs.app.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.view.CommonViewHelper;
import cn.caam.gs.app.user.login.form.LoginForm;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserInfoMapper;
import cn.caam.gs.service.impl.UserService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=CommonViewHelper.URL_BASE)
public class CommonController extends ScreenBaseController{
    
    @Autowired
    OptionalUserInfoMapper optionalUserInfoMapper;
    
    @Autowired
	UserService userService;
    
    @PostMapping(path=CommonViewHelper.URL_WINDOW_RESIZE)
    @ResponseBody
    public String winResize(
            LoginForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
        request.getSession().setAttribute(SessionConstants.MEDIA_HEIGHT.getValue(), pageForm.getMediaHeight());
        request.getSession().setAttribute(SessionConstants.MEDIA_WIDTH.getValue(), pageForm.getMediaWidth());

        return "";
    }

    @PostMapping(path=CommonViewHelper.URL_SHOW_PHOTO)
    public ModelAndView showPhoto(
            UserInfo pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
        
        //;
        MUserExtend userInfo = optionalUserInfoMapper.getUserPhoto(pageForm.getUser().getId());

        return ControllerHelper.getModelAndView(CommonViewHelper.getShowPhotoPage(request, userInfo));
    }
    
    @PostMapping(path=CommonViewHelper.URL_CHECK_USER_PHONE_NUMBER)
    @ResponseBody
	public String checkPhoneNumber(
			@RequestParam("phoneNumber") String phoneNumber,
			HttpServletRequest request,
			HttpServletResponse response) {

		if (userService.isPhoneNumberExist(phoneNumber)) {
			return "existed";
		}
		return "";
	}
    
    @PostMapping(path=CommonViewHelper.URL_CHECK_USER_EMAIL)
    @ResponseBody
	public String checkEmail(
			@RequestParam("email") String email,
			HttpServletRequest request,
			HttpServletResponse response) {

    	if (userService.isEmailExist(email)) {
			return "existed";
		}
		return "";
	}
}

