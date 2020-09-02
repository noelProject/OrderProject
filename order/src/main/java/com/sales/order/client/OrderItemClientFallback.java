/**
 * {@link OrderItemClientFallback}
 */
package com.sales.order.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sales.order.framework.base.BaseResponse;
import com.sales.order.model.OrderItem;

import feign.FeignException;

/**
 * Order Item Client Fallback
 *
 */
public class OrderItemClientFallback implements OrderItemClient {

   private Throwable cause;
   
   public OrderItemClientFallback(Throwable throwable) {
      this.cause = throwable;
   }
   
   /**
    * @see com.sales.order.client.OrderItemClient#addOrderItem(com.sales.order.model.OrderItem)
    */
   @Override
   public ResponseEntity<Void> addOrderItem(OrderItem orderItem) {
      if (cause instanceof FeignException && ((FeignException) cause).status() == HttpStatus.NOT_FOUND.value()) {
         // Exception Stack trace
      }
      return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
    * @see com.sales.order.client.OrderItemClient#retriveOrderItems()
    */
   @Override
   public ResponseEntity<BaseResponse<OrderItem>> retriveOrderItems() {
      if (cause instanceof FeignException && ((FeignException) cause).status() == HttpStatus.NOT_FOUND.value()) {
         // Exception Stack trace
      }
      return new ResponseEntity<>(new BaseResponse<>(), HttpStatus.OK);
   }
   
}
