package com.xzjie.cms.configure;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfigure {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.xzjie.cms"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
//                        .termsOfServiceUrl("http://localhost:8090")
                        .title("CMS API 接口文档")
                        .description("CMS API 接口文档")
                        .version("2.0")
                        .contact(new Contact("xzjie", "https://www.dev56.com", "513961835@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build())
                .securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
//                        显示用
                        .name("Authorization")
                        .description("token")
//                        .bearerFormat("JWT")
//                        .scheme("bearer")
                        .build()))
                .securityContexts(Collections.singletonList(SecurityContext.builder()
                        .securityReferences(Collections.singletonList(SecurityReference.builder()
                                .scopes(new AuthorizationScope[0])
                                .reference("Authorization")
                                .build()))
                        // 声明作用域
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build()))
                .globalRequestParameters(getParameters());
    }

    private List<RequestParameter> getParameters() {
        List<RequestParameter> parameters = new ArrayList<>();

//        parameters.add(new RequestParameterBuilder()
//                .name("Authorization")
//                .description("token")
//                .in(ParameterType.HEADER)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)).defaultValue("Bearer"))
//                .required(true)
//                .build());
//        parameters.add(new RequestParameterBuilder()
//                .name("version")
//                .description("客户端的版本号")
//                .required(true)
//                .in(ParameterType.QUERY)
//                .query(q -> q.defaultValue("1.0.0").model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(false)
//                .build());
        return parameters;
    }
}
