package cn.caam.gs.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import cn.caam.gs.common.enums.UserExpiredType;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;

public class CommonUtil {
	public static Map<String, String[]> mapPolitical;
	public static Map<String, String[]> mapEduDegree;
	public static Map<String, String[]> mapBachelor;
	
	public static String POLITICAL = "团员	F0003	03	共青团员\r\n"
	    		+ "中共党员	F0003	01	中共党员\r\n"
	    		+ "九三学社	F0003	10	九三学社社员\r\n"
	    		+ "党员	F0003	01	中共党员\r\n"
	    		+ "群众	F0003	13	群众\r\n"
	    		+ "三九学社	F0003	10	九三学社社员\r\n"
	    		+ "民盟	F0003	05	民盟盟员\r\n"
	    		+ "民盟盟员	F0003	05	民盟盟员\r\n"
	    		+ "预备党员	F0003	02	中共预备党员\r\n"
	    		+ "民革	F0003	04	民革党员\r\n"
	    		+ "民主党			\r\n"
	    		+ "共青团员	F0003	03	共青团员\r\n"
	    		+ "农工党员	F0003	08	农工党党员\r\n"
	    		+ "民进	F0003	07	民进党员\r\n"
	    		+ "农工党	F0003	08	农工党党员\r\n"
	    		+ "共产党	F0003	01	中共党员\r\n"
	    		+ "九三	F0003	10	九三学社社员\r\n"
	    		+ "共产党员	F0003	01	中共党员\r\n"
	    		+ "干部			\r\n"
	    		+ "中国农工党	F0003	08	农工党党员\r\n"
	    		+ "农工民主党党员	F0003	08	农工党党员\r\n"
	    		+ "共青团	F0003	03	共青团员\r\n"
	    		+ "无	F0003	12	无党派人士\r\n"
	    		+ "九三学社社员	F0003	10	九三学社社员\r\n"
	    		+ "中国党员	F0003	01	中共党员\r\n"
	    		+ "公民			\r\n"
	    		+ "0			\r\n"
	    		+ "中国民主同盟	F0003	05	民盟盟员\r\n"
	    		+ "民主建国会	F0003	06	民建党员\r\n"
	    		+ "中国农工民主党	F0003	08	农工党党员\r\n"
	    		+ "积极分子			\r\n"
	    		+ "民进会员	F0003	07	民进党员\r\n"
	    		+ "中共预备党员	F0003	02	中共预备党员\r\n"
	    		+ "农工民主党	F0003	08	农工党党员\r\n"
	    		+ "农工党党员	F0003	08	农工党党员\r\n"
	    		+ "";
	    
	public static String EDU_DEGREE = "本科	F0004	04	本科\r\n"
	    		+ "大专	F0004	05	大专\r\n"
	    		+ "硕士	F0004	03	硕士\r\n"
	    		+ "中专	F0004	07	中专\r\n"
	    		+ "研究生	F0004	03	硕士\r\n"
	    		+ "专科	F0004	05	大专\r\n"
	    		+ "高中	F0004	06	高中\r\n"
	    		+ "硕士研究生	F0004	03	硕士\r\n"
	    		+ "硕士学历	F0004	03	硕士\r\n"
	    		+ "			\r\n"
	    		+ "大学	F0004	04	本科\r\n"
	    		+ "学士	F0004	04	本科\r\n"
	    		+ "职高中专	F0004	07	中专\r\n"
	    		+ "博士	F0004	02	博士\r\n"
	    		+ "大学本科	F0004	04	本科\r\n"
	    		+ "博士研究生	F0004	02	博士\r\n"
	    		+ "副高			\r\n"
	    		+ "初中	F0004	08	其他\r\n"
	    		+ "本秆	F0004	04	本科\r\n"
	    		+ "本来	F0004	04	本科\r\n"
	    		+ "";
	    
