package cn.caam.gs.app.user;

import java.util.ArrayList;
import java.util.List;

public class UrlConstants {
	
	
	/* ===================resources========================= */
	public static final String JS 			= "/js";
	
	public static final String CSS 			= "/css";
	
	public static final String IMG 			= "/img";
	
	public static final String WEBFONTS 	= "/webfonts";
	
	/* ===================COMMON URL========================= */
	public static final String DB 						= "/db";
	//INDEX
	public static final String INDEX 					= "/login";
	//USER LOGIN
	public static final String USER_LOGIN 				= "/userLogin";
	//USER LOGIN
	public static final String USER_REGIST 				= "/userRegist";
	//ADMIN LOGIN
	public static final String LOGOUT 					= "/logout";
	
	public static final String MENU 					= "/menu";
	
	public static final String CATEGORY 				= "/category";
	//ACCESS DENIED
	public static final String ACCESS_DENIED 			= "/access-denied";
	
	//LOTO	
	public static final String HELP 		= "/help";
	public static final String SEARCH 		= "/search";
	public static final String PAGE 		= "/pageLink";
	public static final String INIT			= "/init";
	public static final String REGIST		= "/regist";
	public static final String UPLOAD		= "/upload";
	public static final String ANALYSIS		= "/analysis";
	public static final String ADD			= "/add";
	public static final String EDIT			= "/edit";
	public static final String DELETE		= "/delete";
	public static final String DETAIL 		= "/detail";
	public static final String DETAIL_LIST 	= "/detailList";
	public static final String SEARCH_CON 	= "/searchCon";
	public static final String CHECK	 	= "/check";
	public static final String OPEN	 		= "/open";
	public static final String CLOSE	 	= "/close";
	public static final String SELECT	 	= "/select";
	public static final String PDF			= "/pdf";
	public static final String EXCEL		= "/excel";
	public static final String ADD_CHECK	= "/addCheck";
	public static final String DELETE_ADD	= "/deleteAdd";
	public static final String PASSWORD		= "/password";
	public static final String KEEP			= "/keep";
	public static final String CHANGE	 	= "/change";
	public static final String MOVE		 	= "/move";
	public static final String DATA			= "/data";
	public static final String IMAGE		= "/image";
	public static final String PRINT		= "/print";
	public static final String GRAPH		= "/graph";
	public static final String BACK			= "/back";
	public static final String CLEAR		= "/clear";	
	public static final String LIST			= "/list";
	public static final String CARD			= "/card";	
	public static final String MANAGE		= "/manage";	
	public static final String CART			= "/cart";	
	public static final String SETTING		= "/setting";
	public static final String COMMIT       = "/commit";
    public static final String CONFIRM      = "/confirm";
	//BATCH
	public static final String BATCH 			= "/batch";
	/* ===================ADMIN ROLE URL========================= */

	//ADMIN
	public static final String ADMIN 			= "/admin";

	public static final String COMPANY 			= "/company";
	
	/* ===================USER ROLE URL========================= */
	//USER
	public static final String USER 			= "/user";
	public static final String PERSONAL 		= "/personal";
	
	public static final String COMMON 			= "/common";
	
	public static final String LIB 			= "/lib";
	
	
	public static final String STORE 			= "/store";
	
	public static final String STORE_PRICE 		= "/storePrice";
	
	public static final String AUTHORITY_GROUP	= "/authorityGroup";
	
	public static final String USER_AUTHORITY	= "/userAuthority";
	public static final String SEARCH_LEFT 		= "/searchLeft";
	public static final String SEARCH_RIGHT		= "/searchRight";
	
	public static final String BID 				= "/bid";
	
	public static final String CTGRY 			= "/ctgry";
	
	public static final String KIND 			= "/kind";
	
	public static final String BUY_PRODUCT 			= "/buyProduct";
	
	public static final String PRODUCT 			= "/product";
	
	public static final String PRODUCT_MANAGE 	= "/productManage";
	
	
	public static final String BUY_PRICE_HISTORY 	= "/buyPriceHistory";
	
	public static final String RECEIPT 			= "/receipt";
	
	//INDEX
	public static final String MESSAGE 			= "/message";
	//INDEX
	public static final String ACCOUNT 			= "/account";
	
	public static final String FAMILY_BUDGET 	= "/familyBudget";
	
	public static final String FAMILY_BUDGET_BILL 	= "/familyBudgetBill";
	
	private static List<String> commonUrlList;
	private static List<String> userUrlList;
	private static List<String> adminUrlList;
	static {
		commonUrlList = new ArrayList<String>();
		commonUrlList.add(JS);
		commonUrlList.add(CSS);
		commonUrlList.add(IMG);
		commonUrlList.add(WEBFONTS);
		commonUrlList.add(INDEX);
		commonUrlList.add(USER_LOGIN);
		commonUrlList.add(LOGOUT);
		commonUrlList.add(ACCESS_DENIED);
		
		
		userUrlList = new ArrayList<String>();
		userUrlList.addAll(commonUrlList);
		
		adminUrlList = new ArrayList<String>();
		adminUrlList.addAll(userUrlList);
		adminUrlList.add(ADMIN);
		adminUrlList.add(BATCH);
		
	}
	
	public static List<String> getCommonUrlList(){
		return commonUrlList;
	}
	
	public static List<String> getUserUrlList(){
		return userUrlList;
	}
	
	public static List<String> getAdminUrlList(){
		return adminUrlList;
	}
}
