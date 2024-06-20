package cn.caam.gs.app.admin.menu.menu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.common.enums.CellWidthType;

@Component
public class AdminMenuViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.MENU;
	//init url
	public static final String URL_C_INIT = URL_BASE + UrlConstants.INIT;
	
	//MENU
	public static final String HTML_MENU_MENU 	= "admin/adminMenu";
	
	public static final String LOGOUT_BTN_ID    = "btnLogoutInit";
	
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
		
		return js;
	}
}
