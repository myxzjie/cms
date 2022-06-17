package com.xzjie.cms.security;

import com.xzjie.cms.configure.LocationProperties;
import com.xzjie.cms.store.local.configure.LocalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private LocalProperties localProperties;
    @Autowired
    private LocationProperties properties;
////    @Autowired
////    private CorsInterceptor corsInterceptor;
//
////    @Override
////    protected void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
////        super.addInterceptors(registry);
////    }
//
//    @Bean
//    public CorsFilter corsFilter()  {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resource = "file:" + localProperties.getPath().replace("\\", "/");
        registry
                .addResourceHandler("/**")
                .addResourceLocations(resource).setCachePeriod(0);
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

//        /**
//         * 配置swagger-ui显示文档
//         */
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

//    @Bean
//    public RequestContextListener requestContextListener(){
//        return new RequestContextListener();
//    }
}
