package com.impurity.twitchwebintegrator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static com.impurity.twitchwebintegrator.constant.Profiles.LOCAL;
import static com.impurity.twitchwebintegrator.constant.Profiles.STAGING;

/**
 * @author tmk2003
 */
@Configuration
@EnableSwagger2
@Profile({LOCAL, STAGING})
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact(
            "Tyler Kokoszka", "https://tmk2003.github.io/Personal-Website/", "techkoko77@gmail.com");

    private static final Collection<VendorExtension> DEFAULT_VENDOREXTENTIONS = new LinkedList<>();

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Web Integrator",
            "A Restful API to query apis", "1.0.0",
            "N/A", DEFAULT_CONTACT,
            "GNU General Public License v3.0",
            "https://github.com/tmk2003/twitch-web-integrator/blob/master/LICENSE",
            DEFAULT_VENDOREXTENTIONS
    );

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.impurity.twitchwebintegrator.controller"))
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}