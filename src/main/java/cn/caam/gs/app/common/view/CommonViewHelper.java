package cn.caam.gs.app.common.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.LabelImageSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.domain.db.base.entity.MUserExtend;

@Component
public class CommonViewHelper extends HtmlViewBaseHelper {

    // base url
    public static final String URL_BASE = "/common";
    // init url
    public static final String URL_WINDOW_RESIZE = "/winResize";
    // init url
    public static final String URL_SHOW_PHOTO = "/showPhoto";
    // init url
    public static final String URL_CHECK_USER_PHONE_NUMBER = "/checkPhoneNumberExist";
    // init url
    public static final String URL_CHECK_USER_EMAIL = "/checkEmailExist";
    
    // js class
    public static final String JS_CLASS = "ShowPhoto";
    // js class
    public static final String FORM_NAME = "showPhotoForm";   
    
    // js class
    public static final String PARAM_USER_ID = "userId";
    
    public static final String IMG_ID    = "showImg";
    
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_ADD   = "btnAdd";
    public static final String BTN_MINUS = "btnMinus";

    // js class
    public static final int CARD_HEIGHT = 400;

    public static ViewData getShowPhotoPage(HttpServletRequest request, MUserExtend userInfo) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder()
                .pageContext(getShowPhotoBody(request, userInfo))
                .jsClassName(JS_CLASS)
                .dataMap(dataMap).build();
        return viewData;
    }
    
    private static String getShowPhotoBody(HttpServletRequest request, MUserExtend userInfo) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(getShowPhotoCard(request, userInfo));
        sb.append(buildFooter());

        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String getShowPhotoCard(HttpServletRequest request, MUserExtend userInfo) {
        StringBuffer sb = new StringBuffer();
        String src = Base64.encodeBase64String(userInfo.getPhoto());
        String ext = userInfo.getPhotoExt();
        List<String> contextList = new ArrayList<String>();
        String context = LabelImageSet.builder()
                .id(IMG_ID)
                .imgWidth(calcImgWidth(request)).imgHeight(CARD_HEIGHT - 40)
                .grids(CssGridsType.G10)
                .base64String(src).extent(ext).build().html();
        contextList.add(context);
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));

        return borderCard().noTitleWithScroll("", CssClassType.WARNING, "", calcCardMaxWidth(request), CARD_HEIGHT,                        
                        divContainer().get(sb.toString())).toString();
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = "btnAdd";
        String comp1 = button().getBorder(IconSetType.PLUS, CssClassType.SUCCESS, id);
        
        id = "btnMinus";
        String comp2 = button().getBorder(IconSetType.MINUS, CssClassType.SUCCESS, id);
        aligs.add(CssAlignType.LEFT);
        
        id = BTN_CLOSE;
        String context = getContext("common.page.btn.close");
        String comp3 = button().getBorder(IconSetType.CLOSE, CssClassType.DARK, id, context);

        aligs.add(CssAlignType.RIGHT);
        sb.append(DivHrSet.builder().build().html());
        sb.append(divRow().get(CellWidthType.TWO_7_5, aligs, concactWithSpace(comp1, comp2), comp3));
        
        return sb.toString();
    }
    
    private static int calcCardMaxWidth(HttpServletRequest request) {
        if (LoginInfoHelper.getMediaWidth(request) < 500) {
            return LoginInfoHelper.getMediaWidth(request) - 20;
        } else {
            return 500 - 20;
        }
    }
    
    private static int calcImgWidth(HttpServletRequest request) {
        return calcCardMaxWidth(request) - 50;
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_com_win_resize",      URL_BASE + URL_WINDOW_RESIZE);
        js.put("url_com_show_photo",      URL_BASE + URL_SHOW_PHOTO);
        js.put("url_com_check_phone_number_exist",      URL_BASE + URL_CHECK_USER_PHONE_NUMBER);
        js.put("url_com_check_email_exist",      URL_BASE + URL_CHECK_USER_EMAIL);
   
        return js;
    }
}
