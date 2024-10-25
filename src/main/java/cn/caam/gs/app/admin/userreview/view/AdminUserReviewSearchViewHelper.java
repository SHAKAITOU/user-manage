package cn.caam.gs.app.admin.userreview.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.html.HtmlPageLinkedHelper;
import cn.caam.gs.common.html.element.CheckBoxSet;
import cn.caam.gs.common.html.element.CheckBoxSet.CheckBoxSetType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.PaginationHolder;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;

@Component
public class AdminUserReviewSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.USER_REVIEW_LIST;
    
    public static final String URL_C_INIT = UrlConstants.INIT;
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
    public static final String URL_C_GROWING = UrlConstants.GROWING;
    
    public static final String SEARCH_MODE_WAIT_LIST          = "waitList";
    public static final String SEARCH_MODE_REVIEWED_LIST      = "reviewedList";
    
    public static final String PREFIX_NAME                    = "user.";
	
    public static final String MAIN_JS_CLASS                  = "AdminUserReviewSearch";
    public static final String USER_LIST_FORM_NAME            = "userReviewSearchForm";
    public static final String USER_LIST_CHECK_ALL_ID         = "user_check_all";
    public static final String USER_CHECK_PREF_ID             = "user_check_";
    public static final String USER_LIST_TABLE_ID             = "userListTable";
    public static final String USER_LIST_REFRESH_BODY_ID      = "userListRefreshBody";
    public static final String SEARCH_MODE                    = "searchMode";
    public static final String SEARCH_BTN_ID                  = "searchBtn";
    public static final String WAITLIST_BTN_ID                = "waitListBtn";
    public static final String REVIEWEDLIST_BTN_ID            = "reviewedListBtn";
    public static final String REVIEWMULTIBTN_BTN_ID          = "reviewMultiBtn";
    public static final String SEARCH_PANEL_ID                = "searchPanel";
    public static final String SHOW_SEARCH_PANEL_BTN_ID       = "showSearchPanelBtn";
    public static final String HIDE_SEARCH_PANEL_BTN_ID       = "hideSearchPanelBtn";
    public static final String TABLE_HEIGHT_WHEN_HIDE_SEARCH  = "tableHeightWhenHideSearch";
    public static final String TABLE_HEIGHT_WHEN_SHOW_SEARCH  = "tableHeightWhenShowSearch";
    
    public static final String TABLE_BTN_REVIEW               = "review";
    public static final String TABLE_BTN_DETAIL               = "detail";
    public static final String SHOW_MORE_BTN_ID               = "showMore";
    public static final String HID_HIDE_SEARCH                = "hideSearch";
    public static final String HID_LIMIT                      = "limit";
    public static final String HID_OFFSET                     = "offset";
    public static final String HID_SELECTEDUSERID             = "selectedUserId";
    public static final int    HEADER_HEIGHT                  = 450;
    public static final int    SEARCH_PANEL_HEIGHT            = 180;
    
    public static final int PHONE_TD_HEIGHT                   = 50;
    
    /** 頁LINK接頭辞ID */
    public static final String PAGE_LINK_ID_PREFIX = "userPageLinkIdPrefix";
    
	
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
                .pageContext(getMainPageContext(request, pageForm, userListOutput))
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
        sb.append(setCardForSearchPanel(request, pageForm));
        sb.append("</div>");
        sb.append(DivChevronSet.builder().idUp(HIDE_SEARCH_PANEL_BTN_ID).idDown(SHOW_SEARCH_PANEL_BTN_ID).build().html());
        sb.append("<div id='" + USER_LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, pageForm, userListOutput));
        sb.append("</div>");
        return getForm(USER_LIST_FORM_NAME, sb.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("menu.group1"), getContext("menu.group1.button1")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header SearchPanel -----------------
    
    private static String setCardForSearchPanel(HttpServletRequest request, UserSearchForm pageForm) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleNoScroll("", CssClassType.SUCCESS, "", 
                "",
                setSearchPanel(request, pageForm)));
        
        return sbBody.toString();
    }
    private static String setSearchPanel(HttpServletRequest request, UserSearchForm pageForm) {
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer sbBody = new StringBuffer();

        //-----row 1-------------[
        List<String> contextList = new ArrayList<String>();

        //name入力値
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = pageForm != null && pageForm.getUser() != null ? nonNull(pageForm.getUser().getName()):"";
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //phone選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_PHONE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = pageForm != null && pageForm.getUser() != null ? nonNull(pageForm.getUser().getPhone()):"";
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //search btn
        labelName = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(labelName).isBorderOnly(true)
                .grids(CssGridsType.G1).outPutType(ButtonSetType.GRID_STYLE).gridFlexType(GridFlexType.LEFT)
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
        cardBody.append(setShowMore(request, pageForm, userListOutput));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, pageForm, userListOutput.getUserList()));
        String cardTitle = getContext("admin.userReviewList.table.waitList.title");
        if (!SEARCH_MODE_WAIT_LIST.equals(pageForm.getSearchMode())) {
        	cardTitle = getContext("admin.userReviewList.table.reviewedList.title");
        }
        
        int startIndex = pageForm.getLimit()*(pageForm.getUserPageLinkIdPrefixIndex());
        int endIndex = startIndex + userListOutput.getUserList().size();
        cardTitle += userListOutput.getCount() > 0 ? 
                "(" + (startIndex + 1) + " - " + endIndex + "/" + 
                userListOutput.getCount() + ")" : "";
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                cardTitle,
                divRow().cellBlank(5),cardBody.toString()));
        sbBody.append(getPageLinked(request, pageForm, userListOutput));
        sbBody.append(divRow().cellBlank(5));
        return sbBody.toString();
    }
    

    private static String setHidden(UserSearchForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(hidden().get(HID_HIDE_SEARCH,  String.valueOf(pageForm.isHideSearch())));
        sb.append(hidden().get(HID_LIMIT,  String.valueOf(pageForm.getLimit())));
        sb.append(hidden().get(HID_OFFSET, String.valueOf(pageForm.getOffset())));
        sb.append(hidden().get(SEARCH_MODE,  String.valueOf(pageForm.getSearchMode())));
        sb.append(hidden().get(HID_SELECTEDUSERID,  "")); 
        return sb.toString();
    }
    
    private static String setShowMore(HttpServletRequest request, 
    		UserSearchForm pageForm, UserListOutput userListOutput) {
        StringBuffer sbBody = new StringBuffer();
        
        
        List<CssAlignType> aligs = new ArrayList<>();
        
        String comp3 = "";
        if (SEARCH_MODE_WAIT_LIST.equals(pageForm.getSearchMode())) {
        	comp3 += button().getBorder(IconSetType.CHECK, CssClassType.SUCCESS, REVIEWMULTIBTN_BTN_ID, getContext("admin.userReviewList.btn.batchReview"))+"&nbsp;&nbsp;";
        }
        comp3 += button().getBorder(IconSetType.SEARCH, CssClassType.PRIMARY, WAITLIST_BTN_ID, getContext("admin.userReviewList.btn.waitList"))+"&nbsp;&nbsp;";
        comp3 += button().getBorder(IconSetType.SEARCH, CssClassType.SECONDARY, REVIEWEDLIST_BTN_ID, getContext("admin.userReviewList.btn.reviewedList"));

        aligs.add(CssAlignType.RIGHT);
        sbBody.append(divRow().get(CellWidthType.ONE, aligs, comp3));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    private static String getPageLinked(HttpServletRequest request, UserSearchForm pageForm, UserListOutput userListOutput) {
        PaginationHolder paginationHolder = new PaginationHolder(userListOutput.getCount(), 
                pageForm.getUserPageLinkIdPrefixIndex(), 
                GlobalConstants.DEFAULT_GROWING_CNT, 
                isPhoneMode(request)? GlobalConstants.SP_LINKCNT : GlobalConstants.PC_LINKCNT);
        paginationHolder.setPageLinkIdPrefix(PAGE_LINK_ID_PREFIX);
        return HtmlPageLinkedHelper.getPageLinkedHtml(paginationHolder);
    }
    
    private static String setUserListTable(HttpServletRequest request, 
    		UserSearchForm pageForm, List<UserInfo> userList) {
    	boolean isWaitList = SEARCH_MODE_WAIT_LIST.equals(pageForm.getSearchMode()); 
        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
        	if (isWaitList) {//待审核列表
	        	String context = CheckBoxSet.builder()
	                    .id(USER_LIST_CHECK_ALL_ID).name(USER_LIST_CHECK_ALL_ID)
	                    .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
	            List<CssAlignType> aligs = new ArrayList<>();
	            aligs.add(CssAlignType.CENTER);
	            aligs.add(CssAlignType.LEFT);
	            aligs.add(CssAlignType.RIGHT);
	            String subRow1 = divRow().get(CellWidthType.THREE_2_6_4, aligs, 
	            		context,
	                    T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName(), 
	                    T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE).getLabelName()
	                    );
	            
	            aligs = new ArrayList<>();
	            aligs.add(CssAlignType.CENTER);
	            aligs.add(CssAlignType.LEFT);
	            aligs.add(CssAlignType.CENTER);
	            String subRow2 = divRow().get(CellWidthType.THREE_2_8_2, aligs, 
	            		"",
	            		T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER).getLabelName(),
	                    getContext("common.page.do"));
	            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2));
        	}else {
        		List<CssAlignType> aligs = new ArrayList<>();
        		aligs.add(CssAlignType.LEFT);
 	            aligs.add(CssAlignType.LEFT);
 	            String subRow1 = divRow().get(CellWidthType.TWO_9_3, aligs, 
 	                    T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName(), 
 	                    T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE).getLabelName()
 	                    );
 	            
 	            aligs = new ArrayList<>();
 	            aligs.add(CssAlignType.LEFT);
 	            aligs.add(CssAlignType.LEFT);
 	            String subRow2 = divRow().get(CellWidthType.TWO_9_3, aligs, 
 	            		T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER).getLabelName(),
 	            		T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS).getLabelName());
 	            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2));
        	}
        } else {
        	String context = "";
        	if (isWaitList) {
	        	context = CheckBoxSet.builder()
	                    .id(USER_LIST_CHECK_ALL_ID).name(USER_LIST_CHECK_ALL_ID)
	                    .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
	        	headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
        	}
            // --col1--
            ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_APPLICATION_DATE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col2--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(isWaitList ? CssGridsType.G1:CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col3--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col4--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_MEMBERSHIP_PATH);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col5--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(isWaitList ? CssGridsType.G2:CssGridsType.G3, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col6--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_PHONE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col7--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_MAIL);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col8--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col9--
            clmForm = T100MUser.getColumnInfo(T100MUser.COL_CHECK_DATE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col10--
            if (isWaitList) {
	            context         = getContext("common.page.do");
	            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
            }
        }
        
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(userList)) {
            for(int i=0; i<userList.size(); i++) {
                UserInfo userInfo = userList.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(userInfo.getId()));
                TrSet tr = tr().row(properties);
                
                String userId = nonNull(userInfo.getId());
                String userName = nonNull(userInfo.getUser().getName());
                String userTypeName = nonNull(userInfo.getUserTypeName());
                String membershipPath = nonNull(userInfo.getMembershipPathName());
                String phone = nonNull(userInfo.getUser().getPhone());
                String mail = nonNull(userInfo.getUser().getMail());
                String employer = nonNull(userInfo.getUser().getEmployer());
                String applicationDate = LocalDateUtility.formatDateZH(nonNull(userInfo.getUser().getApplicationDate()));
                String checkDate = LocalDateUtility.formatDateZH(nonNull(userInfo.getUser().getCheckDate()));
//                String checkStatus = nonNull(userInfo.getCheckStatusName()); 
                String checkStatus = getUserCheckStatus(nonNull(userInfo.getUser().getCheckStatus()));
                String button = button().forTableBorderNameLeft(IconSetType.EDIT, CssClassType.SUCCESS, 
                        "", getContext("admin.userReviewList.btn.review"), userInfo.getId(), TABLE_BTN_REVIEW);
//                button += "&nbsp;&nbsp;";
//                button += button().forTableBorderNameLeft(IconSetType.DETAIL, CssClassType.INFO, 
//                        "", getContext("admin.userList.btn.detail"), userInfo.getId(), TABLE_BTN_DETAIL);
                if (isPhoneMode(request)) {
                	if (isWaitList) {
                	String context = CheckBoxSet.builder()
                            .id(USER_CHECK_PREF_ID + i).name(USER_CHECK_PREF_ID).value(userId)
                            .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
                    List<CssAlignType> aligs = new ArrayList<>();
                    aligs.add(CssAlignType.CENTER);
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.CENTER);
                    String subRow1 = divRow().get(CellWidthType.THREE_2_6_4, aligs, 
                    		context, userName, userTypeName);
                    
                    aligs = new ArrayList<>();
                    aligs.add(CssAlignType.CENTER);
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.CENTER);
                    String subRow2 = divRow().get(CellWidthType.THREE_2_8_2, aligs,
                    		"", employer, button);
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2));
                	}else {
                		List<CssAlignType> aligs = new ArrayList<>();
                        aligs.add(CssAlignType.LEFT);
                        aligs.add(CssAlignType.LEFT);
                        String subRow1 = divRow().get(CellWidthType.TWO_9_3, aligs, userName, userTypeName);
                        
                        aligs = new ArrayList<>();
                        aligs.add(CssAlignType.LEFT);
                        aligs.add(CssAlignType.LEFT);
                        String subRow2 = divRow().get(CellWidthType.TWO_9_3, aligs, employer, checkStatus);
                        tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2));
                	}
                } else {
                	String context = "";
                	if (isWaitList) {
                		context = CheckBoxSet.builder()
                            .id(USER_CHECK_PREF_ID + i).name(USER_CHECK_PREF_ID).value(userId)
                            .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
                		 tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, context));
                	}
                	 // --col1--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, applicationDate));
                    // --col2--
                    tr.addTd(td().get(isWaitList ? CssGridsType.G1:CssGridsType.G2, CssAlignType.CENTER, trimFitForTd(CssGridsType.G12.getKey(), userName, true)));
                    // --col3--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, userTypeName));
                    // --col4--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, membershipPath));
                    // --col5--
                    tr.addTd(td().get(isWaitList ? CssGridsType.G2:CssGridsType.G3, CssAlignType.CENTER, trimFitForTd(CssGridsType.G12.getKey(), employer, true)));
                    // --col6--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, phone));
                    // --col7--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, trimFitForTd(CssGridsType.G12.getKey(), mail, true)));
                    // --col8--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, checkStatus));
                    // --col9--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, checkDate));
                    // --col10--
                    if (isWaitList) {
                    	tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, button));
                    }
                }
                
                bodyList.add(tr);
            }
        }
        
        return table().get(USER_LIST_TABLE_ID, calcTableHeightWhenShowSearch(request), headTr, bodyList);
    }
    
    private static String getUserCheckStatus(String checkStatus) {
    	UserCheckStatusType sts = UserCheckStatusType.keyOf(checkStatus);
        if (sts != null) {
	        return PTextSet.builder().classType(sts.getClassType())
	                .context(getContext(sts.getMsg())).build().html();
        }
        return "";
    }
    
    private static int calcTableHeightWhenShowSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT;
    }
    
    private static int calcTableHeightWhenHideSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT + SEARCH_PANEL_HEIGHT;
    }
    
//    private static int calcTableWidth(int [] widths) {
//        int totalWidth = 0;
//        for (int width : widths) {
//            totalWidth += width;
//        }
//        return totalWidth;
//    }
	
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",              URL_BASE + URL_C_INIT);
		js.put("url_user_list",         URL_BASE + URL_C_SEARCH);
		js.put("url_user_list_growing", URL_BASE + URL_C_GROWING);

		return js;
	}
}
