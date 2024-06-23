package cn.caam.gs.app.user.message.view;

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
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.html.element.SpanSet.SpanSetType;
import cn.caam.gs.common.html.element.SpanSet;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.html.element.bs5.SpanTextSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.domain.db.custom.entity.MessageInfo;
import cn.caam.gs.domain.tabledef.impl.T203MMessage;

@Component
public class MessageSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = "/message";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
    
    public static final String URL_C_GROWING = UrlConstants.GROWING;
    
    public static final String PREFIX_NAME                    = "message.";
	
    public static final String MAIN_JS_CLASS                  = "MessageSearch";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    public static final String LIST_TABLE_ID                  = "messageListTable";
    public static final String LIST_REFRESH_BODY_ID           = "messageListRefreshBody";
    public static final String SEARCH_BTN_ID                  = "searchBtn";

    public static final String TABLE_BTN_DETAIL               = "detail";
    public static final String SHOW_MORE_BTN_ID               = "showMore";
    public static final String HID_HIDE_SEARCH                = "hideSearch";
    public static final String HID_LIMIT                      = "limit";
    public static final String HID_OFFSET                     = "offset";
    public static final int    HEADER_HEIGHT                  = 200;
    
    public static final int PHONE_TD_HEIGHT = 45;
    
	
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
        ViewData viewData = ViewData.builder()
                .pageContext(setCardForTable(request, pageForm, listOutput))
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
        sb.append("<div id='" + LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, pageForm, listOutput));
        sb.append("</div>");
        return getForm(FORM_NAME, sb.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("user.menu.group9.button1")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }
    
    //--------------------body card table -----------------
    private static String setCardForTable(
            HttpServletRequest request, 
            MessageSearchForm pageForm,
            MessageListOutput listOutput) {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer cardBody = new StringBuffer();
        cardBody.append(setHidden(pageForm));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setShowMore(request, listOutput));
        cardBody.append(divRow().cellBlank(5));
        cardBody.append(setUserListTable(request, listOutput.getMessageList()));
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                getContext("admin.messageList.table.title"),
                divRow().cellBlank(5),cardBody.toString()));
        
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
        String context = getContext("common.page.showMore");
        String comp1 = button().getBorder(IconSetType.BAR, CssClassType.INFO, SHOW_MORE_BTN_ID, context, 
                listOutput.getMessageList().size() >= listOutput.getCount());
        
        context = getContext("common.page.btn.close");
        context = "[" + listOutput.getMessageList().size() + "/" + listOutput.getCount() + "]";
        String comp2 = SpanTextSet.builder().classType(CssClassType.CONTEXT).fontSize(font).context(context).build().html();
        aligs.add(CssAlignType.LEFT);
        
        context = getContext("common.page.refresh");
        String comp3 = button().getBorder(IconSetType.REFRESH , CssClassType.INFO, SEARCH_BTN_ID, context, false);
        aligs.add(CssAlignType.RIGHT);

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
                String title = "";
                if (!info.isReadSts()) {
                    title += SpanSet.builder().context("未读").outPutType(SpanSetType.PILL).type(CssClassType.DANGER).build().html();
                }
                title += trimFitForTd(CssGridsType.G10.getKey(), info.getMessage().getTitle(), true);
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
    
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",              URL_BASE + URL_C_INIT);
		js.put("url_message_list",         URL_BASE + URL_C_SEARCH);
		js.put("url_message_list_growing", URL_BASE + URL_C_GROWING);

		return js;
	}
}
