package com.example.demo.Controller;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.IProductService;



@RestController
@RequestMapping(value = "api/v1/home")
@CrossOrigin("*")
public class HomeController {

	@Autowired
	private IProductService iProductService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> Home()
	{
		List<Product> products = iProductService.GetProductList();
		List<ProductDTO> productDTOs = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {}.getType());
		return new ResponseEntity<>(productDTOs,HttpStatus.OK);
	}
}
