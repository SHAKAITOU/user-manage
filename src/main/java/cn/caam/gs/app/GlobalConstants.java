package cn.caam.gs.app;

import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.RowCntType;

public class GlobalConstants {
    
    // base url
    public static final String URL_BASE = "/common";  
    // init url
    public static final String URL_SHOW_PHOTO = "/showPhoto";
    
    public static final CssFontSizeType INPUT_FONT_SIZE           = CssFontSizeType.LABEL_16;
    public static final CssFontSizeType TABLE_HEAD_FONT_SIZE      = CssFontSizeType.LABEL_12B;
    public static final CssFontSizeType TABLE_BODY_FONT_SIZE      = CssFontSizeType.LABEL_12;
    public static final int DEFAULT_MEDIA_HEIGHT           = 730;
    public static final int DEFAULT_MEDIA_WIDTH            = 1100;
    public static final int TD_DEFAULT_HEIGHT              = 30;
    public static final int TH_DEFAULT_HEIGHT              = 30;
    public static final int DEFAULT_GROWING_CNT            = RowCntType.ROW_20.getId();
    public static final String INPUT_BG_COLOER             = "#FFFAF0";
    public static final String INPUT_DISABLED_BG_COLOER    = "#EEEEEE";
    public static final int TABLE_TD_HEIGHT                = 30;
    
    public static final String DFL_SELECT_ALL              = "00";
    
    public static final String DFL_SELECT_DEFAULT          = "";
    
    public static final String DFL_MEMBERSHIP_PATH_GANSU   = "027";
    
    public static final String DFL_NATIONALITY             = "01";
    
    public static final String DFL_POLITICAL               = "01";
    
    public static final String DFL_SEX                     = "01";
    
    public static final int USER_ID_MAX_L              = 20;
    public static final int USER_CODE_MAX_L            = 20;
    public static final int USER_NAME_MAX_L            = 70;
    public static final int USER_PW_MIN_L              = 8;
    public static final int USER_PW_MAX_L              = 40;
    public static final int PHONE_MAX_L                = 11;
    public static final int MAIL_MAX_L                 = 60;
    public static final int AUTH_CODE_MAX_L            = 6;
    public static final int EMPLOYER_MAX_L             = 60;
    public static final int CERTIFICATE_CODE_MAX_L     = 18;
    public static final int POST_CODE_MAX_L            = 6;
    public static final int ADDRESS_MAX_L              = 128;
    public static final int INTRODUCER1_MAX_L          = 32;
    public static final int RESEARCH_DIR_MAX_L         = 64;
    public static final int MAJOR_MAX_L                = 36;
    public static final int LEARN_EXPERIENCE_MAX_L     = 250;
    public static final int AMOUNT_MAX_L               = 10;
    public static final int BILL_CODE_MAX_L            = 12;
    public static final int INVOICE_TITLE_MAX_L        = 64;
    public static final int CREDIT_CODE_MAX_L          = 18;
    public static final int REVIEW_MEMO_MAX_L          = 150;
    public static final int MESSAGE_TITLE_MAX_L        = 100;
    public static final int MESSAGE_MSG_MAX_L          = 200;
    
    public static final int MAX_BILL_AMOUNT            = 10000;
    public static final int MIN_BILL_AMOUNT            = 1;
    
    public static final int ADD_USER_VLID_MONTHS       = 60;
    
//    public static final int USER_REGIST_EXPIRED_MINUTE  = 1;
//    public static final int USER_REGIST_SMS_SEND_INTERVAL = 1;
//    public static final int USER_LOGIN_AUTH_CODE_EXPIRED_MINUTE	= 1;
    
    public static final int AUTH_CODE_EXPIRED_MINUTE		= 3;
    public static final int AUTH_CODE_SEND_INTERVAL_MINUTE	= 1;
    
    public static final int   IMAGE_RESIZE_WIDTH_PC       = 800;
    public static final int   IMAGE_RESIZE_HEIGHT_PC      = 800;
    public static final int   IMAGE_RESIZE_WIDTH_MOBILE   = 500;
    public static final int   IMAGE_RESIZE_HEIGHT_MOBILE  = 500;
    public static final int   IMAGE_RESIZE_WIDTH_SAVE     = 2000;
    public static final int   IMAGE_RESIZE_HEIGHT_SAVE    = 2000;
    
    
    
    
    /** 頁LINK表示数(PC) */
    public static final int PC_LINKCNT = 10;
    /** 頁LINK表示数(SP) */
    public static final int SP_LINKCNT = 5;

    public static final String APPLICATION_FORM_TEMPLATE_FILE = "static/template/甘肃针灸学会普通会员入会申请表.docx";
    public static final String APPLICATION_FORM_NAME = "甘肃针灸学会普通会员入会申请表";
    public static final String APPLICATION_FORM_NAME1 = APPLICATION_FORM_NAME+"1";
    public static final String APPLICATION_FORM_NAME2 = APPLICATION_FORM_NAME+"2";
    
    public static final String CHINA_CERTI_IMAGE1 = "static/img/certi/chinaCertiPage1.png";
    public static final String CHINA_CERTI_IMAGE2 = "static/img/certi/chinaCertiPage2.png";
    public static final String CHINA_CERTI_IMAGE3 = "static/img/certi/chinaCertiPage3.png";
    public static final String CHINA_CERTI_IMAGE4 = "static/img/certi/chinaCertiPage4.png";
    
    public static final String GANSU_CERTI_IMAGE1 = "static/img/certi/chinaCertiPage1.png";
    public static final String GANSU_CERTI_IMAGE2 = "static/img/certi/chinaCertiPage2.png";
    public static final String GANSU_CERTI_IMAGE3 = "static/img/certi/chinaCertiPage3.png";
    public static final String GANSU_CERTI_IMAGE4 = "static/img/certi/chinaCertiPage4.png";
    
    public static final String UPLODA_FILE_SAVE_PATH = System.getProperty("user.dir") + "/upload";
    
    public static final String M_SYSTEM_CONFIG_PRIMARY_KEY = "360b8bda-8856-11ef-9994-900f0c240974";
}
