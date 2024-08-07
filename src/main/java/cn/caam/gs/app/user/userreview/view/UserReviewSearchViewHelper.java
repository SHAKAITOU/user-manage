package cn.caam.gs.app.user.userreview.view;

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
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserCheckHistoryInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T103MUserCheckHistory;

@Component
public class UserReviewSearchViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/userView";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String URL_C_REVIEW = "/review"; 
    
    public static final String MAIN_JS_CLASS                  = "UserReview";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String PREFIX_NAME                    = "";
    
    public static final String LIST_TABLE_ID                  = "checkHistoryListTable";
    public static final int PHONE_TD_HEIGHT 			= 55;
    public static final int    HEADER_HEIGHT            = 320;
    
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
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden(userInfo));
        sb.append(setBreadCrumb(userInfo));
        sb.append(divRow().cellBlank(5));
        sb.append(setCardForTable(request, userCheckHistoryListOutput));
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
        String[] names = new String[] {getContext("user.menu.group4"), getContext("user.menu.group4.button1")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header Panel -----------------
    
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
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT;
    }
    
    
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
//        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",   URL_BASE + URL_C_INIT);
//        js.put("url_review", URL_BASE + URL_C_REVIEW);
        
        return js;
    }
}
