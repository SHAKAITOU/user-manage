package cn.caam.gs.app.common.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.AcceptFileType;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.LoginAccountType;
import cn.caam.gs.common.enums.OnInputMode;
import cn.caam.gs.common.enums.OrderType;
import cn.caam.gs.common.enums.PageModeType;
import cn.caam.gs.common.enums.ReFundStatusType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.TabSet;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelNumberSet;
import cn.caam.gs.common.html.element.bs5.LabelRadioGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet.LabelSelectGroupSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.util.ImageUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T101MUserExtend;
import cn.caam.gs.domain.tabledef.impl.T105MUserCard;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;

/**
 * View helper.
 */
@Component
public class UserDetailViewHelper extends HtmlViewBaseHelper {
    
    // base url
    public static final String URL_BASE = "/userDetail";  
    // init url
    public static final String URL_USER_DETAIL_INIT = UrlConstants.INIT;
    
 // init url
    public static final String URL_USER_DETAIL_FROM_ADMIN_INIT = "/adminInit";
    
    //edit url
    public static final String URL_USER_DETAIL_EDIT = UrlConstants.EDIT;
    
    public static final String URL_USER_DETAIL_ADD_INIT = "/addInit";
    public static final String URL_USER_DETAIL_ADD      = UrlConstants.ADD;
    
    public static final String URL_USER_DETAIL_DELETE = UrlConstants.DELETE;
    
    public static final String URL_USER_DETAIL_PRINT = UrlConstants.PRINT;
    public static final String URL_USER_DETAIL_DOWNLOAD = UrlConstants.DOWNLOAD;
    public static final String URL_USER_DETAIL_MEMBER_INFO = "/memberInfo";
    
    public static final String USER_DETAIL_JS_CLASS          = "UserDetail";
    public static final String USER_DETAIL_FORM_NAME         = "userDetailForm";
    public static final String PREFIX_NAME                   = "userInfo.user.";
    public static final String PREFIX_EXTEND_NAME            = "userInfo.userExtend.";
    public static final String PREFIX_USER_CARD_NAME         = "userInfo.userCard.";
    
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
    
    public static final String HIDE_ITEM_PAGE_MODE_TYPE      = "mode_type";
    
    public static final String TAB_ID                         = "detailTab";
    public static final String TAB_TITLE_USER_ID              = "userTab";
    public static final String TAB_TITLE_ORDER_ID             = "orderTab";
    public static final String TAB_TITLE_USER_CARD_ID         = "userCardTab";
    public static final String TAB_BODY_USER_ID               = "userTabBody";
    public static final String TAB_BODY_ORDER_ID              = "orderTabBody";
    public static final String TAB_BODY_USER_CARD_ID          = "userCardTabBody";
    
    public static final String ORDER_LIST_TABLE_ID            = "orderListTable";
    public static final String ORDER_LIST_REFRESH_BODY_ID     = "orderListRefreshBody";
    
    public static final int    IMG_WIDTH                     = 250;
    public static final int    IMG_HEIGHT                    = 250;
    public static final int    HEADER_HEIGHT                 = 360;
    public static final int    SEARCH_PANEL_HEIGHT           = 90;
    
    public static final int PHONE_TD_HEIGHT = 70;
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    public static ViewData getMainPage(
            HttpServletRequest request, 
            UserDetailForm userDetailForm,
            OrderListOutput orderListOutput,
            PageModeType pageModeType) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder()
                .pageContext(pageModeType == PageModeType.EDIT_BY_ADMIN ?
                				getAdminMainPageContext(request, userDetailForm, orderListOutput, pageModeType) :getDetailContext(request, userDetailForm, pageModeType))
                .jsClassName(USER_DETAIL_JS_CLASS)
                .dataMap(dataMap).build();
        
