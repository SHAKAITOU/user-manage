package cn.caam.gs.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.view.CommonViewHelper;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserInfoMapper;

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

    @PostMapping(path=CommonViewHelper.URL_SHOW_PHOTO)
    public ModelAndView showPhoto(
            UserInfo pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
        
        //;
        MUserExtend userInfo = optionalUserInfoMapper.getUserPhoto(pageForm.getUser().getId());

        return ControllerHelper.getModelAndView(CommonViewHelper.getShowPhotoPage(request, userInfo));
    }

}

