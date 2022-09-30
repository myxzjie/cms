package com.xzjie.cms.exception;

import com.xzjie.cms.wechat.dto.WxMpError;
import lombok.Getter;

@Getter
public class WxMpException extends RuntimeException {

    private WxMpError error;

    public WxMpException(WxMpError error) {
        super(error.toString());
        this.error = error;
    }

    public WxMpException(WxMpError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

}
