package cn.caam.gs.app.admin.usersearch.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.UserExpiredType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.html.element.CheckBoxSet;
import cn.caam.gs.common.html.element.CheckBoxSet.CheckBoxSetType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.IconSet;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.html.element.bs5.SpanTextSet;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;

@Component
public class AdminUserSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.USER_LIST;
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
    
    public static final String URL_C_GROWING = UrlConstants.GROWING;
    
    public static final String PREFIX_NAME                    = "user.";
	
    public static final String MAIN_JS_CLASS                  = "AdminUserSearch";
    public static final String USER_LIST_FORM_NAME            = "userSearchForm";
    public static final String USER_LIST_CHECK_ALL_ID         = "user_check_all";
    public static final String USER_CHECK_PREF_ID             = "user_check_";
    public static final String USER_LIST_TABLE_ID             = "userListTable";
    public static final String USER_LIST_REFRESH_BODY_ID      = "userListRefreshBody";
    public static final String SEARCH_BTN_ID                  = "searchBtn";
    public static final String SEARCH_PANEL_ID                = "searchPanel";
    public static final String SHOW_SEARCH_PANEL_BTN_ID       = "showSearchPanelBtn";
    public static final String HIDE_SEARCH_PANEL_BTN_ID       = "hideSearchPanelBtn";
    public static final String TABLE_HEIGHT_WHEN_HIDE_SEARCH  = "tableHeightWhenHideSearch";
    public static final String TABLE_HEIGHT_WHEN_SHOW_SEARCH  = "tableHeightWhenShowSearch";
    
    public static final String TABLE_BTN_RESETPW              = "restPw";
    public static final String TABLE_BTN_DETAIL               = "detail";
    public static final String SHOW_MORE_BTN_ID               = "showMore";
    public static final String HID_HIDE_SEARCH                = "hideSearch";
    public static final String HID_LIMIT                      = "limit";
    public static final String HID_OFFSET                     = "offset";
    public static final int    HEADER_HEIGHT                  = 450;
    public static final int    SEARCH_PANEL_HEIGHT            = 180;
    
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            UserSearchForm pageForm,
            UserListOutput userListOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, pageForm, userListOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    public static ViewData refeshTable(
            HttpServletRequest request, 
            UserSearchForm pageForm,
            UserListOutput userListOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));
        ViewData viewData = ViewData.builder()
                .pageContext(setCardForTable(request, pageForm, userListOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            UserSearchForm pageForm,
            UserListOutput userListOutput) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb());
        sb.append("<div id='" + SEARCH_PANEL_ID + "'>");
        sb.append(setCardForSearchPanel(request));
        sb.append("</div>");
        sb.append(DivChevronSet.builder().idUp(HIDE_SEARCH_PANEL_BTN_ID).idDown(SHOW_SEARCH_PANEL_BTN_ID).build().html());
        sb.append("<div id='" + USER_LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, pageForm, userListOutput));
        sb.append("</div>");
        return getForm(USER_LIST_FORM_NAME, sb.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("menu.group5"), getContext("menu.group5.button4")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header SearchPanel -----------------
    
    private static String setCardForSearchPanel(HttpServletRequest request) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleNoScroll("", CssClassType.SUCCESS, "", 
                "",
                setSearchPanel(request)));
        
        return sbBody.toString();
    }
    private static String setSearchPanel(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer sbBody = new StringBuffer();
        List<HtmlRadio> radios = new ArrayList<>();

        //-----row 1-------------[
        List<String> contextList = new ArrayList<String>();

        //name入力値
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //phone選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_PHONE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //employer入力値
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.EMPLOYER_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        
        //有效状态入力値
        name      = "validStatus";
        id        = convertNameDotForId(name);
        labelName = getContext("admin.userList.validStatus");
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (UserExpiredType userExpiredType : UserExpiredType.values()) {
            radios.add(new HtmlRadio(userExpiredType.getKey(), getContext(userExpiredType.getMsg())));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));

        //-----row 1-------------]
        
        
        //-----row 2-------------[
        contextList = new ArrayList<String>();

        //regist_date選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_REGIST_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        placeholder = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getPlaceholder();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name      = clmForm.getPageName("") + "To";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.end");
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        //valid_end_date選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name      = clmForm.getPageName("") + "To";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.end");
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 2-------------]

        //-----row 3-------------[
        contextList = new ArrayList<String>();
        
        //id選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .maxlength(GlobalConstants.USER_ID_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //user_type選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> userTypeList = fixedValueMap.get(FixedValueType.USER_TYPE);
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : userTypeList) {
            if (fValueInfo.getValueObj().getValue().equals(UserType.PERSON.getKey())) {
                for (MFixedValue fValue : fValueInfo.getSubList()) {
                    radios.add(new HtmlRadio(fValue.getValue(), fValue.getName()));
                }
                break;
            }
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
 
        //edu_degree選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_EDU_DEGREE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> edu_degreeList = fixedValueMap.get(FixedValueType.EDU_DEGREE);
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : edu_degreeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());

        //political選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_POLITICAL);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> politicalList = fixedValueMap.get(FixedValueType.POLITICAL);
        
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : politicalList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //search btn
        labelName = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(labelName).isBorderOnly(true)
                .grids(CssGridsType.G1).outPutType(ButtonSetType.GRID_STYLE).gridFlexType(GridFlexType.RIGHT)
                .iconSet(IconSet.builder().type(IconSetType.SEARCH).css(IconSetCss.NOMAL_10).build())
                .build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    //--------------------body card table -----------------
    private static String setCardForTable(
            HttpServletRequest request, 
            UserSearchForm pageForm,
            UserListOutput userListOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(setHidden(pageForm));
        cardBody.append(setShowMore(request, userListOutput));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, userListOutput.getUserList()));
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                getContext("admin.userList.table.title"),
                divRow().cellBlank(5),cardBody.toString()));
        
        return sbBody.toString();
    }
    

    private static String setHidden(UserSearchForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(hidden().get(HID_HIDE_SEARCH,  String.valueOf(pageForm.isHideSearch())));
        sb.append(hidden().get(HID_LIMIT,  String.valueOf(pageForm.getLimit())));
        sb.append(hidden().get(HID_OFFSET, String.valueOf(pageForm.getOffset())));
        return sb.toString();
    }

    private static String setShowMore(
            HttpServletRequest request, 
            UserListOutput userListOutput) {
        StringBuffer sbBody = new StringBuffer();
        
        
        List<CssAlignType> aligs = new ArrayList<>();
        String id = SHOW_MORE_BTN_ID;
        String context = getContext("common.page.showMore");
        String comp1 = button().getBorder(IconSetType.BAR, CssClassType.INFO, id, context, 
                userListOutput.getUserList().size() >= userListOutput.getCount());
        
        context = getContext("common.page.btn.close");
        context = "[" + userListOutput.getUserList().size() + "/" + userListOutput.getCount() + "]";
        String comp2 = SpanTextSet.builder().classType(CssClassType.CONTEXT).fontSize(font).context(context).build().html();
        aligs.add(CssAlignType.LEFT);
        sbBody.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    private static String setUserListTable(HttpServletRequest request, 
            List<UserInfo> userList) {
        int [] widths = new int[] {
            40,  //check
            150, //会员ID
            140, //会员名称
            100, //会员类型
            120, //手机号
            150, //电子邮箱
            250, //工作单位
            200, //入会时间
            200, //有效结束日期
            120, //有效状态
            200  //操作
        };
        int index = 0;
        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        // --col1--
        String context = CheckBoxSet.builder()
                .id(USER_LIST_CHECK_ALL_ID).name(USER_LIST_CHECK_ALL_ID)
                .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col2--
        context         = T100MUser.getColumnInfo(T100MUser.COL_ID).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col3--
        context         = T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col4--
        context         = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col5--
        context         = T100MUser.getColumnInfo(T100MUser.COL_PHONE).getLabelName();;
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col6--
        context         = T100MUser.getColumnInfo(T100MUser.COL_MAIL).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col7--
        context         = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col8--
        context         = T100MUser.getColumnInfo(T100MUser.COL_REGIST_DATE).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col9--
        context         = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getLabelName();
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col10--
        context         = getContext("admin.userList.validStatus");
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --col11--
        context         = getContext("common.page.do");
        headTr.addTh(th().get(widths[index++], CssAlignType.CENTER, context));
        // --]
        
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(userList)) {
            for(int i=0; i<userList.size(); i++) {
                index = 0;
                UserInfo userInfo = userList.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(userInfo.getId()));
                TrSet tr = tr().row(properties);
                // --col1--
                context = CheckBoxSet.builder()
                        .id(USER_CHECK_PREF_ID + i).name(USER_CHECK_PREF_ID)
                        .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
                tr.addTd(td().get(widths[index++], CssAlignType.CENTER, context));
                // --col2--
                context = nonNull(userInfo.getId());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.CENTER, context));
                // --col3--
                context = nonNull(userInfo.getUser().getName());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.CENTER, context));
                // --col4--
                context = nonNull(userInfo.getUserTypeName());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.CENTER, context));
                // --col5--
                context = nonNull(userInfo.getUser().getPhone());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.CENTER, context));
                // --col6--
                context = nonNull(userInfo.getUser().getMail());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.LEFT, context));
                // --col7--
                context = nonNull(userInfo.getUser().getEmployer());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.LEFT, context));
                // --col8--
                context = nonNull(userInfo.getUser().getRegistDate());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.CENTER, context));
                // --col9--
                context = nonNull(userInfo.getUser().getValidEndDate());
                tr.addTd(td().withTrimWidth(widths[index++], CssAlignType.CENTER, context));
                // --col10--
                context = nonNull(getValidStatus(userInfo.getUser().getValidEndDate()));
                tr.addTd(td().get(widths[index++], CssAlignType.CENTER, context));
                // --col11--
                context = button().forTableBorderNameLeft(IconSetType.REFRESH, CssClassType.SUCCESS, 
                        "", getContext("admin.userList.btn.resetPw"), userInfo.getId(), TABLE_BTN_RESETPW);
                context += "&nbsp;&nbsp;";
                context += button().forTableBorderNameLeft(IconSetType.DETAIL, CssClassType.INFO, 
                        "", getContext("admin.userList.btn.detail"), userInfo.getId(), TABLE_BTN_DETAIL);
                tr.addTd(td().get(widths[index++], CssAlignType.CENTER, context));
                
                bodyList.add(tr);
            }
        }
        
        return table().get(USER_LIST_TABLE_ID, calcTableWidth(widths), calcTableHeightWhenShowSearch(request), headTr, bodyList);
    }
    
    private static String getValidStatus(String validEndDateStr) {
        UserExpiredType sts = UserExpiredType.VALID;
        if (!StringUtils.isEmpty(validEndDateStr)) {
            LocalDateTime validEndDate = LocalDateUtility.parseLocalDateTime(validEndDateStr, DateTimePattern.UUUUHMMHDDHHQMIQSS);
            LocalDateTime currentDt = LocalDateTime.now();
            if (validEndDate.isAfter(currentDt)) {
                if (LocalDateUtility.getDateDifference(validEndDate.toLocalDate(), currentDt.toLocalDate()) <= 5) {
                    sts = UserExpiredType.EXPIRED_SOON;
                }
            } else {
                if (LocalDateUtility.getDateDifference(currentDt.toLocalDate(), validEndDate.toLocalDate()) >= 60) {
                    sts = UserExpiredType.VARY_EXPIRED;
                } else {
                    sts = UserExpiredType.EXPIRED;
                }
            }
        }
        return PTextSet.builder().classType(sts.getClassType())
                .context(getContext(sts.getMsg())).build().html();
    }
    
    private static int calcTableHeightWhenShowSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT;
    }
    
    private static int calcTableHeightWhenHideSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT + SEARCH_PANEL_HEIGHT;
    }
    
    private static int calcTableWidth(int [] widths) {
        int totalWidth = 0;
        for (int width : widths) {
            totalWidth += width;
        }
        return totalWidth;
    }
	
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",              URL_BASE + URL_C_INIT);
		js.put("url_user_list",         URL_BASE + URL_C_SEARCH);
		js.put("url_user_list_growing", URL_BASE + URL_C_GROWING);

		return js;
	}
}
