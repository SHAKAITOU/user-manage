package cn.caam.gs.app.common.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.common.form.BindPhoneChangeForm;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.OnInputMode;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;

/**
 * View helper.
 */
@Component
public class BindPhoneChangeViewHelper extends HtmlViewBaseHelper {
	// base url
    public static final String URL_BASE = "/phoneChange";
    
    //init url
    public static final String URL_C_INIT	= UrlConstants.INIT;
    public static final String URL_C_EDIT	= UrlConstants.EDIT;
    public static final String URL_C_SEND_AUTH_CODE	= "/sendAuthCode";
    //init url
    
    public static final String BTN_OK		= "btnOk";
    public static final String BTN_CANCEL	= "btnCancel";
    
    public static final String MAIN_JS_CLASS  = "BindPhoneChange";
    public static final String FORM_NAME      = MAIN_JS_CLASS + "Form";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage( HttpServletRequest request, BindPhoneChangeForm pageForm) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, pageForm))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, BindPhoneChangeForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setDetailHidden(request, pageForm));
        sb.append(setCardPanel(request));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setDetailHidden(HttpServletRequest request, BindPhoneChangeForm pageForm) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        String name      = clmForm.getPageName("");
        String value     = nonNull(pageForm.getId());
        sb.append(hidden().get(name, value));
        return sb.toString();
    }
    
    private static String setCardPanel(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        List<String> contextList = new ArrayList<String>();

        //phone入力値
        String property  = "phone";
        String name      = property;
        String labelName = getContext("m_user.phone");
        String placeholder = getContext("m_user.phone.placeholder");
        String value      = "";
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value).onInputMode(OnInputMode.NUMBERS_ONLY)
                .notBlank(true).maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        List<CssAlignType> aligs = new ArrayList<>();
        aligs.add(CssAlignType.LEFT);
        //auth code 入力値
        property  = "authCode";
        name      = property;
        labelName = getContext("login.regist.step1.authCode");
        placeholder = getContext("login.regist.step1.authCode.placeholder");
        String comp1 = LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.AUTH_CODE_MAX_L).placeholder(placeholder)
                .fontSize(font).build().html();
        contextList.add(comp1);
        aligs.add(CssAlignType.LEFT);
        String id = "btnSendAuthCode";
        String context = getContext("PasswordForget.step1.btn.sendAuthCode");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);
        contextList.add(comp2);
//        sb.append(divRow().get(CellWidthType.TWO_9_3, aligs, comp1, comp2));
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString()));
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
        js.put("url_init",				URL_BASE + URL_C_INIT); 
        js.put("url_edit",				URL_BASE + URL_C_EDIT);
        js.put("url_send_auth_code",	URL_BASE + URL_C_SEND_AUTH_CODE); 
        
        return js;
    }
}
