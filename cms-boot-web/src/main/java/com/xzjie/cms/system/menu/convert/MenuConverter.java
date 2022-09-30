package com.xzjie.cms.system.menu.convert;

import com.xzjie.cms.core.convert.Converter;
import com.xzjie.cms.system.menu.model.Menu;
import com.xzjie.cms.system.menu.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConverter extends Converter<MenuVo, Menu> {
    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

}
