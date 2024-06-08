package cn.caam.gs.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class NumberUtility {

	private static DecimalFormat df = new DecimalFormat("#,###.##");
	
	public static String format(BigDecimal value) {
		return df.format(value.doubleValue());
	}
	
	public static String format(int value) {
		return df.format(value);
	}
	
	public static String format(long value) {
		return df.format(value);
	}
	
	public static String format(String value) {
		return StringUtils.isNotEmpty(value) ? df.format(Double.parseDouble(value)) : "";
	}
	
	public static BigDecimal parseBigDecimal(String value) {
		return StringUtils.isNotEmpty(value) ? new BigDecimal(value.replaceAll(",", "")) : BigDecimal.ZERO;
	}
}

