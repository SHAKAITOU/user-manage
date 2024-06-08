package cn.caam.gs.common.exception;

public class ShaException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    public ShaException() {
        super();
    }

    public ShaException(String message) {
        super(message);
    }

    public ShaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShaException(Throwable cause) {
        super(cause);
    }

     protected ShaException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
         super(message, cause, enableSuppression, writableStackTrace);
     }

}

