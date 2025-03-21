package cn.caam.gs.app.user.order.view;

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
import cn.caam.gs.common.enums.AcceptFileType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.InvoiceType;
import cn.caam.gs.common.enums.OrderMethodType;
import cn.caam.gs.common.enums.OrderType;
import cn.caam.gs.common.enums.PayType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelNumberSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.domain.db.base.entity.MUserTypeSettings;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;
import cn.caam.gs.domain.tabledef.impl.T202MImage;

@Component
public class OrderViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = "/order";
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_ADD = UrlConstants.ADD;
    
    public static final String URL_C_PAY = UrlConstants.PAY;
    
    public static final String PREFIX_NAME                  = "order.";
    public static final String PREFIX_IMAGE_NAME            = "image.";
	
    public static final String MAIN_JS_CLASS                = "Order";
    public static final String FORM_NAME                    = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_PAY_QRCODE = "btnPayQrCode";
    public static final String BTN_PAY_BANK = "btnPayBank";
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_ADD   = "btnAdd";
    
    public static final int    IMG_WIDTH                     = 250;
    public static final int    IMG_HEIGHT                    = 250;
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request, 
            OrderInfo orderInfo,
            MUserTypeSettings mUserTypeSettings) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, orderInfo, mUserTypeSettings))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request, 
            OrderInfo orderInfo,
            MUserTypeSettings mUserTypeSettings) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setCardForOrderPanel(request, mUserTypeSettings));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    //--------------------header Panel -----------------
    
    private static String setCardForOrderPanel(HttpServletRequest request, MUserTypeSettings mUserTypeSettings) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 565,
                setOrderPanel(request, mUserTypeSettings)));
        
        return sbBody.toString();
    }
    private static String setOrderPanel(HttpServletRequest request, MUserTypeSettings mUserTypeSettings) {
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
        
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();

        
        //-----row 1-------------[
        
        //订单方式(F0020)選択
        ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_METHOD);
        String name      = clmForm.getPageName(PREFIX_NAME);
        String id        = convertNameDotForId(name);
        String labelName = clmForm.getLabelName();
        List<FixValueInfo> orderMethodList = fixedValueMap.get(FixedValueType.ORDER_METHOD);
        List<HtmlRadio> radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : orderMethodList) {
            if (fValueInfo.getValueObj().getValue().equals(OrderMethodType.PERSONAL.getKey())) {
                radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
            }
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(OrderMethodType.PERSONAL.getKey())
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 1-------------]
        //-----row 2-------------[
        contextList = new ArrayList<String>();
        
        //订单类型(F0015)選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> orderTypeList = fixedValueMap.get(FixedValueType.ORDER_TYPE);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : orderTypeList) {
            if (fValueInfo.getValueObj().getValue().equals(OrderType.JOIN.getKey()) ||
            		fValueInfo.getValueObj().getValue().equals(OrderType.RENEWAL.getKey())) {
                radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
            }
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(OrderType.RENEWAL.getKey())
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 2-------------]
        
        //-----row 3-------------[
        contextList = new ArrayList<String>();
        
        //缴费渠道(F0012)選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_PATH);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> memberShipPathList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : memberShipPathList) {
             radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_MEMBERSHIP_PATH_GANSU/*userInfo.getUser().getMembershipPath()*/)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 3-------------]
        
        //-----row 4-------------[
        contextList = new ArrayList<String>();
        
        //缴费类型(F0014)選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_PAY_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> payTypeList = fixedValueMap.get(FixedValueType.PAY_TYPE);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : payTypeList) {
             radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(PayType.ONLINE.getKey())
                .fontSize(font).grids(CssGridsType.G8).outPutType(LabelSelectSetType.WITH_LABEL).build().html());

        id = BTN_PAY_QRCODE;
        String context = getContext("order.pay.online");
        String comp1 = button().getBorder(IconSetType.PAY_QRCODE, CssClassType.SUCCESS, id, context);
