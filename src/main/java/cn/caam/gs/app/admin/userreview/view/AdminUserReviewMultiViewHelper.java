package cn.caam.gs.app.admin.userreview.view;

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
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.bs5.DivHrSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T103MUserCheckHistory;

@Component
public class AdminUserReviewMultiViewHelper extends HtmlViewHelper {
    // base url
	public static final String URL_BASE = UrlConstants.ADMIN + "/reviewUser";
	//init url
	public static final String URL_C_INIT = "/initMulti";
	//init url
	public static final String URL_C_REVIEW = "/reviewMulti"; 
    
//    public static final String PREFIX_NAME                  = "order.";
//    public static final String PREFIX_IMAGE_NAME            = "image.";
	
    public static final String MAIN_JS_CLASS                = "AdminUserReviewMulti";
    public static final String FORM_NAME                    = MAIN_JS_CLASS + "Form";
    
    public static final String BTN_CLOSE	= "btnClose";
    public static final String BTN_OK		= "btnOk";
	
    public static final CssFontSizeType font = GlobalConstants.INPUT_FONT_SIZE;
    /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request, 
            String[] userIds) {
        Map<String, Object> dataMap = new HashMap<>();

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userIds))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    private static String getMainPageContext(HttpServletRequest request, 
    		String[] userIds) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setDetailHidden(userIds));
        sb.append(setCardForOrderPanel(request));
        sb.append(buildFooter());
        return getForm(FORM_NAME, sb.toString());
    }
    
    private static String setDetailHidden(String[] userIds) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<userIds.length; i++) {
        	 sb.append(hidden().get(AdminUserReviewSearchViewHelper.USER_CHECK_PREF_ID+i, 
        			 AdminUserReviewSearchViewHelper.USER_CHECK_PREF_ID, 
        			 userIds[i]));
        }
        return sb.toString();
    }

    //--------------------header Panel -----------------
    
    private static String setCardForOrderPanel(HttpServletRequest request) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", 350,
        		setReviewPanel(request, calcCardHeight(request))));
        
        return sbBody.toString();
    }
    private static String setReviewPanel(HttpServletRequest request, int cartHeight) {
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        
        StringBuffer sbBody = new StringBuffer();
        List<String> contextList = new ArrayList<String>();
        
        //-----row 1-------------[
        
        contextList = new ArrayList<String>();
        //审核意见(F0024)選択    
        ColumnInfoForm clmForm = T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS);
        String name        = clmForm.getPageName("");
        String id          = convertNameDotForId(name);
        String labelName = getContext("admin.userReview.review.opinion");
        List<HtmlRadio> radios = new ArrayList<>();
        List<FixValueInfo> checkStatusList = fixedValueMap.get(FixedValueType.USER_CHECK_STATUS);
        radios = new ArrayList<>();
        for (FixValueInfo fValueInfo : checkStatusList) {
        	if (!UserCheckStatusType.WAIT_FOR_REVIEW.getKey().equals(fValueInfo.getValueObj().getValue())) {
        		radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        	}
        }
        contextList.add(LabelSelectSet.builder()
                .id(id).name(name).labelName(labelName)
                .radios(radios).selectedValue(GlobalConstants.DFL_SELECT_ALL)
                .fontSize(font).grids(CssGridsType.G12).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        //审核建议(max150)
        clmForm     = T103MUserCheckHistory.getColumnInfo(T103MUserCheckHistory.COL_MEMO);
        name        = clmForm.getPageName("");
        id          = convertNameDotForId(name);
        labelName   = getContext("admin.userReview.review.opinion.memo");
        String placeholder = clmForm.getPlaceholder();
        contextList.add(LabelTextAreaSet.builder()
                .id(id).name(name).labelName(labelName)
                .maxlength(GlobalConstants.REVIEW_MEMO_MAX_L).height(200)
                .placeholder(placeholder).outPutType(LabelTextAreaSetType.WITH_LABEL)
                .fontSize(font).rows(2).grids(CssGridsType.G12).build().html());
        
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return sbBody.toString();
//        return borderCard().noTitleWithScroll("", CssClassType.SUCCESS, "", cartHeight,
//                sbBody.toString());
    }
    
    private static int calcCardHeight(HttpServletRequest request) {
        if (isPhoneMode(request)) {
            return 300;
        }
        return 500;
    }
    
    private static String buildFooter() {
        StringBuffer sb = new StringBuffer();
        List<CssAlignType> aligs = new ArrayList<>();
        // bottom button

        String id = BTN_OK;
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
		js.put("url_review_multi",  URL_BASE + URL_C_REVIEW);		
		
		return js;
	}
}
