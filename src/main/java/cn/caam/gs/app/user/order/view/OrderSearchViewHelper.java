package cn.caam.gs.app.user.order.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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
import cn.caam.gs.common.enums.ReFundStatusType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.html.element.bs5.SpanTextSet;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;

@Component
public class OrderSearchViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/userOrder";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
    //init url
    public static final String URL_C_GROWING = UrlConstants.GROWING;
    
    public static final String PREFIX_NAME                    = "order.";
	
    public static final String MAIN_JS_CLASS                  = "OrderList";
    public static final String ORDER_LIST_FORM_NAME           = "userOrderListForm";
    public static final String ORDER_LIST_TABLE_ID            = "orderListTable";
    public static final String ORDER_LIST_REFRESH_BODY_ID     = "orderListRefreshBody";
    public static final String SEARCH_BTN_ID                  = "searchBtn";
    public static final String ADD_BTN_ID                     = "addBtn";
    public static final String SEARCH_PANEL_ID                = "searchPanel";
    public static final String SHOW_SEARCH_PANEL_BTN_ID       = "showSearchPanelBtn";
    public static final String HIDE_SEARCH_PANEL_BTN_ID       = "hideSearchPanelBtn";
    public static final String TABLE_HEIGHT_WHEN_HIDE_SEARCH  = "tableHeightWhenHideSearch";
    public static final String TABLE_HEIGHT_WHEN_SHOW_SEARCH  = "tableHeightWhenShowSearch";
    
    public static final String SHOW_MORE_BTN_ID               = "showMore";
    public static final String HID_HIDE_SEARCH                = "hideSearch";
    public static final String HID_LIMIT                      = "limit";
    public static final String HID_OFFSET                     = "offset";
    public static final int    HEADER_HEIGHT                  = 360;
    public static final int    SEARCH_PANEL_HEIGHT            = 90;
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    public static final int PHONE_TD_HEIGHT = 65;
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request, 
                                        OrderSearchForm pageForm,
                                        OrderListOutput orderListOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, pageForm, orderListOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    public static ViewData refeshTable(HttpServletRequest request, 
                                        OrderSearchForm pageForm,
                                        OrderListOutput orderListOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));
        ViewData viewData = ViewData.builder()
                .pageContext(setCardForTable(request, pageForm, orderListOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request, 
                                            OrderSearchForm pageForm,
                                            OrderListOutput orderListOutput) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb());
        sb.append("<div id='" + SEARCH_PANEL_ID + "'>");
        sb.append(setCardForSearchPanel(request));
        sb.append("</div>");
        sb.append(DivChevronSet.builder().idUp(HIDE_SEARCH_PANEL_BTN_ID).idDown(SHOW_SEARCH_PANEL_BTN_ID).build().html());
        sb.append("<div id='" + ORDER_LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, pageForm, orderListOutput));
        sb.append("</div>");
        return getForm(ORDER_LIST_FORM_NAME, sb.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("user.menu.group2"), getContext("user.menu.group2.button1")};
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
        List<String> contextList = new ArrayList<String>();
        sbBody.append(divRow().cellBlank(5));
        
        //-----row 1-------------[
        
        //订单类型(F0015)選択
        ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        List<FixValueInfo> orderTypeList = fixedValueMap.get(FixedValueType.ORDER_TYPE);
        List<HtmlRadio> radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : orderTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
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
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //订单时间選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        String placeholder = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getPlaceholder();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name      = clmForm.getPageName("") + "To";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.end");
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(name).isBorderOnly(true)
                .grids(CssGridsType.G1).outPutType(ButtonSetType.NORMAL).gridFlexType(GridFlexType.RIGHT)
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
            OrderListOutput orderListOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(setHidden(pageForm));
        cardBody.append(setShowMore(request, orderListOutput));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setListTable(request, orderListOutput.getOrderList()));

        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                getContext("admin.userList.table.title"),
                divRow().cellBlank(5),cardBody.toString()));
        
        return sbBody.toString();
    }
    
    private static String setHidden(OrderSearchForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(hidden().get(HID_HIDE_SEARCH,  String.valueOf(pageForm.isHideSearch())));
        sb.append(hidden().get(HID_LIMIT,  String.valueOf(pageForm.getLimit())));
        sb.append(hidden().get(HID_OFFSET, String.valueOf(pageForm.getOffset())));
        return sb.toString();
    }

    private static String setShowMore(HttpServletRequest request, 
            OrderListOutput orderListOutput) {
        StringBuffer sbBody = new StringBuffer();
        
        
        List<CssAlignType> aligs = new ArrayList<>();
        String id = SHOW_MORE_BTN_ID;
        String context = getContext("common.page.showMore");
        String comp1 = button().getBorder(IconSetType.BAR, CssClassType.INFO, id, context, 
                orderListOutput.getOrderList().size() >= orderListOutput.getCount());
        
        context = "[" + orderListOutput.getOrderList().size() + "/" + orderListOutput.getCount() + "]";
        String comp2 = SpanTextSet.builder().classType(CssClassType.CONTEXT).fontSize(font).context(context).build().html();
        aligs.add(CssAlignType.LEFT);
        
        id = ADD_BTN_ID;
        context = getContext("order.addPayBtn");
        String comp3 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, id, context);

        aligs.add(CssAlignType.RIGHT);
        sbBody.append(divRow().get(CellWidthType.TWO_7_5, aligs, concactWithSpace(comp1, comp2), comp3));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    private static String setListTable(HttpServletRequest request, 
            List<OrderInfo> orderList) {
        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
            // --col1--
            List<CssAlignType> aligs = new ArrayList<>();
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.RIGHT);
            String subRow1 = divRow().get(CellWidthType.TWO_6_6, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName(), 
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT).getLabelName());
            String subRow2 = divRow().get(CellWidthType.TWO_6_6, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE).getLabelName(), 
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS).getLabelName());
            String subRow3 = divRow().get(CellWidthType.TWO_4_8, aligs, T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS).getLabelName(),
                                                                        T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE).getLabelName());
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G9, CssAlignType.LEFT, subRow1, subRow2, subRow3));
            // --col2--
            String context         = getContext("common.page.do");
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G3, CssAlignType.CENTER, context));
            // --]
        } else {
            // --col1--
            ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
            String context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.LEFT, clmForm.getLabelName()));
            // --col2--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col3--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col4--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_CHECK_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col5--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col6--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, clmForm.getLabelName()));
            // --col7--
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
                if (isPhoneMode(request)) {
                    // --col1--
                    List<CssAlignType> aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String subRow1 = divRow().get(CellWidthType.TWO_8_4, aligs, orderInfo.getId(), orderInfo.getOrder().getOrderAmount().toString());
                    String subRow2 = divRow().get(CellWidthType.TWO_7_5, aligs, orderTypeName, checkStatusName);
                    String subRow3 = divRow().get(CellWidthType.TWO_4_8, aligs, billStatusName, orderInfo.getOrder().getPayDate());
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G9, CssAlignType.LEFT, subRow1, subRow2, subRow3));
                    // --col2--
                    String btnContext = button().forTableBorderNameLeft(IconSetType.DETAIL, CssClassType.INFO, 
                            "", getContext("common.page.info"), orderInfo.getId(), "detail");
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G3, CssAlignType.CENTER, btnContext));
                } else {
                    // --col1--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.LEFT, orderInfo.getId()));
                    // --col2--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getOrder().getOrderAmount().toString()));
                    // --col3--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, orderTypeName));
                    // --col4--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, checkStatusName));
                    // --col5--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, billStatusName));
                    // --col6--
                    tr.addTd(td().get(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getOrder().getPayDate()));
                    // --col7--
                    String context = button().forTableBorderNameRight(IconSetType.DETAIL, CssClassType.INFO, 
                            "", getContext("common.page.info"), orderInfo.getId(), "detail");
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, context));
                }
                bodyList.add(tr);
            }
        }
        
        return table().get(ORDER_LIST_TABLE_ID, calcTableHeightWhenShowSearch(request), headTr, bodyList);
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
		js.put("url_order_list_growing", URL_BASE + URL_C_GROWING);    
		
		return js;
	}
}
