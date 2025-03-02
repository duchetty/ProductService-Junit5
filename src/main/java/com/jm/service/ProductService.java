package com.jm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jm.entity.Product;
import com.jm.repository.ProductRepositoy;

@Service
public class ProductService {

	@Autowired
	private ProductRepositoy productRepo;

	public Product insertProduct(Product product)
	{
		return productRepo.save(product);
	}
	
	public Product getProduct(int productId)
	{
		return productRepo.findById(productId).orElse(null);
	}
	
	public List<Product> getAllProducts()
	{
		return productRepo.findAll();
	}

}
