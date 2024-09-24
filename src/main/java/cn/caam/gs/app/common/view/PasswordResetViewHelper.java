package cn.caam.gs.app.common.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelPasswordSet;

@Component
public class PasswordResetViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/passwordReset";
    
    //init url
//    public static final String URL_C_INIT = UrlConstants.INIT;
    //init url
    public static final String URL_C_USER_RESET = "/user";
    
    public static final String URL_C_ADMIN_RESET = "/admin"; 
    
    public static final String MAIN_JS_CLASS  = "PasswordReset";
    public static final String FORM_NAME      = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_OK    = "btnOk";
    public static final String BTN_CANCEL = "btnCancel";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage( HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setCardPanel(request));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }

    //--------------------header Panel -----------------
    
    private static String setCardPanel(
            HttpServletRequest request) {
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        sbBody.append(divRow().cellBlank(5));
        
        //password入力値
        String name      = "oldPassword";;
        String labelName = getContext("PasswordChange.oldPassword");
        String placeholder = getContext("PasswordChange.oldPassword.placeholder");
        String btnId = convertNameDotForId(name)+ "Show";
        contextList.add(LabelPasswordSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
                .buttonId(btnId).showIcon(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        //新password
        name      = "newPassword";
        labelName = getContext("PasswordChange.newPassword");
        placeholder = getContext("PasswordChange.newPassword.placeholder");
        btnId = convertNameDotForId(name)+ "Show";
        contextList.add(LabelPasswordSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
                .buttonId(btnId).showIcon(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        //新password重复入力値
        name      = "newPasswordRep";
        labelName = getContext("PasswordChange.newPasswordRep");
        placeholder = getContext("PasswordChange.newPasswordRep.placeholder");
        btnId = convertNameDotForId(name)+ "Show";
        contextList.add(LabelPasswordSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
                .buttonId(btnId).showIcon(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return borderCard().noTitleWithScroll("", CssClassType.WARNING, "", 180, divContainer().get(sbBody.toString()));
    }
    
    private static String buildFooter() {
    	 StringBuffer sb = new StringBuffer();
         List<CssAlignType> aligs = new ArrayList<>();
         // bottom button
         String id = BTN_CANCEL;
         String context = getContext("common.page.cancel");
         String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.DANGER, id, context);
         aligs.add(CssAlignType.LEFT);
         
         id = BTN_OK;
         context = getContext("common.page.ok");
         String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

         aligs.add(CssAlignType.RIGHT);
         sb.append(divRow().get(CellWidthType.TWO_6_6, aligs, comp1, comp2));
         
         return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
//        js.put("url_init",	URL_BASE + URL_C_INIT); 
        js.put("url_user_reset",	URL_BASE + URL_C_USER_RESET); 
        js.put("url_admin_reset",	URL_BASE + URL_C_ADMIN_RESET); 
        
        return js;
    }
}
