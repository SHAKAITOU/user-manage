package cn.caam.gs.common.exception;

public class ShaScreenBadRequestException extends ShaException {


    private static final long serialVersionUID = 1L;
    
    public ShaScreenBadRequestException(String message) {
        super(message);
    }

    public ShaScreenBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShaScreenBadRequestException(Throwable cause) {
        super(cause);
    }

    protected ShaScreenBadRequestException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

