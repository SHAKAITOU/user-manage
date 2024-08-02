package cn.caam.gs.app.admin.userreview.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.UserExpiredType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T201MBill;

@Component
public class AdminUserReviewViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + "/reviewUser";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String URL_C_REVIEW_OK = "/ok"; 
    public static final String URL_C_REVIEW_NG = "/ng"; 
    
    public static final String MAIN_JS_CLASS                  = "AdminUserReview";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String PREFIX_NAME                    = "user.";
    
    public static final String SHOW_ORDER_IMG_BTN_ID          = "showOrderImg";
    public static final String ORDER_IMG_ID                   = "orderImg";
    public static final String SHOW_BILL_IMG_BTN_ID           = "showBillImgId";
    public static final String BILL_IMG_ID                    = "billImg";
    
    public static final int IMG_WIDTH                      = 300;
    public static final int IMG_HEIGHT                     = 250;
    
    public static final int PHONE_CARD_HEIGHT              = 270;
    
    public static final String BTN_REVIEW_OK = "btnReviewOk";
    public static final String BTN_REVIEW_NG = "btnReviewNg";
    public static final String BTN_BACK = "btnBack";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            UserInfo userInfo) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userInfo))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden(userInfo));
        sb.append(setBreadCrumb(userInfo));
        sb.append(divRow().cellBlank(5));
        sb.append(setReviewCardPanel(request, userInfo, calcCardHeight(request)));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden(UserInfo userInfo) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
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
        sbBody.append(divRow().get(CellWidthType.ONE, getContext("admin.userReview.review.history")));
        
        //------row3----------[
        contextList = new ArrayList<String>();
        //审核意见(F0013)選択
//        labelName = T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS).getLabelName() + UtilConstants.COLON;
//        context   = PTextSet.builder()
//                .context(getContext("admin.userReview.review.opinion"))
//                .classType(CheckStatusType.keyOf(userInfo.getUser().getCheckStatus()).getClassType()).build().html();
//        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
//                .grids(CssGridsType.G12).classType(CssClassType.CONTEXT)
//                .contexts(new String[] {labelName, context}).build().html());
//        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String id        = convertNameDotForId(name);
        labelName = getContext("admin.userReview.review.opinion");
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> checkStatusList = fixedValueMap.get(FixedValueType.CHECK_STATUS);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : checkStatusList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
      //备注(max250)
        clmForm     = T201MBill.getColumnInfo(T201MBill.COL_BILL_MEMO);
//        String name        = clmForm.getPageName(PREFIX_NAME);
        name = "";
        id          = convertNameDotForId("name");
        labelName   = getContext("admin.userReview.review.opinion.memo");
//        String placeholder = clmForm.getPlaceholder();
        String placeholder = "审核建议";
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(2).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
//        //备注(max250)選択
//        strRow1 = T100MUser.getColumnInfo(T100MUser.COL_MEMO).getLabelName() + UtilConstants.COLON;
//        strRow2   = orderInfo.getOrder().getMemo();
//        context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
//        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
//                .grids(CssGridsType.G6).classType(CssClassType.INFO)
//                .contexts(new String[] {context}).build().html());
//
//        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row4----------]
        return borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", cartHeight,
                sbBody.toString());
    }
    
    private static int calcCardHeight(HttpServletRequest request) {
        if (isPhoneMode(request)) {
            return PHONE_CARD_HEIGHT;
        }
        return 470;
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_REVIEW_OK;
        String context = getContext("admin.order.btn.reviewOk");
        String comp1 = button().getBorder(IconSetType.CHECK, CssClassType.SUCCESS, id, context);
        
        id = BTN_REVIEW_NG;
        context = getContext("admin.order.btn.reviewNg");
        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DANGER, id, context);
        
        id = BTN_BACK;
        context = getContext("common.page.btn.back");
        String comp3 = button().getBorder(IconSetType.BACK, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.LEFT);
        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.TWO_6_6, aligs, concactWithSpace(comp1,comp2), comp3));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",      URL_BASE + URL_C_INIT);
        js.put("url_review_ok", URL_BASE + URL_C_REVIEW_OK);
        js.put("url_review_ng", URL_BASE + URL_C_REVIEW_NG);
        
        return js;
    }
}
