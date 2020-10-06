package com.gerenciadorsessoesvotacao.core;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.gerenciadorsessoesvotacao.controller"))
          .paths(PathSelectors.any())
          .build()
          .useDefaultResponseMessages(false)                                   
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
          .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
          .apiInfo(apiInfo());
    }
	
    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {
            private static final long serialVersionUID = 1L;

            {
            add(new ResponseMessageBuilder()   
                .code(500)
                .message("Internal Server Error")
                .build());
            add(new ResponseMessageBuilder() 
                .code(204)
                .message("No Content!")
                .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPOST()
    {
        return new ArrayList<ResponseMessage>() {
            private static final long serialVersionUID = 1L;

            {
            add(new ResponseMessageBuilder()   
                .code(201)
                .message("Created!")
                .build());
            add(new ResponseMessageBuilder()   
                .code(500)
                .message("Internal Server Error")
                .build());
        }};
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("API de controle de sessões de votação")
	            .description("Um exemplo de aplicação Spring Boot REST API")
	            .version("0.0.1-SNAPSHOT")
	            .contact(new Contact("Erick Teixeira", "https://github.com/ErickHMT/", "erickhenriquemota@gmail.com"))
	            .build();
	}

}