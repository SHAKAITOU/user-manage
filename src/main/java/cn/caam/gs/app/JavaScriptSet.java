package cn.caam.gs.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.core.env.Environment;

import cn.caam.gs.app.admin.login.view.AdminLoginViewHelper;
import cn.caam.gs.app.admin.menu.menu.AdminMenuViewHelper;
import cn.caam.gs.app.admin.message.view.AdminMessagePushViewHelper;
import cn.caam.gs.app.admin.message.view.AdminMessageSearchViewHelper;
import cn.caam.gs.app.admin.userbill.view.AdminBillSearchViewHelper;
import cn.caam.gs.app.admin.userbill.view.BillViewHelper;
import cn.caam.gs.app.admin.userbill.view.RefundViewHelper;
import cn.caam.gs.app.admin.userorder.view.AdminOrderSearchViewHelper;
import cn.caam.gs.app.admin.userorder.view.ReviewNgViewHelper;
import cn.caam.gs.app.admin.userorder.view.ReviewOkViewHelper;
import cn.caam.gs.app.admin.userorder.view.ReviewOrderViewHelper;
import cn.caam.gs.app.admin.userreview.view.AdminUserReviewMultiViewHelper;
import cn.caam.gs.app.admin.userreview.view.AdminUserReviewSearchViewHelper;
import cn.caam.gs.app.admin.userreview.view.AdminUserReviewViewHelper;
import cn.caam.gs.app.admin.usersearch.view.AdminUserSearchViewHelper;
import cn.caam.gs.app.admin.usertypesettings.view.UserTypeSettingsViewHelper;
import cn.caam.gs.app.common.view.CommonViewHelper;
import cn.caam.gs.app.common.view.MessageDetailViewHelper;
import cn.caam.gs.app.common.view.OrderDetailViewHelper;
import cn.caam.gs.app.common.view.UserDetailViewHelper;
import cn.caam.gs.app.user.certi.view.UserCertiViewHelper;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.user.message.view.MessageSearchViewHelper;
import cn.caam.gs.app.user.order.view.OrderSearchViewHelper;
import cn.caam.gs.app.user.order.view.OrderViewHelper;
import cn.caam.gs.app.user.userreview.view.UserReviewSearchViewHelper;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import lombok.Data;

public class JavaScriptSet {

	private MessageSourceUtil messageSourceUtil;

	private Environment environment;

	private JavaScriptSetInfo javaScriptSetInfo;
	
	private HttpServletRequest request;

	public JavaScriptSet(HttpServletRequest request, Environment environment, MessageSourceUtil messageSourceUtil) {
		this.javaScriptSetInfo = new JavaScriptSetInfo();
		this.request = request;
		this.messageSourceUtil = messageSourceUtil;
		this.environment = environment;
		
	}

	public String init() {
		return JsonUtility.toJson(initJavaScriptInfo(false));
	}
	
	public String initAdmin() {
		return JsonUtility.toJson(initJavaScriptInfo(true));
	}

	public String loginSet() {
		JavaScriptSetInfo jsForm = initJavaScriptInfo(false);
		setCommon(jsForm);
		setJsView(jsForm);
		return JsonUtility.toJson(jsForm);
	}
	
	public String loginAdminSet() {
		JavaScriptSetInfo jsForm = initJavaScriptInfo(true);
		setCommon(jsForm);
		setJsView(jsForm);
		return JsonUtility.toJson(jsForm);
	}

	private JavaScriptSetInfo initJavaScriptInfo(boolean adminMode) {
		// HttpStatus
		javaScriptSetInfo.setHttpStatus(initHttpStatus());
		// common
		javaScriptSetInfo.setCommon(initCommon());
		// jsView
		
		javaScriptSetInfo.setJsView(initJsView());
		if(adminMode) {
			javaScriptSetInfo.setAdminJsView(initAdminJsView());
		}
		JSONObject msgObj = new JSONObject();
        for (String key : messageSourceUtil.getKeys()) {
            msgObj.put(key, messageSourceUtil.getContext(key));
        }
		javaScriptSetInfo.setI18n(msgObj.toString());
		return javaScriptSetInfo;
	}

	private Map<String, Object> initHttpStatus() {
		Map<String, Object> form = new HashMap<String, Object>();
		form.put("httpStatus400", getContext("common.http.status.400.msg"));
		form.put("httpStatus403", getContext("common.http.status.403.msg"));
		form.put("httpStatus404", getContext("common.http.status.404.msg"));
		form.put("httpStatus405", getContext("common.http.status.405.msg"));
		form.put("httpStatus500", getContext("common.http.status.500.msg"));
		form.put("httpStatus500AccessDeny", getContext("common.http.status.500.accessDenyMsg"));

		return form;
	}

