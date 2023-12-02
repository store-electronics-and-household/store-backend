package ru.acceleration.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/*@Configuration
@EnableSwagger2*/
public class SpringFoxConfig {
/*    @Bean
    public Docket mainApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.acceleration.store.security.controller")
                        .or(RequestHandlerSelectors.basePackage("ru.acceleration.store.controller")))
                .paths(PathSelectors.any())
                .build();
               // .apiInfo(apiInfo());
    }*/

/*    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Softplat Backend REST API",
                "Describes API for backend of Softplat Project",
                null, null, null, null, null, Collections.emptyList()
        );
    }*/
}
