package cn.caam.gs.manage.admin.view.menu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.manage.util.HtmlViewHelper;
import cn.caam.gs.common.enums.AdminMenuType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.manage.util.UrlConstants;

@Component
public class AdminMenuViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.MENU;
	//init url
	public static final String URL_C_INIT = URL_BASE + UrlConstants.INIT;


	//other url
	public static final String URL_C_MENU_OTHER = "/other";


	//work url
	public static final String URL_C_MENU_WORK = "/work";
	
	//system url
	public static final String URL_C_MENU_SYSTEM = "/system";
	
	//logout url
	public static final String URL_C_MENU_LOGOUT = "/logout";
	
	//MENU
	public static final String HTML_MENU_MENU 				= "admin/adminMenu";
	
	public static final String LOGOUT_BTN_ID = "btnLogoutInit";
	
	public static String getPage(Map<String,Object> session) {
		StringBuffer sb = new StringBuffer();
		sb.append(divRow().cellBlank(5));
		sb.append(divContainer().get(
				divRow().get(CellWidthType.ONE, "<h1 width='400px'>主婦ヘルパー管理</h1>")));
		sb.append(divRow().cellBlank(5));
		sb.append(getMenuBarInfo(session));
		sb.append(getMenuBody());
		return sb.toString();
	}
	
	private static String getMenuBarInfo(Map<String,Object> session) {
		StringBuffer sb = new StringBuffer();
		

		String comp2 = "";
		sb.append(divRow().get(CellWidthType.ONE, comp2));
		return divContainer().get(sb.toString());
	}
	
	private static String getMenuBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='menuBody'></div>");
		return sb.toString();
	}

	
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init", URL_BASE + URL_C_INIT);
		js.put("url_menu_work", URL_BASE + URL_C_MENU_WORK);
		js.put("url_menu_other", URL_BASE + URL_C_MENU_OTHER);
		js.put("url_menu_system", URL_BASE + URL_C_MENU_SYSTEM);
		js.put("url_menu_logout", URL_BASE + URL_C_MENU_LOGOUT);
		
		
		return js;
	}
}
