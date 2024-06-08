package cn.caam.gs.common.exception;

public class ShaScreenException extends ShaException {


    private static final long serialVersionUID = 1L;

    public ShaScreenException() {
        super();
    }

    public ShaScreenException(String message) {
        super(message);
    }

    public ShaScreenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShaScreenException(Throwable cause) {
        super(cause);
    }

    protected ShaScreenException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

