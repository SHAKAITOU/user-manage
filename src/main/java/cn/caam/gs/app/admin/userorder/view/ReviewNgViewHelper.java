package cn.caam.gs.app.admin.userorder.view;

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
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.ReFundStatusType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;

@Component
public class ReviewNgViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + "/reviewNg";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    //init url
    public static final String URL_C_COMMIT = UrlConstants.COMMIT;  
    
    
    public static final String MAIN_JS_CLASS                  = "ReviewNg";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_OK = "btnOk";
    public static final String BTN_CLOSE = "btnClose";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request,
            UserInfo userInfo,
            OrderInfo orderInfo) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userInfo, orderInfo))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            UserInfo userInfo,
            OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden(orderInfo));
        sb.append(setCardPanel(request, userInfo, orderInfo));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden(OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
        String name      = clmForm.getPageName("");
        sb.append(hidden().get(name, orderInfo.getId()));
        return sb.toString();
    }

    //--------------------header Panel -----------------
    
    private static String setCardPanel(
            HttpServletRequest request,
            UserInfo userInfo,
            OrderInfo orderInfo) {
        
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        
        //------row2----------[
        contextList = new ArrayList<String>();
        //订单号(yyyyMMddHHmmssSSSR7)
        String labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName() + UtilConstants.COLON;
        String context   = orderInfo.getOrder().getId();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //会员号(M/TYYMMDDHHmmSSR2)
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_USER_ID).getLabelName() + UtilConstants.COLON;
        context   = orderInfo.getUserName() + "(" + orderInfo.getOrder().getUserId() + ")";
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //旧的有效结束日期(yyyy-MM-dd HH選択
        String oldValidDt = LocalDateUtility.formatDateZH(userInfo.getUser().getValidEndDate());
        labelName = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getLabelName() + UtilConstants.COLON;
        context   = oldValidDt;
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //订单金额選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT).getLabelName() + UtilConstants.COLON;
        context   = formatCurrencyZH(orderInfo.getOrder().getOrderAmount());
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.SUCCESS)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();

        //回执(max250)
        ColumnInfoForm clmForm     = T200MOrder.getColumnInfo(T200MOrder.COL_ANS);
        String name        = clmForm.getPageName("");
        String id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value       = getContext("admin.order.msg.reviewNg");
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName).value(value)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(4).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_REFUND_STATUS);
        name      = clmForm.getPageName("");
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> refundStatusList = fixedValueMap.get(FixedValueType.REFUND_STATUS);
        List<HtmlRadio> radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : refundStatusList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(ReFundStatusType.NOT_CONFIRM.getKey())
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return borderCard().noTitleWithScroll("", CssClassType.DANGER, "", 370,
                sbBody.toString());
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_OK;
        String context = getContext("common.page.ok");
        String comp1 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, id, context);
        id = BTN_CLOSE;
        context = getContext("common.page.btn.close");
        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",          URL_BASE + URL_C_INIT); 
        js.put("url_review_commit", URL_BASE + URL_C_COMMIT); 
        
        
        return js;
    }
}
