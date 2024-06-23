package cn.caam.gs.app.user.regist.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.user.login.view.LoginViewHelper;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateRangeInputSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelPasswordSet;
import cn.caam.gs.common.html.element.bs5.LabelRadioGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet.LabelSelectGroupSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateRangeInputSet.LabelDateRangeInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;

/**
 * View helper.
 */
@Component
public class RegistStep2ViewHelper extends HtmlViewBaseHelper {

    public static final String REGIST_JS_CLASS          = "RegistStep2";
    public static final String REGIST_CONFIRM_JS_CLASS  = "RegistStep2Confirm";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    public static ViewData getRegist2Page(HttpServletRequest request, RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(LoginViewHelper.REGIST_FORM_NAME, LoginViewHelper.REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist2Context(request, registForm)).jsClassName(REGIST_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }
    
    public static ViewData getRegist2ConfirmPage(RegistForm registForm) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(LoginViewHelper.REGIST_FORM_NAME, LoginViewHelper.REGIST_FORM_NAME);
        ViewData viewData = ViewData.builder().pageContext(getRegist2ConfirmContext(registForm)).jsClassName(REGIST_CONFIRM_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    private static String setRegist2Hidden(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        String name = "errorMsg";
        String value = Objects.nonNull(registForm) ? registForm.getErrorMsg() : "";
        sb.append(hidden().get(name, value));
        name = "stepStatus";
        sb.append(hidden().get(name, registForm.getStepStatus()));
        return sb.toString();
    }

    private static String getRegist2Context(HttpServletRequest request, RegistForm registForm) {

        StringBuffer body = new StringBuffer();

        // body
        body.append(buildInputBody(request, registForm));
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildFooter());
        body.append(divRow().cellBlank(5));

        return getForm(LoginViewHelper.REGIST_FORM_NAME, body.toString());
    }
    
    private static String buildInputBody(HttpServletRequest request, RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        
        sb.append(divRow().cellBlank(5));
        
        String prefix_name  = "user.";
        String prefix_label = T100MUser.TABLE_NAME + ".";
        String subfix_placeholder = ".placeholder";

        // ----------hidden------[
        sb.append(setRegist2Hidden(registForm));
        // ----------hidden------]
        List<String> contextList = new ArrayList<String>();

        //会員名入力値
        String property  = "name";
        String name      = prefix_name + property;
        String labelName = getContext(prefix_label + property);
        String placeholder = getContext(prefix_label + property + subfix_placeholder);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //password入力値
        property  = "password";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        placeholder = getContext(prefix_label + property + subfix_placeholder);
        String btnId = convertNameDotForId(name)+ "Show";
        contextList.add(LabelPasswordSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
                .buttonId(btnId).showIcon(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //password重复入力値
        String propertyRep  = "passwordRep";
        name      = prefix_name + propertyRep;
        labelName = getContext(prefix_label + propertyRep);
        placeholder = getContext(prefix_label + property + subfix_placeholder);
        btnId = convertNameDotForId(name)+ "Show";
        contextList.add(LabelPasswordSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
                .buttonId(btnId).showIcon(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //性别(F0001) 入力値
        property  = "sex";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> sexList = fixedValueMap.get(FixedValueType.SEX);
        for (FixValueInfo fValueInfo : sexList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelRadioGroupSet.builder()
                .name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SEX)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //出生年月入力値
        property  = "birth";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        placeholder = getContext(prefix_label + property + subfix_placeholder);
        contextList.add(LabelDateInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .placeholder(placeholder).notBlank(true)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //民族(F0005) 入力値
        property  = "nationality";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> nationalityList = fixedValueMap.get(FixedValueType.NATIONALITY);
        for (FixValueInfo fValueInfo : nationalityList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_NATIONALITY)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //政治面貌(F0003) 入力値
        property  = "political";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> politicalList = fixedValueMap.get(FixedValueType.POLITICAL);
        for (FixValueInfo fValueInfo : politicalList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_POLITICAL)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //学历(F0004) 入力値
        property  = "edu_degree";
        name      = prefix_name + "eduDegree";
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> eduDegreeList = fixedValueMap.get(FixedValueType.EDU_DEGREE);
        for (FixValueInfo fValueInfo : eduDegreeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_POLITICAL)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //学位(F0006) 入力値
        property  = "bachelor";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> bachelorList = fixedValueMap.get(FixedValueType.BACHELOR);
        for (FixValueInfo fValueInfo : bachelorList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_POLITICAL)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //职务(F0007) 入力値
        property  = "position";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> positionList = fixedValueMap.get(FixedValueType.POSITION);
        for (FixValueInfo fValueInfo : positionList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_POLITICAL)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //工作单位(max64)入力値
        property  = "employer";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        placeholder = getContext(prefix_label + property + subfix_placeholder);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .notBlank(true).maxlength(GlobalConstants.EMPLOYER_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //职称(F0009) 入力値
        property  = "job_title";
        name      = prefix_name + "jobTitle";
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> jobTitleList = fixedValueMap.get(FixedValueType.JOB_TITLE);
        Map<HtmlRadio, List<HtmlRadio>> radioMap = new LinkedHashMap<HtmlRadio, List<HtmlRadio>>();
        for (FixValueInfo fValueInfo : jobTitleList) {
            List<HtmlRadio> subList = new ArrayList<HtmlRadio>();
            if (fValueInfo.getSubList() != null && fValueInfo.getSubList().size() > 0) {
                for (MFixedValue mfixedValue : fValueInfo.getSubList()) {
                    subList.add(new HtmlRadio(mfixedValue.getValue(), mfixedValue.getName()));
                }
                radioMap.put(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()), subList);
            } else {
                radioMap.put(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()), null);
            }
        }
        contextList.add(LabelSelectGroupSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radioMap(radioMap).selectedValue(GlobalConstants.DFL_POLITICAL)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectGroupSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();

        //入会途径(F0012) 入力値
        property  = "membership_path";
        name      = prefix_name + "membershipPath";
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> membershipPathList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        for (FixValueInfo fValueInfo : membershipPathList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_MEMBERSHIP_PATH_GANSU)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //关注(F0012) 入力値
        property  = "focus_on";
        name      = prefix_name + "focusOn";
        labelName = getContext(prefix_label + property);
        radios = new ArrayList<>();
        List<FixValueInfo> focusOnList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        for (FixValueInfo fValueInfo : focusOnList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_MEMBERSHIP_PATH_GANSU)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        
        
        
        return borderCard().noTitleWithScroll("", CssClassType.WARNING, "", calculateDlgHeight(request), divContainer().get(sb.toString()));
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button
        String id = "btnCancel";
        String context = getContext("login.regist.step1.btn.cancel");
        String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.DANGER, id, context);
        aligs.add(CssAlignType.LEFT);
        
        id = "btnNext";
        context = getContext("common.page.next");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(divRow().get(CellWidthType.ONE, aligs, comp1, comp2));
        
        return sb.toString();
    }
    
    //confirm page
    private static String getRegist2ConfirmContext(RegistForm registForm) {

        StringBuffer body = new StringBuffer();

        // body
        body.append(buildConfirmBody(registForm));
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildConfirmFooter());
        body.append(divRow().cellBlank(5));

        return getForm(LoginViewHelper.REGIST_FORM_NAME, body.toString());
    }
    
    private static String buildConfirmBody(RegistForm registForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));

        // ----------hidden------[
        sb.append(setRegist2Hidden(registForm));
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
    
    private static int calculateDlgHeight(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - 220;
    }
}
