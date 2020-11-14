package com.xzjie.cms.convert;

import com.xzjie.cms.client.dto.CaseResponse;
import com.xzjie.cms.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryConverter extends Converter<CaseResponse, Category> {
}
