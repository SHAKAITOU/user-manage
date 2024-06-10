
package cn.caam.gs.common.util;

import java.lang.invoke.MethodHandles;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

/**
 * LocalDate変換用共通部品クラス.
 *
 * @version 1.0.0
 * @author 謝海涛 
 *
 *
 */
public class LocalDateUtility {

    public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /** 一秒ミリ秒単位. */
    public static final long SEC_STEP = 1000;

    /** 一分間ミリ秒単位. */
    public static final long MIN_STEP = SEC_STEP * 60;

    /** 一時間ミリ秒単位. */
    public static final long HOUR_STEP = MIN_STEP * 60;

    /** 一日ミリ秒単位. */
    public static final long DATE_STEP = HOUR_STEP * 24;

    /** 日付変換クラス用エラーログタイトル01. */
    public static final String LOCALDATE_UTILITY_ERROR_LOG_TITLE_01 = "日付変換失敗";

    /** 日付変換クラス用エラーメッセージ内容01. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_01 = "(String -> LocalDate)日付変換を失敗しました。";

    /** 日付変換クラス用エラーメッセージ内容02. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_02 = "(String -> LocalTime)時刻変換を失敗しました。";

    /** 日付変換クラス用エラーメッセージ内容03. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_03 = "(String -> LocalDateTime)日時変換を失敗しました。";

    /** 日付変換クラス用エラーメッセージ内容04. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_04 = "(LocalDate -> String)日付変換を失敗しました。";

    /** 日付変換クラス用エラーメッセージ内容05. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_05 = "(LocaTime -> String)時刻変換を失敗しました。";

    /** 日付変換クラス用エラーメッセージ内容06. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_06 = "(LocalDateTime -> String)日時変換を失敗しました。";

    /** 日付変換クラス用エラーメッセージ内容07. */
    public static final String LOCALDATE_UTILITY_ERROR_MSG_01_07 = "(LocalDateTime -> LocalDateTime)日時タイムゾーン変換を失敗しました。";

    //----------------------------------
    //      共通日付、日時、時刻フォマード定義
    //----------------------------------





    private LocalDateUtility() {
        
    }

