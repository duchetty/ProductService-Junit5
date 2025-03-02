package com.jm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jm.entity.Product;
import com.jm.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product prodcut)
	{
		Product product=productService.insertProduct(prodcut);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
		 
	}
	
	@GetMapping("/getProduct/{productId}")
	public ResponseEntity getProduct(@PathVariable int  productId)
	{
		Product status=productService.getProduct(productId);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts()
	{
		return productService.getAllProducts();
	}
}
