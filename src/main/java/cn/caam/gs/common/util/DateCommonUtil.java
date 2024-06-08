package cn.caam.gs.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

public class DateCommonUtil {
    
    @Autowired
    private MessageSourceUtil msg;
    
    public static final String DATE_FORMAT_YYYYSMMSDD = "yyyy/MM/dd";
    
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    
    public static final String DATE_FORMAT_YYYYSMMSDDHHQMIQSS = "yyyy/MM/dd HH:mm:ss";
    
    public static final String DATE_FORMAT_YYYYHMMHDD = "yyyy-MM-dd";
    
    public static final String DATE_FORMAT_YYYYHMMHDDHHQMIQSS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_FORMAT_YYYYSMMSDDHHQMIQSS_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    
    public static final String DATE_FORMAT_YYYYHMMHDDHHQMIQSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public static final String DATE_FORMAT_YYYYHMMHDDHHQMIQSS_SSSZ = "yyyy-MM-dd HH:mm:ss.SSSZ";
    
    public static final String TIME_FORMAT_HHQMI = "HH:mm";
    
    public static final String TIME_FORMAT_HHQMIQSS = "HH:mm:ss";
    
    public static final String TIME_FORMAT_HHQMIQSS_SSS = "HH:mm:ss.SSS";
    
    public static final String DATE_FORMAT_YYYYSMMSDD_YOUBI = "yyyy/MM/dd（曜日）";
    
    public static final long DATE_STEP = 1000*60*60*24;
    
    public static final long HOUR_STEP = 1000*60*60;
    
    public static final long MIN_STEP = 1000*60;
    
    public static final long SEC_STEP = 1000;
    
    private final ReentrantLock lock = new ReentrantLock();

    

    
    /**
     * 日付妥当性チェック<P>
     * 指定された日付が妥当であるかをチェックする。チェックする日付形式も指定する。
     * 
     * @param strDate チェック元日付文字列
     * @return 真偽結果値 true | false
     */
    public boolean checkDate(String strDate) {
        if(strDate.length() == DATE_FORMAT_YYYYSMMSDD.length()) {
            if(parseDate(strDate, DATE_FORMAT_YYYYSMMSDD) != null) {
                return true;
            }else if(parseDate(strDate, DATE_FORMAT_YYYYHMMHDD) != null) {
                return true;
            }
        }else if(strDate.length() == DATE_FORMAT_YYYYMMDD.length()) {
            if(parseDate(strDate, DATE_FORMAT_YYYYMMDD) != null) {
                return true;
            }
        }else if(strDate.length() == DATE_FORMAT_YYYYSMMSDDHHQMIQSS.length()) {
            if(parseDate(strDate, DATE_FORMAT_YYYYSMMSDDHHQMIQSS) != null) {
                return true;
            }else if(parseDate(strDate, DATE_FORMAT_YYYYHMMHDDHHQMIQSS) != null) {
                return true;
            }
        }else if(strDate.length() == DATE_FORMAT_YYYYSMMSDDHHQMIQSS_SSS.length()) {
            if(parseDate(strDate, DATE_FORMAT_YYYYSMMSDDHHQMIQSS_SSS) != null) {
                return true;
            }else if(parseDate(strDate, DATE_FORMAT_YYYYHMMHDDHHQMIQSS_SSS) != null) {
                return true;
            }
        }else {
            return false;
        }
        
        return false;
    }
    
    /**
     * 時刻妥当性チェック<P>
     * 指定された時刻が妥当であるかをチェックする。チェックする時刻形式も指定する。
     * 
     * @param strDate チェック元日付文字列
     * @return 真偽結果値 true | false
     */
    public boolean checkTime(String strTime) {
        if(strTime.length() == TIME_FORMAT_HHQMI.length()) {
            if(parseDate(strTime, TIME_FORMAT_HHQMI) != null) {
                return true;
            }
        }else if(strTime.length() == TIME_FORMAT_HHQMIQSS.length()) {
            if(parseDate(strTime, TIME_FORMAT_HHQMIQSS) != null) {
                return true;
            }
        }else if(strTime.length() == TIME_FORMAT_HHQMIQSS_SSS.length()) {
            if(parseDate(strTime, TIME_FORMAT_HHQMIQSS_SSS) != null) {
                return true;
            }
        }else {
            return false;
        }
        
        return false;
    }
    
