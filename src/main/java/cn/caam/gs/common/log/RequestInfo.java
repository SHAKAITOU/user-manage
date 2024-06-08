
package cn.caam.gs.common.log;

import java.io.Serializable;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import lombok.Data;

/**
 * RequestInfo.
 * the information of request
 *
 * @author Fast Retailing
 * @version $Revision$
 */

@Data
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * method.
     */
    private String method;

    /**
     * request header.
     */
    private HttpHeaders header;

    /**
     * token.
     */
    private String token;

    /**
     * query.
     */
    private String query;

    /**
     * trace id.
     */
    private String traceId;
    
    private Map<String, String> parameterMap;
}
