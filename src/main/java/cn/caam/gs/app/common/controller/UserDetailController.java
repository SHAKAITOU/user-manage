package cn.caam.gs.app.common.controller;


import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.common.view.UserDetailViewHelper;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.DownloadFileType;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.LoginAccountType;
import cn.caam.gs.common.enums.PageModeType;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserCard;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.OrderService;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(path=UserDetailViewHelper.URL_BASE)
public class UserDetailController extends ScreenBaseController{
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserService userService;
	
	@Autowired
    OrderService orderService;

	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_INIT)
    public ModelAndView init(
            HttpServletRequest request,
            HttpServletResponse response)  {
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userService.getUserInfo(userInfo.getUser().getId()));
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm, new OrderListOutput(), PageModeType.EDIT_BY_USER));

    }
	
	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_FROM_ADMIN_INIT)
    public ModelAndView adminInit(
    		IdForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
	    UserInfo userInfo = userService.getUserInfo(pageForm.getId());
	    UserDetailForm userDetailForm = new UserDetailForm();
	    userDetailForm.setId(userInfo.getUser().getId());
	    userDetailForm.setUserInfo(userInfo);
	    
	    OrderSearchForm orderSearchForm = new OrderSearchForm();
	    MOrder mOrder = new MOrder();
	    mOrder.setUserId(userInfo.getUser().getId());
	    orderSearchForm.setOrder(mOrder);
	    orderSearchForm.setOrderPageLinkIdPrefixIndex(0);
	    orderSearchForm.setLimit(99999);
        OrderListOutput orderListOutput = orderService.getOrderList(orderSearchForm);
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm, orderListOutput, PageModeType.EDIT_BY_ADMIN));
    }
	
//	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_FROM_ADMIN_INIT)
//    public ModelAndView adminInit(
//    		UserSearchForm pageForm,
//            HttpServletRequest request,
//            HttpServletResponse response)  {
////		request.getSession().setAttribute(SessionConstants.USER_SEARCH_FORM.getValue(), pageForm);
//	    UserInfo userInfo = userService.getUserInfo(pageForm.getSelectedUserId());
//	    UserDetailForm userDetailForm = new UserDetailForm();
//	    userDetailForm.setId(userInfo.getUser().getId());
//	    userDetailForm.setUserInfo(userInfo);
//	    
//	    OrderSearchForm orderSearchForm = new OrderSearchForm();
//	    MOrder mOrder = new MOrder();
//	    mOrder.setUserId(userInfo.getUser().getId());
//	    orderSearchForm.setOrder(mOrder);
//	    orderSearchForm.setOrderPageLinkIdPrefixIndex(0);
//	    orderSearchForm.setLimit(99999);
//        OrderListOutput orderListOutput = orderService.getOrderList(orderSearchForm);
//	    
//        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm, orderListOutput, PageModeType.EDIT_BY_ADMIN));
//    }
	
//	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_FROM_ADMIN_INIT)
//    public ModelAndView adminInitFromOrder(
//    		OrderSearchForm pageForm,
//            HttpServletRequest request,
//            HttpServletResponse response)  {
//		request.getSession().setAttribute(SessionConstants.ORDER_SEARCH_FORM.getValue(), pageForm);
//	    UserInfo userInfo = userService.getUserInfo(pageForm.getUserId());
//	    UserDetailForm userDetailForm = new UserDetailForm();
//	    userDetailForm.setId(userInfo.getUser().getId());
//	    userDetailForm.setUserInfo(userInfo);
//	    
//        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm));
//
//    }
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_ADD_INIT)
    public ModelAndView addInit(
    		UserSearchForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  {
		 UserDetailForm userDetailForm = new UserDetailForm();
		    
		    UserInfo userInfo = new UserInfo();
		    userInfo.setUser(new MUser());
		    userInfo.setUserExtend(new MUserExtend());
		    userInfo.setUserCard(new MUserCard());
		    userDetailForm.setUserInfo(userInfo);
		    
	        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, userDetailForm, new OrderListOutput(), PageModeType.INSERT_BY_ADMIN));
    }
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_ADD)
    public ModelAndView add(
    		@ModelAttribute UserDetailForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
	    
		String id = userService.insertUserInfo(request, pageForm);
	    
//		UserDetailForm userDetailForm = new UserDetailForm();
		pageForm.setId(id);
		pageForm.setUserInfo(userService.getUserInfo(id));
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, pageForm, new OrderListOutput(), PageModeType.EDIT_BY_ADMIN));

    }
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_EDIT)
    public ModelAndView edit(
    		@ModelAttribute UserDetailForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
		UserInfo userInfo = null;
		if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
	    	userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		}else {
			userInfo = userService.getUserInfo(pageForm.getUserInfo().getUser().getId());
		}
	    userService.updateUserInfo(request, pageForm);
	    
//	    UserDetailForm userDetailForm = new UserDetailForm();
	    pageForm.setId(userInfo.getUser().getId());
	    pageForm.setUserInfo(userService.getUserInfo(userInfo.getUser().getId()));
	    
        return ControllerHelper.getModelAndView(UserDetailViewHelper.getMainPage(request, pageForm, new OrderListOutput(), 
        		LoginInfoHelper.isAdminLogin(request) ? PageModeType.EDIT_BY_ADMIN:PageModeType.EDIT_BY_USER));

    }
	
	@PostMapping(path=UserDetailViewHelper.URL_USER_DETAIL_DELETE)
	@ResponseBody
    public int delete(
    		@ModelAttribute IdForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
		
	    userService.deleteUserInfo(request, pageForm.getId());
	    
	    return ExecuteReturnType.OK.getId();
    }
	
	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_PRINT)
    public void print(
    		@ModelAttribute UserDetailForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
		UserInfo userInfo = null;
		if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
	    	userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    	
	    	response.setHeader(HttpHeaders.PRAGMA, "No-cache");
		    response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
		    String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".docx","UTF-8");
		    response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
		    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8");
		    userService.outputAppliactionForm(userInfo.getUser().getId(), response.getOutputStream());
		}
    }
	
	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_DOWNLOAD+"/{id}/{type}")
    public void download(
    		@PathVariable("id") String id,
    		@PathVariable("type") String type,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
	    DownloadFileType fileType = DownloadFileType.keyOf(type);
	    userService.downloadFile(request, id, fileType, response);
    }
	
	@GetMapping(path=UserDetailViewHelper.URL_USER_DETAIL_MEMBER_INFO+"/{id}")
    public ModelAndView getMemberInfo(
    		@PathVariable("id") String id,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		UserInfo userInfo = userService.getUserInfo(id);
		if(Objects.isNull(userInfo)) {
			userInfo = new UserInfo();
			userInfo.setUser(new MUser());
		}
		mav.addObject("userInfo", userInfo);
		mav.addObject("validEndDate", LocalDateUtility.formatDateZH(userInfo.getUser().getValidEndDate()));
		mav.setViewName("user/memberInfo.html");
		
		return mav;
    }
}
