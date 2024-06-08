package cn.caam.gs.common.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogCommonUtil {
	
	@Autowired
	private StringCommonUtil stringCommonUtil;
	
	private static enum Level {
        TRACE, DEBUG, INFO, NOTIC, WARN, ERROR, CRITICAL
    }
	
	public void trace(String msg) {
		
		writeLog(Level.TRACE, "", msg, null);
	}
	
	public void trace(String title, String msg) {
		
		writeLog(Level.TRACE, title, msg, null);
	}
	
	public void trace(String title, String msg, Object obj) {
		
		writeLog(Level.TRACE, title, msg, obj);
	}
	
	public void trace(String title, String msg, Throwable t) {
		
		writeLog(Level.TRACE, title, msg, t);
	}
	
	public void debug(String msg) {
		
		writeLog(Level.DEBUG, "", msg, null);
	}
	
	public void debug(String title, String msg) {
		
		writeLog(Level.DEBUG, title, msg, null);
	}
	
	public void debug(String title, String msg, Object obj) {
		
		writeLog(Level.DEBUG, title, msg, obj);
	}
	
	public void debug(String title, String msg, Throwable t) {
		
		writeLog(Level.DEBUG, title, msg, t);
	}

	public void info(String msg) {
		
		writeLog(Level.INFO, "", msg, null);
	}
	
	public void info(String title, String msg) {
		
		writeLog(Level.INFO, title, msg, null);
	}
	
	public void info(String title, String msg, Object obj) {
		
		writeLog(Level.INFO, title, msg, obj);
	}
	
	public void info(String title, String msg, Throwable t) {
		
		writeLog(Level.INFO, title, msg, t);
	}

	public void notic(String msg) {
		
		writeLog(Level.NOTIC, "", msg, null);
	}
	public void notic(String title, String msg) {
		
		writeLog(Level.NOTIC, title, msg, null);
	}
	
	public void notic(String title, String msg, Object obj) {
		
		writeLog(Level.NOTIC, title, msg, obj);
	}
	
	public void notic(String title, String msg, Throwable t) {
		
		writeLog(Level.NOTIC, title, msg, t);
	}
	
	public void warn(String msg) {
		
		writeLog(Level.WARN, "", msg, null);
	}
	
	public void warn(String title, String msg) {
		
		writeLog(Level.WARN, title, msg, null);
	}
	
	public void warn(String title, String msg, Object obj) {
		
		writeLog(Level.WARN, title, msg, obj);
	}
	
	public void warn(String title, String msg, Throwable t) {
		
		writeLog(Level.WARN, title, msg, t);
	}
	
	public void error(String msg) {
		
		writeLog(Level.ERROR, "", msg, null);
	}
	
	public void error(String title, String msg) {
		
		writeLog(Level.ERROR, title, msg, null);
	}
	
	public void error(String title, String msg, Object obj) {
		
		writeLog(Level.ERROR, title, msg, obj);
	}
	
	public void error(String title, String msg, Throwable t) {
		
		writeLog(Level.ERROR, title, msg, t);
	}
	
	public void critical(String msg) {
		
		writeLog(Level.CRITICAL, "", msg, null);
	}
	
	public void critical(String title, String msg) {
		
		writeLog(Level.CRITICAL, title, msg, null);
	}
	
	public void critical(String title, String msg, Object obj) {
		
		writeLog(Level.CRITICAL, title, msg, obj);
	}
	
	public void critical(String title, String msg, Throwable t) {
		
		writeLog(Level.CRITICAL, title, msg, t);
	}

	
	/**
	 * 任意のタイミングで業務ログを出力する(出力元メソッド名も出力する)
	 * 
	 * @param level 出力レベル
	 * @param title 出力タイトル
	 * @param msg 出力内容
	 * @param obj 出力オブジェクト
	 */
	private void writeLog(Level level, String title, String msg, Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append(msg);
		if(obj != null) {
			
			try {
				sb.append(stringCommonUtil.toString(obj));
			} catch (IOException e) {
				log.error("【エラー】【"+title+"】", e);
			}
		}

		if(StringUtils.isNotEmpty(title)) {
			sb.append("【"+title+"】");
		}
		if(level.equals(Level.TRACE)) {
			log.trace("【トレース】"+sb.toString());
		}else if(level.equals(Level.DEBUG)) {
			log.debug("【ディバッグ】"+sb.toString());
		}else if(level.equals(Level.INFO)) {
			log.info("【一般】"+sb.toString());
		}
		else if(level.equals(Level.NOTIC)) {
			log.info("【注意】"+sb.toString());
		}else if(level.equals(Level.WARN)) {
			log.warn("【警告】"+sb.toString());
		}else if(level.equals(Level.ERROR)) {
			log.error("【エラー】"+sb.toString());
		}else{
			log.error("【致命エラー】"+sb.toString());
		}
	}
	
	/**
	 * 任意のタイミングで業務ログを出力する(出力元メソッド名も出力する)
	 * 
	 * @param level 出力レベル
	 * @param title 出力タイトル
	 * @param msg 出力内容
	 * @param obj 出力オブジェクト
	 */
	private void writeLog(Level level, String title, String msg, Throwable t) {
		StringBuffer sb = new StringBuffer();
		sb.append(msg);

		if(StringUtils.isNotEmpty(title)) {
			sb.append("【"+title+"】");
		}
		if(level.equals(Level.TRACE)) {
			log.trace("【トレース】"+sb.toString(), t);
		}else if(level.equals(Level.DEBUG)) {
			log.debug("【ディバッグ】"+sb.toString(), t);
		}else if(level.equals(Level.INFO)) {
			log.info("【一般】"+sb.toString(), t);
		}
		else if(level.equals(Level.NOTIC)) {
			log.info("【注意】"+sb.toString(), t);
		}else if(level.equals(Level.WARN)) {
			log.warn("【警告】"+sb.toString(), t);
		}else if(level.equals(Level.ERROR)) {
			log.error("【エラー】"+sb.toString(), t);
		}else{
			log.error("【致命エラー】"+sb.toString(), t);
		}
	}
	
	
//	
//	/**
//	 * パフォーマンスログ
//	 * @param proceedingJoinPoint
//	 */
//	public static void writeServicRequestLog(@SuppressWarnings("rawtypes") Class clazz, InputBaseData inputBaseData) {
//		
//		writeLog(INFO_LEVEL, "パラメータデータ", "["+clazz.getName()+"]", inputBaseData);
//	}
//	
//	
//	public static void writeResponseLog(@SuppressWarnings("rawtypes") Class clazz, OutputBaseData outputBaseData) {
//		writeLog(INFO_LEVEL, "返却データ", "["+clazz.getName()+"]", outputBaseData);
//	}
//	
	
	//-----------------------------------------json-------------------------------------
//	public static void invokeLog(@SuppressWarnings("rawtypes") Class clazz, String scope, JoinPoint proceedingJoinPoint) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("[");
//		sb.append(proceedingJoinPoint.getTarget().getClass() + "#"
//					+ proceedingJoinPoint.getSignature().getName());
//		sb.append("]");
//		writeLog(clazz, INFO_LEVEL, scope, sb.toString());
//	}
//	
//	public static void invokeParamterLog(@SuppressWarnings("rawtypes") Class clazz, JoinPoint proceedingJoinPoint) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("[");
//		for(int i=0; i<proceedingJoinPoint.getArgs().length; i++) {
//			if(i>0) {
//				sb.append(",");
//			}
//			sb.append("arg"+i+"{");
//			sb.append(proceedingJoinPoint.getArgs()[i].toString());
//			sb.append("}");
//		}
//		sb.append("]");
//		writeLog(clazz, INFO_LEVEL, "パラメータ", sb.toString());
//	}
//	
//	
//	public static void writeLog(@SuppressWarnings("rawtypes") Class clazz, String level, String title, String msg) {
//		writeLog(clazz, level, title, msg, null);
//	}
//	
//	
//	public static void writeLog(@SuppressWarnings("rawtypes") Class clazz, String level, String title, String msg, String processMethod) {
//		Logger logger = LoggerFactory.getLogger(clazz);
//		
//		StringBuffer sb = new StringBuffer();
//		if(!StringCommonUtil.isEmpty(processMethod)) {
//			sb.append("[");
//			sb.append(processMethod);
//			sb.append("]");
//		}
//		sb.append(msg);
//		if(level.equals(ERROR_LEVEL)) {
//			logger.error().field("title", title).message(sb.toString()).log();
//		}else if(level.equals(DEBUG_LEVEL)) {
//			logger.debug().field("title", title).message(sb.toString()).log();
//		}else if(level.equals(WARN_LEVEL)) {
//			logger.warn().field("title", title).message(sb.toString()).log();
//		}else {
//			logger.info().field("title", title).message(sb.toString()).log();
//		}
//	}
}

