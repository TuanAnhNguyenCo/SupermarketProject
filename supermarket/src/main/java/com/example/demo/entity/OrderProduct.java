package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(catalog = "marketDB" ,name = "Order_Product")

public class OrderProduct {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "OrderStatus",length = 40,nullable = false)
	@ColumnDefault("chờ xác nhận")
	private String OrderStatus;
	
	@Column(name = "Created_at",insertable = false,nullable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date Created_at;
	
	@Column(name = "Delivery_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date Delivery_date;
	
	@Column(name = "Payment_status")
	@ColumnDefault("false")
	private Boolean Payment_status;
	
	@Column(name = "Total_price")
	private double Total_price;
	
	@Column(name = "Original_total_price")
	private double Original_total_price;
	
	@Column(name = "Address",length = 1000)
	private double points;
	
	@ManyToOne
	@JoinColumn(name = "Customer_ID")
	private Customer customer;
	
	

}
