package com.xzjie.cms.article.convert;

import com.xzjie.cms.article.vo.CaseVo;
import com.xzjie.cms.core.convert.Converter;
import com.xzjie.cms.article.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryVoConverter extends Converter<CaseVo, Category> {
    //静态方法
    CategoryVoConverter INSTANCE = Mappers.getMapper(CategoryVoConverter.class);
}