    /**
     * 指定された日付コンテキストを指定する日付フォーマット形式でLocalDateに変換する.
     *
     * @param dateString 指定された日付コンテキスト
     * @param pattern  指定する日付フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 日時フォーマット形式参照<BR>
     *      use "uuuu/MM/dd" デフォルト日付フォーマット形式、日付フォーマット形式がnull或いは空きの場合
     * @return {@link LocalDate} 変換成功の場合、失敗の場合nullを返す
     */
    public static LocalDate parseLocalDate(final String dateString, final DatePattern pattern) {

        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        String formatPattern = pattern.getPattern();
        if (StringUtils.isEmpty(formatPattern)) {
            formatPattern = DatePattern.UUUUSMMSDD.getPattern();
        }

        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDate.parse(dateString, dateTimeFormatter);

        } catch (DateTimeParseException | IllegalArgumentException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_01, e);
        }

        return null;
    }

    /**
     * 指定された時刻コンテキストを指定する時刻フォーマット形式でLocalTimeに変換する.
     *
     * @param timeString 指定された時刻コンテキスト
     * @param pattern  指定する時刻フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 時刻フォーマット形式参照<BR>
     *      use "HH:mm:ss" デフォルト時刻フォーマット形式、時刻フォーマット形式がnull或いは空きの場合
     * @return {@link LocalTime} 変換成功の場合、失敗の場合nullを返す
     */
    public static LocalTime parseTime(final String timeString, final TimePattern pattern) {

        if (StringUtils.isEmpty(timeString)) {
            return null;
        }

        String formatPattern = pattern.getPattern();
        if (StringUtils.isEmpty(formatPattern)) {
            formatPattern =  TimePattern.HHQMIQSS.getPattern();
        }

        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalTime.parse(timeString, dateTimeFormatter);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_02, e);
        }

        return null;
    }

    /**
     * 指定された日時コンテキストを指定する日時フォーマット形式でLocalDateTimeに変換する.
     *
     * @param dateTimeString 指定された日時コンテキスト
     * @param pattern  指定する日時フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 日時フォーマット形式参照<BR>
     *      use "uuuu/MM/dd HH:mm:ss" デフォルト日時フォーマット形式、日時フォーマット形式がnull或いは空きの場合
     * @return {@link LocalDateTime} 変換成功の場合、失敗の場合nullを返す
     */
    public static LocalDateTime parseLocalDateTime(final String dateTimeString, final DateTimePattern pattern) {

        if (StringUtils.isEmpty(dateTimeString)) {
            return null;
        }

        String formatPattern = pattern.getPattern();
        if (StringUtils.isEmpty(formatPattern)) {
            formatPattern =  DateTimePattern.UUUUSMMSDDHHQMIQSS.getPattern();
        }

        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_03, e);
        }

        return null;
    }

    /**
     * 指定された日時コンテキストを指定する日時フォーマット形式でDateに変換する.
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parseDateTime(final String dateString, final DateTimePattern pattern) {
        return localDateTime2Date(parseLocalDateTime(dateString, pattern));
    }

    /**
     * 指定された日時コンテキストを指定する日時フォーマット形式でDateに変換する.
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parseUtcDateTime(final String dateString, final DateTimePattern pattern) {
        return localDateTime2Date(convertUtc(parseLocalDateTime(dateString, pattern), TimeZone.getTimeZone(ZoneId.systemDefault())));
    }

    /**
     * 指定されたLocalDateを指定する日付フォーマット形式でStringに変換する.
     *
     * @param localDate 指定されたLocalDate
     * @param pattern  指定する日付フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 日付フォーマット形式参照<BR>
     *      use "uuuu/MM/dd" デフォルト日付フォーマット形式、日付フォーマット形式がnull或いは空きの場合
     * @return String変換成功の場合、失敗の場合nullを返す
     */
    public static String formatDate(final LocalDate localDate, final DatePattern pattern) {

        if (localDate == null) {
            return null;
        }

        String formatPattern = pattern.getPattern();
        if (StringUtils.isEmpty(formatPattern)) {
            formatPattern =  DatePattern.UUUUSMMSDD.getPattern();
        }

        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return localDate.format(dateTimeFormatter);
        } catch (DateTimeException | IllegalArgumentException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_04, e);
        }

        return null;
    }

    /**
     * 指定されたDateを指定する日付フォーマット形式でStringに変換する.
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(final Date date, final DatePattern pattern) {
        return formatDate(date2LocalDate(date), pattern);
    }

    /**
     * 指定されたLocalTimeを指定する時刻フォーマット形式でStringに変換する.
     *
     * @param localTime 指定されたLocalTime
     * @param pattern  指定する時刻フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 時刻フォーマット形式参照<BR>
     *      use "HH:mm:ss" デフォルト時刻フォーマット形式、時刻フォーマット形式がnull或いは空きの場合
     * @return String変換成功の場合、失敗の場合nullを返す
     */
    public static String formatTime(final LocalTime localTime, final TimePattern pattern) {

        if (localTime == null) {
            return null;
        }

        String formatPattern = pattern.getPattern();
        if (StringUtils.isEmpty(formatPattern)) {
            formatPattern =  TimePattern.HHQMIQSS.getPattern();
        }

        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return localTime.format(dateTimeFormatter);
        } catch (DateTimeException | IllegalArgumentException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_05, e);
        }

        return null;
    }

    /**
     * 指定されたLocalDateTimeを指定する日時フォーマット形式でStringに変換する.
     *
     * @param localDateTime 指定されたLocalTime
     * @param pattern  指定する日時フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 日時フォーマット形式参照<BR>
     *      use "HH:mm:ss" デフォルト日時フォーマット形式、日時フォーマット形式がnull或いは空きの場合
     * @return String変換成功の場合、失敗の場合nullを返す
     */
    public static String formatDateTime(final LocalDateTime localDateTime, final DateTimePattern pattern) {

        if (localDateTime == null) {
            return null;
        }

        String formatPattern = pattern.getPattern();
        if (StringUtils.isEmpty(formatPattern)) {
            formatPattern =  DateTimePattern.UUUUSMMSDDHHQMIQSS.getPattern();
        }

        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeException | IllegalArgumentException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_06, e);
        }

        return null;
    }

    public static String formatDateTime(final Date date, final DateTimePattern pattern) {
        return formatDateTime(date2LocalDateTime(date), pattern);
    }

    /**
     * 指定されたLocalDateTimeを変換元タイムゾーンに基づく、変換先タイムゾーンに変換する.
     *
     * @param localDateTime 指定されたLocalDateTime
     * @param fromTimeZone 変換元タイムゾーン<br>
     *                                 see {@link TimeZone} タイムゾーン参照
     *                                 see {@link ZoneId} ゾーンID参照
     * @param toTimeZone 変換先タイムゾーン
     * @return {@link LocalDateTime} 変換成功の場合、失敗の場合nullを返す
     */
    public static LocalDateTime convertTimeZone(final LocalDateTime localDateTime,
            final TimeZone fromTimeZone, final TimeZone toTimeZone) {

        if (localDateTime == null || fromTimeZone == null || toTimeZone == null) {
            return null;
        }

        try {
            ZonedDateTime orginZonedDateTime = localDateTime.atZone(fromTimeZone.toZoneId());
            return orginZonedDateTime.toInstant().atZone(toTimeZone.toZoneId())
                    .toLocalDateTime();
        } catch (DateTimeException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_07, e);
        }

        return null;
    }

    /**
     * 日数差算出<br>
     * 基準年月日とそれに対する日数差を求めたい年月日を受け取り、
     * それらの日数差（時分秒含まない）を算出して返却する.
     *
     * @param orgDt 差分元日付
     * @param trgDt 差分先日付
     * @return long 日数差
     */
    public static Long getDateDifference(final LocalDate orgDt, final LocalDate trgDt) {

        if (orgDt == null || trgDt == null) {
            return null;
        }

        long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
        long trgDtTime = Date.from(trgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

        return ((orgDtTime - trgDtTime) / DATE_STEP);
    }

    /**
     * 日数差算出<br>
     * 指定された日付フォーマット形式に基づく、基準年月日とそれに対する日数差を求めたい年月日を受け取り、
     * それらの日数差（時分秒含まない）を算出して返却する.
     *
     * @param orgDtStr 差分元日付コンテキスト
     * @param trgDtStr 差分先日付コンテキスト
     * @param pattern 指定された日付フォーマット形式
     * @return long 日数差
     */
    public static Long getDateDifference(final String orgDtStr, final String trgDtStr, final DatePattern pattern) {

        if (StringUtils.isEmpty(orgDtStr) || StringUtils.isEmpty(orgDtStr) || pattern == null) {
            return null;
        }

        LocalDate orgDt = parseLocalDate(orgDtStr, pattern);
        LocalDate trgDt = parseLocalDate(trgDtStr, pattern);

        return getDateDifference(orgDt, trgDt);
    }

    /**
     * 日数差算出<br>
     * 指定された日付フォーマット形式に基づく、基準年月日とそれに対する日数差を求めたい年月日を受け取り、
     * それらのミリ秒差を算出して返却する.
     *
     * @param fromDateTime 差分元日時
     * @param toDateTime 差分先日時
     * @return long ミリ秒差結果
     */
    public static Long getDateTimeDifference(
            final LocalDateTime fromDateTime, final LocalDateTime toDateTime) {

        if (fromDateTime == null || toDateTime == null) {
            return null;
        }

        return ChronoUnit.MILLIS.between(fromDateTime, toDateTime);
    }

    /**
     * 日数差算出<br>
     * 指定された日付フォーマット形式に基づく、基準年月日とそれに対する日数差を求めたい年月日を受け取り、
     * それらのミリ秒差を算出して返却する.
     *
     * @param stringFromDateTime 差分元日時
     * @param stringToDateTime 差分先日時
     * @param pattern 指定された日時フォーマット形式
     * @return long ミリ秒差結果
     */
    public static Long getDateTimeDifference(
            final String stringFromDateTime, final String stringToDateTime, final DateTimePattern pattern) {

        LocalDateTime fromDateTime = parseLocalDateTime(stringFromDateTime, pattern);
        LocalDateTime toDateTime = parseLocalDateTime(stringToDateTime, pattern);

        if (fromDateTime == null || toDateTime == null) {
            return null;
        }

        return getDateTimeDifference(fromDateTime, toDateTime);
    }


    /**
     * 日付算出 (日数)<br>
     * 基準年月日と加算したい日数を引数で受け取り、年月日に日数を加算した日付を算出して返却する.
     *
     * @param orgDt 日付元
     * @param days 加算日数
     * @return 日付先
     */
    public static LocalDate addDate(final LocalDate orgDt, final long days) {

        if (orgDt == null) {
            return null;
        }

        long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

        // 日付先 ＝ 日付元 + 加算日数
        orgDtTime += days * DATE_STEP;

        return (new Date(orgDtTime)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 日付算出 (日数)<br>
     * 基準年月日と加算したい日数を引数で受け取り、年月日に日数を加算した日付を算出して返却する.
     *
     * @param orgDtStr 日付元
     * @param pattern 指定された日付フォーマット形式
     * @param days 加算日数
     * @return 日付先
     */
    public static String addDate(final String orgDtStr, final DatePattern pattern, final long days) {

        if (StringUtils.isEmpty(orgDtStr)) {
            return null;
        }

        LocalDate orgDt = parseLocalDate(orgDtStr, pattern);
        long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

        // 日付先 ＝ 日付元 + 加算日数
        orgDtTime += days * DATE_STEP;
        LocalDate trgDt = (new Date(orgDtTime)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return formatDate(trgDt, pattern);
    }

    /**
     * 指定された日付コンテキストより後にあるかどうかをチェックする.
     * @param orgDtStr 日付元
     * @param trgDtStr 日付先
     * @param pattern 指定された日付フォーマット形式
     * @return true : より後 <br>
     *                 false : より前
     */
    public static boolean isAfterDate(final String orgDtStr, final String trgDtStr, final DatePattern pattern) {
        LocalDate orgDt = parseLocalDate(orgDtStr, pattern);
        LocalDate trgDt = parseLocalDate(trgDtStr, pattern);
        return orgDt.isAfter(trgDt);
    }

    /**
     * 指定された日付コンテキストより前にあるかどうかをチェックする.
     *
     * @param orgDtStr 日付元
     * @param trgDtStr 日付先
     * @param pattern 指定された日付フォーマット形式
     * @return true : より前 <br>
     *                 false : より後
     */
    public static boolean isBeforeDate(final String orgDtStr, final String trgDtStr, final DatePattern pattern) {
        LocalDate orgDt = parseLocalDate(orgDtStr, pattern);
        LocalDate trgDt = parseLocalDate(trgDtStr, pattern);
        return orgDt.isBefore(trgDt);
    }

    /**
     * 指定された日付コンテキストが等しいかどうかをチェックする.
     *
     * @param orgDtStr 日付元
     * @param trgDtStr 日付先
     * @param pattern 指定された日付フォーマット形式
     * @return true : 等しい <br>
     *                 false : 等しくない
     */
    public static boolean isEqualDate(final String orgDtStr, final String trgDtStr, final DatePattern pattern) {
        LocalDate orgDt = parseLocalDate(orgDtStr, pattern);
        LocalDate trgDt = parseLocalDate(trgDtStr, pattern);
        return orgDt.isEqual(trgDt);
    }

    /**
     * 指定された日時コンテキストより後にあるかどうかをチェックする.
     * @param orgDtStr 日時元
     * @param trgDtStr 日時先
     * @param pattern 指定された日時フォーマット形式
     * @return true : より後 <br>
     *                 false : より前
     */
    public static boolean isAfterDateTime(final String orgDtStr, final String trgDtStr, final DateTimePattern pattern) {
        LocalDateTime orgDt = parseLocalDateTime(orgDtStr, pattern);
        LocalDateTime trgDt = parseLocalDateTime(trgDtStr, pattern);
        return orgDt.isAfter(trgDt);
    }

    /**
     * 指定された日時コンテキストより前にあるかどうかをチェックする.
     *
     * @param orgDtStr 日時元
     * @param trgDtStr 日時先
     * @param pattern 指定された日時フォーマット形式
     * @return true : より前 <br>
     *                 false : より後
     */
    public static boolean isBeforeDateTime(final String orgDtStr, final String trgDtStr, final DateTimePattern pattern) {
        LocalDateTime orgDt = parseLocalDateTime(orgDtStr, pattern);
        LocalDateTime trgDt = parseLocalDateTime(trgDtStr, pattern);
        return orgDt.isBefore(trgDt);
    }

    /**
     * 指定された日付コンテキストが等しいかどうかをチェックする.
     *
     * @param orgDtStr 日時元
     * @param trgDtStr 日時先
     * @param pattern 指定された日時フォーマット形式
     * @return true : 等しい <br>
     *                 false : 等しくない
     */
    public static boolean isEqualDateTime(final String orgDtStr, final String trgDtStr, final DateTimePattern pattern) {
        LocalDateTime orgDt = parseLocalDateTime(orgDtStr, pattern);
        LocalDateTime trgDt = parseLocalDateTime(trgDtStr, pattern);
        return orgDt.isEqual(trgDt);
    }

    /**
     * 日数差算出（日時分秒分ける）<br>
     * 基準年月日とそれに対する日数差を求めたい年月日を受け取り、それらの日数差（日時分秒分ける）を算出して返却する.
     *
     * @param fromDtStr 差分元日時
     * @param toDtStr 差分先日時
     * @return long[] 差分結果<BR>
     *                         long[0] 日数差<BR>
     *                         long[1] 時数差<BR>
     *                         long[2] 分数差<BR>
     *                         long[3] 秒数差<BR>
     */
    public static long[] getDateTimeDifferenceArray(final String fromDtStr, final String toDtStr, final DateTimePattern pattern) {
        LocalDateTime fromDt = parseLocalDateTime(fromDtStr, pattern);
        long fromDtTime = Date.from(fromDt.atZone(ZoneId.systemDefault()).toInstant()).getTime();
        LocalDateTime toDt = parseLocalDateTime(toDtStr, pattern);
        long toDtTime = Date.from(toDt.atZone(ZoneId.systemDefault()).toInstant()).getTime();

        long[] result = new long[4];
        //差分日数
        result[0] = ((toDtTime - fromDtTime) / DATE_STEP);
        long dtDiff = (toDtTime - fromDtTime) % DATE_STEP;
        //差分時数
        result[1] = dtDiff / HOUR_STEP;
        long hourDiff = dtDiff % HOUR_STEP;
        //差分分数
        result[2] = hourDiff / MIN_STEP;
        long minDiff = hourDiff % MIN_STEP;
        //差分秒数
        result[3] = minDiff / SEC_STEP;

        return result;
    }

    /**
     * デフォルトの日時フォーマット形式"uuuu/MM/dd HH:mm:ss"で
     * デフォルトのタイムゾーンのシステム・クロックから現在の日時を取得する.
     * @return 現在の日時コンテキスト
     */
    public static String getCurrentDateTimeString() {
        return getCurrentDateTimeString(DateTimePattern.UUUUSMMSDDHHQMIQSS);
    }

    /**
     * 指定する日時フォーマット形式で
     * デフォルトのタイムゾーンのシステム・クロックから現在の日時を取得する.
     * @return 現在の日時コンテキスト
     */
    public static String getCurrentDateTimeString(final DateTimePattern pattern) {
        return formatDateTime(LocalDateTime.now(), pattern);
    }

    /**
     * 指定するUTC日時フォーマット形式で
     * デフォルトのタイムゾーンのシステム・クロックから現在の日時を取得する.
     * @return 現在の日時コンテキスト
     */
    public static String getCurrentUtcDateTimeString(final DateTimePattern pattern) {

        return formatDateTime(LocalDateTime.now(ZoneOffset.UTC), pattern);
    }

    /**
     * デフォルトの日付フォーマット形式"uuuu/MM/dd"で
     * デフォルトのタイムゾーンのシステム・クロックから現在の日付を取得する.
     * @return 現在の日付コンテキスト
     */
    public static String getCurrentDateString() {
        return getCurrentDateString(DatePattern.UUUUSMMSDD);
    }

    /**
     * 指定する日付フォーマット形式で
     * デフォルトのタイムゾーンのシステム・クロックから現在の日付を取得する.
     * @return 現在の日付コンテキスト
     */
    public static String getCurrentDateString(final DatePattern pattern) {
        return formatDate(LocalDate.now(), pattern);
    }

    /**
     * 指定するUTC日付フォーマット形式で
     * デフォルトのタイムゾーンのシステム・クロックから現在の日付を取得する.
     * @return 現在の日付コンテキスト
     */
    public static String getCurrentUtcDateString(final DatePattern pattern) {
        return formatDate(LocalDate.now(ZoneOffset.UTC), pattern);
    }

    /**
     * 現在日付を取得
     * デフォルトのタイムゾーンのシステム・クロックから現在の日付を取得する.
     * @return 現在の日付コンテキスト
     */
    public static Date getCurrentDate() {
        return localDateTime2Date(LocalDateTime.now());
    }

    /**
     * 現在UTC日付を取得
     * デフォルトのタイムゾーンのシステム・クロックから現在の日付を取得する.
     * @return 現在の日付コンテキスト
     */
    public static Date getCurrentUtcDate() {
        return localDateTime2Date(LocalDateTime.now(ZoneOffset.UTC));
    }

    /**
     * 曜日算出<br>
     * 指定された日付の曜日を算出する.
     *
     * @param trgDtStr 日付元
     * @return 曜日数値 <BR>
     *                 Calendar.SUNDAY=1 <BR>
     *                 Calendar.MONDAY=2 <BR>
     *                 Calendar.TUESDAY=3 <BR>
     *                 Calendar.WEDNESDAY=4 <BR>
     *                 Calendar.THURSDAY=5 <BR>
     *                 Calendar.FRIDAY=6 <BR>
     *                 Calendar.SATURDAY=7
     */
    public static int getWeekDay(final String trgDtStr, final DatePattern pattern) {
        LocalDate trgDt = parseLocalDate(trgDtStr, pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(trgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 指定されたLocalDateTimeを変換元タイムゾーンに基づく、UTCタイムゾーンに変換する.
     *
     * @param localDateTime 指定されたLocalDateTime
     * @param fromTimeZone 変換元タイムゾーン<br>
     *                                 see {@link TimeZone} タイムゾーン参照
     *                                 see {@link ZoneId} ゾーンID参照
     * @return {@link LocalDateTime} 変換成功の場合、失敗の場合nullを返す
     */
    public static LocalDateTime convertUtc(final LocalDateTime localDateTime,
            final TimeZone fromTimeZone) {

        if (localDateTime == null || fromTimeZone == null) {
            return null;
        }

        try {
            ZonedDateTime orginZonedDateTime = localDateTime.atZone(fromTimeZone.toZoneId());
            return orginZonedDateTime.toInstant().atZone(ZoneId.of("UTC"))
                    .toLocalDateTime();
        } catch (DateTimeException e) {
            LOGGER.error(LOCALDATE_UTILITY_ERROR_LOG_TITLE_01,
                    LOCALDATE_UTILITY_ERROR_MSG_01_07, e);
        }

        return null;
    }

    /**
     * 指定された日時コンテキストを指定する日時フォーマット形式でLocalTimeに変換する.
     *
     * @param dateTimeString 指定された日時コンテキスト
     * @param pattern  指定する日時フォーマット形式<BR>
     *      see {@link DateTimeFormatter} 日時フォーマット形式参照<BR>
     *      use "uuuu/MM/dd HH:mm:ss" デフォルト日時フォーマット形式、日時フォーマット形式がnull或いは空きの場合
     * @return {@link LocalDateTime} 変換成功の場合、失敗の場合nullを返す
     */
    public static LocalDateTime parseUtcDateTime(final String dateTimeString, final DateTimePattern pattern, final TimeZone fromTimeZone) {

        if (StringUtils.isEmpty(dateTimeString)) {
            return null;
        }

        LocalDateTime localDateTime = parseLocalDateTime(dateTimeString, pattern);

        return convertUtc(localDateTime, fromTimeZone);

    }

    /**
     * java.util.Date to java.time.LocalDate
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * java.util.Date to java.time.LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    /**
     * java.time.LocalDate to java.util.Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * java.time.LocalDateTime to java.util.Date
     * @param localDate
     * @return
     */
    public static Date localDateTime2Date(final LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
    
    /**
     * 日付フォーマット
     */
    public enum DatePattern {
        /** 日付フォーマットコンテキスト<br> (uuuuMMdd). */
        UUUUMMDD  ("uuuuMMdd"),
        /** 日付フォーマットコンテキスト<br> (uuuu/MM/dd). */
        UUUUSMMSDD("uuuu/MM/dd"),
        /** 日付フォーマットコンテキスト<br> (uuuu-MM-dd). */
        UUUUHMMHDD("uuuu-MM-dd"),
        /** 日付フォーマットコンテキスト<br> (uuuu.MM.dd). */
        UUUUDMMDDD("uuuu.MM.dd"),
        ;

        /** キー. */
        @Getter
        private String pattern;

        /**
         * プライベートコンストラクタ.
         * @param key キー
         */
        private DatePattern(final String pattern) {
            this.pattern = pattern;
        }

        /**
         * 指定されたキーにたいして、列挙型オブジェクトを返す.
         *
         * @param key 指定されたキー
         * @return EncodingType 列挙型オブジェクト
         */
        public static DatePattern keyOf(final String pattern) {
            for (DatePattern b : DatePattern.values()) {
                if (b.pattern.equalsIgnoreCase(pattern)) {
                    return b;
                }
            }
            return null;
        }
    }
    
    /**
     * 日時フォーマット
     */
    public enum DateTimePattern {
        /** 日付時刻フォーマットコンテキスト<br> (uuuuMMdd HHmmss). */
        UUUUMMDD_HHMISS         ("uuuuMMdd HHmmss"),
        /** 日付時刻フォーマットコンテキスト<br> (uuuuMMddHHmmss). */
        UUUUMMDDHHMISS         ("uuuuMMddHHmmss"),
        /** 日付時刻フォーマットコンテキスト<br> (uuuu/MM/dd HH:mm:ss). */
        UUUUSMMSDDHHQMIQSS     ("uuuu/MM/dd HH:mm:ss"),
        /** 日付時刻フォーマットコンテキスト<br> (uuuu-MM-dd HH:mm:ss). */
        UUUUHMMHDDHHQMIQSS     ("uuuu-MM-dd HH:mm:ss"),
        /** 日付時刻フォーマットコンテキスト<br> (uuuu/MM/dd HH:mm:ss.SSS). */
        UUUUSMMSDDHHQMIQSS_SSS ("uuuu/MM/dd HH:mm:ss.SSS"),
        /** 日付時刻フォーマットコンテキスト<br> (uuuu-MM-dd HH:mm:ss.SSS). */
        UUUUHMMHDDHHQMIQSS_SSS ("uuuu-MM-dd HH:mm:ss.SSS"),
        ;

        /** キー. */
        @Getter
        private String pattern;

        /**
         * プライベートコンストラクタ.
         * @param key キー
         */
        private DateTimePattern(final String pattern) {
            this.pattern = pattern;
        }

        /**
         * 指定されたキーにたいして、列挙型オブジェクトを返す.
         *
         * @param key 指定されたキー
         * @return EncodingType 列挙型オブジェクト
         */
        public static DateTimePattern keyOf(final String pattern) {
            for (DateTimePattern b : DateTimePattern.values()) {
                if (b.pattern.equalsIgnoreCase(pattern)) {
                    return b;
                }
            }
            return null;
        }
    }
    
    /**
     * 時刻フォーマット
     */
    public enum TimePattern {

        /** 時刻フォーマットコンテキスト<br> (HHmmss). */
        HHMISS("HHmmss"),
        /** 時刻フォーマットコンテキスト<br> (HH:mm:ss). */
        HHQMIQSS("HH:mm:ss"),
        /** 時刻フォーマットコンテキスト<br> (HHmm). */
        HHMI("HHmm"),
        /** 時刻フォーマットコンテキスト<br> (HH:mm). */
        HHQMI("HH:mm"),
        ;

        /** キー. */
        @Getter
        private String pattern;

        /**
         * プライベートコンストラクタ.
         * @param key キー
         */
        private TimePattern(final String pattern) {
            this.pattern = pattern;
        }

        /**
         * 指定されたキーにたいして、列挙型オブジェクトを返す.
         *
         * @param key 指定されたキー
         * @return EncodingType 列挙型オブジェクト
         */
        public static TimePattern keyOf(final String pattern) {
            for (TimePattern b : TimePattern.values()) {
                if (b.pattern.equalsIgnoreCase(pattern)) {
                    return b;
                }
            }
            return null;
        }
    }
}
