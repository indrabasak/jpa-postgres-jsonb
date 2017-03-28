package com.basaki.example.postgres.jsonb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {@code SwaggerConfiguration} is the configuration for setting up swagger for
 * the author controller. The swagger documentation can be viewed at {@code
 * http://<host>:<port>/swagger-ui-html}
 * <p/>
 *
 * @author Indra Basak
 * @since 2/23/17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Creates the Swagger configuration bean.
     *
     * @return docket bean
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("book")
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.basaki.example.postgres.jsonb.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo("Book API with Postgres JSONB",
                        "Book Service API Powered By PostgreSQL"));
    }

    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @param title       API title
     * @param description API description
     * @return API information
     */
    private ApiInfo apiInfo(String title, String description) {
        Contact contact = new Contact("Indra Basak", "",
                "developer@gmail.com");
        return new ApiInfo(title, description, "1.0", "terms of controller url",
                contact, "license", "license url");
    }
}
