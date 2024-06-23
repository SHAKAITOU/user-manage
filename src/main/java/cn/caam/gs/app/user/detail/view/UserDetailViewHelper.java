package cn.caam.gs.app.user.detail.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.user.detail.form.UserDetailForm;
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
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelRadioGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet.LabelSelectGroupSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T101MUserExtend;

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
    public static final String PREFIX_NAME                   = "userInfo.user.";
    public static final String PREFIX_EXTEND_NAME            = "userInfo.userExtend.";
    
    public static final String BTN_OPEN_EDIT_BASE            = "btnOpenEditBase";
    public static final String BTN_CLOSE_EDIT_BASE           = "btnCloseEditBase";
    public static final String BTN_OPEN_EDIT_EXTEND          = "btnOpenEditExtend";
    public static final String BTN_CLOSE_EDIT_EXTEND         = "btnCloseEditExtend";
    public static final String BTN_OPEN_PHOTO                = "btnOpenPhoto";
    public static final String SHOW_BASE_PANEL_BTN_ID        = "showBasePanelBtn";
    public static final String HIDE_BASE_PANEL_BTN_ID        = "hideBasePanelBtn";
    public static final String SHOW_SELF_PANEL_BTN_ID        = "showSelfPanelBtn";
    public static final String HIDE_SELF_PANEL_BTN_ID        = "hideSelfPanelBtn";
    
    public static final String BASE_PANEL_CARD_ID            = "basePanelCard";
    public static final String SELF_PANEL_CARD_ID            = "selfPanelCard";
    public static final String HID_HIDE_SEARCH               = "hideSearch";
    public static final int    IMG_WIDTH                     = 250;
    public static final int    IMG_HEIGHT                    = 250;
    public static final int    HEADER_HEIGHT                 = 360;
    public static final int    SEARCH_PANEL_HEIGHT           = 90;
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    public static ViewData getMainPage(
            HttpServletRequest request, 
            UserDetailForm userDetailForm) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder()
                .pageContext(getDetailContext(request, userDetailForm))
                .jsClassName(USER_DETAIL_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    private static String getDetailContext(
            HttpServletRequest request, 
            UserDetailForm userDetailForm) {
        
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer body = new StringBuffer();
        
        // hidden
        // ----------hidden------[
        body.append(setDetailHidden(userDetailForm));
        // ----------hidden------]
        // ----------hidden------[
        body.append(setBreadCrumb());
        // ----------hidden------[
        // body
        // ----------body------[
        body.append(buildBaseDetailBody(fixedValueMap, userDetailForm));
        body.append(DivChevronSet.builder().idUp(HIDE_BASE_PANEL_BTN_ID).idDown(SHOW_BASE_PANEL_BTN_ID).build().html());
        body.append(buildSelfDetailBody(fixedValueMap, userDetailForm));
        body.append(DivChevronSet.builder().idUp(HIDE_SELF_PANEL_BTN_ID).idDown(SHOW_SELF_PANEL_BTN_ID).build().html());
        body.append(buildExtendDetailBody(fixedValueMap, userDetailForm));
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

    private static String setDetailHidden(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String value     = userDetailForm.getUserInfo().getUser().getId();
        sb.append(hidden().get(name, value));
        return sb.toString();
    }
    
    private static String buildBaseDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildBaseDetail(fixedValueMap, userDetailForm));
        return borderCard().withTitleNoScroll(BASE_PANEL_CARD_ID, CssClassType.WARNING, "", 
                getContext("userDetail.card1"),
                divContainer().get(sb.toString()));
    }
    
    private static String buildBaseDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        //------row1----------[
        //会员号(M/TYYMMDDHHmmSSR2)入力値
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        String name      = clmForm.getPageName(PREFIX_NAME) + "Show";
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = userDetailForm.getUserInfo().getUser().getId();
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
        value = userDetailForm.getUserInfo().getUser().getUserType();
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
        value = userDetailForm.getUserInfo().getUser().getRegistDate();
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
        value = userDetailForm.getUserInfo().getUser().getValidEndDate();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        return sb.toString();
    }
    
    private static String buildSelfDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildSelfDetail(fixedValueMap, userDetailForm));
        
        return borderCard().withTitleNoScroll(SELF_PANEL_CARD_ID, CssClassType.SUCCESS, "", 
                getContext("userDetail.card2"),
                divContainer().get(sb.toString()));
    }

    private static String buildSelfDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm) {
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
        String value = userDetailForm.getUserInfo().getUser().getName();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //性别(F0001) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_SEX);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        value = userDetailForm.getUserInfo().getUser().getSex();
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
        value = userDetailForm.getUserInfo().getUser().getBirth();
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
        value = userDetailForm.getUserInfo().getUser().getNationality();
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
        value = userDetailForm.getUserInfo().getUser().getPolitical();
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
        value = userDetailForm.getUserInfo().getUser().getEduDegree();
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
        value = userDetailForm.getUserInfo().getUser().getBachelor();
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
        value = userDetailForm.getUserInfo().getUser().getPosition();
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
        value = userDetailForm.getUserInfo().getUser().getEmployer();
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
        value = userDetailForm.getUserInfo().getUser().getEmployerType();
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
        value = userDetailForm.getUserInfo().getUser().getJobTitle();
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
        value = userDetailForm.getUserInfo().getUser().getCertificateType();
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
        value = userDetailForm.getUserInfo().getUser().getCertificateCode();
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
        value = userDetailForm.getUserInfo().getUser().getArea();
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
        value = userDetailForm.getUserInfo().getUser().getPostalCode();
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
        value = userDetailForm.getUserInfo().getUser().getAddress();
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
        value = userDetailForm.getUserInfo().getUser().getMembershipPath();
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
        value = userDetailForm.getUserInfo().getUser().getFocusOn();
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
    
    private static String buildExtendDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildExtendDetail(fixedValueMap, userDetailForm));
        return borderCard().withTitleNoScroll("", CssClassType.INFO, "", 
                getContext("userDetail.card3"),
                divContainer().get(sb.toString()));
    }
    private static String buildExtendDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm) {
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
        String value = userDetailForm.getUserInfo().getUserExtend().getIntroducer1();
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
        value       = userDetailForm.getUserInfo().getUserExtend().getIntroducer2();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.INTRODUCER1_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row1----------]
        //------row2----------[
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PHOTO);
        name        = clmForm.getPageName("") + "File";
        String idFile      = convertNameDotForId(name);
        String idFileOpen  = idFile + "Open";
        String idLbl       = idFile + "Lbl";
        String idFileName  = idFile + "Name";
        String idOldFile   = idFile + "Old";
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G2, idFileOpen, context);
        contextList.add(comp1);
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row2----------]
        //------row3----------[
        
        contextList = new ArrayList<String>();
        String src = Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getPhoto());
        String ext = userDetailForm.getUserInfo().getUserExtend().getPhotoExt();
        context = LabelImageSet.builder()
                .id(idOldFile)
                .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT)
                .grids(CssGridsType.G6)
                .base64String(src).extent(ext).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sb.append(divRow().cellBlank(5));
        //------row3----------]
        //------row4----------[
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_EDUCATIONAL_AT);
        name        = clmForm.getPageName("") + "File";
        idFile      = convertNameDotForId(name);
        idFileOpen  = idFile + "Open";
        idLbl       = idFile + "Lbl";
        idFileName  = idFile + "Name";
        idOldFile   = idFile + "Old";
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G2, idFileOpen, context);
        contextList.add(comp1);
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row4----------]

        //------row5----------[

        contextList = new ArrayList<String>();
        src = Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getEducationalAt());
        ext = userDetailForm.getUserInfo().getUserExtend().getEducationalAtExt();
        if (src == null) {
            src = getNoImgDataString();
            ext = "jpeg";
        }
        context = LabelImageSet.builder()
                .id(idOldFile)
                .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT)
                .grids(CssGridsType.G6)
                .base64String(src).extent(ext).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sb.append(divRow().cellBlank(5));
        //------row5----------]
        
        //------row6----------[
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_BACHELOR_AT);
        name        = clmForm.getPageName("") + "File";
        idFile      = convertNameDotForId(name);
        idFileOpen  = idFile + "Open";
        idLbl       = idFile + "Lbl";
        idFileName  = idFile + "Name";
        idOldFile   = idFile + "Old";
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G2, idFileOpen, context);
        contextList.add(comp1);
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row6----------]
        //------row7----------[

        contextList = new ArrayList<String>();
        src = Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getBachelorAt());
        ext = userDetailForm.getUserInfo().getUserExtend().getBachelorAtExt();
        if (src == null) {
            src = getNoImgDataString();
            ext = "jpeg";
        }
        context = LabelImageSet.builder()
                .id(idOldFile)
                .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT)
                .grids(CssGridsType.G6)
                .base64String(src).extent(ext).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sb.append(divRow().cellBlank(5));
        //------row7----------]
        
        //------row8----------[
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_VOCATIONAL_AT);
        name        = clmForm.getPageName("") + "File";
        idFile      = convertNameDotForId(name);
        idFileOpen  = idFile + "Open";
        idLbl       = idFile + "Lbl";
        idFileName  = idFile + "Name";
        idOldFile   = idFile + "Old";
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G2, idFileOpen, context);
        contextList.add(comp1);
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row8----------]
        //------row9----------[

        contextList = new ArrayList<String>();
        src = Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getVocationalAt());
        ext = userDetailForm.getUserInfo().getUserExtend().getVocationalAtExt();
        if (src == null) {
            src = getNoImgDataString();
            ext = "jpeg";
        }
        context = LabelImageSet.builder()
                .id(idOldFile)
                .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT)
                .grids(CssGridsType.G6)
                .base64String(src).extent(ext).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sb.append(divRow().cellBlank(5));
        //------row9----------]
        
        //------row10----------[

        contextList = new ArrayList<String>();
        
        //专业(max64)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_MAJOR);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userDetailForm.getUserInfo().getUserExtend().getMajor();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).notBlank(true)
                .maxlength(GlobalConstants.MAJOR_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //研究方向(max36)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_RESEARCH_DIR);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userDetailForm.getUserInfo().getUserExtend().getResearchDir();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.RESEARCH_DIR_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row10----------]
        
        //------row11----------[

        contextList = new ArrayList<String>();

        //主要学习经历(max250)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_LEARN_EXPERIENCE);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userDetailForm.getUserInfo().getUserExtend().getLearnExperience();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName).value(value).notBlank(true)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(4).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row11----------]
        //------row12----------[
        contextList = new ArrayList<String>();
        
        
        //主要工作经历(max250)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_WORK_EXPERIENCE);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userDetailForm.getUserInfo().getUserExtend().getWorkExperience();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName).value(value).notBlank(true)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(4).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row12----------]

        //------row13----------[
        contextList = new ArrayList<String>();
        
        
        //代表性论文及著作(max250)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PAPERS);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userDetailForm.getUserInfo().getUserExtend().getPapers();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(4).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row13----------]
        
        //------row14----------[
        contextList = new ArrayList<String>();
        //获得科技奖励及荣誉情况(max250)
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_HONORS);
        name        = clmForm.getPageName(PREFIX_EXTEND_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value       = userDetailForm.getUserInfo().getUserExtend().getHonors();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(4).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row14----------]
        
        
        return sb.toString();
    }
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = "btnOk";
        String context = getContext("common.page.ok");
        String comp1 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(divRow().get(CellWidthType.ONE, aligs, comp1));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_user_detail_init",      URL_BASE + URL_USER_DETAIL_INIT);
        return js;
    }
    
}
