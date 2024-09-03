package cn.caam.gs.app.common.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.common.form.PasswordForgetForm;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelPasswordSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;

/**
 * View helper.
 */
@Component
public class PasswordForgetViewHelper extends HtmlViewBaseHelper {
	// base url
    public static final String URL_BASE = "/passwordForget";
    
    //init url
    public static final String URL_C_INIT	= UrlConstants.INIT;
    public static final String URL_C_EDIT	= UrlConstants.EDIT;
    public static final String URL_C_SEND_AUTH_CODE	= "/sendAuthCode";
    //init url
    public static final String URL_C_STEP2	= "/step2";  
    
    public static final String BTN_OK		= "btnOk";
    public static final String BTN_CANCEL	= "btnCancel";
    public static final String BTN_NEXT		= "btnNext";
    public static final String BTN_BACK		= "btnBack";
    
    public static final String PASSWORD_FORGET_STEP1_JS_CLASS  = "PasswordForgetStep1";
    public static final String PASSWORD_FORGET_STEP1_FORM_NAME = PASSWORD_FORGET_STEP1_JS_CLASS + "Form";
    public static final String PASSWORD_FORGET_STEP2_JS_CLASS  = "PasswordForgetStep2";
    public static final String PASSWORD_FORGET_STEP2_FORM_NAME = PASSWORD_FORGET_STEP2_JS_CLASS + "Form";
    public static final String GET_AUTH_CODE_BY_PHONE = "01";
    public static final String GET_AUTH_CODE_BY_MAIL  = "02";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    public static ViewData getPasswordForgetStep1(PasswordForgetForm pageForm) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder().pageContext(getStep1Context(pageForm)).jsClassName(PASSWORD_FORGET_STEP1_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }
    
    public static ViewData getPasswordForgetStep2(PasswordForgetForm pageForm) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder().pageContext(getStep2Context(pageForm)).jsClassName(PASSWORD_FORGET_STEP2_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    private static String setStep1Hidden(PasswordForgetForm pageForm) {
        StringBuffer sb = new StringBuffer();
        String name = "errorMsg";
        String value = Objects.nonNull(pageForm) ? pageForm.getErrorMsg() : "";
        sb.append(hidden().get(name, value));
        name = "stepStatus";
        sb.append(hidden().get(name, pageForm.getStepStatus()));
        return sb.toString();
    }

    private static String getStep1Context(PasswordForgetForm pageForm) {
        StringBuffer body = new StringBuffer();

        // body
        body.append(buildStep1Body(pageForm));
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildStep1Footer());
        body.append(divRow().cellBlank(5));

        return getForm(PASSWORD_FORGET_STEP1_FORM_NAME, body.toString());
    }
    
    private static String buildStep1Body(PasswordForgetForm pageForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        // ----------hidden------[
        sb.append(setStep1Hidden(pageForm));
        // ----------hidden------]
        List<String> contextList = new ArrayList<String>();
        
        //auth code 入力値
        String property  = "authMethod";
        String name      = property;
        String labelName = getContext("login.regist.selectAuthMethod");
        List<HtmlRadio> radios = new ArrayList<>();
        radios.add(new HtmlRadio(GET_AUTH_CODE_BY_PHONE, getContext("login.regist.getAuthCodeByPhone")));
//        radios.add(new HtmlRadio(GET_AUTH_CODE_BY_MAIL,  getContext("login.regist.getAuthCodeByMail")));
        String value       = Objects.nonNull(pageForm.getMauthCode()) && GET_AUTH_CODE_BY_MAIL.equals(pageForm.getMauthCode().getAuthMethod()) ? GET_AUTH_CODE_BY_MAIL: GET_AUTH_CODE_BY_PHONE;
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());

