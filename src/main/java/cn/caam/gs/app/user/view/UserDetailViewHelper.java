package cn.caam.gs.app.user.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.GlobalConstants;
import cn.caam.gs.app.bean.ViewData;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.app.util.UrlConstants;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelRadioGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet.LabelSelectGroupSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.table.impl.g01master.T100MUser;
import cn.caam.gs.manage.dbmaintenance.table.impl.g01master.T101MUserExtend;

/**
 * View helper.
 */
@Component
public class UserDetailViewHelper extends HtmlViewBaseHelper {
    
    // base url
    public static final String URL_BASE = "/userDetail";  
    // init url
    public static final String URL_USER_DETAIL_INIT = UrlConstants.INIT;
    

    public static final String USER_DETAIL_JS_CLASS          = "UserDetail";
    public static final String USER_DETAIL_FORM_NAME         = "userDetailForm";
    public static final String PREFIX_NAME                   = "user.";
    public static final String PREFIX_EXTEND_NAME            = "userExtend.";
    
    public static final String BTN_OPEN_EDIT_BASE            = "btnOpenEditBase";
    public static final String BTN_CLOSE_EDIT_BASE           = "btnCloseEditBase";
    public static final String BTN_OPEN_EDIT_EXTEND          = "btnOpenEditExtend";
    public static final String BTN_CLOSE_EDIT_EXTEND         = "btnCloseEditExtend";
    public static final String BTN_OPEN_PHOTO                = "btnOpenPhoto";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    public static ViewData getMainPage(HttpServletRequest request, UserInfo userInfo) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder()
                .pageContext(getDetailContext(request, userInfo))
                .jsClassName(USER_DETAIL_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    private static String getDetailContext(HttpServletRequest request, UserInfo userInfo) {
        
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer body = new StringBuffer();
        
        // hidden
        // ----------hidden------[
        body.append(setDetailHidden(userInfo));
        // ----------hidden------]
        // ----------hidden------[
        body.append(divRow().cellBlank(5));
        body.append(setBreadCrumb());
        // ----------hidden------[
        // body
        // ----------body------[
        body.append(buildBaseDetailBody(fixedValueMap, userInfo));
        body.append(buildSelfDetailBody(fixedValueMap, userInfo));
        body.append(buildExtendDetailBody(fixedValueMap, userInfo));
        // ----------body------]
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildFooter());
        body.append(divRow().cellBlank(5));
        // --bottom btn--

        return getForm(USER_DETAIL_FORM_NAME, body.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("user.menu.group1"), getContext("user.menu.group1.button1")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    private static String setDetailHidden(UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String value     = userInfo.getUser().getId();
        sb.append(hidden().get(name, value));
        return sb.toString();
    }
    
    private static String buildBaseDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildBaseDetail(fixedValueMap, userInfo));
        return borderCard().withTitleNoScroll("", CssClassType.WARNING, "", 
                getContext("userDetail.card1"),
                divContainer().get(sb.toString()));
    }
    
