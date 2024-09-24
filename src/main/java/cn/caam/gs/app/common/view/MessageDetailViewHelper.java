package cn.caam.gs.app.common.view;

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
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.tabledef.impl.T203MMessage;

@Component
public class MessageDetailViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/messageDetail";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    //init url
    public static final String URL_C_GET_UN_READ_CNT = "/getUnReadCnt";  
    
    
    public static final String MAIN_JS_CLASS                  = "MessageDetail";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_CLOSE = "btnClose";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            MMessage message) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, message))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            MMessage message) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setCardPanel(request, message));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }

    //--------------------header Panel -----------------
    
    private static String setCardPanel(
            HttpServletRequest request,
            MMessage message) {
        StringBuffer sbBody = new StringBuffer();
        
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        List<FixValueInfo> msgTypeList = fixedValueMap.get(FixedValueType.MSG_TYPE);
        String msgTypeName = "";
        for (FixValueInfo fValueInfo : msgTypeList) {
            if (message.getMsgType().equals(fValueInfo.getValueObj().getValue())) {
                msgTypeName = PTextSet.builder()
                    .context(fValueInfo.getValueObj().getName())
                    .classType(MsgType.keyOf(message.getMsgType()).getClassType()).build().html();
            }
        }
        List<String> contextList = new ArrayList<String>();
        
        //-----row 1-------------[
        //类型
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.AROUND)
                .grids(CssGridsType.G12).classType(CssClassType.WARNING)
                .contexts(new String[] {msgTypeName, message.getRegistDate()}).build().html());
        //时间
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //标题選択
        contextList.add(DivAlertSet.builder()
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {message.getTitle()}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        contextList = new ArrayList<String>();
        //内容
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 200,
                setPanel(request, message)));
        
        return sbBody.toString();
    }
    private static String setPanel(
            HttpServletRequest request,
            MMessage message) {
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        
        //------row2----------[
        contextList = new ArrayList<String>();
        //消息内容
        contextList.add(DivAlertSet.builder()
                .grids(CssGridsType.G12).classType(CssClassType.SUCCESS)
                .contexts(new String[] {convertTextAreaContext(message.getMsg())}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        return sbBody.toString();
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
        js.put("url_init",         URL_BASE + URL_C_INIT); 
        js.put("url_getUnReadCnt", URL_BASE + URL_C_GET_UN_READ_CNT); 
        
        
        return js;
    }
}
