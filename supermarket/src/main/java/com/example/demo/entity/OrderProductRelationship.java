package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@Table(catalog = "marketDB",name = "Order_Product_relationship")
public class OrderProductRelationship {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Order_ID")
	private OrderProduct orderProduct;
	
	@ManyToOne
	@JoinColumn(name = "Product_ID")
	private Product product;
	
	@Column(name = "Num_Of_Products")
	private int num_Of_Products;
}
