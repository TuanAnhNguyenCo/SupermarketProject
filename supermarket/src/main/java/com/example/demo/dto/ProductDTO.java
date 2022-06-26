package com.example.demo.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDTO {
	private int id;
	
	private String name;
	
	private String origin;

	private String description;

	private String image;

	private int num_Of_products;

	private String dvt;

	private int sale;
	
	private double prices;
}
