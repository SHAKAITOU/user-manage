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
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.tabledef.impl.T203MMessage;

@Component
public class AdminMessagePushViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + "/messagePush";
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    //init url
    public static final String URL_C_ADD = UrlConstants.ADD;
    
    public static final String MAIN_JS_CLASS                  = "AdminMessagePush";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_ADD   = "btnAdd";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            MMessage pageForm) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, pageForm))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            MMessage pageForm) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setCardPanel(request));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }

    //--------------------header Panel -----------------
    
    private static String setCardPanel(HttpServletRequest request) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 350,
                setPanel(request)));
        
        return sbBody.toString();
    }
    private static String setPanel(HttpServletRequest request) {
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        
        //-----row 1-------------[
        
        //标题選択
        ColumnInfoForm clmForm = T203MMessage.getColumnInfo(T203MMessage.COL_TITLE);
        String name      = clmForm.getPageName("");
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).notBlank(true)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 1-------------]
        
        //------row2----------[
        contextList = new ArrayList<String>();

        //消息内容
        clmForm     = T203MMessage.getColumnInfo(T203MMessage.COL_MSG);
        name        = clmForm.getPageName("");
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(11).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        return sbBody.toString();
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_ADD;
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
        js.put("url_init",         URL_BASE + URL_C_INIT);
        js.put("url_message_add",  URL_BASE + URL_C_ADD);     
        
        return js;
    }
}