        //phone入力値
        property  = "phone";
        name      = property;
        labelName = getContext("m_user.phone");
        String placeholder = getContext("m_user.phone.placeholder");
        value      = Objects.nonNull(pageForm.getPhone()) ? pageForm.getPhone() : "";
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
//        //mail入力値
//        property  = "mail";
//        name      = prefix_name + property;
//        labelName = getContext(prefix_label + property);
//        placeholder = getContext(prefix_label + property + subfix_placeholder);
//        value       = Objects.nonNull(pageForm.getUser()) ? pageForm.getUser().getMail() : "";
//        contextList.add(LabelInputSet.builder()
//                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
//                .notBlank(true).maxlength(GlobalConstants.MAIL_MAX_L).placeholder(placeholder)
//                .fontSize(font).grids(CssGridsType.G12).build().html());
//        
//        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
//        contextList = new ArrayList<String>();

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
        
        aligs.add(CssAlignType.LEFT);
        String id = "btnSendAuthCode";
        String context = getContext("PasswordForget.step1.btn.sendAuthCode");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);
        sb.append(divRow().get(CellWidthType.TWO_9_3, aligs, comp1, comp2));
        
        return borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString()));
    }
    
    private static String buildStep1Footer() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_CANCEL;
        String context = getContext("common.page.cancel");
        String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.DANGER, id, context);
        aligs.add(CssAlignType.LEFT);

        id = BTN_NEXT;
        context = getContext("PasswordForget.step1.btn.next");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        
        sb.append(divRow().get(CellWidthType.TWO_6_6, aligs, comp1, comp2));
        
        return sb.toString();
    }
    
    private static String setStep2Hidden(PasswordForgetForm pageForm) {
        StringBuffer sb = new StringBuffer();
        String name = "errorMsg";
        String value = Objects.nonNull(pageForm) ? pageForm.getErrorMsg() : "";
        sb.append(hidden().get(name, value));
        name = "stepStatus";
        sb.append(hidden().get(name, pageForm.getStepStatus()));
        
        sb.append(hidden().get("phone", pageForm.getPhone()));
        sb.append(hidden().get("authMethod", pageForm.getAuthMethod())); 
        
        return sb.toString();
    }
    
    //confirm page
    private static String getStep2Context(PasswordForgetForm pageForm) {

        StringBuffer body = new StringBuffer();

        // body
        body.append(buildStep2Body(pageForm));
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildStep2Footer());
        body.append(divRow().cellBlank(5));

        return getForm(PASSWORD_FORGET_STEP2_FORM_NAME, body.toString());
    }
    
    private static String buildStep2Body(PasswordForgetForm pageForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));

        // ----------hidden------[
        sb.append(setStep2Hidden(pageForm));
        // ----------hidden------]
        List<String> contextList = new ArrayList<String>();

        contextList = new ArrayList<String>();
        //新password
        String name      = "newPassword";
        String labelName = getContext("PasswordChange.newPassword");
        String placeholder = getContext("PasswordChange.newPassword.placeholder");
        String btnId = convertNameDotForId(name)+ "Show";
        contextList.add(LabelPasswordSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
                .buttonId(btnId).showIcon(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
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
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));

        return borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString()));
    }
    
    private static String buildStep2Footer() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_CANCEL;
        String context = getContext("common.page.cancel");
        String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.DANGER, id, context);
        aligs.add(CssAlignType.LEFT);
        
        id = BTN_BACK;
        context = getContext("common.page.ahead");
        String comp2 = button().getBorder(IconSetType.TO_LEFT, CssClassType.WARNING, id, context);
        
        id = BTN_OK;
        context = getContext("common.page.ok");
        String comp3 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(divRow().get(CellWidthType.TWO_4_8, aligs, comp1, concactWithSpace(comp2, comp3)));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",				URL_BASE + URL_C_INIT); 
        js.put("url_edit",				URL_BASE + URL_C_EDIT);
        js.put("url_step2",				URL_BASE + URL_C_STEP2); 
        js.put("url_send_auth_code",	URL_BASE + URL_C_SEND_AUTH_CODE); 
        
        return js;
    }
}
