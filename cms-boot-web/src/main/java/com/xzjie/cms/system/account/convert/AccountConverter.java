package com.xzjie.cms.system.account.convert;

import com.xzjie.cms.core.convert.Converter;
import com.xzjie.cms.system.account.dto.AccountDto;
import com.xzjie.cms.system.account.model.Account;
import com.xzjie.cms.vo.AccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Vito
 * @since 2022/3/6 11:46 下午
 */
@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountConverter extends Converter<AccountDto, Account> {
    AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

    AccountVo convert(Account source);
}
