package cn.caam.gs.app.user.login.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.user.UrlConstants;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.SexType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.domain.tabledef.impl.T100MUser;

/**
 * View helper.
 */
@Component
public class LoginViewHelper extends HtmlViewBaseHelper {

    // init url
    public static final String URL_BASE = UrlConstants.INDEX;
    // init url
    public static final String URL_C_LOGIN_INIT  = UrlConstants.INIT;
    // init url
    public static final String URL_C_USER_LOGIN  = UrlConstants.USER_LOGIN;
    // logout url
    public static final String URL_C_LOGOUT      =  UrlConstants.LOGOUT;

    // init url
    public static final String URL_C_USER_REGIST = UrlConstants.USER_REGIST + UrlConstants.INIT;
    // init url
    public static final String URL_C_USER_REGIST_COMMIT = UrlConstants.USER_REGIST + UrlConstants.COMMIT;
    // init url
    public static final String URL_C_USER_REGIST_COMMIT_CONFIRM = UrlConstants.USER_REGIST + UrlConstants.CONFIRM;

    public static final String URL_C_USER_REGIST_STEP2 = UrlConstants.USER_REGIST + "/step2";
    public static final String URL_C_USER_REGIST_STEP3 = UrlConstants.USER_REGIST + "/step3";
    public static final String URL_C_USER_REGIST_STEP_FINAL = UrlConstants.USER_REGIST + "/stepFinal";

    public static final String HTML_INDEX      = "user/index";
    public static final String HTML_LOGIN      = "user/loginBlock";
    //MENU
    public static final String HTML_MENU_MENU  = "user/menu";

    public static final String REGIST_FORM_NAME = "registForm";
    public static final String REGIST_JS_CLASS = "RegistStep1";
    public static final String REGIST2_JS_CLASS = "RegistStep2";
    public static final String REGIST3_JS_CLASS = "RegistStep3";
    
    public static final String STEP_STS_STEP1_INIT   = "STEP1";
    public static final String STEP_STS_STEP1_COMMIT = "STEP1_COMMIT";
    public static final String STEP_STS_STEP1_OUT    = "STEP1_OUT";
    public static final String STEP_STS_STEP1_NG     = "STEP1_NG";
    public static final String STEP_STS_STEP2_INIT   = "STEP2";

    public static final int MAIL_LENGTH = 40;
    public static final int AUTH_CODE_LENGTH = 6;
    public static final int NAME_LENGTH = 20;
    public static final int PW_LENGTH = 8;

    public static final int ADDRESS1_LENGTH = 50;
    public static final int ADDRESS2_LENGTH = 50;
    public static final int POST_CODE_LENGTH = 10;
    public static final int TEL_LENGTH = 20;
    public static final int FAX_LENGTH = 20;

    public static ViewData getRegist1Page(RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(REGIST_FORM_NAME, REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist1Context(registForm)).jsClassName(REGIST_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    public static ViewData getRegist2Page(RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(REGIST_FORM_NAME, REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist2Context(registForm)).jsClassName(REGIST2_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    public static ViewData getRegist3Page(RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(REGIST_FORM_NAME, REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist3Context(registForm)).jsClassName(REGIST3_JS_CLASS)
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
        StringBuffer sb = new StringBuffer();

        sb.append(divRow().cellBlank(5));

        String prefix_name = REGIST_FORM_NAME + ".";
        String prefix_label = T100MUser.TABLE_NAME + ".";
        CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
        // ----------hidden------[
        sb.append(setRegist1Hidden(registForm));
        // ----------hidden------]
        List<String> contextList = new ArrayList<String>();

        //name入力値
        String property  = "mail";
        String name      = prefix_name + property;
        String labelName = getContext(prefix_label + property);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).notBlank(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));

        StringBuffer body = new StringBuffer();

        body.append(borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString())));
        // --bottom btn--[
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = "btnBack";
        String context = getContext("common.page.cancel");
        String comp1 = button().getBorder(IconSetType.TO_LEFT, CssClassType.DANGER, id, context);

        id = "btnRegist";
        context = getContext("common.page.next");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        body.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        body.append(divRow().cellBlank(5));

        return getForm(REGIST_FORM_NAME, body.toString());
    }

