package com.xzjie.common.core.exception;


public class BizException extends RuntimeException {

    protected String bizCode;
    protected String massage;

    public BizException() {
        super();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String massage) {
        super(massage);
    }

//    public TradeBizException(String bizCode, String massage) {
//        super(bizCode, massage);
//    }

    public BizException(String bizCode, String format, Object... args) {
        super(String.format(format, args));
        this.bizCode = bizCode;
        this.massage = String.format(format, args);
    }


}
