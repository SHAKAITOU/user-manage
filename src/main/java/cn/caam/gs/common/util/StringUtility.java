package cn.caam.gs.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

import org.springframework.util.StringUtils;

import cn.caam.gs.common.exception.ShaException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Common component <br>
 * string utility class.
 *
 */
@Slf4j
public class StringUtility {
    
    private StringUtility() {}

    /** default charset UTF-8. */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /** default currency scale (2). */
    private static final int DEFAULT_CURRENCY_SCALE = 2;

    /**
     * parse the specified text to the specified number class type.
     * @param text the specified text
     * @param numberClass the specified number class type
     * @return {@link Number} when the specified text can be parsed, null otherwise
     */
    public static Number parseNumber(String text, NumberClass numberClass) {

        try  {  
            if (StringUtils.isEmpty(text)) {
                return null;
            } else if (NumberClass.INTEGER.equals(numberClass)) {
                return Integer.valueOf(text);
            } else if (NumberClass.LONG.equals(numberClass)) {
                return Long.valueOf(text);
            } else if (NumberClass.DOUBLE.equals(numberClass)) {
                return Double.valueOf(text);
            } else if (NumberClass.FLOAT.equals(numberClass)) {
                return Float.valueOf(text);
            } else {            
                return null;
            }
        } catch (NumberFormatException e) {
            return null;  
        } 
    }

    /**
     * Get the specified text's bytes length by the default charset UTF-8.
     * @param text the specified text
     * @return length of the specified text
     * @throws UnsupportedEncodingException 
     *           throw an exception when the specified encoding type is unsupported
     */
    public static int getByteLength(String text) throws UnsupportedEncodingException {
        return getByteLength(text, DEFAULT_CHARSET);
    }

