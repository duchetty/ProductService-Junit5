package com.jm.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jm.entity.Product;
import com.jm.repository.ProductRepositoy;
import com.jm.service.ProductService;

@SpringBootTest
public class TestProductService {

		@MockBean
		private ProductRepositoy repository;
		
		@Autowired
		private ProductService service;
		
		private Product p1;
		private Product p2;
		
		@BeforeEach
		public void setUp()
		{
			p1=new Product(1,"tv",1,58000);
			p2=new Product(2,"remote",1,280);
		
		}
		
		@Test
		public void testInsertProduct()
		{
			when(repository.save(p1)).thenReturn(p1);
			
			Product product=service.insertProduct(p1);
			assertNotNull(product);
			assertEquals("tv",product.getProductName());
			verify(repository ,times(1)).save(product);
		}
		
		@Test 
		public void testGetProduct()
		{
			when(repository.findById(1)).thenReturn(Optional.of(p1));
			Product foundProduct=service.getProduct(1);
			assertEquals(58000,foundProduct.getPrice());
			verify(repository,times(1)).findById(1);
		}
		
		@Test
		public void testGetAllProducts()
		{
			when(repository.findAll()).thenReturn(Arrays.asList(p1,p2));
			List<Product> list=service.getAllProducts();
			assertEquals(2,list.size());
			verify(repository,times(1)).findAll();
		}
}
