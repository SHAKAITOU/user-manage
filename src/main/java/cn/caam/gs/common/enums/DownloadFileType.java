package cn.caam.gs.common.enums;

public enum DownloadFileType {

	  /** 2寸证件照 */
	  PHOTO		("01", "photo", ".jpg"),
	  /** 学历证书附件 */
	  EDUCATIONAL 		("02", "educational", ".jpg"),
	  /** 学位证书附件 */
	  BACHELOR      ("03", "bachelor", ".jpg"),
      /** 职业证书附件 */
	  VOCATIONAL		("04", "vocational", ".jpg"),
	  /** 申请资料pdf */
	  APPLICATION_FORM		("05", "application_form", ".pdf"),
	  /** 申请资料模版word */
	  APPLICATION_FORM_TEMPLATE		("06", "application_form_template", ".docx"),
	  /** 未知 */
	  UNKNOWN		("", "unknown", "");

    private String fileId;
    
    private String fileType;
    
    private String key;

    private DownloadFileType(String fileId, String key, String fileType) {
    	this.fileId = fileId;
        this.key = key;
        this.fileType = fileType;
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
