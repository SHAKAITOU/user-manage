package cn.caam.gs.app.admin.userbill.view;

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
import cn.caam.gs.common.enums.AcceptFileType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;
import cn.caam.gs.domain.tabledef.impl.T201MBill;
import cn.caam.gs.domain.tabledef.impl.T202MImage;

@Component
public class RefundViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/refund";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_ADD = UrlConstants.ADD;
    
    public static final String PREFIX_ORDER_NAME            = "order.";
    public static final String PREFIX_NAME                  = "bill.";
    public static final String PREFIX_IMAGE_NAME            = "image.";
	
    public static final String MAIN_JS_CLASS                = "Refund";
    public static final String FORM_NAME                    = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_ADD   = "btnAdd";
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request, 
            OrderInfo orderInfo) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, orderInfo))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request, 
            OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden(orderInfo));
        sb.append(setCardForOrderPanel(request, orderInfo));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden(OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
        String name      = clmForm.getPageName(PREFIX_ORDER_NAME);
        sb.append(hidden().get(name, String.valueOf(orderInfo.getOrder().getId())));
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_USER_ID);
        name      = clmForm.getPageName(PREFIX_ORDER_NAME);
        sb.append(hidden().get(name, String.valueOf(orderInfo.getOrder().getUserId())));
        return sb.toString();
    }

    //--------------------header Panel -----------------
    
    private static String setCardForOrderPanel(HttpServletRequest request, OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        //订单号(yyyyMMddHHmmssSSSR7)選択
        String labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ID).getLabelName() + UtilConstants.COLON;
        String context   = orderInfo.getOrder().getId();
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 1-------------]
        //-----row 2-------------[
        contextList = new ArrayList<String>();
        
        //会员号(M/TYYMMDDHHmmSSR2)
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_USER_ID).getLabelName() + UtilConstants.COLON;
        context   = orderInfo.getUserName() + "(" + orderInfo.getUserCode() + ")";
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        contextList = new ArrayList<String>();
        //订单金额
        labelName = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT).getLabelName() + UtilConstants.COLON;
        context   = formatCurrencyZH(orderInfo.getOrder().getOrderAmount());
        contextList.add(DivAlertSet.builder().gridFlexType(GridFlexType.LEFT)
                .grids(CssGridsType.G12).classType(CssClassType.INFO)
                .contexts(new String[] {labelName, context}).build().html());
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        sb.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 220,
                setOrderPanel(request, orderInfo)));
        
        return sb.toString();
    }
    private static String setOrderPanel(HttpServletRequest request, OrderInfo orderInfo) {

        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();

        //备注(max250)
        ColumnInfoForm clmForm     = T201MBill.getColumnInfo(T201MBill.COL_BILL_MEMO);
        String name        = clmForm.getPageName(PREFIX_NAME);
        String id          = convertNameDotForId(name);
        String labelName   = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(2).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row6----------]
        
        //------row7----------[
        contextList = new ArrayList<String>();

        //退款凭证图片
        clmForm     = T202MImage.getColumnInfo(T202MImage.COL_BILL_PHOTO);
        name        = clmForm.getPageName("") + "File";
        String idFile      = convertNameDotForId(name);
        String idFileOpen  = idFile + "Open";
        String idLbl       = idFile + "Lbl";
        String idFileName  = idFile + "Name";

        labelName   = getContext("admin.refund.photo");
        placeholder = getContext("m_user_extend.bachelor_at.placeholder");
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).notBlank(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row7----------]
        
        //------row8----------[
        contextList = new ArrayList<String>();
        
        labelName   = getContext("common.page.File");
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G8).build().html());

        
        String context = getContext("common.page.showImg");
        String comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G4, idFileOpen, context);
        contextList.add(comp1);
    
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row7----------]
        
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
		js.put("url_init",       URL_BASE + URL_C_INIT);
		js.put("url_refund_add",  URL_BASE + URL_C_ADD);		
		
		return js;
	}
}
