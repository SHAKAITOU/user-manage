package cn.caam.gs.common.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import cn.caam.gs.common.enums.WeekDayType;

/**
 * Common component <br>
 * date utility class.
 *
 */
@Slf4j
public class DateUtility {

	public static final long DATE_STEP = 1000*60*60*24;

	public static final long HOUR_STEP = 1000*60*60;

	public static final long MIN_STEP = 1000*60;

	public static final long SEC_STEP = 1000;

	private DateUtility() {}

	/**
	 * Convert the specified text to a LocalDate with the specified format pattern.
	 * 
	 * @param dateString specified date string.
	 * @param pattern  specified format pattern.<BR>
	 *      see {@link DateFormat} to use the predefined patterns <BR>
	 *      see {@link DateTimeFormatter} to use more supported patterns <BR>
	 *      use "uuuu-MM-dd" as default format when empty
	 * @return {@link LocalDate} when success, null otherwise
	 */
	public static LocalDate parseDate(String dateString, String pattern) {

		if (!StringUtils.hasText(dateString)) {
			return null;
		} 
		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern = DateFormat.UUUUHMMHDD.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return LocalDate.parse(dateString, dateTimeFormatter);

		} catch (DateTimeParseException | IllegalArgumentException e) {
			log.error("error in parsing date string.", e);
		}

