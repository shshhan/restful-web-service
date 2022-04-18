package com.example.restfulwebservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //springfox.documentation.service.Contact;
    private static final Contact DEFAULT_CONTACT = new Contact("Shawn Han", "https://velog.io/@shawnhansh", "hansh9501@naver.com");

    private static final ApiInfo DEFAULT_API_INFO
            = new ApiInfo("API Title", "My User management REST API service", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licences/LICENSE-2.0", new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES
            = new HashSet<>(Arrays.asList("application/json", "application/xml"));


    @Bean
    public Docket api(){
        //이 메서드에서 설정한 항목들이 swagger 페이지에 나타나게 된다.
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                ;
    }

    //Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
    //application.yml에서 세팅값 변경으로 해결
    //localhost:port/swagger-ui/index.html이 안나오는 오륲
    //pom.xml에 dependency 추가하여 해결
}
