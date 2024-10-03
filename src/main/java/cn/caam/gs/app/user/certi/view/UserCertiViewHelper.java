package cn.caam.gs.app.user.certi.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.user.detail.form.UserDetailForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.CertiImg1DivSet;
import cn.caam.gs.common.html.element.bs5.CertiImg2DivSet;
import cn.caam.gs.common.html.element.bs5.CertiImg3DivSet;
import cn.caam.gs.common.html.element.bs5.CertiImg4DivSet;
import cn.caam.gs.common.html.element.bs5.DivChevronSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet.DivContainerSetType;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateInputSet.LabelDateInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelFileSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelNumberSet;
import cn.caam.gs.common.html.element.bs5.LabelRadioGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectGroupSet.LabelSelectGroupSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T101MUserExtend;

/**
 * View helper.
 */
@Component
@Deprecated
public class UserCertiViewHelper extends HtmlViewBaseHelper {
    
    // base url
    public static final String URL_BASE = "/userCerti_";  
    // init url
    public static final String URL_USER_CERTI_INIT = UrlConstants.INIT;
    
    

    public static final String USER_DETAIL_JS_CLASS          = "UserCerti";
    public static final String USER_DETAIL_FORM_NAME         = "userCertiForm";
    
    public static final String PAGE1_PANEL_CARD_ID            = "page1PanelCard";
    public static final String PAGE2_PANEL_CARD_ID            = "page2PanelCard";
    public static final String PAGE3_PANEL_CARD_ID            = "page3PanelCard";
    public static final String PAGE3_QR_IMG_DIV_ID            = "page3QrImgDiv";
    public static final String PAGE4_PANEL_CARD_ID            = "page4PanelCard";
    public static final int    IMG_CARD_HEIGHT               = 530;
    public static final int    IMG_HEIGHT                    = 500;
    
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;

    public static ViewData getMainPage(
            HttpServletRequest request, 
            UserDetailForm userDetailForm) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder()
                .pageContext(getDetailContext(request, userDetailForm))
                .jsClassName(USER_DETAIL_JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }

    private static String getDetailContext(
            HttpServletRequest request, 
            UserDetailForm userDetailForm) {

        StringBuffer body = new StringBuffer();
        
        // hidden
        // ----------hidden------[
        body.append(setDetailHidden(userDetailForm));
        // ----------hidden------]
        // ----------hidden------[
        body.append(setBreadCrumb());
        // ----------hidden------[
        // body
        // ----------body------[
        body.append(buildPage1Body(userDetailForm));
        body.append(buildPage2Body(userDetailForm));
        body.append(buildPage3Body(userDetailForm));
        body.append(buildPage4Body(userDetailForm));
        // ----------body------]
        
        // --bottom btn--
        body.append(divRow().cellBlank(5));
        body.append(buildFooter());
        body.append(divRow().cellBlank(5));
        // --bottom btn--

        return getForm(USER_DETAIL_FORM_NAME, body.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("user.menu.group1"), getContext("user.menu.group1.button2")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    private static String setDetailHidden(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }
    
    private static String buildPage1Body(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildPage1(userDetailForm));
        return borderCard().noTitleWithScroll(PAGE1_PANEL_CARD_ID, CssClassType.WARNING, "", 
                IMG_CARD_HEIGHT,
                divContainer().get(sb.toString()));
    }
    
    private static String buildPage1(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        //------row1----------[
        
        String context = CertiImg1DivSet.builder().build().html();
        context = DivContainerSet.builder()
                .contexts(new String[] {context})
                .scrollHeight(IMG_HEIGHT)
                .outPutType(DivContainerSetType.SCROLL).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        return sb.toString();
    }
    
    private static String buildPage2Body(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildPage2(userDetailForm));
        
        return borderCard().noTitleWithScroll(PAGE2_PANEL_CARD_ID, CssClassType.WARNING, "", 
                IMG_CARD_HEIGHT,
                divContainer().get(sb.toString()));
    }

    private static String buildPage2(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        
        String context = CertiImg2DivSet.builder().build().html();
        context = DivContainerSet.builder()
                .contexts(new String[] {context})
                .scrollHeight(IMG_HEIGHT)
                .outPutType(DivContainerSetType.SCROLL).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return sb.toString();
    }
    
    private static String buildPage3Body(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildPage3(userDetailForm));
        return borderCard().noTitleWithScroll(PAGE3_PANEL_CARD_ID, CssClassType.WARNING, "", 
                IMG_CARD_HEIGHT,
                divContainer().get(sb.toString()));
    }
    
    private static String buildPage3(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        //------row1----------[
        
        String context = CertiImg3DivSet.builder()
                .imgSrc("/img/touxiang.png")
                .userId(userDetailForm.getUserInfo().getUser().getId())
                .registDate(userDetailForm.getUserInfo().getUser().getRegistDate())
                .validEndDate(userDetailForm.getUserInfo().getUser().getValidEndDate())
                .qrImgDivId(PAGE3_QR_IMG_DIV_ID)
                .name(userDetailForm.getUserInfo().getUser().getName())
                .sex(userDetailForm.getUserInfo().getSexName())
                .birth(userDetailForm.getUserInfo().getUser().getBirth())
                .employer(userDetailForm.getUserInfo().getEmployerTypeName())
                .jobTitle(userDetailForm.getUserInfo().getJobTitleName())
                .userType(userDetailForm.getUserInfo().getUserTypeName())
                .build().html();
        context = DivContainerSet.builder()
                .contexts(new String[] {context})
                .scrollHeight(IMG_HEIGHT)
                .outPutType(DivContainerSetType.SCROLL).build().html();
        contextList.add(context);
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        return sb.toString();
    }
    
    private static String buildPage4Body(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        
        sb.append(buildPage4(userDetailForm));
        return borderCard().noTitleWithScroll(PAGE4_PANEL_CARD_ID, CssClassType.WARNING, "", 
                IMG_CARD_HEIGHT,
                divContainer().get(sb.toString()));
    }
    
    private static String buildPage4(UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        List<String> contextList = new ArrayList<String>();
        //------row1----------[
        String vaildStartDt = LocalDateUtility.formatDateZH(userDetailForm.getUserInfo().getUser().getValidStartDate());
        String vaildEndDt = LocalDateUtility.formatDateZH(userDetailForm.getUserInfo().getUser().getValidEndDate());
        String context = CertiImg4DivSet.builder()
                .validStartDate(vaildStartDt)
                .validEndDate(vaildEndDt)
                .build().html();
        context = DivContainerSet.builder()
                .contexts(new String[] {context})
                .scrollHeight(IMG_HEIGHT)
                .outPutType(DivContainerSetType.SCROLL).build().html();
        contextList.add(context);
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        //------row2----------]
        
        return sb.toString();
    }

    private static String getValue(String value) {
        return Objects.isNull(value) ? "":value;
    }

    
    private static int calcCardMaxWidth(HttpServletRequest request) {
        if (LoginInfoHelper.getMediaWidth(request) < 500) {
            return LoginInfoHelper.getMediaWidth(request) - 20;
        } else {
            return 500 - 20;
        }
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

//        String id = "btnOk";
//        String context = getContext("common.page.ok");
//        String comp1 = button().getBorder(IconSetType.SEND, CssClassType.SUCCESS, id, context);
//
//        aligs.add(CssAlignType.RIGHT);
//        sb.append(divRow().get(CellWidthType.ONE, aligs, comp1));
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_user_certi_init",      URL_BASE + URL_USER_CERTI_INIT);
        return js;
    }
    
}
