package cn.caam.gs.app.admin.message.view;

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
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.tabledef.impl.T203MMessage;

@Component
public class AdminMessageDetailViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + "/messageDetail";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String MAIN_JS_CLASS                  = "AdminMessageDetail";
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
        List<String> contextList = new ArrayList<String>();
        
        //-----row 1-------------[
        
        //标题選択
        contextList.add(DivAlertSet.builder()
                .grids(CssGridsType.G12).classType(CssClassType.PRIMARY)
                .contexts(new String[] {message.getTitle()}).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 250,
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
        
        return js;
    }
}
