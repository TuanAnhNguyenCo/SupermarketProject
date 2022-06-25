package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(catalog = "marketDB",name = "ImportCoupon_Product")
public class ImportCouponProduct {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "ImportCoupon_ID")
	private ImportCoupon importCoupon;
	
	@ManyToOne
	@JoinColumn(name = "Product_ID")
	private Product product;
	
	@Column(name = "Num_Of_Products")
	private int num_Of_Products;
	
	

}
