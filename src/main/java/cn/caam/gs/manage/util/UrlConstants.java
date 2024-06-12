package cn.caam.gs.manage.util;

import java.util.ArrayList;
import java.util.List;

import cn.caam.gs.common.enums.CssFontSizeType;

public class UrlConstants {
    
    
    /* ===================resources========================= */
    public static final String JS              = "/js";
    
    public static final String CSS             = "/css";
    
    public static final String IMG             = "/img";
    
    public static final String WEBFONTS        = "/webfonts";
    
    /* ===================COMMON URL========================= */
    public static final String DB                         = "/db";
    //INDEX
    public static final String INDEX                      = "/login";
    //USER LOGIN
    public static final String USER_LOGIN                 = "/userLogin";
    //ADMIN LOGIN
    public static final String LOGOUT                     = "/logout";
    
    public static final String MENU                       = "/menu";
    //会员查询
    public static final String USER_LIST                  = "/userList";
    public static final String PAGE                       = "/pageLink";
    public static final String INIT                       = "/init";
    public static final String SEARCH                     = "/search";
    //ACCESS DENIED
    public static final String ACCESS_DENIED              = "/access-denied";
    
    //LOTO    
        
    //BATCH
    public static final String BATCH             = "/batch";
    /* ===================ADMIN ROLE URL========================= */

    //ADMIN
    public static final String ADMIN             = "/admin";
    
    /* ===================USER ROLE URL========================= */
    //USER

    
    public static final String COMMON             = "/common";
    
    public static final String LIB             = "/lib";

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