    /**
     * String --> Date 日付変換<P>
     * 日付文字列を指定された日付形式で変換する。
     * 
     * @param strDate チェック元日付文字列
     * @param format 日付フォーマット
     * @return Date 変換結果　| null　変換失敗
     */
    public Date parseDate(String strDate, String format) {
        try {
            if(strDate.length() != format.length()) {
                return null;
            }
            DateFormat df = new SimpleDateFormat(format);
            df.setLenient(false); // ←これで厳密にチェックしてくれるようになる 2017/01/32のものはエラーがThrowされる
            Date dt = df.parse(strDate);
            if(!strDate.equals(df.format(dt))){
                return null;
            }
            return dt;
        } catch (ParseException p) {
            return null;
        }
    }
    
    public String formatDate(Date date, String format) {
        String str = "";
        if(format.equals(DATE_FORMAT_YYYYSMMSDD_YOUBI)) {
            str = format(date, DATE_FORMAT_YYYYSMMSDD);    
            DayOfWeek day = getDayOfWeek(date);
            str += "("+msg.getContext(day.getName())+")";
        }else {
            str = format(date, format);    
        }
        return str;
    }
    
    public String formatDate(Date date, String format, Locale loc) {
        String str = "";
        if(format.equals(DATE_FORMAT_YYYYSMMSDD_YOUBI)) {
            str = format(date, DATE_FORMAT_YYYYSMMSDD);    
            DayOfWeek day = getDayOfWeek(date);
            str += "("+msg.getContext(loc, day.getName())+")";
        }else {
            str = format(date, format);    
        }
        return str;
    }
    
