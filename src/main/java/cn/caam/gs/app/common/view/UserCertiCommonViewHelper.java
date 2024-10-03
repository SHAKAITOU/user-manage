package cn.caam.gs.app.common.view;

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
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.UserInfoHelper;
import cn.caam.gs.common.bean.ViewData;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.DownloadFileType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.CertiImgDivSet;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.util.CertificateCreateUtil;
import cn.caam.gs.common.util.ImageUtil;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import lombok.extern.slf4j.Slf4j;

/**
 * View helper.
 */
@Slf4j
@Component
public class UserCertiCommonViewHelper extends HtmlViewBaseHelper {
    
    // base url
    public static final String URL_BASE = "/userCerti";  
    // init url
    public static final String URL_USER_CERTI_INIT = UrlConstants.INIT;
//    public static final String URL_USER_CERTI_DETAIL = UrlConstants.DETAIL;
//    public static final String URL_USER_CERTI_DOWNLOAD = UrlConstants.DOWNLOAD;
    
    public static final String USER_DETAIL_JS_CLASS          = "UserCommonCerti";
    public static final String USER_DETAIL_FORM_NAME         = "userCommonCertiForm";
    
    public static final String PAGE_PANEL_CARD_ID            = "page1PanelCard";
    public static final String BTN_DOWNLOAD                   = "btnDownloadCert";
    public static final int    IMG_CARD_HEIGHT               = 580;
    public static final int    IMG_WIDTH                     = 740;//740;
    public static final int    IMG_HEIGHT                    = 543;
    
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

    public static String getDetailContext(
            HttpServletRequest request, 
            UserDetailForm userDetailForm) {

        StringBuffer body = new StringBuffer();
        
        if (!Objects.isNull(userDetailForm) && 
        		!Objects.isNull(userDetailForm.getUserInfo()) &&
        			UserInfoHelper.isUserValid(userDetailForm.getUserInfo().getUser())) {
	        // hidden
	        // ----------hidden------[
	        body.append(setDetailHidden(userDetailForm));
	        // ----------hidden------]
	        // ----------hidden------[
	        body.append(setBreadCrumb());
	        // ----------hidden------[
	        // body
	        // ----------body------[
	        body.append(buildPageBody(request, userDetailForm));
	        // ----------body------]
	        
	        // --bottom btn--
	        body.append(divRow().cellBlank(5));
	        body.append(buildFooter());
	        body.append(divRow().cellBlank(5));
	        // --bottom btn--
        }

        return getForm(USER_DETAIL_FORM_NAME, body.toString());
    }
    
    //--------------------header BreadCrumb -----------------
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("user.menu.group1"), getContext("user.menu.group1.button2")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }

    private static String setDetailHidden(UserDetailForm userDetailForm) {
    	 StringBuffer sb = new StringBuffer();
         ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_ID);
         String name      = clmForm.getPageName("");
         String value     = nonNull(userDetailForm.getUserInfo().getUser().getId());
         sb.append(hidden().get(name, value));
         return sb.toString();
    }
    
    private static String buildPageBody(HttpServletRequest request, UserDetailForm userDetailForm) {
        StringBuffer sb = new StringBuffer();
        
        sb.append(divRow().cellBlank(5));
        sb.append(buildPage(request, userDetailForm));
        return borderCard().noTitleWithScroll(PAGE_PANEL_CARD_ID, CssClassType.WARNING, "", 
                isPhoneMode(request) ? 540:580,
                divContainer().get(sb.toString()));
    }
    
    public static String resizeImageToBase64(HttpServletRequest request, byte[] bytes) {
    	try {
    		if (isPhoneMode(request)) {
    			return ImageUtil.resizeImageToBase64(bytes, IMG_WIDTH, IMG_HEIGHT);
    		}else {
    			return Base64.encodeBase64String(bytes);
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static boolean isPhoneMode(HttpServletRequest request) {
	    int outWidth = LoginInfoHelper.getMediaWidth(request);
	    return outWidth > 800 ? false : true;
	}
    
    public static int getImageWidth(HttpServletRequest request) {
    	return isPhoneMode(request) ? LoginInfoHelper.getMediaWidth(request)-30 : 740;
    }
    
    public static int getImageHeight(HttpServletRequest request) {
    	return isPhoneMode(request) ? (int)(LoginInfoHelper.getMediaWidth(request)*543/740) : 543;
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
//        PaginationHolder paginationHolder = new PaginationHolder(4, 0, 1, 4);
//        paginationHolder.setPageLinkIdPrefix("PAGE_LINK_ID_PREFIX");
//        sb.append(HtmlImagePageLinkedHelper.getPageLinkedHtml(paginationHolder));
        
        List<String> contextList = new ArrayList<String>();
        String context = ButtonSet.builder()
	        .id(BTN_DOWNLOAD).buttonName(getContext("userCerti.download")).classType(CssClassType.SUCCESS)
	        .grids(CssGridsType.G12).gridFlexType(GridFlexType.CENTER)
	        .iconSet(IconSet.builder().type(IconSetType.DOWNLOAD).build()).build().html();
        contextList.add(context);
        
        sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        return sb.toString();
    }
    
    private static String buildPage(HttpServletRequest request, UserDetailForm userDetailForm) {
    	StringBuffer sb = new StringBuffer();
        UserType userType = UserType.keyOf(userDetailForm.getUserInfo().getUser().getUserType());
        List<DownloadFileType> imageList = UserInfoHelper.getCertficateImageList(userType);
        for(DownloadFileType downloadFileType:imageList) {
        	List<String> contextList = new ArrayList<String>();
        	String context = CertiImgDivSet.builder().id(downloadFileType.getKey())
        		.imgWidth(getImageWidth(request)).src(downloadFileType.getFilePath()).build().html();
        	Map<String, CertificateCreateUtil.DrawOject> map = UserInfoHelper.getMapDrawObject(request, userDetailForm.getUserInfo(), downloadFileType);
        	
        	if (map.size() >= 0) {
        		byte[] bytes = UserInfoHelper.drawCertImage(downloadFileType.getFilePath(), map);
        		  context = CertiImgDivSet.builder().id(downloadFileType.getKey()).visible(true)
        		        	.imgWidth(getImageWidth(request)).base64String(resizeImageToBase64(request, bytes))
        		        	.extent(downloadFileType.getFileType().replace(".", "")).build().html();
        	}
        	
        	contextList.add(context);
        	
        	sb.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        	
        	sb.append(divRow().cellBlank(10));
        }
        
        return sb.toString();
    }
    
    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_user_certi_init",      URL_BASE + URL_USER_CERTI_INIT);
//        js.put("url_user_certi_detail",    URL_BASE + URL_USER_CERTI_DETAIL);
//        js.put("url_user_certi_download",  URL_BASE + URL_USER_CERTI_DOWNLOAD);
        return js;
    }
    
}
