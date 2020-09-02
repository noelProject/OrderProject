package com.sales.order.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sales.order.framework.base.BaseBO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "OrderTable")
public class Order extends BaseBO {


	public Order(String customerName, Date orderDate, String shippingAddress, double total) {
		super();
		//this.orderItem = orderItem;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.total = total;
	}
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private Long orderItem;
    private String customerName;
    //@Temporal(TemporalType.DATE)
    private Date orderDate;
    private String shippingAddress;
    private double total;

}
