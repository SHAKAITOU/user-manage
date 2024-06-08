package cn.caam.gs.common.exception;

public class ShaScreenAccessDeniedException extends ShaException {


    private static final long serialVersionUID = 1L;
    
    private static final String MSG = "not login, access denied!";

    public ShaScreenAccessDeniedException() {
        super(MSG);
    }

    public ShaScreenAccessDeniedException(Throwable cause) {
        super(MSG, cause);
    }


    protected ShaScreenAccessDeniedException(Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(MSG, cause, enableSuppression, writableStackTrace);
    }

}

