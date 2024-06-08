package cn.caam.gs.common.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FWCheckResult {
	String resultCode;
	String msgId;
	boolean checkRs;
	
	public FWCheckResult(String resultCode, String msgId, boolean checkRs) {
		this.resultCode = resultCode;
		this.msgId = msgId;
		this.checkRs = checkRs;
	}
}