//        contextList.add(comp1);
        
        id = BTN_PAY_BANK;
        context = getContext("order.pay.offline");
        comp1 += button().getBorder(IconSetType.PAY_BANK, CssClassType.SUCCESS, id, context);
        contextList.add(comp1);
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
//        List<CssAlignType> aligs = new ArrayList<>();
//        aligs.add(CssAlignType.LEFT);
//        aligs.add(CssAlignType.LEFT);
//        sbBody.append(divRow().get(CellWidthType.ONE, aligs, concactWithSpace(comp1, comp2)));
        //-----row 4-------------]
        
        //-----row 5-------------[
        contextList = new ArrayList<String>();
        //订单金额入力値
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ORDER_AMOUNT);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        String placeholder = clmForm.getPlaceholder();
        String orderAmount = "";
        if (mUserTypeSettings != null) {
        	orderAmount = String.valueOf(mUserTypeSettings.getFeeAmount().intValue());
        }
        contextList.add(LabelNumberSet.builder()
                .id(id).name(name).labelName(labelName).notBlank(true).value(orderAmount)
                .maxlength(GlobalConstants.AMOUNT_MAX_L).placeholder(placeholder).readonly(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 5-------------]
        
        //------row6----------[
        contextList = new ArrayList<String>();

        //备注(max250)
        clmForm     = T200MOrder.getColumnInfo(T200MOrder.COL_MEMO);
        name        = clmForm.getPageName(PREFIX_NAME);
        id          = convertNameDotForId(name);
        labelName   = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.LEARN_EXPERIENCE_MAX_L)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(2).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row6----------]
        
        //------row7----------[
        contextList = new ArrayList<String>();

        //订单图片
        clmForm     = T202MImage.getColumnInfo(T202MImage.COL_ORDER_PHOTO);
        name        = clmForm.getPageName("") + "File";
        String idFile      = convertNameDotForId(name);
        String idFileOpen  = idFile + "Open";
        String idLbl       = idFile + "Lbl";
        String idFileName  = idFile + "Name";
        String idOldFile   = idFile + "Old";
        
        String value       = "";
        labelName   = getContext("order.addPayImg");
        placeholder = getContext("m_user_extend.bachelor_at.placeholder");
        contextList.add(LabelInputSet.builder()
                .id(idFileName).labelName(labelName).placeholder(placeholder).notBlank(true)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row7----------]
        
        //------row8----------[
        contextList = new ArrayList<String>();
        
        labelName   = getContext("common.page.File");
        value       = "";
        contextList.add(LabelFileSet.builder()
                .id(idFile).idLablel(idLbl).name(name).labelName(labelName).placeholder(placeholder).acceptFileType(AcceptFileType.IMAGE)
                .fontSize(font).grids(CssGridsType.G8).build().html());

        
        context = getContext("common.page.showImg");
        comp1 = button().getBorder(IconSetType.EYE, CssClassType.INFO, CssGridsType.G4, idFileOpen, context);
        contextList.add(comp1);
    
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row8----------]
        
        //-----row 9-------------[
        contextList = new ArrayList<String>();
        
        //抬头类型(F0027)選択
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_INVOICE_TYPE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        List<FixValueInfo> invoiceTypeList = fixedValueMap.get(FixedValueType.INVOICE_TYPE);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : invoiceTypeList) {
             radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(InvoiceType.ORGANIZATION.getKey())
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());

        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 9-------------]
        
        //-----row 10-------------[
        contextList = new ArrayList<String>();
        //发票抬头入力値
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_INVOICE_TITLE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).notBlank(false)
                .maxlength(GlobalConstants.INVOICE_TITLE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 10-------------]
        
        //-----row 11-------------[
        contextList = new ArrayList<String>();
        //统一社会信用代码入力値
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_CREDIT_CODE);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(id).name(name).labelName(labelName).notBlank(false)
                .maxlength(GlobalConstants.CREDIT_CODE_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //-----row 11-------------]
        
        //-----row 12-------------[
        contextList = new ArrayList<String>();
        //mail入力値
        clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_MAIL);
        name      = clmForm.getPageName(PREFIX_NAME);
        id        = convertNameDotForId(name);
        labelName = clmForm.getLabelName();
        placeholder = clmForm.getPlaceholder();
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName).value(userInfo.getUser().getMail())
                .notBlank(false).maxlength(GlobalConstants.MAIL_MAX_L).placeholder(placeholder)
                .fontSize(font).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
      //-----row 12-------------]
        
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
		js.put("url_order_add",  URL_BASE + URL_C_ADD);		
		js.put("url_order_pay",  URL_BASE + URL_C_PAY);	
		
		return js;
	}
}
