package cn.caam.gs.app.admin.usertypesettings.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.html.HtmlNumberHelper;
import cn.caam.gs.common.html.element.LabelNumberSet;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.LabelNumberSet.LabelNumberSetType;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.domain.db.custom.entity.UserTypeSettingsInfo;
import cn.caam.gs.domain.tabledef.impl.T104MUserTypeSettings;

@Component
public class UserTypeSettingsViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/userTypeSettings";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String URL_C_EDIT = UrlConstants.EDIT; 
    
    public static final String MAIN_JS_CLASS                  = "userTypeSettings";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String PREFIX_NAME_LIST               = "userTypeSettingsInfoList";
    public static final String PREFIX_NAME                    = "userTypeSettings";
    
    public static final String LIST_TABLE_ID                  = "userTypeSettingsTable";
    public static final int PHONE_TD_HEIGHT 			= 55;
    public static final int    HEADER_HEIGHT            = 320;
    public static final int TD_HEIGHT					= 45;
    
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
            List<UserTypeSettingsInfo> userTypeSettingsInfoList) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userTypeSettingsInfoList))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            List<UserTypeSettingsInfo> userTypeSettingsInfoList) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden());
        sb.append(setBreadCrumb());
        sb.append(divRow().cellBlank(5));
        sb.append(setCardForTable(request, userTypeSettingsInfoList));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden() {
        StringBuffer sb = new StringBuffer();
      
        return sb.toString();
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("menu.group2"), getContext("menu.group2.button5")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header Panel -----------------
    
    private static String setCardForTable(
            HttpServletRequest request, 
            List<UserTypeSettingsInfo> userTypeSettingsInfoList) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, userTypeSettingsInfoList));
        String cardTitle = getContext("admin.order.userType.settings.title");
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                cardTitle,
                divRow().cellBlank(5),cardBody.toString()));
        sbBody.append(divRow().cellBlank(5));
        return sbBody.toString();
    }
    
    private static String setUserListTable(
            HttpServletRequest request, 
            List<UserTypeSettingsInfo> userTypeSettingsInfoList) {

        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
             List<GridFlexType> flexs = new ArrayList<>();
             flexs.add(GridFlexType.CENTER);
             flexs.add(GridFlexType.CENTER);
             flexs.add(GridFlexType.CENTER);
             String subRow1 = divRow().getFlex(CellWidthType.THREE_4_4_4, flexs, 
            		 T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_USER_TYPE).getLabelName(), 
            		 T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_FEE_AMOUNT).getLabelName(),
            		 T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_EFFECTIVE_YEAR).getLabelName());
             headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1));
         } else {
            // --[
            // --col1--会员类型
            String context         = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_USER_TYPE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col2--会费
            context         = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_FEE_AMOUNT).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col3--会费年数
            context         = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_EFFECTIVE_YEAR).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col4--
            context         = "";
            headTr.addTh(th().get(CssGridsType.G6, CssAlignType.CENTER, context));
            // --]
        }
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(userTypeSettingsInfoList)) {
            for(int i=0; i<userTypeSettingsInfoList.size(); i++) {
            	UserTypeSettingsInfo info = userTypeSettingsInfoList.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(info.getUserTypeSettings().getUserType()));
                TrSet tr = tr().row(properties);
                String userType = info.getUserTypeSettings().getUserType();
                String userTypeName = info.getUserTypeName();
                String feeAmount = "";
                if (Objects.nonNull(info.getUserTypeSettings().getFeeAmount())){
                	feeAmount = String.valueOf(info.getUserTypeSettings().getFeeAmount().intValue());
                }
                String effectiveYear = "";
                if (Objects.nonNull(info.getUserTypeSettings().getEffectiveYear())){
                	effectiveYear = String.valueOf(info.getUserTypeSettings().getEffectiveYear().intValue());
                }

                if (isPhoneMode(request)) {
                	 // --col1--
                	ColumnInfoForm clmForm = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_USER_TYPE);
                	String name			= getPageName(PREFIX_NAME, clmForm, i);
                    String id			= convertNameDotForId(clmForm.getPageName(PREFIX_NAME+"."));
                    String contexts1 = hidden().get(id, name, userType);
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G4, CssAlignType.CENTER, userTypeName+contexts1));
                    
                    // --col2--
                    clmForm = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_FEE_AMOUNT);
                    name				= getPageName(PREFIX_NAME, clmForm, i);
                    id					= convertNameDotForId(clmForm.getPageName(PREFIX_NAME+"."));
                    String placeholder	= clmForm.getPlaceholder();
                    
                    String contexts2 = LabelNumberSet.builder().id(id).name(name).value(feeAmount)
                    	.placeholder(placeholder).notBlank(true).integerOnly(true).maxLength("5")
                    	.step("50").min(0).max(100000).outPutType(LabelNumberSetType.FOR_TABLE).build().html();
