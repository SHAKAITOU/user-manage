package cn.caam.gs.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static String createAuthCode() {
		return String.valueOf((int)((Math.random()*9+1)*100000));
	}
	
	/**
	 * 校验中国大陆手机号
	 *  中国移动： 139、138、137、136、134、135、147、150、151、152、157、158、159、172、178、182、183、184、187、188、195、197、198。
		中国联通： 130、131、132、140、145、146、155、156、166、185、186、175、176、196。
		中国电信： 133、149、153、177、173、180、181、189、190、191、193、199。
		中国广电： 192。
		虚拟运营商： 162、165、167、170、171。
	 * @param Phone_number
	 * @return
	 */
    public static boolean isMobilePhoneNumber(String Phone_number) {
        String regex = "^((13[0-9])|(14(0|[5-7]|9))|(15([0-3]|[5-9]))|(16(2|[5-7]))|(17[0-8])|(18[0-9])|(19([0-3]|[5-9])))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(Phone_number);
        return m.matches();
    }

}
