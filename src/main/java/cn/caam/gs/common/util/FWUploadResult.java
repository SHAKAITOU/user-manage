package cn.caam.gs.common.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FWUploadResult {

	String resultCode;
	String msgId;
	boolean uploadRs;
	
	public FWUploadResult(String resultCode, String msgId, boolean uploadRs) {
		this.resultCode = resultCode;
		this.msgId = msgId;
		this.uploadRs = uploadRs;
	}
}

