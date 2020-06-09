package com.xzjie.cms.dto;

import com.xzjie.cms.model.WxAccountFans;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class WxAccountFansRequest extends BasePageRequest {

    private Long tagId;
    private String nickName;

    public WxAccountFans toAccountFans() {
        WxAccountFans fans = new WxAccountFans();
        BeanUtils.copyProperties(this, fans);
        return fans;
    }

}
