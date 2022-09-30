package com.xzjie.cms.wechat.convert;

import com.xzjie.cms.core.convert.Converter;
import com.xzjie.cms.dto.WxUserResult;
import com.xzjie.cms.wechat.model.WxAccountFans;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.*;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WxAccountFansConverter implements Converter<WxUserResult, WxAccountFans> {

    @Mappings({
            @Mapping(target = "tagIds", source = "tagIds", ignore = true),
    })
    @Override
    public abstract WxAccountFans target(WxUserResult source);

//    @Mappings({
//            @Mapping(source = "id", target = "userId"),
//            @Mapping(source = "username", target = "name"),
//            @Mapping(source = "role.roleName", target = "roleName")
//    })

    @Mappings({
            @Mapping(source = "tagIds", target = "tagIds", ignore = true)
    })
    @Override
    public abstract WxUserResult source(WxAccountFans target);

    @AfterMapping
    protected void setTagIds(WxUserResult to, @MappingTarget WxAccountFans fans) {
        String tagIds = StringUtils.join(to.getTagIds(), ",");
        fans.setTagIds(tagIds);
    }


}
