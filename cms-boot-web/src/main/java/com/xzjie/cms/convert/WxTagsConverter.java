package com.xzjie.cms.convert;

import com.xzjie.cms.dto.WxTagsResult;
import com.xzjie.cms.model.WxTags;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WxTagsConverter extends Converter<WxTagsResult, WxTags> {
}
