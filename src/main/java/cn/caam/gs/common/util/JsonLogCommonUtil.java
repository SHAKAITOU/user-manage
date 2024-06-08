package cn.caam.gs.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Value;

import com.savoirtech.logging.slf4j.json.logger.JsonLogger;
import com.savoirtech.logging.slf4j.json.logger.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonLogCommonUtil {
	
	 private static String dateFormatString = "yyyy-MM-dd HH:mm:ss.SSSZ";
	 private static FastDateFormat formatter = FastDateFormat.getInstance(dateFormatString);
	
	public static final String CLASS = "class";
	private static final String LEVEL = "capital";
	public static final String MESSAGE = "message";
	private static final String SERVICE = "service";
	public static final String REQUEST_ID = "requestId";
	public static final String TRACE_ID = "traceId";
	public static final String HTTP_REQUEST = "httpRequest";
	public static final String HTTP_SESSION_ID = "sessionId";
	public static final String HOST_NAME = "hostName";
	public static final String HTTP_RESPONSE = "httpResponse";
	public static final String HTTP_RESPONSE_STATUS = "status";
	public static final String EXCEPTION = "exception";

	
	@Value("${spring.application.name}")
	private String applicationName;
	
	
	private static enum Level {
        TRACE, DEBUG, INFO, NOTIC, WARN, ERROR, CRITICAL
    }
	
	public void trace(String requestId, 
						String traceId,
						String message,
						HttpServletRequest request,
						HttpServletResponse response,
						Exception t) {
		
		writeLog(Level.TRACE, getLogMap(requestId, traceId, message, request, response, t));
	}

	public void debug(String requestId, 
						String traceId,
						String message,
						HttpServletRequest request,
						HttpServletResponse response,
						Exception t) {
		
		writeLog(Level.DEBUG, getLogMap(requestId, traceId, message, request, response, t));
	}
	
	public void info(String message) {

		writeLog(Level.INFO, getLogMap("", "", message, null, null, null));
	}
	
	public void info(String requestId, 
						String traceId,
						String message,
						HttpServletRequest request,
						HttpServletResponse response,
						Exception t) {
		
		writeLog(Level.INFO, getLogMap(requestId, traceId, message, request, response, t));
	}

	public void notic(String requestId, 
						String traceId,
						String message,
						HttpServletRequest request,
						HttpServletResponse response,
						Exception t) {
		
		writeLog(Level.NOTIC, getLogMap(requestId, traceId, message, request, response, t));
	}
	
	public void warn(String requestId, 
						String traceId,
						String message,
						HttpServletRequest request,
						HttpServletResponse response,
						Exception t) {
		
		writeLog(Level.WARN, getLogMap(requestId, traceId, message, request, response, t));
	}
	
	public void error(String requestId, 
						String traceId,
						String message,
						HttpServletRequest request,
						HttpServletResponse response,
						Exception t) {
		
		writeLog(Level.ERROR, getLogMap(requestId, traceId, message, request, response, t));
	}
	
	public void critical(String requestId, 
							String traceId,
							String message,
							HttpServletRequest request,
							HttpServletResponse response,
							Exception t) {
		
		writeLog(Level.CRITICAL, getLogMap(requestId, traceId, message, request, response, t));
	}

	
	public void writeLog(Level level, Map<String, Object> mapInfo) {

		Logger logger = new Logger(log, formatter, false);
		JsonLogger jLog ;
		//level
		if(level.equals(Level.TRACE)) {
			jLog = logger.trace();
			jLog.field(LEVEL, "トレース");
		}else if(level.equals(Level.DEBUG)) {
			jLog = logger.debug();
			jLog.field(LEVEL, "ディバッグ");
		}else if(level.equals(Level.INFO)) {
			jLog = logger.info();
			jLog.field(LEVEL, "一般");
		}else if(level.equals(Level.NOTIC)) {
			jLog = logger.info();
			jLog.field(LEVEL, "注意");
		}else if(level.equals(Level.WARN)) {
			jLog = logger.warn();
			jLog.field(LEVEL, "警告");
		}else if(level.equals(Level.ERROR)) {
			jLog = logger.error();
			jLog.field(LEVEL, "エラー");
		}else {
			jLog = logger.error();
			jLog.field(LEVEL, "致命エラー");
		}
		
		//time
		//jLog.field(TIME, dateUtil.formatDate(dateUtil.getSystemCurrentDate(), 
		//											DateCommonUtil.DATE_FORMAT_YYYYHMMHDDHHQMIQSS_SSSZ));
		//message
		if(mapInfo.containsKey(MESSAGE)) {
			jLog.field(MESSAGE, mapInfo.get(MESSAGE).toString());
		}
		//applicationName
		jLog.field(SERVICE, applicationName);
		//REQUEST_ID
		if(mapInfo.containsKey(REQUEST_ID)) {
			jLog.field(REQUEST_ID, mapInfo.get(REQUEST_ID).toString());
		}
		//TRACE_ID
		if(mapInfo.containsKey(TRACE_ID)) {
			jLog.field(TRACE_ID, mapInfo.get(TRACE_ID).toString());
		}
		//HOST_NAME
		try {
			jLog.field(HOST_NAME, InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e2) {
			jLog.exception(EXCEPTION, e2);
		}
		
		//HTTP_REQUEST
		if(mapInfo.containsKey(HTTP_REQUEST)) {
			HttpServletRequest request = (HttpServletRequest)mapInfo.get(HTTP_REQUEST);
			
			Map<String, String> requestMap = new HashMap<>();
			requestMap.put(HTTP_SESSION_ID, request.getSession().getId());
			
			jLog.map(HTTP_REQUEST, requestMap);
		}
		//HTTP_REQUEST
		if(mapInfo.containsKey(HTTP_RESPONSE)) {
			HttpServletResponse response = (HttpServletResponse)mapInfo.get(HTTP_RESPONSE);
			response.getStatus();
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put(HTTP_RESPONSE_STATUS, String.valueOf(response.getStatus()));
			
			jLog.map(HTTP_RESPONSE, responseMap);
		}
		
		if(mapInfo.containsKey(EXCEPTION)) {
			jLog.exception(EXCEPTION, (Exception)mapInfo.get(EXCEPTION));
		}
		
		jLog.log();
	}
	
	private Map<String, Object> getLogMap(String requestId, 
											String traceId,
											String message,
											HttpServletRequest request,
											HttpServletResponse response,
											Exception t){
		Map<String, Object> logMap = new HashMap<>();
		logMap.put(JsonLogCommonUtil.MESSAGE, message);
		logMap.put(JsonLogCommonUtil.REQUEST_ID, requestId);
		logMap.put(JsonLogCommonUtil.TRACE_ID, traceId);
		if(request != null) {
			logMap.put(JsonLogCommonUtil.HTTP_REQUEST, request);
		}
		
		if(response != null) {
			logMap.put(JsonLogCommonUtil.HTTP_RESPONSE, response);
		}
		
		if(t != null) {
			logMap.put(JsonLogCommonUtil.EXCEPTION, t);
		}
		
		return logMap;
	}
}
