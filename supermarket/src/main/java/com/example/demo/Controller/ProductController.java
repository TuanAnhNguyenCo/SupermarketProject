package com.example.demo.Controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IProductService;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1/product")
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private IProductService iProductService;

	@Autowired
	private ICategoryService iCategoryService;

	@Autowired
	private ModelMapper modelMapper;

	// Tìm kiếm theo id
	@GetMapping("/get/{id}")
	public ResponseEntity<?> FindProductById(@PathVariable("id") int id)
	{
		Product product = iProductService.FindProductById(id);
		if(product!=null)
		{
			ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
			return new ResponseEntity<>(productDTO,HttpStatus.OK);
		}

		return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
	}

	// Find by name
	@GetMapping()
	public ResponseEntity<?> FindProductByName(@RequestParam String name)
	{
		List<Product> product = iProductService.FindProductByName(name);
		if(product!=null)
		{
			List<ProductDTO> productDTO = modelMapper.map(product,new TypeToken<List<ProductDTO>>(){}.getType());
			return new ResponseEntity<>(productDTO,HttpStatus.OK);
		}

		return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
	}

	// Update
	@GetMapping("/update/{id}")
	public ResponseEntity<?> UpdateProduct(@PathVariable("id") int id)
	{
		Product product = iProductService.FindProductById(id);
		if(product!=null)
		{
			return new ResponseEntity<>(product,HttpStatus.OK);
		}

		return new ResponseEntity<>("",HttpStatus.NO_CONTENT);

	}
	@PutMapping("/update")
	public  ResponseEntity<?> UpdateProduct(@RequestParam int id,
			@RequestParam String name,@RequestParam String origin,
			@RequestParam String description,@RequestParam List<MultipartFile> image,
			@RequestParam int num_of_products,@RequestParam String dvt,
			@RequestParam int sale,@RequestParam double prices,
			@RequestParam int category_id
	)
	{
		boolean status = iProductService.UpdateProduct(id,name, origin, description, image,
				num_of_products, dvt, sale, prices,category_id);
		if(status==true)
			return new ResponseEntity<>("Sưa dữ liệu thành công",HttpStatus.OK);
		else {
			return new ResponseEntity<>("Sửa dữ liệu thất bại",HttpStatus.BAD_REQUEST);
		}
	}






	// Insert product
	@PostMapping("/insert")
	public  ResponseEntity<?> InsertProduct(
			@RequestParam String name,@RequestParam String origin,
			@RequestParam String description,@RequestParam List<MultipartFile> image,
			@RequestParam int num_of_products,@RequestParam String dvt,
			@RequestParam int sale,@RequestParam double prices,
			@RequestParam int category_id
			)
	{
		boolean status = iProductService.AddProduct(name, origin, description, image, 
														num_of_products, dvt, sale, prices,category_id);
        if(status==true)
        	return new ResponseEntity<>("Thêm dữ liệu thành công",HttpStatus.OK);
        else {
        	return new ResponseEntity<>("Thêm dữ liệu thất bại",HttpStatus.BAD_REQUEST);
		}
	}

	// Delete product

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> DeleteProduct(@PathVariable("id") int id)
	{
		int status = iProductService.DeleteProduct(id);
		if (status==0)
			return new ResponseEntity<>("This product doesn't exist",HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>("This product was deleted",HttpStatus.OK);
	}

}