	private Map<String, Object> initCommon() {
		Map<String, Object> common = new HashMap<String, Object>();
		common.put("appDomain", environment.getProperty("app.domain"));
		common.put("urlBasePath", environment.getProperty("server.servlet.context-path"));
		// check
		common.put("checkBlankMsg", getContext("common.check.msg.blank"));
		common.put("checkNumberOnlyMsg", getContext("common.check.msg.numberOnly"));
		common.put("checkMaxLengthMsg", getContext("common.check.msg.maxLength"));
		common.put("checkMinLengthMsg", getContext("common.check.msg.minLength"));
		common.put("checkMaxNumberMsg", getContext("common.check.msg.maxNumber"));
		common.put("checkMinNumberMsg", getContext("common.check.msg.minNumber"));
		common.put("checkRequiredNumberValMsg", getContext("common.check.msg.requiredNumberVal"));
		common.put("checkPhoneNumberMsg", getContext("common.check.msg.phoneNumberMsg"));
		common.put("checkEmailMsg", getContext("common.check.msg.emailMsg"));
		common.put("checkPhoneNumberExistedMsg", getContext("common.check.msg.phoneNumberExistedMsg"));
		common.put("checkPhoneNumberNotExistedMsg", getContext("common.check.msg.phoneNumberNotExistedMsg"));
		common.put("checkEmailExistedMsg", getContext("common.check.msg.emailExistedMsg"));
		common.put("checkAuthCodeErrorMsg", getContext("common.check.msg.authCodeErrorMsg"));

		// enum
		common.put("executeReturnTypeOk", ExecuteReturnType.OK.getId());
		common.put("executeReturnTypeNg", ExecuteReturnType.NG.getId());

		// properties
		common.put("datetimeFormat", getContext("datetime.format"));
		common.put("dayTypeName", getContext("DayType.dayName"));

		common.put("msg_conditionChanged", getContext("common.msg.conditionChanged"));
		common.put("msg_noData", getContext("common.msg.noData"));
		common.put("msg_dialogs_confirm_add", getContext("dialogs.confirm.add.msg"));
		common.put("msg_dialogs_confirm_edit", getContext("dialogs.confirm.edit.msg"));
		common.put("msg_dialogs_confirm_delete", getContext("dialogs.confirm.delete.msg"));
		common.put("msg_dialogs_confirm_move", getContext("dialogs.confirm.move.msg"));
		common.put("msg_dialogs_add_success", getContext("dialogs.add.success.msg"));
		common.put("msg_dialogs_edit_success", getContext("dialogs.edit.success.msg"));
		common.put("msg_dialogs_delete_success", getContext("dialogs.delete.success.msg"));
		common.put("msg_dialogs_move_success", getContext("dialogs.move.success.msg"));
		common.put("msg_dialogs_cancel_regist", getContext("dialogs.confirm.cancelRegist.msg"));
		
		common.put("common",        CommonViewHelper.getJsProperties());
		common.put("orderDetail",   OrderDetailViewHelper.getJsProperties());
		common.put("messageDetail", MessageDetailViewHelper.getJsProperties());
		
		common.put("max_bill_amount", GlobalConstants.MAX_BILL_AMOUNT);
		common.put("min_bill_amount", GlobalConstants.MIN_BILL_AMOUNT);
		
		common.put("user_regist_sms_send_interval", GlobalConstants.USER_REGIST_SMS_SEND_INTERVAL);
		common.put("user_login_auth_code_expired_minute", GlobalConstants.USER_LOGIN_AUTH_CODE_EXPIRED_MINUTE);
		
		common.put("user_id_max_l", GlobalConstants.USER_ID_MAX_L);
		common.put("user_name_max_l", GlobalConstants.USER_NAME_MAX_L);
		common.put("user_pw_max_l", GlobalConstants.USER_PW_MAX_L);
		common.put("user_pw_min_l", GlobalConstants.USER_PW_MIN_L);
		common.put("phone_max_l", GlobalConstants.PHONE_MAX_L);
		common.put("mail_max_l", GlobalConstants.MAIL_MAX_L);
		common.put("auth_code_max_l", GlobalConstants.AUTH_CODE_MAX_L);
		common.put("employer_max_l", GlobalConstants.EMPLOYER_MAX_L);
		common.put("certificate_code_max_l", GlobalConstants.CERTIFICATE_CODE_MAX_L);
		common.put("post_code_max_l", GlobalConstants.POST_CODE_MAX_L);
		common.put("address_max_l", GlobalConstants.ADDRESS_MAX_L);
		common.put("introducer1_max_l", GlobalConstants.INTRODUCER1_MAX_L);
		common.put("research_dir_max_l", GlobalConstants.RESEARCH_DIR_MAX_L);
		common.put("major_max_l", GlobalConstants.MAJOR_MAX_L);
		common.put("learn_experience_max_l", GlobalConstants.LEARN_EXPERIENCE_MAX_L);
		common.put("amount_max_l", GlobalConstants.AMOUNT_MAX_L);
		common.put("bill_code_max_l", GlobalConstants.BILL_CODE_MAX_L);
		common.put("bill_title_max_l", GlobalConstants.BILL_TITLE_MAX_L);
		common.put("check_status_type_pass", UserCheckStatusType.PASS.getKey());

		JSONObject msgObj = new JSONObject();
        for (String key : messageSourceUtil.getKeys()) {
            msgObj.put(key, messageSourceUtil.getContext(key));
        }
        common.put("i18n", msgObj.toString());
		return common;
	}

