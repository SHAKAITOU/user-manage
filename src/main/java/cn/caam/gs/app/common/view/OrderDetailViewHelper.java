package cn.caam.gs.app.common.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.BillType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.OrderMethodType;
import cn.caam.gs.common.enums.OrderType;
import cn.caam.gs.common.enums.PayType;
import cn.caam.gs.common.enums.ReFundStatusType;
import cn.caam.gs.common.enums.VoteMethodType;
import cn.caam.gs.common.html.element.TabSet;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet.DivContainerSetType;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet.LabelImageSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;
import cn.caam.gs.domain.tabledef.impl.T201MBill;
import cn.caam.gs.domain.tabledef.impl.T202MImage;

@Component
public class OrderDetailViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/orderDetail";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String URL_C_GET_NOT_FINISH_CNT = "/getNotFinishCnt"; 
    
    public static final String MAIN_JS_CLASS                  = "OrderDetail";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String TAB_ID                         = "detailTab";
    public static final String TAB_TITLE_ORDER_ID             = "orderTab";
    public static final String TAB_TITLE_BILL_ID              = "billTab";
    public static final String TAB_TITLE_BILL_DETAIL_ID       = "billDetailTab";
    public static final String TAB_BODY_ORDER_ID              = "orderTabBody";
    public static final String TAB_BODY_BILL_ID               = "billTabBody";
    public static final String TAB_BODY_BILL_DETAIL_ID        = "billDetailTabBody";
    
    public static final String SHOW_ORDER_IMG_BTN_ID          = "showOrderImg";
    public static final String ORDER_IMG_ID                   = "orderImg";
    public static final String SHOW_BILL_IMG_BTN_ID           = "showBillImg";
    public static final String BILL_IMG_ID                    = "billImg";
    
    public static final int IMG_WIDTH                      = 300;
    public static final int IMG_HEIGHT                     = 250;
    
    public static final int ORDER_BASE_CARD_HEIGHT         = 515;
    public static final int PHONE_ORDER_BASE_CARD_HEIGHT   = 380;
    
    public static final String BTN_CLOSE = "btnClose";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            OrderInfo orderInfo) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, orderInfo))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        String tabHtml = TabSet.builder()
                .id(TAB_ID)
            .tabTitleIds(new String [] {TAB_TITLE_ORDER_ID, TAB_TITLE_BILL_ID, TAB_TITLE_BILL_DETAIL_ID})
            .tabTitles(new String [] {
                    getContext("admin.order.tab.title"), 
                    getContext("admin.bill.tab.title"),
                    getContext("admin.billDetail.tab.title")})
            .tabBodyIds(new String [] {TAB_BODY_ORDER_ID, TAB_BODY_BILL_ID, TAB_BODY_BILL_DETAIL_ID})
            .tabBodys(new String [] {
                    setOrderCardPanel(request, orderInfo, calcOrderCardHeight(request)), 
                    setBillCardPanel(request, orderInfo, calcOrderCardHeight(request)),
                    setBillDetailCardPanel(request, orderInfo, calcOrderCardHeight(request))})
            .build().html();
        sb.append(tabHtml);
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }

    //--------------------header Panel -----------------
    
    public static String setOrderCardPanel(
            HttpServletRequest request,
            OrderInfo orderInfo,
            int cartHeight) {
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        
        //-----row 1-------------[
        //订单号(yyyyMMddHHmmssSSSR7)
        String labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName() + UtilConstants.COLON;
        String context   = orderInfo.getOrder().getId();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());

        //会员号(M/TYYMMDDHHmmSSR2)
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_USER_ID).getLabelName() + UtilConstants.COLON;
        context   = orderInfo.getUserName() + "(" + orderInfo.getOrder().getUserId() + ")";
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 2-------------]
        
        //------row1----------[
        contextList = new ArrayList<String>();
        //订单方式(F0020)選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_METHOD).getLabelName() + UtilConstants.COLON;
        context   = PTextSet.builder()
                .context(orderInfo.getOrderMethodName())
                .classType(OrderMethodType.keyOf(orderInfo.getOrder().getOrderMethod()).getClassType()).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        
        //订单类型(F0015)選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE).getLabelName() + UtilConstants.COLON;
        context   = PTextSet.builder()
                .context(orderInfo.getOrderTypeName())
                .classType(OrderType.keyOf(orderInfo.getOrder().getOrderType()).getClassType()).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row1----------]
        
        //------row2----------[
        contextList = new ArrayList<String>();
        //缴费渠道(F0012)選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_PATH).getLabelName() + UtilConstants.COLON;
        context   = orderInfo.getPayPathName();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        
        //缴费类型(F0014)選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_TYPE).getLabelName() + UtilConstants.COLON;
        context   = PTextSet.builder()
                .context(orderInfo.getPayTypeName())
                .classType(PayType.keyOf(orderInfo.getOrder().getPayType()).getClassType()).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        //------row3----------[
        contextList = new ArrayList<String>();
        //订单金额選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT).getLabelName() + UtilConstants.COLON;
        context   = formatCurrencyZH(orderInfo.getOrder().getOrderAmount()).toString();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        
        //缴费时间(yyyy-MM-dd HH選択
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE).getLabelName() + UtilConstants.COLON;
        context   = orderInfo.getOrder().getPayDate();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row3----------]
        
        //------row4----------[
        contextList = new ArrayList<String>();
        
        //订单图片選択
        String strRow1 = T202MImage.getColumnInfo(T202MImage.COL_ORDER_PHOTO).getLabelName() + UtilConstants.COLON;
        String strRow2 = divRow().get(getContext("common.page.noImge"));
        if (orderInfo.getOrderImg() != null && orderInfo.getOrderImg().getOrderPhoto() != null) {
            strRow1 += UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + button().forTableBorder(IconSetType.EYE, CssClassType.INFO, SHOW_ORDER_IMG_BTN_ID);
            String src = Base64.encodeBase64String(orderInfo.getOrderImg().getOrderPhoto());
            strRow2 = LabelImageSet.builder().extent(orderInfo.getOrderImg().getOrderPhotoExt())
                    .base64String(src).id(ORDER_IMG_ID)
                    .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT).outPutType(LabelImageSetType.SIMPLE).build().html();
        }
        context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {context}).build().html());
        
        //备注(max250)選択
        strRow1 = T200MOrder.getColumnInfo(T200MOrder.COL_MEMO).getLabelName() + UtilConstants.COLON;
        strRow2   = orderInfo.getOrder().getMemo();
        context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.INFO)
                .contexts(new String[] {context}).build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row4----------]
        return borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", cartHeight,
                sbBody.toString());
    }
    
    public static String setBillCardPanel(
            HttpServletRequest request,
            OrderInfo orderInfo,
            int cartHeight) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbBody = new StringBuffer();

        List<String> contextList = new ArrayList<String>();
        

        //------row5----------[
        contextList = new ArrayList<String>();
        
        //审核状态(F0013)選択
        String labelName = T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS).getLabelName() + UtilConstants.COLON;
        String context   = PTextSet.builder()
                    .context(orderInfo.getCheckStatusName())
                    .classType(CheckStatusType.keyOf(orderInfo.getOrder().getCheckStatus()).getClassType()).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.SUCCESS)
                .contexts(new String[] {labelName, context}).build().html());
  
        //回执(max250)選択
        String strRow1 = T200MOrder.getColumnInfo(T200MOrder.COL_ANS).getLabelName() + UtilConstants.COLON;
        String strRow2 = orderInfo.getOrder().getAns();
        context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G6).classType(CssClassType.SUCCESS)
                .contexts(new String[] {context}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));

        //------row5----------]
        //------row6----------[
        contextList = new ArrayList<String>();

        if (orderInfo.getOrder().getCheckStatus().equals(CheckStatusType.REFUSED.getKey())) {
            //退款状态(F0016)選択
            labelName = T200MOrder.getColumnInfo(T200MOrder.COL_REFUND_STATUS).getLabelName() + UtilConstants.COLON;
            context   = PTextSet.builder()
                        .context(orderInfo.getRefundStatusName())
                        .classType(ReFundStatusType.keyOf(orderInfo.getOrder().getRefundStatus()).getClassType()).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DANGER)
                    .contexts(new String[] {labelName, context}).build().html());
            
            labelName = T200MOrder.getColumnInfo(T200MOrder.COL_REFUND_DATE).getLabelName() + UtilConstants.COLON;
            context   = orderInfo.getOrder().getRefundDate();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DANGER)
                    .contexts(new String[] {labelName, context}).build().html());
            
            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
            
            if (orderInfo.getOrder().getRefundStatus().equals(ReFundStatusType.REFUND_OVER.getKey())) {
                contextList = new ArrayList<String>();
                
                //退款凭证图片選択
                strRow1 = getContext("admin.refund.photo") + UtilConstants.COLON;
                strRow2 = divRow().get(getContext("common.page.noImge"));
                if (orderInfo.getOrderImg() != null && orderInfo.getOrderImg().getBillPhoto() != null) {
                    strRow1 += UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + button().forTableBorder(IconSetType.EYE, CssClassType.INFO, SHOW_BILL_IMG_BTN_ID);
                    String src = Base64.encodeBase64String(orderInfo.getOrderImg().getBillPhoto());
                    strRow2 = LabelImageSet.builder().extent(orderInfo.getOrderImg().getBillPhotoExt())
                            .base64String(src).id(BILL_IMG_ID)
                            .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT).outPutType(LabelImageSetType.SIMPLE).build().html();
                }
                context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
                contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                        .grids(CssGridsType.G6).classType(CssClassType.DANGER)
                        .contexts(new String[] {context}).build().html());
                
                //开票备注(max250)選択
                strRow1 = T201MBill.getColumnInfo(T201MBill.COL_BILL_MEMO).getLabelName() + UtilConstants.COLON;
                strRow2 = orderInfo.getBill().getBillMemo();
                context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
                contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                        .grids(CssGridsType.G6).classType(CssClassType.DANGER)
                        .contexts(new String[] {context}).build().html());
                
                sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
                contextList = new ArrayList<String>();
            }
            
        } else {
          //开票状态(F0018)選択
            labelName = T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS).getLabelName() + UtilConstants.COLON;
            context   = PTextSet.builder()
                        .context(orderInfo.getBillStatusName())
                        .classType(BillStatusType.keyOf(orderInfo.getOrder().getBillStatus()).getClassType()).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.SUCCESS)
                    .contexts(new String[] {labelName, context}).build().html());

            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        }

        //内容
        sb.append(borderCard().noTitleWithScroll("", CssClassType.WARNING, "", cartHeight,
                sbBody.toString()));
        return sb.toString();
    }
    
    private static String setBillDetailCardPanel(
            HttpServletRequest request,
            OrderInfo orderInfo,
            int cartHeight) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbBody = new StringBuffer();

        List<String> contextList = new ArrayList<String>();

        //------row5----------[
        if (orderInfo.getOrder().getCheckStatus().equals(CheckStatusType.PASS.getKey()) && 
                orderInfo.getOrder().getBillStatus().equals(BillStatusType.INVOICED.getKey())) {
            //发票代码選択
            String labelName = T201MBill.getColumnInfo(T201MBill.COL_BILL_CODE).getLabelName() + UtilConstants.COLON;
            String context   = orderInfo.getBill().getBillCode();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            //发票抬头
            labelName = T201MBill.getColumnInfo(T201MBill.COL_BILL_TITLE).getLabelName() + UtilConstants.COLON;
            context   = orderInfo.getBill().getBillTitle();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
            contextList = new ArrayList<String>();
            
            //实收金额選択
            labelName = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_AMOUNT).getLabelName() + UtilConstants.COLON;
            context   = Objects.nonNull(orderInfo.getOrder().getPayAmount()) ? 
                    formatCurrencyZH(orderInfo.getOrder().getPayAmount()) : 
                        PTextSet.builder()
                        .context(getContext("common.page.toBeConfirm"))
                        .classType(CssClassType.DANGER).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            //发票类型(F0017)
            labelName = T201MBill.getColumnInfo(T201MBill.COL_BILL_TYPE).getLabelName() + UtilConstants.COLON;
            context   = PTextSet.builder()
                    .context(orderInfo.getBillTypeName())
                    .classType(BillType.keyOf(orderInfo.getBill().getBillType()).getClassType()).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
            contextList = new ArrayList<String>();
            
            //统一社会信用代码
            labelName = T201MBill.getColumnInfo(T201MBill.COL_CREDIT_CODE).getLabelName() + UtilConstants.COLON;
            context   = orderInfo.getBill().getCreditCode();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            //取票方式(F0019)
            labelName = T201MBill.getColumnInfo(T201MBill.COL_VOTE_METHOD).getLabelName() + UtilConstants.COLON;
            context   = PTextSet.builder()
                    .context(orderInfo.getVoteMethodName())
                    .classType(VoteMethodType.keyOf(orderInfo.getBill().getVoteMethod()).getClassType()).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
            contextList = new ArrayList<String>();
    
            //开票图片選択
            String strRow1 = T202MImage.getColumnInfo(T202MImage.COL_BILL_PHOTO).getLabelName() + UtilConstants.COLON;
            String strRow2 = divRow().get(getContext("common.page.noImge"));
            if (orderInfo.getOrderImg() != null && orderInfo.getOrderImg().getBillPhoto() != null) {
                strRow1 += UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + button().forTableBorder(IconSetType.EYE, CssClassType.INFO, SHOW_BILL_IMG_BTN_ID);
                String src = Base64.encodeBase64String(orderInfo.getOrderImg().getBillPhoto());
                strRow2 = LabelImageSet.builder().extent(orderInfo.getOrderImg().getBillPhotoExt())
                        .base64String(src).id(BILL_IMG_ID)
                        .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT).outPutType(LabelImageSetType.SIMPLE).build().html();
            }
            context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {context}).build().html());
            
            //开票备注(max250)選択
            strRow1 = T201MBill.getColumnInfo(T201MBill.COL_BILL_MEMO).getLabelName() + UtilConstants.COLON;
            strRow2 = orderInfo.getBill().getBillMemo();
            context = DivContainerSet.builder().contexts(new String[] {strRow1, divRow().cellBlank(5), strRow2}).outPutType(DivContainerSetType.SIMPLE).build().html();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {context}).build().html());
            
            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
            contextList = new ArrayList<String>();
            //开票时间(yyyy-MM-dd HH
            labelName = T201MBill.getColumnInfo(T201MBill.COL_BILL_DATE).getLabelName() + UtilConstants.COLON;
            context   = orderInfo.getBill().getBillDate();
            contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                    .grids(CssGridsType.G6).classType(CssClassType.DARK)
                    .contexts(new String[] {labelName, context}).build().html());
            
            sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        }
        
        //内容
        sb.append(borderCard().noTitleWithScroll("", CssClassType.DARK, "", cartHeight,
                sbBody.toString()));
        return sb.toString();
    }
    
    private static int calcOrderCardHeight(HttpServletRequest request) {
        if (isPhoneMode(request)) {
            return PHONE_ORDER_BASE_CARD_HEIGHT;
        }
        return ORDER_BASE_CARD_HEIGHT;
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_CLOSE;
        String context = getContext("common.page.btn.close");
        String comp1 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.ONE, aligs, comp1));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",                URL_BASE + URL_C_INIT);
        js.put("url_get_not_finfish_cnt", URL_BASE + URL_C_GET_NOT_FINISH_CNT);
        
        return js;
    }
}