        return viewData;
    }

    public static String getDetailContext(
            HttpServletRequest request, 
            UserDetailForm userDetailForm, PageModeType pageModeType) {
        
//        @SuppressWarnings("unchecked")
//        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
//                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer body = new StringBuffer();
        
        if (PageModeType.VIEW != pageModeType) {
        	 // hidden
            // ----------hidden------[
            body.append(setDetailHidden(request, userDetailForm, pageModeType));
            // ----------hidden------]
            // ----------hidden------[
        	body.append(setBreadCrumb());
        	// ----------hidden------[
        }
        // body
        // ----------body------[
        body.append(setUserCardPanel(request, userDetailForm, pageModeType));
//        body.append(buildBaseDetailBody(fixedValueMap, userDetailForm, pageModeType));
//        body.append(DivChevronSet.builder().idUp(HIDE_BASE_PANEL_BTN_ID).idDown(SHOW_BASE_PANEL_BTN_ID).build().html());
//        body.append(buildSelfDetailBody(fixedValueMap, userDetailForm, pageModeType));
//        body.append(DivChevronSet.builder().idUp(HIDE_SELF_PANEL_BTN_ID).idDown(SHOW_SELF_PANEL_BTN_ID).build().html());
//        body.append(buildExtendDetailBody(request,fixedValueMap, userDetailForm, pageModeType));
        // ----------body------]
       
        if (PageModeType.VIEW != pageModeType) {
        	return getForm(USER_DETAIL_FORM_NAME, body.toString());
        }else {
        	return body.toString();
        }
        
    }
    
    public static String setUserCardPanel(
            HttpServletRequest request, 
            UserDetailForm userDetailForm, PageModeType pageModeType) {
        
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer body = new StringBuffer();
        
        // ----------body------[
        body.append(buildBaseDetailBody(fixedValueMap, userDetailForm, pageModeType));
        body.append(DivChevronSet.builder().idUp(HIDE_BASE_PANEL_BTN_ID).idDown(SHOW_BASE_PANEL_BTN_ID).build().html());
        body.append(buildSelfDetailBody(fixedValueMap, userDetailForm, pageModeType));
        body.append(DivChevronSet.builder().idUp(HIDE_SELF_PANEL_BTN_ID).idDown(SHOW_SELF_PANEL_BTN_ID).build().html());
        body.append(buildExtendDetailBody(request,fixedValueMap, userDetailForm, pageModeType));
//        // ----------body------]
        
        if (PageModeType.VIEW != pageModeType) {
	        // --bottom btn--
	        body.append(divRow().cellBlank(5));
	        body.append(buildFooter(request));
	        body.append(divRow().cellBlank(5));
	        // --bottom btn--
        }
        
        return body.toString();
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("user.menu.group1"), getContext("user.menu.group1.button1")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    private static String setDetailHidden(HttpServletRequest request, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String value     = nonNull(userDetailForm.getUserInfo().getUser().getId());
        sb.append(hidden().get(name, value));
        sb.append(hidden().get(HIDE_ITEM_PAGE_MODE_TYPE, pageModeType.getKey()));
        return sb.toString();
    }
    
    private static String buildBaseDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildBaseDetail(fixedValueMap, userDetailForm, pageModeType));
        return borderCard().withTitleNoScroll(BASE_PANEL_CARD_ID, CssClassType.WARNING, "", 
                getContext("userDetail.card1"),
                divContainer().get(sb.toString()));
    }
    
    private static String buildBaseDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        //------row1----------[
        //会员号(M/TYYMMDDHHmmSSR2)入力値
        ColumnInfoForm clmForm = T105MUserCard.getColumnInfo(T105MUserCard.COL_USER_CODE);
        String name      = clmForm.getPageName(PREFIX_USER_CARD_NAME);
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = !Objects.isNull(userDetailForm.getUserInfo().getUserCard()) ? nonNull(userDetailForm.getUserInfo().getUserCard().getUserCode()):"";
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).disabled(PageModeType.INSERT_BY_ADMIN != pageModeType)
                .notBlank(PageModeType.INSERT_BY_ADMIN == pageModeType)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .onInputMode(OnInputMode.NUMBERS_UPPERLETTERS)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //会员类型(F0002) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getUserType());
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> userTypeList = fixedValueMap.get(FixedValueType.USER_TYPE);
        for (FixValueInfo fValueInfo : userTypeList) {
//            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        	 if (fValueInfo.getValueObj().getValue().equals(UserType.PERSON.getKey())) {
                 for (MFixedValue fValue : fValueInfo.getSubList()) {
                	 if (pageModeType == PageModeType.INSERT_BY_ADMIN && fValue.getValue().equals(UserType.PERSON_GANSU.getKey())) {
                		continue;
                	 }
                	 radios.add(new HtmlRadio(fValue.getValue(), fValue.getName()));
                 }
                 break;
             }
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value)
                .disabled(PageModeType.INSERT_BY_ADMIN != pageModeType && PageModeType.EDIT_BY_ADMIN != pageModeType)
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
        value = LocalDateUtility.formatDateZH(nonNull(nonNull(userDetailForm.getUserInfo().getUser().getRegistDate())));
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .disabled(PageModeType.INSERT_BY_ADMIN != pageModeType && PageModeType.EDIT_BY_ADMIN != pageModeType)
                .placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        //有效结束日期(yyyy-MM-dd HH 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = LocalDateUtility.formatDateZH(nonNull(nonNull(userDetailForm.getUserInfo().getUser().getValidEndDate())));
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).disabled(PageModeType.INSERT_BY_ADMIN != pageModeType && PageModeType.EDIT_BY_ADMIN != pageModeType)
                .placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        contextList = new ArrayList<String>();
        
        return sb.toString();
    }
    
    private static String buildSelfDetailBody(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildSelfDetail(fixedValueMap, userDetailForm, pageModeType));
        
        return borderCard().withTitleNoScroll(SELF_PANEL_CARD_ID, CssClassType.SUCCESS, "", 
                getContext("userDetail.card2"),
                divContainer().get(sb.toString()));
    }
    
    private static String buildSelfDetail(Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        
        //boolean disabled = PageModeType.INSERT_BY_ADMIN != pageModeType ;
        boolean disabled = false;
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        boolean isAdminEdit = PageModeType.INSERT_BY_ADMIN == pageModeType || PageModeType.EDIT_BY_ADMIN == pageModeType;
        String id = "";
        String context = "";
//        String comp1 = "";
        // bottom button
        if (!(PageModeType.VIEW ==pageModeType || PageModeType.INSERT_BY_ADMIN == pageModeType)) {
//	        id = BTN_OPEN_EDIT_BASE;
//	        context = getContext("common.page.openEdit");
//	        comp1 = button().getBorder(IconSetType.CHECK, CssClassType.DANGER, id, context);
//	        id = BTN_CLOSE_EDIT_BASE;
//	        context = getContext("common.page.closeEdit");
//	        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);1
//	
//	        aligs.add(CssAlignType.LEFT);
//	        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
//	        sb.append(divRow().cellBlank(5));
	        
        	id = "btnTop";
            context = getContext("userDetail.moveTop");
            String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.INFO, "", context, "", id);
            
	        id = "btnOk";
	        context = getContext("common.page.ok");
	        String comp2 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, "", context, "", id);
	        aligs.add(CssAlignType.LEFT);
	        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
	        sb.append(divRow().cellBlank(5));
        }
        
        List<String> contextList = new ArrayList<String>();

        //------row1----------[
        
        //会员名称(max70)
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
        String name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = nonNull(userDetailForm.getUserInfo().getUser().getName());
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
                .notBlank(true).maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //性别(F0001) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_SEX);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        value = nonNull(userDetailForm.getUserInfo().getUser().getSex());
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> sexList = fixedValueMap.get(FixedValueType.SEX);
        for (FixValueInfo fValueInfo : sexList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelRadioGroupSet.builder()
                .id(id).name(name).labelName(labelName).selectedValue(value)
                .radios(radios).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getBirth());
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .placeholder(placeholder).notBlank(!isAdminEdit).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        //民族(F0005) 入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_NATIONALITY);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getNationality());
        radios = new ArrayList<HtmlRadio>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> nationalityList = fixedValueMap.get(FixedValueType.NATIONALITY);
        for (FixValueInfo fValueInfo : nationalityList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getPolitical());
        radios = new ArrayList<>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> politicalList = fixedValueMap.get(FixedValueType.POLITICAL);
        for (FixValueInfo fValueInfo : politicalList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        
        //学历(F0004) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_EDU_DEGREE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getEduDegree());
        radios = new ArrayList<>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> eduDegreeList = fixedValueMap.get(FixedValueType.EDU_DEGREE);
        for (FixValueInfo fValueInfo : eduDegreeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getBachelor());
        radios = new ArrayList<>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> bachelorList = fixedValueMap.get(FixedValueType.BACHELOR);
        for (FixValueInfo fValueInfo : bachelorList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //职务(F0007) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_POSITION);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getPosition());
        radios = new ArrayList<>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> positionList = fixedValueMap.get(FixedValueType.POSITION);
        for (FixValueInfo fValueInfo : positionList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getEmployer());
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
                .notBlank(!isAdminEdit).maxlength(GlobalConstants.EMPLOYER_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //单位性质(F0008) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getEmployerType());
        radios = new ArrayList<>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> employerTypeList = fixedValueMap.get(FixedValueType.EMPLOYER_TYPE);
        for (FixValueInfo fValueInfo : employerTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getJobTitle());
        radios = new ArrayList<>();
        List<FixValueInfo> jobTitleList = fixedValueMap.get(FixedValueType.JOB_TITLE);
        Map<HtmlRadio, List<HtmlRadio>> radioMap = new LinkedHashMap<HtmlRadio, List<HtmlRadio>>();
        radioMap.put(getDefaultHtmlRadio(labelName), null);
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
                .radioMap(radioMap).selectedValue(value).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectGroupSetType.WITH_LABEL).build().html());
        

        //证件类型(F0010) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_CERTIFICATE_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getCertificateType());
        radios = new ArrayList<>();
        List<FixValueInfo> certificateTypeList = fixedValueMap.get(FixedValueType.CERTIFICATE_TYPE);
        for (FixValueInfo fValueInfo : certificateTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getCertificateCode());
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
                .notBlank(!isAdminEdit).maxlength(GlobalConstants.CERTIFICATE_CODE_MAX_L).placeholder(placeholder)
                .onInputMode(OnInputMode.NUMBERS_UPPERLETTERS)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //所在地区(F0011) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_AREA);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getArea());
        radios = new ArrayList<>();
        List<FixValueInfo> areaList = fixedValueMap.get(FixedValueType.AREA);
        radioMap = new LinkedHashMap<HtmlRadio, List<HtmlRadio>>();
        radioMap.put(getDefaultHtmlRadio(labelName), null);
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
                .radioMap(radioMap).selectedValue(value).disabled(disabled)
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
        value = nonNull(userDetailForm.getUserInfo().getUser().getPostalCode());
        contextList.add(LabelNumberSet.builder()
                .id(id).name(name).labelName(labelName).value(value).integerOnly(true).disabled(disabled)
                .notBlank(!isAdminEdit).maxlength(GlobalConstants.POST_CODE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //通讯地址(max128)
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_ADDRESS);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getAddress());
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
                .notBlank(!isAdminEdit).maxlength(GlobalConstants.ADDRESS_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row8----------]
        
        //------row9----------[
        contextList = new ArrayList<String>();
        
        //phone入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_PHONE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getPhone());
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .disabled(disabled).notBlank(true)
                .onInputMode(OnInputMode.NUMBERS_ONLY)
                .maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        //mail入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_MAIL);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getMail());
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .disabled(disabled).notBlank(false)
                .maxlength(GlobalConstants.MAIL_MAX_L).placeholder(placeholder)
                .onInputMode(OnInputMode.EMAIL)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row9----------]
        
        //------row10----------[
        contextList = new ArrayList<String>();
        
        //入会途径(F0012) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_MEMBERSHIP_PATH);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getMembershipPath());
        radios = new ArrayList<>();
        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> membershipPathList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        for (FixValueInfo fValueInfo : membershipPathList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(value).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //关注(F0012) 入力値
        clmForm  = T100MUser.getColumnInfo(T100MUser.COL_FOCUS_ON);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = nonNull(userDetailForm.getUserInfo().getUser().getFocusOn());
        radios = new ArrayList<>();
//        addDefaultHtmlRadio(radios, labelName);
        List<FixValueInfo> focusOnList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        for (FixValueInfo fValueInfo : focusOnList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValues(Arrays.asList(value.split(","))).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).outPutType(LabelSelectSetType.MULTI_SELECT_WITH_LABEL).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row10----------]
        contextList = new ArrayList<String>();
        
        return sb.toString();
    }
    
    private static String buildExtendDetailBody(HttpServletRequest request, Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildExtendDetail(request, fixedValueMap, userDetailForm, pageModeType));
        return borderCard().withTitleNoScroll("", CssClassType.INFO, "", 
                getContext("userDetail.card3"),
                divContainer().get(sb.toString()));
    }
    private static String buildExtendDetail(HttpServletRequest request, Map<FixedValueType, List<FixValueInfo>> fixedValueMap, UserDetailForm userDetailForm, PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        
        boolean isAdminEdit = PageModeType.INSERT_BY_ADMIN == pageModeType || PageModeType.EDIT_BY_ADMIN == pageModeType;
//        boolean disabled = PageModeType.INSERT_BY_ADMIN != pageModeType ;
        boolean disabled = false;
        List<CssAlignType> aligs = new ArrayList<>();
        String id = "";
        String context = "";
        String comp1 = "";
        // bottom button
        if (!(PageModeType.VIEW ==pageModeType || PageModeType.INSERT_BY_ADMIN == pageModeType)) {
//	        id = BTN_OPEN_EDIT_EXTEND;
//	        context = getContext("common.page.openEdit");
//	        comp1 = button().getBorder(IconSetType.CHECK, CssClassType.DANGER, id, context);
//	        id = BTN_CLOSE_EDIT_EXTEND;
//	        context = getContext("common.page.closeEdit");
//	        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);
//	    
//	        aligs.add(CssAlignType.LEFT);
//	        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
//	        sb.append(divRow().cellBlank(5));
        	
        	id = "btnTop";
            context = getContext("userDetail.moveTop");
            comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.INFO, "", context, "", id);
            
	        id = "btnOk";
	        context = getContext("common.page.ok");
	        String comp2 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, "", context, "", id);
	        aligs.add(CssAlignType.LEFT);
	        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
	        sb.append(divRow().cellBlank(5));
        }
        
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
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
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
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
                .maxlength(GlobalConstants.INTRODUCER1_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G6).build().html());
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row1----------]
        //------row2----------[
        aligs = new ArrayList<>();
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PHOTO);
        name        = clmForm.getPageName("") + "File";
        String idFile      = convertNameDotForId(name);
        String idFileOpen  = idFile + "Open";
        String idLbl       = idFile + "Lbl";
        String idFileName  = idFile + "Name";
        String idOldFile   = idFile + "Old";
        String idFileDownload   = idFile + "Download";
        String src = Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getPhoto());
        String ext = userDetailForm.getUserInfo().getUserExtend().getPhotoExt();
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, idFileOpen, context);
        String comp2 = "";
        if (!Strings.isBlank(src)) {
	        context = getContext("common.page.download");
	        comp2 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.INFO, idFileDownload, context);
        }
        contextList.add(concactWithSpace(comp1, comp2));
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row2----------]
        //------row3----------[
        
        contextList = new ArrayList<String>();
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
        idFileDownload   = idFile + "Download";
        src = resizeImageToBase64(request, userDetailForm.getUserInfo().getUserExtend().getEducationalAt(), userDetailForm.getUserInfo().getUserExtend().getEducationalAtExt());//Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getEducationalAt());
        ext = userDetailForm.getUserInfo().getUserExtend().getEducationalAtExt();
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, idFileOpen, context);
        comp2 = "";
        if (!Strings.isBlank(src)) {
	        context = getContext("common.page.download");
	        comp2 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.INFO, idFileDownload, context);
        }
        contextList.add(concactWithSpace(comp1, comp2));
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row4----------]

        //------row5----------[

        contextList = new ArrayList<String>();
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
        idFileDownload   = idFile + "Download";
        src = resizeImageToBase64(request, userDetailForm.getUserInfo().getUserExtend().getBachelorAt(), userDetailForm.getUserInfo().getUserExtend().getBachelorAtExt());//Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getBachelorAt());
        ext = userDetailForm.getUserInfo().getUserExtend().getBachelorAtExt();
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, idFileOpen, context);
        comp2 = "";
        if (!Strings.isBlank(src)) {
	        context = getContext("common.page.download");
	        comp2 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.INFO, idFileDownload, context);
        }
        contextList.add(concactWithSpace(comp1, comp2));
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row6----------]
        //------row7----------[

        contextList = new ArrayList<String>();
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
        idFileDownload   = idFile + "Download";
        src = resizeImageToBase64(request, userDetailForm.getUserInfo().getUserExtend().getVocationalAt(), userDetailForm.getUserInfo().getUserExtend().getVocationalAtExt());//Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getVocationalAt());
        ext = userDetailForm.getUserInfo().getUserExtend().getVocationalAtExt();
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, idFileOpen, context);
        comp2 = "";
        if (!Strings.isBlank(src)) {
	        context = getContext("common.page.download");
	        comp2 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.INFO, idFileDownload, context);
        }
        contextList.add(concactWithSpace(comp1, comp2));
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row8----------]
        //------row9----------[

        contextList = new ArrayList<String>();
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
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(!isAdminEdit).disabled(disabled)
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
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
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
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(false).disabled(disabled)
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
                .id(id).name(name).labelName(labelName).value(value)
                .notBlank(false).disabled(disabled)
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
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
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
                .id(id).name(name).labelName(labelName).value(value).disabled(disabled)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(4).grids(CssGridsType.G12).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row14----------]
        
        
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_APPLICATION_FORM);
        name        = clmForm.getPageName("") + "File";
        idFile      = convertNameDotForId(name);
        idFileOpen  = idFile + "Open";
        idLbl       = idFile + "Lbl";
        idFileName  = idFile + "Name";
        idOldFile   = idFile + "Old";
        idFileDownload   = idFile + "Download";
        String idFilePrint = idFile + "Print";
        
        src = resizeImageToBase64(request, userDetailForm.getUserInfo().getUserExtend().getApplicationForm(), userDetailForm.getUserInfo().getUserExtend().getApplicationFormExt());//Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getApplicationForm());
        ext = userDetailForm.getUserInfo().getUserExtend().getApplicationFormExt();
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, idFileOpen, context);
        comp2 = "";
        if (!Strings.isBlank(src)) {
	        context = getContext("common.page.download");
	        comp2 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.INFO, idFileDownload, context);
        }
        contextList.add(concactWithSpace(comp1, comp2));
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        
        //------row8----------]
        //------row9----------[

        contextList = new ArrayList<String>();
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
        
        contextList = new ArrayList<String>();
        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_APPLICATION_FORM2);
        name        = clmForm.getPageName("") + "File";
        idFile      = convertNameDotForId(name);
        idFileOpen  = idFile + "Open";
        idLbl       = idFile + "Lbl";
        idFileName  = idFile + "Name";
        idOldFile   = idFile + "Old";
        idFileDownload   = idFile + "Download";