	private Map<String, Object> initJsView() {
		Map<String, Object> jsView = new HashMap<String, Object>();
	    // login
		jsView.put("login",                LoginViewHelper.getJsProperties());
		jsView.put("userDetail",           UserDetailViewHelper.getJsProperties());
		jsView.put("userCerti",            UserCertiViewHelper.getJsProperties());

		jsView.put("orderSearch",          OrderSearchViewHelper.getJsProperties());
		jsView.put("order",                OrderViewHelper.getJsProperties());
		
		jsView.put("userReviewSearch",     UserReviewSearchViewHelper.getJsProperties());
		
		jsView.put("messageSearch",        MessageSearchViewHelper.getJsProperties());
		
		return jsView;
	}
	
	private Map<String, Object> initAdminJsView() {
		Map<String, Object> jsView = new HashMap<String, Object>();
		jsView.put("adminLogin",         AdminLoginViewHelper.getJsProperties());
		jsView.put("adminMenu",          AdminMenuViewHelper.getJsProperties());
		
		jsView.put("adminUserReview",		AdminUserReviewViewHelper.getJsProperties());
		jsView.put("adminUserReviewMulti",	AdminUserReviewMultiViewHelper.getJsProperties());
		jsView.put("adminUserReviewSearch",	AdminUserReviewSearchViewHelper.getJsProperties());
		
		jsView.put("adminUserSearch",    AdminUserSearchViewHelper.getJsProperties());
		
		jsView.put("adminMessageSearch", AdminMessageSearchViewHelper.getJsProperties());
		jsView.put("adminMessagePush",   AdminMessagePushViewHelper.getJsProperties());
		
		jsView.put("adminOrderSearch",   AdminOrderSearchViewHelper.getJsProperties());
		jsView.put("adminReviewOrder",   ReviewOrderViewHelper.getJsProperties());
		jsView.put("adminReviewOk",      ReviewOkViewHelper.getJsProperties());
		jsView.put("adminReviewNg",      ReviewNgViewHelper.getJsProperties());
		
		jsView.put("adminBillSearch",    AdminBillSearchViewHelper.getJsProperties());
		jsView.put("adminBill",          BillViewHelper.getJsProperties());
		jsView.put("adminRefund",        RefundViewHelper.getJsProperties());
		
		jsView.put("adminRefund",        RefundViewHelper.getJsProperties());
		
		jsView.put("userTypeSettings",	UserTypeSettingsViewHelper.getJsProperties());
		
		
		
		return jsView;
	}

	// after login
	private void setCommon(JavaScriptSetInfo setInfo) {
		/*
		setInfo.getCommon().put("adminAuth", LoginInfoHelper.isAdminAuth(request));
		setInfo.getCommon().put("mainManagerAuth", LoginInfoHelper.isMainManagerAuth(request));
		setInfo.getCommon().put("salesManAuth", LoginInfoHelper.isSalesManAuth(request));

		setInfo.getCommon().put("msg_applyTypeError",
				messageSourceUtil.getContext("authorityGroupAdd.msg.applyTypeError"));
				*/
	}

	private void setJsView(JavaScriptSetInfo setInfo) {

		// menu
		//setInfo.getJsView().put("menu", 				MenuViewHelper.getJsProperties());
		
	}

	private String getContext(String key) {
		return messageSourceUtil.getContext(key);
	}

	@Data
	public class JavaScriptSetInfo {

		private Map<String, Object> httpStatus;

		private Map<String, Object> common;

		private Map<String, Object> jsView;
		
		private Map<String, Object> adminJsView;
		
		private String i18n;
	}
}
