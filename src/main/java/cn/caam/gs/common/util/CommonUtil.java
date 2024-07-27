package cn.caam.gs.common.util;

public class CommonUtil {

	public static String createAuthCode() {
		return String.valueOf((int)((Math.random()*9+1)*100000));
	}
}
