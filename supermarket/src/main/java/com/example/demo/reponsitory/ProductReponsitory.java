package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
@Repository
public interface ProductReponsitory extends JpaRepository<Product, Integer> {
	
	// Insert product
	@Query(name  = "insert into product(name,origin,description,image,num_of_products,dvt,sale,prices) "
			+ "values(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
	void InsertProduct(String name,String origin,String description, String image,
			int num_of_products,String dvt,int sale, double prices
			) ;
	
	
	

}
