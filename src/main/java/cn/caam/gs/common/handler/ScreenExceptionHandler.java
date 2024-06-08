package cn.caam.gs.common.handler;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import cn.caam.gs.common.exception.ShaScreenAccessDeniedException;
import cn.caam.gs.common.exception.ShaScreenAuthorityDeniedException;
import cn.caam.gs.common.exception.ShaScreenBadRequestException;
import cn.caam.gs.common.exception.ShaScreenException;

/**
 * The class is used to process the exception that occurred from screen application.
 *
 */
@Slf4j
@ControllerAdvice
public class ScreenExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Http header for judging ajax request or not.
     */
    private static final String HTTP_HEADER_X_REQUESTED_WITH = "X-Requested-With";

    /**
     * Value of http header for judging ajax request or not.
     */
    private static final String HTTP_HEADER_VALUE_XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * Process the screen exception.
     *
     * @param screenException Occur exception.
     * @return The target page and data model.
     */
    @ExceptionHandler(value = ShaScreenAccessDeniedException.class)
    public Object screenExceptionHandler(ShaScreenAccessDeniedException screenException,
            HttpServletRequest request) {

        if (isAjaxRequest(request)) {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        	return new ResponseEntity<>(screenException, headers, HttpStatus.FORBIDDEN);
        }

        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("403");
        modelView.addObject("errorMsg", screenException.getMessage());
        return modelView;
    }
    
    /**
     * Process the screen exception.
     *
     * @param screenException Occur exception.
     * @return The target page and data model.
     */
    @ExceptionHandler(value = ShaScreenAuthorityDeniedException.class)
    public Object screenExceptionHandler(ShaScreenAuthorityDeniedException screenException,
            HttpServletRequest request) {

        if (isAjaxRequest(request)) {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        	return new ResponseEntity<>(screenException, headers, HttpStatus.FORBIDDEN);
        }

        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("403");
        modelView.addObject("errorMsg", screenException.getMessage());
        return modelView;
    }
    
    /**
     * Invoked to send a server error. Sets the status to 500 and also sets the request attribute
     * "javax.servlet.error.exception" to the exception. The default implementation sends a HTTP 500
     * error, and returns an empty.
     *
     * @param exception The exception to be handled.
     * @return ResultObject handled exception info.
     * @throws IOException I/O exception.
     */
    @ExceptionHandler({ShaScreenBadRequestException.class})
    public Object screenExceptionHandler(ShaScreenBadRequestException exception, HttpServletRequest request) {

        log.error("Invoking @handleDefaultException method: {}",
                extractExceptionStackTrace(exception));

        if (isAjaxRequest(request)) {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        	return new ResponseEntity<>(exception, headers, HttpStatus.BAD_REQUEST);
        }
        
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("400");
        modelView.addObject("errorMsg", exception.getMessage());
        return modelView;
    }
    
    /**
     * Invoked to send a server error. Sets the status to 500 and also sets the request attribute
     * "javax.servlet.error.exception" to the exception. The default implementation sends a HTTP 500
     * error, and returns an empty.
     *
     * @param exception The exception to be handled.
     * @return ResultObject handled exception info.
     * @throws IOException I/O exception.
     */
    @ExceptionHandler({ShaScreenException.class})
    public Object screenExceptionHandler(ShaScreenException exception, HttpServletRequest request) {

        log.error("Invoking @handleDefaultException method: {}",
                extractExceptionStackTrace(exception));

        if (isAjaxRequest(request)) {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        	return new ResponseEntity<>(exception, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("500");
        modelView.addObject("errorMsg", exception.getMessage());
        return modelView;
    }

    /**
     * Invoked to send a server error. Sets the status to 500 and also sets the request attribute
     * "javax.servlet.error.exception" to the exception. The default implementation sends a HTTP 500
     * error, and returns an empty.
     *
     * @param exception The exception to be handled.
     * @return ResultObject handled exception info.
     * @throws IOException I/O exception.
     */
    @ExceptionHandler({Exception.class})
    public Object handleDefaultException(Exception exception, HttpServletRequest request) {

        log.error("Invoking @handleDefaultException method: {}",
                extractExceptionStackTrace(exception));

        if (isAjaxRequest(request)) {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        	return new ResponseEntity<>(exception, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("500");
        modelView.addObject("errorMsg", exception.getMessage());
        return modelView;
    }

    /**
     * Extracts exception stack trace info from exception.
     * 
     * @param throwable Throwable
     * @return exception Info with stack trace
     */
    private String extractExceptionStackTrace(Throwable throwable) {

        if (throwable == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Caused by:" + throwable.toString());
        if (throwable.getStackTrace() != null) {
            Arrays.stream(throwable.getStackTrace()).forEach(
                    stackTraceElement -> stringBuilder.append(" at ").append(stackTraceElement));
        }
        stringBuilder.append(throwable.getCause());

        return stringBuilder.toString();
    }

    /**
     * If the request is an aJax request, return true.
     * 
     * @param request Http reqeust.
     * @return True if is an aJax request.
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        return HTTP_HEADER_VALUE_XML_HTTP_REQUEST
                .equals(request.getHeader(HTTP_HEADER_X_REQUESTED_WITH));
    }
}
