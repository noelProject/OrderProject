/**
 * {@link RequestHandlerInterceptor}
 */
package com.sales.order.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Default Request Handler Interceptor for any request
 *
 */
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

   /**
    * No Args Constructor
    */
   public RequestHandlerInterceptor() {}
   
   /**
    * Request Handler entry - handle any Thread level config
    *   - To Initialize ThreadLocal values and use it anywhere in the application request context
    *   
    * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
    *      javax.servlet.http.HttpServletResponse, java.lang.Object)
    */
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      Enumeration<String> headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()) {
         // Header request values and preset to Thread local / MDC (logging) purpose
      }
      return super.preHandle(request, response, handler);
   }
   
   /**
    * Post Handle request handler
    *   - Remove ThreadLocal instance values and MDC removes / any resource utilization free-up process
    *   
    * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
    *      javax.servlet.http.HttpServletResponse, java.lang.Object,
    *      org.springframework.web.servlet.ModelAndView)
    */
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
      super.postHandle(request, response, handler, modelAndView);
      // Thread Locale / MDC clear
   }
   
}
