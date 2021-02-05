package com.tenpo.app.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
						.apiInfo(getApiInfo())
						.securityContexts(Arrays.asList(securityContext()))
						.securitySchemes(Arrays.asList(apiKey()))
						.select()
						.apis(RequestHandlerSelectors.basePackage("com.tenpo.app.controllers"))
						.build();
	}


	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
						.securityReferences(defaultAuth())
						.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
						= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Tenpo Challenge App")
						.description("App for a challenge of tenpo company")
						.version("1.0")
						.contact(new Contact("Juan Fausto Peralta",
										"https://www.linkedin.com/in/juan-fausto-peralta-8a872094/",
										"juuan.peralta@gmail.com")).build();

	}
}
