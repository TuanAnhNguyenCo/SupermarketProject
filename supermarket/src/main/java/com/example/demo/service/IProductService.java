package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IProductService {
	
	boolean InsertProduct(String name,String origin,String description, MultipartFile image,
			int num_of_products,String dvt,int sale, double prices
			) ;
}