    /**
     * Get the specified text's length by the specified charset.
     * @param text the specified text
     * @param charsetName  The name of a supported {@linkplain java.nio.charset.Charset
     *         charset}
     * @return true or false
     * @throws UnsupportedEncodingException 
     *            throw an exception when the specified encoding type is unsupported
     */
    public static int getByteLength(String text, String charsetName) 
            throws UnsupportedEncodingException {
        int length = 0;
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(charsetName)) {
            return length;
        } else {
            return text.getBytes(charsetName).length;
        } 
    }

    /**
     * convert the specified long value to a text number with the specified locale format<BR>
     * example:<BR>
     *  StringUtility.formatNumber(1234567, Locale.JAPAN) --> 1,234,567.
     * @param value the specified long value
     * @param locale the specified Locale. Uses Locale.getDefault() if null.
     * @return formatted text.
     */
    public static String formatNumber(long value, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(value);
    }

    /**
     * convert the specified double value to a text number with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatNumber(1234567.891234, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 1,234,567.891<BR>
     *  StringUtility.formatNumber(0.2355, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 0.236
     * @param value the specified double value
     * @param scale the specified scale
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale. Uses Locale.getDefault() if null.
     * @return formatted text.
     */
    public static String formatNumber(double value, int scale, RoundingMode roundMode, Locale locale) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        BigDecimal scaledBigDecimal = bigDecimal.setScale(scale, roundMode);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(scaledBigDecimal.doubleValue());
    }
    

    /**
     * convert the specified double value to a text number with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatNumber(1234567.891234, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 1,234,567.891<BR>
     *  StringUtility.formatNumber(0.2355, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 0.236
     * @param value the specified double value
     * @param scale the specified scale
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale. Uses Locale.getDefault() if null.
     * @return formatted text.
     */
    public static String formatNumber(BigDecimal value) {
    	if (value == null) {
    		return "";
    	}
        BigDecimal scaledBigDecimal = value.setScale(0, RoundingMode.HALF_UP);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(
                Optional.ofNullable(Locale.getDefault())
                        .orElse(Locale.getDefault()));
        return numberFormat.format(scaledBigDecimal.doubleValue());
    }
    
    /**
     * convert the specified double value to a text number with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatNumber(1234567.891234, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 1,234,567.891<BR>
     *  StringUtility.formatNumber(0.2355, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 0.236
     * @param value the specified double value
     * @param scale the specified scale
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale. Uses Locale.getDefault() if null.
     * @return formatted text.
     */
    public static String formatNumber(int value) {
        BigDecimal scaledBigDecimal = new BigDecimal(value);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(
                Optional.ofNullable(Locale.getDefault())
                        .orElse(Locale.getDefault()));
        return numberFormat.format(scaledBigDecimal.doubleValue());
    }

    /**
     * convert the specified double value to a text number with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatNumber(1234567.891234, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 1,234,567.891<BR>
     *  StringUtility.formatNumber(0.2355, 3, RoundMode.ROUND, Locale.JAPAN)
     *   --> 0.236
     * @param value the specified double value
     * @param scale the specified scale
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale. Uses Locale.getDefault() if null.
     * @return formatted text.
     */
    public static String formatNumber(BigDecimal value, int scale, RoundingMode roundMode, Locale locale) {
        BigDecimal scaledBigDecimal = value.setScale(scale, roundMode);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(scaledBigDecimal.doubleValue());
    }
    
    /**
     * convert the specified long value to a string with the specified locale format<BR>
     * example:<BR>
     *  StringUtility.formatCurrency(1234567, Locale.JAPAN) -->￥1,234,567<BR>
     *  StringUtility.formatCurrency(12345, Locale.FRANCE) -->12 345,00 €.
     * @param value the specified long value
     * @param locale the specified Locale. Uses Locale.getDefault() if null.
     * @return formatted text.
     */
    public static String formatCurrency(long value, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(value);
    }

    /**
     * convert the specified double value to a text currency with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatCurrency(1234567.891234, RoundMode.ROUND, Locale.JAPAN)
     *   -->￥1,234,568<BR>
     *  StringUtility.formatCurrency(0.2355, RoundMode.ROUND, Locale.FRANCE)
     *   -->0.24 €
     * @param value the specified double value
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale
     * @return formatted text
     */
    public static String formatCurrency(double value, RoundingMode roundMode, Locale locale) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
        BigDecimal resultBigDecimal = bigDecimal.setScale(
                DEFAULT_CURRENCY_SCALE, roundMode);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(resultBigDecimal.doubleValue());
    }
    
    /**
     * convert the specified double value to a text currency with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatCurrency(1234567.891234, RoundMode.ROUND, Locale.JAPAN)
     *   -->￥1,234,568<BR>
     *  StringUtility.formatCurrency(0.2355, RoundMode.ROUND, Locale.FRANCE)
     *   -->0.24 €
     * @param value the specified double value
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale
     * @return formatted text
     */
    public static String formatCurrency(BigDecimal value, RoundingMode roundMode, Locale locale) {
        BigDecimal resultBigDecimal = value.setScale(
                DEFAULT_CURRENCY_SCALE, roundMode);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(resultBigDecimal.doubleValue());
    }
    /**
     * convert the specified double value to a text percent with the specified locale format
     * and the specified scale and round type <BR>
     * example:<BR>
     *  StringUtility.formatPercent(0.891234, 3, RoundMode.ROUND, Locale.JAPAN)
     *   -->89.1%<BR>
     *  StringUtility.formatPercent(0.2355, 1, RoundMode.ROUND, Locale.FRANCE)
     *   -->20 %
     * @param value the specified double value
     * @param scale the specified scale
     * @param roundMode the specified RoundMode
     * @param locale the specified Locale
     * @return formatted text.
     */
    public static String formatPercent(double value, 
            int scale, RoundingMode roundMode, Locale locale) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
        BigDecimal resultBigDecimal = bigDecimal.setScale(scale, roundMode);
        NumberFormat numberFormat = NumberFormat.getPercentInstance(
                Optional.ofNullable(locale)
                        .orElse(Locale.getDefault()));
        return numberFormat.format(resultBigDecimal.doubleValue());
    }
    
    public static int byteLength(String str) {
    	int cnt = 0;
    	if (str == null) {
    	    return cnt;
    	}
    	try{
            for (int i = 0; i < str.length(); i++) {
              String tmpStr = str.substring(i, i + 1);
              byte[] b = tmpStr.getBytes(DEFAULT_CHARSET);
              int bLength = b.length;
              if(bLength == 3) {//when UTF-8 3 --> 2
            	  bLength = 2;
              }
              cnt += bLength;
            }
        } catch (Exception e){
            throw new ShaException(e);
        }
    	return cnt;
    }
    
    public static String leftB(String str, Integer len){
        StringBuffer sb = new StringBuffer();
        int cnt = 0;

        try{
            for (int i = 0; i < str.length(); i++) {
              String tmpStr = str.substring(i, i + 1);
              byte[] b = tmpStr.getBytes(DEFAULT_CHARSET);
              int bLength = b.length;
              if(bLength == 3) {//when UTF-8 3 --> 2
            	  bLength = 2;
              }
              if (cnt + bLength > len) {
                return sb.toString();
              } else {
                sb.append(tmpStr);
                cnt += bLength;
              }
            }
        } catch (Exception e){
            throw new ShaException(e);
        }
        return sb.toString();
    }

    /*
     	* 文字列の桁あわせを行うメソッド
     * @param string 文字列
     * @param int 長さ
     * @return 指定した長さの文字列
     */
    public static String padRight(String str, int len, String fillChar){
        try {
	    	int bytelen = byteLength(str);
	        for(int i=0; i<(len - bytelen);i++){
	            str = str + fillChar;
	        }
	    } catch (Exception e){
	        throw new ShaException(e);
	    }
        return str;
    }
    
    public static String padLeft(String str, int len, String fillChar){
        try {
	    	int bytelen = byteLength(str);
	        for(int i=0; i<(len - bytelen);i++){
	            str = fillChar + str;
	        }
	    } catch (Exception e){
	        throw new ShaException(e);
	    }
        return str;
    }
    
    public static String padBothSide(String str, int len, String fillChar){
        try {
	    	int bytelen = byteLength(str);
	    	if(len > bytelen) {
	    		int halfSize = (len - bytelen)/2;
	        	int leftNum = (len - bytelen)%2;
	        	for(int i=0; i<halfSize;i++){
	        		str = fillChar + str;
	        	}
	        	
	        	for(int i=0; i<(halfSize+leftNum);i++){
	        		str = str + fillChar;
	        	}
	    	}
	    } catch (Exception e){
	        throw new ShaException(e);
	    }
        return str;
    }

    /**
     * The java number class type enum.
     *
     */
    public enum NumberClass {
        /** 
         * {@link Integer} .
         */
        INTEGER(Integer.class), 
        /** 
         * {@link Long} .
         */
        LONG(Long.class),
        /** 
         * {@link Float} .
         */
        FLOAT(Float.class),
        /** 
         * {@link Double} .
         */
        DOUBLE(Double.class); 

        /** class type. */
        @Getter
        private Class<? extends Number> classType;

        private NumberClass(Class<? extends Number> classType) {
            this.classType = classType;
        }
    }
}

