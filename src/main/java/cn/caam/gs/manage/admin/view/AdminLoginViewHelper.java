package cn.caam.gs.manage.admin.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.util.UrlConstants;
import cn.caam.gs.common.html.HtmlViewBaseHelper;


@Component
public class AdminLoginViewHelper extends HtmlViewBaseHelper {
    
    //init url
    public static final String URL_BASE         = UrlConstants.ADMIN;
    //init url
    public static final String URL_C_LOGIN_INIT = UrlConstants.INIT;
    //init url
    public static final String URL_C_USER_LOGIN = UrlConstants.USER_LOGIN;
    //logout url
    public static final String URL_C_LOGOUT     = UrlConstants.LOGOUT;

    
    public static final String HTML_INDEX                 = "admin/adminIndex";
    public static final String HTML_LOGIN                 = "admin/adminLoginBlock";


    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_login",      URL_BASE);
        js.put("url_login_init", URL_BASE + URL_C_LOGIN_INIT);
        js.put("url_user_login", URL_BASE + URL_C_USER_LOGIN);
        js.put("url_logout",     URL_BASE + URL_C_LOGOUT);
        // btn
        js.put("btn_logout",     getContext("login.panel.btn.logout"));
        // title
        js.put("title",          getContext("login.title"));
        // label
        js.put("label_id",     getContext("m_admin.id"));
        js.put("label_pw", getContext("userAdd.item.mail"));
        js.put("msg_logout_confirm", getContext("login.logout.confirm.msg"));
        
        
        
        return js;
    }
}
