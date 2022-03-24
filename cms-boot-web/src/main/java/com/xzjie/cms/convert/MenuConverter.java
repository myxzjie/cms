package com.xzjie.cms.convert;

import com.xzjie.cms.vo.MenuVo;
import com.xzjie.cms.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConverter extends Converter<MenuVo, Menu> {
}
