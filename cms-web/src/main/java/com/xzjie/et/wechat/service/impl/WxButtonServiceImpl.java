package com.xzjie.et.wechat.service.impl;

import com.xzjie.et.wechat.dao.WxButtonMapper;
import com.xzjie.et.wechat.entity.*;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.model.WxButton;
import com.xzjie.et.wechat.service.WxAccountService;
import com.xzjie.et.wechat.service.WxButtonService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wxButtonService")
public class WxButtonServiceImpl extends AbstractBaseService<WxButton,Long> implements WxButtonService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WxButtonMapper wxButtonMapper;

    @Autowired
    private WechatHelper wechatHelper;

    @Autowired
    private WxAccountService wxAccountService;

    @Override
    protected BaseMapper<WxButton, Long> getMapper() {
        return wxButtonMapper;
    }

    @Override
    public List<WxButton> getTree(Long buttonId, Long userId) {
        return wxButtonMapper.selectTree(null);
    }

    @Override
    public void syncButton(Long siteId) throws Exception {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(siteId);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);
        List<WxButton> list = this.getList(new WxButton(siteId));



        if(logger.isDebugEnabled()){
            logger.debug(".....一级菜单的个数是....." + list.size());
        }

        Menu menu = new Menu();
        Button firstArr[] = new Button[list.size()];
        for (int a = 0; a < list.size(); a++) {
            WxButton entity = list.get(a);

            WxButton childRecord=new WxButton();

            childRecord.setpId(entity.getId());
            childRecord.setSiteId(siteId);

            List<WxButton> childList = this.getList(childRecord);
            if(logger.isDebugEnabled()){
                logger.debug(".....二级菜单的个数是....." + childList.size());
            }

            if (childList.size() == 0) {
                if("view".equals(entity.getType())){
                    ViewButton viewButton = new ViewButton();
                    viewButton.setName(entity.getName());
                    viewButton.setType(entity.getType());
                    viewButton.setUrl(entity.getUrl());
                    firstArr[a] = viewButton;
                }else if("click".equals(entity.getType())){
                    CommonButton cb = new CommonButton();
                    cb.setKey(entity.getButtonKey());
                    cb.setName(entity.getName());
                    cb.setType(entity.getType());
                    firstArr[a] = cb;
                }

            } else {
                ComplexButton complexButton = new ComplexButton();
                complexButton.setName(entity.getName());

                Button[] secondARR = new Button[childList.size()];
                for (int i = 0; i < childList.size(); i++) {
                    WxButton children = childList.get(i);
                    String type = children.getType();
                    if ("view".equals(type)) {
                        ViewButton viewButton = new ViewButton();
                        viewButton.setName(children.getName());
                        viewButton.setType(children.getType());
                        viewButton.setUrl(children.getUrl());
                        secondARR[i] = viewButton;

                    } else if ("click".equals(type)) {

                        CommonButton cb1 = new CommonButton();
                        cb1.setName(children.getName());
                        cb1.setType(children.getType());
                        cb1.setKey(children.getButtonKey());
                        secondARR[i] = cb1;

                    }

                }
                complexButton.setSub_button(secondARR);
                firstArr[a] = complexButton;
            }
        }
        menu.setButton(firstArr);

        JSONObject jsonMenu = JSONObject.fromObject(menu);

        wechatHelper.createButton(accessToken.getAccess_token(),jsonMenu.toString());

    }
}
