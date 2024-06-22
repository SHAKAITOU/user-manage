package cn.caam.gs.app.admin.message.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.admin.message.form.AdminMessageSearchForm;
import cn.caam.gs.app.admin.message.output.AdminMessageListOutput;
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
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.enums.OrderType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.IconSet;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.SpanTextSet;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.MessageInfo;
import cn.caam.gs.domain.tabledef.impl.T203MMessage;

@Component
public class AdminMessageSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + "/message";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
    
    public static final String URL_C_GROWING = UrlConstants.GROWING;
    
    public static final String PREFIX_NAME                    = "message.";
	
    public static final String MAIN_JS_CLASS                  = "AdminMessageSearch";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    public static final String LIST_TABLE_ID                  = "messageListTable";
    public static final String LIST_REFRESH_BODY_ID           = "messageListRefreshBody";
    public static final String SEARCH_BTN_ID                  = "searchBtn";
    public static final String SEARCH_PANEL_ID                = "searchPanel";
    public static final String SHOW_SEARCH_PANEL_BTN_ID       = "showSearchPanelBtn";
    public static final String HIDE_SEARCH_PANEL_BTN_ID       = "hideSearchPanelBtn";
    public static final String TABLE_HEIGHT_WHEN_HIDE_SEARCH  = "tableHeightWhenHideSearch";
    public static final String TABLE_HEIGHT_WHEN_SHOW_SEARCH  = "tableHeightWhenShowSearch";

    public static final String TABLE_BTN_DETAIL               = "detail";
    public static final String SHOW_MORE_BTN_ID               = "showMore";
    public static final String ADD_BTN_ID                     = "btnAdd";
    public static final String HID_HIDE_SEARCH                = "hideSearch";
    public static final String HID_LIMIT                      = "limit";
    public static final String HID_OFFSET                     = "offset";
    public static final int    HEADER_HEIGHT                  = 450;
    public static final int    SEARCH_PANEL_HEIGHT            = 180;
    
    public static final int PHONE_TD_HEIGHT = 45;
    
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            AdminMessageSearchForm pageForm,
            AdminMessageListOutput listOutput) {
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
            AdminMessageSearchForm pageForm,
            AdminMessageListOutput listOutput) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TABLE_HEIGHT_WHEN_HIDE_SEARCH, calcTableHeightWhenHideSearch(request));
        dataMap.put(TABLE_HEIGHT_WHEN_SHOW_SEARCH, calcTableHeightWhenShowSearch(request));
        ViewData viewData = ViewData.builder()
                .pageContext(setCardForTable(request, pageForm, listOutput))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            AdminMessageSearchForm pageForm,
            AdminMessageListOutput listOutput) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb());
        sb.append("<div id='" + SEARCH_PANEL_ID + "'>");
        sb.append(setCardForSearchPanel(request));
        sb.append("</div>");
        sb.append(DivChevronSet.builder().idUp(HIDE_SEARCH_PANEL_BTN_ID).idDown(SHOW_SEARCH_PANEL_BTN_ID).build().html());
        sb.append("<div id='" + LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, pageForm, listOutput));
        sb.append("</div>");
        return getForm(FORM_NAME, sb.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("menu.group5"), getContext("menu.group5.button1")};
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
        sbBody.append(divRow().cellBlank(5));
        List<HtmlRadio> radios = new ArrayList<>();

        //-----row 1-------------[
        List<String> contextList = new ArrayList<String>();

        //站内消息类型(F0021)入力値
        ColumnInfoForm clmForm = T203MMessage.getColumnInfo(T203MMessage.COL_MSG_TYPE);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        List<FixValueInfo> msgTypeList = fixedValueMap.get(FixedValueType.MSG_TYPE);
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(GlobalConstants.DFL_SELECT_ALL, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : msgTypeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //会员号(M/TYYMMDDHHmmSSR2)選択
        clmForm = T203MMessage.getColumnInfo(T203MMessage.COL_USER_ID);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.USER_ID_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //消息时间(yyyy-MM-dd HH選択
        clmForm = T203MMessage.getColumnInfo(T203MMessage.COL_REGIST_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        placeholder = clmForm.getPlaceholder();
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

        //-----row 1-------------]
        
        return sbBody.toString();
    }
    
    //--------------------body card table -----------------
    private static String setCardForTable(
            HttpServletRequest request, 
            AdminMessageSearchForm pageForm,
            AdminMessageListOutput listOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(setHidden(pageForm));
        cardBody.append(setShowMore(request, listOutput));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, listOutput.getMessageList()));
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                getContext("admin.messageList.table.title"),
                divRow().cellBlank(5),cardBody.toString()));
        
        return sbBody.toString();
    }
    

    private static String setHidden(AdminMessageSearchForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(hidden().get(HID_HIDE_SEARCH,  String.valueOf(pageForm.isHideSearch())));
        sb.append(hidden().get(HID_LIMIT,  String.valueOf(pageForm.getLimit())));
        sb.append(hidden().get(HID_OFFSET, String.valueOf(pageForm.getOffset())));
        return sb.toString();
    }

    private static String setShowMore(
            HttpServletRequest request, 
            AdminMessageListOutput listOutput) {
        StringBuffer sbBody = new StringBuffer();
        
        
        List<CssAlignType> aligs = new ArrayList<>();
        String context = getContext("common.page.showMore");
        String comp1 = button().getBorder(IconSetType.BAR, CssClassType.INFO, SHOW_MORE_BTN_ID, context, 
                listOutput.getMessageList().size() >= listOutput.getCount());
        
        context = getContext("common.page.btn.close");
        context = "[" + listOutput.getMessageList().size() + "/" + listOutput.getCount() + "]";
        String comp2 = SpanTextSet.builder().classType(CssClassType.CONTEXT).fontSize(font).context(context).build().html();
        aligs.add(CssAlignType.LEFT);
        
        context = getContext("admin.messageList.sendAll");
        aligs.add(CssAlignType.RIGHT);
        String comp3 = button().getBorder(IconSetType.BULLHORN, CssClassType.SUCCESS, ADD_BTN_ID, context);
        sbBody.append(divRow().get(CellWidthType.TWO_6_6, aligs, concactWithSpace(comp1, comp2), comp3));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    private static String setUserListTable(
            HttpServletRequest request, 
            List<MessageInfo> list) {

        //head
        TrSet headTr = tr().head(CssClassType.INFO);
        // --[
        if (isPhoneMode(request)) {
            List<CssAlignType> aligs = new ArrayList<>();
            aligs.add(CssAlignType.LEFT);
            aligs.add(CssAlignType.RIGHT);
            String subRow1 = divRow().get(CellWidthType.TWO_4_8, aligs, T203MMessage.getColumnInfo(T203MMessage.COL_MSG_TYPE).getLabelName(), 
                                                                        T203MMessage.getColumnInfo(T203MMessage.COL_REGIST_DATE).getLabelName());
            String subRow2 = divRow().get(CellWidthType.ONE,     aligs, T203MMessage.getColumnInfo(T203MMessage.COL_TITLE).getLabelName());
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G10, CssAlignType.LEFT, subRow1, subRow2));
            String context         = getContext("common.page.do");
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G2, CssAlignType.CENTER, context));
        } else {
            // --[
            // --col1--站内消息类型(F0021)
            String context  = T203MMessage.getColumnInfo(T203MMessage.COL_MSG_TYPE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
            // --col2--标题
            context         = T203MMessage.getColumnInfo(T203MMessage.COL_TITLE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G7, CssAlignType.CENTER, context));
            // --col3--消息时间(yyyy-MM-dd HH
            context         = T203MMessage.getColumnInfo(T203MMessage.COL_REGIST_DATE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G3, CssAlignType.CENTER, context));
            // --col4--操作
            context         = getContext("common.page.do");
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
            // --]
        }
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(list)) {
            for(int i=0; i<list.size(); i++) {
                MessageInfo info = list.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(info.getId()));
                TrSet tr = tr().row(properties);
                String msgType = PTextSet.builder()
                        .context(info.getMsgTypeName())
                        .classType(MsgType.keyOf(info.getMessage().getMsgType()).getClassType()).build().html();
                String registDt = info.getMessage().getRegistDate();
                String title = trimFitForTd(CssGridsType.G10.getKey(), info.getMessage().getTitle(), true);
                String btnDetail = button().forTableBorderNameRight(IconSetType.DETAIL, CssClassType.INFO, 
                        "", getContext("admin.userList.btn.detail"), info.getId(), TABLE_BTN_DETAIL);
                if (isPhoneMode(request)) {
                    List<CssAlignType> aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String subRow1 = divRow().get(CellWidthType.TWO_4_8, aligs, msgType, registDt);
                    String subRow2 = divRow().get(CellWidthType.ONE,     aligs, title);
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G10, CssAlignType.LEFT, subRow1, subRow2));
                    // --col4--
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G2, CssAlignType.CENTER, btnDetail));
                } else {
                    // --col1--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, msgType));
                    // --col2--
                    tr.addTd(td().get(CssGridsType.G7, CssAlignType.LEFT, title));
                    // --col3--
                    tr.addTd(td().withTrim(CssGridsType.G3, CssAlignType.CENTER, registDt));
                    // --col4--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, btnDetail));
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
		js.put("url_init",              URL_BASE + URL_C_INIT);
		js.put("url_message_list",         URL_BASE + URL_C_SEARCH);
		js.put("url_message_list_growing", URL_BASE + URL_C_GROWING);

		return js;
	}
}
