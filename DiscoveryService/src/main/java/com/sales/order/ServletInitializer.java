/**
 * {@link ServletInitializer}
 */
package com.sales.order;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet Initializer
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

   /**
    * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
    */
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(DiscoveryServiceApplication.class);
   }
   
}
