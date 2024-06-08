package cn.caam.gs.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Base64Utils;

public class StringCommonUtil {
	
	public static final String DECIMALFORMAT_NUMBER = "#,###.##";
	
	public static final String DECIMALFORMAT_CURRENCY = "00,000.00";
	
	public static final String DECIMALFORMAT_PERCENT = "##0.00%";
	
	private static final int OBJECT_TO_STRING_MAX_LOOP_CNT = 5;
	
	public int obj2StringLoopCnt = 0;
	
	
	public boolean isEmpty(String value) {
		if(value == null || value.equals("")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 型チェック<P>
	 * 指定された文字列とデータ型情報を基に、文字列の型が情報通りであるかチェックする。
	 * @param checkStr チェック元文字列
	 * @param type チェック先型クラス
	 * @return 真偽結果値 true | false
	 */
	public boolean checkType(String checkStr, @SuppressWarnings("rawtypes") Class type) {
		
		//int type
		if(type == Integer.class) {
			try  {  
				Integer.parseInt(checkStr);  
				return true;
			} catch(NumberFormatException nfe) {  
				return false;  
			}  
		}else if(type == Long.class) {
			try  {  
				Long.parseLong(checkStr);  
				return true;
			} catch(NumberFormatException nfe) {  
				return false;  
			} 
		}else if(type == Double.class) {
			try  {  
				Double.parseDouble(checkStr);  
				return true;
			} catch(NumberFormatException nfe) {  
				return false;  
			}
		}else if(type == Float.class) {
			try  {  
				Float.parseFloat(checkStr);  
				return true;
			} catch(NumberFormatException nfe) {  
				return false;  
			}
		}
		
		return false;
	}
	
	/**
	 * バイト桁数チェック<P>
	 * 指定された文字列と桁情報を基に、文字列のバイト桁が情報通りであるかチェックする。
	 * 
	 * @param checkStr チェック元文字列
	 * @param length チェックバイト桁数
	 * @return 真偽結果値 true | false
	 */
	public boolean checkLength(String checkStr, int length) {
		return checkLength(checkStr, length, null);
	}
	
	/**
	 * 文字数チェック<P>
	 * 指定された文字列と文字数情報を基に、文字列の文字数が情報通りであるかチェックする。
	 * 
	 * @param checkStr チェック元文字列
	 * @param length チェック文字数
	 * @param encodingType
	 * @return 真偽結果値 true | false
	 */
	public boolean checkLength(String checkStr, int length, String encodingType) {
		if(encodingType != null) {
			try {
				int lt = checkStr.getBytes("UTF-8").length;
				if(lt == length) {
					return true;
				}
			} catch (UnsupportedEncodingException e) {
				return false;
			}
		}else {
			int lt = checkStr.length();
			if(lt == length) {
				return true;
			}
		}
		return false;
	}

	public static String trimString(String checkStr, int maxLength) {
		return checkStr.substring(0, Math.min(checkStr.length(), maxLength));
	}
	
	
	public String formatValue(long value, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(value);
	}
	
	public String formatValue(double value, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(value);
	}
	
	public String toString(Object obj) throws IOException {
		obj2StringLoopCnt = 0;
		//return toStringObject(obj);
		return ReflectionToStringBuilder.toString(obj, ToStringStyle.JSON_STYLE);
	}
	
	/**
	 * データオブジェクト内容文字列化<P>
	 * データオブジェクト内容を連結して、文字列に変換して返却する
	 * @param obj データオブジェクト
	 * @return 連結内容文字列
	 */
	public String toStringObject(Object obj) {
		if(obj2StringLoopCnt == OBJECT_TO_STRING_MAX_LOOP_CNT) {
			return "";
		}else {
			obj2StringLoopCnt++;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(obj.getClass().getName()+"{");
		int idx = 0;
		for (Field field : obj.getClass().getDeclaredFields()) {
	        try {
	            field.setAccessible(true);
	            if(idx > 0) {
	            	sb.append(",");
	            }
	            sb.append("\""+field.getName()+"\":");
	            Object item = field.get(obj);
	            if(item == null) {
	            	 sb.append("null");
	            }else if(item instanceof String ||
	            		item instanceof Integer ||
	            		item instanceof Long ||
	            		item instanceof Float ||
	            		item instanceof Double){
	            	sb.append("\""+item.toString()+"\"");
	            }else{
	            	sb.append(toStringObject(item));
	            }
	        } catch (IllegalAccessException e) {
	            sb.append(field.getName() + " = " + "access denied\n");
	        }
	        idx++;
	    }
		sb.append("}");
		
		return sb.toString();
	}

    public String serializeObjectToString(Object object) throws IOException {
    	try (
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(arrayOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream);) {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return new String(Base64Utils.encode(arrayOutputStream.toByteArray()));
        }
    }
    
    public String formatString(String str, Object... arg) {
    	return MessageFormat.format(str, arg);
    }

	/*
	public void main(String[] arg) {
		StringCommonUtil util = new StringCommonUtil();
		
		System.out.println("int checkType(123):"+util.checkType("123", Integer.class));
		System.out.println("int checkType(123.12):"+util.checkType("123.12", Integer.class));
		System.out.println("int checkType(abc):"+util.checkType("abc", Integer.class));
		System.out.println("int checkType(１２３４):"+util.checkType("１２３４", Integer.class));
		System.out.println("int checkType(あいう):"+util.checkType("あいう", Integer.class));
		
		System.out.println("int checkLength(あいう, 3, false):"+util.checkLength("あいう", 3));
		System.out.println("int checkLength(あいう, 9, Shift_JIS):"+util.checkLength("あいう", 9, "Shift_JIS"));
		System.out.println("int checkLength(あいう, 9, UTF-8):"+util.checkLength("あいう", 9, "UTF-8"));
		System.out.println("int checkLength(あいう123, 12, UTF-8):"+util.checkLength("あいう123", 12, "UTF-8"));
		System.out.println("int checkLength(あいう123ｱｲｳ, 12, UTF-8):"+util.checkLength("あいう123ｱｲｳ", 15, "UTF-8"));
		System.out.println("int checkLength(あいう123ｱｲｳ, 9):"+util.checkLength("あいう123ｱｲｳ", 9));
		
		System.out.println("int formatValue(123456.78, #,###.##):"+util.formatValue(123456.78, DECIMALFORMAT_NUMBER));
		System.out.println("int formatValue(123456, #,###.##):"+util.formatValue(123456, DECIMALFORMAT_NUMBER));
		System.out.println("int formatValue(123456.78, #,###.##):"+util.formatValue(123456.78, DECIMALFORMAT_CURRENCY));
		System.out.println("int formatValue(123456, #,###.##):"+util.formatValue(123456, DECIMALFORMAT_CURRENCY));
		System.out.println("int formatValue(123456.78, #,###.##):"+util.formatValue(123456.78, DECIMALFORMAT_PERCENT));
		System.out.println("int formatValue(123456, #,###.##):"+util.formatValue(123456, DECIMALFORMAT_PERCENT));
		
	
	}
	*/

}
