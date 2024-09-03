package cn.caam.gs.app.admin.adminmanage.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.admin.adminmanage.form.AdminUserDetailForm;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.AdminUserType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelPasswordSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.tabledef.impl.T000MAdmin;

/**
 * View helper.
 */
@Component
public class AdminManageEditViewHelper extends HtmlViewBaseHelper {
    // base url
	public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.ADMIN_LIST;
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    public static final String URL_C_INIT_LOGIN_ADMIN = "/initLogin";
    public static final String URL_C_INIT_ADD 		= "/initAdd";
    //init url
    public static final String URL_C_EDIT = UrlConstants.EDIT; 
    public static final String URL_C_EDIT_LOGIN_AMDIN = "/editLogin";  
    public static final String URL_C_ADD = UrlConstants.ADD;  
    
    public static final int    IMG_WIDTH                     = 200;
    public static final int    IMG_HEIGHT                    = 200;
    
    public static final String MAIN_JS_CLASS  = "AdminManageEdit";
    public static final String FORM_NAME      = MAIN_JS_CLASS + "Form";
    public static final String PREFIX_NAME    = "userInfo.user.";
    public static final String HID_MODE		  = "hid_mode";
    
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_OK   = "btnOk";
    public static final int MODE_EDIT = 1;
    public static final int MODE_ADD = 2;
    public static final int MODE_LOGINING_EDIT = 3;
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage( HttpServletRequest request, AdminUserDetailForm userDetailForm, int mode) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userDetailForm, mode))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, AdminUserDetailForm userDetailForm, int mode) {
        StringBuffer sb = new StringBuffer();
        // ----------hidden------[
        sb.append(setHidden(request, userDetailForm, mode));
        sb.append(divRow().cellBlank(5));
        sb.append(setCardForOrderPanel(request, userDetailForm, mode));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }

    private static String setCardForOrderPanel(HttpServletRequest request, AdminUserDetailForm userDetailForm, int mode) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", mode==MODE_EDIT ? 460:580,
        		setCardPanel(request, userDetailForm, mode)));
        
        return sbBody.toString();
    }
    //--------------------header Panel -----------------
    
    private static String setHidden(HttpServletRequest request, AdminUserDetailForm userDetailForm, int mode) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T000MAdmin.getColumnInfo(T000MAdmin.COL_ID);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String value     = userDetailForm.getUserInfo().getUser().getId() != null ? userDetailForm.getUserInfo().getUser().getId():"";
        sb.append(hidden().get(name, value));
        sb.append(hidden().get(clmForm.getName(), value));
        sb.append(hidden().get(HID_MODE, String.valueOf(mode)));
        return sb.toString();
    }
    
    private static String setCardPanel(
            HttpServletRequest request, AdminUserDetailForm userDetailForm, int mode) {
    	 @SuppressWarnings("unchecked")
         Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                 (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
    	 
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        sbBody.append(divRow().cellBlank(5));
        
        //会员名称(max70)
        ColumnInfoForm clmForm = T000MAdmin.getColumnInfo(T000MAdmin.COL_NAME);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String value = getValue(userDetailForm.getUserInfo().getUser().getName());
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.USER_NAME_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        if (mode == MODE_ADD) {
        	//password入力値
	        contextList = new ArrayList<String>();
	        clmForm = T000MAdmin.getColumnInfo(T000MAdmin.COL_PASSWORD);
	        name      = clmForm.getPageName(PREFIX_NAME);
	        labelName = clmForm.getLabelName();
	        placeholder = clmForm.getLabelName();
	        value = getValue(userDetailForm.getUserInfo().getPassword());
	        String btnId = convertNameDotForId(name) + "Show";
	        contextList.add(LabelPasswordSet.builder()
	                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
	                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
	                .buttonId(btnId).showIcon(true)
	                .fontSize(font).grids(CssGridsType.G12).build().html());
	        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
	        
	        contextList = new ArrayList<String>();
	        //password重复入力値
	        name      = clmForm.getPageName(PREFIX_NAME) + "Rep";
	        labelName = getContext("m_admin.passwordRep");
	        placeholder = getContext("m_admin.password.placeholder");
	        value = getValue(userDetailForm.getUserInfo().getPasswordRep());
	        btnId = convertNameDotForId(name) + "Show";
	        contextList.add(LabelPasswordSet.builder()
	                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
	                .notBlank(true).maxlength(GlobalConstants.USER_PW_MAX_L).placeholder(placeholder)
	                .buttonId(btnId).showIcon(true)
	                .fontSize(font).grids(CssGridsType.G12).build().html());
	        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        }
        
        if (mode != MODE_LOGINING_EDIT) {
	        contextList = new ArrayList<String>();
	        //user_type選択
	        clmForm = T000MAdmin.getColumnInfo(T000MAdmin.COL_USER_TYPE);
	        name      = clmForm.getPageName(PREFIX_NAME);
	        labelName = clmForm.getLabelName();
	        List<FixValueInfo> userTypeList = fixedValueMap.get(FixedValueType.AUTH_LEVEL);
	        List<HtmlRadio> radios = new ArrayList<HtmlRadio>();
	        for (FixValueInfo fValueInfo : userTypeList) {
	        	if (!AdminUserType.SUPER.getKey().equals(fValueInfo.getValueObj().getValue())) {
	        		radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
	        	}
	        }
	        contextList.add(LabelSelectSet.builder()
	                .id(convertNameDotForId(name)).name(name).labelName(labelName)
	                .radios(radios).selectedValue(value)
	                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
	        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        }
        
        contextList = new ArrayList<String>();
        //phone入力値
        clmForm = T000MAdmin.getColumnInfo(T000MAdmin.COL_PHONE);
        name      = clmForm.getPageName(PREFIX_NAME);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = getValue(userDetailForm.getUserInfo().getUser().getPhone());
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.PHONE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        
        //mail入力値
        clmForm = T000MAdmin.getColumnInfo(T000MAdmin.COL_MAIL);
        name      = clmForm.getPageName(PREFIX_NAME);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        value = getValue(userDetailForm.getUserInfo().getUser().getMail());
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(value)
                .notBlank(true).maxlength(GlobalConstants.MAIL_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        //头像
        contextList = new ArrayList<String>();
        clmForm     = T000MAdmin.getColumnInfo(T000MAdmin.COL_PHOTO);
        name        = clmForm.getPageName("") + "File";
        String idFile      = convertNameDotForId(name);
        String idFileOpen  = idFile + "Open";
        String idLbl       = idFile + "Lbl";
        String idFileName  = idFile + "Name";
        String idOldFile   = idFile + "Old";
        
        value       = "";
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).disabled(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G9).build().html());
        String context = getContext("common.page.showImg");
        String  comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G3, idFileOpen, context);
        contextList.add(comp1);
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        String src = Base64.encodeBase64String(userDetailForm.getUserInfo().getPhoto());
        String ext = userDetailForm.getUserInfo().getPhotoExt();
        if (src == null) {
            src = getNoImgDataString();
            ext = "jpeg";
        }
        context = LabelImageSet.builder()
                .id(idOldFile)
                .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT)
                .grids(CssGridsType.G6)
                .base64String(src).extent(ext).build().html();
        contextList.add(context);
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sbBody.append(divRow().cellBlank(5));
        
        return sbBody.toString();
    }
    
    private static String buildFooter() {
    	 StringBuffer sb = new StringBuffer();
         List<CssAlignType> aligs = new ArrayList<>();
         
         String id = BTN_OK;
         String context = getContext("common.page.ok");
         String comp1 = button().getBorder(IconSetType.EDIT, CssClassType.SUCCESS, id, context);
         
         id = BTN_CLOSE;
         context = getContext("common.page.btn.close");
         String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);

         aligs.add(CssAlignType.RIGHT);
         sb.append(DivHrSet.builder().build().html());
         sb.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
         
         return sb.toString();
    }
    
    private static String getValue(String value) {
    	return Objects.isNull(value) ? "":value;
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",	URL_BASE + URL_C_INIT); 
        js.put("url_init_login",	URL_BASE + URL_C_INIT_LOGIN_ADMIN); 
        js.put("url_init_add",	URL_BASE + URL_C_INIT_ADD); 
        js.put("url_edit",	URL_BASE + URL_C_EDIT); 
        js.put("url_edit_login",	URL_BASE + URL_C_EDIT_LOGIN_AMDIN);
        js.put("url_add",	URL_BASE + URL_C_ADD); 
        
        return js;
    }
}
