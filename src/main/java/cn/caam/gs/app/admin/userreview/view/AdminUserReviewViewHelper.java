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
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.common.output.UserCheckHistoryListOutput;
import cn.caam.gs.app.common.view.UserDetailViewHelper;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.PageModeType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.TabSet;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserCheckHistoryInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T103MUserCheckHistory;

@Component
public class AdminUserReviewViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + "/reviewUser";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String URL_C_REVIEW = "/review"; 
    
    public static final String MAIN_JS_CLASS                  = "AdminUserReview";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String PREFIX_NAME                    = "user.";
    
    public static final String TAB_ID                         = "detailTab";
    public static final String TAB_TITLE_REVIEW_ID            = "reviewTab";
    public static final String TAB_TITLE_USER_DETAIL_ID       = "userDetailTab";
    public static final String TAB_BODY_REVIEW_ID             = "reviewTabBody";
    public static final String TAB_BODY_USER_DETAIL_ID        = "userDetailTabBody";
    
    public static final String SHOW_ORDER_IMG_BTN_ID          = "showOrderImg";
    public static final String ORDER_IMG_ID                   = "orderImg";
    public static final String SHOW_BILL_IMG_BTN_ID           = "showBillImgId";
    public static final String BILL_IMG_ID                    = "billImg";
    public static final String LIST_TABLE_ID                  = "checkHistoryListTable";
    public static final int PHONE_TD_HEIGHT = 70;
    
    public static final int PHONE_CARD_HEIGHT              = 470;
    public static final int    HEADER_HEIGHT               = 390;
    
//    public static final String BTN_REVIEW_OK = "btnReviewOk";
//    public static final String BTN_REVIEW_NG = "btnReviewNg";
    public static final String BTN_OK	= "btnOk";
    public static final String BTN_BACK = "btnBack";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            UserInfo userInfo,
            UserCheckHistoryListOutput userCheckHistoryListOutput) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userInfo, userCheckHistoryListOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            UserInfo userInfo,
            UserCheckHistoryListOutput userCheckHistoryListOutput) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(divRow().cellBlank(5));
