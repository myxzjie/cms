//package com.xzjie.cms.convert;
//
//import com.xzjie.cms.dto.ArticleDto;
//import com.xzjie.cms.dto.LabelQueryDto;
//import com.xzjie.cms.article.model.Article;
//import com.xzjie.cms.label.model.Label;
//import org.mapstruct.Mapper;
//import org.mapstruct.ReportingPolicy;
//import org.mapstruct.factory.Mappers;
//
///**
// * @author Vito
// * @since 2022/3/17 1:40 下午
// */
//@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface LabelQueryDtoConverter extends Converter<LabelQueryDto, Label> {
//    LabelQueryDtoConverter INSTANCE = Mappers.getMapper(LabelQueryDtoConverter.class);
//}
