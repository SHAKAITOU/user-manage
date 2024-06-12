package cn.caam.gs.manage.admin.view.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.caam.gs.GlobalConstants;
import cn.caam.gs.app.bean.ViewData;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.html.element.CheckBoxSet;
import cn.caam.gs.common.html.element.CheckBoxSet.CheckBoxSetType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.IconSet;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.bs5.BreadCrumbSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.html.element.bs5.LabelDateRangeInputSet;
import cn.caam.gs.common.html.element.bs5.LabelDateRangeInputSet.LabelDateRangeInputSetType;
import cn.caam.gs.common.html.element.bs5.LabelInputSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet;
import cn.caam.gs.common.html.element.bs5.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.manage.dbmaintenance.table.impl.g01master.T100MUser;
import cn.caam.gs.manage.util.HtmlViewHelper;
import cn.caam.gs.manage.util.UrlConstants;

@Component
public class AdminUserSearchViewHelper extends HtmlViewHelper {
	
	public static final String URL_BASE = UrlConstants.ADMIN + UrlConstants.USER_LIST;
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	//init url
    public static final String URL_C_SEARCH = UrlConstants.SEARCH;
	
    public static final String MAIN_JS_CLASS                = "AdminUserManageList";
    public static final String USER_LIST_FORM_NAME          = "user_list_form";
    public static final String USER_LIST_CHECK_ALL_ID       = "user_list_check_all";
    public static final String USER_CHECK_PREF_ID           = "user_check_";
    public static final String USER_LIST_TABLE_ID           = "userListTable";
    public static final String USER_LIST_REFRESH_BODY_ID    = "userListRefreshBody";
    public static final String SEARCH_BTN_ID                = "searchBtn";
	
	   /**
     * main画面用
     * @param request
     * @return
     */
    public static ViewData getMainPage(HttpServletRequest request, 
            List<UserInfo> userList) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(SessionConstants.MAIN_FORM_ID.getValue(), 
                request.getSession().getAttribute(SessionConstants.MAIN_FORM_ID.getValue()));