//        sb.append(setHidden(userInfo));
//        sb.append(setBreadCrumb(userInfo));
//        sb.append(divRow().cellBlank(5));
//        sb.append(setReviewCardPanel(request, userInfo, userCheckHistoryListOutput, calcCardHeight(request)));
//        sb.append(buildFooter());
//        return getForm(FORM_NAME, sb.toString());
    	UserDetailForm userDetailForm = new UserDetailForm();
    	userDetailForm.setUserInfo(userInfo);
        
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden(userInfo));
        sb.append(setBreadCrumb(userInfo));
        sb.append(divRow().cellBlank(5));
        String tabHtml = TabSet.builder()
                .id(TAB_ID)
            .tabTitleIds(new String [] {TAB_TITLE_REVIEW_ID, TAB_TITLE_USER_DETAIL_ID})
            .tabTitles(new String [] {
                    getContext("admin.userReview.review.tab.title"), 
                    getContext("admin.userReview.userdetail.tab.title")})
            .tabBodyIds(new String [] {TAB_BODY_REVIEW_ID, TAB_BODY_USER_DETAIL_ID})
            .tabBodys(new String [] {
            		setReviewCardPanel(request, userInfo, userCheckHistoryListOutput, calcCardHeight(request)), 
            		UserDetailViewHelper.getDetailContext(request, userDetailForm, PageModeType.VIEW)})
            .build().html();
        sb.append(tabHtml);
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden(UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_USER_ID);
        String name      = clmForm.getPageName("");
        sb.append(hidden().get(name, userInfo.getId()));
        return sb.toString();
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb(UserInfo userInfo) {
        String[] names = new String[] {getContext("menu.group1"), getContext("menu.group1.button3")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header Panel -----------------
    
    public static String setReviewCardPanel(
            HttpServletRequest request,
            UserInfo userInfo,
            UserCheckHistoryListOutput userCheckHistoryListOutput,
            int cartHeight) {
    	@SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
    	
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        
        //-----row 1-------------[
        //会员名称
        String labelName = T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName() + UtilConstants.COLON;
        String context   = userInfo.getUser().getName();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G4).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());

        //会员类型
        labelName = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE).getLabelName() + UtilConstants.COLON;
        context   = userInfo.getUserTypeName();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G4).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        
       //分会名称
        labelName = T100MUser.getColumnInfo(T100MUser.COL_MEMBERSHIP_PATH).getLabelName() + UtilConstants.COLON;
        context   = userInfo.getMembershipPathName();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G4).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 1-------------]
        
        //审核历史记录  
        //-----row 2-------------[
        sbBody.append(setCardForTable(request, userCheckHistoryListOutput));
        //-----row 2-------------]
        
        //------row3----------[
        contextList = new ArrayList<String>();
        //审核意见(F0024)選択    
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS);
        String name        = clmForm.getPageName("");
        String id          = convertNameDotForId(name);
        labelName = getContext("admin.userReview.review.opinion");
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> checkStatusList = fixedValueMap.get(FixedValueType.USER_CHECK_STATUS);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : checkStatusList) {
        	if (!UserCheckStatusType.WAIT_FOR_REVIEW.getKey().equals(fValueInfo.getValueObj().getValue())) {
        		radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        	}
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        //审核建议(max150)
        clmForm     = T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_MEMO);
        name        = clmForm.getPageName("");
        id          = convertNameDotForId(name);
        labelName   = getContext("admin.userReview.review.opinion.memo");
        String placeholder = clmForm.getPlaceholder();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.REVIEW_MEMO_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(2).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row3----------]
        
        return borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", cartHeight,
                sbBody.toString());
    }
    
    private static String setCardForTable(
            HttpServletRequest request, 
            UserCheckHistoryListOutput userCheckHistoryListOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, userCheckHistoryListOutput.getUserCheckHistoryList()));
        String cardTitle = getContext("admin.userReview.review.history");
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                cardTitle,
                divRow().cellBlank(5),cardBody.toString()));
        sbBody.append(divRow().cellBlank(5));
        return sbBody.toString();
    }
    
    private static String setUserListTable(
            HttpServletRequest request, 
            List<UserCheckHistoryInfo> list) {

        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
        	 List<CssAlignType> aligs = new ArrayList<>();
             aligs.add(CssAlignType.LEFT);
             aligs.add(CssAlignType.RIGHT);
             //String subRow1 = divRow().get(CellWidthType.ONE,     aligs, T100MUser.getColumnInfo(T100MUser.COL_MEMBERSHIP_PATH).getLabelName());
             List<GridFlexType> flexs = new ArrayList<>();
             flexs.add(GridFlexType.LEFT);
             flexs.add(GridFlexType.RIGHT);
             String subRow1 = divRow().getFlex(CellWidthType.TWO_6_6, flexs, T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_CHECK_STATUS).getLabelName(), 
             		T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_CHECK_DATE).getLabelName());
             flexs = new ArrayList<>();
             flexs.add(GridFlexType.LEFT);
             flexs.add(GridFlexType.RIGHT);
             String subRow2 = divRow().get(CellWidthType.ONE,     aligs, T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_MEMO).getLabelName());
             headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2));
         } else {
//        } else {
            // --[
            // --col1--审核结果
            String context         = T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_CHECK_STATUS).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col2--审核时间
            context         = T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_CHECK_DATE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col3--原因
            context         = T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_MEMO).getLabelName();
            headTr.addTh(th().get(CssGridsType.G8, CssAlignType.CENTER, context));
            // --]
        }
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(list)) {
            for(int i=0; i<list.size(); i++) {
            	UserCheckHistoryInfo info = list.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(info.getId()));
                TrSet tr = tr().row(properties);
                String checkStatusNmae = PTextSet.builder()
                        .context(info.getCheckStatusName())
                        .classType(UserCheckStatusType.keyOf(info.getUserCheckHistory().getCheckStatus()).getClassType()).build().html();
                String checkDate = nonNull(info.getUserCheckHistory().getCheckDate());
                String memo = nonNull(info.getUserCheckHistory().getMemo());//trimFitForTd(CssGridsType.G12.getKey(), info.getUserCheckHistory().getMemo(), true);
                if (isPhoneMode(request)) {
                    List<GridFlexType> flexs = new ArrayList<>();
                    flexs.add(GridFlexType.LEFT);
                    flexs.add(GridFlexType.RIGHT);
                    String subRow1 = divRow().getFlex(CellWidthType.TWO_4_8, flexs, checkStatusNmae, checkDate);
                    flexs = new ArrayList<>();
                    flexs.add(GridFlexType.LEFT);
                    flexs.add(GridFlexType.RIGHT);
                    String subRow2 = divRow().getFlex(CellWidthType.ONE, flexs, memo);
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2));
                } else {
                    // --col1--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, true, checkStatusNmae));
                    // --col2--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, true, checkDate));
                    // --col3--
                    tr.addTd(td().get(CssGridsType.G8, CssAlignType.LEFT, true, memo));
                }
                
                bodyList.add(tr);
            }
        }
        
        return table().get(LIST_TABLE_ID, calcTableHeightWhenShowSearch(request), headTr, bodyList);
    }
    
    private static int calcTableHeightWhenShowSearch(HttpServletRequest request) {
//        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT;
    	return 200;
    }
    
    private static int calcCardHeight(HttpServletRequest request) {
        if (isPhoneMode(request)) {
            return PHONE_CARD_HEIGHT;
        }
        return 500;
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_OK;
        String context = getContext("common.page.ok");
        String comp1 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, id, context);
        
        id = BTN_BACK;
        context = getContext("common.page.btn.back");
        String comp2 = button().getBorder(IconSetType.BACK, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",   URL_BASE + URL_C_INIT);
        js.put("url_review", URL_BASE + URL_C_REVIEW);
        
        return js;
    }
}
