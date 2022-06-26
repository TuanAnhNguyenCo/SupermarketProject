package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.reponsitory.IProductReponsitory;


@Service
public class ProductService implements IProductService{
	@Value("${upload.path}")
	private String imageFolder; 
	@Autowired
	private IProductReponsitory productReponsitory;
	
	@Override
	
	// Thêm sản phẩm
	public boolean AddProduct(String name,String origin,String description, MultipartFile image,
			int num_of_products,String dvt,int sale, double prices)
	{
		
		
		String fileName = image.getOriginalFilename();
		String filePath =   imageFolder +"/"+ fileName;
		Random generator = new Random(19900828);
		long random;
		// nếu file tồn tại thì thêm đuôi ở file
		// có thể có nhiều người dùng đặt tên file giống nhau
		while (new File(filePath).exists())
		{
			random = generator.nextInt(10000000) + 1;
			filePath = filePath.replace(".", random+".");
		}

        // Insert data
		try {
			productReponsitory.InsertProduct(name, origin, description, filePath, num_of_products, dvt, sale, prices);
		} catch (Exception e) {
			return false;
		}
		// Copy file
        try {
            FileCopyUtils.copy(image.getBytes(), new File(filePath));
        } catch (IOException e) {
            return false;
        }
		return true;
	}
	
	// Lấy ra danh sách các sản phẩm
	public List<Product> GetProductList(){
		return productReponsitory.GetProductList();
	}

}