//        idFilePrint = idFile + "Print";
        
        src = resizeImageToBase64(request, userDetailForm.getUserInfo().getUserExtend().getApplicationForm2(), userDetailForm.getUserInfo().getUserExtend().getApplicationFormExt2());//Base64.encodeBase64String(userDetailForm.getUserInfo().getUserExtend().getApplicationForm());
        ext = userDetailForm.getUserInfo().getUserExtend().getApplicationFormExt2();
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
                .fontSize(font).grids(CssGridsType.G6).build().html());
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G4).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, idFileOpen, context);
        comp2 = "";
        if (!Strings.isBlank(src)) {
	        context = getContext("common.page.download");
	        comp2 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.INFO, idFileDownload, context);
        }
        contextList.add(concactWithSpace(comp1, comp2));
    
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
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
        
        contextList = new ArrayList<String>();
    	context = getContext("userDetail.print");
        comp1 = button().getBorder(IconSetType.PRINT, CssClassType.SUCCESS, CssGridsType.G12, idFilePrint, context);
        contextList.add(comp1);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //------row15----------[
//        contextList = new ArrayList<String>();
//        clmForm     = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_APPLICATION_FORM);
//        name        = clmForm.getPageName("") + "File";
//        idFile      = convertNameDotForId(name);
//        idFileOpen  = idFile + "Open";
//        String idFilePrint = idFile + "Print";
//        idFileDownload = idFile + "Download";
//        idLbl       = idFile + "Lbl";
//        idFileName  = idFile + "Name";
//        idOldFile   = idFile + "Old";
//        
//        boolean isApplicationForm = userDetailForm.getUserInfo().getUserExtend().getApplicationForm() != null &&
//        		userDetailForm.getUserInfo().getUserExtend().getApplicationForm().length > 0;
//        
//        if (LoginInfoHelper.isUserLogin(request)) {
//	        value       = "";
//	        labelName   = clmForm.getLabelName();
//	        placeholder = clmForm.getPlaceholder();
//	        contextList.add(LabelInputSet.builder()
//	                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(disabled)
//	                .fontSize(font).grids(CssGridsType.G4).build().html());
//	        
//	        labelName   = getContext("userDetail.uploadApplicationForm");
//	        value       = "";
//	        contextList.add(LabelFileSet.builder()
//	                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).disabled(disabled).acceptFileType(AcceptFileType.IMAGE)
//	                .fontSize(font).grids(CssGridsType.G4).build().html());
//	        
//	        context = getContext("userDetail.print");
//	        comp1 = button().getBorder(IconSetType.PRINT, CssClassType.SUCCESS, idFilePrint, context);
//	        contextList.add(comp1);
//	        
//	        if (isApplicationForm) {
//		        context = getContext("userDetail.download");
//		        comp1 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.SUCCESS, idFileDownload, context);
//		        contextList.add(comp1);
//	        }else {
//	        	contextList.add("");
//	        }
//	        
//	        aligs = new ArrayList<>();
//	        aligs.add(CssAlignType.LEFT);
//	        aligs.add(CssAlignType.LEFT);
//	        aligs.add(CssAlignType.LEFT);
////  	        sb.append(divRow().get(CellWidthType.THREE_4_4_4, aligs, contextList.get(0), contextList.get(1), concactWithSpace(contextList.get(2),contextList.get(3))));
//  	        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
//        }
//        
//        if (LoginInfoHelper.isAdminLogin(request) && isApplicationForm) {
//        	contextList = new ArrayList<String>();
//        	context = getContext("userDetail.download");
//  	        comp1 = button().getBorder(IconSetType.DOWNLOAD, CssClassType.SUCCESS, CssGridsType.G12, idFileDownload, context);
//  	        contextList.add(comp1);
//  	        
//  	        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
//        }
//        //------row15----------]
        
        
        return sb.toString();
    }
    
    public static String getAdminMainPageContext(
            HttpServletRequest request, 
            UserDetailForm userDetailForm,
            OrderListOutput orderListOutput,
            PageModeType pageModeType) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        // ----------hidden------[
        sb.append(setDetailHidden(request, userDetailForm, pageModeType));
        // ----------hidden------]
        // ----------hidden------[
        sb.append(setBreadCrumb());
    	// ----------hidden------[
        String tabHtml = TabSet.builder()
                .id(TAB_ID)
            .tabTitleIds(new String [] {TAB_TITLE_USER_ID, TAB_TITLE_ORDER_ID, TAB_TITLE_USER_CARD_ID})
            .tabTitles(new String [] {
                    getContext("userDetail.user.tab.title"), 
                    getContext("userDetail.order.tab.title"),
                    getContext("userDetail.userCard.tab.title")})
            .tabBodyIds(new String [] {TAB_BODY_USER_ID, TAB_BODY_ORDER_ID, TAB_BODY_USER_CARD_ID})
            .tabBodys(new String [] {
            		setUserCardPanel(request, userDetailForm, pageModeType), 
            		setOrderCardPanel(request, orderListOutput),
            		setUserCertiPanel(request, userDetailForm)})
            .build().html();
        sb.append(tabHtml);
        
        return getForm(USER_DETAIL_FORM_NAME, sb.toString());
    }
    
    public static String setOrderCardPanel(
            HttpServletRequest request,
            OrderListOutput orderListOutput) {
        List<OrderInfo> orderList = orderListOutput.getOrderList();
        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
            // --col1--
            List<CssAlignType> aligs = new ArrayList<>();
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.RIGHT);
            String subRow1 = divRow().get(CellWidthType.THREE_6_3_3, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName(), 
                                                                        T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName(),
                                                                        getContext("order.amountTile"));
            String subRow2 = divRow().get(CellWidthType.THREE_6_3_3, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE).getLabelName(), 
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS).getLabelName(),
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS).getLabelName());
            List<GridFlexType> flexs = new ArrayList<>();
            flexs.add(GridFlexType.LEFT);
            flexs.add(GridFlexType.RIGHT);
            String subRow3 = divRow().getFlex(CellWidthType.TWO_6_6, flexs, T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE).getLabelName(),
                                                                        getContext("common.page.do"));
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2, subRow3));
            // --]
        } else {
            // --col1--
            ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
            String context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.LEFT, clmForm.getLabelName()));
            // --col2--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
           // --col3--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_AMOUNT);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col4--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col5--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col6--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col7--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col8--
            context         = getContext("common.page.do");
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
        }
        
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(orderList)) {
            for(int i=0; i<orderList.size(); i++) {
                OrderInfo orderInfo = orderList.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(orderInfo.getId()));
                TrSet tr = tr().row(properties);
                String orderTypeName = PTextSet.builder()
                        .context(orderInfo.getOrderTypeName())
                        .classType(OrderType.keyOf(orderInfo.getOrder().getOrderType()).getClassType()).build().html();
                String billStatusName = "";
                if (orderInfo.getOrder().getCheckStatus().equals(CheckStatusType.REFUSED.getKey())) {
                    billStatusName = PTextSet.builder()
                        .context(orderInfo.getRefundStatusName())
                        .classType(ReFundStatusType.keyOf(orderInfo.getOrder().getRefundStatus()).getClassType()).build().html();
                } else {
                    billStatusName = PTextSet.builder()
                        .context(orderInfo.getBillStatusName())
                        .classType(BillStatusType.keyOf(orderInfo.getOrder().getBillStatus()).getClassType()).build().html();
                }
                String checkStatusName = PTextSet.builder()
                        .context(orderInfo.getCheckStatusName())
                        .classType(CheckStatusType.keyOf(orderInfo.getOrder().getCheckStatus()).getClassType()).build().html();
                
                String amount = formatCurrencyZH(orderInfo.getOrder().getOrderAmount());
                String payAmount = formatCurrencyZH(orderInfo.getOrder().getPayAmount());
                String payDate = orderInfo.getOrder().getPayDate();
             // --col2--
                String btnContext = "";
                if (isPhoneMode(request)) {
                	if(Strings.isBlank(payAmount)) {
                		payAmount = StringUtility.padLeft(payAmount, 4, "-");
                	}
                    // --col1--
                    List<CssAlignType> aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String subRow1 = divRow().get(CellWidthType.THREE_6_3_3, aligs, orderInfo.getId(), orderInfo.getUserName(), amount+"/"+payAmount);
                    String subRow2 = divRow().get(CellWidthType.THREE_6_3_3, aligs, orderTypeName, checkStatusName, billStatusName);
                    List<GridFlexType> flexs = new ArrayList<>();
                    flexs.add(GridFlexType.LEFT);
                    flexs.add(GridFlexType.RIGHT);
                    String subRow3 = divRow().getFlex(CellWidthType.TWO_6_6, flexs, payDate, btnContext);
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2, subRow3));
                } else {
                    // --col1--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.LEFT, orderInfo.getId()));
                    // --col2--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, amount));
                    // --col3--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, payAmount));
                    // --col4--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, orderTypeName));
                    // --col5--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, checkStatusName));
                    // --col6--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, billStatusName));
                    // --col7--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, payDate));
                    // --col8--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, btnContext));
                }
                bodyList.add(tr);
            }
        }
        
        return borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 300,
        		table().get(ORDER_LIST_TABLE_ID, 200, headTr, bodyList));
    }
    
    public static String setUserCertiPanel(
            HttpServletRequest request, 
            UserDetailForm userDetailForm) {
    	return UserCertiCommonViewHelper.getDetailContext(request, userDetailForm);
    }
    
    
    
    private static String buildFooter(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button
        
        String id = "btnTop";
        String context = getContext("userDetail.moveTop");
        String comp1 = button().getBorder(IconSetType.TO_UP, CssClassType.INFO, "", context, "", id);

        id = "btnOk";
        context = getContext("common.page.ok");
//        String comp2 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, id, context);
        String comp2 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, "", context, "", id);
        
        id = "btnBack";
        context = getContext("common.page.btn.back");
        String comp3 = button().getBorder(IconSetType.BACK, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.RIGHT);
        if (LoginAccountType.USER.equals(LoginInfoHelper.getLoginAccountType(request))) {
        	sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        }else {
        	sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2, comp3)));
        }
        
        return sb.toString();
    }
    
    public static boolean isPhoneMode(HttpServletRequest request) {
	    int outWidth = LoginInfoHelper.getMediaWidth(request);
	    return outWidth > 800 ? false : true;
	}
    
    public static String resizeImageToBase64(HttpServletRequest request, byte[] bytes, String type) {
    	try {
    		return ImageUtil.resizeImageToBase64(bytes, 
    			isPhoneMode(request) ? GlobalConstants.IMAGE_RESIZE_WIDTH_MOBILE:GlobalConstants.IMAGE_RESIZE_WIDTH_PC,
    					isPhoneMode(request) ? GlobalConstants.IMAGE_RESIZE_HEIGHT_MOBILE:GlobalConstants.IMAGE_RESIZE_HEIGHT_PC, type);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static String getMemeberInfo(HttpServletRequest request) {
    	return "";
    }
    
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_user_detail_init",				URL_BASE + URL_USER_DETAIL_INIT);
        js.put("url_user_detail_from_admin_init",	URL_BASE + URL_USER_DETAIL_FROM_ADMIN_INIT);
        js.put("url_user_detail_add_init",			URL_BASE + URL_USER_DETAIL_ADD_INIT);
        js.put("url_user_detail_add",				URL_BASE + URL_USER_DETAIL_ADD);
        js.put("url_user_detail_edit",				URL_BASE + URL_USER_DETAIL_EDIT);
        js.put("url_user_detail_delete",			URL_BASE + URL_USER_DETAIL_DELETE);
        js.put("url_user_detail_print",				URL_BASE + URL_USER_DETAIL_PRINT);
        js.put("url_user_detail_download",			URL_BASE + URL_USER_DETAIL_DOWNLOAD);
        return js;
    }
    
}
