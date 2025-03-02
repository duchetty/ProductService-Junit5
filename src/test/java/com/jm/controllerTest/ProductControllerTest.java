package com.jm.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.controller.ProductController;
import com.jm.entity.Product;
import com.jm.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@MockBean
	ProductService productService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void Test_saveProduct() throws Exception
	{
		Product product=new Product(1,"tv",1,15000);
		when(productService.insertProduct(product)).thenReturn(product);
		
			ObjectMapper mapper=new ObjectMapper();
			String productJson=mapper.writeValueAsString(product);
			
			MockHttpServletRequestBuilder requstBuilder=MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON).content(productJson);
			ResultActions perform=mockMvc.perform(requstBuilder);
			MvcResult andReturn=perform.andReturn();
			MockHttpServletResponse response=andReturn.getResponse();
			int status=response.getStatus();
			assertEquals(201,status);
	
	}
	
	@Test
	public void Test_getProduct() throws Exception {
		
		Product product=new Product(1,"tv",1,15000);
		when(productService.getProduct(1)).thenReturn(product);
		
		MockHttpServletRequestBuilder requstBuilder=MockMvcRequestBuilders.get("/getProduct/{productId}", 1);
		int status=	mockMvc.perform(requstBuilder).andReturn().getResponse().getStatus();
        assertEquals(200, status);
		
	}
	
	
	@Test
	public void Test_getAllProducts() throws Exception
	{
		
		List<Product> products=Arrays.asList(new Product(2,"mobile charger", 1, 500), new Product(3, "mouse with keyboard", 1 ,2500), new Product(4, "mobile",1, 25000));
		when(productService.getAllProducts()).thenReturn(products);
		
		MockHttpServletRequestBuilder requstBuilder=MockMvcRequestBuilders.get("/getAllProducts");
		int status=	mockMvc.perform(requstBuilder).andReturn().getResponse().getStatus();
        assertEquals(200, status);
	
	}


}
