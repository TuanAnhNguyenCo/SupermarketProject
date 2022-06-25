package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

@Table(catalog = "marketDB",name = "Customer")

public class Customer {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Fullname",length = 40,nullable = false)
	private String fullname;
	
	@Column(name = "Email",length = 100,nullable = false,unique = true)
	private String email;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Customer_points")
	@ColumnDefault("0")
	private double points;
	
	@Column(name = "CCCD",length = 20 ,nullable = false)
	private String CCCD;
	
	@Column(name = "PhoneNumber",length = 15,nullable = false)
	private String PhoneNumber;
	
	
	
	
	
	

}
