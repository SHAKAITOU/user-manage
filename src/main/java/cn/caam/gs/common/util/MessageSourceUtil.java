package cn.caam.gs.common.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageSourceUtil {

    @Autowired
    HttpServletRequest request;
    
    
    private Map<Locale, ResourceBundle> bundleMap = new HashMap<Locale, ResourceBundle>();

    public String getContext(String code, String... args) {
        String context = getResourceBundle().getString(code);
        MessageFormat format = new MessageFormat(context);
        return format.format(args);
    }
    
    public String getContext(String code) {
        return getResourceBundle().getString(code);
    }
    
    public List<String> getKeys() {
        Enumeration<String> keys = getResourceBundle().getKeys();
        return Collections.list(keys);
    }
    
    private ResourceBundle getResourceBundle() {
        Locale locale = Locale.JAPAN;
        if (request.getHeader("accept-language") != null) {
            String[] lang = request.getHeader("accept-language").split(",");
            if (lang[0].startsWith("zh")) {
                locale = Locale.CHINESE;
            } else if (lang[0].startsWith("en")) {
                locale = Locale.US;
            }
        }
        if (!bundleMap.containsKey(locale)) {
            bundleMap.put(locale, ResourceBundle.getBundle("i18n/message", locale));
        } 
        return bundleMap.get(locale);
    }
}

