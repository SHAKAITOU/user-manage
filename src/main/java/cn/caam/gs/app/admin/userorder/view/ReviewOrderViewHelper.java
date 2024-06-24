package cn.caam.gs.app.admin.userorder.view;

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
import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.view.OrderDetailViewHelper;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.HtmlViewHelper;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.OrderMethodType;
import cn.caam.gs.common.enums.OrderType;
import cn.caam.gs.common.enums.PayType;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet.DivContainerSetType;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet.LabelImageSetType;
import cn.caam.gs.common.html.element.bs5.PTextSet;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.tabledef.impl.T200MOrder;
import cn.caam.gs.domain.tabledef.impl.T202MImage;

@Component
public class ReviewOrderViewHelper extends HtmlViewHelper {
    // base url
    public static final String URL_BASE = UrlConstants.ADMIN + "/reviewOrder";
    
    //init url
    public static final String URL_C_INIT = UrlConstants.INIT;
    
    public static final String URL_C_REVIEW_OK = "/ok"; 
    public static final String URL_C_REVIEW_NG = "/ng"; 
    
    public static final String MAIN_JS_CLASS                  = "ReviewOrder";
    public static final String FORM_NAME                      = MAIN_JS_CLASS + "Form";
    
    public static final String SHOW_ORDER_IMG_BTN_ID          = "showOrderImg";
    public static final String ORDER_IMG_ID                   = "orderImg";
    public static final String SHOW_BILL_IMG_BTN_ID           = "showBillImgId";
    public static final String BILL_IMG_ID                    = "billImg";
    
    public static final int IMG_WIDTH                      = 300;
    public static final int IMG_HEIGHT                     = 250;
    
    public static final int PHONE_CARD_HEIGHT              = 270;
    
    public static final String BTN_REVIEW_OK = "btnReviewOk";
    public static final String BTN_REVIEW_NG = "btnReviewNg";
    public static final String BTN_BACK = "btnBack";
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(
            HttpServletRequest request, 
            OrderInfo orderInfo) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, orderInfo))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(
            HttpServletRequest request, 
            OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setHidden(orderInfo));
        sb.append(setBreadCrumb(orderInfo));
        sb.append(divRow().cellBlank(5));
        sb.append(OrderDetailViewHelper.setOrderCardPanel(request, orderInfo, calcCardHeight(request)));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setHidden(OrderInfo orderInfo) {
        StringBuffer sb = new StringBuffer();
        ColumnInfoForm clmForm = T200MOrder.getColumnInfo(T200MOrder.COL_ID);
        String name      = clmForm.getPageName("");
        sb.append(hidden().get(name, orderInfo.getId()));
        return sb.toString();
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb(OrderInfo orderInfo) {
        String[] names = new String[] {getContext("menu.group2"), getContext("menu.group2.button3"), 
                getContext("menu.group2.button3.review", orderInfo.getId())};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    //--------------------header Panel -----------------
    
    
    private static int calcCardHeight(HttpServletRequest request) {
        if (isPhoneMode(request)) {
            return PHONE_CARD_HEIGHT;
        }
        return 470;
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_REVIEW_OK;
        String context = getContext("admin.order.btn.reviewOk");
        String comp1 = button().getBorder(IconSetType.CHECK, CssClassType.SUCCESS, id, context);
        
        id = BTN_REVIEW_NG;
        context = getContext("admin.order.btn.reviewNg");
        String comp2 = button().getBorder(IconSetType.CLOSE, CssClassType.DANGER, id, context);
        
        id = BTN_BACK;
        context = getContext("common.page.btn.back");
        String comp3 = button().getBorder(IconSetType.BACK, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.LEFT);
        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.TWO_6_6, aligs, concactWithSpace(comp1,comp2), comp3));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_init",      URL_BASE + URL_C_INIT);
        js.put("url_review_ok", URL_BASE + URL_C_REVIEW_OK);
        js.put("url_review_ng", URL_BASE + URL_C_REVIEW_NG);
        
        return js;
    }
}
