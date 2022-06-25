package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Table(catalog = "marketDB",name = "Product")
public class Product {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Name",length = 40,nullable = false)
	private String name;
	
	@Column(name = "Origin",length = 100,nullable = false)
	private String origin;
	
	@Column(name = "Description",nullable = false)
	private String description;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Num_Of_products")
	private int num_Of_products;
	
	@Column(name = "DVT",length = 30)
	private String dvt;
	
	@Column(name = "Sale")
	@ColumnDefault("0")
	private int sale;
	
	@Column(name = "Prices")
	private double prices;
	
	@ManyToOne
	@JoinColumn(name = "Category_ID")
	private Category category;
	

}
