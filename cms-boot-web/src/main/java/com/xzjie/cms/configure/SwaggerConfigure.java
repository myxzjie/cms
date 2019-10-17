package com.xzjie.cms.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfigure {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xzjie.cms"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(new ApiInfoBuilder()
//                        .termsOfServiceUrl("http://localhost:8090")
                        .title("CMS API 接口文档")
                        .description("CMS API 接口文档")
                        .version("2.0")
                        .contact(new Contact("xzjie", "https://www.dev56.com", "513961835@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build()).globalOperationParameters(getParameters());
    }

    private List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("Bearer ")
                .required(true)
                .build();

        parameters.add(parameter);
        return parameters;
    }
}
