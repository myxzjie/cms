package com.xzjie.cms.client.convert;

import com.xzjie.cms.client.vo.CaseVo;
import com.xzjie.cms.convert.Converter;
import com.xzjie.cms.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryVoConverter extends Converter<CaseVo, Category> {
    //静态方法
    CategoryVoConverter INSTANCE = Mappers.getMapper(CategoryVoConverter.class);
}
