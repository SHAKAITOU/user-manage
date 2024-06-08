/**
 * @(#)OmsJsonLayout.java Copyright (c) 2017 Fast Retailing Corporation.
 */

package cn.caam.gs.common.log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OmsJsonLayout.<br>
 *     All layout of log with jackson format.<br>
 * @author Fast Retailing
 * @version $Revision$
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OmsJsonLayout extends JsonLayout {

    /**
     * the attribute name of hostname in log.
     */
    private static final String ATTRIBUTE_HOSTNAME = "hostName";

    /**
     * the attribute name of level in log.
     */
    public static final String ATTRIBUTE_LEVEL = "level";

    /**
     * the attribute name of time in log.
     */
    private static final String ATTRIBUTE_TIME = "time";

    /**
     * the attribute name of trace id in log.
     */
    private static final String ATTRIBUTE_TRACE_ID = "traceId";

    /**
     * the attribute name of request id in log.
     */
    private static final String ATTRIBUTE_REQUEST_ID = "requestId";

    /**
     * the attribute name of service in log.
     */
    private static final String ATTRIBUTE_SERVICE = "service";

    /**
     * the attribute name of message in log.
     */
    private static final String ATTRIBUTE_MESSAGE = "message";

    /**
     * the attribute name of raw message in log.
     */
    private static final String ATTRIBUTE_RAW_MESSAGE = "rawMessage";

    /**
     * the attribute name of thread in log.
     */
    private static final String ATTRIBUTE_THREAD = "thread";

    /**
     * the attribute name of logger in log.
     */
    private static final String ATTRIBUTE_LOGGER = "logger";

    /**
     * the attribute name of raw message in log.
     */
    private int loggerNameLength = 40;

    /**
     * Constructor.
     */
    public OmsJsonLayout() {
        super();
        this.appendLineSeparator = true;
        this.includeContextName = false;
        this.includeMDC = false;
    }

    /**
     * generate the log output map.
     * @param event event
     * @return map
     */
    @SuppressWarnings("unchecked")
	@Override
    protected Map<String, Object> toJsonMap(ILoggingEvent event) {
        Map<String, Object> map = new LinkedHashMap<>();

        // hostname
        try {
            map.put(ATTRIBUTE_HOSTNAME, InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            map.put(ATTRIBUTE_HOSTNAME, "UnknownHost");
        }

        // level
        Level level = event.getLevel();
        if (level != null) {
            String lvlString = String.valueOf(level);
            map.put(ATTRIBUTE_LEVEL, lvlString);
        }
        // if marker is exists,  level overwrite
        if (event.getMarker() != null) {
            map.put(ATTRIBUTE_LEVEL, event.getMarker().getName());
        }

        // time
        map.put(ATTRIBUTE_TIME, LocalDateTime.now().atOffset(ZoneOffset.UTC).toString());

        // trace id & request id & service
        Map<String, String> mdc = event.getMDCPropertyMap();
        if (mdc != null && !mdc.isEmpty()) {
            map.put(ATTRIBUTE_TRACE_ID, mdc.get(MdcKeyEnum.AMAZON_TRACE_ID.getKey()));
            map.put(ATTRIBUTE_REQUEST_ID, mdc.get(MdcKeyEnum.REQUEST_ID.getKey()));
            map.put(ATTRIBUTE_SERVICE, mdc.get(MdcKeyEnum.ATTRIBUTE_SERVICE.getKey()));
        }

        // thread
        if (this.includeThreadName) {
            String threadName = event.getThreadName();
            if (threadName != null) {
                map.put(ATTRIBUTE_THREAD, threadName);
            }
        }

        // logger
        if (this.includeLoggerName) {
            String loggerName = event.getLoggerName();
            if (loggerName != null) {
                TargetLengthBasedClassNameAbbreviator targetLengthBasedClassNameAbbreviator =
                        new TargetLengthBasedClassNameAbbreviator(this.loggerNameLength);
                map.put(ATTRIBUTE_LOGGER,
                        targetLengthBasedClassNameAbbreviator.abbreviate(loggerName));
            }
        }

        // message
        if (this.includeFormattedMessage) {
            String msg = event.getFormattedMessage();
            if (msg != null && !StringUtils.isEmpty(msg)) {
                map.put(ATTRIBUTE_MESSAGE, msg);
            }
        }
        if (this.includeMessage) {
            String msg = event.getMessage();
            if (msg != null && !StringUtils.isEmpty(msg)) {
                map.put(ATTRIBUTE_RAW_MESSAGE, msg);
            }
        }

        // argument info
        Object[] argumentArray = event.getArgumentArray();
        if (argumentArray != null && argumentArray.length > 0) {
            Arrays.stream(argumentArray).forEach(argument -> {
                if (argument instanceof LinkedHashMap) {
                    ((Map<String, Object>) argument).forEach((key, value) ->
                            map.put(key.toString(), value)
                    );
                }
            });
        }

        return map;
    }
}
