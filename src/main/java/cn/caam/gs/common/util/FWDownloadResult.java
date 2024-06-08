package cn.caam.gs.common.util;

import java.io.OutputStream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FWDownloadResult {

	String resultCode;
	String msgId;
	OutputStream outputStream;
	boolean downloadRs;
	
	public FWDownloadResult(String resultCode, String msgId, boolean downloadRs) {
		this.resultCode = resultCode;
		this.msgId = msgId;
		this.downloadRs = downloadRs;
	}
}

