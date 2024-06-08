package cn.caam.gs.common.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageSourceUtil {
	
	@Autowired 
	private MessageSource msg;
	
    @Autowired
    HttpServletRequest request;
    
//	private static MessageUtil instance;
//	@PostConstruct
//    public void registerInstance() {
//        instance = this;
//    }
	public String getContext(String key) {		
		return msg.getMessage(key, null, getLocale());
	}
	
	public String getContext(String key, String... args) {		
		return msg.getMessage(key, args, getLocale());
	}
	
	public String getContext(Locale location, String key, String... args) {	
	    if (location.equals(Locale.CHINESE)) {
	        return msg.getMessage(key, args, location);
	    } else {
	        return msg.getMessage(key, args, getLocale());
	    }
	}
	
	private Locale getLocale() {
        Locale locale = Locale.CHINESE;
        if (request.getHeader("accept-language") != null) {
            String[] lang = request.getHeader("accept-language").split(",");
            if (lang[0].startsWith("zh")) {
                locale = Locale.CHINESE;
            } else if (lang[0].startsWith("en")) {
                locale = Locale.US;
            }
        }
        return locale;
    }
}

