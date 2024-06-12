package cn.caam.gs.app.user.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import cn.caam.gs.GlobalConstants;
import cn.caam.gs.app.bean.ViewData;
import cn.caam.gs.app.form.RegistForm;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.manage.dbmaintenance.table.impl.g01master.T100MUser;

/**
 * View helper.
 */
@Component
public class RegistStep1ViewHelper extends HtmlViewBaseHelper {

    public static final String REGIST_FORM_NAME = "registForm";
    public static final String REGIST_JS_CLASS          = "RegistStep1";
    public static final String REGIST_CONFIRM_JS_CLASS  = "RegistStep1Confirm";
    public static final String GET_AUTH_CODE_BY_PHONE = "01";
    public static final String GET_AUTH_CODE_BY_MAIL  = "02";
    
    public static final CssFontSizeType font = GlobalConstants.FONT_SIZE;

    public static ViewData getRegist1Page(RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(REGIST_FORM_NAME, REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist1Context(registForm)).jsClassName(REGIST_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }
    
    public static ViewData getRegist1ConfirmPage(RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(REGIST_FORM_NAME, REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist1ConfirmContext(registForm)).jsClassName(REGIST_CONFIRM_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    private static String setRegist1Hidden(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        String name = "errorMsg";
        String value = Objects.nonNull(registForm) ? registForm.getErrorMsg() : "";
        sb.append(hidden().get(name, value));
        return sb.toString();
    }

    private static String getRegist1Context(RegistForm registForm) {

        StringBuffer body = new StringBuffer();

        // body
        body.append(buildInputBody(registForm));
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildFooter());
        body.append(divRow().cellBlank(5));

        return getForm(REGIST_FORM_NAME, body.toString());
    }
    
    private static String buildInputBody(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        String prefix_name  = "user.";
        String prefix_label = T100MUser.TABLE_NAME + ".";
        String subfix_placeholder = ".placeholder";

        // ----------hidden------[
        sb.append(setRegist1Hidden(registForm));
        // ----------hidden------]
        List<String> contextList = new ArrayList<String>();

        //phone入力値
        String property  = "phone";
        String name      = prefix_name + property;
        String labelName = getContext(prefix_label + property);
        String placeholder = getContext(prefix_label + property + subfix_placeholder);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //mail入力値
        property  = "mail";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        placeholder = getContext(prefix_label + property + subfix_placeholder);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.MAIL_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();

        //auth code 入力値
        property  = "authMethod";
        name      = property;
        labelName = getContext("login.regist.selectAuthMethod");
        List<HtmlRadio> radios = new ArrayList<>();
        radios.add(new HtmlRadio(GET_AUTH_CODE_BY_PHONE, getContext("login.regist.getAuthCodeByPhone")));
        radios.add(new HtmlRadio(GET_AUTH_CODE_BY_MAIL,  getContext("login.regist.getAuthCodeByMail")));
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GET_AUTH_CODE_BY_PHONE)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString()));
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = "btnBack";
        String context = getContext("login.regist.step1.btn.cancel");
        String comp1 = button().getBorder(IconSetType.TO_LEFT, CssClassType.DANGER, id, context);

        id = "btnNext";
        context = getContext("login.regist.step1.btn.next");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        
        return sb.toString();
    }
    
    //confirm page
    private static String getRegist1ConfirmContext(RegistForm registForm) {

        StringBuffer body = new StringBuffer();

        // body
        body.append(buildConfirmBody(registForm));
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildConfirmFooter());
        body.append(divRow().cellBlank(5));

        return getForm(REGIST_FORM_NAME, body.toString());
    }
    
    private static String buildConfirmBody(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));

        // ----------hidden------[
        sb.append(setRegist1Hidden(registForm));
        // ----------hidden------]
        List<String> contextList = new ArrayList<String>();

        //auth code 入力値
        String property  = "authCode";
        String name      = property;
        String labelName = getContext("login.regist.step1.authCode");
        String placeholder = getContext("login.regist.step1.authCode.placeholder");
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.AUTH_CODE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();

        return borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString()));
    }
    
    private static String buildConfirmFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = "btnCancel";
        String context = getContext("login.regist.step1.btn.cancel");
        String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.DANGER, id, context);
        aligs.add(CssAlignType.LEFT);
        
        id = "btnBack";
        context = getContext("common.page.ahead");
        String comp2 = button().getBorder(IconSetType.TO_LEFT, CssClassType.WARNING, id, context);
        
        id = "btnNext";
        context = getContext("common.page.next");
        String comp3 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(divRow().get(CellWidthType.TWO_4_8, aligs, comp1, concactWithSpace(comp2, comp3)));
        
        return sb.toString();
    }
}
