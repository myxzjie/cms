package com.xzjie.cms.wechat.dto;

import com.xzjie.cms.dto.BasePageDto;
import com.xzjie.cms.wechat.model.WxAccountFans;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class WxAccountFansDto extends BasePageDto {

    private Long tagId;
    private String nickName;

    public WxAccountFans toAccountFans() {
        WxAccountFans fans = new WxAccountFans();
        BeanUtils.copyProperties(this, fans);
        return fans;
    }

}
