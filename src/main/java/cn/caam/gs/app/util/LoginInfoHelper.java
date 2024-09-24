package cn.caam.gs.app.util;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.LoginAccountType;
import cn.caam.gs.common.util.JsonUtility;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.custom.entity.LoginResult;
import cn.caam.gs.domain.db.custom.entity.UserInfo;

@Component
public class LoginInfoHelper {
    
    @Autowired
    private HttpServletRequest request0;
    private static HttpServletRequest request;
    @PostConstruct
    private void initStaticDao () {
        request = request0;
    }
	
	
	public static boolean isLogined(Map<String,Object> session) {
		if(session.containsKey(SessionConstants.LOGIN_INFO.getValue())) {
			return true;
		}
		
		return false;
	}
	
	public static UserInfo getLoginResult() {
	    if(Objects.nonNull(request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()))) {
            return null;
        }
        
        return (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
    }
	
	public static LoginResult getLoginResult(Map<String,Object> session) {
		if(session.containsKey(SessionConstants.LOGIN_INFO.getValue())) {
			return JsonUtility.toObject((String)session.get(SessionConstants.LOGIN_INFO.getValue()), LoginResult.class);
		}
		
		return null;
	}
	

	public static boolean isLogined(HttpServletRequest request) {
		if(Objects.nonNull(request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()))) {
			return true;
		}
		
		return false;
	}
	
	public static LoginResult getLoginResult(HttpServletRequest request) {
		if(Objects.nonNull(request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()))) {
			return JsonUtility.toObject((String)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()), LoginResult.class);
		}
		
		return null;
	}
	
	public static int getMediaHeight(Map<String,Object> session) {
		if(Objects.nonNull(session.get(SessionConstants.MEDIA_HEIGHT.getValue()))) {
			return (int)session.get(SessionConstants.MEDIA_HEIGHT.getValue());
		}
		
		return GlobalConstants.DEFAULT_MEDIA_HEIGHT;
	}

	public static int getMediaHeight(HttpServletRequest request) {
		if(Objects.nonNull(request.getSession().getAttribute(SessionConstants.MEDIA_HEIGHT.getValue()))) {
			return (int)request.getSession().getAttribute(SessionConstants.MEDIA_HEIGHT.getValue());
		}
		
		return GlobalConstants.DEFAULT_MEDIA_HEIGHT;
	}

	public static int getMediaWidth(HttpServletRequest request) {
		if(Objects.nonNull(request.getSession().getAttribute(SessionConstants.MEDIA_WIDTH.getValue()))) {
			return (int)request.getSession().getAttribute(SessionConstants.MEDIA_WIDTH.getValue());
		}
		
		return GlobalConstants.DEFAULT_MEDIA_WIDTH;
	}

	public static String getRandomAuthCode() {
		Random rand = new Random();
		String authCode = String.valueOf(rand.nextInt(10))
				+ String.valueOf(rand.nextInt(10))
				+ String.valueOf(rand.nextInt(10))
				+ String.valueOf(rand.nextInt(10))
				+ String.valueOf(rand.nextInt(10))
				+ String.valueOf(rand.nextInt(10));
		return authCode;
	}
	
	public static String formatUserCode(String userId) {
		Random rand = new Random();
		String userCode = String.valueOf(rand.nextInt(10))
						+ String.valueOf(rand.nextInt(10))
						+ StringUtility.padLeft(userId, 5, "0");
		return userCode;
	}

	public static LoginAccountType getLoginAccountType(HttpServletRequest request) {
		if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof UserInfo){
			return LoginAccountType.USER;
		}else if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof MAdmin){
			return LoginAccountType.ADMIN;
		}
		
		return LoginAccountType.UNKNOWN;
	}
	
	public static boolean isUserLogin(HttpServletRequest request) {
		if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof UserInfo){
			return true;
		}
		
		return false;
	}
	
	public static boolean isAdminLogin(HttpServletRequest request) {
		if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof MAdmin){
			return true;
		}
		
		return false;
	}
	
	public static String getLoginId(HttpServletRequest request) {
		if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof UserInfo){
			return ((UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue())).getUserCode();
		}else if (request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()) instanceof MAdmin){
			return ((MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue())).getId();
		}
		
		return "";
	}
}
