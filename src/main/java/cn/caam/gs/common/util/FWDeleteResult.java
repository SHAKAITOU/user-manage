package cn.caam.gs.common.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FWDeleteResult {

	String resultCode;
	String msgId;
	boolean deleteRs;
	
	public FWDeleteResult(String resultCode, String msgId, boolean deleteRs) {
		this.resultCode = resultCode;
		this.msgId = msgId;
		this.deleteRs = deleteRs;
	}
}