    private static String setRegist2Hidden(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(setRegist1Hidden(registForm));
        String name = "users.mail";
        String value = Objects.nonNull(registForm) ? registForm.getUser().getMail() : "";
        sb.append(hidden().get(name, value));
        return sb.toString();
    }

    private static String getRegist2Context(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();

        sb.append(divRow().cellBlank(5));

        int labelWidth = 120;
        String labelName = "";
        String name = "";
        String value = "";
        String component_no1 = "";
        // ----------hidden------[
        sb.append(setRegist2Hidden(registForm));
        // ----------hidden------]

        name = "authCode";
        labelName = getContext("login.regist.authCode");
        value = Objects.nonNull(registForm) ? registForm.getUser().getName() : "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, AUTH_CODE_LENGTH,
                AUTH_CODE_LENGTH + getContext("common.placeholder.minNum"), true);
        sb.append(divRow().get(CellWidthType.TWO_8_4, component_no1));

        StringBuffer body = new StringBuffer();

        body.append(borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString())));
        // --bottom btn--[
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button
        String id = "btnBack1";
        String context = getContext("common.page.cancel");
        String comp1 = button().getBorder(IconSetType.TO_LEFT, CssClassType.DANGER, id, context);

        id = "btnRegist2";
        context = getContext("common.page.next");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);
        aligs.add(CssAlignType.RIGHT);
        body.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        body.append(divRow().cellBlank(5));

        return getForm(REGIST_FORM_NAME, body.toString());
    }

    private static String setRegist3Hidden(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(setRegist2Hidden(registForm));
        return sb.toString();
    }

    private static String getRegist3Context(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();

        sb.append(divRow().cellBlank(5));

        int labelWidth = 120;
        String labelName = "";
        String name = "";
        String value = "";
        String component_no1 = "";
        // ----------hidden------[
        sb.append(setRegist3Hidden(registForm));
        // ----------hidden------]

        name = "users.familyName";
        labelName = getContext("userAdd.item.familyName");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, NAME_LENGTH,
                NAME_LENGTH + getContext("common.placeholder.minChar"), true);
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        name = "users.surname";
        labelName = getContext("userAdd.item.surname");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, NAME_LENGTH,
                NAME_LENGTH + getContext("common.placeholder.minChar"), true);
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        name = "users.familyNameJa";
        labelName = getContext("userAdd.item.familyNameJa");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, NAME_LENGTH,
                NAME_LENGTH + getContext("common.placeholder.minChar"), true);
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        name = "users.surnameJa";
        labelName = getContext("userAdd.item.surnameJa");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, NAME_LENGTH,
                NAME_LENGTH + getContext("common.placeholder.minChar"), true);
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        // -----------------row 2----------------[
        name = "newPw";
        labelName = getContext("login.regist.pw");
        value = "";
        component_no1 = password().withLabel(labelWidth, labelName, name, value, PW_LENGTH,
                PW_LENGTH + getContext("common.placeholder.minAbc"));
        sb.append(divRow().get(CellWidthType.ONE, component_no1));
        // -----------------row 2----------------]

        // -----------------row 3----------------[
        name = "users.pw";
        labelName = getContext("login.regist.pw2");
        value = "";
        component_no1 = password().withLabel(labelWidth, labelName, name, value, PW_LENGTH,
                PW_LENGTH + getContext("common.placeholder.minAbc"));
        sb.append(divRow().get(CellWidthType.ONE, component_no1));
        // -----------------row 3----------------]

        name = "usersExtend.sex";
        labelName = getContext("userAdd.item.sex");
        value = String.valueOf(SexType.MAN.getId());
        component_no1 = radio().withLabel(labelWidth, labelName, name, value, SexType.values());
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        name = "usersExtend.birthday";
        labelName = getContext("userAdd.item.birthday");
        String formatDt = "";
        component_no1 = dateInput().withLabel(labelWidth, labelName, name, formatDt);
        sb.append(divRow().get(CellWidthType.TWO_10_2, component_no1));

        String id = "btnSearchPostCode";
        String foot = button().forTableBorder(IconSetType.SEARCH, CssClassType.INFO, id, "", "searchPostCode");

        name = "usersExtend.postCode";
        labelName = getContext("store.postCode");
        value = "";
        component_no1 = text().withLabelWithFoot(labelWidth, labelName, name, value, foot, CssAlignType.LEFT,
                POST_CODE_LENGTH, POST_CODE_LENGTH + getContext("common.placeholder.minNum"), false);
        sb.append(divRow().get(CellWidthType.TWO_10_2, component_no1));

        name = "usersExtend.address1";
        labelName = getContext("store.address1");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, ADDRESS1_LENGTH,
                ADDRESS1_LENGTH + getContext("common.placeholder.minChar"), false);
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        name = "usersExtend.address2";
        labelName = getContext("store.address2");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, ADDRESS2_LENGTH,
                ADDRESS2_LENGTH + getContext("common.placeholder.minChar"), false);
        sb.append(divRow().get(CellWidthType.ONE, component_no1));

        name = "usersExtend.tel";
        labelName = ("store.tel");
        value = "";
        component_no1 = text().withLabel(labelWidth, labelName, name, value, CssAlignType.LEFT, TEL_LENGTH,
                TEL_LENGTH + getContext("common.placeholder.minNum"), true);
        sb.append(divRow().get(CellWidthType.TWO_10_2, component_no1));
        // -----------------row 6----------------]

        StringBuffer body = new StringBuffer();

        body.append(borderCard().noTitleNoScroll("", CssClassType.WARNING, "", divContainer().get(sb.toString())));
        // --bottom btn--[
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button
        id = "btnBack3";
        String context = getContext("common.page.cancel");
        String comp1 = button().getBorder(IconSetType.TO_LEFT, CssClassType.DANGER, id, context);

        id = "btnRegist3";
        context = getContext("common.page.ok");
        String comp2 = button().getBorder(IconSetType.THUMBS_UP, CssClassType.SUCCESS, id, context);
        aligs.add(CssAlignType.RIGHT);
        body.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        body.append(divRow().cellBlank(5));

        return getForm(REGIST_FORM_NAME, body.toString());
    }

    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_login",           URL_BASE);
        js.put("url_login_init",      URL_BASE + URL_C_LOGIN_INIT);
        js.put("url_user_login",      URL_BASE + URL_C_USER_LOGIN);
        js.put("url_logout",          URL_BASE + URL_C_LOGOUT);
        js.put("url_userRegist",      URL_BASE + URL_C_USER_REGIST);
        js.put("url_userRegist_commit",  URL_BASE + URL_C_USER_REGIST_COMMIT);
        js.put("url_userRegist_confirm", URL_BASE + URL_C_USER_REGIST_COMMIT_CONFIRM);
        
        js.put("url_userRegist2",        URL_BASE + URL_C_USER_REGIST_STEP2);
        js.put("url_userRegist3",        URL_BASE + URL_C_USER_REGIST_STEP3);
        js.put("url_userRegistFinal",    URL_BASE + URL_C_USER_REGIST_STEP_FINAL);

        js.put("userRegist_title",         getContext("login.regist.title"));
        js.put("userRegist_confirm_title", getContext("login.regist.step1.confirmTitle"));
        js.put("userRegist2_title", getContext("login.regist.title2"));
        js.put("userRegist3_title", getContext("login.regist.title3"));

        // btn
        js.put("btn_logout", getContext("login.panel.btn.logout"));
        // title
        js.put("title", getContext("login.title"));
        // label
        js.put("label_pw", getContext("login.regist.pw"));
        js.put("label_pw2", getContext("login.regist.pw2"));
        js.put("label_tel", ("store.tel"));
        // msg
        js.put("msg_stop_regist_confirm", getContext("login.confirm.cancelRegist.msg"));
        js.put("msg_regist_pwNotSameError", getContext("login.regist.pwNotSameError"));
        js.put("msg_regist_confirm", getContext("login.regist.confirm.msg"));

        js.put("msg_regist_success", getContext("login.regist.success"));
        js.put("msg_logout_confirm", getContext("login.logout.confirm.msg"));

        return js;
    }
}
