package com.xzjie.cms.convert;

import com.xzjie.cms.model.Account;
import com.xzjie.cms.vo.UserInfoVo;
import com.xzjie.cms.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Vito
 * @since 2022/3/7 12:26 上午
 */
@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserVoConverter extends Converter<UserVo, Account> {
    UserVoConverter INSTANCE = Mappers.getMapper(UserVoConverter.class);
}