    private static String buildBaseDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        //------row1----------[
        //会员号(M/TYYMMDDHHmmSSR2)入力値
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        String name      = clmForm.getPageName(PREFIX_NAME) + "Show";
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = userInfo.getUser().getId();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //会员类型(F0002) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getUserType();
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> userTypeList = fixedValueMap.get(FixedValueType.USER_TYPE);
        for (FixValueInfo fValueInfo : userTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row1----------]
        //------row2----------[
        contextList = new ArrayList<String>();
        //入会时间(yyyy-MM-dd HH 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_REGIST_DATE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getRegistDate();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //有效结束日期(yyyy-MM-dd HH 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getValidEndDate();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        return sb.toString();
    }
    
    private static String buildSelfDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildSelfDetail(fixedValueMap, userInfo));
        
        return borderCard().withTitleNoScroll("", CssClassType.SUCCESS, "", 
                getContext("userDetail.card2"),
                divContainer().get(sb.toString()));
    }

    private static String buildSelfDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_OPEN_EDIT_BASE;
        String context = getContext("common.page.openEdit");
        String comp1 = button().getBorder(IconSetType.CHECK, CssClassType.DANGER, id, context);
        id = BTN_CLOSE_EDIT_BASE;
        context = getContext("common.page.closeEdit");
        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.LEFT);
        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        sb.append(divRow().cellBlank(5));
        
        List<String> contextList = new ArrayList<String>();

        //------row1----------[
        
        //会员名称(max64)
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
        String name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = userInfo.getUser().getName();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //性别(F0001) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_SEX);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        value = userInfo.getUser().getSex();
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> sexList = fixedValueMap.get(FixedValueType.SEX);
        for (FixValueInfo fValueInfo : sexList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelRadioGroupSet.builder()
                .id(id).name(name).labelName(labelName).selectedValue(value)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).build().html());

        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row1----------]
        //------row2----------[
        contextList = new ArrayList<String>();

        
        //出生年月入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_BIRTH);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getBirth();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .placeholder(placeholder).notBlank(true)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        //民族(F0005) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_NATIONALITY);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getNationality();
        radios = new ArrayList<>();
        List<FixValueInfo> nationalityList = fixedValueMap.get(FixedValueType.NATIONALITY);
        for (FixValueInfo fValueInfo : nationalityList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        //------row3----------[
        contextList = new ArrayList<String>();

        
        //政治面貌(F0003) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_POLITICAL);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getPolitical();
        radios = new ArrayList<>();
        List<FixValueInfo> politicalList = fixedValueMap.get(FixedValueType.POLITICAL);
        for (FixValueInfo fValueInfo : politicalList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        
        //学历(F0004) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_EDU_DEGREE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getEduDegree();
        radios = new ArrayList<>();
        List<FixValueInfo> eduDegreeList = fixedValueMap.get(FixedValueType.EDU_DEGREE);
        for (FixValueInfo fValueInfo : eduDegreeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row3----------[
        //------row4----------]
        contextList = new ArrayList<String>();

        
        //学位(F0006) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_BACHELOR);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getBachelor();
        radios = new ArrayList<>();
        List<FixValueInfo> bachelorList = fixedValueMap.get(FixedValueType.BACHELOR);
        for (FixValueInfo fValueInfo : bachelorList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //职务(F0007) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_POSITION);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getPosition();
        radios = new ArrayList<>();
        List<FixValueInfo> positionList = fixedValueMap.get(FixedValueType.POSITION);
        for (FixValueInfo fValueInfo : positionList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row4----------]
        //------row5----------[
        contextList = new ArrayList<String>();

        
        //工作单位(max64)入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getEmployer();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.EMPLOYER_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //单位性质(F0008) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getEmployerType();
        radios = new ArrayList<>();
        List<FixValueInfo> employerTypeList = fixedValueMap.get(FixedValueType.EMPLOYER_TYPE);
        for (FixValueInfo fValueInfo : employerTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row5----------]
        //------row6----------[
        contextList = new ArrayList<String>();

        
        //职称(F0009) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_JOB_TITLE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getJobTitle();
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
                .id(id).name(name).labelName(labelName)
                .radioMap(radioMap).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectGroupSetType.WITH_LABEL).build().html());
        

        //证件类型(F0010) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_CERTIFICATE_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getCertificateType();
        radios = new ArrayList<>();
        List<FixValueInfo> certificateTypeList = fixedValueMap.get(FixedValueType.CERTIFICATE_TYPE);
        for (FixValueInfo fValueInfo : certificateTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row6----------]
        //------row7----------[
        contextList = new ArrayList<String>();

        //证件号码(max18)
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_CERTIFICATE_CODE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getCertificateCode();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.CERTIFICATE_CODE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //所在地区(F0011) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_AREA);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getArea();
        radios = new ArrayList<>();
        List<FixValueInfo> areaList = fixedValueMap.get(FixedValueType.AREA);
        radioMap = new LinkedHashMap<HtmlRadio, List<HtmlRadio>>();
        for (FixValueInfo fValueInfo : areaList) {
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
                .id(id).name(name).labelName(labelName)
                .radioMap(radioMap).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectGroupSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row7----------]
        //------row8----------[
        contextList = new ArrayList<String>();

        //邮政编码(max10) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_POSTAL_CODE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getPostalCode();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.POST_CODE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //通讯地址(max128)
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_ADDRESS);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getAddress();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.ADDRESS_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row8----------]
        
        //------row9----------[
        contextList = new ArrayList<String>();
        
        //入会途径(F0012) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_MEMBERSHIP_PATH);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getMembershipPath();
        radios = new ArrayList<>();
        List<FixValueInfo> membershipPathList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        for (FixValueInfo fValueInfo : membershipPathList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //关注(F0012) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_FOCUS_ON);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = userInfo.getUser().getFocusOn();
        radios = new ArrayList<>();
        List<FixValueInfo> focusOnList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        for (FixValueInfo fValueInfo : focusOnList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row9----------]
        contextList = new ArrayList<String>();
        
        return sb.toString();
    }
    
    private static String buildExtendDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildExtendDetail(fixedValueMap, userInfo));
        return borderCard().withTitleNoScroll("", CssClassType.INFO, "", 
                getContext("userDetail.card3"),
                divContainer().get(sb.toString()));
    }
    private static String buildExtendDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button
    
        String id = BTN_OPEN_EDIT_EXTEND;
        String context = getContext("common.page.openEdit");
        String comp1 = button().getBorder(IconSetType.CHECK, CssClassType.DANGER, id, context);
        id = BTN_CLOSE_EDIT_EXTEND;
        context = getContext("common.page.closeEdit");
        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);
    
        aligs.add(CssAlignType.LEFT);
        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        sb.append(divRow().cellBlank(5));
        
        List<String> contextList = new ArrayList<String>();
    
        //------row1----------[
        
        //介绍人1(max32)
        ColumnInfoForm clmForm = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_INTRODUCER1);
        String name      = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id               = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = userInfo.getUserExtend().getIntroducer1();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.INTRODUCER1_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //介绍人2(max32)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_INTRODUCER2);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userInfo.getUserExtend().getIntroducer2();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.INTRODUCER1_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row1----------]
        //------row2----------[
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PHOTO);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME) + "File";
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userInfo.getUserExtend().getIntroducer2();
        contextList.add(LabelFileSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        id = BTN_OPEN_PHOTO;
        context = clmForm.getLabelName();
        comp1 = button().getBorder(IconSetType.CHECK, CssClassType.INFO, CssGridsType.G6, id, context);
        contextList.add(comp1);
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row2----------]
        
        return sb.toString();
    }
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = "btnOut";
        String context = getContext("login.regist.step1.btn.cancel");
        String comp1 = button().getBorder(IconSetType.TO_LEFT, CssClassType.DANGER, id, context);

        id = "btnNext";
        context = getContext("login.regist.step1.btn.next");
        String comp2 = button().getBorder(IconSetType.TO_RIGHT, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_user_detail_init",      URL_BASE + URL_USER_DETAIL_INIT);
        return js;
    }
    
}
