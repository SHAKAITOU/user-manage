package cn.caam.gs.common.exception;

public class ShaScreenAuthorityDeniedException extends ShaException {


    private static final long serialVersionUID = 1L;
    
    private static final String MSG = "no authority, handle refused!";

    public ShaScreenAuthorityDeniedException() {
        super(MSG);
    }

    public ShaScreenAuthorityDeniedException(Throwable cause) {
        super(MSG, cause);
    }


    protected ShaScreenAuthorityDeniedException(Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(MSG, cause, enableSuppression, writableStackTrace);
    }

}

