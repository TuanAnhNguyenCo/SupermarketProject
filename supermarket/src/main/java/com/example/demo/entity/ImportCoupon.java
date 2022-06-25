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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(catalog = "marketDB" ,name = "ImportCoupon")

public class ImportCoupon {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "Import_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date import_at;
	
	@Column(name = "Total_prices")
	private double Total_prices;
	
	@Column(name = "Import_Status")
	@ColumnDefault("false")
	private boolean import_Status;
	
	@ManyToOne
	@JoinColumn(name = "Customer_ID")
	private Employee employee;
	
}

