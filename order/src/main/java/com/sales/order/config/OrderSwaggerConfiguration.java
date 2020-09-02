/**
 * {@link OrderSwaggerConfiguration}
 * 
 */
package com.sales.order.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sales.order.constants.ApplicationConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration
 */

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(value = ApplicationConstants.BASE_PACKAGE)
public class OrderSwaggerConfiguration implements WebMvcConfigurer{

   @Value("${api.contact.name}")
   private String contactName;
   
   @Value("${api.contact.url}")
   private String contactUrl;
   
   @Value("${api.contact.email}")
   private String contactEmail;
   
   @Value("${api.title}")
   private String apiTitle;
   
   @Value("${api.version}")
   private String apiVersion;
   
   @Value("${gui.protocol}")
   private String guiProtocol;
   
   @Value("${gui.host}")
   private String guiHost;
   
   @Value("${gui.port}")
   private String guiPort;
   
   private static final String SWAGGER_HTML = "swagger-ui.html";
   private static final String WEBJARS = "/webjars/**";
   private static final String RESOURCE_CONFIG = "classpath:/META-INF/resources/";
   private static final String RESOURCE_WEBJARS = "classpath:/META-INF/resources/webjars/";
   private static final List<String> DEFAULT_PERMIT_METHODS = Collections
         .unmodifiableList(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
               HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
   
   /**
    * This method creates Docket Bean and helps to renders the swagger-ui page
    * 
    * @return
    */
   @Bean
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
            .select() //
            .apis(RequestHandlerSelectors.any()) //
            .paths(PathSelectors.any()) //
            .build();
   }
   
   /**
    * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(SWAGGER_HTML).addResourceLocations(RESOURCE_CONFIG);
      registry.addResourceHandler(WEBJARS).addResourceLocations(RESOURCE_WEBJARS);
   }
   
   /**
    * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
    */
   @Override
   public void addCorsMappings(CorsRegistry registry) {
      StringBuilder serviceMapping = new StringBuilder();
      serviceMapping.append(guiProtocol).append("://").append(guiHost).append(":").append(guiPort);
      registry //
         .addMapping("/**") //
         .allowedOrigins(serviceMapping.toString()) //
         .allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "**", HttpHeaders.CONTENT_TYPE) //
         .allowedMethods(DEFAULT_PERMIT_METHODS.toArray(new String[DEFAULT_PERMIT_METHODS.size()]));
   }
   
}
