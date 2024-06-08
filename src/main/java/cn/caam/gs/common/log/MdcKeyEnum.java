
package cn.caam.gs.common.log;

import lombok.Getter;

/**
 * MDC key's enum.<br>
 *
 * @author Fast Retailing
 * @version $Revision$
 */
public enum MdcKeyEnum {

    /**
     * Amazon's trace-id.<br>
     */
    AMAZON_TRACE_ID("X-Amzn-Trace-Id"),

    /**
     * Host name.<br>
     */
    HOST_NAME("hostname"),

    /**
     * Request id.<br>
     */
    REQUEST_ID("request_id"),

    /**
     * Attribute service.<br>
     */
    ATTRIBUTE_SERVICE("service");

    /**
     * Key.
     */
    @Getter
    private String key;

    /**
     * Constructor.
     * @param key key
     */
    MdcKeyEnum(String key) {
        this.key = key;
    }

    /**
     * Returns the key.
     * @return key
     */
    @Override
    public String toString() {
        return this.key;
    }

}
