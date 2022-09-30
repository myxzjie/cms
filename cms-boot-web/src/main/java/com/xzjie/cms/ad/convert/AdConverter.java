package com.xzjie.cms.ad.convert;

import com.xzjie.cms.ad.model.Ad;
import com.xzjie.cms.ad.vo.AdVo;
import com.xzjie.cms.core.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdConverter extends Converter<AdVo, Ad> {
    AdConverter INSTANCE = Mappers.getMapper(AdConverter.class);
}
