/**
 * {@link OrderItemClient}
 */
package com.sales.order.client;

import static com.sales.order.constants.ServiceClientConstants.ACCEPT_APPLICATION_JSON;
import static com.sales.order.constants.ServiceClientConstants.API_CREATE_ORDER_ITEM;
import static com.sales.order.constants.ServiceClientConstants.API_RETRIVE_ORDER_ITEMS;
import static com.sales.order.constants.ServiceClientConstants.CONTENT_TYPE_APPLICATION_JSON;
import static com.sales.order.constants.ServiceClientConstants.ORDER_ITEM_BASE_PATH;
import static com.sales.order.constants.ServiceClientConstants.ORDER_ITEM_INSTANCE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import com.sales.order.framework.base.BaseResponse;
import com.sales.order.model.OrderItem;

import feign.Headers;
import feign.RequestLine;

/**
 * Order Item Service Client
 */
@Headers({ ACCEPT_APPLICATION_JSON })
@FeignClient(name = ORDER_ITEM_INSTANCE, path = ORDER_ITEM_BASE_PATH, fallbackFactory = OrderItemClientFallbackFactory.class)
public interface OrderItemClient {

   @Headers({ CONTENT_TYPE_APPLICATION_JSON })
   @RequestLine(value = API_CREATE_ORDER_ITEM)
   ResponseEntity<Void> addOrderItem(OrderItem orderItem);
   
   @Headers({ CONTENT_TYPE_APPLICATION_JSON })
   @RequestLine(value = API_RETRIVE_ORDER_ITEMS)
   ResponseEntity<BaseResponse<OrderItem>> retriveOrderItems();
   
}
