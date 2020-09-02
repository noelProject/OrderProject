/**
 * {@link OrderWebConfiguration}
 */
package com.sales.order.config;

import static com.sales.order.constants.ServiceClientConstants.CONNECTION_TIMEOUT;
import static com.sales.order.constants.ServiceClientConstants.READ_TIMEOUT;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sales.order.interceptor.RequestHandlerInterceptor;

import feign.Contract;
import feign.Feign;
import feign.Request;
import feign.hystrix.HystrixFeign;

/**
 * Order Web Configuration route request and Handle types
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableAspectJAutoProxy
public class OrderWebConfiguration implements WebMvcConfigurer, BeanPostProcessor {

   /**
    * Default Constructor - Initialize
    */
   public OrderWebConfiguration() {}
   
   /**
    * Hystrix Feign supported Client
    * 
    * @return
    */
   @Bean
   @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public Feign.Builder feignBuilder() {
      return HystrixFeign.builder();
   }
   
   /**
    * Enable Feign contract
    * 
    * @return Feign Contract
    */
   @Bean
   public Contract feignContract() {
      return new Contract.Default();
   }
   
   /**
    * Feign Request options
    * 
    * @param env
    * @return Timeout for feign requests
    */
   @Bean
   public Request.Options requestOptions(ConfigurableEnvironment env) {
      return new Request.Options(CONNECTION_TIMEOUT, TimeUnit.SECONDS, READ_TIMEOUT, TimeUnit.SECONDS, true);
   }
   
   /**
    * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object,
    *      java.lang.String)
    */
   @Override
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
      // Return final Bean
      return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
   }

   public void addInerceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(new RequestHandlerInterceptor()).excludePathPatterns("/swagger**");
   }
}
