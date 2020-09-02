/**
 * {@link ServiceClientConstants}
 */
package com.sales.order.constants;

/**
 * Service Client Constants
 */
public final class ServiceClientConstants {

   private ServiceClientConstants() {
      throw new RuntimeException("Create instances are not allowed");
   }
   //
   public static final String X_AUTH_USER_ID_HEADER = "X-Auth-User-Id";
   //
   public static final String ACCEPT_HEADER = "Accept";
   public static final String ACCEPT_APPLICATION_JSON = "Accept: application/json";
   public static final String ACCEPT_APPLICATION_XML = "Accept: application/xml";
   public static final String CONTENT_TYPE_HEADER = "Content-Type";
   public static final String CONTENT_TYPE_APPLICATION_JSON = "Content-Type: application/json";
   public static final String CONTENT_TYPE_APPLICATION_XML = "Content-Type: application/xml";
   //
   public static final String ORDER_ITEM_BASE_PATH = "/orderitem/api";
   public static final String API_CREATE_ORDER_ITEM = "POST /add/order-item";
   public static final String API_RETRIVE_ORDER_ITEMS = "POST /retrive/order-items";
   //
   public static final String SERVICE_INSTANCE = "${spring.application.name}";
   public static final String ORDER_ITEM_INSTANCE = "OrderItemService";
   public static final String CREATE_ORDER_ITEM_URI = "/orderitem/api/add/order-item";
   public static final String RETRIVE_ORDER_ITEMS_URI = "/orderitem/api/retrive/order-items";
   //
   public static final int CONNECTION_TIMEOUT = 3000;
   public static final int READ_TIMEOUT = 5000;
   
}
