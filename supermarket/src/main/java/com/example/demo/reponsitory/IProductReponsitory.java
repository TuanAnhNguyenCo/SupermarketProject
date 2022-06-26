package com.example.demo.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;




public interface IProductReponsitory extends JpaRepository<Product, Integer>{

		@Query(value = "select * from product",nativeQuery = true)
		List<Product> GetProductList();
		
		@Modifying
		@Transactional
		@Query(value  = "insert into product(name,origin,description,image,num_of_products,dvt,sale,prices) "
				+ "values(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
		public void InsertProduct(String name,String origin,String description, String image,
				Integer num_of_products,String dvt,Integer sale, Double prices) ;
		

	

		
}
