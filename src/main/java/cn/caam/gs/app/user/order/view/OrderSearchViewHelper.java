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
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.IconSet;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
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
    
    public static final String PREFIX_NAME                  = "order.";
	
    public static final String MAIN_JS_CLASS                = "OrderList";
    public static final String ORDER_LIST_FORM_NAME         = "userOrderListForm";
    public static final String ORDER_LIST_TABLE_ID          = "orderListTable";
    public static final String ORDER_LIST_REFRESH_BODY_ID   = "orderListRefreshBody";
    public static final String SEARCH_BTN_ID                = "searchBtn";
    public static final String ADD_BTN_ID                   = "addBtn";
    public static final String SEARCH_PANEL_ID              = "searchPanel";
    public static final String SHOW_SEARCH_PANEL_BTN_ID     = "showSearchPanelBtn";
    public static final String HIDE_SEARCH_PANEL_BTN_ID     = "hideSearchPanelBtn";
    public static final String TABLE_HEIGHT_WHEN_HIDE_SEARCH     = "tableHeightWhenHideSearch";
    public static final String TABLE_HEIGHT_WHEN_SHOW_SEARCH     = "tableHeightWhenShowSearch";
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    public static final int PHONE_TD_HEIGHT = 65;
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request, 
            List<OrderInfo> orderList) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, orderList))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    public static ViewData refeshTable(HttpServletRequest request, 
            List<OrderInfo> orderList) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));
        ViewData viewData = ViewData.builder()
                .pageContext(setCardForTable(request, orderList))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request, 
            List<OrderInfo> orderList) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb());
        sb.append("<div id='" + SEARCH_PANEL_ID + "'>");
        sb.append(setCardForSearchPanel(request));
        sb.append("</div>");
        sb.append(DivChevronSet.builder().idUp(HIDE_SEARCH_PANEL_BTN_ID).idDown(SHOW_SEARCH_PANEL_BTN_ID).build().html());
        sb.append("<div id='" + ORDER_LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, orderList));
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
                .fontSize(font).grids(CssGridsType.G4).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //订单时间選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        String placeholder = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getPlaceholder();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name      = clmForm.getPageName("") + "To";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.end");
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 1-------------]
        //-----row 2-------------[
        contextList = new ArrayList<String>();
        
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
                .fontSize(font).grids(CssGridsType.G4).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        name = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(name).isBorderOnly(true)
                .grids(CssGridsType.G8).outPutType(ButtonSetType.NORMAL).gridFlexType(GridFlexType.RIGHT)
                .iconSet(IconSet.builder().type(IconSetType.SEARCH).css(IconSetCss.NOMAL_10).build())
                .build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    //--------------------body card table -----------------
    private static String setCardForTable(
            HttpServletRequest request, 
            List<OrderInfo> orderList) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        String name = getContext("order.addPayBtn");
        contextList.add(ButtonSet.builder()
                .id(ADD_BTN_ID).buttonName(name).isBorderOnly(true).classType(CssClassType.SUCCESS)
                .grids(CssGridsType.G12).outPutType(ButtonSetType.NORMAL).gridFlexType(GridFlexType.RIGHT)
                .iconSet(IconSet.builder().type(IconSetType.SEND).css(IconSetCss.NOMAL_10).build())
                .build().html());

        cardBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setListTable(request, orderList));

        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                getContext("admin.userList.table.title"),
                divRow().cellBlank(5),cardBody.toString()));
        
        return sbBody.toString();
    }
    
    private static String setListTable(HttpServletRequest request, 
            List<OrderInfo> orderList) {
        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
            // --col1--
            String context  = T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName();
            context  += "/" + T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT).getLabelName();
            context  += "<BR>" + T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS).getLabelName();
            context  += "/" + T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE).getLabelName();
            context  += "<BR>" + T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE).getLabelName();
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G9, context, CssAlignType.LEFT));
            // --col2--
            context         = getContext("common.page.do");
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G3, context, CssAlignType.CENTER));
            // --]
        } else {
            // --col1--
            ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
            String context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G3, clmForm.getLabelName(), CssAlignType.LEFT));
            // --col2--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, clmForm.getLabelName(), CssAlignType.CENTER));
            // --col3--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, clmForm.getLabelName(), CssAlignType.CENTER));
            // --col4--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_DATE);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, clmForm.getLabelName(), CssAlignType.CENTER));
            // --col5--
            clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_BILL_STATUS);
            context  = clmForm.getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, clmForm.getLabelName(), CssAlignType.CENTER));
            // --col6--
            context         = getContext("common.page.do");
            headTr.addTh(th().get(CssGridsType.G1, context, CssAlignType.CENTER));
        }
        
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(orderList)) {
            for(int i=0; i<orderList.size(); i++) {
                OrderInfo orderInfo = orderList.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(orderInfo.getId()));
                TrSet tr = tr().row(properties);
                if (isPhoneMode(request)) {
                    // --col1--
                    tr.addTd(td().withTrim(PHONE_TD_HEIGHT, CssGridsType.G9, CssAlignType.LEFT, 
                            orderInfo.getId() + "/" + orderInfo.getOrder().getOrderAmount().toString(), 
                            orderInfo.getOrderTypeName() + "/" + orderInfo.getBillStatusName(),
                            orderInfo.getOrder().getPayDate()));
                    // --col2--
                    String btnContext = button().forTableBorderNameLeft(IconSetType.DETAIL, CssClassType.INFO, 
                            "", getContext("common.page.info"), orderInfo.getId(), "detail");
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G3, CssAlignType.CENTER, btnContext));
                } else {
                    // --col1--
                    tr.addTd(td().withTrim(CssGridsType.G3, CssAlignType.LEFT, orderInfo.getId()));
                    // --col2--
                    tr.addTd(td().withTrim(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getOrder().getOrderAmount().toString()));
                    // --col3--
                    tr.addTd(td().withTrim(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getOrderTypeName()));
                    // --col4--
                    tr.addTd(td().withTrim(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getOrder().getPayDate()));
                    // --col5--
                    tr.addTd(td().withTrim(CssGridsType.G2, CssAlignType.CENTER, orderInfo.getBillStatusName()));
                    // --col6--
                    String context = button().forTableBorderNameLeft(IconSetType.DETAIL, CssClassType.INFO, 
                            "", getContext("common.page.info"), orderInfo.getId(), "detail");
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, context));
                }
                bodyList.add(tr);
            }
        }
        
        return table().get(ORDER_LIST_TABLE_ID, calcTableHeightWhenShowSearch(request), headTr, bodyList);
    }
    
    private static int calcTableHeightWhenShowSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - 450;
    }
    
    private static int calcTableHeightWhenHideSearch(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - 270;
    }
	
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",       URL_BASE + URL_C_INIT);
		js.put("url_order_list", URL_BASE + URL_C_SEARCH);		
		
		return js;
	}
}
