package cn.caam.gs.app.admin.userorder.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.OrderType;
import cn.caam.gs.common.enums.PayType;
import cn.caam.gs.common.enums.ReFundStatusType;
import cn.caam.gs.common.html.HtmlPageLinkedHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.html.element.bs5.SpanTextSet;
import cn.caam.gs.common.util.PaginationHolder;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;

@Component
public class AdminOrderSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + "/orderList";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
    
    //init url
    public static final String URL_C_SEARCH_WAIT   = "/searchWait";
    //init url
    public static final String URL_C_SEARCH_REVIEW = "/searchReview";
    //init url
    public static final String URL_C_SEARCH_PASS = "/searchPass";
    
    public static final String URL_C_GROWING = UrlConstants.GROWING;
    
    public static final String URL_C_PUT_TO_REVIEW = "/putToReview";
    
    public static final String PREFIX_NAME                    = "order.";
	
    public static final String MAIN_JS_CLASS                  = "AdminOrderList";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    public static final String LIST_TABLE_ID                  = "orderListTable";
    public static final String LIST_REFRESH_BODY_ID           = "orderListRefreshBody";
    public static final String SEARCH_BTN_ID                  = "searchBtn";
    public static final String SEARCH_PANEL_ID                = "searchPanel";
    public static final String SHOW_SEARCH_PANEL_BTN_ID       = "showSearchPanelBtn";
    public static final String HIDE_SEARCH_PANEL_BTN_ID       = "hideSearchPanelBtn";
    public static final String TABLE_HEIGHT_WHEN_HIDE_SEARCH  = "tableHeightWhenHideSearch";
    public static final String TABLE_HEIGHT_WHEN_SHOW_SEARCH  = "tableHeightWhenShowSearch";
    
    public static final String TABLE_BTN_USER_DETAIL          = "userDetail";
    public static final String TABLE_BTN_DETAIL               = "detail";
    public static final String TABLE_BTN_PUT_TO_REVIEW        = "putToReview";
    public static final String TABLE_BTN_START_REVIEW         = "startReview";
    public static final String SHOW_MORE_BTN_ID               = "showMore";
    public static final String HID_HIDE_SEARCH                = "hideSearch";
    public static final String HID_INVISABLE_SEARCH           = "inVisableSearch";
    public static final String HID_LIMIT                      = "limit";
    public static final String HID_OFFSET                     = "offset";
    public static final int    HEADER_HEIGHT                  = 360;
    public static final int    SEARCH_PANEL_HEIGHT            = 90;
    
    public static final int PHONE_TD_HEIGHT = 70;
    
    /** 頁LINK接頭辞ID */
    public static final String PAGE_LINK_ID_PREFIX = "orderPageLinkIdPrefix";
    
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            OrderSearchForm pageForm,
            OrderListOutput listOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, pageForm, listOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    public static ViewData refeshTable(
            HttpServletRequest request, 
            OrderSearchForm pageForm,
            OrderListOutput listOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));
        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, pageForm, listOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            OrderSearchForm pageForm,
            OrderListOutput listOutput) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb(pageForm));
        sb.append("<div id='" + SEARCH_PANEL_ID + "'>");
        sb.append(setCardForSearchPanel(request, pageForm));
        sb.append("</div>");
        sb.append(DivChevronSet.builder().idUp(HIDE_SEARCH_PANEL_BTN_ID).idDown(SHOW_SEARCH_PANEL_BTN_ID)
                .inVlisable(pageForm.isInVisableSearch()).build().html());
        sb.append("<div id='" + LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, pageForm, listOutput));
        sb.append("</div>");
        return getForm(FORM_NAME, sb.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb(OrderSearchForm pageForm) {
        String subCrum = getContext("menu.group2.button1");
        if (pageForm.getOrder().getCheckStatus().equals(CheckStatusType.WAIT_FOR_REVIEW.getKey())) {
            subCrum = getContext("menu.group2.button2");
        } else if (pageForm.getOrder().getCheckStatus().equals(CheckStatusType.REVIEW.getKey())) {
            subCrum = getContext("menu.group2.button3");
        } else if (pageForm.getOrder().getCheckStatus().equals(CheckStatusType.PASS.getKey())) {
            subCrum = getContext("menu.group2.button4");
        }
        String[] names = new String[] {getContext("menu.group2"), subCrum};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header SearchPanel -----------------
    
    private static String setCardForSearchPanel(HttpServletRequest request, OrderSearchForm pageForm) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleNoScroll("", CssClassType.SUCCESS, "", 
                "",
                setSearchPanel(request, pageForm)));
        
        return sbBody.toString();
    }
    private static String setSearchPanel(HttpServletRequest request, OrderSearchForm pageForm) {
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        sbBody.append(divRow().cellBlank(5));
        
        //name入力値
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
        String name      = clmForm.getPageName("");
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(nonNull(pageForm.getName()))
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //订单类型(F0015)選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> orderTypeList = fixedValueMap.get(FixedValueType.ORDER_TYPE);
        List<HtmlRadio> radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : orderTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(nonNull(pageForm.getOrder().getOrderType()))
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //开票状态(F0018)選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> billStatusList = fixedValueMap.get(FixedValueType.BILL_STATUS);
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : billStatusList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName).selectedValue(nonNull(pageForm.getOrder().getBillStatus()))
                .radios(radios).selectedValue(nonNull(pageForm.getOrder().getBillStatus()))
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //订单时间選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        placeholder = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getPlaceholder();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder).value(nonNull(pageForm.getPayDateFrom()))
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name      = clmForm.getPageName("") + "To";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.end");
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder).value(nonNull(pageForm.getPayDateTo()))
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(name).isBorderOnly(true)
                .grids(CssGridsType.G1).outPutType(ButtonSetType.NORMAL).gridFlexType(GridFlexType.LEFT)
                .iconSet(IconSet.builder().type(IconSetType.SEARCH).css(IconSetCss.NOMAL_10).build())
                .build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 2-------------]
        
        return sbBody.toString();
    }
    
    //--------------------body card table -----------------
    private static String setCardForTable(
            HttpServletRequest request, 
            OrderSearchForm pageForm,
            OrderListOutput listOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(setHidden(pageForm));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setListTable(request, pageForm, listOutput.getOrderList()));
        String cardTitle = getContext("admin.orderList.table.title");
        int startIndex = pageForm.getLimit()*(pageForm.getOrderPageLinkIdPrefixIndex());
        int endIndex = startIndex + listOutput.getOrderList().size();
        cardTitle += listOutput.getCount() > 0 ? 
                "(" + (startIndex + 1) + " - " + endIndex + "/" + 
                listOutput.getCount() + ")" : "";
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                cardTitle,
                divRow().cellBlank(5),cardBody.toString()));
        sbBody.append(getPageLinked(request, pageForm, listOutput));
        sbBody.append(divRow().cellBlank(5));
        return sbBody.toString();
    }
    

    private static String setHidden(OrderSearchForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(hidden().get(HID_HIDE_SEARCH,  String.valueOf(pageForm.isHideSearch())));
        sb.append(hidden().get(HID_INVISABLE_SEARCH,  String.valueOf(pageForm.isInVisableSearch())));
        sb.append(hidden().get(HID_LIMIT,  String.valueOf(pageForm.getLimit())));
        sb.append(hidden().get(HID_OFFSET, String.valueOf(pageForm.getOffset())));
        ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS);
        String name      = clmForm.getPageName(PREFIX_NAME);
        sb.append(hidden().get(name, String.valueOf(pageForm.getOrder().getCheckStatus())));
        return sb.toString();
    }

    private static String getPageLinked(HttpServletRequest request, OrderSearchForm pageForm, OrderListOutput listOutput) {
        PaginationHolder paginationHolder = new PaginationHolder(listOutput.getCount(), 
                pageForm.getOrderPageLinkIdPrefixIndex(), 
                GlobalConstants.DEFAULT_GROWING_CNT, 
                isPhoneMode(request)? GlobalConstants.SP_LINKCNT : GlobalConstants.PC_LINKCNT);
        paginationHolder.setPageLinkIdPrefix(PAGE_LINK_ID_PREFIX);
        return HtmlPageLinkedHelper.getPageLinkedHtml(paginationHolder);
    }
    
    private static String setListTable(
            HttpServletRequest request, 
            OrderSearchForm pageForm,
            List<OrderInfo> list) {

        //head
      //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
            // --col1--
            List<CssAlignType> aligs = new ArrayList<>();
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.CENTER);
            String subRow1 = divRow().get(CellWidthType.THREE_6_3_3, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName(), 
                                                                        T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName(),
                                                                        getContext("order.amountTile"));
            aligs = new ArrayList<>();
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.CENTER);
            String subRow2 = divRow().get(CellWidthType.THREE_6_3_3, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE).getLabelName(), 
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS).getLabelName(),
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS).getLabelName());
            aligs = new ArrayList<>();
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.CENTER);
            String subRow3 = divRow().get(CellWidthType.THREE_6_3_3, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE).getLabelName(),
            		"",
                                                                        getContext("common.page.do"));
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2, subRow3));
            // --]
        } else {
        	// --col1--
            ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
            String context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col2--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col3--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col4--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_AMOUNT);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col5--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col6--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_TYPE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col7--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col8--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col9--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col10--
            context         = getContext("common.page.do");
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
        }
        
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(list)) {
            for(int i=0; i<list.size(); i++) {
                OrderInfo orderInfo = list.get(i);
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
                
                String payTypeName = PTextSet.builder()
                        .context(orderInfo.getPayTypeName())
                        .classType(PayType.keyOf(orderInfo.getOrder().getPayType()).getClassType()).build().html();
                
                String name = nonNull(orderInfo.getUserName());
                String amount = formatCurrencyZH(orderInfo.getOrder().getOrderAmount());
                String payAmount = formatCurrencyZH(orderInfo.getOrder().getPayAmount());
                String payDate = orderInfo.getOrder().getPayDate();
                String btn = button().forTableBorderNameRight(IconSetType.DETAIL, CssClassType.INFO, 
                        "", getContext("admin.order.btn.userDetail"), orderInfo.getOrder().getUserId(), TABLE_BTN_USER_DETAIL);
                btn += isPhoneMode(request) ? "":"&nbsp;";
                btn += button().forTableBorderNameRight(IconSetType.DETAIL, CssClassType.INFO, 
                        "", getContext("common.page.info"), orderInfo.getId(), TABLE_BTN_DETAIL);
                btn += isPhoneMode(request) ? "":"&nbsp;";
                if (pageForm.getOrder().getCheckStatus().equals(CheckStatusType.WAIT_FOR_REVIEW.getKey())) {
                    btn += button().forTableBorderNameLeft(IconSetType.HAND_RIGHT, CssClassType.SUCCESS, 
                            "", getContext("admin.order.btn.putToReview"), orderInfo.getId(), TABLE_BTN_PUT_TO_REVIEW);
                } else if (pageForm.getOrder().getCheckStatus().equals(CheckStatusType.REVIEW.getKey())) {
                    btn += button().forTableBorderNameLeft(IconSetType.STAMP, CssClassType.DANGER, 
                            "", getContext("admin.order.btn.startToReview"), orderInfo.getId(), TABLE_BTN_START_REVIEW);
                }

                if (isPhoneMode(request)) {
                	if(Strings.isBlank(payAmount)) {
                		payAmount = StringUtility.padLeft(payAmount, 4, "-");
                	}
                    // --col1--
                    List<CssAlignType> aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String orderId = SpanTextSet.builder().fontSize(CssFontSizeType.LABEL_12)
        	                .context(orderInfo.getId()).build().html();
                    String subRow1 = divRow().get(CellWidthType.THREE_6_3_3, aligs, orderId, name, amount+"/"+payAmount);
                    aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String subRow2 = divRow().get(CellWidthType.THREE_6_3_3, aligs, orderTypeName, checkStatusName, billStatusName);
                    aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String subRow3 = divRow().get(CellWidthType.TWO_5_7, aligs, payDate, btn);
                            
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2, subRow3));
                } else {
                	// --col1--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, name));
                    // --col2--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getId()));
                    // --col3--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, amount));
                    // --col4--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, payAmount));
                    // --col5--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, orderTypeName));
                    // --col6--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, payTypeName));
                    // --col7--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, checkStatusName));
                    // --col8--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, billStatusName));
                    // --col9--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, payDate));
                    // --col10--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, btn));
                }
                bodyList.add(tr);
            }
        }
        
        return table().get(LIST_TABLE_ID, calcTableHeightWhenShowSearch(request), headTr, bodyList);
    }
    
    private static int calcTableHeightWhenShowSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT;
    }
    
    private static int calcTableHeightWhenHideSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - HEADER_HEIGHT + SEARCH_PANEL_HEIGHT;
    }
    
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",               URL_BASE + URL_C_INIT);
		js.put("url_order_list",         URL_BASE + URL_C_SEARCH);
		js.put("url_order_list_wait",    URL_BASE + URL_C_SEARCH_WAIT);
		js.put("url_order_list_review",  URL_BASE + URL_C_SEARCH_REVIEW);
		js.put("url_order_list_pass",    URL_BASE + URL_C_SEARCH_PASS);
		
		
		js.put("url_order_list_growing", URL_BASE + URL_C_GROWING);
		js.put("url_order_putToReview",  URL_BASE + URL_C_PUT_TO_REVIEW);
		
		

		return js;
	}
}
