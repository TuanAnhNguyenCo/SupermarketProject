package com.example.demo.Controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IProductService;

import java.io.IOError;
import java.io.IOException;


@RestController
@RequestMapping(value = "api/v1/product")
public class ProductController {
	@Autowired
	private IProductService iProductService;

	@Autowired
	private ModelMapper modelMapper;


	@GetMapping("/get")
	public ResponseEntity<?> FindProductById(@RequestParam int id)
	{
		Product product = iProductService.FindProductById(id);
		if(product!=null)
		{
			ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
			return new ResponseEntity<>(productDTO,HttpStatus.OK);
		}

		return new ResponseEntity<>("",HttpStatus.NO_CONTENT);


	}
	
	@PostMapping("/insert")
	public  ResponseEntity<?> InsertProduct(
			@RequestParam String name,@RequestParam String origin,
			@RequestParam String description,@RequestParam MultipartFile image,
			@RequestParam int num_of_products,@RequestParam String dvt,
			@RequestParam int sale,@RequestParam double prices
			)
	{
		boolean status = iProductService.AddProduct(name, origin, description, image, 
														num_of_products, dvt, sale, prices);
        if(status==true)
        	return new ResponseEntity<>("Thêm dữ liệu thành công",HttpStatus.OK);
        else {
        	return new ResponseEntity<>("Thêm dữ liệu thất bại",HttpStatus.BAD_REQUEST);
		}
	}
}
