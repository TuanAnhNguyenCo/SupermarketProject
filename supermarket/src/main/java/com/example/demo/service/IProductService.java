package com.example.demo.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;


public interface IProductService {

	boolean AddProduct(String name,String origin,String description, List<MultipartFile> image,
			int num_of_products,String dvt,int sale, double prices, int category_id
			) ;

	boolean UpdateProduct(int id,String name,String origin,String description, List<MultipartFile> image,
					   int num_of_products,String dvt,int sale, double prices, int category_id
	) ;
	List<Product> GetProductList();

	Product FindProductById(int id);

	List<Product> FindProductByName(String name);

	int DeleteProduct(int id);

}