//                    String contexts2 = HtmlNumberHelper.getForTable(id, name, feeAmount, "50", placeholder, true, true);
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G4, CssAlignType.RIGHT, contexts2));
                    
                    // --col3--
                    clmForm = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_EFFECTIVE_YEAR);
                    name				= getPageName(PREFIX_NAME, clmForm, i);
                    id					= convertNameDotForId(clmForm.getPageName(PREFIX_NAME+"."));
                    placeholder			= clmForm.getPlaceholder();
//                    String contexts3 = HtmlNumberHelper.getForTable(id, name, effectiveYear, "1", placeholder, true, true);
                    String contexts3 = LabelNumberSet.builder().id(id).name(name).value(effectiveYear)
                        	.placeholder(placeholder).notBlank(true).integerOnly(true).maxLength("2")
                        	.step("1").min(0).max(10).outPutType(LabelNumberSetType.FOR_TABLE).build().html();
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G4, CssAlignType.RIGHT, contexts3));
                } else {
                    // --col1--
                	ColumnInfoForm clmForm = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_USER_TYPE);
                	String name			= getPageName(PREFIX_NAME, clmForm, i);
                    String id			= convertNameDotForId(clmForm.getPageName(PREFIX_NAME+"."));
                    String contexts1 = hidden().get(id, name, userType);
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G2, CssAlignType.CENTER, userTypeName+contexts1));
                    
                    // --col2--
                    clmForm = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_FEE_AMOUNT);
                    name				= getPageName(PREFIX_NAME, clmForm, i);
                    id					= convertNameDotForId(clmForm.getPageName(PREFIX_NAME+"."));
                    String placeholder	= clmForm.getPlaceholder();
//                    String contexts2 = HtmlNumberHelper.getForTable(id, name, feeAmount, "50", placeholder, true);
                    String contexts2 = LabelNumberSet.builder().id(id).name(name).value(feeAmount)
                        	.placeholder(placeholder).notBlank(true).integerOnly(true).maxLength("5")
                        	.step("50").min(0).max(100000).outPutType(LabelNumberSetType.FOR_TABLE).build().html();
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G2, CssAlignType.RIGHT, contexts2));
                    
                    // --col3--
                    clmForm = T104MUserTypeSettings.getColumnInfo(T104MUserTypeSettings.COL_EFFECTIVE_YEAR);
                    name				= getPageName(PREFIX_NAME, clmForm, i);
                    id					= convertNameDotForId(clmForm.getPageName(PREFIX_NAME+"."));
                    placeholder			= clmForm.getPlaceholder();
//                    String contexts3 = HtmlNumberHelper.getForTable(id, name, effectiveYear, "1", placeholder, true);
                    String contexts3 = LabelNumberSet.builder().id(id).name(name).value(effectiveYear)
                        	.placeholder(placeholder).notBlank(true).integerOnly(true).maxLength("2")
                        	.step("1").min(0).max(10).outPutType(LabelNumberSetType.FOR_TABLE).build().html();
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G2, CssAlignType.RIGHT, contexts3));
                    
                    tr.addTd(td().get(TD_HEIGHT, CssGridsType.G6, CssAlignType.LEFT, ""));
                }
                
                bodyList.add(tr);
            }
        }
        
        return table().get(LIST_TABLE_ID, calcTableHeightWhenShowSearch(request), headTr, bodyList);
    }
    
    public static String getPageName(String prefixName, ColumnInfoForm clmForm, int index) {
    	return clmForm.getPageName(PREFIX_NAME_LIST+"["+index+"]."+PREFIX_NAME+".");
    }
    
    private static int calcTableHeightWhenShowSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT;
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
        js.put("url_init",   URL_BASE + URL_C_INIT);
        js.put("url_edit", URL_BASE + URL_C_EDIT);
        
        return js;
    }
}
