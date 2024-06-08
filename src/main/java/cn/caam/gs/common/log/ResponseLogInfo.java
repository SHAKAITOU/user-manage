
package cn.caam.gs.common.log;

import java.io.Serializable;

import lombok.Data;


/**
 * ResponseLogInfo.
 * log information of response
 *
 * @author Fast Retailing
 * @version $Revision$
 */
@Data
public class ResponseLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * status code.
     */
    private int statusCode;

    /**
     * response time.
     */
    private long responseTime;

    /**
     * response.
     */
    private Object response;

    /**
     * time.
     */
    private String time;
}
