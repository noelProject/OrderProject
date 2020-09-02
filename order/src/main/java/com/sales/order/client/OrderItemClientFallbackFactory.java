/**
 * {@link OrderItemClientFallbackFactory}
 */
package com.sales.order.client;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * Order Item Client Fall back Factory
 *
 */
@Component
public class OrderItemClientFallbackFactory implements FallbackFactory<OrderItemClient> {

   /**
    * OrderItem Client Create
    * 
    * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
    */
   @Override
   public OrderItemClient create(Throwable throwable) {
      return new OrderItemClientFallback(throwable);
   }

}