        ViewData viewData = ViewData.builder()
                .pageContext(getMainPageContext(request, userList))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }
    
    public static ViewData refeshTable(HttpServletRequest request, 
            List<UserInfo> userList) {
        Map<String, Object> dataMap = new HashMap<>();
        ViewData viewData = ViewData.builder()
                .pageContext(setCardForTable(request, userList))
                .jsClassName(MAIN_JS_CLASS)
                .dataMap(dataMap)
                .build();
        return viewData;
    }

    private static String setCardForTable(
            HttpServletRequest request, 
            List<UserInfo> userList) {
        StringBuffer sbBody = new StringBuffer();
        sbBody.append(borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
                getContext("admin.userList.table.title"),
                divRow().cellBlank(5),
                setUserListTable(request, userList)));
        
        return sbBody.toString();
    }
    
    private static String getMainPageContext(HttpServletRequest request, 
            List<UserInfo> userList) {
        StringBuffer sb = new StringBuffer();
        sb.append(divRow().cellBlank(5));
        sb.append(setBreadCrumb());
        sb.append(setSearchPanel(request));
        sb.append("<div id='" + USER_LIST_REFRESH_BODY_ID + "'>");
        sb.append(setCardForTable(request, userList));
        sb.append("</div>");
        return getForm(USER_LIST_FORM_NAME, sb.toString());
    }
    
    
    private static String setBreadCrumb() {
        String[] names = new String[] {getContext("menu.group5"), getContext("menu.group5.button4")};
        return BreadCrumbSet.builder().labelNames(names).build().html();
    }
    
    private static String setSearchPanel(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        List<String> contextList = new ArrayList<String>();
        StringBuffer sbBody = new StringBuffer();
        String prefix_name = USER_LIST_FORM_NAME + ".";
        String prefix_label = T100MUser.TABLE_NAME + ".";
        CssFontSizeType font = GlobalConstants.FONT_SIZE;
        //name入力値
        String property  = "name";
        String name      = prefix_name + property;
        String labelName = getContext(prefix_label + property);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .fontSize(font).grids(CssGridsType.G2).build().html());
        
        //phone選択
        property  = "phone";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .fontSize(font).grids(CssGridsType.G2).build().html());

        List<HtmlRadio> radios = new ArrayList<>();
        String selectedValue = "00";
        
        //employer入力値
        property  = "employer";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .fontSize(font).grids(CssGridsType.G3).build().html());

        //regist_date選択
        property  = "regist_date";
        String propertyFrom  = property + "_from";
        String propertyTo    = property + "_to";
        String nameFrom      = prefix_name + propertyFrom;
        String nameTo        = prefix_name + propertyTo;
        labelName = getContext(prefix_label + property);
        contextList.add(LabelDateRangeInputSet.builder()
                .idFrom(convertNameDotForId(nameFrom)).nameFrom(nameFrom)
                .idTo(convertNameDotForId(nameTo)).nameTo(nameTo).labelName(labelName)
                .fontSize(font).grids(CssGridsType.G4).outPutType(LabelDateRangeInputSetType.WITH_LABEL_FOOT).build().html());

        //row 1
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        contextList = new ArrayList<String>();
        //id選択
        property  = "id";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        contextList.add(LabelInputSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .fontSize(font).grids(CssGridsType.G2).build().html());
        
        //user_type選択
        property  = "user_type";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        List<FixValueInfo> userTypeList = fixedValueMap.get(FixedValueType.USER_TYPE);
        selectedValue = "00";
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(selectedValue, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : userTypeList) {
            if (fValueInfo.getValueObj().getValue().equals(UserType.PERSON.getKey())) {
                for (MFixedValue fValue : fValueInfo.getSubList()) {
                    radios.add(new HtmlRadio(fValue.getValue(), fValue.getName()));
                }
                break;
            }
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(selectedValue)
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
 
        //edu_degree選択
        property  = "edu_degree";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        List<FixValueInfo> edu_degreeList = fixedValueMap.get(FixedValueType.EDU_DEGREE);
        selectedValue = "00";
        radios = new ArrayList<>();
        radios.add(new HtmlRadio(selectedValue, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : edu_degreeList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(selectedValue)
                .fontSize(font).grids(CssGridsType.G1).outPutType(LabelSelectSetType.WITH_LABEL).build().html());

        //political選択
        property  = "political";
        name      = prefix_name + property;
        labelName = getContext(prefix_label + property);
        List<FixValueInfo> politicalList = fixedValueMap.get(FixedValueType.POLITICAL);
        
        radios.add(new HtmlRadio(selectedValue, getContext("AvailabilityType.ALL")));
        for (FixValueInfo fValueInfo : politicalList) {
            radios.add(new HtmlRadio(fValueInfo.getValueObj().getValue(), fValueInfo.getValueObj().getName()));
        }
        contextList.add(LabelSelectSet.builder()
                .id(convertNameDotForId(name)).name(name).labelName(labelName)
                .radios(radios).selectedValue(selectedValue)
                .fontSize(font).grids(CssGridsType.G2).outPutType(LabelSelectSetType.WITH_LABEL).build().html());
        
        //valid_end_date選択
        property  = "valid_end_date";
        propertyFrom  = property + "_from";
        propertyTo    = property + "_to";
        nameFrom      = prefix_name + propertyFrom;
        nameTo        = prefix_name + propertyTo;
        labelName = getContext(prefix_label + property);
        contextList.add(LabelDateRangeInputSet.builder()
                .idFrom(convertNameDotForId(nameFrom)).nameFrom(nameFrom)
                .idTo(convertNameDotForId(nameTo)).nameTo(nameTo).labelName(labelName)
                .fontSize(font).grids(CssGridsType.G4).outPutType(LabelDateRangeInputSetType.WITH_LABEL_FOOT).build().html());
        
        //search btn
        labelName = getContext("common.page.search");
        contextList.add(ButtonSet.builder()
                .id(SEARCH_BTN_ID).buttonName(labelName)
                .grids(CssGridsType.G1).outPutType(ButtonSetType.GRID_STYLE).gridFlexType(GridFlexType.CENTER)
                .iconSet(IconSet.builder().type(IconSetType.SEARCH).css(IconSetCss.NOMAL_10).build())
                .build().html());
        //row 2
        sbBody.append(divRow().get(contextList.toArray(new String[contextList.size()])));
        
        return sbBody.toString();
    }
    
    private static String setUserListTable(HttpServletRequest request, 
            List<UserInfo> userList) {
        int [] widths = new int[] {
            40,  //check
            150, //会员ID
            140, //会员名称
            100, //会员类型
            120, //手机号
            150, //电子邮箱
            150, //工作单位
            200, //入会时间
            200, //有效结束日期
            200  //操作
        };
        int index = 0;
        //head
        TrSet headTr = tr().head(CssClassType.LIGHT);
        // --[
        // --col1--
        String context = CheckBoxSet.builder().id(USER_LIST_CHECK_ALL_ID)
                .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col2--
        context         = getContext("m_user.id");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col3--
        context         = getContext("m_user.name");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col4--
        context         = getContext("m_user.user_type");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col5--
        context         = getContext("m_user.phone");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col6--
        context         = getContext("m_user.mail");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col7--
        context         = getContext("m_user.employer");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col8--
        context         = getContext("m_user.regist_date");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col9--
        context         = getContext("m_user.valid_end_date");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --col10--
        context         = getContext("common.page.do");
        headTr.addTh(th().get(widths[index++], context, CssAlignType.CENTER));
        // --]
        
        //body
        List<TrSet> bodyList = new ArrayList<>();
        if(Objects.nonNull(userList)) {
            for(int i=0; i<userList.size(); i++) {
                index = 0;
                UserInfo userInfo = userList.get(i);
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("rowDataKey", String.valueOf(userInfo.getId()));
                TrSet tr = tr().row(properties);
                // --col1--
                context = CheckBoxSet.builder().id(USER_CHECK_PREF_ID + i)
                        .outPutType(CheckBoxSetType.SINGLE_FOR_TABLE).build().html();
                tr.addTd(td().get(widths[index++], context, CssAlignType.CENTER));
                // --col2--
                context = nonNull(userInfo.getId());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.CENTER));
                // --col3--
                context = nonNull(userInfo.getUser().getName());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.CENTER));
                // --col4--
                context = nonNull(userInfo.getUserTypeName());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.CENTER));
                // --col5--
                context = nonNull(userInfo.getUser().getPhone());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.CENTER));
                // --col6--
                context = nonNull(userInfo.getUser().getMail());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.LEFT));
                // --col7--
                context = nonNull(userInfo.getUser().getEmployer());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.LEFT));
                // --col8--
                context = nonNull(userInfo.getUser().getRegistDate());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.CENTER));
                // --col9--
                context = nonNull(userInfo.getUser().getValidEndDate());
                tr.addTd(td().withTrimWidth(widths[index++], context, CssAlignType.CENTER));
                // --col9--
                context = button().forTableBorderNameLeft(IconSetType.REFRESH, CssClassType.INFO, 
                        "", getContext("admin.userList.btn.resetPw"), userInfo.getId(), "restPw");
                context += "&nbsp;&nbsp;";
                context += button().forTableBorderNameLeft(IconSetType.DETAIL, CssClassType.INFO, 
                        "", getContext("admin.userList.btn.detail"), userInfo.getId(), "detail");
                tr.addTd(td().get(widths[index++], context, CssAlignType.CENTER));
                
                bodyList.add(tr);
            }
        }
        
        return table().get(USER_LIST_TABLE_ID, calcTableWidth(widths), calcTableHeight(request), headTr, bodyList);
    }
    
    private static int calcTableHeight(HttpServletRequest request) {
        return LoginInfoHelper.getMediaHeight(request) - 200;
    }
    
    private static int calcTableWidth(int [] widths) {
        int totalWidth = 0;
        for (int width : widths) {
            totalWidth += width;
        }
        return totalWidth + 20;
    }
	
	public static Map<String, String> getJsProperties() {
		Map<String, String> js = new HashMap<String, String>();
		// url
		js.put("url_init",      URL_BASE + URL_C_INIT);
		js.put("url_user_list", URL_BASE + URL_C_SEARCH);		
		
		return js;
	}
}
