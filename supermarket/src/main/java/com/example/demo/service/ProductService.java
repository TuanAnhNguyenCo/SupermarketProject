package com.example.demo.service;

import java.io.File;

import java.io.IOException;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.reponsitory.IProductReponsitory;


@Service
public class ProductService implements IProductService{
//	@Value("${upload.path}")
//	private String imageFolder;
	@Autowired
	private IProductReponsitory productReponsitory;
	
	@Override
	
	// Thêm sản phẩm
	public boolean AddProduct(String name,String origin,String description, List<MultipartFile> image,
			int num_of_products,String dvt,int sale, double prices)
	{
		UUID uuid ;
		// Lấy đường dẫn đến thư mục
		String imageFolder = System.getProperty("user.dir") +"/src/main/resources/static";
		String imageName;
		String imageUrl;
		String url = "";
		for (MultipartFile img:image)
		{
			do
			{
				uuid = UUID.randomUUID();
				imageName = img.getOriginalFilename();
				imageName = imageName.substring(imageName.indexOf('.'));
				imageUrl = "/images/" +uuid + imageName;
			}while (new File(imageFolder + imageUrl ).exists());

			try{
				FileCopyUtils.copy(img.getBytes(), new File(imageFolder+imageUrl));
			}catch (IOException e)
			{
				return false;
			}

			url = url + imageUrl +" ";
		}
		url = url.strip();


//         Insert data
		try {
			productReponsitory.InsertProduct(name, origin, description,
					url,
					 num_of_products, dvt, sale, prices);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	// Lấy ra danh sách các sản phẩm
	public List<Product> GetProductList(){
		return productReponsitory.GetProductList();
	}
	// Tìm kiếm theo id
	public Product FindProductById(int id)
	{
		return productReponsitory.FindProductByID(id);
	}
	// Tìm kiếm theo name
	public List<Product> FindProductByName(String name)
	{
		return productReponsitory.FindProductByName(name);
	}

	// Xoá sản phẩm theo id
	public int DeleteProduct(int id)
	{
		return productReponsitory.DeleteProduct(id);
	}
}
