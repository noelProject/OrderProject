/**
 * {@link FeignRequestInterceptor}
 */
package com.sales.order.interceptor;

import static com.sales.order.constants.ServiceClientConstants.X_AUTH_USER_ID_HEADER;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sales.order.constants.ApplicationConstants;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Intercept all feign request (Inter-service calls)
 *
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

   /**
    * @see feign.RequestInterceptor#apply(feign.RequestTemplate)
    */
   @Override
   public void apply(RequestTemplate template) {
      // Thread Local is not empty, then attach it to request headers
      Map<String, Collection<String>> requestHeaders = new HashMap<>();
      requestHeaders.put(X_AUTH_USER_ID_HEADER, Collections.singleton(ApplicationConstants.DEFAULT_USER));
      // Set Headers
      template.headers(requestHeaders);
   }

}
