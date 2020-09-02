/**
 * {@link OrderApplication}
 */
package com.sales.order;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.sales.order.model.Order;
import com.sales.order.repository.OrderRepository;

/**
 * Order Application Bootstrap configuration
 * 
 * - Discovery Service support and client implementation
 * - Feign Clients supported for inter-service HTTP calls
 * - Circuit Breaker supported for Service Resilience
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
public class OrderApplication {

   /**
    * Application main method to start the spring boot app
    * 
    * @param args
    */
   public static void main(String[] args) {
      SpringApplication.run(OrderApplication.class, args);
   }

	// init bean to insert 3 orders into h2 database.
   @Bean
   CommandLineRunner initDatabase(OrderRepository repository) {
       return args -> {
    	   repository.save(new Order("Customer Name1", new Date(), "LORONG 1", 10.10));
    	   repository.save(new Order("Customer Name2", new Date(), "LORONG 2", 20.20));
    	   repository.save(new Order("Customer Name3", new Date(), "LORONG 3", 30.30));
       };
   }
}
