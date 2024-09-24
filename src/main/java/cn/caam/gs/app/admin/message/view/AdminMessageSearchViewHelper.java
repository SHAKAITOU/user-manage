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
import cn.caam.gs.app.common.form.MessageSearchForm;
import cn.caam.gs.app.common.output.MessageListOutput;
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
import cn.caam.gs.common.util.PaginationHolder;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.MessageInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T105MUserCard;
import cn.caam.gs.domain.tabledef.impl.T203MMessage;

@Component
public class AdminMessageSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + "/message";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
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
    public static final int    HEADER_HEIGHT                  = 390;
    public static final int    SEARCH_PANEL_HEIGHT            = 180;
    
    public static final int PHONE_TD_HEIGHT = 70;
    
    /** 頁LINK接頭辞ID */
    public static final String PAGE_LINK_ID_PREFIX = "messagePageLinkIdPrefix";
    
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            MessageSearchForm pageForm,
            MessageListOutput listOutput) {
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
            MessageSearchForm pageForm,
            MessageListOutput listOutput) {
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
            MessageSearchForm pageForm,
            MessageListOutput listOutput) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb());
        sb.append("<div id='" + SEARCH_PANEL_ID + "'>");
        sb.append(setCardForSearchPanel(request, pageForm));
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
    
    private static String setCardForSearchPanel(HttpServletRequest request, MessageSearchForm pageForm) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleNoScroll("", CssClassType.SUCCESS, "", 
                "",
                setSearchPanel(request, pageForm)));
        
        return sbBody.toString();
    }
    private static String setSearchPanel(HttpServletRequest request, MessageSearchForm pageForm) {
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
                .radios(radios).selectedValue(pageForm.getMessage() != null ? nonNull(pageForm.getMessage().getMsgType()):"")
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //会员名称選択
        clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
        name      = "userName";//clmForm.getPageName("");
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
//        placeholder = clmForm.getPlaceholder();
        placeholder = getContext("common.search.input.prefixNname") + labelName;
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(nonNull(pageForm.getUserName()))
                .maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //会员号(M/TYYMMDDHHmmSSR2)選択
        clmForm = T105MUserCard.getColumnInfo(T105MUserCard.COL_USER_CODE);
        name      = clmForm.getPageName("");
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
//        placeholder = clmForm.getPlaceholder();
        placeholder = getContext("common.search.input.prefixNname") + labelName;
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(nonNull(pageForm.getUserCode()))
                .maxlength(GlobalConstants.USER_ID_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G3).build().html());
        
        //标题選択
        clmForm = T203MMessage.getColumnInfo(T203MMessage.COL_TITLE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
//        placeholder = clmForm.getPlaceholder();
        placeholder = getContext("common.search.input.prefixNname") + labelName;
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).value(pageForm.getMessage() != null ? nonNull(pageForm.getMessage().getTitle()):"")
                .maxlength(GlobalConstants.MESSAGE_TITLE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G4).build().html());
        
        //消息时间(yyyy-MM-dd HH選択
        clmForm = T203MMessage.getColumnInfo(T203MMessage.COL_REGIST_DATE);
        name      = clmForm.getPageName("") + "From";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.start");
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder).value(nonNull(pageForm.getRegistDateFrom()))
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name      = clmForm.getPageName("") + "To";
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName() + getContext("common.page.end");
        contextList.add(LabelDateInputSet.builder()
                .id(id).name(name).labelName(labelName).placeholder(placeholder).value(nonNull(pageForm.getRegistDateTo()))
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelDateInputSetType.WITH_LABEL_FOOT).build().html());
        
        name = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(name).isBorderOnly(true)
                .grids(CssGridsType.G1).outPutType(ButtonSetType.NORMAL).gridFlexType(GridFlexType.LEFT)
                .iconSet(IconSet.builder().type(IconSetType.SEARCH).css(IconSetCss.NOMAL_10).build())
                .build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));

        //-----row 1-------------]
        
        return sbBody.toString();
    }
    
    //--------------------body card table -----------------
    private static String setCardForTable(
            HttpServletRequest request, 
            MessageSearchForm pageForm,
            MessageListOutput listOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(setHidden(pageForm));
        cardBody.append(setShowMore(request, listOutput));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, listOutput.getMessageList()));
        String cardTitle = getContext("admin.messageList.table.title");
        int startIndex = pageForm.getLimit()*(pageForm.getMessagePageLinkIdPrefixIndex());
        int endIndex = startIndex + listOutput.getMessageList().size();
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
    

    private static String setHidden(MessageSearchForm pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(hidden().get(HID_HIDE_SEARCH,  String.valueOf(pageForm.isHideSearch())));
        sb.append(hidden().get(HID_LIMIT,  String.valueOf(pageForm.getLimit())));
        sb.append(hidden().get(HID_OFFSET, String.valueOf(pageForm.getOffset())));
        return sb.toString();
    }

    private static String setShowMore(
            HttpServletRequest request, 
            MessageListOutput listOutput) {
        StringBuffer sbBody = new StringBuffer();
        
        
        List<CssAlignType> aligs = new ArrayList<>();

        String context = getContext("admin.messageList.sendAll");
        aligs.add(CssAlignType.RIGHT);
        String comp3 = button().getBorder(IconSetType.BULLHORN, CssClassType.SUCCESS, ADD_BTN_ID, context);
        sbBody.append(divRow().get(CellWidthType.ONE, aligs, comp3));
        //-----row 3-------------]
        
        return sbBody.toString();
    }
    
    private static String getPageLinked(HttpServletRequest request, MessageSearchForm pageForm, MessageListOutput listOutput) {
        PaginationHolder paginationHolder = new PaginationHolder(listOutput.getCount(), 
                pageForm.getMessagePageLinkIdPrefixIndex(), 
                GlobalConstants.DEFAULT_GROWING_CNT, 
                isPhoneMode(request)? GlobalConstants.SP_LINKCNT : GlobalConstants.PC_LINKCNT);
        paginationHolder.setPageLinkIdPrefix(PAGE_LINK_ID_PREFIX);
        return HtmlPageLinkedHelper.getPageLinkedHtml(paginationHolder);
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
            String subRow1 = divRow().get(CellWidthType.ONE,     aligs, T203MMessage.getColumnInfo(T203MMessage.COL_TITLE).getLabelName());
            List<GridFlexType> flexs = new ArrayList<>();
            flexs.add(GridFlexType.LEFT);
            flexs.add(GridFlexType.LEFT);
            flexs.add(GridFlexType.RIGHT);
            String subRow2 = divRow().getFlex(CellWidthType.THREE_4_4_4, flexs, T203MMessage.getColumnInfo(T203MMessage.COL_MSG_TYPE).getLabelName(), 
                                                                        T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName(),
                                                                        T203MMessage.getColumnInfo(T203MMessage.COL_USER_ID).getLabelName());
            flexs = new ArrayList<>();
            flexs.add(GridFlexType.LEFT);
            flexs.add(GridFlexType.RIGHT);
            String subRow3 = divRow().getFlex(CellWidthType.TWO_6_6, flexs, T203MMessage.getColumnInfo(T203MMessage.COL_REGIST_DATE).getLabelName(),
                                                                        getContext("common.page.do"));
            headTr.addTh(th().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2, subRow3));
        } else {
            // --[
            // --col1--标题
            String context  = T203MMessage.getColumnInfo(T203MMessage.COL_TITLE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G5, CssAlignType.CENTER, context));
            // --col2--站内消息类型(F0021)
            context         = T203MMessage.getColumnInfo(T203MMessage.COL_MSG_TYPE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
            // --col3--会员名
            context         = T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName();
            headTr.addTh(th().get(CssGridsType.G1, CssAlignType.CENTER, context));
            // --col4--会员号(M/TYYMMDDHHmmSSR2)
            context         = T203MMessage.getColumnInfo(T203MMessage.COL_USER_ID).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col5--消息时间(yyyy-MM-dd HH
            context         = T203MMessage.getColumnInfo(T203MMessage.COL_REGIST_DATE).getLabelName();
            headTr.addTh(th().get(CssGridsType.G2, CssAlignType.CENTER, context));
            // --col6--操作
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
                String userCode = nonNull(info.getUserCode());
                String userName = nonNull(info.getUserName());
                String registDt = info.getMessage().getRegistDate();
                String title = trimFitForTd(CssGridsType.G12.getKey(), info.getMessage().getTitle(), true);
//                String title =  nonNull(info.getMessage().getTitle());
                String btnDetail = button().forTableBorderNameRight(IconSetType.DETAIL, CssClassType.INFO, 
                        "", getContext("admin.userList.btn.detail"), info.getId(), TABLE_BTN_DETAIL);
                if (isPhoneMode(request)) {
                    List<CssAlignType> aligs = new ArrayList<>();
                    aligs.add(CssAlignType.LEFT);
                    aligs.add(CssAlignType.RIGHT);
                    String subRow1 = divRow().get(CellWidthType.ONE,     aligs, title);
                    List<GridFlexType> flexs = new ArrayList<>();
                    flexs.add(GridFlexType.LEFT);
                    flexs.add(GridFlexType.LEFT);
                    flexs.add(GridFlexType.RIGHT);
                    String subRow2 = divRow().getFlex(CellWidthType.THREE_4_4_4, flexs, msgType, userName, userCode);
                    flexs = new ArrayList<>();
                    flexs.add(GridFlexType.LEFT);
                    flexs.add(GridFlexType.RIGHT);
                    String subRow3 = divRow().getFlex(CellWidthType.TWO_6_6, flexs, registDt, btnDetail);
                    tr.addTd(td().get(PHONE_TD_HEIGHT, CssGridsType.G12, CssAlignType.LEFT, subRow1, subRow2, subRow3));
                } else {
                    // --col1--
                    tr.addTd(td().withTooltip(CssGridsType.G5, title, CssAlignType.LEFT, title));
                    // --col2--
                    tr.addTd(td().get(CssGridsType.G1, CssAlignType.CENTER, msgType));
                    // --col3--
                    tr.addTd(td().withTrim(CssGridsType.G1, CssAlignType.CENTER, userName));
                    // --col4--
                    tr.addTd(td().withTrim(CssGridsType.G2, CssAlignType.CENTER, userCode));
                    // --col5--
                    tr.addTd(td().withTrim(CssGridsType.G2, CssAlignType.CENTER, registDt));
                    // --col6--
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
