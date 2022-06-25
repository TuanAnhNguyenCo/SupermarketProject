package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Table(catalog = "marketDB",name = "Employee")
public class Employee {
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
	
	@Column(name = "CCCD",length = 20 ,nullable = false)
	private String CCCD;
	
	@Column(name = "PhoneNumber",length = 15,nullable = false)
	private String PhoneNumber;
	
	@Column(name = "Position",length = 30)
	private String position;
}