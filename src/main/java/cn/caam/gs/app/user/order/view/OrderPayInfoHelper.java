package cn.caam.gs.app.user.order.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
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
import cn.caam.gs.common.enums.PayType;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.domain.db.base.entity.MSystemConfig;
import cn.caam.gs.domain.tabledef.impl.T400MSystemConfig;

@Component
public class OrderPayInfoHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/orderPay";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    
    public static final String MAIN_JS_CLASS                = "OrderPayInfo";
    public static final String FORM_NAME                    = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_CLOSE = "btnClose";
    
    public static final int    IMG_WIDTH                     = 380;
    public static final int    IMG_HEIGHT                    = 525;
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    
    
    public static ViewData getMainPage(HttpServletRequest request, String payType, MSystemConfig systemConfig) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, payType, systemConfig))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request, String payType, MSystemConfig systemConfig) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setCardForMainPanel(request, payType, systemConfig));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setCardForMainPanel(HttpServletRequest request, String payType, MSystemConfig systemConfig) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", PayType.ONLINE.getKey().equals(payType) ? 565 :230,
        		setMainPanel(request, payType, systemConfig)));
        
        return sbBody.toString();
    }
    private static String setMainPanel(HttpServletRequest request, String payType, MSystemConfig systemConfig) {
    	 StringBuffer sb = new StringBuffer();
    	 List<String> contextList = new ArrayList<String>();
    	 
    	if (PayType.ONLINE.getKey().equals(payType)) {
    		sb.append(divRow().cellBlank(15));
    		
    		 contextList = new ArrayList<String>();
    		 String src = Base64.encodeBase64String(systemConfig.getPayQrcode());
	        if (src == null) {
	            src = getNoImgDataString();
	        }
	        String context = LabelImageSet.builder()
	                .id("")
	                .imgWidth(IMG_WIDTH).imgHeight(IMG_HEIGHT)
	                .grids(CssGridsType.G12).aligntType(CssAlignType.CENTER)
	                .base64String(src).build().html();
	        contextList.add(context);
	        
	        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
    	        
    	}else {
    		sb.append(divRow().cellBlank(25));
    		
    		//账户名
            ColumnInfoForm clmForm = T400MSystemConfig.getColumnInfo(T400MSystemConfig.COL_ACCOUNT_NAME);
            String name      = clmForm.getPageName("");
            String id        = convertNameDotForId(name);
            String labelName = clmForm.getLabelName();
            String placeholder = clmForm.getPlaceholder();
            String value = nonNull(systemConfig.getAccountName());
            contextList.add(LabelInputSet.builder()
                    .id(id).name(name).labelName(labelName).value(value).readonly(true)
                    .placeholder(placeholder)
                    .fontSize(font).grids(CssGridsType.G12).build().html());
            
          //纳税人识别号
            clmForm = T400MSystemConfig.getColumnInfo(T400MSystemConfig.COL_CREDIT_CODE);
            name      = clmForm.getPageName("");
            id        = convertNameDotForId(name);
            labelName = clmForm.getLabelName();
            placeholder = clmForm.getPlaceholder();
            value = nonNull(systemConfig.getCreditCode());
            contextList.add(LabelInputSet.builder()
                    .id(id).name(name).labelName(labelName).value(value).readonly(true)
                    .placeholder(placeholder)
                    .fontSize(font).grids(CssGridsType.G12).build().html());
            
          //开户行
            clmForm = T400MSystemConfig.getColumnInfo(T400MSystemConfig.COL_OPENING_BANK);
            name      = clmForm.getPageName("");
            id        = convertNameDotForId(name);
            labelName = clmForm.getLabelName();
            placeholder = clmForm.getPlaceholder();
            value = nonNull(systemConfig.getOpeningBank());
            contextList.add(LabelInputSet.builder()
                    .id(id).name(name).labelName(labelName).value(value).readonly(true)
                    .placeholder(placeholder)
                    .fontSize(font).grids(CssGridsType.G12).build().html());
            
          //账号
            clmForm = T400MSystemConfig.getColumnInfo(T400MSystemConfig.COL_ACCOUNT_NUMBER);
            name      = clmForm.getPageName("");
            id        = convertNameDotForId(name);
            labelName = clmForm.getLabelName();
            placeholder = clmForm.getPlaceholder();
            value = nonNull(systemConfig.getAccountNumber());
            contextList.add(LabelInputSet.builder()
                    .id(id).name(name).labelName(labelName).value(value).readonly(true)
                    .placeholder(placeholder)
                    .fontSize(font).grids(CssGridsType.G12).build().html());
            
            sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
    	}
        
    	sb.append(divRow().cellBlank(5));
        return sb.toString();
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_CLOSE;
        String context = getContext("common.page.btn.close");
        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.ONE, aligs, comp2));
        
        return sb.toString();
    }
	
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",       URL_BASE + URL_C_INIT);
		
		return js;
	}
}
