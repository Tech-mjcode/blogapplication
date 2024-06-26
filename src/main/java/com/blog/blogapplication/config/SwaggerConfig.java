package com.blog.blogapplication.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }

    private List<SecurityReference> sf(){
        AuthorizationScope scopes = new AuthorizationScope("global", "accessEveryThing");
        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
    }
    @Bean
    public Docket api(){
        
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(getApiInfo())
                    .securityContexts(securityContexts())
                    .securitySchemes(Arrays.asList(apiKey()))
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Blogging Application" , "This project is developed by Manash jyoti Handiue", "1.0", "Terms of service", new Contact("Manash jyoti Handique","website url","mj@gmail.com"),"License of apis","Api licences url",Collections.emptyList() );
    }
}
