package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IProductService;

@RestController
@RequestMapping(value = "api/v1/home")
public class ProductController {
	@Autowired
	private IProductService iProductService;
	
	@GetMapping()
	public ResponseEntity<?> Home()
	{
		return new ResponseEntity<>("Hello world",HttpStatus.OK);
	}
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseEntity<?> InsertProduct(
			@RequestParam String name,@RequestParam String origin,
			@RequestParam String description,@RequestParam MultipartFile image,
			@RequestParam int num_of_products,@RequestParam String dvt,
			@RequestParam int sale,@RequestParam double prices
			)
	{
		boolean status = iProductService.InsertProduct(name, origin, description, image, 
														num_of_products, dvt, sale, prices);
        if(status==true)
        	return new ResponseEntity<>("Thêm dữ liệu thành công",HttpStatus.OK);
        else {
        	return new ResponseEntity<>("Thêm dữ liệu thất bại",HttpStatus.BAD_REQUEST);
		}
	}
}
