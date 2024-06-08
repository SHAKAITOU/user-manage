package cn.caam.gs.common.exception;

public class ShaApiException extends ShaException {


    private static final long serialVersionUID = 1L;

    public ShaApiException() {
        super();
    }

    public ShaApiException(String message) {
        super(message);
    }

    public ShaApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShaApiException(Throwable cause) {
        super(cause);
    }

     protected ShaApiException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
         super(message, cause, enableSuppression, writableStackTrace);
     }

}
