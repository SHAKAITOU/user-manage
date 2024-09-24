package cn.caam.gs;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.util.SessionConstants;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * Http header for judging ajax request or not.
     */
    private static final String HTTP_HEADER_X_REQUESTED_WITH = "X-Requested-With";

    /**
     * Value of http header for judging ajax request or not.
     */
    private static final String HTTP_HEADER_VALUE_XML_HTTP_REQUEST = "XMLHttpRequest";
    
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("getRequestURL="+request.getRequestURL());
    	if(!Objects.nonNull(request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue()))) {
    		String redirectUrl = UrlConstants.INDEX + UrlConstants.LOGOUT;
    		if (request.getRequestURI().startsWith(UrlConstants.ADMIN)) {
    			redirectUrl = UrlConstants.ADMIN + UrlConstants.LOGOUT;
    		}
    		response.addHeader("Location", redirectUrl);
    		
    		if (isAjaxRequest(request)) {
    			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    			response.setContentType("application/json;charset=UTF-8");
    		    response.getWriter().write("no login");
    		}else {
    			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    		}
    		
			return false;
		}
        
        return true;
    }
    
    /**
     * If the request is an aJax request, return true.
     * 
     * @param request HttpServletRequest
     * @return True if is an aJax request.
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        return HTTP_HEADER_VALUE_XML_HTTP_REQUEST
                .equals(request.getHeader(HTTP_HEADER_X_REQUESTED_WITH));
    }
    
    private String getRequestURL(HttpServletRequest request) {
        StringBuilder uri = new StringBuilder(request.getRequestURI());
        if (request.getQueryString() != null) {
            uri.append("?");
            uri.append(request.getQueryString());
        }
        return uri.toString();
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

     }
}