		return null;
	}
	/**
	 * Convert the specified text to a LocalDateTime with the specified format pattern.
	 * 
	 * @param dateTimeString specified dateTime string.
	 * @param pattern specified format pattern.<BR>
	 *      see {@link DateTimeFormat} to use the predefined pattern. <BR>
	 *      see {@link DateTimeFormatter} to use more patterns. <BR>
	 *      use "uuuu-MM-dd HH:mm:ss.SSS" as default format when empty
	 * @return {@link LocalDateTime} when success, null otherwise
	 */
	public static LocalTime parseTime(String timeString, String pattern) {

		if (!StringUtils.hasText(timeString)) {
			return null;
		} 

		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  TimeFormat.HHQMIQSS.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return LocalTime.parse(timeString, dateTimeFormatter);
		} catch (DateTimeParseException | IllegalArgumentException e) {
			log.error("error in parsing date string.", e);
		}

		return null;
	}

	/**
	 * Convert the specified text to a LocalDateTime with the specified format pattern.
	 * 
	 * @param dateTimeString specified dateTime string.
	 * @param pattern specified format pattern.<BR>
	 *      see {@link DateTimeFormat} to use the predefined pattern. <BR>
	 *      see {@link DateTimeFormatter} to use more patterns. <BR>
	 *      use "uuuu-MM-dd HH:mm:ss.SSS" as default format when empty
	 * @return {@link LocalDateTime} when success, null otherwise
	 */
	public static LocalDateTime parseDateTime(String dateTimeString, String pattern) {

		if (!StringUtils.hasText(dateTimeString)) {
			return null;
		} 

		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
		} catch (DateTimeParseException | IllegalArgumentException e) {
			log.error("error in parsing date string.", e);
		}

		return null;
	}
	
	/**
	 * Convert the specified localDate to a date string with the specified format pattern.
	 * @param localDate the specified localDate.
	 * @param pattern the specified format pattern.<BR>
	 *      see {@link DateFormat} to use the predefined pattern. <BR>
	 *      see {@link DateTimeFormatter} to use more pattern. <BR>
	 *      use "uuuu-MM-dd" as default format when empty.
	 * @return a formatted date string when success, null otherwise.
	 */
	public static String formatYearMonth(YearMonth yearMonth, String pattern) {
		if (yearMonth == null) {
			return null;
		}
		
		LocalDate localDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);


		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  DateFormat.UUUUHMMHDD.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return localDate.format(dateTimeFormatter);
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error("error in formatting date with pattern.", e);
		}

		return null;
	}
	
	public static String formatYearMonth(String yearMonthStr, MonthFormat inPattern, MonthFormat outPattern) {
		if (StringUtils.isEmpty(yearMonthStr)) {
			return null;
		}
		
		YearMonth yearMonth = parseYearMonth(yearMonthStr, inPattern);

		return formatYearMonth(yearMonth, outPattern.getFormat());
	}

	/**
	 * Convert the specified localDate to a date string with the specified format pattern.
	 * @param localDate the specified localDate.
	 * @param pattern the specified format pattern.<BR>
	 *      see {@link DateFormat} to use the predefined pattern. <BR>
	 *      see {@link DateTimeFormatter} to use more pattern. <BR>
	 *      use "uuuu-MM-dd" as default format when empty.
	 * @return a formatted date string when success, null otherwise.
	 */
	public static String formatDate(LocalDate localDate, String pattern) {

		if (localDate == null) {
			return null;
		}

		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  DateFormat.UUUUHMMHDD.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return localDate.format(dateTimeFormatter);
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error("error in formatting date with pattern.", e);
		}

		return null;
	}
	/**
	 * Convert the specified localDateTime to a date time text with the specified format pattern.
	 * @param localDateTime the specified localDateTime.
	 * @param pattern the specified format pattern. <BR>
	 *      see {@link DateTimeFormat} to use the predefined pattern. <BR>
	 *      see {@link DateTimeFormatter} to use more pattern. <BR>
	 *      use "uuuu-MM-dd HH:mm:ss.SSS" as default format when empty.
	 * @return a date time text when success, null otherwise.
	 */
	public static String formatTime(LocalTime localTime, String pattern) {

		if (localTime == null) {
			return null;
		}

		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  TimeFormat.HHQMIQSS.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return localTime.format(dateTimeFormatter);
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error("error in formatting date with pattern.", e);
		}

		return null;
	}

	/**
	 * Convert the specified localDateTime to a date time text with the specified format pattern.
	 * @param localDateTime the specified localDateTime.
	 * @param pattern the specified format pattern. <BR>
	 *      see {@link DateTimeFormat} to use the predefined pattern. <BR>
	 *      see {@link DateTimeFormatter} to use more pattern. <BR>
	 *      use "uuuu-MM-dd HH:mm:ss.SSS" as default format when empty.
	 * @return a date time text when success, null otherwise.
	 */
	public static String formatDateTime(LocalDateTime localDateTime, String pattern) {

		if (localDateTime == null) {
			return null;
		}

		String formatPattern = pattern;
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return localDateTime.format(dateTimeFormatter);
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error("error in formatting date with pattern.", e);
		}

		return null;
	}

	/**
	 * convert a localDateTime with the specified time zone from. 
	 * to a localDateTime with the specified time zone to.
	 * @param localDateTime a specified localDateTime from.
	 * @param fromTimeZone a specified time zone from.
	 * @param toTimeZone a specified time zone to.
	 * @return {@link LocalDateTime} when success, null otherwise.
	 */
	public static LocalDateTime convertTimeZone(LocalDateTime localDateTime,
			TimeZone fromTimeZone, TimeZone toTimeZone) {

		if (localDateTime == null || fromTimeZone == null || toTimeZone == null) {
			return null;
		}

		try {
			ZonedDateTime orginZonedDateTime = localDateTime.atZone(fromTimeZone.toZoneId());
			return orginZonedDateTime.toInstant().atZone(toTimeZone.toZoneId())
					.toLocalDateTime();
		} catch (DateTimeException e) {
			log.error("error in converting time zone.", e);
		}

		return null;
	}

	/**
	 * Calculate the amount of days between the specified two LocalDate.
	 * @param stringFromDate from LocalDate
	 * @param stringToDate to LocalDate
	 * @param pattern the specified format pattern<BR>
	 *      see {@link DateFormat} to use the predefined pattern <BR>
	 *      see {@link DateTimeFormatter} to use the more pattern <BR>
	 *      use "uuuu-MM-dd" as default format when empty
	 * @return the amount of days between two LocalDate, null when failed
	 */
	public static long getDateDifference(String orgDtStr, 
			String trgDtStr, DateFormat format) {

		LocalDate orgDt = DateUtility.parseDate(orgDtStr, format.getFormat());
		long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
		LocalDate trgDt = DateUtility.parseDate(trgDtStr, format.getFormat());
		long trgDtTime = Date.from(trgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

		return ((orgDtTime - trgDtTime)/DATE_STEP);
	}

	/**
	 * Calculate the amount of time between the specified two LocalDateTimes.
	 * 
	 * @param stringFromDateTime from LocalDateTime
	 * @param stringToDateTime to LocalDateTime
	 * @param pattern the specified format pattern<BR>
	 *      see {@link DateTimeFormatType} to use the predefined pattern <BR>
	 *      see {@link DateTimeFormatter} to use more pattern <BR>
	 *      use {@link DateTimeFormatType.UUUUHMMHDD} as default format when empty
	 * @return the amount of time between two LocalDateTime, null when failed
	 */
	public static Long getDateTimeDifference(
			String stringFromDateTime, String stringToDateTime, String pattern) {

		LocalDateTime fromDateTime = parseDateTime(stringFromDateTime, pattern);
		LocalDateTime toDateTime = parseDateTime(stringToDateTime, pattern);

		if (fromDateTime == null || toDateTime == null) {
			return null;
		}

		return ChronoUnit.MILLIS.between(fromDateTime, toDateTime);
	}

	/**
	 * Calculate the amount of time between the specified two LocalDateTimes.
	 * 
	 * @param stringFromDateTime from LocalDateTime
	 * @param stringToDateTime to LocalDateTime
	 * @param pattern the specified format pattern<BR>
	 *      see {@link DateTimeFormatType} to use the predefined pattern <BR>
	 *      see {@link DateTimeFormatter} to use more pattern <BR>
	 *      use {@link DateTimeFormatType.UUUUHMMHDD} as default format when empty
	 * @return the amount of time between two LocalDateTime, null when failed
	 */
	public static Long getMonthDifference(
			String stringFromDate, String stringToDate, DateUtility.DateFormat format) {

		LocalDate fromDate = parseDate(stringFromDate, format.getFormat());
		LocalDate toDate = parseDate(stringToDate, format.getFormat());

		if (fromDate == null || toDate == null) {
			return null;
		}

		return ChronoUnit.MONTHS.between(fromDate, toDate);
	}

	/**
	 * 日付算出 (日時分秒)<P>
	 * 基準年月日と加算したい日数を引数で受け取り、年月日に日数（時分秒含む）を加算した日付を算出して返却する。
	 * 
	 * @param orgDt 日付元
	 * @param diffTime 加算日時分秒（long[4]配列：long[0]=日数差、long[1]=時数差、long[2]=分数差、long[3]=秒数差）
	 * @return　日付先
	 */
	public static Date addDate(Date orgDt, long[] diffTime) {
		long orgDtTime = orgDt.getTime();

		// 日付先 ＝ 日付元 + 差分日数 + 差分時数 + 差分分数 + 差分秒数
		orgDtTime += diffTime[0]*DATE_STEP + diffTime[1]*HOUR_STEP + diffTime[2]*MIN_STEP + diffTime[3]*SEC_STEP;

		return new Date(orgDtTime);
	}

	/**
	 * 日付算出 (日時分秒)<P>
	 * 基準年月日と加算したい日数を引数で受け取り、年月日に日数（時分秒含む）を加算した日付を算出して返却する。
	 * 
	 * @param orgDt 日付元
	 * @param diffTime 加算日時分秒（long[4]配列：long[0]=日数差、long[1]=時数差、long[2]=分数差、long[3]=秒数差）
	 * @return　日付先
	 */
	public static LocalDate addDate(LocalDate orgDt, long[] diffTime) {
		long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

		// 日付先 ＝ 日付元 + 差分日数 + 差分時数 + 差分分数 + 差分秒数
		orgDtTime += diffTime[0]*DATE_STEP + diffTime[1]*HOUR_STEP + diffTime[2]*MIN_STEP + diffTime[3]*SEC_STEP;

		return (new Date(orgDtTime)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 日付算出 (日時分秒)<P>
	 * 基準年月日と加算したい日数を引数で受け取り、年月日に日数（時分秒含む）を加算した日付を算出して返却する。
	 * 
	 * @param orgDt 日付元
	 * @param diffTime 加算日時分秒（long[4]配列：long[0]=日数差、long[1]=時数差、long[2]=分数差、long[3]=秒数差）
	 * @return　日付先
	 */
	public static String addDate(String orgDtStr, DateUtility.DateFormat format, long[] diffTime) {
		LocalDate orgDt = DateUtility.parseDate(orgDtStr, format.getFormat());
		long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

		// 日付先 ＝ 日付元 + 差分日数 + 差分時数 + 差分分数 + 差分秒数
		orgDtTime += diffTime[0]*DATE_STEP + diffTime[1]*HOUR_STEP + diffTime[2]*MIN_STEP + diffTime[3]*SEC_STEP;
		LocalDate trgDt = (new Date(orgDtTime)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return DateUtility.formatDate(trgDt, format.getFormat());
	}

	public static boolean isAfter(String orgDtStr, String trgDtStr, DateUtility.DateFormat format) {
		LocalDate orgDt = DateUtility.parseDate(orgDtStr, format.getFormat());
		LocalDate trgDt = DateUtility.parseDate(trgDtStr, format.getFormat());
		return orgDt.isAfter(trgDt);
	}

	public static boolean isBefore(String orgDtStr, String trgDtStr, DateUtility.DateFormat format) {
		LocalDate orgDt = DateUtility.parseDate(orgDtStr, format.getFormat());
		LocalDate trgDt = DateUtility.parseDate(trgDtStr, format.getFormat());
		return orgDt.isBefore(trgDt);
	}

	public static boolean isEqual(String orgDtStr, String trgDtStr, DateUtility.DateFormat format) {
		LocalDate orgDt = DateUtility.parseDate(orgDtStr, format.getFormat());
		LocalDate trgDt = DateUtility.parseDate(trgDtStr, format.getFormat());
		return orgDt.isEqual(trgDt);
	}

	public static boolean isAfter(String orgDtStr, String trgDtStr, DateUtility.DateTimeFormat format) {
		LocalDateTime orgDt = DateUtility.parseDateTime(orgDtStr, format.getFormat());
		LocalDateTime trgDt = DateUtility.parseDateTime(trgDtStr, format.getFormat());
		return orgDt.isAfter(trgDt);
	}

	public static boolean isBefore(String orgDtStr, String trgDtStr, DateUtility.DateTimeFormat format) {
		LocalDateTime orgDt = DateUtility.parseDateTime(orgDtStr, format.getFormat());
		LocalDateTime trgDt = DateUtility.parseDateTime(trgDtStr, format.getFormat());
		return orgDt.isBefore(trgDt);
	}

	public static boolean isEqual(String orgDtStr, String trgDtStr, DateUtility.DateTimeFormat format) {
		LocalDateTime orgDt = DateUtility.parseDateTime(orgDtStr, format.getFormat());
		LocalDateTime trgDt = DateUtility.parseDateTime(trgDtStr, format.getFormat());
		return orgDt.isEqual(trgDt);
	}

	/**
	 * 日数差算出<P>
	 * 基準年月日とそれに対する日数差を求めたい年月日を受け取り、それらの日数差（時分秒含む）を算出して返却する。
	 * 
	 * @param orgDt 差分元日付
	 * @param trgDt 差分先日付
	 * @return long[] 差分結果<BR>
	 * 		long[0] 日数差<BR>
	 * 		long[1] 時数差<BR>
	 * 		long[2] 分数差<BR>
	 * 		long[3] 秒数差<BR>
	 */
	public static long[] getDateDifference(String orgDtStr, String trgDtStr, DateUtility.DateTimeFormat format) {
		LocalDateTime orgDt = DateUtility.parseDateTime(orgDtStr, format.getFormat());
		long orgDtTime = Date.from(orgDt.atZone(ZoneId.systemDefault()).toInstant()).getTime();
		LocalDateTime trgDt = DateUtility.parseDateTime(trgDtStr, format.getFormat());
		long trgDtTime = Date.from(trgDt.atZone(ZoneId.systemDefault()).toInstant()).getTime();

		long[] result = new long[4];
		//差分日数
		result[0] = ((trgDtTime - orgDtTime)/DATE_STEP);
		long dtDiff = (trgDtTime - orgDtTime)%DATE_STEP;
		//差分時数
		result[1] = dtDiff/HOUR_STEP;
		long hourDiff = dtDiff%HOUR_STEP;
		//差分分数
		result[2] = hourDiff/MIN_STEP;
		long minDiff = hourDiff%MIN_STEP;
		//差分秒数
		result[3] = minDiff/SEC_STEP;

		return result;
	}

	public static String getCurrentDateTime() {
		return getCurrentDateTime(DateUtility.DateTimeFormat.UUUUMMDDHHMISS);
	}

	public static String getCurrentDateTime(DateUtility.DateTimeFormat format) {
		return DateUtility.formatDateTime(LocalDateTime.now(), format.getFormat());
	}

	public static String getCurrentDate() {
		return getCurrentDate(DateUtility.DateFormat.UUUUMMDD);
	}


	public static String getCurrentDate(DateUtility.DateFormat format) {
		return DateUtility.formatDate(LocalDate.now(), format.getFormat());
	}

	public static String getCurrentMonth() {
		return getCurrentMonth(DateUtility.MonthFormat.UUUUMM);
	}

	public static String getCurrentMonth(DateUtility.MonthFormat format) {
		return DateUtility.formatDate(LocalDate.now(), format.getFormat());
	}

	public static YearMonth parseYearMonth(String yearMonth, DateUtility.MonthFormat format) {
		String formatPattern = format.getFormat();
		if (!StringUtils.hasText(formatPattern)) {
			formatPattern =  DateUtility.MonthFormat.UUUUMM.getFormat();
		}

		try {
			DateTimeFormatter dateTimeFormatter = 
					DateTimeFormatter.ofPattern(formatPattern)
					.withResolverStyle(ResolverStyle.STRICT);
			return YearMonth.parse(yearMonth, dateTimeFormatter);
		} catch (DateTimeParseException | IllegalArgumentException e) {
			log.error("error in parsing date string.", e);
		}

		return null;
	}

	public static List<String> getMonthDates(String yearMonthStr, DateUtility.MonthFormat format, DateUtility.DateFormat outFormat) {
		YearMonth yearMonth = parseYearMonth(yearMonthStr, format);
		LocalDate start = yearMonth.atDay(1);
		LocalDate end   = yearMonth.atEndOfMonth();
		long numOfDaysBetween = ChronoUnit.DAYS.between(start, end); 
		return IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetween+1)
				.mapToObj(i -> formatDate(start.plusDays(i), outFormat.getFormat()))
				.collect(Collectors.toList()); 
	}


	/**
	 * 曜日算出<P>
	 * 指定された日付の曜日を算出する。
	 * 
	 * @param orgDt 日付元
	 * @return 曜日数値　<BR>
	 * Calendar.SUNDAY=1 <BR>
	 * Calendar.MONDAY=2 <BR> 
	 * Calendar.TUESDAY=3 <BR>
	 * Calendar.WEDNESDAY=4 <BR> 
	 * Calendar.THURSDAY=5 <BR> 
	 * Calendar.FRIDAY=6 <BR> 
	 * Calendar.SATURDAY=7 
	 */
	public static WeekDayType getWeekDay(String trgDtStr, DateUtility.DateFormat format) {
		LocalDate trgDt = parseDate(trgDtStr, format.getFormat());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date.from(trgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return WeekDayType.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
	}

	/**
	 * default date format pattern enum.
	 *
	 */
	public enum MonthFormat {
		/** date format context<br> (uuuuMM). */
		UUUUMM("uuuuMM"), 
		/** date format context<br> (uuuu/MM). */
		UUUUSMM("uuuu/MM"),
		/** date format context<br> (uuuu-MM). */
		UUUUHMM("uuuu-MM"),
		/** date format context<br> (uuuu.MM). */
		UUUUDMM("uuuu.MM"),
		/** date format context<br> (uuuu年MM月). */
		UUUUCMMC("uuuu年MM月");

		/** format pattern. */
		@Getter
		private String format;

		private MonthFormat(String format) {
			this.format = format;
		}
	}

	/**
	 * default date format pattern enum.
	 *
	 */
	public enum DateFormat {
		/** date format context<br> (uuuuMMdd). */
		UUUUMMDD("uuuuMMdd"), 
		/** date format context<br> (uuuu/MM/dd). */
		UUUUSMMSDD("uuuu/MM/dd"),
		/** date format context<br> (uuuu-MM-dd). */
		UUUUHMMHDD("uuuu-MM-dd"),
		/** date format context<br> (uuuu.MM.dd). */
		UUUUDMMDDD("uuuu.MM.dd");

		/** format pattern. */
		@Getter
		private String format;

		private DateFormat(String format) {
			this.format = format;
		}
	}

	/**
	 * default date format pattern enum.
	 *
	 */
	public enum TimeFormat {
		/** date format context<br> (HHmmss). */
		HHMISS("HHmmss"), 
		/** date format context<br> (HH:mm:ss). */
		HHQMIQSS("HH:mm:ss"),
		/** date format context<br> (HHmm). */
		HHMI("HHmm"),
		/** date format context<br> (HH:mm). */
		HHQMI("HH:mm");

		/** format pattern. */
		@Getter
		private String format;

		private TimeFormat(String format) {
			this.format = format;
		}
	}

	/**
	 * default dateTime format pattern enum.
	 *
	 */
	public enum DateTimeFormat {
		/** date time format context<br> (uuuuMMddHHmmss). */
		UUUUMMDDHHMISS("uuuuMMddHHmmss"),
		/** date time format context<br> (uuuu/MM/dd HH:mm:ss). */
		UUUUSMMSDDHHQMIQSS("uuuu/MM/dd HH:mm:ss"),
		/** date time format context<br> (uuuu-MM-dd HH:mm:ss). */
		UUUUHMMHDDHHQMIQSS("uuuu-MM-dd HH:mm:ss"),
		/** date time format context<br> (uuuu/MM/dd HH:mm:ss.SSS). */
		UUUUSMMSDDHHQMIQSS_SSS("uuuu/MM/dd HH:mm:ss.SSS"),
		/** date time format context<br> (uuuu-MM-dd HH:mm:ss.SSS). */
		UUUUHMMHDDHHQMIQSS_SSS("uuuu-MM-dd HH:mm:ss.SSS");

		/** format pattern. */
		@Getter
		private String format;

		private DateTimeFormat(String format) {
			this.format = format;
		}
	}
}

