package com.opaywallet.assessment.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api/v1.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("OPay Test")
                .description("My submission for the OPay Test")
                .termsOfServiceUrl("http://www.opay-inc.com")
                .contact("tech@opay-inc.com").license("MIT Documentation License")
                .licenseUrl("tech@opay-inc.com").version("1.0").build();
    }

}
