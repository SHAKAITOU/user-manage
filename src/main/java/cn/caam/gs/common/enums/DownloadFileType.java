package cn.caam.gs.common.enums;

import cn.caam.gs.app.GlobalConstants;

public enum DownloadFileType {

	  /** 2寸证件照 */
	  PHOTO		("01", "photo", ".jpg", ""),
	  /** 学历证书附件 */
	  EDUCATIONAL 		("02", "educational", ".jpg", ""),
	  /** 学位证书附件 */
	  BACHELOR      ("03", "bachelor", ".jpg", ""),
      /** 职业证书附件 */
	  VOCATIONAL		("04", "vocational", ".jpg", ""),
	  /** 申请资料pdf */
	  APPLICATION_FORM		("05", "application_form", ".jpg", ""),
	  /** 申请资料模版word */
	  APPLICATION_FORM_TEMPLATE		("06", "application_form_template", ".docx", ""),
	  /** 中国协会证明书1 */
	  USER_CHINA_CERTIFICATE_1		("07", "user_china_certificate_1", ".png", GlobalConstants.CHINA_CERTI_IMAGE1),
	  /** 中国协会证明书2 */
	  USER_CHINA_CERTIFICATE_2		("08", "user_china_certificate_2", ".png", GlobalConstants.CHINA_CERTI_IMAGE2),
	  /** 中国协会证明书3 */
	  USER_CHINA_CERTIFICATE_3		("09", "user_china_certificate_3", ".png", GlobalConstants.CHINA_CERTI_IMAGE3),
	  /** 中国协会证明书4 */
	  USER_CHINA_CERTIFICATE_4		("10", "user_china_certificate_4", ".png", GlobalConstants.CHINA_CERTI_IMAGE4),
	  /** 中国协会证明书zip */
	  USER_CHINA_CERTIFICATE		("11", "user_china_certificate", ".zip", ""),
	  /** 甘肃证明书1 */
	  USER_GANSU_CERTIFICATE_1		("12", "user_gansu_certificate_1", ".png", GlobalConstants.GANSU_CERTI_IMAGE1),
	  /** 甘肃证明书2 */
	  USER_GANSU_CERTIFICATE_2		("13", "user_gans_certificate_2", ".png", GlobalConstants.GANSU_CERTI_IMAGE2),
	  /** 甘肃证明书3 */
	  USER_GANSU_CERTIFICATE_3		("14", "user_gans_certificate_3", ".png", GlobalConstants.GANSU_CERTI_IMAGE3),
	  /** 甘肃证明书4 */
	  USER_GANSU_CERTIFICATE_4		("15", "user_gans_certificate_4", ".png", GlobalConstants.GANSU_CERTI_IMAGE4),
	  /** 甘肃证明书zip */
	  USER_GANSU_CERTIFICATE		("16", "user_gans_certificate", ".zip", ""),
	  /** 证明书zip */
	  USER_CERTIFICATE		("07", "user_certificate", ".zip", ""),
	  /** 未知 */
	  UNKNOWN		("", "unknown", "", "");

    private String fileId;
    
    private String fileType;
    
    private String key;
    
    private String filePath;

    private DownloadFileType(String fileId, String key, String fileType, String filePath) {
    	this.fileId = fileId;
        this.key = key;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    public String getFileId() {
        return fileId;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public DownloadFileType[] list() {
    	return DownloadFileType.values();
    }
    
    public static DownloadFileType fileIdOf(String fileId) {
    	for(DownloadFileType type : DownloadFileType.values()) {
    		if(type.getFileId().equals(fileId)) {
    			return type;
    		}
    	}
    	
    	return UNKNOWN;
    }
    
    public static DownloadFileType keyOf(String key) {
    	for(DownloadFileType type : DownloadFileType.values()) {
    		if(type.getKey().equals(key)) {
    			return type;
    		}
    	}
    	
    	return UNKNOWN;
    }
}
