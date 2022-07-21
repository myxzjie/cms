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
                        .name("JWT")
                        .build()))
                .securityContexts(Collections.singletonList(SecurityContext.builder()
                        .securityReferences(Collections.singletonList(SecurityReference.builder()
                                .scopes(new AuthorizationScope[0])
                                .reference("JWT")
                                .build()))
                        // 声明作用域
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build()));


//                .globalRequestParameters(getParameters());
    }

//    private List<RequestParameter> getParameters() {
//        List<RequestParameter> parameters = new ArrayList<>();
//        RequestParameter parameter = new RequestParameterBuilder()
//                .name("Authorization")
//                .description("token")
//                .in(ParameterType.HEADER)
//                .defaultValue("Bearer ")
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(true)
//                .build();
//
//        parameters.add(parameter);
//        return parameters;
//    }
}
