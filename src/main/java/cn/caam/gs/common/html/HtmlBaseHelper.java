package cn.caam.gs.common.html;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.common.util.UtilConstants;

@Component
public class HtmlBaseHelper {
    @Autowired
    private MessageSourceUtil messageSourceUtil0;
    private static MessageSourceUtil messageSourceUtil;
    
    @Autowired
    private Environment environment0;
    private static Environment environment;
    
    public static final String BTN_CSS_60H = "btn_60h";
    public static final String BTN_CSS_100 = "btn_100";
    

    @PostConstruct
    private void initStaticDao() {
        messageSourceUtil = this.messageSourceUtil0;
        environment = this.environment0;
    }
    
    public static String getEnvironmentProperty(String key) {
        return environment.getProperty(key);
    }
    
    public static String getContext(String key) {
        return messageSourceUtil.getContext(LocaleContextHolder.getLocale(), key);
    }

    public static String nonNull(boolean value) {
        return Objects.nonNull(value) ? Boolean.toString(value) : "";
    }
    
    public static String nonNull(boolean value, String replaceValue) {
        return Objects.nonNull(value) ? Boolean.toString(value) : replaceValue;
    }
    
    public static String nonNull(String value) {
        return Objects.nonNull(value) ? value : "";
    }
    
    public static String nonNull(String value, String replaceValue) {
        return Objects.nonNull(value) ? value : replaceValue;
    }
    
    public static String nonNull(Integer value) {
        return Objects.nonNull(value) ? value.toString() : "";
    }
    
    public static String nonNull(Integer value, String replaceValue) {
        return Objects.nonNull(value) ? value.toString() : replaceValue;
    }
    
    public static String nonNull(Integer value, Integer replaceValue) {
        return Objects.nonNull(value) ? value.toString() : replaceValue.toString();
    }
    
    public static String nonNull(String value, Integer replaceValue) {
        return Objects.nonNull(value) ? value : replaceValue.toString();
    }
    
    public static String trimStringByByte(String checkStr, int maxLength, boolean addDot) {
        if (StringUtils.isEmpty(checkStr)) {
            return "";
        }
        int byteLength = StringUtility.byteLength(checkStr);
        String str = StringUtility.leftB(checkStr, maxLength);
        if (addDot) {
            return byteLength > maxLength ? str+"..." : str;
        }
        
        return str;
    }
    
    public static String convertNameDotForId(String name) {
        return name.replace(".", "_").replace("[", "").replace("]", "");
    }
    

    public static String trimFitForTd(int grids, String context, boolean addDot) {
        return trimStringByByte(context, getMaxLengthByGrids(grids), addDot);
    }
    
    public static String trimFitForSubTd(int grids, String context, boolean addDot) {
        return trimStringByByte(context, getMaxLengthBySubGrids(grids), addDot);
    }
    
    public static int getMaxLengthByGrids(int grids) {
        if(grids == 1) {
            return 8;
        } else {
            return grids*10;
        }
    }
    
    public static int getMaxLengthBySubGrids(int grids) {
        if(grids == 1) {
            return 4;
        } else {
            return grids*6;
        }
    }
    
    public static String filterSpecialCharacters(String context) {
        return context.replace("\"", UtilConstants.DUBLE_QUOTE_MARK)
                .replace("<", UtilConstants.LEFT_ANGLE)
                .replace(">", UtilConstants.RIGHT_ANGLE);
    }
    
    public static String concactWithSpace(String... contexts) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for(String context : contexts) {
            if(index > 0) {
                sb.append(UtilConstants.HTML_SPACE);
                sb.append(UtilConstants.HTML_SPACE);
            }
            sb.append(context);
            index++;
        }
        return sb.toString();
    }
}