    /**
     * 日数差算出<P>
     * 基準年月日とそれに対する日数差を求めたい年月日を受け取り、それらの日数差（時分秒含む）を算出して返却する。
     * 
     * @param orgDt 差分元日付
     * @param trgDt 差分先日付
     * @return long[] 差分結果<BR>
     *         long[0] 日数差<BR>
     *         long[1] 時数差<BR>
     *         long[2] 分数差<BR>
     *         long[3] 秒数差<BR>
     */
    public long[] getDateDifference(Date orgDt, Date trgDt) {
        long orgDtTime = orgDt.getTime();
        long trgDtTime = trgDt.getTime();
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
    
    
    /**
     * 
     * @param orgDt
     * @param days
     * @return
     */
    public Date addDays(Date orgDt, long days) {
        return addDate(orgDt, new long[] {days, 0L, 0L, 0L});
    }
    
    /**
     * 
     * @param orgDt
     * @param hours
     * @return
     */
    public Date addHours(Date orgDt, long hours) {
        return addDate(orgDt, new long[] {0, hours, 0L, 0L});
    }
    
    /**
     * 
     * @param orgDt
     * @param minutes
     * @return
     */
    public Date addMinutes(Date orgDt, long minutes) {
        return addDate(orgDt, new long[] {0L, 0L, minutes, 0L});
    }
    
    /**
     * 
     * @param orgDt
     * @param seconds
     * @return
     */
    public Date addSeconds(Date orgDt, long seconds) {
        return addDate(orgDt, new long[] {0L, 0L, 0L, seconds});
    }
    
    /**
     * 日付算出 (日時分秒)<P>
     * 基準年月日と加算したい日数を引数で受け取り、年月日に日数（時分秒含む）を加算した日付を算出して返却する。
     * 
     * @param orgDt 日付元
     * @param diffTime 加算日時分秒（long[4]配列：long[0]=日数差、long[1]=時数差、long[2]=分数差、long[3]=秒数差）
     * @return　日付先
     */
    public Date addDate(Date orgDt, long[] diffTime) {
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
    public LocalDate addDate(LocalDate orgDt, long[] diffTime) {
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
    public String addDate(String orgDtStr, String pattern, long[] diffTime) {
        LocalDate orgDt = DateUtility.parseDate(orgDtStr, pattern);
        long orgDtTime = Date.from(orgDt.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
        
        // 日付先 ＝ 日付元 + 差分日数 + 差分時数 + 差分分数 + 差分秒数
        orgDtTime += diffTime[0]*DATE_STEP + diffTime[1]*HOUR_STEP + diffTime[2]*MIN_STEP + diffTime[3]*SEC_STEP;
        LocalDate trgDt = (new Date(orgDtTime)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DateUtility.formatDate(trgDt, pattern);
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
    public DayOfWeek getDayOfWeek(Date orgDt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orgDt);
        return DayOfWeek.getEnum(calendar.get(Calendar.DAY_OF_WEEK));
    }
    
    /**
     * 日付書式変換<P>
     * 日付を指定されたフォーマットに変換する。
     * 
     * @param orgDt 日付元
     * @param format 日付フォーマット
     * @return 日付文字列
     */
    private String format(Date orgDt, String format) {
        lock.lock();
        DateFormat df = new SimpleDateFormat(format);
        String dtString = df.format(orgDt);
        lock.unlock();
        return dtString;
         
    }
    
    public Date getSystemCurrentDate() {
        return new Date(System.currentTimeMillis());
    }
    
    public enum DayOfWeek {
        
        SUNDAY(Calendar.SUNDAY, "SUNDAY"), 
        MONDAY(Calendar.MONDAY, "MONDAY"),
        TUESDAY(Calendar.TUESDAY, "TUESDAY"),
        WEDNESDAY(Calendar.WEDNESDAY, "WEDNESDAY"),
        THURSDAY(Calendar.THURSDAY, "THURSDAY"),
        FRIDAY(Calendar.FRIDAY, "FRIDAY"),
        SATURDAY(Calendar.SATURDAY, "SATURDAY");
        
        @Getter
        private int id;
        
        @Getter
        private String name;
        
 
        // コンストラクタの実装
        private DayOfWeek (int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public static DayOfWeek getEnum(int id) {
            DayOfWeek[] enumArray = DayOfWeek.values();
             
            // 取得出来たenum型分ループします。
            for(DayOfWeek enumStr : enumArray) {
                // 引数とenum型の文字列部分を比較します。
                if (id == enumStr.getId()){
                    return enumStr;
                }
            }

            return null;
        }
    }
    
    /*
    public static void main(String[] arg) {
        
        DateCommonUtil util = new DateCommonUtil();
        //------------------------
        System.out.println("checkDate(2017/01/32):"+util.checkDate("2017/01/32"));
        System.out.println("checkDate(2017/01/31):"+util.checkDate("2017/01/31"));
        System.out.println("checkDate(2018/02/29):"+util.checkDate("2018/02/29"));
        System.out.println("checkDate(20180229):"+util.checkDate("20180229"));
        System.out.println("checkDate(2017/02/2A):"+util.checkDate("2017/02/2A"));
        System.out.println("checkDate(2017/02/cA):"+util.checkDate("2017/02/cA"));
        System.out.println("checkDate(2017/02/2Ac):"+util.checkDate("2017/02/2Ac"));
        //------------------------
        System.out.println("checkDate(20170132):"+util.checkDate("20170132"));
        System.out.println("checkDate(20170131):"+util.checkDate("20170131"));
        System.out.println("checkDate(20180229):"+util.checkDate("20180229"));
        System.out.println("checkDate(2018/02/28):"+util.checkDate("2018/02/28"));
        System.out.println("checkDate(2017022A):"+util.checkDate("2017022A"));
        System.out.println("checkDate(201702cA):"+util.checkDate("201702cA"));
        System.out.println("checkDate(2017022Ac):"+util.checkDate("2017022Ac"));
        System.out.println("checkDate(2017A22A):"+util.checkDate("2017A222"));
        //------------------------
        System.out.println("checkTime(34:56):"+util.checkTime("34:56"));
        System.out.println("checkTime(12:56):"+util.checkTime("12:56"));
        System.out.println("checkTime(12:70):"+util.checkTime("12:70"));
        Calendar org = Calendar.getInstance();
        org.set(2017, 10, 12, 22, 23, 20);
        Calendar trg = Calendar.getInstance();
        trg.set(2017, 10, 13, 22, 43, 20);
        
        System.out.println("getDateDiff(org, trg):"+util.getDateDifference(org.getTime(), trg.getTime()).toString());
        //------------------------
        org = Calendar.getInstance();
        org.set(2017, 10, 12, 22, 23, 20);
        
        System.out.println("addDate(org, new long[] {1, 2, 3, 4}):"+util.addDate(org.getTime(), new long[] {1, 2, 3, 4}).toString());
        
        //------------------------
        System.out.println("getDayOfWeek(org):"+util.getDayOfWeek(org.getTime()));
        
        //------------------------
        System.out.println("formatDate(org, yyyy/MM/dd):"+util.formatDate(org.getTime(), DATE_FORMAT_YYYYSMMSDD_YOUBI));
        
    }
    */
}