	public static String BACHELOR = "本科	F0006	03	学士学位\r\n"
	    		+ "大专	F0006	04	其他\r\n"
	    		+ "硕士	F0006	02	硕士学位\r\n"
	    		+ "中专	F0006	04	其他\r\n"
	    		+ "研究生	F0006	02	硕士学位\r\n"
	    		+ "专科	F0006	04	其他\r\n"
	    		+ "高中	F0006	04	其他\r\n"
	    		+ "硕士研究生	F0006	02	硕士学位\r\n"
	    		+ "硕士学历	F0006	02	硕士学位\r\n"
	    		+ "	F0006	04	其他\r\n"
	    		+ "大学	F0006	03	学士学位\r\n"
	    		+ "学士	F0006	03	学士学位\r\n"
	    		+ "职高中专	F0006	04	其他\r\n"
	    		+ "博士	F0006	01	博士学位\r\n"
	    		+ "大学本科	F0006	03	学士学位\r\n"
	    		+ "博士研究生	F0006	01	博士学位\r\n"
	    		+ "副高	F0006	04	其他\r\n"
	    		+ "初中	F0006	04	其他\r\n"
	    		+ "本秆	F0006	03	学士学位\r\n"
	    		+ "本来	F0006	03	学士学位\r\n"
	    		+ "";
	    
	    static {
	    	try {
	    	mapPolitical = convertCsvToMap(POLITICAL);
			mapEduDegree = convertCsvToMap(EDU_DEGREE);
			mapBachelor = convertCsvToMap(BACHELOR);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }

	
	public static String createAuthCode() {
		return String.valueOf((int)((Math.random()*9+1)*100000));
	}
	

	public static File convertMultipartFileToFile(MultipartFile file) throws IOException {
	    File convertedFile = Files.createTempFile("temp-", "-" + file.getOriginalFilename()).toFile();
	    file.transferTo(convertedFile);
	    return convertedFile;
	}

	public static UserExpiredType getValidStatus(String validEndDateStr) {
        UserExpiredType sts = UserExpiredType.VALID;
        if (!StringUtils.isEmpty(validEndDateStr)) {
            LocalDateTime validEndDate = LocalDateUtility.parseLocalDateTime(validEndDateStr, DateTimePattern.UUUUHMMHDDHHQMIQSS);
            LocalDateTime currentDt = LocalDateTime.now();
            if (validEndDate.isAfter(currentDt)) {
                if (LocalDateUtility.getDateDifference(validEndDate.toLocalDate(), currentDt.toLocalDate()) <= 5) {
                    sts = UserExpiredType.EXPIRED_SOON;
                }
            } else {
                if (LocalDateUtility.getDateDifference(currentDt.toLocalDate(), validEndDate.toLocalDate()) >= 60) {
                    sts = UserExpiredType.VARY_EXPIRED;
                } else {
                    sts = UserExpiredType.EXPIRED;
                }
            }
        }
        return sts;
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
    
    public static boolean isAlphaNum(String value) {
    	return value.matches("^[0-9a-zA-Z]+$");
    }
    
    public static boolean checkUserCode(String value) {
    	return value.matches("^[0-9a-zA-Z]+$") && (value.startsWith("M") || value.startsWith("G"));
    }
    
    public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}

		return dest;
	}
    
    public static Map<String, String[]> convertCsvToMap(String csv) {
    	Map<String, String[]> map = new ConcurrentHashMap<String, String[]>();
    	String[] lines = csv.split("\r\n");
    	for(String line:lines) {
    		if (!Strings.isBlank(replaceBlank(line))) {
    			String[] data = line.split("\t");
    			if (data.length > 2)
    			map.put(replaceBlank(data[0]), data);
    		}
    	}
    	
    	return map;
    }
    
    public static String getPoliticalValue(String name) {
    	if (name != null && mapPolitical.containsKey(name) && !Strings.isBlank(mapPolitical.get(name)[2])) {
    		return mapPolitical.get(name)[2];
    	}
    	
    	return null;
    }
    
    public static String getEduDegreeValue(String name) {
    	if (name != null && mapEduDegree.containsKey(name) && !Strings.isBlank(mapEduDegree.get(name)[2])) {
    		return mapEduDegree.get(name)[2];
    	}

    	return null;
    }
    
    public static String getBachelorValue(String name) {
    	if (name != null && mapBachelor.containsKey(name) && !Strings.isBlank(mapBachelor.get(name)[2])) {
    		return mapBachelor.get(name)[2];
    	}
    	
    	return null;
    }
    
   
}
