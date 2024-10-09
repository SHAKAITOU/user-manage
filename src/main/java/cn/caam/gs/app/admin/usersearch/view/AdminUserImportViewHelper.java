package cn.caam.gs.app.admin.usersearch.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.AcceptFileType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;

@Component
public class AdminUserImportViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.USER_IMPORT;
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_IMPORT = UrlConstants.IMPORT;
	
    public static final String MAIN_JS_CLASS                = "AdminUserImport";
    public static final String FORM_NAME                    = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_IMPORT   = "btnImport";
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden());
        sb.append(setCardForImportPanel(request));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden() {
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }

    //--------------------header Panel -----------------
    
    private static String setCardForImportPanel(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 110,
        		setImportPanel(request)));
        
        return sb.toString();
    }
    private static String setImportPanel(HttpServletRequest request) {

        StringBuffer sbBody = new StringBuffer();
        sbBody.append(divRow().cellBlank(10));
        
        List<String> contextList = new ArrayList<String>();

        //会员文件
        String name        = "importFile";
        String idFile      = convertNameDotForId(name);
//        String idFileOpen  = idFile + "Open";
        String idLbl       = idFile + "Lbl";
        String idFileName  = idFile + "Name";

        String labelName   = getContext("userImport.uploadFile");
        String placeholder = getContext("userImport.uploadFile.placeholder");
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).notBlank(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row7----------]
        
        //------row8----------[
        contextList = new ArrayList<String>();
        
        labelName   = getContext("common.page.File");
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).acceptFileType(AcceptFileType.EXCEL)
                .fontSize(font).grids(CssGridsType.G12).build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row7----------]
        
        return sbBody.toString();
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_IMPORT;
        String context = getContext("common.page.import");
        String comp1 = button().getBorder(IconSetType.IMPORT, CssClassType.DANGER, id, context);
        
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
		js.put("url_init",       URL_BASE + URL_C_INIT);
		js.put("url_import",  URL_BASE + URL_C_IMPORT);		
		
		return js;
	}
}
