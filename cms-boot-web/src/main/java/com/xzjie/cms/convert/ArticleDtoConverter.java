package com.xzjie.cms.convert;

import com.xzjie.cms.dto.ArticleDto;
import com.xzjie.cms.dto.UserDto;
import com.xzjie.cms.dto.WxUserResult;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.model.WxAccountFans;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vito
 * @since 2022/3/17 9:54 上午
 */
@Mapper(componentModel = Converter.componentModel, uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ArticleDtoConverter implements Converter<ArticleDto, Article> {
    public static ArticleDtoConverter INSTANCE = Mappers.getMapper(ArticleDtoConverter.class);

    @Mappings({
            @Mapping(source = "labels", target = "labels", ignore = true)
    })
    @Override
    public abstract ArticleDto source(Article target);

    @Mappings({
            @Mapping(source = "labels", target = "labels", ignore = true)
    })
    @Override
    public abstract Article target(ArticleDto source);

    @AfterMapping
    protected void setLabels(ArticleDto to, @MappingTarget Article article) {
        List<Label> labels = new ArrayList<>();
        for (Long id : to.getLabels()) {
            Label label = new Label();
            label.setId(id);
            labels.add(label);
        }
        article.setLabels(labels);
     }
}
